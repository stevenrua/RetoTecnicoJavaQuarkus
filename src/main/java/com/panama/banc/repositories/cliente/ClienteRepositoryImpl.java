package com.panama.banc.repositories.cliente;

import com.panama.banc.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

import java.util.Optional;

@ApplicationScoped
@Default
public class ClienteRepositoryImpl implements ClienteRepository {
    @Override
    public Optional<Cliente> findByIdentificacion(String identificacion) {
        return find("identificacion", identificacion).firstResultOptional();
    }
}
