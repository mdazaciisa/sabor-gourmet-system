package cl.ipss.sabor_gourmet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.repositories.MesaRepository;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

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
        mesaRepository.deleteById(id);
    }
}
