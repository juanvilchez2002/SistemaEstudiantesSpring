package gm.estudiantes;

import gm.estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
    }
}
