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
        Cliente cliente = clienteService.findById(id);
        if (cliente == null)
            return Response.noContent().build();
        return Response.ok(cliente).build();
    }

    @POST
    public Response crearCliente(Cliente cliente) {
        boolean existClient = clienteService.findByIdentificacion(cliente.getIdentificacion());
        if(!existClient){
            Cliente createdCliente = clienteService.save(cliente);
            return Response.ok(createdCliente).build();
        }
        String message = "El cliente con esa identificación ya esta almacenado";
        Mensaje responseMessage = new Mensaje(message);
        return Response.ok(responseMessage).status(Response.Status.BAD_REQUEST).build();
    }
}
