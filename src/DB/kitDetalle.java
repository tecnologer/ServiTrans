package DB;

public class kitDetalle 
{
    private String   id_kit;
    private String   id_refaccion;
    private String de_refaccion;
    private float  im_refaccion;
    private int    nu_cantidad;
    private int    min_cantidad;
    private String sn_borrado;
    private String ubicacion;

    public kitDetalle(String id_kit, String id_refaccion, String de_refaccion, float im_refaccion, int nu_cantidad, int min_cantidad, String sn_borrado, String ubicacion) {
        this.id_kit = id_kit;
        this.id_refaccion = id_refaccion;
        this.de_refaccion = de_refaccion;
        this.im_refaccion = im_refaccion;
        this.nu_cantidad = nu_cantidad;
        this.min_cantidad = min_cantidad;
        this.sn_borrado = sn_borrado;
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }   

    public int getMin_cantidad() {
        return min_cantidad;
    }

    public void setMin_cantidad(int min_cantidad) {
        this.min_cantidad = min_cantidad;
    }

   
    public String getDe_refaccion() {
        return de_refaccion;
    }

    public String getId_kit() {
        return id_kit;
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

    public void setId_kit(String id_kit) {
        this.id_kit = id_kit;
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
