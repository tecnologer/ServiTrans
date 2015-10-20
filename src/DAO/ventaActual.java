package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import java.util.List;

public class ventaActual 
{
    
    public static void agregarLinea(ObjectContainer db,Object row, Object id,Object descripcion,Object importe, Object cantidad,Object total,Object ubicacion,Object id_kit)
    {
        DB.ventaActual DBD = new DB.ventaActual(row,id, descripcion, importe, cantidad, total,ubicacion,id_kit);
        db.store(DBD);
    }
    
    public static Object[][] getCurrentSale(ObjectContainer db)
    {
            DB.ventaActual tabla = new DB.ventaActual(null,null,null, null, null, null, null,null);
            ObjectSet result = db.queryByExample(tabla);
            List <DB.ventaActual> lista=result;
            Object datos[][]=new Object [lista.size()][8];
            int i=0;
            while(i<lista.size())
            {
                datos[i][0]=lista.get(i).getId_articulo();
                datos[i][1]=lista.get(i).getDe_articulo();
                datos[i][2]=lista.get(i).getIm_articulo();
                datos[i][3]=lista.get(i).getNu_cantidad();
                datos[i][4]=lista.get(i).getIm_total();
                datos[i][5]=lista.get(i).getId_kit();
                datos[i][6]=lista.get(i).getRow();
                datos[i][7]=lista.get(i).getUbicacion();
                i++;
            }
            return datos;
    }
    
    public static void clearDatabase(ObjectContainer db) {
            ObjectSet result=db.queryByExample(DB.ventaActual.class);
            while(result.hasNext()) {
                db.delete(result.next());
            }
        }
}
