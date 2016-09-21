
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

    
    /**
     * Salida de datos al archivo
     */
    public static boolean exportarDatos (Collection<Object> datos_sistema) 
    {
        boolean exportado = false;
        ObjectOutputStream salida = null;
        
          try{
            salida = new ObjectOutputStream (new FileOutputStream ("ArchivoDatosSistema.txt"));
            salida.writeObject (datos_sistema);
            exportado = true;
            
        } catch (FileNotFoundException e) {
            System.out.println (e.getMessage());
        } catch (IOException e) {
             System.out.println (e.getMessage());
        }  finally {
     
            try {
                salida.flush();
                if (salida != null) salida.close();
            } catch (IOException e) {
              System.out.println ("!!!Error"+e.getMessage());  
            }
            
        }
        return exportado;
    }
    
    
    /**
     * Entrada de datos al sistema
     */
    public static Collection<Object> importarDatos () 
    {
        Collection<Object> lista_datos = new ArrayList<Object>();
        ObjectInputStream entrada = null;
        
        try{
           entrada = new ObjectInputStream (new FileInputStream ("ArchivoDatosSistema.txt"));
           lista_datos = (Collection<Object>) entrada.readObject ();
           
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
