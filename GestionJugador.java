


/**
 * Gestiona lo relacionado con jugadores. Se crean y eliminan jugadores. Se pueden ver todos
 * los jugadores qu existen en el sistema con sus datos personales.
 * 
 * @author (Juan José Romero) 
 * @version (1.0  27/08/2016)
 */


public class GestionJugador extends Gestion
{
   

    public GestionJugador ()
    {
        menuGestionJugador ();
    }

    
    /**
     * Menu principal de la gestión de un jugador
     */
    public void menuGestionJugador ()
    {
        String [] datos = {"Crear jugador", "Eliminar jugador", "Listar jugadores", "Crear bono para un jugador", "Eliminar bono de un jugador", "Listar bonos jugador",
                            "Buscar bono", "Buscar jugador"};
        String titulo = "  GESTION JUGADOR  ";
        int opcion = menuOpciones (datos, titulo);
        
        switch (opcion) {
           case 1: crearJugador (); break;
           case 2: eliminarJugador (); break;
           case 3: listarJugadores (); break;
           case 4: crearBonoJugador (); break;
           case 5: eliminarBonoJugador (); break;
           case 6: listarBonosJugador(); break;
           case 7: buscarBono(); break;
           case 8: buscarJugador(); break;
        }
    }
   
    
    /**
     * Crea un nuevo jugador en el sistema. No tendra ningun bono asociado desde el principio. Se debe crear un nuevo bono para este jugador
     */
    private void crearJugador ()
    {
      boolean correcto = false, repetir = false;
      datos_leidos.clear();
      String [] datos_jugador = {"Nombre jugador", "Apellido jugador", "DNI jugador", "Nº-Telefono jugador", "Nº-socio jugador"};
      
      datos_leidos = leerArrayDatos (datos_jugador);
      
      if (!datos_leidos.isEmpty() && datos_leidos.size() == datos_jugador.length) {
          Jugador jugador = new Jugador (datos_leidos.get(0), datos_leidos.get(1), datos_leidos.get(2), datos_leidos.get(3), datos_leidos.get(4));
          Inicio.getBaseDatos().setJugador (jugador);
          getGPrincipal().pausaSalir (" Jugador creado");
        } else {
            getGPrincipal().pausaSalir (" No hay datos sufientes para crear el jugador..."); 
        }
    }
    
    
    /**
     * Pide nombre y apellido de jugador, lo busca en la base de datos. Si jugador encontrado pregunta si eliminar. Si todo
     * correcto lo elimina. De lo contrario nos da mensaje de error por el problema ocurrido.
     */
    private void eliminarJugador ()
    {
         boolean eliminar = false;
         Jugador jugador = pedirJugador();
       
          if (jugador != null){
            getGPrincipal().pintarObjeto (jugador);
            eliminar = getGPrincipal().preguntarSiNo (" Eliminar jugador? S/N: ");
                 if (eliminar){
                    Inicio.getBaseDatos().eliminarJugador (jugador);
                    getGPrincipal().pausaSalir (" Jugador eliminado..."); 
                  } else {
                    getGPrincipal().pausaSalir (" Accion cancelada..."); 
                  }
            
        } else {
            getGPrincipal().pausaSalir (" No existe ningun jugador con ese nombre y apellido...");
        }
    }
    
    
    /**
     * Imprime la lista de jugadores que existe en el sistema.
     */
    private void listarJugadores ()
    {
        getGPrincipal().imprimirMensaje ("  LISTAR JUGADORES");
        getGPrincipal().pintarListaJugador (Inicio.getBaseDatos().getListaJugador());
        getGPrincipal().pausaSalir (" Fin de los datos..."); 
    }
    
    
    /**
     * Nos jugador que compra el bono que sera comprobado en la base de datos que existe.
     * Si todo correcto nos pide las horas que tendra el bono y su número. Le agrega el bono al jugador.
     */
    private void crearBonoJugador ()
    {
        String [] datos_bono = {"Nº horas del bono", "Nº-Bono"};
        Jugador jugador = pedirJugador ();
        int horas_bono = 0;
        
        if (jugador != null){
              datos_leidos.clear();
              datos_leidos = leerArrayDatos (datos_bono);
              horas_bono = Recursos.pasarCadenaInt (datos_leidos.get(0));
              boolean correcto = jugador.comprarBono (horas_bono, datos_leidos.get(1), jugador.getNumSocio());
               if (correcto){
                   getGPrincipal().pausaSalir (" Bono dado de alta la jugador..."); 
                } else {
                   getGPrincipal().pausaSalir (" !!!Error bono no dado de alta...");  
                }
        } else {
            getGPrincipal().pausaSalir (" No existe ningun jugador con ese nombre y apellido..."); 
        }
        
    }
    
    
    /**
     * Pide el jugador que quiere borrar uno de sus bonos. Si el jugador correcto nos pide el bono que quiere borrar mostrando en pantalla
     * sus bono. Le tenemos que dar el id del bono que queremos borrar.
     */
    private void eliminarBonoJugador ()
    {
        Jugador jugador = pedirJugador ();
        boolean eliminar = false;
        
     if (jugador != null){
            getGPrincipal().pintarLista (jugador.getListaBono());
            String [] datos = {"ID del bono a borrar", "Nº bono a borrar"};
            datos_leidos.clear();
            datos_leidos = leerArrayDatos (datos);
            
              if (datos_leidos.size()<2){
                    getGPrincipal().pausaSalir (" Accion cancelada...");   
              } else {
              Bono bono = jugador.buscarBono (datos_leidos.get(0), datos_leidos.get(1));
                 if (bono != null){
                   eliminar = getGPrincipal().preguntarSiNo (" Eliminar bono? S/N: ");
                      if (eliminar){
                         jugador.eliminarBono (bono);
                         getGPrincipal().pausaSalir (" Bono eliminado...");   
                       }
                    } else {
                      getGPrincipal().pausaSalir (" El bono no existe o no es correcto...");   
                    }
                }   
                  
        } else {
           getGPrincipal().pausaSalir (" El jugador no existe o no ha sido encontrado...");    
        }
    }
    
    
    private void listarBonosJugador ()
    {
         Jugador jugador = pedirJugador ();
         
         if (jugador != null){
              getGPrincipal().imprimirMensaje ("  BONOS JUGADOR "+jugador.getNombre()+" "+jugador.getApellido());
              getGPrincipal().pintarLista (jugador.getListaBono());
              getGPrincipal().pausaSalir (" Fin de los datos..."); 
            } else {
               getGPrincipal().pausaSalir (" El jugador no existe o no ha sido encontrado...");  
            }
    }
    
    
    /**
     * Busca todos los bonos que correspondan con el mismo id que escribamos. Cada jugador tiene su lista de bono y los id
     * de los diferentes bonos pueden coincidir
     */
    private void buscarBono ()
    {
      String num_bono = getGPrincipal().leerDatoUsuario ("Escriba el numero de bono a buscar");
      Bono bono = Inicio.getBaseDatos().buscarBonoIdNum (num_bono);
      
      if (bono != null) {
           getGPrincipal().pintarObjeto (bono);
           getGPrincipal().pausaSalir (" Fin de los datos..."); 
        } else {
            getGPrincipal().pausaSalir (" El bono no existe o no ha sido encontrado...");  
        }
    }
    
    
    /**
     * Busca un jugador por nombre y apellido. Si lo encuentra los muestra en pantalla
     */
    private void buscarJugador ()
    {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}