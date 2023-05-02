package com.panama.banc.services.cliente;

import com.panama.banc.entities.Cliente;
import com.panama.banc.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public Cliente save(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteById = clienteRepository.findById(id);
        clienteById.setNombre(cliente.getNombre());
        clienteById.setGenero(cliente.getGenero());
        clienteById.setEdad(cliente.getEdad());
        clienteById.setDireccion(cliente.getDireccion());
        clienteById.setTelefono(cliente.getTelefono());
        clienteById.setContrasena(cliente.getContrasena());
        clienteById.setEstado(cliente.isEstado());
        clienteById.setIdentificacion(cliente.getIdentificacion());
        clienteRepository.persist(clienteById);
        return clienteById;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
