package envios;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private int indice;
    private String mensaje;

    public Mensaje(String mensaje, int indice) {
        this.mensaje = mensaje;
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}