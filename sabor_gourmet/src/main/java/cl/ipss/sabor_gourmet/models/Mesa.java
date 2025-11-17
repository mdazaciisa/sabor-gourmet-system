package cl.ipss.sabor_gourmet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mesa {
    /*@OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
      private List<Reserva> reservas;
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacidad;
    private String ubicacion;
    private Boolean disponible;
    // Campos sencillos para indicar quién reservó (implementación simple para aprendizaje)
    private Long reservadoPorId;
    private String reservadoPorNombre;

    public Mesa() {}

    public Mesa(int capacidad, String ubicacion, boolean disponible) {
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Long getReservadoPorId() {
        return reservadoPorId;
    }

    public void setReservadoPorId(Long reservadoPorId) {
        this.reservadoPorId = reservadoPorId;
    }

    public String getReservadoPorNombre() {
        return reservadoPorNombre;
    }

    public void setReservadoPorNombre(String reservadoPorNombre) {
        this.reservadoPorNombre = reservadoPorNombre;
    }

}
