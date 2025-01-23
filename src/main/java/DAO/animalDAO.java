package DAO;

import entities.animales;
import entities.especies;

import java.util.List;

public interface animalDAO {

    /**
     *
     * @insert Inserta un nuevo animal en la bbdd
     */
    public void insertarAnimal();

    /**
     * @param especie
     * @return Retorna una lista de animales de la especie indicada
     */
    public List<animales> consultarAnimales(especies especie);

    /**
     *
     * @update Actualiza el estado de un animal
     */
    public void actualizarEstadoAnimal();

}
