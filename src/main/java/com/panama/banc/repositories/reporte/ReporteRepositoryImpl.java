package com.panama.banc.repositories.reporte;

import com.panama.banc.entities.Reporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReporteRepositoryImpl implements ReporteRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Reporte> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        String query = "SELECT " +
                "m.date as Fecha, " +
                "cl.nombre as Cliente, " +
                "c.numerocuenta as NumeroCuenta, " +
                "c.tipocuenta as Tipo, " +
                "m.saldo as SaldoInicial, " +
                "c.estado as Estado, " +
                "m.valor as Movimiento, " +
                "c.saldoinicial as SaldoDisponible, " +
                "m.tipomovimiento "
                + "FROM movimientos m "
                + "JOIN cuenta c ON m.cuenta_id = c.id "
                + "JOIN cliente cl ON c.cliente_id = cl.id "
                + "WHERE cl.id = :idCliente AND m.date BETWEEN :fechaInicio AND :fechaFin ";

        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("idCliente", idCliente);
        nativeQuery.setParameter("fechaInicio", fechaInicio);
        nativeQuery.setParameter("fechaFin", fechaFin);

        List<Object[]> resultList = nativeQuery.getResultList();
        List<Reporte> reporteList = new ArrayList<>();


        for (Object[] result : resultList) {
            double isRetiro = (result[8].equals("Retiro")? (double) result[6] * -1 : (double) result[6]);
            Reporte reporte = new Reporte();
            reporte.setFecha(fechaFin);
            reporte.setCliente((String) result[1]);
            reporte.setNumeroCuenta((int) result[2]);
            reporte.setTipo((String) result[3]);
            reporte.setSaldoInicial((Double) result[4]);
            reporte.setEstado((Boolean) result[5]);
            reporte.setMovimiento(isRetiro);
            reporte.setSaldoDisponible((Double) result[7]);
            reporteList.add(reporte);
        }

        return reporteList;

    }
}
