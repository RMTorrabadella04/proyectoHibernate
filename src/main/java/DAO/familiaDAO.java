package DAO;

import entities.animales;

public interface familiaDAO {

    /**
     * @insert Inserta una nueva familia en la bbdd
     */
    public void insertarFamilia();

    /**
     * @return Retorna una lista de familias con animales acogidos
     */
    public void consultarFamiliasConAnimalesAcogidos();

    /**
     * @return Retorna una lista de todas las familias
     */
    public void verFamilias();

    /**
     * @insert Realiza una nueva adopcion
     */
    public void realizarAdopcion();

}
