package tools;

import servitrans.Session;
import servitrans.interfaz;

public class ifWindowsOpen 
{
    public static boolean ventaOpen=false;
    public static boolean articuloOpen=false;
    
    public static void activaBotones()
    {
        if(existWindowsOpen())
            disabledButtons();
        else
            enabledButtons();
    }
    
    public static boolean existWindowsOpen()
    {
       if(ventaOpen)
           return true;
       else if(articuloOpen)
           return true;
       else
           return false;
    }
    
    public static void enabledButtons()
    {
        if(Session.almacen)
            interfaz.btnVenta.setEnabled(false);
        else
            interfaz.btnVenta.setEnabled(true);
        
        interfaz.btnProductos.setEnabled(true);        
        interfaz.btnSalir.setEnabled(true);
    }
    
    public static void disabledButtons()
    {
        interfaz.btnVenta.setEnabled(false);
        interfaz.btnProductos.setEnabled(false);
        interfaz.btnSalir.setEnabled(false);
    }
}
