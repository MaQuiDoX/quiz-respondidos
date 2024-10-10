/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Bombita extends Bomba {
    private static final int cantExplosiones = 1;
    
    Bombita(){
       this.nombre = "bombita";
       this.descripcion = "¡Explota 1 respuesta incorrecta!";
       this.precio = 20;
       this.usos = 0;
    }
    
    @Override
    protected int getCantExplosiones(){
        return cantExplosiones;
    }
}
