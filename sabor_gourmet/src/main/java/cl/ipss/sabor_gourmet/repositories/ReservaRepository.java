package cl.ipss.sabor_gourmet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.sabor_gourmet.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findFirstByMesaIdAndEstado(Long mesaId, String estado);
    boolean existsByClienteId(Long clienteId);
    boolean existsByMesaId(Long mesaId);
    boolean existsByMesaIdAndEstado(Long mesaId, String estado);
    Optional<Reserva> findFirstByMesaId(Long mesaId);
    java.util.List<Reserva> findByMesaIdAndEstadoNot(Long mesaId, String estado);
}
