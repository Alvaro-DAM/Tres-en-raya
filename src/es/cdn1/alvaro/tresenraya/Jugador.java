package es.cdn1.alvaro.tresenraya;

/**
 * Clase que representa al jugador
 *
 * @author Alvaro Pisabarros - CDN1
 */

public class Jugador {

    private String nombre;
    private char cruzOCirculo;
    private boolean esGanador;

    /**
     * Constructor de la clase Jugador
     *
     * @param nombre       El nombre del jugador
     * @param cruzOCirculo El simbolo que utilizara el jugador ('X' o 'O')
     */
    public Jugador(String nombre, char cruzOCirculo) {
        this.nombre = nombre;
        this.cruzOCirculo = cruzOCirculo;
        this.esGanador = false;
    }

    /**
     * Obtiene la marca que el jugador utiliza
     *
     * @return 'X' o 'O'
     */
    public char obtenerMarca() {
        return this.cruzOCirculo;
    }

    /**
     * Metodo para obtener el nombre del jugador
     *
     * @return El nombre del jugador
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Comprueba si este jugador ha ganado
     *
     * @return <code>true</code> si el jugador ha hecho un "Tres en raya" y <code>false</code> si no
     */
    public boolean getEsGanador() {
        return this.esGanador;
    }

    /**
     * Metodo para establecer si el jugador ha hecho un "Tres en raya"
     *
     * @param esGanador <code>true</code> si el jugador ha ganado y <code>false</code> si no
     */
    public void setEsGanador(boolean esGanador) {
        this.esGanador = esGanador;
    }
}
