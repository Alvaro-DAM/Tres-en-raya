package es.cdn1.alvaro.tresenraya;

public class TresEnRaya {
    private Tablero tablero;
    private String jugador1, jugador2;

    public TresEnRaya(String nombreJ1, String nombreJ2) {
        this.tablero = new Tablero();
        this.jugador1 = nombreJ1;
        this.jugador2 = nombreJ2;
    }

    public void iniciar() {
        boolean fin = false;
        Jugador jugador;

        this.tablero.empieza(jugador1, jugador2);

        do {
            jugador = this.tablero.obtenerTurno();

            this.tablero.mostrarTablero();

            if (this.tablero.getContador() == 9) {
                fin = true;
                System.out.println('\n' + "Ha sido un empate.");
            } else if (jugador.getEsGanador()) {
                fin = true;
                System.out.println("¡Fin de partida!");
                System.out.println("Gana el jugador: \"" + jugador.getNombre() + '"');
            } else {
                this.tablero.jugar(jugador);
            }

        } while (!fin);
    }
}
