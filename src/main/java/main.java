import DAO.animalDAOImpl;
import DAO.familiaDAOImpl;
import entities.especies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class main {
    public static animalDAOImpl animalDAO = new animalDAOImpl();
    public static familiaDAOImpl familiaDAO = new familiaDAOImpl();

    public static void main(String[] args) {
        // Redirigir errores a un flujo vacío para no mostrar nada en la consola, de esta manera no salen todos los mensajes en rojo en la consola al ejecutar
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // No hacer nada, simplemente redirige la salida de errores
            }
        }));

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        int opcion = 0;
        do {


            System.out.println("""
                    \n\n
                        Menu de Opciones:
                        1. Insertar Animal
                        2. Consultar Animales por Especie
                        3. Actualizar Estado de Animal
                        4. Insertar Familia
                        5. Consultar Familias
                        6. Consultar Familias con Animales Acogidos
                        7. Realizar Adopcion
                        8. Salir
                    """);
            Scanner scanner = new Scanner(System.in);
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    animalDAO.insertarAnimal();
                    break;
                case 2:
                    int opcionEspecie = 0;
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
                                System.out.println("Opción inválida");
                                return;
                        }
                    } while (opcionEspecie < 1 || opcionEspecie > 7);

                    animalDAO.consultarAnimales(especie);
                    break;
                case 3:
                    animalDAO.actualizarEstadoAnimal();
                    break;
                case 4:
                    familiaDAO.insertarFamilia();
                    break;
                case 5:
                    familiaDAO.verFamilias();
                    break;
                case 6:
                    familiaDAO.consultarFamiliasConAnimalesAcogidos();
                    break;
                case 7:
                    familiaDAO.realizarAdopcion();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        } while (opcion != 8);
    }

}
