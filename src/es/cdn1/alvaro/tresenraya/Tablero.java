package es.cdn1.alvaro.tresenraya;

import java.lang.Math;

/**
 * Clase que simula un tablero de TresEnRaya
 */

public class Tablero {
    private final char[][] valores;
    private final char CRUZ = 'X';
    private final char CIRCULO = 'O';

    private int contador; // Contador para numero maximo de movimientos (9)

    private Jugador j1; // Jugador 1
    private Jugador j2; // Jugador 2

    private final int[][] combinacionesGanadoras;

    /**
     * Constructor de la clase Tablero
     */
    public Tablero() {
        this.valores = new char[3][3];

        // Escribimos un tablero vacio
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.valores[i][j] = '_';
            }
        }

        this.combinacionesGanadoras = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontales
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // verticales
                {0, 4, 8}, {2, 4, 6} // diagonales
        };
    }

    public int getFilasTablero() {
        return this.valores.length;
    }

    public int getColumnasTablero() {
        return this.valores[0].length;
    }

    public int getContador() {
        return this.contador;
    }

    public char getValor(int fila, int columna) {
        return this.valores[fila][columna];
    }

    public void setValores(int fila, int columna, char valor) {
        this.valores[fila][columna] = valor;
    }

    public void setJugadores(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    /**
     * Metodo para obtener que jugador empieza
     *
     * @param j1 El nombre del primer jugador
     * @param j2 El nombre del segundo jugador
     */
    public void empieza(String j1, String j2) {
        Jugador jugador1 = new Jugador(j1, CRUZ);
        Jugador jugador2 = new Jugador(j2, CIRCULO);

        int dado1 = (int) (Math.random() * 6 + 1);
        int dado2 = (int) (Math.random() * 6 + 1);

        System.out.println("El jugador " + j1 + " tira el dado: " + dado1);
        System.out.println("El jugador " + j2 + " tira el dado: " + dado2);

        if (dado1 > dado2) {
            setJugadores(jugador1, jugador2);
        } else if (dado2 > dado1) {
            setJugadores(jugador2, jugador1);
        } else {
            setJugadores(jugador1, jugador2);
        }
        System.out.println("Empieza: " + this.j1.getNombre() + ", con: " + this.j1.obtenerMarca());
    }

    /**
     * Metodo para saber a quien le toca
     *
     * @return El jugador del cual es el turno
     */
    public Jugador obtenerTurno() {
        Jugador jugador;

        if (contador % 2 == 0) {
            jugador = this.j2;
        } else {
            jugador = this.j1;
        }

        return jugador;
    }

    /**
     * Muestra el estado actual del tablero por pantalla
     */
    public void mostrarTablero() {
        char[] columnas = {'A', 'B', 'C'};

        System.out.println();

        System.out.println('\t' + "1" + "\t 2" + "\t  3");

        for (int i = 0; i < this.valores.length; i++) {
            System.out.print(columnas[i] + "" + ' ');

            for (int j = 0; j < this.valores[0].length; j++) {
                System.out.print("| " + this.valores[i][j] + " |");
            }
            System.out.print('\n');
        }

        System.out.println();
    }

    /**
     * Permite al jugador poner su marca ('X' o 'O') en la posicion que desee
     */
    public void jugar() {

    }
}
