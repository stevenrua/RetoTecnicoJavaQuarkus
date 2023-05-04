package com.panama.banc.entities;

import java.time.LocalDate;

public class Reporte {
    private LocalDate Fecha;
    private String Cliente;
    private int NumeroCuenta;
    private String Tipo;
    private double SaldoInicial;
    private boolean Estado;
    private double Movimiento;
    private double SaldoDisponible;

    public Reporte(LocalDate fecha, String cliente, int numeroCuenta, String tipo, double saldoInicial, boolean estado, double movimiento, double saldoDisponible) {
        Fecha = fecha;
        Cliente = cliente;
        NumeroCuenta = numeroCuenta;
        Tipo = tipo;
        SaldoInicial = saldoInicial;
        Estado = estado;
        Movimiento = movimiento;
        SaldoDisponible = saldoDisponible;
    }

    public Reporte() {
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate fecha) {
        Fecha = fecha;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public int getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        NumeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public double getSaldoInicial() {
        return SaldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        SaldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public double getMovimiento() {
        return Movimiento;
    }

    public void setMovimiento(double movimiento) {
        Movimiento = movimiento;
    }

    public double getSaldoDisponible() {
        return SaldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        SaldoDisponible = saldoDisponible;
    }
}
