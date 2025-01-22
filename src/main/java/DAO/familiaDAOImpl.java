package DAO;

import entities.adopcion;
import entities.animales;
import entities.familia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class familiaDAOImpl implements familiaDAO {

    /**
     * @insert Inserta una nueva familia en la bbdd
     */
    @Override
    public void insertarFamilia() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la familia:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la edad de la familia:");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese la ciudad de la familia:");
        String ciudad = scanner.nextLine();

        familia familia = new familia();
        familia.setNombre(nombre);
        familia.setEdad(edad);
        familia.setCiudad(ciudad);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(familia);
        session.getTransaction().commit();

    }

    /**
     * @return Retorna una lista de familias con animales acogidos
     */
    @Override
    public void consultarFamiliasConAnimalesAcogidos() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Sentencia HQL
        String hql = "SELECT f, an " +
                "FROM familia f " +
                "JOIN adopcion a ON a.id_familia.id = f.id " +
                "JOIN animales an ON an.id = a.id_animal.id";

        Query<Object[]> query = session.createQuery(hql);
        List<Object[]> resultados = query.list();

        // Inicializamos una variable para almacenar el nombre de la familia actual
        String familiaActual = "";

        System.out.println("\n\nFamilias con animales acogidos:");

        // Iteramos sobre los resultados de la consulta
        for (Object[] resultado : resultados) {

            // Obtenemos la familia y el animal de cada resultado
            familia fam = (familia) resultado[0];
            animales an = (animales) resultado[1];

            // Comprobamos si la familia actual es diferente a la familia del resultado
            if (!fam.getNombre().equals(familiaActual)) {
                // Si es diferente, imprimimos el nombre de la familia
                System.out.println("Familia: " + fam.getNombre());
                // Actualizamos la familia actua
                familiaActual = fam.getNombre();
            }
            // Imprimimos el nombre del animal
            System.out.println("  - " + an.getNombre() + " - " + an.getEspecie());
        }
    }

    @Override
    public void verFamilias() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query<familia> query = session.createQuery("FROM familia");
        List<familia> familias = query.getResultList();

        System.out.println("\n\n");
        for (familia familia : familias) {
            System.out.println(familia.toString());
        }
    }

    /**
     * @insert Realiza una nueva adopcion
     */
    @Override
    public void realizarAdopcion() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nIngrese el ID de la familia:");
        int idFamilia = Integer.parseInt(scanner.nextLine());

        System.out.println("\n\nIngrese el ID del animal:");
        int idAnimal = Integer.parseInt(scanner.nextLine());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        familia familiaAdoptante = session.get(familia.class, idFamilia);
        animales animalAdoptado = session.get(animales.class, idAnimal);

        if (familiaAdoptante != null && animalAdoptado != null) {
            adopcion nuevaAdopcion = new adopcion(null, familiaAdoptante, animalAdoptado);
            session.beginTransaction();
            session.save(nuevaAdopcion);
            session.getTransaction().commit();
            System.out.println("\n\nAdopción realizada con éxito.");
        } else {
            System.out.println("\n\nFamilia o animal no encontrado.");
        }

        session.close();

    }
}
