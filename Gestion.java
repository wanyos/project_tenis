
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Clase encargada de toda la gestion lógica de la aplicación
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */


public abstract class Gestion
{
    private GraficaPrincipal g_principal;
    protected List<String> datos_leidos;

    
    public Gestion ()
    {
      g_principal = new GraficaPrincipal ();
      datos_leidos = new ArrayList<String>();
    }

   
    /**
     * Devuelve el objeto gráfica principal que esta inicializado en el constructor
     * @return objeto gráfica principal
     */
    protected GraficaPrincipal getGPrincipal ()
    {
        return g_principal;
    }
    
    
    /**
     * Menu principal de todas las distintas gestiones.
     * @param array con las opciones que tiene el menu. Titulo del menu
     * @return opción que elige el usuario
     */
    protected int menuOpciones (String [] datos, String titulo)
    {
         int opcion = 0;
        do{
             opcion = g_principal.menuGestionOpciones (datos, titulo); 
          
            if (opcion == 115 || opcion == 83){
			  break;
			 }
			  
        } while (opcion < 1 || opcion > datos.length);
        return opcion;
    }
    
    
    /**
     * Pregunta al usuario la lista de datos que venga en el array. Cada dato lo manda a validar por separado, dependiendo
     * del valor que se espere, ya sea un telefono o un nombre por ejemplo.
     * @param array de datos a leer
     * @retur lista con los datos leidos
     */
    protected List<String> leerArrayDatos (String [] datos_imprimir)
    {
        datos_leidos = new ArrayList<String>();
        boolean correcto = false, repetir = false, cancelar = false;
        
        for (int a = 0; (a < datos_imprimir.length) && !cancelar; a++){
            
            do{
            repetir = false;
            String dato_leido = g_principal.leerDatoUsuario (datos_imprimir[a]);
            correcto = g_principal.validarCadena (dato_leido, datos_imprimir[a]);
            
             if (!correcto){
                 repetir = preguntarSiRepetir ();
                      if (!repetir){
                         cancelar = true;
                        }
                } else {
                   datos_leidos.add (dato_leido);    
                }
                
            } while (repetir && !cancelar);
        }
        return datos_leidos;
    }
    
    
    /**
     * Pregunta si repetir la misma accion o abortar 
     * @return true si repetir
     */
    private boolean preguntarSiRepetir ()
    {
        boolean repetir = false;
        repetir = g_principal.preguntarSiNo ("!!!Error el valor no es valido, quiero intentarlo de nuevo? S/N: ");
        return repetir;
    }
    
    
    /**
     *Pide a la base de datos un jugador por nombre y apellidos que ingresa el usuario
     *Si el jugador es erróneo o no existe, el jugador sera null.
     */
    protected Jugador pedirJugador ()
    {
        datos_leidos = new ArrayList<String>();
        Jugador jugador = null;
        String [] datos = {"Nombre jugador", "Apellido jugador"};
        datos_leidos.clear();
        datos_leidos = leerArrayDatos (datos);  
        jugador = Inicio.getBaseDatos().buscaJugador (datos_leidos.get(0), datos_leidos.get(1));
        return jugador;
    }
    
    
    /**
     * Pedir el día, mes y año 
     * @return fecha del día jugado
     */
    protected GregorianCalendar pedirFecha ()
    {
        GregorianCalendar fecha_jugado = null;
        int dia = getGPrincipal().leerInt ("Day");
        int mes = getGPrincipal().leerInt ("Month");
        int anyo = getGPrincipal().leerInt ("Year");
        boolean correcto = validarFecha (dia, mes, anyo);
        
        if (correcto) {
          fecha_jugado = new GregorianCalendar (anyo, mes-1, dia); 
        }
        return fecha_jugado;
    }
    
    
    /**
     * Valida el dia, mes y año escrito por el usuario. Evalua la fecha despues de introducir todos los valores. Si alguno no es correcto
     * se pedira repetir los tres campos de nuevo.
     * @param enteros de dia, mes y año
     * @return valor true si la fecha es correcta
     */
    private boolean validarFecha (int dia, int mes, int anyo) 
    {
        boolean correcto = false, repetir = false;
        
       if ((dia > 0 && dia <= 31) && (mes > 0 && mes <= 12) && (anyo > 2000 && anyo <= 5000)) {
           correcto = true;
        } else {
         repetir = preguntarSiRepetir ();  
            if (repetir) {
               pedirFecha (); 
            } else {
               g_principal.pausaSalir ("Accion cancelada..."); 
            }
        }
       return correcto; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
