package com.panama.banc.controllers.cuenta;

import com.panama.banc.entities.Cliente;
import com.panama.banc.entities.Cuenta;
import com.panama.banc.mensaje.Mensaje;
import com.panama.banc.services.cuenta.ICuentaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cuenta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CuentaController {
    @Inject
    private ICuentaService cuentaService;

    @GET
    public Response listarCuentas() {
        List<Cuenta> cuentas = cuentaService.findAll();
        if (cuentas.isEmpty())
            return Response.noContent().build();
        return Response.ok(cuentas).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCuentaById(@PathParam("id") Long id) {
        String message = "Cuenta con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Cuenta cuenta = cuentaService.findById(id);
            if (cuenta == null)
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            return Response.ok(cuenta).build();
        }catch (Exception e){
            responseMessage.mensajeNotFound(e.getMessage());
            return Response.ok(responseMessage).status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    public Response crearCuenta(Cuenta cuenta) {
        try{
            Cuenta createdCuenta = cuentaService.save(cuenta);
            return Response.ok(createdCuenta).build();
        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarCuenta(@PathParam("id") Long id, Cuenta cuenta){
        try{
            Cuenta cuentaById = cuentaService.findById(id);
            if(cuentaById == null){
                String message = "Cuenta con ese Id no encontrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(message);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            cuentaService.update(id, cuenta);
            return Response.status(Response.Status.CREATED).entity(cuenta).build();

        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCuenta(@PathParam("id") Long id){
        String message = "Cuenta con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Cuenta cuentaById = cuentaService.findById(id);
            if(cuentaById == null){
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            cuentaService.delete(id);
            message = "Cuenta eliminada";
            responseMessage.mensajeNotFound(message);
            return Response.ok(responseMessage).build();
        }catch (Exception e){
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
