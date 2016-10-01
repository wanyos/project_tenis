
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Write a description of class Recursos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public abstract class Recursos
{
   

    public Recursos()
    {
    }

    
    /**
     * Pasar una fecha de GregorianCalendar a String para imprimirla
     * @param GregorianCalendar 
     * @return Cadena formateada
     */
    public static String convertirFecha (GregorianCalendar fecha)
    {
       //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy - kk:mm");
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       return sdf.format (fecha.getTime());
    }
   
    
    /**
     * Convierte una cadena en entero, teniendo en cuenta que lo que viene en la cadena es un 
     * valor entero
     * @param cadena a pasar a entero
     * @return valor entero leido de la cadena
     */
    public static int pasarCadenaInt (String cadena)
    {
        int valor = 0;
        
        try{
			valor = Integer.parseInt(cadena);
		} catch (Exception e){
			valor = (int) cadena.charAt(0);
		}
		
        return valor;
    }
    
    
    /**
     *  Devuelve el total de dias del mes de la fecha del parámetro
     *  @return dias que tiene un mes
     */
    public static int getNumeroDiasMes (GregorianCalendar fecha_jugado)
    {
        int dias_del_mes = 0;
        dias_del_mes = fecha_jugado.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        return dias_del_mes;
    }
    
    
    /**
     *  Extrae el entero que queramos de la fecha del parámetro. Sea el día, mes o año.
     *  @param fecha de referencia, enteros de la fecha. Solo buscara el dato que no sea null
     *  @return entero extraido de la fecha del parámetro, sea el día, mes o año
     */
    public static int devolverNumeroFecha (GregorianCalendar fecha, int day, int month, int year)
    {
       int entero_extraido = 0;
       
       if (day > 0 && month <= 0 && year <= 0) {
           entero_extraido = fecha.get(GregorianCalendar.MONTH)+1;
        } else if (day <= 0 && month > 0 && year <= 0) {
            entero_extraido = fecha.get(GregorianCalendar.DAY_OF_MONTH); 
        } else if (day <= 0 && month <= 0 && year > 0) {
            entero_extraido = fecha.get(GregorianCalendar.YEAR); 
        }
            
       return entero_extraido;
    }
    
    
    
    
    
    
    
    
    
    
    
}
