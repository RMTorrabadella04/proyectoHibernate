package DAO;

import entities.especies;

public interface animalDAO {

    /**
     *
     * @insert Inserta un nuevo animal en la bbdd
     */
    public void insertarAnimal();

    /**
     *
     * @param especie
     *
     * @return Retorna una lista de animales de la especie indicada
     */
    public void consultarAnimales(especies especie);

    /**
     *
     * @update Actualiza el estado de un animal
     */
    public void actualizarEstadoAnimal();

}
