package MNG;

import servitrans.Session;
import tools.msj;

public class pagos 
{

    public static boolean recibirPago(String monto,String montoventa, char tipo)
    {
        BRO.pagos.recibirPago(monto, montoventa, tipo);
       
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
