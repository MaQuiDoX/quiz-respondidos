package utilities;

/**
 * La clase Tupla representa un par de valores, también conocido como duple. La tupla es
 * inmutable en referencia al primer valor y mutable en referencia al segundo valor.
 *
 * @param <A> El tipo del primer elemento de la tupla
 * @param <B> El tipo del segundo elemento de la tupla
 */
public class Tupla<A, B> {
    private final A primero;
    private B segundo;

    /**
     * Constructor para crear una instancia de la clase Tupla con dos elementos.
     *
     * @param primero el primer elemento de la tupla de tipo A
     * @param segundo el segundo elemento de la tupla de tipo B
     */
    public Tupla(A primero, B segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    /**
     * Devuelve el primer elemento de la tupla.
     *
     * @return el primer elemento de la tupla de tipo A
     */
    public A getPrimero() {
        return primero;
    }

    /**
     * Devuelve el segundo elemento de la tupla.
     *
     * @return el segundo elemento de la tupla de tipo B
     */
    public B getSegundo() {
        return segundo;
    }

    /**
     * Establece el segundo elemento de la tupla.
     *
     * @param segundo el nuevo valor para el segundo elemento de la tupla, de tipo B
     */
    public void setSegundo(B segundo){
        this.segundo = segundo;
    }
}

