package com.panama.banc.controllers;

import com.panama.banc.controllers.cliente.ClienteController;
import com.panama.banc.entities.Cliente;
import com.panama.banc.repositories.cliente.ClienteRepository;
import com.panama.banc.services.cliente.ClienteServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class ClienteControllerTest {
    @Inject
    private final ClienteRepository clienteRepositoryMock = Mockito.mock(ClienteRepository.class);
    @Inject
    private final ClienteServiceImpl clienteService = new ClienteServiceImpl(clienteRepositoryMock);
    @Inject
    private final ClienteController clienteController = new ClienteController(clienteService);

    @Test
    void testGetListClientes(){
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        clienteList.add(new Cliente(2L, "shara", "f", 37, "1036957015", "Las partidas", "3234676759", "123456", true));
        clienteList.add(new Cliente(3L, "celeste", "f", 8, "1036262115", "Las partidas", "3148973570", "123456", true));

        Mockito.when(clienteRepositoryMock.listAll()).thenReturn(clienteList);

        Response serviceResponse;
        serviceResponse = clienteController.listarClientes();

        List<Cliente> expectedClienteList = new ArrayList<>();
        expectedClienteList.add(new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        expectedClienteList.add(new Cliente(2L, "shara", "f", 37, "1036957015", "Las partidas", "3234676759", "123456", true));
        expectedClienteList.add(new Cliente(3L, "celeste", "f", 8, "1036262115", "Las partidas", "3148973570", "123456", true));

        Assertions.assertEquals(HttpStatus.SC_OK, serviceResponse.getStatus());
        Assertions.assertEquals(expectedClienteList, serviceResponse.getEntity());
    }
    @Test
    void testGetListClientesVoid(){
        List<Cliente> clienteList = new ArrayList<>();

        Mockito.when(clienteRepositoryMock.listAll()).thenReturn(clienteList);

        Response responseService;
        responseService = clienteController.listarClientes();

        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, responseService.getStatus());

    }

    @Test
    void testGetClienteById(){
        Cliente cliente = new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true);
        Mockito.when(clienteRepositoryMock.findById(1L)).thenReturn(cliente);

        Response serviceResponse;
        serviceResponse = clienteController.buscarClienteById(1L);

        Cliente expectedCliente = new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true);

        Assertions.assertEquals(HttpStatus.SC_OK, serviceResponse.getStatus());
        Assertions.assertEquals(expectedCliente, serviceResponse.getEntity());
    }
}
