
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;


/**
 * Clase encargada de persistir los datos de la aplicacion en un archivo 
 * 
 * @author (Juan José Romero Ramos) 
 * @version (07/09/2016  1.0)
 */
public abstract class Archivo
{
    

   
    public Archivo()
    {
    }

    
    
    
    
    public static boolean exportarColeccionDatos (Set<Jugador> lista_jugador, List<DiaJugado> lista_dia_jugado, String nombre_archivo)
    {
        boolean exportado = false;
        ObjectOutputStream archivo_exportar = null;
        Object objeto_exportar = null;
        
        if (lista_jugador != null) {
            objeto_exportar = lista_jugador;
        } else if (lista_dia_jugado != null) {
            objeto_exportar = lista_dia_jugado;
        }
        
        try {
           archivo_exportar = new ObjectOutputStream (new FileOutputStream (nombre_archivo)); 
           archivo_exportar.writeObject (objeto_exportar);
           archivo_exportar.close();
           exportado = true;
        } catch (Exception e) {
             System.out.println ("!!!Error "+e.getMessage());  
        } finally {
            
            try {
               if (archivo_exportar != null) 
                archivo_exportar.close();
            } catch (IOException e) {
                System.out.println ("!!!Error "+e.getMessage());  
            }
            
        }
        return exportado;
    }
    
    
    public static Object importarObjeto (String nombre_archivo)
    {
        Object importar_objeto = null;
        ObjectInputStream archivo_importar = null;
        
        try {
            archivo_importar = new ObjectInputStream (new FileInputStream (nombre_archivo));
            importar_objeto = archivo_importar.readObject ();
            archivo_importar.close();
        } catch (Exception e) {
             System.out.println ("!!!Error "+e.getMessage());  
        } finally {
            
            try {
                if (archivo_importar != null)
                  archivo_importar.close();
            } catch (IOException e) {
                 System.out.println ("!!!Error "+e.getMessage());  
            }
        }
        return importar_objeto;
    }
    
    
    /*
     * Salida de datos al archivo
     *
    public static boolean exportarDatos (Collection<Object> datos_sistema)
    {
        boolean exportado = false;
        ObjectOutputStream salida = null;
        
          try{
            salida = new ObjectOutputStream (new FileOutputStream ("ArchivoDatosSistema.txt"));
            salida.writeObject (datos_sistema);
            salida.close();
            exportado = true;
            
        } catch (FileNotFoundException e) {
            System.out.println (e.getMessage());
        } catch (IOException e) {
             System.out.println (e.getMessage());
        }  finally {
     
            try {
                salida.close();
            } catch (IOException e) {
              System.out.println ("!!!Error"+e.getMessage());  
            }
            
        }
        return exportado;
    }
    
    
    /**
     * Entrada de datos al sistema
     *
    public static Collection<Object> importarDatos () 
    {
        Collection<Object> lista_datos = new ArrayList<Object>();
        ObjectInputStream entrada = null;
        
        try{
           entrada = new ObjectInputStream (new FileInputStream ("ArchivoDatosSistema.txt"));
           lista_datos = (Collection<Object>) entrada.readObject ();
           entrada.close();
           
        } catch (FileNotFoundException e) {
            System.out.println (e.getMessage());
        } catch (ClassNotFoundException e) {
             System.out.println (e.getMessage());
        } catch (IOException e) {
             System.out.println (e.getMessage());
        } finally {
    
          try {
              if (entrada != null) entrada.close ();
            } catch (IOException e) {
                 System.out.println (e.getMessage());
            }
        }
        return lista_datos;
    }
    */
    
    
    
    
    /*
     public boolean deleteArchivo (String archivo){
       File fichero = new File (archivo);
   
        if(fichero.delete()){
            return true;
         } else{
             return false;
            }
      } 
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
