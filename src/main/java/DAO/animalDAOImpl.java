package DAO;

import entities.animales;
import entities.especies;
import entities.estado_animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class animalDAOImpl implements animalDAO {

    /**
     *
     * @insert Inserta un nuevo animal en la bbdd
     *
     */
    @Override
    public void insertarAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del animal:");
        String nombre = scanner.nextLine();

        int opcionEspecie=0;
        especies especie = null;
        do {
            System.out.println("""
                    \n\n
                    Ingrese la especie del animal
                    1. Perros
                    2. Gatos
                    3. Pajaritos
                    4. CerdosVietnamitas
                    5. Serpientes
                    6. Camaleones
                    7. Aranyas
                """);

            opcionEspecie = Integer.parseInt(scanner.nextLine());

            switch (opcionEspecie) {
                case 1:
                    especie = especie.Perros;
                    break;
                case 2:
                    especie = especie.Gatos;
                    break;
                case 3:
                    especie = especie.Pajaritos;
                    break;
                case 4:
                    especie = especie.CerdosVietnamitas;
                    break;
                case 5:
                    especie = especie.Serpientes;
                    break;
                case 6:
                    especie = especie.Camaleones;
                    break;
                case 7:
                    especie = especie.Aranyas;
                    break;
                default:
                    System.out.println("\n\nOpción inválida");
                    return;
            }
        }while (opcionEspecie < 1 || opcionEspecie > 7);

        System.out.println("\n\nIngrese la edad del animal:");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("\n\nIngrese la descripción del animal:");
        String descripcion = scanner.nextLine();

        System.out.println("""
            \n\n
            Ingrese el estado del animal
            1. RecienAbandonado
            2. TiempoEnElRefugio
            3. ProximamenteEnAcogida
        """);

        int opcionEstado = Integer.parseInt(scanner.nextLine());
        estado_animal estado = null;
        switch (opcionEstado) {
            case 1:
                estado = estado_animal.RecienAbandonado;
                break;
            case 2:
                estado = estado_animal.TiempoEnElRefugio;
                break;
            case 3:
                estado = estado_animal.ProximamenteEnAcogida;
                break;
            default:
                System.out.println("\n\nOpción inválida");
                return;
        }

        animales animal = new animales();
        animal.setNombre(nombre);
        animal.setEspecie(especie);
        animal.setEdad(edad);
        animal.setDescripcion_perdida(descripcion);
        animal.setEstado_animal(estado);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(animal);
        session.getTransaction().commit();
    }

    /**
     *
     * @param especie
     *
     * @return Retorna una lista de animales de la especie indicada
     */
    @Override
    public void consultarAnimales(especies especie) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query<animales> query = session.createQuery("FROM animales WHERE especie = :especieElegida", animales.class);
        query.setParameter("especieElegida", especie);

        List<animales> animales = query.list();
        for (animales animal: animales) {
            System.out.println(animal.toString());
        }

    }

    /**
     *
     * @update Actualiza el estado de un animal
     */
    @Override
    public void actualizarEstadoAnimal() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nIngrese el id del animal a actualizar:");
        int id = Integer.parseInt(scanner.nextLine());

        animales animal = session.get(animales.class, id);

        System.out.println("""
            \n\n
            Ingrese el estado del animal
            1. RecienAbandonado
            2. TiempoEnElRefugio
            3. ProximamenteEnAcogida
        """);

        int opcionEstado = Integer.parseInt(scanner.nextLine());
        estado_animal estado = null;
        switch (opcionEstado) {
            case 1:
                estado = estado_animal.RecienAbandonado;
                break;
            case 2:
                estado = estado_animal.TiempoEnElRefugio;
                break;
            case 3:
                estado = estado_animal.ProximamenteEnAcogida;
                break;
            default:
                System.out.println("\n\nOpción inválida");
                return;
        }

        animal.setEstado_animal(estado);

        session.beginTransaction();
        session.update(animal);
        session.getTransaction().commit();
    }
}
