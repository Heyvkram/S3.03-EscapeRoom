import java.sql.*;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final String CABECERA_APP = String.format(
            "\n%c%s%c%n%c  %s  %c%n%c%s%c%n",
            '\u2554', String.valueOf('\u2550').repeat(27), '\u2557',
            '\u2551', "VIRTUAL ESCAPE ROOM APP", '\u2551',
            '\u255A', String.valueOf('\u2550').repeat(27), '\u255D'
    );

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        Tipos de traza disponibles:
            log.trace("Trace message", "El nivell més detallat, utilitzat per a depuració fina");
            log.debug("Debug message", "Informació de depuració general");
            log.info("Info message", "Missatges informatius normals");
            log.warn("warn message", "Advertències, situacions que poden causar problemes");
            log.error("error message", "Errors, situacions que han causat problemes.");
            log.fatal("fatal message", "Errors crítics que poden fer que l'aplicació falli");
        */
        Scanner scanner = new Scanner(System.in);
        System.out.println(CABECERA_APP);
        Menu.principalMenu(scanner);
        scanner.close();

    }

}

