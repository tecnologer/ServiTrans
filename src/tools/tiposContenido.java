/****************************************************
 * Autor: REY DAVID DOMINGUEZ
 * FECHA: 09/05/2013
 * UTILIDADES QUE RESTRINGEN LA ENTRADA INCORRECTA DE CARACTERES SEGUN CORRESPONDA
 ****************************************************/
package tools;

public class tiposContenido 
{

    public boolean numerico(int k)
    {
       if (k <48 || k>57)
            return false;
       else
           return true;
    }
    
    public boolean flotante(int k)
    {
       if (k <48 || k>57 || k!=46)
            return false;
       else
           return true;
    }
}
