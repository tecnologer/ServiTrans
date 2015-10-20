package DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;

public class kitsIncompletos 
{
    
    public static String getId_kit(ObjectContainer db,String id_kit,String ubicacion)
    {
        DB.kistIncompletos tabla = new DB.kistIncompletos(id_kit,ubicacion);
        ObjectSet result = db.queryByExample(tabla);
        List <DB.kistIncompletos> lista=result;
        
        if(lista.size()>0)
            return lista.get(0).getId_kit();
        else
            return "";
    }
    
    public static void agregarId_kit(ObjectContainer db,String id_kit,String ubicacion)
    {
        DB.kistIncompletos DBD = new DB.kistIncompletos(id_kit,ubicacion);
        db.store(DBD);
    }
    
    public static void eliminarId_kit(ObjectContainer db,String id_kit,String ubicacion)
    {
        ObjectSet result = db.queryByExample(new DB.kistIncompletos(id_kit,ubicacion));
        DB.kistIncompletos found = (DB.kistIncompletos) result.next();
        db.delete(found);
    }
}
