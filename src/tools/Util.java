package tools;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;


public class Util {
    
    static Calendar  calendar = Calendar .getInstance();
    
    public static void listResult(List<?> result){
    	System.out.println(result.size());
        for (Object o : result) {
            System.out.println(o);
        }
    }
    
    public static void listRefreshedResult(ObjectContainer container,ObjectSet result,int depth) {
        System.out.println(result.size());
        while(result.hasNext()) {
            Object obj = result.next();
            container.ext().refresh(obj, depth);
            System.out.println(obj);
        }
    }
    
    public static void retrieveAll(ObjectContainer db){
        ObjectSet result=db.queryByExample(new Object());
        listResult(result);
    }
    
    public static void deleteAll(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Object());
        while(result.hasNext()) {
            db.delete(result.next());
        }
    }
    
    public static boolean isNumeric(String num)
    { try{
            Integer.parseInt(num);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean isLong(String num)
    { try{
            Long.parseLong(num);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean isFloat(String num)
    { try{
            Float.parseFloat(num);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static String[] splitCantCod(String cantYcod)
    {
      StringTokenizer Token=new StringTokenizer (cantYcod,"*");
      String cantCod[]=new String [2];
      
      if(Token.countTokens()==1)
      {
          cantCod[0]="1";
          cantCod[1]=Token.nextToken();
      }
      else
      {
            cantCod[0]=Token.nextToken();
            cantCod[1]=Token.nextToken();
      }
      
      return cantCod;
    }
    
    public static String formatoMoneda(String monto)
    {
         
         String newMonto="";
        if(isNumeric(monto))
            newMonto="$ "+monto+".00";
        else if(isFloat(monto))
        {
            String punto[]=new String [2];
            StringTokenizer Token=new StringTokenizer (monto,".");
            int i=0;
            while(Token.hasMoreTokens())
            {
                punto[i]=Token.nextToken();
                i++;
            }
                if(punto[1].length()!=2)
                      newMonto="$ "+punto[0]+'.'+punto[1]+"0";
                else
                    newMonto="$ "+monto;
        }
        return newMonto;
    }
    
    public static String quitarFormatoMoneda(String monto)
    {
        String newMonto="";
        
        for(int i=0;i<monto.length();i++)
        {
            if(monto.charAt(i)!='$' && monto.charAt(i)!=' ' && monto.charAt(i)!=',')
                newMonto+=monto.charAt(i);
        }
        
        return newMonto;
    }
    
    public static String formatDate(String date)
    {
        String Fecha[];
        Fecha=date.split("/");
        int dia=Integer.parseInt(Fecha[1]);
        int mes=Integer.parseInt(Fecha[0]);
        String Dia;
        String Mes;
        if(dia<10)
            Dia="0"+dia;
        else
            Dia=""+dia;
        if(mes<10)
            Mes="0"+mes;
        else
            Mes=""+mes;
        
        String anio;
        if(Fecha[2].length()==2)
             anio="20"+Fecha[2];
        else
            anio=Fecha[2];
        
        String newDate=Dia+"/"+Mes+"/"+anio;
        return newDate;
    }
    
     public static String delFormatDate(String date)
    {
        String Fecha[];
        Fecha=date.split("/");
        int dia=Integer.parseInt(Fecha[0]);
        int mes=Integer.parseInt(Fecha[1]);
        
        String anio;
        if(Fecha[2].length()==2)
             anio=Fecha[2];
        else
            anio=Fecha[2].charAt(2)+""+Fecha[2].charAt(3);
        
        String newDate=mes+"/"+dia+"/"+anio;
        return newDate;
    }
    
    public static String[] betweenDate(String date_inicial,String date_final)
    {
        int nu_diasMes[]={31,28,31,30,31,30,31,31,30,31,30,31};
        String FechaInicial[]=date_inicial.split("/");
        String FechaFinal[]=date_final.split("/");
        String Fechas[]=new String [0];
        int dia_inicial=Integer.parseInt(FechaInicial[0]);
        int mes_inicial=Integer.parseInt(FechaInicial[1]);
        int year_inicial=Integer.parseInt(FechaInicial[2]);
        int dia_final=Integer.parseInt(FechaFinal[0]);
        int mes_final=Integer.parseInt(FechaFinal[1]);
        int year_final=Integer.parseInt(FechaFinal[2]);        
        int currentmonth=mes_inicial;
        int currentYear=year_inicial;
        int currenDay=dia_inicial;
        
        while(currentYear<=year_final)
        {
            if(isLeap(currentYear))//si es biciesto
                nu_diasMes[1]=29;//asiganmos un dia mas a febrero
            else
                nu_diasMes[1]=28;//si no, asignamos los 28 dias de un año normal
            if(currentmonth<=mes_final)
            {    
                while(currentmonth<=mes_final)
                {
                    int lastDay_Month=nu_diasMes[currentmonth-1];
                    if(currenDay<=dia_final)
                        while(currenDay<=dia_final)
                        {
                            Fechas=newDate(Fechas, currenDay, currentmonth, currentYear);                    
                            currenDay++;
                        }
                    else
                        while(currenDay>dia_final)
                        {

                            Fechas=newDate(Fechas, currenDay, currentmonth, currentYear);                    
                            currenDay++;
                            if(currenDay>lastDay_Month)
                            {
                                currenDay=1;
                            }
                        }

                    currentmonth++;
                }
            }
            else
            {
                while(currentmonth>mes_final)
                {
                    int lastDay_Month=nu_diasMes[currentmonth-1];
                    if(currenDay<=dia_final)
                        while(currenDay<=dia_final)
                        {
                            Fechas=newDate(Fechas, currenDay, currentmonth, currentYear);                    
                            currenDay++;
                        }
                    else
                        while(currenDay>dia_final)
                        {

                            Fechas=newDate(Fechas, currenDay, currentmonth, currentYear);                    
                            currenDay++;
                            if(currenDay>lastDay_Month)
                            {
                                currenDay=1;
                            }
                        }

                    currentmonth++;
                    if(currentmonth>12)
                        currentmonth=1;
                }
            }
            currentYear++;
        }
        return Fechas;
    }
    
    private static String[] newDate(String fechas[], int dia, int mes, int year)
    {
        String fecha[]=new String [fechas.length+1];     
        String newDate=formatDate(""+dia+"/"+mes+"/"+year);
        int i=0;
        for(i=0;i<fechas.length;i++)
            fecha[i]=fechas[i];
        
        fecha[i]=newDate;
        
        return fecha;
    }
    //es bisiesto
    private static boolean isLeap(int year)
    {
        if(year<100)
            year+=2000;
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
            return true;
        else
            return false;
    }
    
    public static boolean validaFecha(String fecha)
    {
        String date[]=fecha.split("/");
        
        if(date.length!=3)
            return false;
        
        if(!isNumeric(date[0]))
            return false;
        else if(Integer.parseInt(date[0])<1)
            return false;
        else if(Integer.parseInt(date[0])>31)
            return false;
            
        
        if(!isNumeric(date[1]))
            return false;
        else if(Integer.parseInt(date[1])<1)
            return false;
        else if(Integer.parseInt(date[1])>12)
            return false;
        
        if(!isNumeric(date[2]))
            return false;
        else if(Integer.parseInt(date[2])<1)
            return false;
        else if(Integer.parseInt(date[2])<1999 || Integer.parseInt(date[2])>2100)
            return false;
        
        return true;
    }
    
    //OBTIENE LA FECHA ACTUAL Y LE DA FORMATO
    public static String getFecha()    {
        
        String d;
        String m;
        int dia = calendar.get(Calendar .DAY_OF_MONTH);   //dia del mes
        int mes = calendar.get(Calendar .MONTH)+1;  //mes, de 0 a 11
        int anio = calendar.get(Calendar .YEAR);  //año
        if(dia<10)
            d="0"+dia;
        else
            d=""+dia;
        if(mes<10)
            m="0"+mes;
        else
            m=""+mes;
        
        return d+"/"+m+"/"+anio;
    }
}

