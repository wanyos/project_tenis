
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.io.Serializable;

/**
 * Clase encargada de llevar toda la gestion de un jugador. Existe una lista estatica donde se guardan los bonos de un jugador
 * 
 * @author (Juan José Romero) 
 * @version (1.0  25/08/2016)
 */

public class Jugador implements Serializable
{
    private List<Bono> lista_bono = new ArrayList<Bono>();
    
    private GregorianCalendar fecha_alta;
    private String nombre, apellido, dni, telefono, num_socio;
    private int id, contador_horas;
    private Bono bono;

    
    /**
     * 
     */
    public Jugador (String nombre, String apellido, String dni, String telefono, String num_socio)
    {
        this.fecha_alta = new GregorianCalendar();
        this.id = Inicio.getBaseDatos().getNuevoIdJugador ();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.num_socio = num_socio;
        this.contador_horas = 0;
    }

    
    public Jugador (String nombre, String apellido)
    {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    
    public String getIdString ()
    {
        return "JU00"+this.id;
    }
    
    
    public GregorianCalendar getFechaAlta ()
    {
        return this.fecha_alta;
    }
    
    
    public int getId ()
    {
        return this.id;
    }
    
    
    public String getNombre ()
    {
        return this.nombre;
    }
    
    
    public String getApellido ()
    {
        return this.apellido;
    }
    
   
    public String getDni ()
    {
        return this.dni;
    }
    
    
    public String getTelefono ()
    {
        return this.telefono;
    }
    
    
    public String getNumSocio ()
    {
      return this.num_socio;  
    }
    
    
    public int getContador ()
    {
        return this.contador_horas;
    }
    
    
    /**
     * Aumenta el contador la cantidad de horas del parametro
     * @param cantidad de horas a sumar
     */
    public void setContador (int total_horas_sumar)
    {
        this.contador_horas = this.contador_horas + total_horas_sumar; 
    }
    
    
    /**
     * Resta la cantidad de horas del parametro al contador de horas
     * @param cantidad de horas a restar
     */
    public void restarHoraContador (int total_restar)
    {
        this.contador_horas = this.contador_horas - total_restar;
    }
    
    
    /**
     * Obtiene el último id de la lista de bono para darle uno más al nuevo bono
     * @return número id que tendra el nuevo bono
     */
    private int getUltimoIdBono ()
    {
        int num_bono = lista_bono.size()+1;
        return num_bono;
    }
    
    
    /**
     * Al hacer una compra de un bono se aumentan las horas del contador multiplicadas por 2
     */
    public boolean comprarBono (int total_horas_bono, String num_bono, String num_jugador)
    {
        bono = new Bono (new GregorianCalendar(), getUltimoIdBono(), total_horas_bono, num_bono, num_jugador);
           this.setContador (total_horas_bono*2);
           lista_bono.add (bono);
           return true;
    }
    
    
    /**
     *Busca un bono en la lista de los bonos del jugador. Comprueba el id y el número de bono.
     *Si uno de esos datos es null buscara solo por el otro dato.
     *@param id y num_bono a buscar
     *@return bono si es que es correcto y existe
     */
    public Bono buscarBono (String id, String num_bono)
    {
        Bono bono = null;
        boolean encontrado = false;
        Iterator<Bono> it = lista_bono.iterator();
        
        while (it.hasNext() && !encontrado) {
          Bono aux = (Bono) it.next();
           if (id == null && aux.getNumBono().equals(num_bono)){
              bono = aux;
              encontrado = true;
            } else if (num_bono == null && aux.getIdString().equals(id)) {
               bono = aux;
               encontrado = true;
            }
        }
        return bono;
    }
    
    
    /**
     * Le carga las horas de juego al bono que queramos
     * @param bono para el cargo de horas y cantidad de horas
     * @return confirmación de la operación
     */
    public boolean restarHoraBono (Bono bono, int cantidad_hora)
    {
        boolean correcto = false;
        Iterator<Bono> it = lista_bono.iterator();
        
        while (it.hasNext()) {
            Bono aux = it.next();
            if (aux.equals(bono)) {
              aux.restarHoraBono (cantidad_hora);  
              correcto = true;
            }
        }
        return correcto;
    }
    
    
    public List<Bono> getListaBono ()
    {
        return this.lista_bono;
    }
    
    
    public void eliminarBono (Bono bono)
    {
        lista_bono.remove (bono);
    }
    
    
    public String toString ()
    {
        return "ID: "+getIdString()+" -- Fecha alta: "+Recursos.convertirFecha(fecha_alta)+" -- Nombre: "+nombre+" -- Apellidos: "+apellido+
               " -- DNI: "+dni+" -- Telefono: "+telefono+" -- Numero: "+num_socio+"  -- Contador: "+getContador()+"\n";
    }
    
    
    public boolean equals (Object obj)
    {
        boolean iguales = false;
        if (obj != null && obj instanceof Jugador){
            Jugador jug = (Jugador) obj;
            
            if (jug.getNombre().equals(nombre) && jug.getApellido().equals(apellido) && jug.getDni().equals(dni)){
                iguales = true;
            } 
        }
        return iguales;
    }
    
    
    
    public int hashCode ()
    {
         return 9 * this.nombre.hashCode() + this.apellido.hashCode();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
