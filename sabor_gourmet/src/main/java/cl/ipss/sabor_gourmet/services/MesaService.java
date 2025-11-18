package cl.ipss.sabor_gourmet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.repositories.MesaRepository;
import cl.ipss.sabor_gourmet.repositories.ReservaRepository;
import cl.ipss.sabor_gourmet.models.Reserva;
import java.util.Optional;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa listarMesaId(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    public Mesa agregarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa actualizarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void eliminarMesa(Long id) {
        // Evitar eliminar una mesa que tiene una reserva activa
        if (reservaRepository.existsByMesaIdAndEstado(id, cl.ipss.sabor_gourmet.services.ReservaService.ESTADO_ACTIVE)) {
            // Intentar obtener el nombre del cliente asociado para el mensaje de error
            Optional<Reserva> opt = reservaRepository.findFirstByMesaIdAndEstado(id, cl.ipss.sabor_gourmet.services.ReservaService.ESTADO_ACTIVE);
            String clienteNombre = opt.flatMap(r -> Optional.ofNullable(r.getCliente()))
                    .map(c -> c.getNombre()).orElse("un cliente");
            throw new IllegalStateException("No se puede eliminar la mesa ya que tiene asociada la reserva de " + clienteNombre);
        }

        // Eliminar reservas no activas
        java.util.List<Reserva> otras = reservaRepository.findByMesaIdAndEstadoNot(id, cl.ipss.sabor_gourmet.services.ReservaService.ESTADO_ACTIVE);
        if (otras != null && !otras.isEmpty()) {
            reservaRepository.deleteAll(otras);
        }

        try {
            mesaRepository.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            // Si aún hay referencias en la BD, lanzar excepción
            throw new IllegalStateException("No se puede eliminar la mesa porque todavía tiene reservas en la base de datos.");
        }
    }
}
