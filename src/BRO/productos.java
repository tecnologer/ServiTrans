package BRO;

import Productos.eliminado;
import Productos.nuevoProducto;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import servitrans.Formatea;
import servitrans.Session;
import tools.Util;
import tools.manejador;
import tools.msj;
import static ventas.buscarArticulo.vtnTabla;

public class productos
{
    static DefaultTableModel inventario;
    static DefaultTableModel busqueda;
    public static int row;    
        
    public static manejador actualizarInventario(String id_articulo,String nu_cantidad)
    {
        try
        {
            if(Util.isLong(id_articulo))
            {
                String id_art=id_articulo;
                if(Util.isNumeric(nu_cantidad))
                {
                    int nu_cant=Integer.parseInt(nu_cantidad);
                    if(DAO.refacciones.isRefaccion(Session.cnx, id_art,Session.ubicacion))
                    {
                        //obtenemos los kits donde se encuentre la refaccion
                        Object datos[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, null, id_articulo,Session.ubicacion);
                        for(int i=0;i<datos.length;i++)
                        {
                            if(DAO.kitDetalle.needResurtir(Session.cnx, ""+datos[i][6], ""+datos[i][0], Session.ubicacion))
                            {
                                int cantResurtir=Integer.parseInt(""+datos[i][4])-Integer.parseInt(""+datos[i][3]);
                                if(cantResurtir>0)
                                {
                                    int cantRestante=Integer.parseInt(nu_cantidad)-cantResurtir;
                                    if(cantRestante>=0)
                                        DAO.kitDetalle.updateCantidad(Session.cnx,  ""+datos[i][6], ""+datos[i][0], cantResurtir,Session.ubicacion);
                                    nu_cantidad=""+cantRestante;
                                    if(DAO.kits.isComplete(Session.cnx, ""+datos[i][6], Session.ubicacion))
                                        DAO.kits.setComplete(Session.cnx, ""+datos[i][6], Session.ubicacion, "1");
                                }
                            }
                        }
                        nu_cant=Integer.parseInt(nu_cantidad);
                        if(Integer.parseInt(nu_cantidad)>0)
                            DAO.refacciones.updateCantidad(Session.cnx, id_art, nu_cant,Session.ubicacion);
                    }
                    else if(DAO.kits.isKit(Session.cnx, id_art))
                    {
                        DAO.kits.updateCantidad(Session.cnx, id_art, nu_cant,Session.ubicacion);
                    }
                    else
                    {
                        int val=msj.cf("Producto no encontrado", "El producto no existe.\n¿Desea darlo de alta?");
                        if(JOptionPane.YES_OPTION==val)
                        {
                            nuevoProducto nProducto=new nuevoProducto(null, true);                            
                            nProducto.id_producto.setText(id_art);
                            nProducto.nu_cantidad.setText(""+nu_cant);
                            nProducto.setVisible(true);
                            nProducto.setLocationRelativeTo(null);
                        }
                        else
                            return Session.mng=new manejador(true,"");
                    }
                }
                else
                {
                    Session.mng=new manejador(true,"La cantidad debe de ser un numero entero.");
                }
            }
            else
            {
                Session.mng=new manejador(true,"El id del producto debe ser numerico.");
            }
            return Session.mng;
        }
        catch(Exception e){
            Session.mng=new manejador(true,"Ocurrio un error durante el proceso.\nDetalle:\n"+e.getMessage());
            return Session.mng;
        }
    }
    
    public static void mostrarInventario(String id,String de, byte flag,byte local)
    {
        String id_producto=id;
        if(id_producto.equals(""))
            id_producto=null;
        String de_descripcion=de.toUpperCase();
        
        Object datos1[][]=new Object[0][0];
        Object datos2[][]=new Object[0][0];
        String ubicacion=Session.ubicacion;
        
        if(local==0)
            ubicacion=null;
        //KITS Y REFACCIONES
        if(flag==1)
        {
            datos1=DAO.refacciones.getRefaccionPorID(Session.cnx, id_producto,ubicacion);
            datos2=DAO.kits.getKitPorID(Session.cnx, id_producto,ubicacion);
        }//SOLO REFACCIONES
        else if(flag==2)
        {
            datos1=DAO.refacciones.getRefaccionPorID(Session.cnx, id_producto,ubicacion);
        }//SOLO KITS
        else 
        {
            datos2=DAO.kits.getKitPorID(Session.cnx, id_producto,ubicacion);
        }
        
        llenarTabla(datos1,datos2);
    }
    
    private static void llenarTabla(Object datos1[][],Object datos2[][])
    {
       servitrans.articulo.jTable1.setModel(inventario=new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "DESCRIPCIÓN", "PRECIO", "CANTIDAD DE PIEZAS", "CANTIDAD EN INVENTARIO","UBICACIÓN","COMPLETO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       servitrans.articulo.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                row=servitrans.articulo.jTable1.getSelectedRow();
                if(evt.getClickCount()==2)
                {
                    editarArticulo();
                }
            }
        });
       int renglon=0;
       for(int i=0;i<datos1.length;i++)
       {
           inventario.addRow(datos1);
           for(int j=0;j<datos1[0].length;j++)
           {
               if(j==2)
                   datos1[i][j]=Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos1[i][j]));
               if(j==3)
                   inventario.setValueAt(datos1[i][4], renglon, j);
               else if(j==4)
                   inventario.setValueAt(datos1[i][3], renglon, j);
               else
                inventario.setValueAt(datos1[i][j], renglon, j);
           }
           renglon++;
       }
       
       for(int i=0;i<datos2.length;i++)
       {
           inventario.addRow(datos2);
           for(int j=0;j<datos2[0].length;j++)
           {
               if(j==2)
                   datos2[i][j]=Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos2[i][j]));
               else if(j==6)
               {
                   if(datos2[i][j].equals("0"))
                       datos2[i][j]="No";
                   else
                       datos2[i][j]="Si"; 
               }
               
                 inventario.setValueAt(datos2[i][j], renglon, j);
           }
           renglon++;
       }
       
       servitrans.articulo.jTable1.getColumnModel().getColumn(2).setCellRenderer(Session.getRightRenderer());
       servitrans.articulo.jTable1.getColumnModel().getColumn(3).setCellRenderer(Session.getCenterRenderer());
       servitrans.articulo.jTable1.getColumnModel().getColumn(4).setCellRenderer(Session.getCenterRenderer());
       servitrans.articulo.jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        javax.swing.table.TableColumn col = servitrans.articulo.jTable1.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor() {

            @Override
            public Object getCellEditorValue() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });         
    }    
    
    public abstract static class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        JComponent component = new JTextField();
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
            ((JTextField)component).setText((String)value);
            ((JTextField)component).setFont(new java.awt.Font("Arial Unicode MS", 0, 20));
            return component;
        }
    }
    
    public static void editarArticulo()
    {
         row=servitrans.articulo.jTable1.getSelectedRow();
         String id=""+inventario.getValueAt(row, 0);
         String ubicacion=""+inventario.getValueAt(row, 5);
         Object datos[][];
         //si es kit obtenemos los datos del kit y su detalle
         if(DAO.kits.isKit(Session.cnx, id))
         {
             datos=DAO.kits.getKitPorID(Session.cnx, id, ubicacion);
             Object detalle[][]=DAO.kitDetalle.getDetallePorKit(Session.cnx, id, null,ubicacion);
             editarKit(datos,detalle);
         }
         //si es no es kit, obtenemos los datos de la refaccion
         else 
         {
             datos=DAO.refacciones.getRefaccionPorID(Session.cnx, id, ubicacion);
             editarRefaccion(datos);
         }
    }
    
    public static void editarKit(Object datos[][],Object detalle[][])
    {
        Productos.nuevoProducto newP=new Productos.nuevoProducto(null, true);
        newP.edicion=true;
        newP.isNew=false;
        newP.id_producto.setText(""+datos[0][0]);
        newP.id_producto.setEditable(false);
        newP.de_producto.setText(""+datos[0][1]);
        newP.im_producto.setText(""+Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos[0][2])));
        newP.nu_cantidad.setText(""+datos[0][4]);
        newP.isComplete=""+datos[0][6];
        newP.nu_cantidad.setEditable(false);
        newP.ubicacion=""+datos[0][5];
        newP.isKit.setSelected(true);
        newP.isKit.setVisible(true);
        newP.isKit.setEnabled(false);
        newP.lbl_complete.setVisible(true);
        newP.detalleKit=new JTextField[0][4];
        newP.deleteDetalle=new JButton[0];
        newP.flag=0;
        newP.eli=new eliminado[0];
        newP.llenarDetalle(detalle);
        newP.setTitle("Editar kit");
        for(int i=0;i<detalle.length;i++)
        {
            newP.setEliminados(""+detalle[i][0], false, true,i);
            newP.agregarUnElemento();
        }
        
        newP.mostrarDetalle();
        newP.llenadoDetalle=false;
        newP.setVisible(true);
    }
    
    public static void editarRefaccion(Object datos[][])
    {   
        Productos.nuevoProducto newP=new Productos.nuevoProducto(null, true);
        newP.isNew=false;
        newP.edicion=true;
        newP.id_producto.setText(""+datos[0][0]);
        newP.id_producto.setEditable(false);
        newP.de_producto.setText(""+datos[0][1]);
        newP.im_producto.setText(""+Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos[0][2])));
        newP.nu_cantidad.setText(""+datos[0][4]);
        newP.ubicacion=""+datos[0][5];
        newP.nu_cantidad.setEditable(false);
        newP.detalleKit=new JTextField[0][4];
        newP.deleteDetalle=new JButton[0];
        newP.lbl_complete.setVisible(false);
        newP.isKit.setSelected(false); 
        newP.isKit.setVisible(false);
        newP.mostrarDetalle();
        newP.setTitle("Editar refacción");
        newP.setVisible(true);
    }
    
    public static manejador buscarArticulo(String id,String descrip,byte tpo,byte local)
    {
        try
        {
             String id_producto=id;
            if(id_producto.equals(""))
                id_producto=null;
            String de_descripcion=descrip.toUpperCase();

            Object datos1[][]=new Object[0][0];
            Object datos2[][]=new Object[0][0];
            String ubicacion=Session.ubicacion;

            if(local==0)
                ubicacion=null;
            //KITS Y REFACCIONES
            if(tpo==1)
            {
                datos1=DAO.refacciones.getRefaccionPorID(Session.cnx, id_producto,ubicacion);
                datos2=DAO.kits.getKitPorID(Session.cnx, id_producto,ubicacion);
            }//SOLO REFACCIONES
            else if(tpo==2)
            {
                datos1=DAO.refacciones.getRefaccionPorID(Session.cnx, id_producto,ubicacion);
            }//SOLO KITS
            else 
            {
                datos2=DAO.kits.getKitPorID(Session.cnx, id_producto,ubicacion);
            }

            llenarTablaBusqueda(datos1,datos2);
            return Session.mng=new manejador(false, "");
        }
        catch(Exception e)
        {
            return Session.mng=new manejador(true, "Error inesperado.\nDetalle: "+e.getMessage());
        }
    }
    
    private static void llenarTablaBusqueda(Object datos1[][],Object datos2[][])
    {
       vtnTabla.setModel(busqueda=new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "DESCRIPCIÓN", "PRECIO", "CANTIDAD DE PIEZAS", "UBICACIÓN", "COMPLETO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       vtnTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                row=vtnTabla.getSelectedRow();
                if(evt.getClickCount()==2)
                {
                    servitrans.venta.txt_codigo.setText(""+busqueda.getValueAt(row, 0));
                    servitrans.venta.bArt.dispose();
                }
            }
        });
       int renglon=0;
       for(int i=0;i<datos1.length;i++)
       {
           busqueda.addRow(datos1);
           int col=0;
           for(int j=0;j<datos1[0].length;j++)
           {
               if(j!=3)
               {
                    if(j==2)
                        datos1[i][j]=Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos1[i][j]));

                    busqueda.setValueAt(datos1[i][j], renglon, col);
                    col++;
               }
           }
           renglon++;
       }
       
       for(int i=0;i<datos2.length;i++)
       {
           busqueda.addRow(datos2);
           int col=0;
           for(int j=0;j<datos2[0].length;j++)
           {
               if(j!=3)
               {
                    if(j==2)
                        datos2[i][j]=Formatea.alinder("$ ###,###.00", Double.parseDouble(""+datos2[i][j]));
                    else if(j==6)
                    {
                        if(datos2[i][j].equals("0"))
                            datos2[i][j]="No";
                        else
                            datos2[i][j]="Si"; 
                    }

                      busqueda.setValueAt(datos2[i][j], renglon, col);
                      col++;
               }
           }
           renglon++;
       }
       
      vtnTabla.getColumnModel().getColumn(2).setCellRenderer(Session.getRightRenderer());
       vtnTabla.getColumnModel().getColumn(3).setCellRenderer(Session.getCenterRenderer());
       vtnTabla.getColumnModel().getColumn(4).setCellRenderer(Session.getCenterRenderer());
       vtnTabla.getColumnModel().getColumn(4).setMinWidth(100);
        javax.swing.table.TableColumn col = vtnTabla.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor() {

            @Override
            public Object getCellEditorValue() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });         
    }
}
