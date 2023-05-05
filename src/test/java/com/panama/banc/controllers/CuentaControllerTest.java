package com.panama.banc.controllers;

import com.panama.banc.controllers.cliente.ClienteController;
import com.panama.banc.controllers.cuenta.CuentaController;
import com.panama.banc.entities.Cliente;
import com.panama.banc.entities.Cuenta;
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
}
