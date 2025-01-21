package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class familiaTest {

    @Test
    void anyadirFamiliasTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        familia familia1 = new familia(null, "Alvaro", 20, "Sevilla");
        familia familia2 = new familia(null, "Segismundo", 68, "Fuentealbilla");
        familia familia3 = new familia(null, "Sergio", 37, "Barcelona");

        session.beginTransaction();

        session.save(familia1);
        session.save(familia2);
        session.save(familia3);

        session.getTransaction().commit();
        session.close();
    }

}