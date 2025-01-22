package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "adopcion")
public class adopcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_adopcion;

    @ManyToOne
    @JoinColumn(name = "id_familia", referencedColumnName = "id", insertable = true, updatable = false)
    private familia id_familia;

    @ManyToOne
    @JoinColumn(name = "id_animal", referencedColumnName = "id", insertable = true, updatable = false)
    private animales id_animal;

    public adopcion() { }

    public adopcion(Integer id_adopcion, familia id_familia, animales id_animal) {
        this.id_adopcion = id_adopcion;
        this.id_familia = id_familia;
        this.id_animal = id_animal;
    }

    public Integer getId_adopcion() { return id_adopcion; }

    public familia getId_familia() { return id_familia; }

    public animales getId_animal() { return id_animal; }


}
