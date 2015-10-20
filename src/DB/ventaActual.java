package DB;

public class ventaActual 
{
    private Object  row;
    private Object   id_articulo;
    private Object de_articulo;
    private Object  im_articulo;
    private Object    nu_cantidad;
    private Object  im_total;
    private Object ubicacion;
    private Object id_kit;

    public ventaActual(Object row, Object id_articulo, Object de_articulo, Object im_articulo, Object nu_cantidad, Object im_total, Object ubicacion, Object id_kit) {
        this.row = row;
        this.id_articulo = id_articulo;
        this.de_articulo = de_articulo;
        this.im_articulo = im_articulo;
        this.nu_cantidad = nu_cantidad;
        this.im_total = im_total;
        this.ubicacion = ubicacion;
        this.id_kit = id_kit;
    }

    public Object getId_kit() {
        return id_kit;
    }

    public Object getRow() {
        return row;
    }

    public Object getUbicacion() {
        return ubicacion;
    }

    public Object getId_articulo() {
        return id_articulo;
    }

    public Object getDe_articulo() {
        return de_articulo;
    }

    public Object getIm_articulo() {
        return im_articulo;
    }

    public Object getNu_cantidad() {
        return nu_cantidad;
    }

    public Object getIm_total() {
        return im_total;
    }   
    
}
