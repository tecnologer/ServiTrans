package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.List;
import servitrans.Session;
import tools.manejador;
import tools.msj;

public class kits 
{
    static Query query=null; 
    
    //BUSCA LA CLASE DE KITS, SI EXISTE REGRESA TRUE, SI NO, REGRESA FALSE
	public static boolean existeKit(ObjectContainer db, String id_kit) {
		DB.kits tabla = new DB.kits(id_kit,null,0,0,0,null,null,"0");
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
        
        //BUSCA SI EL KIT ESTA INCOMPLETO
	public static boolean isIncomplete(ObjectContainer db, String id_kit,String ubicacion) {
		DB.kits tabla = new DB.kits(id_kit,null,0,0,0,"0",ubicacion,"0");
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
        
        //INDICA SI EL CODIGO BUSCADO ES KITS
        public static boolean isKit(ObjectContainer db, String id_kit)
        {
            return existeKit(db, id_kit);
        }
        
        public static boolean isComplete(ObjectContainer db,String id_kit,String ubicacion)
        {
            if(isIncomplete(db, id_kit,ubicacion))
            {
                Object data[][]=DAO.kitDetalle.getDetallePorKit(db, id_kit, null, ubicacion);
                
                for(int i=0;i<data.length;i++)
                {
                    if(Integer.parseInt(""+data[i][3])<Integer.parseInt(""+data[i][4]))
                        return false;
                }
                return true;
            }
            else
                return true;
        }
        
        //ACTUALIZA LA CANTIDAD DE EXISTENCIA EN EL INVENTARIO
        public static void updateCantidad(ObjectContainer db, String id_kit,int nu_cantidad,String ubicacion) 
        {
                Object articulo[][]=getKitPorID(db,id_kit,ubicacion);
                nu_cantidad+=Integer.parseInt(""+articulo[0][4]);
		ObjectSet result = db
				.queryByExample(new DB.kits(id_kit,null,0,0,0,null,ubicacion,"0"));
		DB.kits found = (DB.kits) result.next();
                found.setNu_cantidad(nu_cantidad);
		db.store(found);
	}
        
        //OBTIENE LOS DATOS DE UNA REFACCION BUSCANDO POR SU ID
        public static Object[][] getKitPorID(ObjectContainer db,String id_kit,String ubicacion) {
            //id_kit, de_kit, im_kit,  nu_cantidad,  nu_piezas,  sn_incompleto, ubicacion, sn_borrado
		DB.kits tabla = new DB.kits(id_kit,null,0,0,0,null,ubicacion,"0");
		ObjectSet result = db.queryByExample(tabla);
		List <DB.kits> lista=result;
                Object datos[][]=new Object [lista.size()][7];
                int i=0;
                while(i<lista.size())
                {
                    datos[i][0]=lista.get(i).getId_kit();
                    datos[i][1]=lista.get(i).getDe_kit();
                    datos[i][2]=lista.get(i).getIm_kit();
                    datos[i][3]=lista.get(i).getNu_piezas();
                    datos[i][4]=lista.get(i).getNu_cantidad();
                    datos[i][5]=lista.get(i).getUbicacion();
                    datos[i][6]=lista.get(i).getSn_incompleto();
                    i++;
                }
                return datos;
	}
        
         public static Object[][] verKitsPorIDyDes(ObjectContainer db,String id_kit,String de_kit) {   
            query=db.query();
            query.constrain(DB.kits.class);
            Constraint constr=query.descend("id_kit").constrain(id_kit).like().
                                    and(query.descend("de_kit").constrain(de_kit).like()).
                                    and(query.descend("sn_borrado").constrain("0").not());
            ObjectSet result = query.execute();
            List <DB.kits> lista=result;
            Object datos[][]=new Object [lista.size()][7];
            int i=0;
            while(i<lista.size())
            {
                datos[i][0]=lista.get(i).getId_kit();
                datos[i][1]=lista.get(i).getDe_kit();
                datos[i][2]=lista.get(i).getIm_kit();
                datos[i][3]=lista.get(i).getNu_cantidad();
                datos[i][4]=lista.get(i).getNu_piezas();
                datos[i][5]=lista.get(i).getUbicacion();
                datos[i][6]=lista.get(i).getSn_incompleto();
                i++;
            }
            return datos;
	}
         
         public static void setComplete(ObjectContainer db, String id_kit,String ubicacion,String complete)
         {
             ObjectSet result = db
				.queryByExample(new DB.kits(id_kit,null,0,0,0,null,ubicacion,"0"));
		DB.kits found = (DB.kits) result.next();
                found.setSn_incompleto(complete);
		db.store(found);
         }
          //AGREGA UNA NUEVO KIT
	public static void newKit(ObjectContainer db, String id_kit, String de_kit, float im_kit, int nu_cantidad,int nu_piezas,String ubicacion) {
		DB.kits DBD = new DB.kits(id_kit,de_kit,im_kit,nu_cantidad,nu_piezas,"1",ubicacion,"0");
		db.store(DBD);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        public static void editarKit(ObjectContainer db, String id_kit, String de_kit, float im_kit, int nu_cantidad,int nu_piezas,String ubicacion,String isComplete) {
		ObjectSet result = db
				.queryByExample(new DB.kits(id_kit,null,0,0,0,null,ubicacion,"0"));
		DB.kits found = (DB.kits) result.next();
		found.setDe_kit(de_kit);
                found.setIm_kit(im_kit);
                found.setNu_cantidad(nu_cantidad);
                found.setNu_piezas(nu_piezas);
                found.setSn_incompleto(isComplete);
		db.store(found);
		Session.mng=new manejador(false,"Datos guardados correctamente.");
	}
        
        public static int getCantidad(ObjectContainer db, String id_articulo,String ubicacion) 
        {
            if(existeKit(db, id_articulo))
            {
                Object articulo[][]=getKitPorID(db,id_articulo,ubicacion);
                if(articulo.length>0)
                    return Integer.parseInt(""+articulo[0][3]);
                else
                    return 0;
            }
            else
                return 0;
	}
}

