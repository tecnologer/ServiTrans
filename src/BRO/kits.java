package BRO;

import Productos.eliminado;
import javax.swing.JOptionPane;
import servitrans.Session;
import tools.Util;
import tools.manejador;
import tools.msj;

public class kits 
{
    public static manejador agregarKit(String id_kit,String  de_kit, String im_kit,String  cantidad, String nu_piezas,String detalle[][])
    {
        try
        {
            if(Util.isLong(id_kit))
            {
                String id_articulo=id_kit;
                im_kit=Util.quitarFormatoMoneda(im_kit);
                if(Util.isFloat(im_kit))
                {
                    float im_refaccion=Float.parseFloat(im_kit);
                    if(Util.isNumeric(cantidad))
                    {
                        int nu_cantidad=Integer.parseInt(cantidad);
                        if(!DAO.refacciones.existeRefaccion(Session.cnx, id_articulo,Session.ubicacion))
                        {
                            if(!DAO.kits.existeKit(Session.cnx, id_articulo))
                            {
                                
                                for(int i=0;i<detalle.length;i++)
                                {
                                    if(Util.isLong(detalle[i][0]))
                                    {
                                        detalle[i][2]=Util.quitarFormatoMoneda(detalle[i][2]);
                                        if(Util.isFloat(detalle[i][2]))
                                        {
                                            if(Util.isNumeric(detalle[i][3]))
                                                if(!DAO.kitDetalle.existRefaccion(Session.cnx, id_articulo, detalle[i][0], Session.ubicacion))
                                                    DAO.kitDetalle.addDetailKit(Session.cnx, id_articulo, detalle[i][0], detalle[i][1].toUpperCase(),Float.parseFloat(detalle[i][2]),Integer.parseInt(detalle[i][3]));
                                                else
                                                   return Session.mng=new manejador(true, "La refacción \""+detalle[i][1].toUpperCase()+"\" ya se encuentra registrada en el kit.");
                                            else
                                            {
                                                return Session.mng=new manejador(true, "La cantidad de la pieza \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                            }
                                        }
                                        else
                                        {
                                            return Session.mng=new manejador(true, "El importe del articulo \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                        }
                                    }
                                    else
                                    {
                                        return Session.mng=new manejador(true, "El codigo del articulo \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                    }
                                }
                                if(!Session.mng.isError())
                                    DAO.kits.newKit(Session.cnx, id_articulo, de_kit.toUpperCase(), im_refaccion, nu_cantidad,Integer.parseInt(nu_piezas),Session.ubicacion);
                                
                            }
                            else
                            {
                                int opc=msj.cf("Ya existe", "El kit ya se encuentra registrado.\n¿Desea actualizar la información?");
                                String isComplete="0";
                                if(opc==JOptionPane.YES_OPTION)
                                {
                                    if(DAO.kits.isComplete(Session.cnx, id_kit, Session.ubicacion))
                                        isComplete="1";
                                    DAO.kits.editarKit(Session.cnx, id_articulo, de_kit.toUpperCase(), im_refaccion, nu_cantidad,Integer.parseInt(nu_piezas),Session.ubicacion,isComplete);
                                }
                                else
                                {
                                   return Session.mng=new manejador(false,"");
                                }
                            }
                            
                        }
                        else
                        {
                           return  Session.mng=new manejador(true,"No se puede guardar como kit, ya que es una refacción.");
                        }
                    }
                    else
                    {
                        return Session.mng=new manejador(true,"La cantidad de inventario del kit debe ser un numero entero.");
                    }
                }
                else
                {
                    return Session.mng=new manejador(true,"El importe del kit debe ser numerico.");
                }
            }
            else
            {
                return Session.mng=new manejador(true,"El id del kit debe ser numerico.");
            }
            
            return Session.mng=new manejador(false,"Producto guardado correctamente.");
        }
        catch(Exception e)
        {
            return Session.mng=new manejador(true,"Ocurrio un error.\nDetalle:\n"+e.getMessage());
        }
    }
    
    public static manejador editarKit(String id_kit,String  de_kit, String im_kit,String  cantidad, String nu_piezas,String detalle[][], eliminado eli[],String ubicacion)
    {
        try
        {
            //VERIFICA QUE EL ID DEL KIT ESTE CORRECTO
            if(Util.isLong(id_kit))
            {
                String id_articulo=id_kit;
                //LE QUITAMOS LOS SIGNOS DE PESOS Y COMAS AL IMPORTE
                im_kit=Util.quitarFormatoMoneda(im_kit);
                //VERIFICAMOS QUE EL IMPORTE SEA FLOAT
                if(Util.isFloat(im_kit))
                {
                    //CONVERTIMOS EL IMPORTE A FLOAT, YA QUE VIENE DE TIPO STRING
                    float im_refaccion=Float.parseFloat(im_kit);
                    //VERIFICAMOS QUE LA CANTIDAD SEA NUMERICA
                    if(Util.isNumeric(cantidad))
                    {
                        //CONVERTIMOS LA CANTIDAD A NUMERICA TIPO INT
                        int nu_cantidad=Integer.parseInt(cantidad);
                        //COMPROBAMOS QUE EL ID NO PERTENESCA A UNA REFACCION
                        if(!DAO.refacciones.existeRefaccion(Session.cnx, id_articulo,ubicacion))
                        {
                            //COMPROBAMOS QUE EL KIT NO EXISTA
                            if(DAO.kits.existeKit(Session.cnx, id_articulo))
                            {
                                //RECORRE EL VECTOR DE DETALLES
                                for(int i=0;i<detalle.length;i++)
                                {
                                    //VERIFICA QUE EL ID DE LA REFACCION SEA NUMERICO
                                    if(Util.isLong(detalle[i][0]))
                                    {
                                         detalle[i][2]=Util.quitarFormatoMoneda(detalle[i][2]);
                                        //VERIFICA QUE EL IMPORTE SEA DE TIPO FLOAT
                                        if(Util.isFloat(detalle[i][2]))
                                        {
                                            //VERIFICA QUE LA CANTIDAD SEA NUMERICA
                                            if(Util.isNumeric(detalle[i][3]))
                                                //AGREGAR NUEVA REFACCION AL KIT
                                                if(!DAO.kitDetalle.existRefaccion(Session.cnx, id_articulo, detalle[i][0],ubicacion))
                                                    DAO.kitDetalle.addDetailKit(Session.cnx, id_articulo, detalle[i][0], detalle[i][1].toUpperCase(),Float.parseFloat(detalle[i][2]),Integer.parseInt(detalle[i][3]));
                                                //EDITAR LOS DATOS DE LA REFACCION
                                                else
                                                    DAO.kitDetalle.editarDetalle(Session.cnx, id_articulo, detalle[i][0], detalle[i][1], Float.parseFloat(detalle[i][2]), Integer.parseInt(detalle[i][3]), ubicacion);
                                            else
                                            {
                                               return Session.mng=new manejador(true, "La cantidad de la pieza \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                            }
                                        }
                                        else
                                        {
                                           return Session.mng=new manejador(true, "El importe del articulo \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                        }
                                    }
                                    else
                                    {
                                       return Session.mng=new manejador(true, "El codigo del articulo \""+detalle[i][1].toUpperCase()+"\" debe ser numerico");
                                    }
                                }
                                //ELIMINAR REFACCIONES DEL KIT
                                for(int j=0;j<eli.length;j++)
                                {
                                    if(!eli[j].getCodigo().equals(""))
                                        if(eli[j].isDb())
                                            if(eli[j].isDelete())
                                                DAO.kitDetalle.deleteRefaccion(Session.cnx, id_articulo, eli[j].getCodigo(), ubicacion);
                                }
                                if(!Session.mng.isError())
                                {
                                    if(!DAO.kits.existeKit(Session.cnx, id_articulo))
                                        DAO.kits.newKit(Session.cnx, id_articulo, de_kit.toUpperCase(), im_refaccion, nu_cantidad,Integer.parseInt(nu_piezas),Session.ubicacion);
                                    else
                                    {
                                        String isComplete="0";
                                        if(DAO.kits.isComplete(Session.cnx, id_kit, ubicacion))
                                            isComplete="1";
                                        DAO.kits.editarKit(Session.cnx, id_articulo, de_kit.toUpperCase(), im_refaccion, nu_cantidad, Integer.parseInt(nu_piezas), ubicacion,isComplete);
                                    }
                                }
                                
                                
                            }
                            else
                            {
                                int opc=msj.cf("Ya existe", "El kit ya se encuentra registrado.\n¿Desea actualizar la información?");
                                if(opc==JOptionPane.YES_OPTION)
                                {
                                    String isComplete="0";
                                    if(DAO.kits.isComplete(Session.cnx, id_kit, Session.ubicacion))
                                        isComplete="1";
                                    DAO.kits.editarKit(Session.cnx, id_articulo, de_kit.toUpperCase(), im_refaccion, nu_cantidad,Integer.parseInt(nu_piezas),Session.ubicacion,isComplete);
                                    return Session.mng=new manejador(false,"");
                                }
                                else
                                {
                                  return  Session.mng=new manejador(true,"");
                                }
                            }
                            
                        }
                        else
                        {
                            Session.mng=new manejador(true,"No se puede guardar como kit, ya que es una refacción.");
                            return Session.mng;
                        }
                    }
                    else
                    {
                        Session.mng=new manejador(true,"La cantidad de inventario del kit debe ser un numero entero.");
                    }
                }
                else
                {
                    Session.mng=new manejador(true,"El importe del kit debe ser numerico.");
                }
            }
            else
            {
                Session.mng=new manejador(true,"El id del kit debe ser numerico.");
            }
            
            return Session.mng=new manejador(false,"Datos guardados correctamente.");
        }
        catch(Exception e)
        {
            Session.mng=new manejador(true,"Ocurrio un error.\nDetalle:\n"+e.getMessage());
            return Session.mng;
        }
    }
}
