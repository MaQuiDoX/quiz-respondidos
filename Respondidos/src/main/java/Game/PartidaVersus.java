package Game;


import Game.Partida;
import java.util.ArrayList;

public class PartidaVersus extends Partida implements PuntajeJuego {
    protected Jugador jugador1;
    protected Jugador jugador2;

    
    public PartidaVersus(int id, ArrayList<ArrayList<Integer>> pR, Jugador j1, Jugador j2) {
        this.idPartida = id;
        this.preguntasRealizadas = pR;
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActivo = null;
    }
    
    public void cambiarJugador(Jugador jugador){
        if (this.jugadorActivo != jugador){
            this.jugadorActivo = jugador;
        } else {
            System.out.println("El jugador ingresado ya esta jugando");
        }
    }
    
    public void iniciarPartida(Jugador j1, Jugador j2) throws Exception {
        partidaVersus = true;
        int turno = 0;
        boolean partidaNoTerminada = true;
        
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }
        
        while (partidaNoTerminada){
            //La logica con la que nos fijaremos de quien es el turno
            if (turno % 2 == 0){
                cambiarJugador(j1);
            } else {
                cambiarJugador(j2);
            }
            
            System.out.println("///Es el turno de:" + this.jugadorActivo.getNombre() + " que va " + this.jugadorActivo.getPuntajePartida() + " puntos///");
            turnoJugador(this.jugadorActivo);
            //Revisamos que la partida haya terminado (cuando alguno de los dos jugadores llegue o sobrepase el limite, por ejemplo 100 puntos.
            if (this.jugadorActivo.getPuntajePartida() >= 25){
                System.out.println("El juego termino, nuestro ganador es: " + this.jugadorActivo.getNombre());
                partidaNoTerminada = false;
            }
            
            turno++;
            
        }
        calcularPuntaje(j1);
        calcularPuntaje(j2);
        
        
    }
    
    @Override
    public void calcularPuntaje(Jugador jugador){
        if (jugador.getPuntajePartida() >= 25){
            int resto = jugador.getPuntajePartida() - 25;
            int puntosTotales = 25 + (resto - (2* jugador.getContadorUsoPoderes()));
            jugador.sumarPuntos(puntosTotales);
        } else {
            jugador.sumarPuntos(10);
        }
        jugador.resetRacha();
        jugador.resetContadorUsoPoderes();
        jugador.setPuntajePartida(0);
    }
}
