/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Pelicula.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa una pelicula que se encuentra en la videotienda y
 * de la cual puede haber copias disponibles o prestadas.
 */ 
public class Pelicula
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Titulo de la pelicula
     */
    private String titulo;

    /**
     * Lista de copias disponibles
     */
    private ArrayList disponibles;

    /**
     * Lista de copias prestadas
     */
    private ArrayList prestadas;

    /**
     * Numero de la siguiente copia a adicionar
     */
    private int codigoSiguienteCopia;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una pelicula de la videotienda con el titulo dado. <br>
     * <b>post: </b> La pelicula se crea sin copias disponibles ni prestadas.
     * @param unTitulo Titulo de la pelicula. unTitulo != null.
     */
    public Pelicula( String unTitulo )
    {
    	titulo  = unTitulo;
    	disponibles = new ArrayList<Copia>();
    	prestadas  = new ArrayList<Copia>();
    	codigoSiguienteCopia = 1;	  
    }

    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------

    /**
     * Adiciona una nueva copia de la pelicula. <br>
     * <b>post: </b>La lista de peliculas disponibles tiene una nueva copia.
     * @return codigo de la copia creada. c�digo >= 1;
     */
    public int agregarCopia( )
    {
        Copia nuevaCopia = new Copia(titulo, codigoSiguienteCopia);
        disponibles.add(nuevaCopia);
        codigoSiguienteCopia++;
        return nuevaCopia.darCodigo();
    }
    /**
     * Retorna una copia de pelicula para alquilar si hay disponibles. <br>
     * <b>post: </b> la copia queda en la lista de prestadas.
     * @return Copia que ha sido alquilada o null si no hay disponibles.
     */
    public Copia alquilarCopia( )
    {
        if(disponibles.isEmpty())
        {
            return null;
        }
        
        Copia copia = disponibles.get(0);
        disponibles.remove(0);
        prestadas.add(copia);
        return copia;
    }

    /**
     * Devuelve una copia de la pel�cula y la coloca como disponible. <br>
     * <b>post: </b> regresa la copia a la lista de disponibles, solo si esta prestada.
     * @param codigoCopia Codigo de la copia que se quiere devolver.
     * @throws Exception Si la copia a devolver no esta prestada.
     */
    public void devolverCopia(int codigoCopia) throws Exception
    {
    	Copia copiaADevolver = null;
    	
    	for(Copia copia : prestadas)
    	{
    		if(copia.darCodigo() == codigoCopia)
    		{
    			copiaADevolver = copia;
    			break;
    		}
    	}
    	
    	if(copiaADevolver == null)
    	{
    		throw new Exception("la copia" + codigoCopia + "No está prestada");
    	}
    	
    	prestadas.remove(copiaADevolver);
    	disponibles.add(copiaADevolver);
    }
    /**
     * Retorna el t�tulo de la pelicula.
     * @return t�tulo de la pel�cula.
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna la cantidad total de copias que existen de la pelicula en la videotienda
     * @return entero con la cantidad de copias que existen en la tienda
     */
   public int darTotalcopias()
   {
	   return disponibles.size() + prestadas.size();
   }
   
    /**
     * Retorna el numero de copias disponibles
     * @return numero de copias disponibles
     */
   public int darNumeroDisponibles()
   {
	   return disponibles.size();
   }
}