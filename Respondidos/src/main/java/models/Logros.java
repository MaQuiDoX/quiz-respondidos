package models;

public abstract class Logros {
    protected String nombre;
    protected int meta;
    protected static int total = 1;


    public Logros(int num) {

        elegirNombre(num);


    }

    public String getNombre() {
        return nombre;
    }

    public void elegirNombre(int num) {

    }

    public void comprobar(boolean aumentar) {

    }



}
