package tools;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class msj 
{
    public static int error=JOptionPane.ERROR_MESSAGE;
    public static int info=JOptionPane.INFORMATION_MESSAGE;
    public static int conf=JOptionPane.YES_NO_OPTION;
    
    public static void ms(String enc,String msn,int tipo)
    {
        if(enc.equals(""))
            enc="Zodo";
        JOptionPane.showMessageDialog(null,msn, enc,tipo);
    }
    
     public static int cf(String enc,String msn)
    {
        return JOptionPane.showConfirmDialog(null, msn,enc,conf);
    }
     
      public static Icon getIconInfo()
    {
        ImageIcon icono=new ImageIcon("imagenes/msj/info.png");
        Icon icon=icono;
        return icon;
    }

}
