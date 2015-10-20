package DB;

import java.util.Date;


public class ventas 
{
    private String id_venta;
    private String fh_venta;
    private String nu_articulos;
    private float im_venta;
    private String id_usuario;

    public ventas(String id_venta, String fh_venta, String nu_articulos, float im_venta, String id_usuario) {
        this.id_venta = id_venta;
        this.fh_venta = fh_venta;
        this.nu_articulos = nu_articulos;
        this.im_venta = im_venta;
        this.id_usuario = id_usuario;
    }

    public String getFh_venta() {
        return fh_venta;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getId_venta() {
        return id_venta;
    }

    public float getIm_venta() {
        return im_venta;
    }

    public String getNu_articulos() {
        return nu_articulos;
    }

    public void setFh_venta(String fh_venta) {
        this.fh_venta = fh_venta;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public void setIm_venta(float im_venta) {
        this.im_venta = im_venta;
    }

    public void setNu_articulos(String nu_articulos) {
        this.nu_articulos = nu_articulos;
    }
    
    
}
