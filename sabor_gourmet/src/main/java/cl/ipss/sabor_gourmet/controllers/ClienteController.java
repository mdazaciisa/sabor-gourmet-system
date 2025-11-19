package cl.ipss.sabor_gourmet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "lista-clientes";
    }

    @GetMapping("/{id}")
    public String listarClienteId(Model model, @PathVariable Long id) {
        model.addAttribute("cliente", clienteService.listarClienteId(id));
        return "detalle-cliente";
    }

    @GetMapping("/registrar")
    public String registrarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registrar-cliente";
    }
    
    @PostMapping("/agregar")
    public String agregarCliente(Cliente cliente) {
        clienteService.agregarCliente(cliente);
        return "redirect:/clientes";
    }

        @GetMapping("/editar/{id}")
    public String editarCliente(Model model, @PathVariable Long id) {
        model.addAttribute("cliente", clienteService.listarClienteId(id));
        return "editar-cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttrs, HttpServletRequest request) {
        try {
            clienteService.eliminarCliente(id);
            redirectAttrs.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } catch (IllegalStateException ex) {
            redirectAttrs.addFlashAttribute("error", ex.getMessage());
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/clientes");
    }
    

}
