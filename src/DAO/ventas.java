/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.List;

/**
 *
 * @author Tecnologer
 */
public class ventas 
{
    
   static Query query=null; 
	
    public static void agregarVenta(ObjectContainer db, String id_venta, String fh_venta, String nu_articulos, float im_venta, String id_usuario) 
    {
        DB.ventas DBD = new DB.ventas(id_venta,fh_venta,nu_articulos,im_venta,id_usuario);
        db.store(DBD);
    }
    
    public static String getNextId(ObjectContainer db)
    {
        DB.ventas DBD = new DB.ventas(null,null,null,0,null);
        ObjectSet result = db.queryByExample(DBD);
        List <DB.ventas> lista=result;
        int idInt=0;
        String id="";
        if(lista.size()>0)
            idInt=Integer.parseInt(""+lista.get(lista.size()-1).getId_venta());
        
            id=""+(idInt+1);
            
            return id;
    }
    
//    public static Object[][] verVentas(ObjectContainer db, String fc_venta,String nb_usuario) {
//		DB.ventas DBD = new DB.ventas(null,fc_venta,0,0,nb_usuario);
//		ObjectSet result = db.queryByExample(DBD);
//                List <DB.ventas> lista=result;
//                Object datos[][]=new String [lista.size()][5];
//                int i=0;
//                while(i<lista.size())
//                {
//                    datos[i][0]=lista.get(i).getId_venta();
//                    datos[i][1]=lista.get(i).getFc_venta();
//                    datos[i][2]=""+lista.get(i).getIm_venta();
//                    datos[i][3]=""+lista.get(i).getNu_articulos();
//                    datos[i][4]=lista.get(i).getNb_usuario();
//                    i++;
//                }
//                return datos;
//	}
    
//     public static Object[][] verVenta(ObjectContainer db, String fc_venta,String nb_usuario) {
//            query=db.query();
//            query.constrain(DB.ventas.class);
//            Constraint constr=query.descend("fc_venta").constrain(fc_venta).like().
//                                    and(query.descend("nb_usuario").constrain(nb_usuario).like());
//            ObjectSet result = query.execute();
//                List <DB.ventas> lista=result;
//                Object datos[][]=new String [lista.size()][5];
//                int i=0;
//                while(i<lista.size())
//                {
//                    datos[i][0]=lista.get(i).getId_venta();
//                    datos[i][1]=lista.get(i).getFc_venta();
//                    datos[i][2]=""+lista.get(i).getIm_venta();
//                    datos[i][3]=""+lista.get(i).getNu_articulos();
//                    datos[i][4]=lista.get(i).getNb_usuario();
//                    i++;
//                }
//                return datos;
//	}
     
//     public static String getMontoByFecha(ObjectContainer db, String fc_venta,String nb_usuario) {
//		DB.ventas DBD = new DB.ventas(null,fc_venta,0,0,nb_usuario);
//		ObjectSet result = db.queryByExample(DBD);
//                List <DB.ventas> lista=result;
//                int i=0;
//                float importe=0;
//                while(i<lista.size())
//                {
//                    importe+=Float.parseFloat(""+lista.get(i).getIm_venta());
//                    i++;
//                }
//                return ""+importe;
//	}
     
      public static void clearDatabase(ObjectContainer db) {
            ObjectSet result=db.queryByExample(DB.ventas.class);
            while(result.hasNext()) {
                db.delete(result.next());
            }
        }
}
