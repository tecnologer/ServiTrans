package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import servitrans.Session;
import tools.manejador;
import tools.msj;

public class kitDetalle 
{
     //AGREGA UNA NUEVO KIT
	public static void addDetailKit(ObjectContainer db, String id_kit, String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad) {
		DB.kitDetalle  DBD = new DB.kitDetalle(id_kit,id_refaccion,de_refaccion,im_refaccion,nu_cantidad,nu_cantidad,"0",Session.ubicacion);
		db.store(DBD);
	}
        
        //OBTIENE EL DETALLE DE UN KIT BUSCANDO POR SU ID O UNA REFACCION DEL KIT
        public static Object[][] getDetallePorKit(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;
                Object datos[][]=new Object [lista.size()][7];
                int i=0;
                while(i<lista.size())
                {
                    datos[i][0]=lista.get(i).getId_refaccion();
                    datos[i][1]=lista.get(i).getDe_refaccion();
                    datos[i][2]=lista.get(i).getIm_refaccion();
                    datos[i][3]=lista.get(i).getNu_cantidad();
                    datos[i][4]=lista.get(i).getMin_cantidad();
                    datos[i][5]=lista.get(i).getUbicacion();
                    datos[i][6]=lista.get(i).getId_kit();
                    i++;
                }
                return datos;
	}
        
        //OBTENER LA DESCRIPCION DE LA REFACCION
        public static String getDescripcion(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion)
        {
            if(getDetallePorKit(db, id_kit, id_refaccion,ubicacion).length>0)
               return ""+getDetallePorKit(db, id_kit, id_refaccion,ubicacion)[0][1];
            else
                return "";
        }
        
        //OBTIENE EL LA CANTIDAD ACTUAL DE CIERTA REFACCION EN EL KIT
        public static int getCantidad(ObjectContainer db,String id_kit,String id_refaccion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",Session.ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;
                if(lista.size()>0)
                    return lista.get(0).getNu_cantidad();                
                else
                    return 0;
	}
        
        //ACTUALIZA LA CANTIDAD DE EXISTENCIA EN EL INVENTARIO
        public static void updateCantidad(ObjectContainer db, String id_kit,String id_refaccion,int nu_cantidad,String ubicacion) 
        {
                nu_cantidad+=getCantidad(db,id_kit,id_refaccion);
		ObjectSet result = db
				.queryByExample(new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",ubicacion));
		DB.kitDetalle found = (DB.kitDetalle) result.next();
                found.setNu_cantidad(nu_cantidad);
		db.store(found);
	}
        
        //ACTUALIZA LA INFORMACION DE LA REFACCION
        public static void editarDetalle(ObjectContainer db,String id_kit, String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad,String ubicacion) 
        {
		ObjectSet result = db
				.queryByExample(new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",ubicacion));
		DB.kitDetalle found = (DB.kitDetalle) result.next();
                found.setDe_refaccion(de_refaccion);
                found.setIm_refaccion(im_refaccion);
                found.setNu_cantidad(nu_cantidad);
		db.store(found);
                
	}
        
        //OBTIENE LA CANTIDAD MAXIMA DE UNA REFACCION DENTRO DEL KIT
        public static int getMaxCantidadKit(ObjectContainer db,String id_kit,String id_refaccion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",Session.ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;                
                if(lista.size()>0)
                    return lista.get(0).getMin_cantidad();
                else
                    return 0;
	}
        
        //VERIFICA SI LA REFACCION NO EXISTE EN EL KIT
        public static boolean existRefaccion(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,null,ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;                
                if(lista.size()>0)
                    return true;
                else
                    return false;
	}
        
        //VERIFICA SI LA REFACCION ESTA BORRADA
        public static boolean isDeleteRefaccion(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"1",ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;                
                if(lista.size()>0)
                    return true;
                else
                    return false;
	}
        
        //VERIFICA SI LA REFACCION ESTA BORRADA
        public static void deleteRefaccion(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion) {
		ObjectSet result = db
				.queryByExample(new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",ubicacion));
		DB.refacciones found = (DB.refacciones) result.next();
		found.setSn_borrado("1");
		db.store(found);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        //VERIFICA SI LA REFACCION NECESITA RESURTIRSE
        public static boolean needResurtir(ObjectContainer db,String id_kit,String id_refaccion,String ubicacion) {
		DB.kitDetalle tabla = new DB.kitDetalle(id_kit,id_refaccion,null,0,0,0,"0",ubicacion);
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kitDetalle> lista=result;                
                if(lista.size()>0)
                    if(DAO.kits.isIncomplete(db, id_kit,ubicacion))
                        if(lista.get(0).getNu_cantidad()<lista.get(0).getMin_cantidad())
                            return true;
                        else
                            return false;
                    else
                        return false;
                else
                    return false;
	}
}
