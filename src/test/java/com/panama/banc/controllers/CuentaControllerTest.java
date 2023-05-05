package com.panama.banc.controllers;

import com.panama.banc.controllers.cliente.ClienteController;
import com.panama.banc.controllers.cuenta.CuentaController;
import com.panama.banc.entities.Cliente;
import com.panama.banc.entities.Cuenta;
import com.panama.banc.mensaje.Mensaje;
import com.panama.banc.repositories.cuenta.CuentaRepository;
import com.panama.banc.services.cuenta.CuentaServiceImpl;
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
public class CuentaControllerTest {
    @Inject
    private final CuentaRepository cuentaRepositoryMock = Mockito.mock(CuentaRepository.class);
    @Inject
    private final CuentaServiceImpl cuentaService = new CuentaServiceImpl(cuentaRepositoryMock);
    @Inject
    private final CuentaController cuentaController = new CuentaController(cuentaService);

    @Test
    void testGetListCuentas(){
        List<Cuenta> cuentaList = new ArrayList<>();
        cuentaList.add(new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));
        cuentaList.add(new Cuenta(2L, 123456, "Ahorros", 2000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));
        cuentaList.add(new Cuenta(3L, 1234567, "Ahorros", 3000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));

        Mockito.when(cuentaRepositoryMock.listAll()).thenReturn(cuentaList);

        Response serviceResponse;
        serviceResponse = cuentaController.listarCuentas();

        List<Cuenta> expectedCuentaList = new ArrayList<>();
        expectedCuentaList.add(new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));
        expectedCuentaList.add(new Cuenta(2L, 123456, "Ahorros", 2000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));
        expectedCuentaList.add(new Cuenta(3L, 1234567, "Ahorros", 3000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true)));

        Assertions.assertEquals(HttpStatus.SC_OK, serviceResponse.getStatus());
        Assertions.assertEquals(expectedCuentaList, serviceResponse.getEntity());
    }

    @Test
    void testGetListCuentasVoid(){
        List<Cuenta> cuentaList = new ArrayList<>();
        Mockito.when(cuentaRepositoryMock.listAll()).thenReturn(cuentaList);

        Response serviceResponse;
        serviceResponse = cuentaController.listarCuentas();

        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, serviceResponse.getStatus());
    }

    @Test
    void testGetClienteById(){
        Cuenta cuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        Mockito.when(cuentaRepositoryMock.findById(1L)).thenReturn(cuenta);

        Response serviceResponse;
        serviceResponse = cuentaController.buscarCuentaById(1L);

        Cuenta expectedCuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));

        Assertions.assertEquals(HttpStatus.SC_OK, serviceResponse.getStatus());
        Assertions.assertEquals(expectedCuenta, serviceResponse.getEntity());
    }

    @Test
    void testGetCuentaByIdNotFound(){
        Cuenta cuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        Mockito.when(cuentaRepositoryMock.findById(1L)).thenReturn(null);

        Response serviceResponse;
        serviceResponse = cuentaController.buscarCuentaById(1L);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("Cuenta con ese Id no encontrado");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, serviceResponse.getStatus());
        Assertions.assertEquals(mensaje, serviceResponse.getEntity());
    }
    @Test
    void testNewCliente(){
        Cuenta newCuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        Mockito.doNothing().when(cuentaRepositoryMock).persist(newCuenta);

        Response serviceResponse;
        serviceResponse = cuentaController.crearCuenta(newCuenta);

        Cuenta expectedCuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));

        Assertions.assertEquals(HttpStatus.SC_CREATED, serviceResponse.getStatus());
        Assertions.assertEquals(expectedCuenta, serviceResponse.getEntity());
    }

    @Test
    void testUpdateCuenta(){
        Cuenta cuentaById = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        Cuenta updatedCuenta = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven rua jaramillo", "m", 29, "1036945145", "Las partidas", "3148973570", "123456", true));
        Mockito.when(cuentaRepositoryMock.findById(cuentaById.getId())).thenReturn(cuentaById);
        Mockito.doNothing().when(cuentaRepositoryMock).persist(updatedCuenta);

        Response serviceResponse;
        serviceResponse = cuentaController.actualizarCuenta(1L, updatedCuenta);

        Cuenta expectedCliente = new Cuenta(1L, 12345, "Ahorros", 1000000, true,
                new Cliente(1L, "steven rua jaramillo", "m", 29, "1036945145", "Las partidas", "3148973570", "123456", true));

        Assertions.assertEquals(HttpStatus.SC_CREATED, serviceResponse.getStatus());
        Assertions.assertEquals(expectedCliente, serviceResponse.getEntity());
    }
    @Test
    void testUpdateCuentaNoFound(){
        Mockito.when(cuentaRepositoryMock.findById(2L)).thenReturn(null);
        Cuenta updatedCuenta = new Cuenta(2L, 12345, "Ahorros", 1000000, true,
                new Cliente(2L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));

        Response serviceResponse;
        serviceResponse = cuentaController.actualizarCuenta(2L, updatedCuenta);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("Cuenta con ese Id no encontrada");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, serviceResponse.getStatus());
        Assertions.assertEquals(mensaje, serviceResponse.getEntity());
    }
    @Test
    void testDeleteCliente(){
        Cuenta cuentaById = new Cuenta(2L, 12345, "Ahorros", 1000000, true,
                new Cliente(2L, "steven", "m", 30, "1036945145", "Las partidas", "3148973570", "123456", true));
        Mockito.when(cuentaRepositoryMock.findById(2L)).thenReturn(cuentaById);

        Mockito.when(cuentaRepositoryMock.deleteById(cuentaById.getId())).thenReturn(true);

        Response serviceResponse;
        serviceResponse = cuentaController.deleteCuenta(2L);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("Cuenta eliminada");

        Assertions.assertEquals(HttpStatus.SC_OK, serviceResponse.getStatus());
        Assertions.assertEquals(mensaje, serviceResponse.getEntity());
    }

    @Test
    void testDeleteClienteNoFound(){
        Mockito.when(cuentaRepositoryMock.findById(1L)).thenReturn(null);

        Response serviceResponse;
        serviceResponse = cuentaController.deleteCuenta(1L);

        Mensaje mensaje = new Mensaje();
        mensaje.mensajeNotFound("Cuenta con ese Id no encontrada");

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, serviceResponse.getStatus());
        Assertions.assertEquals(mensaje, serviceResponse.getEntity());
    }
}
