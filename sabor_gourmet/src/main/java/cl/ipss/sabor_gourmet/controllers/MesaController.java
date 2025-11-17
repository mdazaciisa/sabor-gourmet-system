package cl.ipss.sabor_gourmet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.services.MesaService;

@Controller
@RequestMapping("/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mesas", mesaService.listarMesas());
        return "lista-mesas";
    }

    @GetMapping("/{id}")
    public String listarMesaId(Model model, @PathVariable Long id) {
        model.addAttribute("mesa", mesaService.listarMesaId(id));
        return "detalle-mesa";
    }

    @GetMapping("/registrar")
    public String registrarMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "registrar-mesa";
    }

    @PostMapping("/agregar")
    public String agregarMesa(Mesa mesa) {
        mesaService.agregarMesa(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(Model model, @PathVariable Long id) {
        model.addAttribute("mesa", mesaService.listarMesaId(id));
        return "editar-mesa";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
        return "redirect:/mesas";
    }

}
