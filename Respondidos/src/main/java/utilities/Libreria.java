package utilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase de utilidad que proporciona funcionalidades comunes para la interacción del usuario.
 */
public class Libreria {
    /**
     * Función para el ingreso de una opción que chequee si el número ingresado es un Entero y si pertenece a las opciones brindadas
     * @param rangoMin Número de opción mínima
     * @param rangoMax Número de opción máxoma
     * @return Devuelve el número ingresado por el usuario si cumple con las condiciones
     * @author Quesada Manuel
     */
    public static int catchInt(int rangoMin, int rangoMax){
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
        return numero;
    }

    /**
     * Imprime una pregunta junto con una lista de posibles respuestas.
     *
     * @param pregunta La pregunta a mostrar.
     * @param listaTuplas Una lista de tuplas donde cada tupla contiene un par de valores,
     *                    el primero es un número entero y el segundo es una cadena que representa una posible respuesta.
     * @author Quesada Manuel
     */

    public static void imprimirPregunta(String pregunta, ArrayList<Tupla<Integer,String>> listaTuplas){
        System.out.println("Pregunta: " + pregunta);
        int contador = 1;

        for (Tupla tupla : listaTuplas){
            String respuesta = (String) tupla.getSegundo();
            System.out.println(contador + ": " + respuesta);
            contador++;
        }
    }
}
