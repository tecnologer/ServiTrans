package servitrans;
import java.text.*;


public class Formatea 
{
	public static StringBuffer alinder(String patron, double dato)
	{
		FieldPosition fp=new FieldPosition(NumberFormat.FRACTION_FIELD);
		DecimalFormat formato= new DecimalFormat(patron);
		StringBuffer salida=new StringBuffer();
		formato.format(dato,salida,fp);
	
		for (int i=0; i< (patron.length()-fp.getEndIndex()); i++)
			salida.insert(0,' ');
		return salida;
	}

	public static double alinder(String string, String string2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
