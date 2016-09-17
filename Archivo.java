
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;


/**
 * Clase encargada de persistir los datos de la aplicacion en un archivo 
 * 
 * @author (Juan José Romero Ramos) 
 * @version (07/09/2016  1.0)
 */
public class Archivo
{
    

   
    public Archivo()
    {
    }

    
    public boolean salidaDatos (List<Object> datos) throws IOException
    {
        boolean exportado = false;
        ObjectOutputStream salida = null;
        
          try{
            salida = new ObjectOutputStream (new FileOutputStream ("ArchivoDatos.txt"));
            salida.writeObject (datos);
            exportado = true;
            
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            
        }  finally {
            salida.close ();
        }
        return exportado;
    }
    
    
    public List<Object> entradaDatos () throws Exception
    {
        List<Object> lista_datos = new ArrayList<Object>();
        ObjectInputStream entrada = null;
        
        try{
           entrada = new ObjectInputStream (new FileInputStream ("ArchivoDatos.txt"));
           lista_datos = (List<Object>) entrada.readObject ();
           
        } catch (FileNotFoundException e) {
           //hacer algo  
        } catch (ClassNotFoundException e) {
            
        } catch (IOException e) {
            
        } finally {
           entrada.close ();
        }
        
        return lista_datos;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
