package DB;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.*;
import tools.msj;

public class cnxAlmacen
{
    private static cnxAlmacen conexion=null;
    
    private static String path="insorob.al";
    private static ObjectContainer db;
    
    public cnxAlmacen()
    {
    }
    
    private synchronized static void crear_conexion()
    {
        if (conexion==null)
        {
            conexion=new cnxAlmacen();
            conexion.conexion();
        }
        
    }
    
    public  void conexion()
    {
        
        try
        {
            EmbeddedConfiguration config=Db4oEmbedded.newConfiguration();
            db=Db4oEmbedded.openFile(config,path);
        }
        catch(Db4oIOException | DatabaseFileLockedException | IncompatibleFileFormatException | OldFormatException | DatabaseReadOnlyException e)
        {
            msj.ms("Error", "Solo se permite una instancia abierto.\nDetalle:\n"+e.getMessage(),msj.error);
            System.exit(0);
        }
            
       
    }
    
    public static ObjectContainer obtener_conexion()
    {
        if(conexion==null)
        {
           crear_conexion();
        }
        return db;
    }
    
    public static void cerrar_conexion()
    {
        try
        {
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static cnxAlmacen getConexion() {
        return conexion;
    }
    
    
}