package MNG;

import servitrans.Session;
import tools.msj;

public class productos 
{
 
    public static boolean actualizarInventario(String id,String cant)
    {
        BRO.productos.actualizarInventario(id,cant);
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
        
        return Session.mng.isError();
    }
    
    public static void editar()
    {
        BRO.productos.editarArticulo();
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
    }
    
    public static boolean buscarArticulo(String id,String descrip,byte tpo,byte local)
    {
        BRO.productos.buscarArticulo(id,descrip,tpo,local);
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
        
        return Session.mng.isError();
    }
}
