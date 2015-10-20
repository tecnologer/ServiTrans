package Productos;

public class eliminado 
{
    private String codigo;
    private boolean delete;
    private boolean db;
    private int     posicion;

    
    public eliminado(String codigo, boolean delete, boolean db, int posicion) {
        this.codigo = codigo;
        this.delete = delete;
        this.db = db;
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isDb() {
        return db;
    }

    public void setDb(boolean db) {
        this.db = db;
    }
    
    
}
