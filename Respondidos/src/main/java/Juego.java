import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pregunta pregunta = Pregunta.obtenerPregunta();

        System.out.println(pregunta.getPregunta());
        System.out.println(pregunta.getRespuestaCorrecta());
        for (String respIn : pregunta.getRespuestasIncorrectas()){
            System.out.println(respIn);
        }

    }

    /**
     * Función para el ingreso de una opción que chequee si el número ingresado es un Entero y si pertenece a las opciones brindadas
     * @param rangoMin Número de opción mínima
     * @param rangoMax Número de opción máxoma
     * @return Devuelve el número ingresado por el usuario si cumple con las condiciones
     */
    private int catchInt(int rangoMin, int rangoMax){
        Scanner sc1 = new Scanner(System.in);
        boolean entradaValida = false;
        int numero = 0;

        while (!entradaValida){
            try {
                System.out.println("Ingrese una opción: ");
                numero = sc1.nextInt();

                if (numero >= rangoMin && numero <= rangoMax) {
                    entradaValida = true;
                } else {
                    System.out.println("El número no está dentro del rango permitido. Elija una opción entre "+rangoMin+" y "+rangoMax+".");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Debes introducir un número entero.");
                sc1.next();
            }
        }

        sc1.close();
        return numero;

    }
}
