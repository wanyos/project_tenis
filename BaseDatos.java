
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * Clase que gestiona varias listas de la aplicación a modo base de datos
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */

public class BaseDatos implements Serializable
{
   
    private Set<Jugador> lista_jugador;
    private List<DiaJugado> lista_dia_jugado;
    private GregorianCalendar fecha_hoy;
   
    
    
    public BaseDatos()
    {
       lista_jugador = new HashSet<Jugador>();
       lista_dia_jugado = new ArrayList<DiaJugado>();
    }

    
    /**
     * Busca un jugador por su nombre y apellidos
     * @param nombre y apellido del jugador que debe buscar
     * @return si encuentra un jugador con ese nombre y apellido
     */
   public Jugador buscaJugador (String nombre, String apellido)
    {
       Jugador jugador = null;
       boolean encontrado = false;
       Iterator<Jugador> it = lista_jugador.iterator();
       
       while (it.hasNext() && !encontrado) {
           Jugador aux = (Jugador) it.next();
            if (aux.getNombre().equals(nombre) && (aux.getApellido().equals(apellido))) {
                jugador = aux;
                encontrado = true;
            }
        }
       return jugador;
    }
    
    
    public int getNuevoIdJugador ()
    {
        return lista_jugador.size()+1;
    }
    
    
    public int getNuevoIdDiaJugado ()
    {
        return lista_dia_jugado.size()+1;
    }
    
    
    public Set<Jugador> getListaJugador ()
    {
        return this.lista_jugador;
    }
    
    
    public List<DiaJugado> getDiaJugado ()
    {
        return this.lista_dia_jugado;
    }
    
    
    public void setJugador (Jugador j)
    {
        lista_jugador.add (j);
    }
    
    
    public void eliminarJugador (Jugador j)
    {
        lista_jugador.remove (j);
    }
    
   
    public void setDiaJugado (DiaJugado d_jugado)
    {
        lista_dia_jugado.add (d_jugado);
    }
    
    
    public void eliminarDiaJugado (DiaJugado d_jugado)
    {
        lista_dia_jugado.remove (d_jugado);
    }
    
    
    /**
     * Devuelve una lista con todos los dias jugados ya sea en un día, mes o año. Si buscamos por
     * un día, debe de traer el mes y el año. Si lo que queremos es el resumen de un mes, los parámetros
     * traeran solo el mes y año, y lo mismo para un año
     * @param dia, mes y año que se quiere buscar el resumen de todos los jugados
     * @return lista con los datos
     */
    public List<DiaJugado> resumenListaJugado (int dia, int mes, int anyo)
    {
        List<DiaJugado> lista_resumen = new ArrayList<DiaJugado>();
        
         for (DiaJugado aux: lista_dia_jugado){
        if (dia > 0 && mes > 0 && anyo > 0){
                if (aux.getDiaJugado() == dia && aux.getMesJugado() == mes && aux.getAnyoJugado() == anyo){
                   lista_resumen.add (aux);
                }
          } else if (dia <= 0 && mes > 0 && anyo > 0) {
               if (aux.getMesJugado() == mes && aux.getAnyoJugado() == anyo){
                   lista_resumen.add (aux);
                }
            } else if (dia <= 0 && mes <= 0 && anyo > 0) {
                if (aux.getAnyoJugado() == anyo){
                   lista_resumen.add (aux);
                }
            }
        }
        return lista_resumen;
    }
    
    
    /**
     * Recibe una fecha de referencia, dependiendo del valor boolean de cada parámetro se buscaran todos los partidos antes o despues de la
     * fecha actual.
     * @param fecha de referencia para buscar todos los que esten antes de esa fecha o despues de esa fecha, dependiendo de los bolean
     * @return lista con los datos recogidos
     */
    public List<DiaJugado> resumenListaJugado (boolean buscar_antes_hoy, boolean buscar_despues_hoy)
    {
        List<DiaJugado> lista_resumen = new ArrayList<DiaJugado>();
        fecha_hoy = new GregorianCalendar ();
        
        for (DiaJugado aux: lista_dia_jugado) {
            
            if (buscar_antes_hoy) {
                if (aux.getFechaJugado().before (fecha_hoy)) {
                    lista_resumen.add (aux);
                }
             } else if (buscar_despues_hoy) {
                if (aux.getFechaJugado().after (fecha_hoy)) {
                    lista_resumen.add (aux);
                }
             }
        }
        return lista_resumen;
    }
    
    
    /**
     * Busca un dia jugado por su id, si no lo encuentra devuelve null
     * @param id dia jugado
     * @return dia jugado
     */
    public DiaJugado buscarDiaJugado (String id_dia_jugado)
    {
        DiaJugado dia_jugado = null;
        boolean encontrado = false;
        Iterator<DiaJugado> it = lista_dia_jugado.iterator ();
        
        while (it.hasNext() && !encontrado) {
           DiaJugado aux = (DiaJugado) it.next(); 
           if (aux.getIdString().equals(id_dia_jugado)) {
               dia_jugado = aux;
               encontrado = true;
            }
        }
        return dia_jugado;
    }
    
    
    /**
     * Devuelve el bono de un jugador. Se busca en la lista personal de cada jugador. En la primera coincidencia
     * devuelve el bono encontrado. Para saber a que jugador pertenece nos fijamos en el número del socio del jugador
     * @param id del bono a buscar
     * @return bono encontrado por ese id si es que existe
     */
    public Bono buscarBonoId (String num_bono)
    {
        Bono bono = null;
        boolean encontrado = false;
        Iterator<Jugador> it = lista_jugador.iterator();
        
        while (it.hasNext() && !encontrado) {
          Jugador jugador = (Jugador) it.next();  
          Bono aux = jugador.buscarBono (null, num_bono);
            if (bono != null) {
              encontrado = true;  
              bono = aux;  
            }
        }
       return bono;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
