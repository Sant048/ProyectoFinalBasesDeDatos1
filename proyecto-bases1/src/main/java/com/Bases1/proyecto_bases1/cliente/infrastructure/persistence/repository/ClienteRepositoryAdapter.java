package com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final JpaClienteRepository repository;

    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Cliente> obtenerClientesSinVehiculo() {
        return List.of();
    }

    @Override
    public Cliente obtenerClienteMasActivo() {
        return null;
    }
}