package com.panama.banc.controllers.cliente;

import com.panama.banc.entities.Cliente;
import com.panama.banc.mensaje.Mensaje;
import com.panama.banc.services.cliente.IClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {
    @Inject
    private IClienteService clienteService;

    @GET
    public Response listarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty())
            return Response.noContent().build();
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarClienteById(@PathParam("id") Long id) {
        String message = "Cliente con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Cliente cliente = clienteService.findById(id);
            if (cliente == null)
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            return Response.ok(cliente).build();
        }catch (Exception e){
            responseMessage.mensajeNotFound(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    public Response crearCliente(Cliente cliente) {
        try{
            Cliente createdCliente = clienteService.save(cliente);
            return Response.ok(createdCliente).build();
        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
   public Response actualizarCliente(@PathParam("id") Long id, Cliente cliente){
        try{
            Cliente clienteById = clienteService.findById(id);
            if(clienteById == null){
                String message = "Cliente con ese Id no encontrado";
                Mensaje responseMessage = new Mensaje();
                responseMessage.mensajeNotFound(message);
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            clienteService.update(id, cliente);
            return Response.status(Response.Status.CREATED).entity(clienteById).build();

        }catch (Exception e){
            Mensaje responseMessage = new Mensaje();
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long id){
        String message = "Cliente con ese Id no encontrado";
        Mensaje responseMessage = new Mensaje();
        responseMessage.mensajeNotFound(message);
        try{
            Cliente clienteById = clienteService.findById(id);
            if(clienteById == null){
                return Response.ok(responseMessage).status(Response.Status.NOT_FOUND).build();
            }
            clienteService.delete(id);
            message = "Cliente eliminado";
            responseMessage.mensajeNotFound(message);
            return Response.ok(responseMessage).build();
        }catch (Exception e){
            responseMessage.mensajeErrorBD(e.getCause().getCause().getCause().getMessage());
            return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
        }




    }
}
