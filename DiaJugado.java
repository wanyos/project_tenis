
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * Clase que describe un dia jugado por uno o dos jugadores del sistema
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */


public class DiaJugado
{

    private GregorianCalendar fecha_jugado;
    private Jugador jugador1, jugador2;
    private Bono bono_jugado;
    private int id, cantHoraJ1, cantHoraJ2, cantHoraBono;
    
   
    public DiaJugado (Jugador jugador1, Jugador jugador2, Bono bono_jugado, GregorianCalendar fecha_jugado, int cantHoraJ1, int cantHoraJ2, int cantHoraBono)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.bono_jugado = bono_jugado;
        this.fecha_jugado = fecha_jugado;
        this.cantHoraJ1 = cantHoraJ1;
        this.cantHoraJ2 = cantHoraJ2;
        this.cantHoraBono = cantHoraBono;
        this.id = Inicio.getBaseDatos().getNuevoIdDiaJugado();
    }

    
    public String getIdString ()
    {
      return "DJ00"+this.id;    
    }
    
    
    public int getId ()
    {
        return id;
    }
    
    
    public GregorianCalendar getFechaJugado ()
    {
        return fecha_jugado;
    }
    
    
    public int getDiaJugado ()
    {
        int dia = fecha_jugado.get (Calendar.DATE);
        return dia;
    }
    
    
    public int getMesJugado ()
    {
        int mes = fecha_jugado.get (Calendar.MONTH);
        return mes+1;
    }
    
    
    public int getAnyoJugado ()
    {
        int anyo = fecha_jugado.get (Calendar.YEAR);
        return anyo;
    }
    
    
    public Jugador getJugador1 ()
    {
        return jugador1;
    }
    
    
    public Jugador getJugador2 ()
    {
        return jugador2;
    }
    
    
    public Bono getBonoJugado ()
    {
        return bono_jugado;
    }
   
    
    public int getCantHoraJ1 ()
    {
        return this.cantHoraJ1;
    }
    
    
    public int getCantHoraJ2 ()
    {
        return this.cantHoraJ2;
    }
    
    
    public int getCantHoraBono ()
    {
        return this.cantHoraBono;
    }
    
    
    public boolean equals (Object obj)
    {
        //repasar este metodo porque solo por la fecha es poco para comparar dos objetos
        boolean iguales = false;
        if (obj instanceof DiaJugado){
         DiaJugado d_jug = (DiaJugado) obj; 
           
           if (d_jug.getDiaJugado() == getDiaJugado() && d_jug.getMesJugado() == getMesJugado() && d_jug.getAnyoJugado() == getAnyoJugado()){
               iguales = true;
            } else {
                iguales = false;
            }
        }
        return iguales;
    }
    
    
    public int hashCode ()
    {
        return 9 * this.jugador1.hashCode() + this.bono_jugado.hashCode();
    }
    
    
    public String toString ()
    {
        return " ID: "+getIdString()+" -- Fecha jugado: "+Recursos.convertirFecha(fecha_jugado)+"\n Jugador 1: "+jugador1.getNombre()+" -- Apellido 1: "+jugador1.getApellido()+
               " -- Hora J1: "+getCantHoraJ1()+"\n Jugador 2: "+jugador2.getNombre()+" -- Apellido 2: "+jugador2.getApellido()+" -- Hora J2: "+getCantHoraJ2()+"\n Bono: "+getBonoJugado()+"\n\n";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
