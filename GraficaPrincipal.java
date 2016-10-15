
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;



/**
 * Clase que se encarga de toda la parte gráfica de la aplicación
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */


public class GraficaPrincipal
{
   
    private Scanner sc;

    
    public GraficaPrincipal()
    {
    }

    
    /**
     * Carga los datos necesarios para el menú de inicio de la aplicación
     * @return devuelve el número de opción seleccionado por el usuario
     */
    public int menuInicio ()
    {
        int opcion = 0;
       String [] datos = {"Gestion jugador", "Gestion dia jugar", "Resumen datos", "Guardar datos en archivo"}; 
       opcion = devolverMenuPantalla (datos, "  MENU PRINCIPAL");
       return opcion;
    }
    
    
    /**
     * Carga en pantalla el menú de opciones que puede realizar un usuario
     * @param datos a imprimir en pantalla, titulo del menu
     * @return opción seleccionada por el usuario
     */
    public int menuGestionOpciones (String [] datos, String titulo)
    {
        int opcion = 0;
        opcion = devolverMenuPantalla (datos, "  "+titulo);
        return opcion;
    }
    
    
    public void cabeceraInicio ()
    {
       System.out.println ();
       System.out.println (" =================================================================================================");
       System.out.println (" ===                            APLICACION DATOS TENIS                                         ===");
       System.out.println (" =================================================================================================");
       System.out.println (); 
    }
    
    
   /**
	 * Recibe un string por parámetro que sera lo que imprime en pantalla, recoge el dato ingresado por el usuario
	 * @param dato a leer
	 * @return dato leido
	 */
	public String leerDatoUsuario (String dato_imprimir)
	{
	    String dato_leido = null;
	    System.out.println ();
	    System.out.print(" "+dato_imprimir+": ");
	    dato_leido = leerString ();
	    return dato_leido;
	 }
	
	
	 /**
	  * Lee un valor entero escrito por el usuario 
	  * @param mensaje que se mostrara en pantalla
	  */
	public int leerInt (String dato_imprimir)
	{
		sc = new Scanner (System.in);
		int valor_leido = 0;
		String cadena = null;
		
		try{
		    System.out.println ();
		    System.out.print(" "+dato_imprimir+": ");
			cadena = sc.nextLine();
			valor_leido = Integer.parseInt(cadena);
		} catch (NumberFormatException e){
			valor_leido = (int) cadena.charAt(0);
		}
		return valor_leido;
	} 
    
	
	/**
	 * Pinta en pantalla los datos de un List por medio su metodo toString
	 * @param lista_obj que pintara en pantalla
	 */
	public void pintarLista (List<?> lista_obj)
	{
	    System.out.println ();
		System.out.println (lista_obj.toString());
	}
	
	
	/**
	 * Pinta en pantalla los datos de un Set por medio del método toString del objeto
	 * @param lista_obj que se pintara en pantalla
	 */
	public void pintarListaJugador (Set<?> lista_obj)
	{
	    System.out.println ();
	    System.out.println (lista_obj.toString());
	}
	
		
	/**
	 * Pinta en pantalla el objeto del parámetro a traves de su método toString
	 * @param obj
	 */
	public void pintarObjeto (Object obj)
	{
		pintarLinea ();
		System.out.println (obj.toString());
		System.out.println ();
	}
    
	
	/**
	 * Hace una pausa con un aviso antes de salir del menu donde se encuentra la aplicacion
	 */
	public void pausaSalir (String mensaje)
	{
		String volver = null;
		do{
			System.out.println ("\n  "+mensaje+" Pulse 's' para continuar...");
			volver = leerString ();
		} while (!volver.equals("s") && !volver.equals("S"));
	}
	
	
	/**
	 * Pregunta al usuario lo que venga en el parametro String, solo admite si/no
	 * @return - true si la respuesta es afirmativa
	 */
	public boolean preguntarSiNo (String mensaje)
	{
		boolean devolver = false;
		String respuesta = null;
		
		do{
			pintarLinea ();
			System.out.print (mensaje);
			respuesta = leerString ();
			
			if (respuesta.equals("s") || respuesta.equals("S")){
				devolver = true;
			}else if (respuesta.equals("n") || respuesta.equals("N")){
				devolver = false;
			}
			
		} while (!respuesta.equals("s") && !respuesta.equals("S") && !respuesta.equals("n") && !respuesta.equals("N"));
		
		return devolver;
	}

	
	/**
	 * Escribe en pantalla el mesaje del parametro  
	 * @param - String mensaje
	 */
	public void imprimirMensaje (String mensaje)
	{
	    System.out.println ();
		System.out.println (" "+mensaje);
		pintarLinea ();
	}
	
	
	/**
	 *  Imprime una linea sin salto de linea y con los dos puntos ':'
	 * @param linea
	 */
	public void imprimirLinea (String linea)
	{
		System.out.println ();
		System.out.print (linea+": ");
	}
	
	
	/**
	 *  Validar una cadena de caracteres segun el valor de la cadena valor. Si la cadena valor es por ejemplo 'DNI', se valida el dni
	 *   si es un número se válida que la cadena sea un número.
	 *  cero a nueve.
	 * @param cadena_validar - cadena que se debe validar
	 * @param cadena_valor - el valor de la cadena de entrada
	 * @return true si es correcto
	 */
	public boolean validarCadena (String cadena_validar, String cadena_valor)
	{
		boolean correcto = false;
		
		if (cadena_valor.contains ("nº") || cadena_valor.contains ("Nº")){
			 correcto = cadena_validar.matches("[0-9]+");
		} else if (cadena_valor.contains ("DNI")){
			 correcto = cadena_validar.matches("[0-9]+[A-Z]{1}");
        } else if (cadena_valor.contains ("ID")){
		     correcto = cadena_validar.matches("[A-Z]+[0-9]{3}");
		} else {
			correcto = cadena_validar.matches ("[a-z A-Z]+");
		}
		
		return correcto;
	}
    
    
    /**
	 * Cabecera en los menus principales
	 */
	private void pintarCabeceraMenu (String titulo)
	{
		System.out.println ();
		System.out.println ();
		System.out.println ();
		System.out.println (" *************************************************************************************************");
		System.out.println (" ******                         "+titulo+"                                                        ");
		System.out.println (" *************************************************************************************************");
	}
	
	
	/**
	 * Linea de separacion para menus
	 */
	private void pintarLinea ()
	{
	    System.out.println ("--------------------------------------------------------------------------------------------------");
	}
	
	
	/**
	 * Pinta el menu en pantalla con los datos del array
	 * @return - entero con la opcion elegida por el usuario.
	 */
	private int devolverMenuPantalla (String [] datos, String titulo)
	{
		int opcion = 0;
		pintarCabeceraMenu (titulo);
		pintarMenu (datos);
		opcion = leerInt ();
		return opcion;
	}
	
	
	/**
	 * Pinta en pantalla el array pasado por parametro
	 * @param datos que se imprimiran en pantalla
	 */
	private void pintarMenu (String [] datos)
	{
		pintarLinea();
		System.out.println (" "+0+" - "+"Pulse 's' 0 'S' para salir");
		for (int a = 0; a<datos.length; a++){
			System.out.println (" "+(a+1)+" - "+datos[a]);
		}
		System.out.println ();
		System.out.print (" Seleccione una opcion: ");
	}

	
	/**
	 * Lee la cadena ingresada por el usuario y la devuelve
	 * @return - cadena leida
	 */
	private String leerString ()
	{
		sc = new Scanner (System.in);
		String leido = null;
		leido = sc.nextLine ();
		return leido;
	}
	
	
	/**
	 * Lee una cadena y la pasa a entero. Si lo que lee es una letra, devuelve su codigo ascii
	 * @return - cadena pasada a entero leida por Scanner o su codigo ascii
	 */
	private int leerInt ()
	{
		sc = new Scanner (System.in);
		int valor_leido = 0;
		String cadena = null;
		
		try{
			cadena = sc.nextLine();
			valor_leido = Integer.parseInt(cadena);
		} catch (Exception e){
			valor_leido = (int) cadena.charAt(0);
		}
		
		return valor_leido;
	}
	
}
