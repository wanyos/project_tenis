import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.text.DecimalFormat; 

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
       int opcion = 0;
       do {
        String [] datos = {"Mostrar datos de un dia", "Buscar datos de un mes", "Buscar datos de un anyo", "Dias jugados de un jugador", "Historial de un bono"};
        String titulo = "  GESTION RESUMEN DATOS";
        String salir = null;
        opcion = menuOpciones (datos, titulo);
        boolean dia = false, mes = false, anyo = false;
       
         switch (opcion) {
           case 1: dia = true; mes = true; anyo = true; datosDia (dia , mes, anyo); break;
           case 2: mes = true; anyo = true; datosDia (dia, mes, anyo); break;
           case 3: anyo = true; datosDia (dia, mes, anyo); break;
           case 4: datosJugador (); break;
           case 5: historialBono (); break;
          }
          
          if (opcion == (int)'s' || opcion == (int)'S'){
			  break;
		     }
          
        } while (opcion >= 1 || opcion <= 4);
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
       
        getGPrincipal().pintarLista (Inicio.getBaseDatos().resumenListaJugado (dia, mes, anyo)); 
        getGPrincipal().pausaSalir (" Fin de los datos...");
    }
    
    
    /**
     * Listado de lo jugado por un jugador en un mes y año concreto
     */
    private void datosJugador ()
    {
        int mes = 0, anyo = 0;
        List<DiaJugado> lista_resumen_jugado_mes = new ArrayList<DiaJugado>();
        getGPrincipal().imprimirMensaje (" Datos de jugador de un mes y anyo...");
        
        try {
          Jugador jugador = pedirJugadorNombre ();    
          mes = getGPrincipal().leerInt ("Month");
          anyo = getGPrincipal().leerInt ("Year"); 
          getGPrincipal().imprimirMensaje (" Datos del mes "+mes+"/"+anyo);
          lista_resumen_jugado_mes = Inicio.getBaseDatos().resumenListaJugado (mes, anyo, jugador);
          
          if (!lista_resumen_jugado_mes.isEmpty()) {
          getGPrincipal().pintarLista (lista_resumen_jugado_mes); 
          totalHorasJugadosMes (lista_resumen_jugado_mes, jugador);
          totalHorasJugadasAnyo (anyo, jugador);
          totalBonoGastadoAnyo (anyo, jugador);
        } 
          getGPrincipal().pausaSalir (" Fin de los datos...");
          
        } catch (NullPointerException e){
           getGPrincipal().pausaSalir (" !!!El jugador no ha sido encontrado... "); 
        }  catch (Exception e) {
           getGPrincipal().pausaSalir (" !!!Se ha producido un error inesperado... ");
        }
    }
    
    
    /**
     *  Total de horas jugadas en el mes que se pregunta
     *  @param lista de los datos de un jugador en un mes y anyo concreto
     *  @return suma total de las horas de ese mes
     */
    private void totalHorasJugadosMes (List<DiaJugado> lista_resumen_jugado, Jugador jugador) throws NullPointerException
    {
        int total_horas_jugadas_mes = 0;
        
          for (DiaJugado dia_aux: lista_resumen_jugado) {
             if (dia_aux.getJugador1().equals(jugador)) {
                total_horas_jugadas_mes += dia_aux.getCantHoraJ1();
                
             } else if (dia_aux.getJugador2().equals(jugador)) {
                total_horas_jugadas_mes += dia_aux.getCantHoraJ2();
             }
          }
         getGPrincipal().imprimirMensaje (" Horas total del mes: "+total_horas_jugadas_mes);
         mediaHoraMes (lista_resumen_jugado, total_horas_jugadas_mes);
    }
    
    
    /**
     *  Media de horas jugadas en un mes.
     *  @return media aritmética de horas jugadas en un mes
     */
    private void mediaHoraMes (List<DiaJugado> lista_resumen_jugado, int total_horas_jugadas_mes) throws NullPointerException
    {
      GregorianCalendar fecha_jugado = lista_resumen_jugado.get(0).getFechaJugado(); 
      DecimalFormat df = new DecimalFormat("0.00"); 
      float resultado = (float) total_horas_jugadas_mes/Recursos.getNumeroDiasMes (fecha_jugado);

      getGPrincipal().imprimirMensaje (" Media de horas jugadas al mes: "+df.format(resultado));  
    }
    
   
    /**
     * Total de horas jugadas en el año que se pregunta
     * @return suma total de horas del año que se pregunta
     */
    private void totalHorasJugadasAnyo (int anyo, Jugador jugador)
    {
        int total_horas_anyo = 0;
        List<DiaJugado> lista_jugado_anyo = Inicio.getBaseDatos().resumenListaJugado (0, anyo, jugador);

        for (DiaJugado dia_aux: lista_jugado_anyo) {
            if (dia_aux.getJugador1().equals(jugador)) {
               total_horas_anyo += dia_aux.getCantHoraJ1();
             } else if (dia_aux.getJugador2().equals(jugador)) {
                 total_horas_anyo += dia_aux.getCantHoraJ2();
             }
          }
        getGPrincipal().imprimirMensaje (" Horas total del anyo: "+total_horas_anyo);
        mediaHoraAnyo (total_horas_anyo);
    }
    
    
     /**
     *  Media de horas jugadas en un año
     *  @return media aritmética de horas jugadas en el año
     */
    private void mediaHoraAnyo (int total_horas_anyo)
    {
       DecimalFormat df = new DecimalFormat("0.00"); 
       float resultado = (float) total_horas_anyo/365;
       
       getGPrincipal().imprimirMensaje (" Media de horas jugadas al anyo: "+df.format(resultado));  
    }
    
    
    /**
     *  Total bonos gastados en el año que se pregunta
     *  @return suma total de los bonos
     */
    private void totalBonoGastadoAnyo (int anyo, Jugador jugador)
    {
       int total_bonos_gastados_anyo = 0;
       for (Bono bono_aux: jugador.getListaBono()) {
         
           if ((anyo == Recursos.devolverNumeroFecha (bono_aux.getFechaAlta(), 0, 0, 1)) && bono_aux.getHoraBono() <= 0) {
               total_bonos_gastados_anyo += 1;
            }
        }
        getGPrincipal().imprimirMensaje (" Total bonos gastados anyo "+anyo+": "+total_bonos_gastados_anyo);  
    }
    
    
    /** 
     * Resumen del historial que ha tenido o tiene un bono
     */
    private void historialBono ()
    {
        List<DiaJugado> lista_cargados_bono = new ArrayList<DiaJugado>();
       try { 
        String numero_bono = getGPrincipal().leerDatoUsuario ("Numero de bono");
        Bono bono = Inicio.getBaseDatos().buscarBonoNum (numero_bono);
        
        lista_cargados_bono = Inicio.getBaseDatos().diasJugadosBono (bono);
        getGPrincipal().pintarLista (lista_cargados_bono); 
        
       } catch (NullPointerException e) {
         getGPrincipal().imprimirMensaje (" Bono no existe o no ha sido encontrado...");      
       }
       getGPrincipal().pausaSalir (" Fin de los datos...");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
