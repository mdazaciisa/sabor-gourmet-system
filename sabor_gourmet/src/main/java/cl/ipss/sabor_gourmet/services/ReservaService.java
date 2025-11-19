package cl.ipss.sabor_gourmet.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.models.Reserva;
import cl.ipss.sabor_gourmet.repositories.ClienteRepository;
import cl.ipss.sabor_gourmet.repositories.MesaRepository;
import cl.ipss.sabor_gourmet.repositories.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public static final String ESTADO_ACTIVE = "ACTIVE";
    public static final String ESTADO_CANCELLED = "CANCELLED";

    @Transactional
    public Reserva crearReserva(Long mesaId, Long clienteId) {
        Mesa m = mesaRepository.findById(mesaId).orElse(null);
        if (m == null) throw new IllegalArgumentException("Mesa no encontrada");
        if (Boolean.FALSE.equals(m.getDisponible())) throw new IllegalStateException("Mesa ocupada");

        Cliente c = null;
        if (clienteId != null) c = clienteRepository.findById(clienteId).orElse(null);

        Reserva r = new Reserva();
        r.setMesa(m);
        r.setCliente(c);
        r.setFechaHora(LocalDateTime.now());
        r.setEstado(ESTADO_ACTIVE);

        Reserva saved = reservaRepository.save(r);

        // actualizar estado de la mesa
        m.setDisponible(false);
        if (c != null) {
            m.setReservadoPorId(c.getId());
            m.setReservadoPorNombre(c.getNombre() + " " + c.getApellido());
        }
        mesaRepository.save(m);

        return saved;
    }

    @Transactional
    public Optional<Reserva> cancelarReservaPorMesa(Long mesaId) {
        Optional<Reserva> opt = reservaRepository.findFirstByMesaIdAndEstado(mesaId, ESTADO_ACTIVE);
        if (opt.isPresent()) {
            Reserva r = opt.get();
            r.setEstado(ESTADO_CANCELLED);
            reservaRepository.save(r);

            Mesa m = r.getMesa();
            m.setDisponible(true);
            m.setReservadoPorId(null);
            m.setReservadoPorNombre(null);
            mesaRepository.save(m);
        }
        return opt;
    }

}
