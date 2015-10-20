package BRO;

import servitrans.Session;

public class venta 
{
    public static void totalizarVenta(String monto)
    {
        int row=Session.lineaVent.getRowCount();
        Object n[][]=new Object [1][5];
        Session.lineaVent.addRow(n);
        Session.lineaVent.setValueAt("", row, 0);//id
        Session.lineaVent.setValueAt("", row, 1);//descripcion
        Session.lineaVent.setValueAt("",row, 2);//importe
        Session.lineaVent.setValueAt("Total: ", row, 3);//cantidad
        Session.lineaVent.setValueAt(monto, row, 4);
        servitrans.venta.isTotalizada=true;
    }
    
}
