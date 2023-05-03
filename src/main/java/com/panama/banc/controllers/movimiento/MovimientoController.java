package com.panama.banc.controllers.movimiento;

import com.panama.banc.entities.Cuenta;
import com.panama.banc.entities.Movimientos;
import com.panama.banc.entities.Reporte;
import com.panama.banc.mensaje.Mensaje;
import com.panama.banc.services.movimiento.IMovimientoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Path("/movimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovimientoController {
    @Inject
    private IMovimientoService movimientoService;

    @GET
    public Response listarMovimientos() {
        List<Movimientos> movimientos = movimientoService.findAll();
        if (movimientos.isEmpty())
            return Response.noContent().build();
        return Response.ok(movimientos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarMovimientoById(@PathParam("id") Long id) {
        String message = "Movimiento con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Movimientos movimiento = movimientoService.findById(id);
            if (movimiento == null)
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            return Response.ok(movimiento).build();
        }catch (Exception e){
            responseMessage.mensajeNotFound(e.getMessage());
            return Response.ok(responseMessage).status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    public Response crearMovimiento(Movimientos movimiento) {
        try{
            Movimientos createdMovimiento = movimientoService.save(movimiento);
            return Response.ok(createdMovimiento).build();
        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/reporte")
    public Response generarReporte(@QueryParam("fechaInicio") String fechaInicio,
                                   @QueryParam("fechaFin") String fechaFin,
                                   @QueryParam("idCliente") Long idCliente) {
        LocalDate fechaInicio1 = LocalDate.parse(fechaInicio);
        LocalDate fechaFin1 = LocalDate.parse(fechaFin);
        List<Reporte> cosa = movimientoService.reporte(fechaInicio1, fechaFin1, idCliente);
        return Response.ok(cosa).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMovimiento(@PathParam("id") Long id, Movimientos movimiento){
        try{
            Movimientos movimientoById = movimientoService.findById(id);
            if(movimientoById == null){
                String message = "Movimiento con ese Id no encontrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(message);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            movimientoService.update(id, movimiento);
            return Response.status(Response.Status.CREATED).entity(movimiento).build();

        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovimiento(@PathParam("id") Long id){
        String message = "Movimiento con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Movimientos movimientoById = movimientoService.findById(id);
            if(movimientoById == null){
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            movimientoService.delete(id);
            message = "Movimiento eliminado";
            responseMessage.mensajeNotFound(message);
            return Response.ok(responseMessage).build();
        }catch (Exception e){
            responseMessage.mensajeErrorBD(e.getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
