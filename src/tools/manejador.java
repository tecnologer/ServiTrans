package tools;

public class manejador 
{
    private boolean error;
    private String msj;

    public manejador(boolean error, String msj) {
        this.error = error;
        this.msj = msj;
    }

    public boolean isError() {
        return error;
    }

    public String getMsj() {
        return msj;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
    
    
}
