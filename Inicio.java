

/**
 * Inicia la aplicación con el menú de opciones.
 *  Activa de los bonos activos y jugadores con saldo negativo.
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
        
        try {
          base_datos.importarListaJugador();
          base_datos.importarListaDiaJugado();
        } catch (Exception e) {
         System.out.println (" !!!Error al importar los archivos del sistema... ");
        }
       
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
          int opcion = 0;
          grafica_principal.cabeceraInicio ();
          grafica_principal.imprimirMensaje (" Lista jugadores contador negativo...");
          grafica_principal.pintarListaJugador (base_datos.listaJugadorContadorNegativo ());
          
          grafica_principal.imprimirMensaje (" Lista bonos activos a fecha de hoy...");
          grafica_principal.pintarLista (base_datos.listaBonoActivos());
          
        do {
            opcion = grafica_principal.menuInicio ();
            
             switch (opcion) {
               case 1: new GestionJugador (); break;
               case 2: new GestionDiaJugar (); break;
               case 3: new GestionResumen (); break;
               case 4: guardarDatosSistema(); break;
            }
            
            if (opcion == (int)'s' || opcion == (int)'S'){
              guardarDatosSistema ();   
			  break;
		  }
        } while (opcion >= 1 || opcion <= 4);
    }

    
    
    private void guardarDatosSistema ()
    {
         boolean exportado_lis_jugador = this.base_datos.exportListaJugador ();
         boolean exportado_lis_dia_jugado = this.base_datos.exportListaDiaJugado ();
              if (exportado_lis_jugador && exportado_lis_dia_jugado) {
                  grafica_principal.imprimirMensaje ("  Datos exportados a archivo...");
                } else {
                  grafica_principal.imprimirMensaje (" !!!Error datos no exportados a archivo..."); 
                }
    }
    
   
}
