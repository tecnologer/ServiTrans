package tools;

import DB.cnxServiTrans;
import java.util.Date;
import servitrans.Session;

public class pruebas 
{
 
    public static void main(String arg[])
    {
        //DAO.kits.editarKit(Session.cnx, "45", "KIT TRANSMISION AUTOMATICA TSURU 2000", 
                    //Float.parseFloat("3421.80"), 5, 4, "Sucursal", "0");
//        DAO.refacciones.clearDatabase(Session.cnx);
//       Object datos[][]=DAO.refacciones.getRefaccionPorID(Session.cnx, "",Session.ubicacion);
       
//       for(int i=0;i<datos.length;i++)
//       {
//           System.out.println();
//           for(int j=0;j<datos[0].length;j++)
//               System.out.println(datos[i][j]+",");
//       }
        DAO.ventaActual.clearDatabase(Session.cnx);
        cnxServiTrans.cerrar_conexion();
    }       
}
