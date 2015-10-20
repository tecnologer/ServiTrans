package BRO;

import javax.swing.JOptionPane;
import servitrans.Session;
import tools.*;

public class refacciones 
{
    public static manejador agregarRefaccion(String id,String descripcion,String importe,String cantidad)
    {
        try
        {
            if(Util.isLong(id))
            {
                String id_articulo=id;
                importe=Util.quitarFormatoMoneda(importe);
                if(Util.isFloat(importe))
                {
                    float im_refaccion=Float.parseFloat(importe);
                    if(Util.isNumeric(cantidad))
                    {
                        int nu_cantidad=Integer.parseInt(cantidad);
                        if(!DAO.refacciones.existeRefaccion(Session.cnx, id_articulo,Session.ubicacion))
                        {
                            if(!DAO.kits.existeKit(Session.cnx, id_articulo))
                            {
                                DAO.refacciones.newRefaccion(Session.cnx, id_articulo, descripcion.toUpperCase(), im_refaccion, nu_cantidad,Session.ubicacion);
                            }
                            else
                            {
                                Session.mng=new manejador(true,"No se puede guardar como refacción, ya que es un kit.");
                                return Session.mng;
                            }
                        }
                        else
                        {
                             int opc=msj.cf("Ya existe", "La refaccion ya se encuentra regitsrada.\n¿Desea actualizar la información?");
                             if(opc==JOptionPane.YES_OPTION)
                                 DAO.refacciones.editarArticulo(Session.cnx, id_articulo, descripcion.toUpperCase(), im_refaccion, nu_cantidad,Session.ubicacion);
                             else
                             {
                                 Session.mng=new manejador(false,"");
                             }
                        }
                    }
                    else
                    {
                        Session.mng=new manejador(true,"La cantidad de la refaccion debe ser un numero entero.");
                    }
                }
                else
                {
                    Session.mng=new manejador(true,"El importe de la refaccion debe ser numerica.");
                }
            }
            else
            {
                Session.mng=new manejador(true,"El id de la refaccion debe ser numerica.");
            }
            
            return Session.mng;
        }
        catch(Exception e)
        {
            Session.mng=new manejador(true,"Ocurrio un error.\nDetalle:\n"+e.getMessage());
            return Session.mng;
        }
    }
    
    public static manejador editarRefaccion(String id,String descripcion,String importe,String cantidad,String ubicacion)
    {
        try
        {
            if(Util.isLong(id))
            {
                String id_articulo=id;
                importe=Util.quitarFormatoMoneda(importe);
                if(Util.isFloat(importe))
                {
                    float im_refaccion=Float.parseFloat(importe);
                    if(Util.isNumeric(cantidad))
                    {
                        int nu_cantidad=Integer.parseInt(cantidad);
                        if(DAO.refacciones.existeRefaccion(Session.cnx, id_articulo,ubicacion))
                        {
                            if(!DAO.kits.existeKit(Session.cnx, id_articulo))
                            {
                                DAO.refacciones.editarArticulo(Session.cnx, id_articulo, descripcion.toUpperCase(), im_refaccion, nu_cantidad,ubicacion);
                            }
                            else
                            {
                                Session.mng=new manejador(true,"No se puede guardar como refacción, ya que es un kit.");
                                return Session.mng;
                            }
                        }
                    }
                    else
                    {
                        return Session.mng=new manejador(true,"La cantidad de la refaccion debe ser un numero entero.");
                    }
                }
                else
                {
                    return Session.mng=new manejador(true,"El importe de la refaccion debe ser numerica.");
                }
            }
            else
            {
                return Session.mng=new manejador(true,"El id de la refaccion debe ser numerica.");
            }
            
            return Session.mng;
        }
        catch(Exception e)
        {
            Session.mng=new manejador(true,"Ocurrio un error.\nDetalle:\n"+e.getMessage());
            return Session.mng;
        }
    }
}
