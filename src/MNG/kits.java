package MNG;

import Productos.eliminado;
import servitrans.Session;
import tools.msj;

public class kits 
{
    public static boolean agregarKit(String id_kit,String  de_kit, String im_kit,String  nu_cantidad, String nu_piezas,String detalle[][])
    {
        BRO.kits.agregarKit(id_kit, de_kit, im_kit, nu_cantidad,nu_piezas,detalle);
        
        if(Session.mng.isError())
        {
            msj.ms("Error", Session.mng.getMsj(),msj.error);
            Session.cnx.rollback();
        }
        else if(!Session.mng.getMsj().equals(""))
        {
            msj.ms("", Session.mng.getMsj(),msj.info);
            Session.cnx.commit();
        }
        else
            Session.cnx.commit();
        
        return Session.mng.isError();
    }
    
    public static boolean editarKit(String id_kit,String  de_kit, String im_kit,String  nu_cantidad, String nu_piezas,String detalle[][], eliminado eli[],String ubicacion)
    {
        BRO.kits.editarKit(id_kit, de_kit, im_kit, nu_cantidad,nu_piezas,detalle,eli,ubicacion);
        
        if(Session.mng.isError())
        {
            msj.ms("Error", Session.mng.getMsj(),msj.error);
            Session.cnx.rollback();
        }
        else if(!Session.mng.getMsj().equals(""))
        {
            msj.ms("", Session.mng.getMsj(),msj.info);
            Session.cnx.commit();
        }
        else
            Session.cnx.commit();
        
        return Session.mng.isError();
    }
}
