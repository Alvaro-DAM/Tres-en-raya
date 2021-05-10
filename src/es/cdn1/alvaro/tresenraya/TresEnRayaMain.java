package es.cdn1.alvaro.tresenraya;

import java.util.Scanner;

public class TresEnRayaMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String j1, j2;

        boolean status = false;

        do {
            System.out.println("Deme el nombre del primer jugador");
            j1 = sc.nextLine();

            System.out.println("Deme el nombre del segundo jugador");
            j2 = sc.nextLine();

            if (j1.isBlank() || j2.isBlank()) {
                System.out.println('\n' + "Por favor, no introduza nombre/s vacio/s" + '\n');
            }
            else {
                status = true;
            }

        } while (!status);

        new TresEnRaya(j1, j2).iniciar();
    }
}
