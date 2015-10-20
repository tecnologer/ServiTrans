package BRO;

import javax.swing.JOptionPane;
import servitrans.Session;
import tools.Util;
import tools.manejador;

public class pagos 
{
    
    public static manejador recibirPago(String monto,String montoventa, char tipo)
    {
        try
        {
            if(Util.isFloat(monto))
            {
                float pago=Float.parseFloat(monto);
                float total=Float.parseFloat(montoventa);
                float cambio=0;
                float restante=0;
                String nu_autoriza="";
                switch(tipo)
                {
                    case '1':
                        if(pago>=total)
                        {
                            cambio=pago-total;
                            restante=0;
                        }
                        else
                        {
                            cambio=0;
                            restante=total-pago;
                        }                        
                        break;
                    case '2':
                        if(pago>total)
                            return Session.mng=new manejador(true, "El pago no puede superar el total");
                        
                        cambio=total-pago;
                        restante=cambio;
                        nu_autoriza=JOptionPane.showInputDialog("Numero de autorización:");
                        break;
                }
                agregarDetallePago(total,pago,cambio,restante,tipo,nu_autoriza);
            }
            else
                return Session.mng=new manejador(true, "Monto invalido");
            return Session.mng=new manejador(false, "");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Session.mng=new manejador(true, "Error inesperado.\nDetalle: "+e.getMessage());
        }
    }
    
    public static void agregarDetallePago(float total,float pago,float cambio,float restante,char tipo,String nu_autoriza)
    {
        int row=Session.lineaVent.getRowCount();
        Object n[][]=new Object [1][5];
            Session.lineaVent.addRow(n);
            Session.lineaVent.setValueAt("", row, 0);//id
            Session.lineaVent.setValueAt("", row, 1);//descripcion
            Session.lineaVent.setValueAt("",row, 2);//importe
            switch(tipo)
            {
                case '1': 
                    Session.lineaVent.setValueAt("Efectivo:", row, 3);//cantidad
                    Session.lineaVent.setValueAt(servitrans.Formatea.alinder("$ ###,###.00", pago), row, 4);//mont0
                    break;
                case '2': 
                    Session.lineaVent.setValueAt("Tarjeta:", row, 3);//cantidad
                    Session.lineaVent.setValueAt(servitrans.Formatea.alinder("$ ###,###.00", pago), row, 4);//mont0
                    row=Session.lineaVent.getRowCount(); 
                    Session.lineaVent.addRow(n);                   
                    Session.lineaVent.setValueAt("", row, 0);//id
                    Session.lineaVent.setValueAt("", row, 1);//descripcion
                    Session.lineaVent.setValueAt("",row, 2);//importe
                    Session.lineaVent.setValueAt("Autorización: ", row, 3);//cantidad
                    Session.lineaVent.setValueAt(nu_autoriza, row, 4);
                    break;
            }
            
        if(restante==0)
        {   
            row=Session.lineaVent.getRowCount(); 
            Session.lineaVent.addRow(n);
            Session.lineaVent.setValueAt("", row, 0);//id
            Session.lineaVent.setValueAt("", row, 1);//descripcion
            Session.lineaVent.setValueAt("",row, 2);//importe
            Session.lineaVent.setValueAt("Cambio:", row, 3);
            if(cambio>0)
                Session.lineaVent.setValueAt(servitrans.Formatea.alinder("$ ###,###.00", cambio), row, 4);
            else
                Session.lineaVent.setValueAt("$ 0.00", row, 4);
            
            DAO.ventas.agregarVenta(Session.cnx, DAO.ventas.getNextId(Session.cnx), Session.fecha, servitrans.venta.cantArticulos.getText(), 
                                    Float.parseFloat(Util.quitarFormatoMoneda(servitrans.venta.montoVenta.getText())), Session.usuario);
            
            Object ventaActu[][]=DAO.ventaActual.getCurrentSale(Session.cnx);
            for(int i=0;i<ventaActu.length;i++)
            {
                if(DAO.kits.isKit(Session.cnx, ""+ventaActu[i][5]))
                {
                    DAO.kitDetalle.updateCantidad(Session.cnx, ""+ventaActu[i][5], ""+ventaActu[i][0], -Integer.parseInt(""+ventaActu[i][3]),""+ventaActu[i][7]);
                    DAO.kits.setComplete(Session.cnx, ""+ventaActu[i][5], ""+ventaActu[i][7], "0");
                }
                else if(DAO.refacciones.isRefaccion(Session.cnx, ""+ventaActu[i][0], null))
                {
                    DAO.refacciones.updateCantidad(Session.cnx, ""+ventaActu[i][0], -Integer.parseInt(""+ventaActu[i][3]),""+ventaActu[i][7]);
                }
                else if(DAO.kits.isKit(Session.cnx, ""+ventaActu[i][0]))
                {
                    DAO.kits.updateCantidad(Session.cnx, ""+ventaActu[i][0], -Integer.parseInt(""+ventaActu[i][3]),""+ventaActu[i][7]);
                }
                
            }
            DAO.ventaActual.clearDatabase(Session.cnx);            
            servitrans.venta.isNewVenta=true;
            servitrans.venta.montoVenta.setText("$ 0.00");
            servitrans.venta.cantArticulos.setText("0");
        }
        else            
            servitrans.venta.montoVenta.setText(""+servitrans.Formatea.alinder("$ ###,###.00", restante));
        
        servitrans.venta.txt_codigo.setText("");
        servitrans.venta.txt_codigo.requestFocus();
            
    }
}
