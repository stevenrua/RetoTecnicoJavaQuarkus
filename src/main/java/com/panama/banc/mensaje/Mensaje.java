package com.panama.banc.mensaje;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Mensaje{" +
                "error='" + error + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensaje mensaje)) return false;
        return Objects.equals(getError(), mensaje.getError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getError());
    }
}
