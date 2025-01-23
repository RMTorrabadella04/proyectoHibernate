package entities;

import DAO.animalDAOImpl;
import DAO.familiaDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 *
 * Algunos de estos test solo se pueden hacer 1 vez a no ser que cambies los datos de la base de datos para que no exista una familia con el mismo nombre y no se raye a la hora de comprobar
 *
 * Por esa razon pongo nombres raros y largos para que de casualidad no lo pongais en la base de datos a la hora de añadir una familia
 *
 */

class familiaTest {

    /**
     * Este test inserta una familia en la base de datos y verifica que se haya insertado correctamente
     */

    @Test
    public void insertarFamiliaTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String input = "GonzalitoGonzalesDeLosGonzos\n40\nMadrid\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        familiaDAOImpl familia = new familiaDAOImpl();

        familia.insertarFamilia();

        session.beginTransaction();

        familia familiaRecuperada = session.createQuery("FROM familia WHERE nombre = :nombre", familia.class)
                .setParameter("nombre", "GonzalitoGonzalesDeLosGonzos")
                .uniqueResult();

        assertNotNull(familiaRecuperada);
        assertEquals("GonzalitoGonzalesDeLosGonzos", familiaRecuperada.getNombre());
        assertEquals(40, familiaRecuperada.getEdad());
        assertEquals("Madrid", familiaRecuperada.getCiudad());

        session.getTransaction().commit();
        session.close();
    }


    /**
     *
     * Testea la realizacion de una adopcion
     *
     */
    @Test
    public void realizarAdopcionTest() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        familia familiaTest = new familia(null, "Familia Test", 30, "Ciudad Test");
        animales animalTest = new animales(null, "Animal Test", especies.Gatos, 3, "Perdido", estado_animal.RecienAbandonado);

        session.save(familiaTest);
        session.save(animalTest);

        session.getTransaction().commit();

        familiaDAOImpl familiaDAO = new familiaDAOImpl();

        String input= familiaTest.getId() + "\n" + animalTest.getId()+"\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        familiaDAO.realizarAdopcion();

        session = HibernateUtil.getSessionFactory().openSession();
        Query<adopcion> query = session.createQuery("FROM adopcion WHERE id_familia.id = :idFamilia AND id_animal.id = :idAnimal");
        query.setParameter("idFamilia", familiaTest.getId());
        query.setParameter("idAnimal", animalTest.getId());
        List<adopcion> adopciones = query.getResultList();

        assertEquals(false,adopciones.isEmpty());

        session.close();

    }

}