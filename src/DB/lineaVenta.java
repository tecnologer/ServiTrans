package DB;

public class lineaVenta 
{
    private int    id_venta;
    private long   id_articulo;
    private String de_articulo;
    private float  im_articulo;
    private int    nu_cantidad;
    private float  im_total;
    private String sn_borrado;

    public lineaVenta(int id_venta, long id_articulo, String de_articulo, float im_articulo, int nu_cantidad, float im_total, String sn_borrado) {
        this.id_venta = id_venta;
        this.id_articulo = id_articulo;
        this.de_articulo = de_articulo;
        this.im_articulo = im_articulo;
        this.nu_cantidad = nu_cantidad;
        this.im_total = im_total;
        this.sn_borrado = sn_borrado;
    }

    public String getDe_articulo() {
        return de_articulo;
    }

    public long getId_articulo() {
        return id_articulo;
    }

    public int getId_venta() {
        return id_venta;
    }

    public float getIm_articulo() {
        return im_articulo;
    }

    public float getIm_total() {
        return im_total;
    }

    public int getNu_cantidad() {
        return nu_cantidad;
    }

    public String getSn_borrado() {
        return sn_borrado;
    }

    public void setDe_articulo(String de_articulo) {
        this.de_articulo = de_articulo;
    }

    public void setId_articulo(long id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public void setIm_articulo(float im_articulo) {
        this.im_articulo = im_articulo;
    }

    public void setIm_total(float im_total) {
        this.im_total = im_total;
    }

    public void setNu_cantidad(int nu_cantidad) {
        this.nu_cantidad = nu_cantidad;
    }

    public void setSn_borrado(String sn_borrado) {
        this.sn_borrado = sn_borrado;
    }
    
    
}
