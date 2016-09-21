
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 * Clase dedicada a crear un bono para un jugador
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */


public class Bono implements Serializable
{
    
    private GregorianCalendar fecha_alta;
    private int id, hora_bono;
    private String num_bono;
    private String num_jugador;

    
    public Bono (GregorianCalendar fecha_alta, int id, int horas_bono, String num_bono, String num_jugador)
    {
       this.fecha_alta = fecha_alta;
       this.id = id;
       this.hora_bono = horas_bono;
       this.num_bono = num_bono;
       this.num_jugador = num_jugador;
    }

    
    public String getIdString ()
    {
        return "BN00"+id;
    }
    
    
    public int getId ()
    {
      return this.id; 
    }
   
    
    public GregorianCalendar getFechaAlta ()
    {
        return this.fecha_alta;
    }
    
    
    public int getHoraBono ()
    {
        return this.hora_bono;
    }
    
    
    public String getNumBono ()
    {
        return this.num_bono;
    }
    
    
    public String getNumJugador ()
    {
        return this.num_jugador;
    }
    
    
    /**
     * Aumenta el numero de horas del bono. Por ejemplo cuando se cambia un dia que se iba a jugar
     * y al final no se juega. se devuelve la hora al bono
     */
    public void setHora (int hora_devuelta)
    {
        this.hora_bono = this.hora_bono + hora_devuelta;
    }
    
    
    /**
     * Resta la cantidad de horas del parametro al bono. Pueden ser más de una hora si un jugador
     * juega con otro fuera del sistema o juega un día más de una hora. Si no quedan horas en el 
     * bono no se puede efectuar el cargo en este bono.
     * @param hora a descontar del bono
     * @return false si el número de horas es igual o inferior a cero
     */
    public boolean restarHoraBono (int restar_hora)
    {
        boolean cargo_realizado = false;
        
        if (this.hora_bono > 0){
           this.hora_bono = this.hora_bono - restar_hora;
           cargo_realizado = true;
        }
        return cargo_realizado;
    }
    
    
    public String toString ()
    {
        return " Id: "+getIdString()+" -- Fecha alta: "+Recursos.convertirFecha(getFechaAlta())+"\n Horas bono: "+getHoraBono()+
                     " -- Nº-Bono: "+getNumBono()+" -- Numero jugador: "+getNumJugador()+"\n\n";
    }
    
    
    
    
    
    
    
    
    
   
}
