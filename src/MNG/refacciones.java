package MNG;

import servitrans.Session;
import tools.msj;

public class refacciones 
{
    
    public static boolean agregarRefaccion(String id_refaccion,String  de_refaccion, String im_refaccion,String  nu_cantidad)
    {
        BRO.refacciones.agregarRefaccion(id_refaccion, de_refaccion, im_refaccion, nu_cantidad);
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
        
        return Session.mng.isError();
    }
    
     public static boolean editarRefaccion(String id_refaccion,String  de_refaccion, String im_refaccion,String  nu_cantidad,String ubicacion)
    {
        BRO.refacciones.editarRefaccion(id_refaccion, de_refaccion, im_refaccion, nu_cantidad,ubicacion);
        
        if(Session.mng.isError())
            msj.ms("Error", Session.mng.getMsj(),msj.error);
        else if(!Session.mng.getMsj().equals(""))
            msj.ms("", Session.mng.getMsj(),msj.info);
        
        return Session.mng.isError();
    }
}
