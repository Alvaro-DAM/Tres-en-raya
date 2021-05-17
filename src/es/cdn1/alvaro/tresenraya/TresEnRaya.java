package es.cdn1.alvaro.tresenraya;

/**
 * Clase para jugar al "Tres en raya" en linea de comandos
 *
 * @author Alvaro Pisabarros - CDN1
 */

public class TresEnRaya {
    private Tablero tablero;
    private String jugador1, jugador2;
    private Log log;

    /**
     * Constructor de la clase TresEnRaya
     *
     * @param nombreJ1 nombre del primer jugador
     * @param nombreJ2 nombre del segundo jugador
     */
    public TresEnRaya(String nombreJ1, String nombreJ2) {
        this.log = new Log();
        this.tablero = new Tablero(this.log);
        this.jugador1 = nombreJ1;
        this.jugador2 = nombreJ2;
    }

    /**
     * Metodo encargado de iniciar el juego
     */
    public void iniciar() {
        boolean fin = false;
        Jugador jugador;
        String mensaje;

        this.tablero.empieza(jugador1, jugador2);

        do {
            jugador = this.tablero.obtenerTurno();

            this.tablero.mostrarTablero();

            if (this.tablero.getContador() == 9) {
                fin = true;
                mensaje = "Ha sido un empate.";

                this.log.escribir(mensaje);
                System.out.println('\n' + mensaje);

                this.log.acabar();

            } else if (jugador.getEsGanador()) {
                fin = true;

                mensaje = "¡Fin de partida!";
                System.out.println(mensaje);
                this.log.escribir(mensaje);

                mensaje = "Gana el jugador: \"" + jugador.getNombre() + '"';
                System.out.println(mensaje);
                this.log.escribir(mensaje);

                this.log.acabar();
            } else {
                this.tablero.jugar(jugador);
            }

        } while (!fin);
    }
}
