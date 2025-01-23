package entities;

import DAO.animalDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/*
 *
 * Algunos de estos test solo se pueden hacer 1 vez a no ser que cambies los datos de la base de datos para que no exista un animal con el mismo nombre
 *
 * Por esa razon pongo nombres raros y largos para que de casualidad no lo pongais en la base de datos a la hora de añadir un animal
 *
 */
class animalesTest {

    /**
     * Este test inserta un animal en la base de datos y verifica que se haya insertado correctamente
     */

    @Test
    void insertarAnimalTest() {
        String input = "PeperracoElGatoDiosDelosAlpes\n2\n5\nGato perdido\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        animalDAOImpl animalDAO = new animalDAOImpl();

        animalDAO.insertarAnimal();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        animales animalRecuperado = session.createQuery("FROM animales WHERE nombre = :nombre", animales.class)
                .setParameter("nombre", "PeperracoElGatoDiosDelosAlpes")
                .uniqueResult();

        assertNotNull(animalRecuperado);
        assertEquals("PeperracoElGatoDiosDelosAlpes", animalRecuperado.getNombre());
        assertEquals(especies.Gatos, animalRecuperado.getEspecie());
        assertEquals(5, animalRecuperado.getEdad());
        assertEquals("Gato perdido", animalRecuperado.getDescripcion_perdida());
        assertEquals(estado_animal.RecienAbandonado, animalRecuperado.getEstado_animal());

        session.close();
    }

    /**
     * Este test comprueba que se hayan recuperado todos los animales de la especie "Gatos" correctamente
     */
    @Test
    void consultarAnimalesTest() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        animales animal = new animales(null, "MicharracaGatoEspecialDeTorreblanca", especies.Gatos, 3, "michi perdido", estado_animal.RecienAbandonado);
        animales animal2 = new animales(null, "PedritoElGatoTacticoSargentoMayor", especies.Gatos, 3, "michi perdido", estado_animal.RecienAbandonado);

        session.beginTransaction();

        // Guardar los animales
        session.save(animal);
        session.save(animal2);

        animalDAOImpl animalDAO = new animalDAOImpl();
        List<animales> gatos = animalDAO.consultarAnimales(especies.Gatos);

        assertNotNull(gatos);

        for (animales gato : gatos) {
            assertEquals(especies.Gatos, gato.getEspecie());
        }
    }


    /**
     *
     * Este test comprueba que se haya actualizado el estado de un animal correctamente
     *
     * Hay 2 sesiones por que se raya y al actualizar un valor pilla el de la sesion anterior antes de acutualizarlo, por lo que debes crear otra con los valores correctos
     *
     */
    @Test
    void actualizarEstadoAnimalTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        animales animalico = new animales(null, "ErBerengarioLeyendaVivienteInmuneAlVeneno", especies.Gatos, 3, "michi perdido", estado_animal.RecienAbandonado);


        session.beginTransaction();
        session.save(animalico);
        session.getTransaction().commit();

        animalDAOImpl animalDAO = new animalDAOImpl();
        List<animales> gatos = animalDAO.consultarAnimales(especies.Gatos);

        assertFalse(gatos.isEmpty(), "La lista de gatos no debe estar vacía");

        animales animal = gatos.get(0);
        System.out.println("Animal antes de la actualización: " + animal);


        String input = animal.getId() + "\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        animalDAO.actualizarEstadoAnimal();

        session.close();

        /**
         *  Segunda sesion para pillar los valores actualizados y que no se raye
         */

        Session session2 = sessionFactory.openSession();

        animales animalActualizado = session2.get(animales.class, animal.getId()); // Recuperar el animal actualizado

        assertEquals(estado_animal.ProximamenteEnAcogida, animalActualizado.getEstado_animal());

        session2.close();
    }



}