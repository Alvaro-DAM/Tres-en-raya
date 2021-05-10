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
        boolean acabar = false;

        this.tablero.empieza(jugador1, jugador2);

        do {
            this.tablero.mostrarTablero();

            if (this.tablero.getContador() == 9) {
                acabar = true;
                System.out.println('\n' + "Empate");
            }

        } while (!acabar);
    }
}
