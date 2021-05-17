package es.cdn1.alvaro.tresenraya;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que simula un tablero de "Tres en Raya"
 *
 * @author Alvaro Pisabarros - CDN1
 */

public class Tablero {
    private final char[][] valores;

    private int contador; // Contador para numero maximo de movimientos (9)

    private Jugador j1; // Jugador 1
    private Jugador j2; // Jugador 2

    private Log log;

    /**
     * Constructor de la clase Tablero
     */
    public Tablero(Log log) {
        this.valores = new char[3][3];

        // Escribimos un tablero vacio
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.valores[i][j] = '_';
            }
        }

        this.log = log;
    }

    /**
     * Metodo para obtener el numero de movimientos realizados
     *
     * @return el numero actual de movimientos que se han realizado hasta ahora
     */
    public int getContador() {
        return this.contador;
    }

    /**
     * Metodo para establecer quien sera el primer jugador y quien sera el segundo
     *
     * @param j1 El jugador que ira primero
     * @param j2 El jugador que ira segundo
     */
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
        char cruz = 'X';
        char circulo = 'O';

        Jugador jugador1 = new Jugador(j1, cruz);
        Jugador jugador2 = new Jugador(j2, circulo);

        Random rand = new Random(System.currentTimeMillis());

        String mensaje;

        int dado1 = rand.nextInt(6) + 1;
        int dado2 = rand.nextInt(6) + 1;

        mensaje = "El jugador \"" + j1 + "\" tira el dado: " + dado1;
        System.out.println(mensaje);
        this.log.escribir(mensaje);

        mensaje = "El jugador \"" + j2 + "\" tira el dado: " + dado2;
        System.out.println(mensaje);
        this.log.escribir(mensaje);

        if (dado1 > dado2) {
            setJugadores(jugador1, jugador2);
        } else if (dado2 > dado1) {
            setJugadores(jugador2, jugador1);
        } else {
            setJugadores(jugador1, jugador2);
        }

        mensaje = "Empieza: \"" + this.j1.getNombre() + "\", jugando con: " + this.j1.obtenerMarca();
        System.out.println(mensaje);
        this.log.escribir(mensaje);
    }

    /**
     * Metodo para saber de quien es el turno
     *
     * @return El jugador del cual es el turno
     */
    public Jugador obtenerTurno() {
        Jugador jugador;

        if (contador % 2 == 0) {
            jugador = this.j1;
        } else {
            jugador = this.j2;
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
    public void jugar(Jugador jugador) {
        boolean status = false;

        Scanner sc = new Scanner(System.in);

        do {
            boolean correcto = false;

            String letraFila;
            int fila = 0, col;

            do {
                System.out.println("Jugador: \"" + jugador.getNombre() + "\" introduzca la fila:");
                letraFila = sc.next();

                if (letraFila.isBlank()) {
                    System.out.println("No ha introducido una fila, por favor introduzca una\n");
                } else {
                    switch (letraFila.toLowerCase()) {
                        case "a":
                            fila = 0;
                            correcto = true;
                            break;

                        case "b":
                            fila = 1;
                            correcto = true;
                            break;

                        case "c":
                            fila = 2;
                            correcto = true;
                            break;

                        default:
                            System.out.println("Fila incorrecta, por favor introduzcala de nuevo\n");
                            break;
                    }
                }
            } while (!correcto);

            correcto = false;

            do {
                col = 0;

                System.out.println("Jugador: \"" + jugador.getNombre() + "\" introduzca la columna:");

                try {
                    col = sc.nextInt() - 1;
                    correcto = true;

                } catch (InputMismatchException e) {
                    System.out.println("Por favor, introduzca un numero\n");
                    sc.next();
                }

            } while (!correcto);

            try {
                if (this.valores[fila][col] != '_') {
                    System.out.println("Posicion ya ocupada\n");
                } else {
                    this.log.escribir("El jugador " + jugador.getNombre() +
                            " ha introducido un/a '" + jugador.obtenerMarca() +
                            "' en la posicion " + fila + ',' + ' ' + col);

                    this.valores[fila][col] = jugador.obtenerMarca();
                    status = true;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Posicion no valida\n");
            }

        } while (!status);

        if (!comprobarTresEnRaya(jugador)) {
            this.contador++;
        }
    }

    /**
     * Metodo para comprobar si alguien ha ganado
     *
     * @param jugador El jugador del cual comprobaremos si ha hecho un "Tres en raya"
     * @return <code>true</code> si hay un ganador y <code>false</code> si no
     */
    public boolean comprobarTresEnRaya(Jugador jugador) {
        boolean tresEnRaya = false;
        char marca = jugador.obtenerMarca();

        if (comprobarFilas(marca)) {
            tresEnRaya = true;
            jugador.setEsGanador(true);

            this.log.escribir("El jugador " + jugador.getNombre() + " ha hecho un \"Tres en raya\" por filas");
        } else if (comprobarColumnas(marca)) {
            tresEnRaya = true;
            jugador.setEsGanador(true);

            this.log.escribir("El jugador " + jugador.getNombre() +
                    " ha hecho un \"Tres en raya\" por columnas");
        } else if (comprobarDiagonales(marca)) {
            tresEnRaya = true;
            jugador.setEsGanador(true);

            this.log.escribir("El jugador " + jugador.getNombre()
                    + " ha hecho un \"Tres en raya\" por diagonal");
        }

        return tresEnRaya;
    }

    /**
     * Comprueba si hay un "Tres en raya" en alguna fila
     *
     * @param marca La marca que el jugador utliza ('X' o 'O')
     * @return <code>true</code> si hay una fila con "Tres en raya" y <code>false</code> si no
     */
    private boolean comprobarFilas(char marca) {
        boolean tresEnRayaFila = false;
        char[] fila = {marca, marca, marca};

        for (char[] filas : this.valores) {
            if (Arrays.equals(fila, filas)) {
                tresEnRayaFila = true;
                break;
            }
        }

        return tresEnRayaFila;
    }

    /**
     * Comprueba si hay un "Tres en raya" en alguna columna
     *
     * @param marca La marca que el jugador utliza ('X' o 'O')
     * @return <code>true</code> si hay una columna con "Tres en raya" y <code>false</code> si no
     */
    private boolean comprobarColumnas(char marca) {
        boolean tresEnRayaCol = false;

        char[] col = {marca, marca, marca};

        char[] col1 = {valores[0][0], valores[1][0], valores[2][0]};
        char[] col2 = {valores[0][1], valores[1][1], valores[2][1]};
        char[] col3 = {valores[0][2], valores[1][2], valores[2][2]};

        if (Arrays.equals(col, col1)) {
            tresEnRayaCol = true;
        } else if (Arrays.equals(col, col2)) {
            tresEnRayaCol = true;
        } else if (Arrays.equals(col, col3)) {
            tresEnRayaCol = true;
        }

        return tresEnRayaCol;
    }

    /**
     * Comprueba si hay un "Tres en raya" en alguna diagonal
     *
     * @param marca La marca que el jugador utliza ('X' o 'O')
     * @return <code>true</code> si hay una diagonal con "Tres en raya" y <code>false</code> si no
     */
    private boolean comprobarDiagonales(char marca) {
        boolean tresEnRayaDiag = false;

        char[] diagonal = {marca, marca, marca};
        char[] diagonalPrincipal = new char[this.valores.length];
        char[] diagonalSecundaria = new char[this.valores.length];

        // Anadimos valores a las diagonales
        for (int i = 0; i < this.valores.length; i++) {
            for (int j = 0; j < this.valores[0].length; j++) {
                if (i == j) {
                    diagonalPrincipal[i] = this.valores[i][j];
                }
                if (i + j == this.valores.length - 1) {
                    diagonalSecundaria[i] = this.valores[i][j];
                }
            }
        }

        if (Arrays.equals(diagonal, diagonalPrincipal)) {
            tresEnRayaDiag = true;
        } else if (Arrays.equals(diagonal, diagonalSecundaria)) {
            tresEnRayaDiag = true;
        }

        return tresEnRayaDiag;
    }
}
