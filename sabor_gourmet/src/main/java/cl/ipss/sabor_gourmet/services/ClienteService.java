package cl.ipss.sabor_gourmet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.repositories.ClienteRepository;
import cl.ipss.sabor_gourmet.repositories.ReservaRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente listarClienteId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        // Evitar eliminar un cliente que está referenciado por una reserva
        if (reservaRepository.existsByClienteId(id)) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene reservas asociadas");
        }

        clienteRepository.deleteById(id);
    }

}
