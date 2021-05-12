/**
 * Clase que simula un "log"
 */

package es.cdn1.alvaro.tresenraya;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private BufferedWriter log;

    /**
     * Constrcutor de la clase "Log"
     */
    public Log() {
        String date = new SimpleDateFormat("dd-MM-yyyy_HHmm").format(new Date());
        String path = "logs\\";
        String extension = "-log.txt";
        String filename = path.concat(date).concat(extension);

        try {
            this.log = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Escribe el mensaje pasado en el fichero de log
     * Si el programa termina "abruptamente", los datos pueden no ser escritos
     *
     * @param mensaje El mensaje que queremos escribir
     */
    public void escribir(String mensaje) {

        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());

        try {
            this.log.write(date + " - " + mensaje + '\n');
            this.log.newLine();
            this.log.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obliga a escribir todos los streams de datos y cierra el fichero
     */
    public void acabar() {
        try {
            this.log.flush();
            this.log.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
