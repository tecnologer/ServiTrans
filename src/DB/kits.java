package DB;

public class kits 
{
    private String   id_kit;
    private String de_kit;
    private float  im_kit;
    private int nu_cantidad;
    private int    nu_piezas;
    private String sn_incompleto;
    private String ubicacion;
    private String sn_borrado;

    public kits(String id_kit, String de_kit, float im_kit, int nu_cantidad, int nu_piezas, String sn_incompleto, String ubicacion, String sn_borrado) {
        this.id_kit = id_kit;
        this.de_kit = de_kit;
        this.im_kit = im_kit;
        this.nu_cantidad = nu_cantidad;
        this.nu_piezas = nu_piezas;
        this.sn_incompleto = sn_incompleto;
        this.ubicacion = ubicacion;
        this.sn_borrado = sn_borrado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    public String getDe_kit() {
        return de_kit;
    }

    public String getId_kit() {
        return id_kit;
    }

    public float getIm_kit() {
        return im_kit;
    }

    public int getNu_cantidad() {
        return nu_cantidad;
    }

    public int getNu_piezas() {
        return nu_piezas;
    }

    public String getSn_borrado() {
        return sn_borrado;
    }

    public String getSn_incompleto() {
        return sn_incompleto;
    }

    public void setDe_kit(String de_kit) {
        this.de_kit = de_kit;
    }

    public void setId_kit(String id_kit) {
        this.id_kit = id_kit;
    }

    public void setIm_kit(float im_kit) {
        this.im_kit = im_kit;
    }

    public void setNu_cantidad(int nu_cantidad) {
        this.nu_cantidad = nu_cantidad;
    }

    public void setNu_piezas(int nu_piezas) {
        this.nu_piezas = nu_piezas;
    }

    public void setSn_borrado(String sn_borrado) {
        this.sn_borrado = sn_borrado;
    }

    public void setSn_incompleto(String sn_incompleto) {
        this.sn_incompleto = sn_incompleto;
    }
    
    
}
