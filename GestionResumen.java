

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
       
       switch (opcion) {
           case 1: datosDia(); break;
           case 2: datosMes(); break;
           case 3: datosAnyo(); break;
           case 4: datosJugador(); break;
        }
    }
    
    
    
    private void datosDia ()
    {
        
    }
    
    
    
    private void datosMes ()
    {
        
    }
    
    
    private void datosAnyo ()
    {
        
    }
    
    
    private void datosJugador ()
    {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
