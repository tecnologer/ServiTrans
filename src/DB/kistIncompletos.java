package DB;

public class kistIncompletos 
{
    private String id_kit;
    private String ubicacion;

    public kistIncompletos(String id_kit, String ubicacion) {
        this.id_kit = id_kit;
        this.ubicacion = ubicacion;
    }

    public String getId_kit() {
        return id_kit;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    
}
