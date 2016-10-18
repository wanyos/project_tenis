
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

/**
 * Se encarga de toda la gestión de los dias jugados por los jugadores. Crea y elimina los dias que se
 * juegan. Se cargan los datos a cada jugador y a cada bono de un jugador.
 * 
 * 
 * @author (Juan José Romero) 
 * @version (1.0  27/08/2016)
 */


public class GestionDiaJugar extends Gestion
{
    

    public GestionDiaJugar ()
    {
       menuGestionDiaJugar ();
    }

    
     /**
     * Menu principal de la gestión de jugar un dia
     */
    public void menuGestionDiaJugar ()
    {
        int opcion = 0;
        do {
          String [] datos = {"Crear dia jugado", "Eliminar dia jugado", "Listar dias de juego"};
          String titulo = "  GESTION DIA JUGAR";
          opcion = menuOpciones (datos, titulo);
        
          switch (opcion) {
            case 1: crearDiaJugado(); break;
            case 2: eliminarDiaJugado(); break;
            case 3: listarDiaJugado(); break;
           }
           
          if (opcion == 115 || opcion == 83){
			  break;
		     }
       } while (opcion >= 1 || opcion <= 3);
    }
    
    
    /**
     * Crea un dia de juego entre dos jugadores del sistema o solo uno con otro jugador que no lo es.
     * Descuenta las horas de juego a cada jugador. Pide el jugador que pagara con su bono las horas de juego.
     * Se descuentan las horas de juego. Se pide la fecha del partido.
     */
     private void crearDiaJugado ()
    {
        GregorianCalendar fecha_juego = null;
        Jugador jugador_descontar_bono = null;
        int cantidad_hora_bono = 0;
        Bono bono = null;
        Jugador [] jugadores = new Jugador [2];
        int [] hora_juego_jugadores = null;
        boolean validar_horas_descontadas = false;
        
       try {
         jugadores = pedirJugadoresDiaJugar();
         hora_juego_jugadores = pedirHoraJuegoJugadores (jugadores);
         
         jugador_descontar_bono = pedirJugadorDescontarBono();   
         bono = pedirBonoJugador (jugador_descontar_bono);
         Bono bono_copia = (Bono) bono.clone();
         cantidad_hora_bono = pedirHoraDescontarBono ();  
         
         getGPrincipal().imprimirMensaje ("Fecha para el dia jugado...");
         fecha_juego = pedirFecha ();
         
         DiaJugado dia_jugado = new DiaJugado (jugadores[0], jugadores[1], bono_copia, fecha_juego, hora_juego_jugadores[0], hora_juego_jugadores[1], cantidad_hora_bono);
         Inicio.getBaseDatos().setDiaJugado (dia_jugado);
         
         descontarHorasJugadores (hora_juego_jugadores, jugadores);
         descontarHoraBono (jugador_descontar_bono, bono, cantidad_hora_bono);  
         getGPrincipal().pausaSalir ("Dia jugado creado correctamente...");
         
       } catch (ExcepcionPropia e) {
         getGPrincipal().pausaSalir (e.getMessage());
       } catch (NullPointerException e) {
          getGPrincipal().pausaSalir ("!!! Error, jugador o bono incorrecto...");   
       } catch (ArrayIndexOutOfBoundsException e) {
          getGPrincipal().pausaSalir ("!!!Error, los jugadores no son correctos (array fuera de los limites)...");    
       }
    }
    
    
    /**
     * Pide los jugadores que van a jugar para comprobar que estan en el sistema. Si no hay ninguno no se podra crear el
     * día jugado. Si existe uno de ellos el otro sera un jugador externo al sistema y se crea un jugador auxiliar.
     */
    private Jugador[] pedirJugadoresDiaJugar ()
    {
      Jugador [] jugadores = new Jugador [2];
      Jugador jugador1 = null, jugador2 = null;
      
       getGPrincipal().imprimirMensaje ("Seleccionar jugador1...");
       jugador1 = pedirJugadorNombre();
     
       getGPrincipal().imprimirMensaje ("Seleccionar jugador2...");
       jugador2 = pedirJugadorNombre();
       
       if (jugador1 == null && jugador2 == null) {
              jugadores = null;
        } else {
             if (jugador1 != null && jugador2 == null) {
                   jugador2 = new Jugador (datos_leidos.get(0), datos_leidos.get(1));
            
              } else if (jugador1 == null && jugador2 != null) {
                   jugador1 = new Jugador (datos_leidos.get(0), datos_leidos.get(1));
              }
           jugadores[0] = jugador1;
           jugadores[1] = jugador2;
        }
        return jugadores;
    }
    
    
    /**
     * Pide las horas que jugaran cada jugador
     * @return array con dos elementos que seran las horas correspondientes a descontar a cada jugador
     */
    private int [] pedirHoraJuegoJugadores (Jugador [] jugadores) throws ExcepcionPropia
    {
       int [] horas_juego_jugadores = new int [2];
       horas_juego_jugadores [0] = getGPrincipal().leerInt ("Numero de horas a descontar a "+jugadores[0].getNombre()+"");
       horas_juego_jugadores [1] = getGPrincipal().leerInt ("Numero de horas a descontar a "+jugadores[1].getNombre()+"");
       
       return horas_juego_jugadores;
    }
    
    
    /**
     * Descuenta las horas de juego a cada jugador en sua contador personal
     */
    private void descontarHorasJugadores (int [] horas_juego_jugadores, Jugador [] jugadores) throws ExcepcionPropia
    {
         descontarHoraJugador (horas_juego_jugadores[0], jugadores[0]);
         descontarHoraJugador (horas_juego_jugadores[1], jugadores[1]);
    }
    
    
    /**
     * Jugador que se le descontara la hora o las horas de algún bono que tenga activo
     * @return jugador que pagara la hora de su bono
     */
    private Jugador pedirJugadorDescontarBono ()
    {
        Jugador jugador_descontar_bono = null;
        boolean repetir = false;
        
        do{
        getGPrincipal().imprimirMensaje ("Seleccionar jugador descontar hora bono...");
        jugador_descontar_bono = pedirJugadorNombre();
        
         if (jugador_descontar_bono == null) {
              repetir = getGPrincipal().preguntarSiNo ("El jugador no es correcto o no existe, volver a intentar S/N: ");
            }
        
        } while (repetir && jugador_descontar_bono == null);
        
        return jugador_descontar_bono;
    }
   
    
    /**
     * Descuenta la hora o las horas que se quieran aplicar a un jugador
     * @param jugador del sistema
     */
    private void descontarHoraJugador (int cantidad_hora_descontar, Jugador jugador) throws ExcepcionPropia
    {
        if (cantidad_hora_descontar > 0 && cantidad_hora_descontar <= 2) {
            jugador.restarHoraContador (cantidad_hora_descontar);
            getGPrincipal().imprimirMensaje (jugador.informarContadorJugadorNegativo());
        } else {
            throw new ExcepcionPropia ("!!!Error, el valor maximo de horas a descontar a un jugador es 2 horas...");
        }
    }
    
    
    private void descontarHoraBono (Jugador jug_descontar_hora_bono, Bono bono, int cantidad_hora_descontar) 
    { 
       boolean correcto_restar_bono = jug_descontar_hora_bono.restarHoraBono (bono, cantidad_hora_descontar);
            if (correcto_restar_bono) {
               getGPrincipal().imprimirMensaje (jug_descontar_hora_bono.informarBonoAgotado(bono));
            }
        
    }
    
    
    private int pedirHoraDescontarBono () throws ExcepcionPropia
    {
       int cantidad_hora_descontar = getGPrincipal().leerInt ("Numero de horas a descontar del bono"); 
          if (cantidad_hora_descontar > 0 && cantidad_hora_descontar <= 2) {
        } else {
          throw new ExcepcionPropia ("!!!Error, el valor maximo de horas de un bono es 2 horas...");   
        }
       return cantidad_hora_descontar;
    }
    
    
    /**
     * Pide el jugador que se le descontara la hora de juego. Despues un bono de los que ese jugador tenga activo.
     * Si el bono no está activo devolvera null.
     */
    private Bono pedirBonoJugador (Jugador jugador_descontar_bono)
    {
        Bono bono = null;
        boolean repetir = false;
        
           getGPrincipal().pintarLista (jugador_descontar_bono.getListaBonoActivo());
        do {
            String id_bono = getGPrincipal().leerDatoUsuario ("Escriba id del bono que quiera descontar la hora");
            bono = jugador_descontar_bono.buscarBono (id_bono, null);
            if (bono == null) {
               repetir = getGPrincipal().preguntarSiNo ("Error el bono no es correcto, volver a intentar S/N: "); 
            }
        } while (repetir && bono == null);
        
        if (bono == null) {
            throw new NullPointerException ();
        }
         return bono;
    }
    
    
    /**
     * Elimina un día que se habia creado para jugar y no se ha jugado. Se devulven las horas a los jugadores y al bono que se
     * desconto la o las horas.
     */
    private void eliminarDiaJugado ()
    {
        DiaJugado dia_jugado = null;
        String id_dia_jugado = getGPrincipal().leerDatoUsuario ("Escriba id del dia jugado que quiere eliminar"); 
   
        dia_jugado = Inicio.getBaseDatos().buscarDiaJugado (id_dia_jugado);
        
       try {
           devolverHoraJugador (dia_jugado);
           devolverHoraBono (dia_jugado);
           Inicio.getBaseDatos().eliminarDiaJugado (dia_jugado);
           getGPrincipal().pausaSalir ("Dia jugado eliminado y valores restablecidos...");    
           
        } catch (NullPointerException e) {
           getGPrincipal().pausaSalir ("El dia jugado no existe o no es correcto...");  
        }
    }
    
    
    /**
     * Antes de eliminar un día jugado se deben devolver las horas de juego a sus jugadores y las horas
     * de juego al bono donde se cargo el día de juego
     */
    private void devolverHoraJugador (DiaJugado dia_jugado) 
    {
		boolean contador_jugador1_modificado = false, contador_jugador2_modificado = false;
		
        Jugador jugador1 = dia_jugado.getJugador1 ();
        int cant_hora_jugador1 = dia_jugado.getCantHoraJ1 ();
		contador_jugador1_modificado = Inicio.getBaseDatos().modificarContadorJugador (jugador1, cant_hora_jugador1);
        
        Jugador jugador2 = dia_jugado.getJugador2 ();
        int cant_hora_jugador2 = dia_jugado.getCantHoraJ2 ();
        contador_jugador2_modificado = Inicio.getBaseDatos().modificarContadorJugador (jugador2, cant_hora_jugador1);
        
    }
    
    
    private void devolverHoraBono (DiaJugado dia_jugado)
    {
        boolean jugador_bono_modificado = false;
        Bono bono_jugador = dia_jugado.getBonoJugado ();
        int cant_hora_bono = dia_jugado.getCantHoraBono ();
        jugador_bono_modificado = Inicio.getBaseDatos().modificarContadorBonoJugador (bono_jugador, cant_hora_bono);
    }
    
    
    /**
     * Lista los dias que existen en la lista de dias jugados. Se ofrecen dos vistas, una los dias que estan por jugar y otra los 
     * dias ya jugados
     */
    private void listarDiaJugado ()
    {
       String [] datos = {"Dias jugados pasados", "Dias por jugar", "Listado completo"};  
       String titulo = "  SELECCIONE UNA OPCION";
       int opcion = menuOpciones (datos, titulo);
       
       if (opcion == 1) {
           listaDiasJugadosPasados ();
        } else if (opcion == 2) {
            listaDiasPorJugar ();
        } else if (opcion == 3) {
            listaCompletaDiasJugados ();
        }
    }
    
    
    /**
     * Pide a la base de datos que busque en el listado de dias jugados todos los dias que se hayan jugado antes de la fecha que 
     * se le pide al usuario. Si la fecha es correcta devolvera un listado con todos esos dias que se imprimen en pantalla
     * @return listado con todos los dias antes de la fecha pasada
     */
    private void listaDiasJugadosPasados ()
    {
        getGPrincipal().pintarLista (Inicio.getBaseDatos().resumenListaJugado (true, false));
        getGPrincipal().pausaSalir (" Fin de los datos..."); 
    }
    
    
    /**
     * Pide a la base de datos que busque een el listado de dias jugados todos los dias que estan por jugar desde la fecha que le 
     * pasamos. Si la fecha es correcta nos devolvera un listado con todos esos dias que se imprimira en pantalla
     * @return listado con todos los dias antes de la fecha pasada
     */
    private void listaDiasPorJugar ()
    {
        getGPrincipal().pintarLista (Inicio.getBaseDatos().resumenListaJugado (false, true));
        getGPrincipal().pausaSalir (" Fin de los datos..."); 
    }
    
    
    private void listaCompletaDiasJugados ()
    {
        getGPrincipal().imprimirMensaje ("  DIAS JUGADOS");
        getGPrincipal().pintarLista (Inicio.getBaseDatos().getListaDiaJugado());
        getGPrincipal().pausaSalir (" Fin de los datos..."); 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
