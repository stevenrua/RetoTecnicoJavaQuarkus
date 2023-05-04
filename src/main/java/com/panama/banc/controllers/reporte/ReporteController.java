package com.panama.banc.controllers.reporte;

import com.panama.banc.entities.Reporte;
import com.panama.banc.mensaje.Mensaje;
import com.panama.banc.services.reporte.IReporteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/reporte")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteController {
    @Inject
    private IReporteService reporteService;
    @GET
    public Response generarReporte(@QueryParam("fechaInicio") String fechaInicio,
                                   @QueryParam("fechaFin") String fechaFin,
                                   @QueryParam("idCliente") Long idCliente) {
        try{
            LocalDate fechaInicio1 = LocalDate.parse(fechaInicio);
            LocalDate fechaFin1 = LocalDate.parse(fechaFin);
            List<Reporte> reporte = reporteService.reporte(fechaInicio1, fechaFin1, idCliente);
            return Response.ok(reporte).build();
        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }

    }
}
