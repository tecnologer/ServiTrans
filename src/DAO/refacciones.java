package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.List;
import servitrans.Session;
import tools.manejador;
import tools.msj;

public class refacciones 
{
        
        static Query query=null; 
        
        //BUSCA LA CLASE DE REFACCIONES, SI EXISTE REGRESA TRUE, SI NO, REGRESA FALSE
	public static boolean existeRefaccion(ObjectContainer db, String id_articulo,String ubicacion) {
		DB.refacciones tabla = new DB.refacciones(id_articulo,null,0,0,ubicacion,"0");
		ObjectSet result = db.queryByExample(tabla);
		if(result.size()>0) 
                {
                    return true;
                }
                else 
                {
                    return false;
                }
	}
        
        //INDICA SI EL CODIGO BUSCADO ES REFACCION
        public static boolean isRefaccion(ObjectContainer db, String id_articulo,String ubicacion)
        {
            return existeRefaccion(db, id_articulo,ubicacion);
        }
        
        //ACTUALIZA LA CANTIDAD DE EXISTENCIA EN EL INVENTARIO
        public static void updateCantidad(ObjectContainer db, String id_articulo,int nu_cantidad,String ubicacion) 
        {
                Object articulo[][]=getRefaccionPorID(db,id_articulo,ubicacion);
                if(nu_cantidad>0)
                    nu_cantidad+=Integer.parseInt(""+articulo[0][3]);
                else
                    nu_cantidad=Integer.parseInt(""+articulo[0][3])+nu_cantidad;
		ObjectSet result = db
				.queryByExample(new DB.refacciones(id_articulo,null,0,0,ubicacion,"0"));
		DB.refacciones found = (DB.refacciones) result.next();
                found.setNu_cantidad(nu_cantidad);
		db.store(found);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        //OBTIENE LOS DATOS DE UNA REFACCION BUSCANDO POR SU ID
        public static Object[][] getRefaccionPorID(ObjectContainer db,String id_articulo,String ubicacion) {
		DB.refacciones tabla = new DB.refacciones(id_articulo,null,0,0,ubicacion,"0");
		ObjectSet result = db.queryByExample(tabla);
		List <DB.refacciones> lista=result;
                Object datos[][]=new Object [lista.size()][7];
                int i=0;
                while(i<lista.size())
                {
                    datos[i][0]=lista.get(i).getId_refaccion();
                    datos[i][1]=lista.get(i).getDe_refaccion();
                    datos[i][2]=lista.get(i).getIm_refaccion();
                    datos[i][3]=lista.get(i).getNu_cantidad();
                    datos[i][4]="1";
                    datos[i][5]=lista.get(i).getUbicacion();
                    datos[i][6]="Si";
                    i++;
                }
                return datos;
	}
        
        //OBTINEE LA DESCRICION DE LA REFACCION
        public static String getDescripcion(ObjectContainer db,String id_articulo,String ubicacion)
        {
            Object data[][]=getRefaccionPorID(db, id_articulo, ubicacion);
            
            return ""+data[0][1];
        }
        //AGREGA UNA NUEVA REFACCION
	public static void newRefaccion(ObjectContainer db, String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad,String ubicacion) {
		DB.refacciones DBD = new DB.refacciones(id_refaccion,de_refaccion,im_refaccion,nu_cantidad,ubicacion,"0");
		db.store(DBD);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        public static void editarArticulo(ObjectContainer db, String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad,String ubicacion) {
		ObjectSet result = db
				.queryByExample(new DB.refacciones(id_refaccion,null,0,0,ubicacion,"0"));
		DB.refacciones found = (DB.refacciones) result.next();
		found.setDe_refaccion(de_refaccion);
                found.setIm_refaccion(im_refaccion);
                found.setNu_cantidad(getCantidad(db,id_refaccion,ubicacion)+nu_cantidad);
		db.store(found);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        public static int getCantidad(ObjectContainer db, String id_articulo,String ubicacion) 
        {
            if(existeRefaccion(db, id_articulo,ubicacion))
            {
                Object articulo[][]=getRefaccionPorID(db,id_articulo,ubicacion);
                return Integer.parseInt(""+articulo[0][3]);
            }
            else
                return 0;
	}
        
        public static Object[][] verRefaccionesPorIDyDes(ObjectContainer db,String id_refaccion,String de_refaccion) {   
            Query query=db.query();
            query.constrain(DB.refacciones.class);
            Constraint constr=query.descend("id_refaccion").constrain(id_refaccion).like().
                                    and(query.descend("de_refaccion").constrain(de_refaccion).like()).
                                    and(query.descend("sn_borrado").constrain("0").not());
            ObjectSet result = query.execute();
            List <DB.refacciones> lista=result;
            Object datos[][]=new Object [lista.size()][6];
            int i=0;
            while(i<lista.size())
            {
                datos[i][0]=lista.get(i).getId_refaccion();
                datos[i][1]=lista.get(i).getDe_refaccion();
                datos[i][2]=lista.get(i).getIm_refaccion();
                datos[i][3]="1";
                datos[i][4]=lista.get(i).getNu_cantidad();
                datos[i][5]=lista.get(i).getUbicacion();
                i++;
            }
            return datos;
	}
        
        public static void clearDatabase(ObjectContainer db) {
            ObjectSet result=db.queryByExample(DB.refacciones.class);
            while(result.hasNext()) {
                db.delete(result.next());
            }
        }
}
