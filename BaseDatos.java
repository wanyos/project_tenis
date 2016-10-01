
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Collection;


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
     * Se guardan en la lista todos los jugadores con el contador en negativo.
     * @return lista de jugadores con el contador en negativo.
     */
    public Set<Jugador> listaJugadorContadorNegativo ()
    {
       Set<Jugador> lista_jugador_negativo = new HashSet<Jugador>(); 
       
       for (Jugador jugador_aux: lista_jugador) {
           if (jugador_aux.getContador() <= 0) {
               lista_jugador_negativo.add (jugador_aux);
            }
        }
       
       return lista_jugador_negativo;
    }
    
    
    /**
     * Lista de bonos activos del sistema.
     * @return lista de todos los bonos activos en el momento de la consulta
     */
    public List<Bono> listaBonoActivos ()
    {
        List<Bono> lista_bono_activo = new ArrayList<Bono>();
        
        for (Jugador jugador_aux: lista_jugador) {
            for (Bono bono_aux: jugador_aux.getListaBono()) {
              if (bono_aux.getHoraBono() > 0) {
                  lista_bono_activo.add (bono_aux);
                }
            }
        }
        
        return lista_bono_activo;
    }
    
    
    public boolean exportListaJugador ()
    {
        boolean exportado = false;
        exportado = Archivo.exportarColeccionDatos (this.lista_jugador, null, "lista_jugador.txt");
        return exportado;
    }
    
    
     public boolean exportListaDiaJugado ()
    {
        boolean exportado = false;
        exportado = Archivo.exportarColeccionDatos (null, this.lista_dia_jugado, "lista_dia_jugado.txt");
        return exportado;
    }
    
    
    public boolean importarListaJugador ()
    {
        boolean importado = false;
        Object importar_objeto = null;
        
        try{
           importar_objeto = Archivo.importarObjeto ("lista_jugador.txt");
               if (importar_objeto instanceof HashSet) {
                lista_jugador =  (Set<Jugador>) importar_objeto;
                }
        } catch (Exception e) {
            
        }
        return importado; 
    }
    
    
     public boolean importarListaDiaJugado ()
    {
        boolean importado = false;
        Object importar_objeto = null; 
        importar_objeto = Archivo.importarObjeto ("lista_dia_jugado.txt");
        
        if (importar_objeto instanceof ArrayList) {
            lista_dia_jugado = (List<DiaJugado>) importar_objeto;
        }
        return importado; 
    }
    
    
    
    /*
     * 
     *Guarda los datos del sistema en un archivo persistente 
     *
    public boolean exportDatosSistema ()
    {
      boolean exportado = false;
     
      Collection<Object> datos_exportar = new ArrayList<Object>();
      datos_exportar.add (lista_jugador);
      datos_exportar.add (lista_dia_jugado);
     
      exportado = Archivo.exportarDatos (datos_exportar);
      
      return exportado;
    }
    
    
    
     /**
      * 
      *Importa los datos del archivo persistente al sistema. Importara la lista de jugador y la lista dia jugado
     
    public boolean importDatosSistema ()
    {
       boolean importar = false;
       Collection<Object> datos_importados = new ArrayList<Object>();
       datos_importados = Archivo.importarDatos ();
       
       if (datos_importados != null) {
           for (Object obj: datos_importados) {
               
              if (obj instanceof HashSet) {
                   for (Jugador jug: (HashSet<Jugador>) obj) {
                       lista_jugador.add (jug);
                    }
                    
                } else if (obj instanceof ArrayList) {
                    for (DiaJugado dia_jug: (ArrayList<DiaJugado>) obj) {
                        lista_dia_jugado.add (dia_jug);
                    }
                }
            }
           importar = true;
        }
       return importar;
    }
    */
    
    /**
     * Busca un jugador por su nombre y apellidos o por su número de socio.
     * @param nombre y apellido del jugador que debe buscar
     * @return si encuentra un jugador con ese nombre y apellido
     */
   public Jugador buscaJugador (String nombre, String apellido, String num_socio)
    {
       Jugador jugador = null;
       boolean encontrado = false;
       Iterator<Jugador> it = lista_jugador.iterator();
       
       while (it.hasNext() && !encontrado) {
           Jugador aux = (Jugador) it.next();
            if (num_socio == null && aux.getNombre().equals(nombre) && (aux.getApellido().equals(apellido))) {
                jugador = aux;
                encontrado = true;
            } else if (nombre == null && apellido == null && aux.getNumSocio().equals(num_socio)) {
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
    
    
    public List<DiaJugado> getListaDiaJugado ()
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
     * traeran solo el mes y año, y lo mismo para un año. Otra opción es buscar los dias jugados por un jugador en un mes y año concreto.
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
     *  Lista resumen de los datos de un jugador ya sea por un mes o un año concreto
     *  @param mes y año de la consulta
     *  @return lista con los datos encontrados
     */
    public List<DiaJugado> resumenListaJugado (int mes, int anyo, Jugador jugador)
    {
        List<DiaJugado> lista_resumen = new ArrayList<DiaJugado>();
        
        for (DiaJugado aux: lista_dia_jugado) {
         if (mes > 0 && anyo > 0 && jugador != null) {
            if ((aux.getJugador1().equals(jugador) || aux.getJugador2().equals(jugador)) && aux.getMesJugado() == mes && aux.getAnyoJugado() == anyo) {
                   lista_resumen.add (aux); 
                }
                
            } else if (mes <= 0 && anyo > 0 && jugador != null) {
                if ((aux.getJugador1().equals(jugador) || aux.getJugador2().equals(jugador)) && aux.getAnyoJugado() == anyo) {
                    lista_resumen.add (aux);
                }
            }
        }
        return lista_resumen;
    }
    
    
    /**
     * Dependiendo del valor boolean de cada parámetro se buscaran todos los partidos antes o despues de la fecha actual.
     * @param bolean true o false para buscar todos los dias jugados antes o despues de la fecha actual
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
     * Devuelve el bono de un jugador. Se busca en la lista de bonos del jugador por el número de bono, que sera un número único de cada bono.
     * @param id del bono a buscar o número del bono
     * @return bono encontrado por ese id o número de bono si es que existe
     */
    public Bono buscarBonoNum (String num_bono)
    {
        Bono bono = null;
        boolean encontrado = false;
        Iterator<Jugador> it = lista_jugador.iterator();
        
        while (it.hasNext() && !encontrado) {
          Jugador jugador = (Jugador) it.next();  
          Bono aux = jugador.buscarBono (null, num_bono);
            if (aux != null) {
              encontrado = true;  
              bono = aux;  
            }
        }
       return bono;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
