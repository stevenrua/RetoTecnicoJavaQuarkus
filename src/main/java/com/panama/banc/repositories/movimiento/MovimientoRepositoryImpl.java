package com.panama.banc.repositories.movimiento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class MovimientoRepositoryImpl implements MovimientoRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Object[]> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        System.out.println("Entra aqui psssssssssss989898");
        String query = "SELECT m.date as Fecha, cl.nombre as Cliente, c.numerocuenta as NumeroCuenta, c.tipocuenta as Tipo, c.estado as Estado, m.valor as Movimiento, c.saldoinicial as SaldoDisponible "
                + "FROM movimientos m "
                + "JOIN cuenta c ON m.cuenta_id = c.id "
                + "JOIN cliente cl ON c.cliente_id = cl.id "
                + "WHERE cl.id = :idCliente AND m.date BETWEEN :fechaInicio AND :fechaFin ";

        return entityManager.createNativeQuery(query)
                .setParameter("idCliente", idCliente)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }
}
