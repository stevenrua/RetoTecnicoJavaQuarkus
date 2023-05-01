package com.panama.banc.repositories.cliente;

import com.panama.banc.entities.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public interface ClienteRepository extends PanacheRepositoryBase<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);
}
