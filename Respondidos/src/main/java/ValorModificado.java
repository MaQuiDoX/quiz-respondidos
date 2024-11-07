public class ValorModificado {
    private int valor = 0;
    private final Object lock = new Object();

    public void esperarHastaModificacion() {
        synchronized (lock) {
            try {
                // Espera hasta que el valor cambie
                System.out.println("Esperando que el valor cambie...");
                lock.wait();
                System.out.println("Valor ha cambiado: " + valor);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Hilo interrumpido.");
            }
        }
    }

    public void modificarValor(int nuevoValor) {
        synchronized (lock) {
            valor = nuevoValor;
            System.out.println("Valor modificado a: " + valor);
            // Notifica a los hilos en espera que el valor ha cambiado
            lock.notifyAll();
        }
    }

    public int getValor() {
        return valor;
    }
}


