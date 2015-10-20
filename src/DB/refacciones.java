package DB;

public class refacciones 
{
 
    private String id_refaccion;
    private String de_refaccion;
    private float im_refaccion;
    private int nu_cantidad;
    private String ubicacion;
    private String sn_borrado;

    public refacciones(String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad, String ubicacion, String sn_borrado) {
        this.id_refaccion = id_refaccion;
        this.de_refaccion = de_refaccion;
        this.im_refaccion = im_refaccion;
        this.nu_cantidad = nu_cantidad;
        this.ubicacion = ubicacion;
        this.sn_borrado = sn_borrado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDe_refaccion() {
        return de_refaccion;
    }

    public String getId_refaccion() {
        return id_refaccion;
    }

    public float getIm_refaccion() {
        return im_refaccion;
    }

    public int getNu_cantidad() {
        return nu_cantidad;
    }

    public String getSn_borrado() {
        return sn_borrado;
    }

    public void setDe_refaccion(String de_refaccion) {
        this.de_refaccion = de_refaccion;
    }

    public void setId_refaccion(String id_refaccion) {
        this.id_refaccion = id_refaccion;
    }

    public void setIm_refaccion(float im_refaccion) {
        this.im_refaccion = im_refaccion;
    }

    public void setNu_cantidad(int nu_cantidad) {
        this.nu_cantidad = nu_cantidad;
    }

    public void setSn_borrado(String sn_borrado) {
        this.sn_borrado = sn_borrado;
    }
    
    
}
