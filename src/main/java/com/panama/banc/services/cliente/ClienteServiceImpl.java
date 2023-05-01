package com.panama.banc.services.cliente;

import com.panama.banc.entities.Cliente;
import com.panama.banc.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteServiceImpl implements IClienteService{

    @Inject
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> findAll() {
        return clienteRepository.listAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public boolean findByIdentificacion(String identificacion) {
        boolean existClient = false;
        Optional<Cliente> cliente = clienteRepository.findByIdentificacion(identificacion);
        if(cliente.isEmpty()) return existClient;
        existClient = true;
        return existClient;
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }
}
