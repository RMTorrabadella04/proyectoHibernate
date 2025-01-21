package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class animalesTest {

    @Test
    void creacionTablasYAnyadirAnimalesTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        animales animal = new animales(null, "michi", especies.Gatos, 3, "michi perdido", estado_animal.RecienAbandonado);
        animales animal2 = new animales(null, "pedro", especies.Serpientes, 5, "Serpiente perdida", estado_animal.TiempoEnElRefugio);
        animales animal3 = new animales(null, "ernesto", especies.CerdosVietnamitas, 2, "Cerdo perdido", estado_animal.RecienAbandonado);

        session.beginTransaction();

        session.save(animal);
        session.save(animal2);
        session.save(animal3);

        session.getTransaction().commit();
        session.close();
    }


}