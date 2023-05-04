package com.panama.banc.mensaje;

public class Mensaje {
    private String error;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void mensajeErrorBD(String mensaje){
        setError(mensaje);

    }

    public void mensajeNotFound(String mensaje){
        setError(mensaje);
    }
}
