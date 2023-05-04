package com.panama.banc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente extends Persona{
    private String contrasena;
    private boolean estado;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String genero, int edad, String identificacion, String direccion, String telefono, String contrasena, boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @JsonIgnore
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "contrasena='" + contrasena + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        if (!super.equals(o)) return false;
        return isEstado() == cliente.isEstado() && getContrasena().equals(cliente.getContrasena()) && Objects.equals(getCuentas(), cliente.getCuentas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContrasena(), isEstado(), getCuentas());
    }
}
