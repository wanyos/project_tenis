


/**
 * Clase principal donde se inicia la aplicacion
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */


public class Inicio
{
    
    private GraficaPrincipal grafica_principal;
    private GestionResumen g_resumen;
    private GestionDiaJugar g_dia_jugar;
    private GestionJugador g_jugador;
    private static BaseDatos base_datos;
    
    public static void main (String [] args)
    {
        base_datos = new BaseDatos();
        new Inicio ();
    }

    
    /**
     * Desde este método se devuelve el objeto base_datos. Solo se inicia una vez al arrancar la aplicación
     * Se mantiene un solo objeto para los cambios se sufriran las listas de esta clase y que son esenciales 
     * en la aplicación.
     * @return objeto base_datos
     */
    public static BaseDatos getBaseDatos ()
    {
        return base_datos;
    }
   
    
    public Inicio()
    {
        grafica_principal = new GraficaPrincipal ();
        menuPrincipal ();
    }
    
    
    /**
     * Menu principal de la aplicacion
     */
    private void menuPrincipal ()
    {
        cargarDatos ();
        int opcion = 0;
          grafica_principal.cabeceraInicio ();
        do {
            opcion = grafica_principal.menuInicio ();
            
             switch (opcion) {
               case 1: new GestionJugador (); break;
               case 2: new GestionDiaJugar (); break;
               case 3: new GestionResumen (); break;
            }
            
            if (opcion == 115 || opcion == 83){
			  break;
		  }
        } while (opcion >= 1 || opcion < 3);
    }

    
    private void cargarDatos ()
    {
        Jugador juanjo = new Jugador ("juanjo", "romero", "52950980N", "615615529", "12568");
        juanjo.comprarBono (10, "123456", "12568");
         Inicio.getBaseDatos().setJugador (juanjo);
         
         Jugador manolo = new Jugador ("manolo", "vega", "51930680N", "612617529", "12570");
         manolo.comprarBono (10, "123", "12590");
         Inicio.getBaseDatos().setJugador (manolo);
    }
    
   
}
