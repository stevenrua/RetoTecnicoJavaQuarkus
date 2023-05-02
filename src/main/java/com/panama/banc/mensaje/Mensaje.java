package com.panama.banc.mensaje;

public class Mensaje {
    private String error;
    private String detail;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void mensajeErrorBD(String mensaje){
        String[] message = mensaje.split("Detail:");
        setError(message[0].split("ERROR:")[1]);
        setDetail(message[1]);
    }

    public void mensajeNotFound(String mensaje){
        setError(mensaje);
        setDetail(mensaje);
    }
}
