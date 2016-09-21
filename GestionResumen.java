

/**
 * Clase encargada de la gestión de resumen de los datos. Se pueden buscar los dias jugados por un jugador,
 * o todos los partidos jugados en un mes, etc...
 * 
 * @author (Juan José Romero) 
 * @version (1.0  27/08/2016)
 */


public class GestionResumen extends Gestion
{
    

    
    public GestionResumen ()
    {
       
       menuGestionResumen ();
    }

    
     /**
     * Menu principal de la gestión del resumen de datos
     */
    public void menuGestionResumen ()
    {
        String [] datos = {"Mostrar datos de un dia", "Buscar datos de un mes", "Buscar datos de un anyo", "Dias jugados de un jugador"};
        String titulo = "  GESTION RESUMEN DATOS";
        int opcion = menuOpciones (datos, titulo);
        boolean dia = false, mes = false, anyo = false;
       
       switch (opcion) {
           case 1: dia = true; mes = true; anyo = true; datosDia (dia , mes, anyo); break;
           case 2: mes = true; anyo = true; datosDia (dia, mes, anyo); break;
           case 3: anyo = true; datosDia (dia, mes, anyo); break;
           case 4: datosJugador (); break;
        }
    }
    
    
    /**
     * Todos los partidos jugados en un día, mes y año concreto
     */
    private void datosDia (boolean day, boolean month, boolean year)
    {
        int dia = 0, mes = 0, anyo = 0;
      if (day && month && year){
          dia = getGPrincipal().leerInt ("Day");
          mes = getGPrincipal().leerInt ("Month");
          anyo = getGPrincipal().leerInt ("Year");
          getGPrincipal().imprimirMensaje (" Datos del dia "+dia+"/"+mes+"/"+anyo);
          
        } else if (!day && month && year) {
          mes = getGPrincipal().leerInt ("Month");
          anyo = getGPrincipal().leerInt ("Year"); 
          getGPrincipal().imprimirMensaje (" Datos del mes "+mes+"/"+anyo);
          
        } else if (!day && !month && year) {
          anyo = getGPrincipal().leerInt ("Year");
          getGPrincipal().imprimirMensaje (" Datos del anyo "+anyo);
        }
       
        getGPrincipal().pintarLista (Inicio.getBaseDatos().resumenListaJugado (dia, mes, anyo, null)); 
        getGPrincipal().pausaSalir (" Fin de los datos...");
    }
    
    
    /**
     * Listado de lo jugado por un jugador en un mes y año concreto
     */
    private void datosJugador ()
    {
        int mes = 0, anyo = 0;
        getGPrincipal().imprimirMensaje (" Datos de jugador de un mes y anyo...");
        Jugador jugador = pedirJugador ();
        
        if (jugador != null) {
          mes = getGPrincipal().leerInt ("Month");
          anyo = getGPrincipal().leerInt ("Year"); 
          getGPrincipal().imprimirMensaje (" Datos del mes "+mes+"/"+anyo);
          getGPrincipal().pintarLista (Inicio.getBaseDatos().resumenListaJugado (0, mes, anyo, jugador)); 
          getGPrincipal().pausaSalir (" Fin de los datos...");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
