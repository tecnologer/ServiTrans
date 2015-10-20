package servitrans;

import DB.cnxServiTrans;
import com.db4o.ObjectContainer;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import tools.manejador;

public class Session 
{
 
    public static  String usuario;
    public static  String fecha;
    public static  String ubicacion;
    public static  boolean admin;
    public static  boolean almacen=false;
    public static  ObjectContainer cnx=cnxServiTrans.obtener_conexion();
    public static  manejador mng=new manejador(false,"");
    private static DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private static DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
    
    public static DefaultTableModel lineaVent;
    
    public static Image getIcon()
    {
        Image icono=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/app_icono.png"));
        return icono;
    }

    public static DefaultTableCellRenderer getCenterRenderer() {
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        return centerRenderer;
    }

    public static DefaultTableCellRenderer getRightRenderer() {
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        return rightRenderer;
    }

    public static DefaultTableCellRenderer getLeftRenderer() {
        leftRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        return leftRenderer;
    }    
    
}
