/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: VideoTienda.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Esta clase representa a la VideoTienda
 */
public class VideoTienda
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Tarifa de alquiler diario
     */
    private int tarifaDiaria;

    /**
     * Clientes
     */
    private ArrayList<Cliente> clientes;

    /**
     * Cat�logo de pel�culas
     */
    private ArrayList<Pelicula> catalogo;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una videotienda sin pel�culas registradas.
     * @param unaTarifa Tarifa diaria de alquiler. tarifa > 0.
     */
    public VideoTienda( int unaTarifa )
    {
    	tarifaDiaria = unaTarifa;
    	clientes = new ArrayList<Cliente>();
    	catalogo = new ArrayList<Pelicula>();
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Carga en memoria los datos del archivo de pel�culas. <br>
     * <b>post: </b> Se almacenan los datos de las pel�culas del archivo en el cat�logo eliminando las pel�culas anteriiores. <br>
     * @param archivo Nombre del archivo que contiene la informaci�n de las pel�culas.
     * @throws Exception si hay datos inv�lidos en el archivo o no tiene el formato adecuado.
     */
    public void cargarPeliculas( String archivo ) throws Exception
    {
        String titulo, dato;
        int peliculas, copias;
        Pelicula pel;

        //Limpia los datos iniciales de pel�culas
        catalogo.clear( );

        //Obtiene los datos
        try
        {
            Properties datos = new Properties( );
            FileInputStream input = new FileInputStream( archivo );
            datos.load( input );

            //Obtiene el n�mero de pel�culas
            peliculas = Integer.parseInt( datos.getProperty( "total.peliculas" ) );

            for( int i = 1; i <= peliculas; i++ )
            {
                dato = "pelicula" + i + ".nombre";
                //Carga una pel�cula
                titulo = datos.getProperty( dato );
                if( titulo == null )
                {
                    throw new Exception( "Falta definir la propiedad " + dato );
                }

                copias = Integer.parseInt( datos.getProperty( "pelicula" + i + ".copias" ) );
                pel = new Pelicula( titulo );
                for( int j = 1; j <= copias; j++ )
                {
                    pel.agregarCopia( );
                }

                catalogo.add( pel );
            }
        }
        catch( Exception e )
        {
            throw new Exception( "Error al cargar los datos almacenados de pel�culas" );
        }
    }

    /**
     * Afilia un cliente a la videotienda. <br>
     * <b>post: </b> Se crea un nuevo cliente y se agrega a la lista de clientes de la videotienda.
     * @param cedula C�dula del cliente a afiliar. cedula != null.
     * @param nombre Nombre del cliente a afiliar. nombre != null.
     * @param direccion Direcci�n del cliente a afiliar. direccion != null.
     * @throws Exception Si la c�dula del cliente ya est� registrada en la videotienda.
     */
    public void afiliarCliente( String cedula, String nombre, String direccion ) throws Exception
    {
    	Cliente clienteExistente = buscarCliente(cedula);
    	if(clienteExistente !=null)
    	{
    		throw new Exception("Ya existe un cliente con la cedula" + cedula);
    	}
    	
    	Cliente nuevoCliente = new Cliente(cedula, nombre, direccion);
    	clientes.add(nuevoCliente);
    }
    
    /**
     * Busca el cliente dada la c�dula.
     * @param cedula C�dula del cliente. cedula != null.
     * @return el cliente correspondiente a la c�dula, o null si no hay un cliente con la c�dula dada.
     */
    public Cliente buscarCliente( String cedula )
    {
    	for(Cliente cliente : clientes)
    	{
    		if(cliente.darCedula().equals(cedula))
        	{
        		return cliente;
        	}
    	}
    	
    	return null;
    }



    /**
     * Adiciona el monto dado al saldo disponible del cliente. <br>
     * <b>post: </b> el saldo del cliente identificado con la c�dula se increment� con el monto dado. <br>
     * @param cedula C�dula del cliente. cedula != null.
     * @param monto Cantidad de dinero a adicionar en la cuenta. monto > 0.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si la recarga de saldo es menor que 0.
     */
    public void cargarSaldoCliente( String cedula, int monto ) throws Exception
    {
    	if(monto <=0)
    	{
    		throw new Exception("El monto debe ser mayor que 0");
    	}
    	Cliente cliente = buscarCliente(cedula);
    	if(cliente == null)
    	{
    		throw new Exception("No existe un cliente con la cedula" + cedula);
    	}
    	cliente.cargarSaldo(monto);
    }

    /**
     * Alquila una pel�cula a un cliente. <br>
     * <b>post: </b> si hay copias disponibles, alquila una copia de la pel�cula, adicion�ndola a la lista de alquiladas del cliente y de la videotienda.
     * @param titulo T�tulo de la pel�cula. titulo != null.
     * @param cedula C�dula del cliente. cedula != null.
     * @return n�mero de copia alquilada.
     * @throws Exception Si la pel�cula no existe.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si no hay copias disponibles.
     * @throws Exception Si el saldo del cliente no es suficiente para el alquiler.
     */
    public int alquilarPelicula( String titulo, String cedula ) throws Exception
    {
    	Cliente cliente =buscarCliente(cedula);
    	if(cliente == null)
    	{
    		throw new Exception("No existe un cliente con la cedula" + cedula);
    	}
    	
    	Pelicula pelicula = buscarPelicula(titulo);
    	if (pelicula == null)
    	{
    		throw new Exception("No existe una película con ese título" + titulo);
    	}
    	
    	if (cliente.darSaldo() < tarifaDiaria)
    	{
    		throw new Exception("El saldo del cliente no es suficiente para el alquiler ");
    	}
    	
    	Copia copia = pelicula.alquilarCopia();
    	if(copia == null)
    	{throw new Exception("No hay copias disponibles" + titulo);
    	}
    	
    	cliente.alquilarCopia(copia);
    	cliente.descargarSaldo(tarifaDiaria);
    	
    	return copia.darCodigo();
    }

    /**
     * Devuelve a la videotienda una copia alquilada por el cliente identificado con la c�dula dada. <br>
     * <b>post: </b> Si la copia est� alquilada por el cliente, la copia se deja disponible, y el cliente ya no la tiene entre sus prestadas.
     * @param titulo T�tulo de la pel�cula. titulo != null.
     * @param numeroCopia N�mero de copia a devolver.
     * @param cedula C�dula del cliente. cedula != null.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si el cliente no tiene la copia alquilada.
     */
    public void devolverCopia( String titulo, int numeroCopia, String cedula ) throws Exception
    {
    	Cliente cliente = buscarCliente(cedula);
    	if(cliente == null)
    	{
    		throw new Exception("No exiten un cliente con la cédula" + cedula);
    	}
    	
    	Copia copia = cliente.buscarPeliculaAlquilada(titulo, numeroCopia);
    	if(copia == null)
    	{
    		throw new Exception("El cliente no tiene alquilada la copia " + numeroCopia + " de la pelicula " + titulo);
    	}
    	
    	cliente.devolverCopia(titulo, numeroCopia);
    	pelicula.devolverCopia(numeroCopia);
    }





    /**
     * Retorna la lista de clientes de la videotienda
     * @return ArrayList la lista de clientes
     */
    public ArrayList<Cliente> darListaCliente()
    {
    	return clientes;
    }

    /**
     * Retorna el cat�logo de pel�culas de la videotienda
     * @return lista de pel�culas existentes. lista != null.
     */
    public ArrayList<Pelicula> darCatalogo()
    {
    	return catalogo;
    }
    
    /**
     * Modifica la tarifa diaria de alquiler
     * @param nuevaTarifa Nueva tarifa diaria. nuevaTarifa > 0.
     */
    public void modificarTarifa(int nuevaTarifa)
    {
    	tarifaDiaria = nuevaTarifa;
    }
    
    /**
     * Retorna la tarifa diaria de alquiler
     * @return tarifa diaria de alquiler
     */
    public int darTarifa()
    {
    	return tarifaDiaria;
    }
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return Respuesta de la extensi�n 1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n 2
     * @return Respuesta de la extensi�n 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
