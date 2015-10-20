package BRO;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import servitrans.Formatea;
import servitrans.Session;
import static servitrans.venta.tablaVenta;
import tools.Util;
import tools.manejador;
import tools.msj;

public class lineaVenta 
{
    
    static int cantidadArt;
    static String ubicacion2;
    public static manejador nueva(String codigo)
    {
        int opc;
        try
        {
            if(Session.ubicacion.equals("Sucursal"))
                ubicacion2="Almacen";
            else
                ubicacion2="Sucursal";
            
            String codYcant[]=Util.splitCantCod(codigo);
            if(Util.isNumeric(codYcant[0]))
            {
                int cantidad=Integer.parseInt(codYcant[0]);
                cantidadArt=cantidad;
                if(Util.isLong(codYcant[1]))
                {
                    //VERIFICAMOS SI ES UNA REFACCION
                    if(DAO.refacciones.isRefaccion(Session.cnx, codYcant[1], null))
                    {
                        if(DAO.refacciones.getCantidad(Session.cnx, codYcant[1], Session.ubicacion)>0)
                            agregarLineaventa(DAO.refacciones.getRefaccionPorID(Session.cnx, codYcant[1], Session.ubicacion),Session.ubicacion);
                        //BUSCAMOS LA REFACCION ENTRE LOS KITS LOCALES
                        else if(DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], Session.ubicacion).length>0)
                        {
                            opc=msj.cf("En kit", "La refacción se encuentra en un kit.\n¿Desea continuar?");
                                
                            if(opc== JOptionPane.YES_OPTION)
                            {
                                Object data[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], Session.ubicacion);
                                String id_kit="";
                                boolean completo=true;
                                //BUSCAMOS UN KIT INCOMPLETO
                                for(int i=0;i<data.length;i++)
                                {
                                    id_kit=DAO.kitsIncompletos.getId_kit(Session.cnx, ""+data[i][0], Session.ubicacion);

                                    if(!id_kit.equals(""))
                                    {
                                        completo=false;
                                        break;
                                    }
                                    else
                                    {
                                        completo=true;
                                        id_kit=""+data[0][6];
                                    }
                                }
                                if(completo)
                                    DAO.kitsIncompletos.agregarId_kit(Session.cnx, id_kit, Session.ubicacion);

                                agregarLineaventa(DAO.kitDetalle.getDetallePorKit(Session.cnx, id_kit, codYcant[1], Session.ubicacion),Session.ubicacion);
                            }
                        }                                
                        //BUSCAMOS LA REFACCION EN UBICACION FORANEA
                        else if(DAO.refacciones.getCantidad(Session.cnx, codYcant[1], ubicacion2)>0)
                        {
                            opc=msj.cf("En "+ubicacion2, "La refacción se encuentra en "+ubicacion2+",\n¿Desea continuar?");
                                
                                if(opc== JOptionPane.YES_OPTION)
                                    agregarLineaventa(DAO.refacciones.getRefaccionPorID(Session.cnx, codYcant[1], ubicacion2),ubicacion2);
                                else
                                    return Session.mng=new manejador(false, "");
                        }
                        //BUSCAMOS LA REFACCION ENTRE LOS KITS FORANEOS
                        else if(DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], ubicacion2).length>0)
                        {
                            opc=msj.cf("En kit en "+ubicacion2, "La refacción se encuentra en un kit en "+ubicacion2+".\n¿Desea continuar?");
                                
                            if(opc== JOptionPane.YES_OPTION)
                            {
                                Object data[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], ubicacion2);
                                String id_kit="";
                                boolean completo=true;
                                //BUSCAMOS UN KIT INCOMPLETO
                                for(int i=0;i<data.length;i++)
                                {
                                    id_kit=DAO.kitsIncompletos.getId_kit(Session.cnx, ""+data[i][0], ubicacion2);

                                    if(!id_kit.equals(""))
                                    {
                                        completo=false;
                                        break;
                                    }
                                    else
                                    {
                                        completo=true;
                                        id_kit=""+data[0][6];
                                    }
                                }
                                if(completo)
                                    DAO.kitsIncompletos.agregarId_kit(Session.cnx, id_kit, ubicacion2);

                                agregarLineaventa(DAO.kitDetalle.getDetallePorKit(Session.cnx, id_kit, codYcant[1], ubicacion2),ubicacion2);
                            }
                        }
                        else
                            return Session.mng=new manejador(true, "Refacción agotada");
                    }
                    //BUSCAMOS LA REFACCION ENTRE LOS KITS LOCALES
                    else if(DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], Session.ubicacion).length>0)
                    {
                        opc=msj.cf("En kit", "La refacción se encuentra en un kit.\n¿Desea continuar?");

                        if(opc== JOptionPane.YES_OPTION)
                        {
                            Object data[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], Session.ubicacion);
                            String id_kit="";
                            boolean completo=true;
                            //BUSCAMOS UN KIT INCOMPLETO
                            for(int i=0;i<data.length;i++)
                            {
                                id_kit=DAO.kitsIncompletos.getId_kit(Session.cnx, ""+data[i][0], Session.ubicacion);

                                if(!id_kit.equals(""))
                                {
                                    completo=false;
                                    break;
                                }
                                else
                                {
                                    completo=true;
                                    id_kit=""+data[0][6];
                                }
                            }
                            if(completo)
                                DAO.kitsIncompletos.agregarId_kit(Session.cnx, id_kit, Session.ubicacion);

                            agregarLineaventa(DAO.kitDetalle.getDetallePorKit(Session.cnx, id_kit, codYcant[1], Session.ubicacion),Session.ubicacion);
                        }
                    }                                
                    //BUSCAMOS LA REFACCION EN UBICACION FORANEA
                    else if(DAO.refacciones.getCantidad(Session.cnx, codYcant[1], ubicacion2)>0)
                    {
                        opc=msj.cf("En "+ubicacion2, "La refacción se encuentra en "+ubicacion2+",\n¿Desea continuar?");

                            if(opc== JOptionPane.YES_OPTION)
                                agregarLineaventa(DAO.refacciones.getRefaccionPorID(Session.cnx, codYcant[1], ubicacion2),ubicacion2);
                            else
                                return Session.mng=new manejador(false, "");
                    }
                    //BUSCAMOS LA REFACCION ENTRE LOS KITS FORANEOS
                    else if(DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], ubicacion2).length>0)
                    {
                        opc=msj.cf("En kit en "+ubicacion2, "La refacción se encuentra en un kit en "+ubicacion2+".\n¿Desea continuar?");

                        if(opc== JOptionPane.YES_OPTION)
                        {
                            Object data[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, null, codYcant[1], ubicacion2);
                            String id_kit="";
                            boolean completo=true;
                            //BUSCAMOS UN KIT INCOMPLETO
                            for(int i=0;i<data.length;i++)
                            {
                                id_kit=DAO.kitsIncompletos.getId_kit(Session.cnx, ""+data[i][0], ubicacion2);

                                if(!id_kit.equals(""))
                                {
                                    completo=false;
                                    break;
                                }
                                else
                                {
                                    completo=true;
                                    id_kit=""+data[0][6];
                                }
                            }
                            if(completo)
                                DAO.kitsIncompletos.agregarId_kit(Session.cnx, id_kit, ubicacion2);

                            agregarLineaventa(DAO.kitDetalle.getDetallePorKit(Session.cnx, id_kit, codYcant[1], ubicacion2),ubicacion2);
                        }
                    }
                    //VERIFICAMOS SI ES UN KIT
                    else if(DAO.kits.isKit(Session.cnx, codYcant[1]))
                    {
                        //SI LA CANTIDAD EN EXISTENCIA ES MAYOR A CERO, AGREGAMOS LOS ARTICULOS
                        if(DAO.kits.getCantidad(Session.cnx, codYcant[1], Session.ubicacion)>0)
                        {
                            if(DAO.kits.getCantidad(Session.cnx, codYcant[1], Session.ubicacion)>= Integer.parseInt(codYcant[0]))
                                agregarLineaventa(DAO.kits.getKitPorID(Session.cnx, codYcant[1], Session.ubicacion),Session.ubicacion);
                            else
                                return Session.mng=new manejador(true, "No hay suficiente en inventario");
                        }
                        //SI NO, VERIFICAMOS SI HAY EN OTRA UBICACION
                        else if(DAO.kits.getCantidad(Session.cnx, codYcant[1], ubicacion2)>0)
                        {
                            //INFORMAMOS AL USUARIO DE QUE SE ENCUENTRA EN OTRO LADO, Y ESPERAMOS CONFIRMACION
                            opc=msj.cf("En "+ubicacion2, "El kit se encuentra en "+ubicacion2+",\n¿Desea agregarlo?");
                             
                            //SI ES SI, VERIFICAMOS QUE HAYA CANTIDAD SUFICIENTE PARA EL PEDIDO
                            if(opc== JOptionPane.YES_OPTION)
                            {
                                if(DAO.kits.getCantidad(Session.cnx, codYcant[1], ubicacion2)>=Integer.parseInt(codYcant[0]))
                                    agregarLineaventa(DAO.kits.getKitPorID(Session.cnx, codYcant[1], ubicacion2),ubicacion2);
                                //SI NO, NOTIFICAMOS AL USUARIO QUE NO LA HAY
                                else
                                    return Session.mng=new manejador(true, "No hay suficiente en inventario");
                            }
                            else
                                return Session.mng=new manejador(false, "");
                        }
                        else
                            return Session.mng=new manejador(true, "Kit temporalmente agotado");
                    }
                    else
                        return Session.mng=new manejador(true, "Articulo no encontrado.");
                }
                else
                {
                    return Session.mng=new manejador(true, "El codigo debe ser numerico.");
                }
            }
            else
            {
                return Session.mng=new manejador(true, "La cantidad es invalida.");
            }
            
            //TODO SALIO BIEN
            return Session.mng=new manejador(false,"");
        }
        catch(Exception e)
        {
            return Session.mng=new manejador(true, "Ocurrio un error inesperado.\nDetalle: "+e.getMessage()+"\n"+e.getStackTrace());
        }
    }
    
    public static void agregarLineaventa(Object lineaVenta[][],String ubicacion)
    {
        int row=Session.lineaVent.getRowCount();
              
        
            Session.lineaVent.addRow(lineaVenta);
            Session.lineaVent.setValueAt(lineaVenta[0][0], row, 0);//id
            Session.lineaVent.setValueAt(lineaVenta[0][1], row, 1);//descripcion
            Session.lineaVent.setValueAt(""+servitrans.Formatea.alinder("$ ###,###.00", Double.parseDouble(""+lineaVenta[0][2])),row, 2);//importe
            Session.lineaVent.setValueAt(cantidadArt, row, 3);//cantidad
            float monto=Float.parseFloat(""+lineaVenta[0][2])*Float.parseFloat(""+cantidadArt);
            Session.lineaVent.setValueAt(""+servitrans.Formatea.alinder("$ ###,###.00", monto), row, 4);
            
            if(DAO.kits.isKit(Session.cnx, ""+lineaVenta[0][6]))
                DAO.ventaActual.agregarLinea(Session.cnx,row, lineaVenta[0][0], lineaVenta[0][1], lineaVenta[0][3], cantidadArt, servitrans.Formatea.alinder("$ ###,###.00", monto),ubicacion,lineaVenta[0][6]);
            else
                DAO.ventaActual.agregarLinea(Session.cnx,row, lineaVenta[0][0], lineaVenta[0][1], lineaVenta[0][3], cantidadArt, servitrans.Formatea.alinder("$ ###,###.00", monto),ubicacion,"");
            
            
            tablaVenta.getColumnModel().getColumn(0).setCellRenderer(Session.getCenterRenderer());
            tablaVenta.getColumnModel().getColumn(2).setCellRenderer(Session.getRightRenderer());
            tablaVenta.getColumnModel().getColumn(3).setCellRenderer(Session.getRightRenderer());
            tablaVenta.getColumnModel().getColumn(4).setCellRenderer(Session.getRightRenderer());
            
            tablaVenta.getColumnModel().getColumn(0).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(0).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(1).setMinWidth(300);
            tablaVenta.getColumnModel().getColumn(2).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(2).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(3).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(3).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(4).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(4).setMaxWidth(100);
            
            
            float montoTotal=Float.parseFloat(Util.quitarFormatoMoneda(servitrans.venta.montoVenta.getText()))+monto;
            servitrans.venta.montoVenta.setText(""+servitrans.Formatea.alinder("$ ###,###.00", montoTotal));
            servitrans.venta.cantArticulos.setText(""+(row+1));
            servitrans.venta.txt_codigo.setText("");
            servitrans.venta.txt_codigo.requestFocus();
            servitrans.venta.isTotalizada=false;
    }
    
    public static void restaurarVenta(Object lineaVenta[][])
    {
        int row=Session.lineaVent.getRowCount();
        for(int i=0;i<lineaVenta.length;i++)
        {
            row=i;
            Session.lineaVent.addRow(lineaVenta);
            Session.lineaVent.setValueAt(lineaVenta[i][0], row, 0);//id
            Session.lineaVent.setValueAt(lineaVenta[i][1], row, 1);//descripcion
            Session.lineaVent.setValueAt(""+servitrans.Formatea.alinder("$ ###,###.00", Double.parseDouble(""+lineaVenta[i][2])),row, 2);//importe
            Session.lineaVent.setValueAt(cantidadArt, row, 3);//cantidad
            Session.lineaVent.setValueAt(lineaVenta[i][4], row, 4);//monto
            
            float montoTotal=Float.parseFloat(Util.quitarFormatoMoneda(servitrans.venta.montoVenta.getText()))+Float.parseFloat(Util.quitarFormatoMoneda(""+lineaVenta[i][4]));
            servitrans.venta.montoVenta.setText(""+servitrans.Formatea.alinder("$ ###,###.00", montoTotal));
            
            
            servitrans.venta.cantArticulos.setText(""+(row+1));
            servitrans.venta.txt_codigo.setText("");
            servitrans.venta.txt_codigo.requestFocus();
        }   
            
            tablaVenta.getColumnModel().getColumn(0).setCellRenderer(Session.getCenterRenderer());
            tablaVenta.getColumnModel().getColumn(2).setCellRenderer(Session.getRightRenderer());
            tablaVenta.getColumnModel().getColumn(3).setCellRenderer(Session.getRightRenderer());
            tablaVenta.getColumnModel().getColumn(4).setCellRenderer(Session.getRightRenderer());
            
            tablaVenta.getColumnModel().getColumn(0).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(0).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(1).setMinWidth(300);
            tablaVenta.getColumnModel().getColumn(2).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(2).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(3).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(3).setMaxWidth(100);
            tablaVenta.getColumnModel().getColumn(4).setMinWidth(100);
            tablaVenta.getColumnModel().getColumn(4).setMaxWidth(100);
            
    }
    
}
