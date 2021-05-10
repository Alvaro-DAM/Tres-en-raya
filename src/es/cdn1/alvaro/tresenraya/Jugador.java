package es.cdn1.alvaro.tresenraya;

/**
 * Clase para representar a los jugadores
 */

public class Jugador {

    private String nombre;
    private char cruzOCirculo;
    private boolean esGanador;

    /**
     * Constrcutor de la clase Jugador
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

    public String getNombre() {
        return this.nombre;
    }

    public boolean getEsGanador() {
        return this.esGanador;
    }

    public void setEsGanador(boolean esGanador) {
        this.esGanador = esGanador;
    }
}
