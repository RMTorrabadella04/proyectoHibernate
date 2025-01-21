package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "animales")
public class animales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private especies especie;

    private int edad;

    private String descripcion_perdida;

    @Enumerated(EnumType.STRING)
    private estado_animal estado_animal;

    public animales() {}

    public animales(Integer id, String nombre, especies especie, int edad, String descripcion_perdida, estado_animal estado_animal) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcion_perdida = descripcion_perdida;
        this.estado_animal = estado_animal;
    }

    public Integer getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public especies getEspecie() { return especie; }

    public void setEspecie(especies especie) { this.especie = especie; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getDescripcion_perdida() { return descripcion_perdida; }

    public void setDescripcion_perdida(String descripcion_perdida) { this.descripcion_perdida = descripcion_perdida; }

    public estado_animal getEstado_animal() { return estado_animal; }

    public void setEstado_animal(estado_animal estado_animal) { this.estado_animal = estado_animal; }


    @Override
    public String toString() {
        return "animales{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", descripcion_perdida='" + descripcion_perdida + '\'' +
                ", estado_animal='" + estado_animal + '\'' +
                '}';
    }
}
