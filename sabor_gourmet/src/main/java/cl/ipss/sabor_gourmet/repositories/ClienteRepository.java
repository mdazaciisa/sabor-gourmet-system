package cl.ipss.sabor_gourmet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.sabor_gourmet.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
