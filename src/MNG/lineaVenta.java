package MNG;

import servitrans.Session;
import tools.Util;
import tools.msj;

public class lineaVenta 
{
    
    public static void nueva(String codigo)
    {
        BRO.lineaVenta.nueva(codigo);
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
    }
}
