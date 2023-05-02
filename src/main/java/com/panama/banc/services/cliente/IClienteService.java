package com.panama.banc.services.cliente;

import com.panama.banc.entities.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    //boolean findByIdentificacion(String identificacion);
    Cliente save(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    void delete(Long id);
}
