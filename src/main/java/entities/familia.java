package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "familia")
public class familia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    private int edad;

    private String ciudad;

    public familia() {}

    public familia(Integer id, String nombre, int edad, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public Integer getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    @Override
    public String toString() {
        return "familia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
