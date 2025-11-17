package cl.ipss.sabor_gourmet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.sabor_gourmet.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findFirstByMesaIdAndEstado(Long mesaId, String estado);
}
