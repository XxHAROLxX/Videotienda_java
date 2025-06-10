package uniandes.cupi2.videotienda.mundo;
import java.util.ArrayList;
/**
 * Esta clase presenta los clientes de la tienda
 */
public class Cliente
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Cedula del cliente
	 */
	private String cedula;
	
	/**
	 * Nombre del cliente
	 */
	private String nombre;
	
	/**
	 * Dirección del cliente
	 */
	private String direccion;
	
	/**
	 * Saldo del cliente
	 */
	private int saldo;
	
	/**
     * Lista de copias alquiladas por el cliente
     */
    private ArrayList<Copia> alquiladas;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * Crea un cliente con los parametros dados
	*/
	public Cliente( String laCedula, String elNombre, String laDireccion )
	{	
		cedula = laCedula;
		nombre = elNombre;
		direccion = laDireccion;
		saldo= 0;
        alquiladas = new ArrayList<Copia>();

	}
	
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 *Devuelve la cedula del cliente
	 */
	public String darCedula() {
		return cedula;
		
	}
	
	/**
	 * devuelve el sando del cliente
	 */
	public int darSaldo() {
		return saldo; 
	}
	
	/**
	 * Da el nombre del cliente
	 */
	public String darNombre() {
		return nombre;
	}
	
	/**
	 * proporciona la direccion
	 */
	public String darDireccion() {
		return direccion;
	}
	
	/**
	 * Alquila una copia de pelicula
	 */
	public void alquilarCopia(Copia copia) {
		alquiladas.add(copia);
	}
	
	public void cargarSaldo(int monto) {
		saldo += monto;
	}
	 /**
     * Descarga saldo del cliente
     */
    public void descargarSaldo(int monto)
    {
        saldo -= monto;
    }
	
    /**
     * Retorna el número de películas alquiladas
    */
    public int darNumeroAlquiladas()
    {
        return alquiladas.size();
    }
    
    /**
     * Retorna la lista de copias alquiladas
     */
    public ArrayList<Copia> darAlquiladas()
    {
        return alquiladas;
    }
    
    /**
     * Busca una película alquilada por el cliente
     */
    public Copia buscarPeliculaAlquilada(String pelicula, int codigo)
    {
        for(Copia copia : alquiladas)
        {
            if(copia.darTituloPelicula().equals(pelicula) && copia.darCodigo() == codigo)
            {
                return copia;
            }
        }
        return null;
    }
    
    /**
     * Devuelve una copia alquilada
     */
    public void devolverCopia(String pelicula, int codigo)
    {
        for(int i = 0; i < alquiladas.size(); i++)
        {
            Copia copia = alquiladas.get(i);
            if(copia.darTituloPelicula().equals(pelicula) && copia.darCodigo() == codigo)
            {
                alquiladas.remove(i);
                break;
            }
        }
    }
}