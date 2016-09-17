
import java.util.GregorianCalendar;

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
        String [] datos = {"Crear dia jugado", "Eliminar dia jugado", "Listar dias de juego"};
        String titulo = "  GESTION DIA JUGAR";
        int opcion = menuOpciones (datos, titulo);
        
        switch (opcion) {
            case 1: crearDiaJugado(); break;
            case 2: eliminarDiaJugado(); break;
            case 3: listarDiaJugado(); break;
        }
    }
    
    
    /**
     * Pide los jugadores que van a jugar para comprobar que estan en el sistema. Si no hay ninguno no se podra crear el
     * día jugado. Se agrega la hora la bono que se diga del jugador. Si no es posible descontar la hora no se creara el día jugado.
     * Despues de crear el día se aumenta en las horas jugadoas a los jugadores y se descuentan las horas al bono.
     */
    private void crearDiaJugado ()
    {
       GregorianCalendar fecha_juego = null;
       Bono bono = null;
       Jugador jugador1 = null, jugador2 = null, jugador_descontar_bono = null;;
       int cantHoraJ1 = 0, cantHoraJ2 = 0, cantHoraBono = 0;
       String nombre_anonimo, apellido_anonimo;
       
       getGPrincipal().imprimirMensaje ("Seleccionar jugador1...");
       jugador1 = pedirJugador ();
        if (jugador1 != null) {
           cantHoraJ1 = descontarHoraJugador (jugador1);
        } else {
           jugador1 = new Jugador (datos_leidos.get(0), datos_leidos.get(1));
        }
        
       getGPrincipal().imprimirMensaje ("Seleccionar jugador2...");
       jugador2 = pedirJugador ();
        if (jugador2 != null) {
           cantHoraJ2 = descontarHoraJugador (jugador2);
        } else {
            jugador2 = new Jugador (datos_leidos.get(0), datos_leidos.get(1));
        }
       
        
       if (jugador1 != null || jugador2 != null){
          getGPrincipal().imprimirMensaje ("Seleccionar jugador descontar hora bono...");
          jugador_descontar_bono = pedirJugador ();
          bono = pedirBonoJugador (jugador_descontar_bono);
          cantHoraBono = ejecutarDescontarHoraBono (jugador_descontar_bono, bono); 
          getGPrincipal().imprimirMensaje ("Fecha para el dia jugado...");
          fecha_juego = pedirFecha ();
          
          DiaJugado dia_jugado = new DiaJugado (jugador1, jugador2, bono, fecha_juego, cantHoraJ1, cantHoraJ2, cantHoraBono);
          Inicio.getBaseDatos().setDiaJugado (dia_jugado);
          getGPrincipal().pausaSalir ("Dia jugado creado correctamente...");
        } else {
          getGPrincipal().pausaSalir ("No es posible seguir ningun jugador pertenece al sistema...");
        }
    }
    
    
    /**
     * Descuenta la hora o las horas que se quieran aplicar a un jugador
     * @param jugador del sistema
     */
    private int descontarHoraJugador (Jugador jugador)
    {
        int cantidad_hora_descontar = getGPrincipal().leerInt ("Numero de horas a descontar a "+jugador.getNombre()+"");
        if (cantidad_hora_descontar > 0) {
            jugador.restarHoraContador (cantidad_hora_descontar);
            getGPrincipal().imprimirMensaje ("Hora descontada al contador del jugador "+jugador.getNombre());
        } else {
         getGPrincipal().pausaSalir ("No es posible seguir el numero de hora no es valido...");   
       }
       return cantidad_hora_descontar;
    }
    
    
    /**
     * Pedir jugador que se le descontara la hora de juego. Si el jugador no existe no es posible seguir. Si no tienes horas
     * suficientes no se podra seguir. Si todo correcto se descuenta la hora de juego y se guarda el día de juego
     */
    private Bono pedirBonoJugador (Jugador jugador_descontar_bono)
    {
        Bono bono = null;

         if (jugador_descontar_bono != null) {
              getGPrincipal().pintarLista (jugador_descontar_bono.getListaBono());
              bono = buscarBonoJugador (jugador_descontar_bono);
              
                 if (bono == null || bono.getHoraBono() < 0) {
                     getGPrincipal().pausaSalir ("No es posible seguir el bono no tiene sufientes horas o no existe...");  
                 } 
                 
            } else {
               getGPrincipal().pausaSalir ("No es posible seguir el jugador no existe o no es correcto...");  
            }
         return bono;
    }
    
    
    private int ejecutarDescontarHoraBono (Jugador jug_descontar_hora_bono, Bono bono)
    {
       int hora_descontada = 0;
       hora_descontada = confirmarDescontarHoraBono (bono);
       boolean correcto_restar_bono = jug_descontar_hora_bono.restarHoraBono (bono, hora_descontada);
                   
         if (correcto_restar_bono) {
            getGPrincipal().imprimirMensaje ("Hora descontada del bono "+bono.getIdString()+" del jugador "+jug_descontar_hora_bono.getNombre());  
         } else {
            getGPrincipal().pausaSalir ("Ocurrio un error si no se pudo cargar la hora en el bono...");    
         }
       return hora_descontada;
    }
    
    
    /**
     * Pedir al usuario que seleccione un id de bono y su numero para buscar un bono de un jugador. Si el bono no tiene horas disponibles se
     * devuelve el bono nulo.
     * @param Jugador que se debe buscar sus bonos
     * @return bono del jugador
     */
    private Bono buscarBonoJugador (Jugador jug_descontar_hora)
    {
       Bono bono = null;
       String id_bono = getGPrincipal().leerDatoUsuario ("Escriba id del bono que quiera descontar la hora");
        bono =  jug_descontar_hora.buscarBono (id_bono, null);
         
         if (bono.getHoraBono() <= 0){
             bono = null;
            }
  
         return bono;
    }
    
    
    /**
     * Confirma si descontar horas del bono se puede llevar a cabo y cuantas horas se van a descontar
     * @param bono del que se descontaran las horas en casa de que exista sufiente crédito de horas
     * @return número de horas a descontar
     */
    private int confirmarDescontarHoraBono (Bono bono)
    {
        int cantidad_hora = getGPrincipal().leerInt ("Numero de horas a descontar del bono");
        
         if (cantidad_hora > 0) {
               boolean respuesta = getGPrincipal().preguntarSiNo ("Descontar "+cantidad_hora+" hora del bono S/N: ");
               
                     if (!respuesta) {
                           getGPrincipal().pausaSalir ("Accion cancelada...");    
                           cantidad_hora = 0; 
                        } 
                               
            } else {
                 getGPrincipal().pausaSalir ("El valor de hora descontar no es correcto...");    
                 cantidad_hora = 0;
            }
        return cantidad_hora;
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
        
        if (dia_jugado != null) {
           Jugador jugador1 = dia_jugado.getJugador1 ();
           int cant_hora_jugador1 = dia_jugado.getCantHoraJ1 ();
           jugador1.setContador (cant_hora_jugador1);
           
           Jugador jugador2 = dia_jugado.getJugador2 ();
           int cant_hora_jugador2 = dia_jugado.getCantHoraJ2 ();
           jugador2.setContador (cant_hora_jugador2);
           
           Bono bono_jugador = dia_jugado.getBonoJugado ();
           int cant_hora_bono = dia_jugado.getCantHoraBono ();
           bono_jugador.setHora (cant_hora_bono);
           
           Inicio.getBaseDatos().eliminarDiaJugado (dia_jugado);
           getGPrincipal().pausaSalir ("Dia jugado eliminado y valores restablecidos...");    
        } else {
           getGPrincipal().pausaSalir ("El dia jugado no existe o no es correcto...");  
        }
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
        getGPrincipal().pintarLista (Inicio.getBaseDatos().getDiaJugado());
        getGPrincipal().pausaSalir (" Fin de los datos..."); 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
