package gm.estudiantes;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.List;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
    //para indicar que se ha de ejecutar una aplicacion por consola en Spring
    //se tiene que implementar la interface CommandLineRunner, y a su vez el metodo
    //a implementar es "run"

    @Autowired
    private EstudianteServicio estudianteServicio;

    //forma en que se envia la info
    //como se usa Spring, ya no se usara System.Out si no se usara logger
    private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

    //se define una variable para el salto de linea para cualquier SO
    String nl = System.lineSeparator();

	public static void main(String[] args) {
        logger.info("Iniciando la aplicacion...");
        //levantamos la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
        logger.info("Aplicacion finalizada");
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info(nl+"Ejecutando metodo run de Spring...."+nl);
        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir){
            mostrarMenu();
            salir = ejecutarOperaciones(consola);
            logger.info(nl);
        }//fin del while
    }

    public static void mostrarMenu() {
        logger.info("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elije una opcion:
                                
                """);
    }

    public boolean ejecutarOperaciones(Scanner consola){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion){
            case 1 ->{
                //listar estudiantes
                logger.info(nl+"Listado de Estudiantes.."+nl);
                List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
                estudiantes.forEach((estudiante -> logger.info(estudiante.toString()+nl)));
            }
            case 2 ->{
                logger.info("Ingrese el id de estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante encontrado = estudianteServicio.buscarEstudiantePorId(idEstudiante);
                if(encontrado!=null)
                    logger.info("Estudiante encontrado: "+encontrado+nl);
                else
                    logger.info("Estudiante No encontrado: "+encontrado+nl);
            }
            case 3->{
                logger.info("Agregar Estudiante");
                logger.info("Nombre: ");
                var nombre = consola.nextLine();
                logger.info("Apellido: ");
                var apellido = consola.nextLine();
                logger.info("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();

                //creando un objeto estudiante
                var estudiante = new Estudiante();
                estudiante.setEmail(nombre);
                estudiante.setApellido(apellido);
                estudiante.setTelefono(telefono);
                estudiante.setEmail(email);

                estudianteServicio.guardarEstudiante(estudiante);

                logger.info("Estudiante agregado: "+estudiante+nl);
            }
            case 4->{
                logger.info("Modificar estudiante: ");
                logger.info("Id Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());

                //verificamos si el estudiante existe
                Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

                if(estudiante!=null){
                    System.out.print("Nombre: ");
                    var nombre = consola.nextLine();
                    System.out.print("Apellido: ");
                    var apellido = consola.nextLine();
                    System.out.print("Telefono: ");
                    var telefono = consola.nextLine();
                    System.out.print("Email: ");
                    var email = consola.nextLine();

                    estudiante.setEmail(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setTelefono(telefono);
                    estudiante.setEmail(email);

                    estudianteServicio.guardarEstudiante(estudiante);
                    logger.info("Estudiante modificado: "+estudiante+nl);
                }else {
                    logger.info("No existe el Estudiante");
                }
            }
            case 5 ->{
                logger.info("Eliminar estudiante");
                logger.info("Id Estudiante: ");
                var IdEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(IdEstudiante);

                if(estudiante!=null) {
                    logger.info("Estudiante eliminado con Id: " + IdEstudiante);
                }else {
                    logger.info("Estudiante NO encontrado con Id: " + IdEstudiante);
                }
            }
            case 6 ->{
                logger.info("Hasta pronto!!!"+nl+nl);
                salir = true;
            }
            default -> logger.info("Opcion no reconocida");

        }
        return salir;
    }
}
