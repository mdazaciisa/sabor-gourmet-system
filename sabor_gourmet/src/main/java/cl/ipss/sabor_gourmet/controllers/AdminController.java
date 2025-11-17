package cl.ipss.sabor_gourmet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ipss.sabor_gourmet.services.ClienteService;
import cl.ipss.sabor_gourmet.services.MesaService;
import cl.ipss.sabor_gourmet.services.ReservaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MesaService mesaService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String dashboardAdmin(Model model) {
        model.addAttribute("mesas", mesaService.listarMesas());
        model.addAttribute("clientes", clienteService.listarClientes());
        return "admin-dashboard";
    }

    @GetMapping("/api/mesas")
    @ResponseBody
    public List<cl.ipss.sabor_gourmet.models.Mesa> apiListarMesas() {
        return mesaService.listarMesas();
    }

    @GetMapping("/api/mesas/{id}/disponible")
    @ResponseBody
    public Map<String, Object> apiDisponible(@PathVariable Long id) {
        cl.ipss.sabor_gourmet.models.Mesa m = mesaService.listarMesaId(id);
        Map<String, Object> resp = new HashMap<>();
        boolean disponible = m != null && Boolean.TRUE.equals(m.getDisponible());
        resp.put("disponible", disponible);
        return resp;
    }

    @PostMapping("/api/mesas/{id}/reservar")
    @ResponseBody
    public ResponseEntity<?> reservarMesa(@PathVariable Long id, @RequestParam(required = false) Long clienteId) {
        try {
            reservaService.crearReserva(id, clienteId);
            cl.ipss.sabor_gourmet.models.Mesa m = mesaService.listarMesaId(id);
            return ResponseEntity.ok(m);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al reservar");
        }
    }

    @PostMapping("/api/mesas/{id}/liberar")
    @ResponseBody
    public ResponseEntity<?> liberarMesa(@PathVariable Long id) {
        try {
            java.util.Optional<cl.ipss.sabor_gourmet.models.Reserva> cancelled = reservaService.cancelarReservaPorMesa(id);
            cl.ipss.sabor_gourmet.models.Mesa m = mesaService.listarMesaId(id);
            if (m == null) return ResponseEntity.notFound().build();
            
            if (cancelled.isEmpty()) {
                m.setDisponible(true);
                m.setReservadoPorId(null);
                m.setReservadoPorNombre(null);
                mesaService.actualizarMesa(m);
            }
            return ResponseEntity.ok(m);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al liberar mesa");
        }
    }

    @GetMapping("/api/mesas/{id}/reserva")
    @ResponseBody
    public Map<String, Object> apiVerReserva(@PathVariable Long id) {
        cl.ipss.sabor_gourmet.models.Mesa m = mesaService.listarMesaId(id);
        Map<String, Object> resp = new HashMap<>();
        if (m == null) {
            resp.put("found", false);
            return resp;
        }
        resp.put("found", true);
        resp.put("disponible", Boolean.TRUE.equals(m.getDisponible()));
        resp.put("reservadoPorId", m.getReservadoPorId());
        resp.put("reservadoPorNombre", m.getReservadoPorNombre());
        return resp;
    }

}
