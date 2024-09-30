package org.example;

public class Tupla<A, B> {
    private final A primero;
    private final B segundo;

    public Tupla(A primero, B segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public A getPrimero() {
        return primero;
    }

    public B getSegundo() {
        return segundo;
    }
}

