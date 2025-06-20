/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * esta clase reprecenta una copia de una pelicula en la video tienda 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.videotienda.mundo;

import javax.swing.JOptionPane;

/**
 * Esta clase representa una copia de una pelicula que se encuentra en la videotienda 
 */ 
public class Copia
{
	 //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	 /**
     * Titulo de la pelicula
     */
	private String tituloPelicula;
	
	/** 
	 * código de la copia 
	 */
	private int codigo;
	//-----------------------------------------------------------------
    // constructores 
    //-----------------------------------------------------------------
	
	/**
	 * crea una copia de la película
	 * @param laPelicula Titulo de la pelicula != null.
	 * @param elCodigo Código de la copia. elCodigo > 0.
	 */
	public Copia(String laPelicula, int elCodigo)
	{
		tituloPelicula = laPelicula;
		codigo = elCodigo;
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Retorna el codigo de la copia 
	 * @return código de la copia 
	 */
	public int darCodigo()
	{
		return codigo;
	}
	
	/** Retorna el titulo de la película
	 * @return titulo de la pelicula public void agregarCopia( String titulo )
    {
        try
        {
            videotienda.agregarCopiaPelicula( titulo );
            Pelicula p = videotienda.buscarPelicula( titulo );
            JOptionPane.showMessageDialog( this, "La pelicula \"" + titulo + "\" tiene " + p.darTotalCopias( ) + " copias", "Agregar Copia", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Copia", JOptionPane.ERROR_MESSAGE );
        }

    }
	 */
	public String darTituloPelicula()
	{
		return tituloPelicula;	
	}
	
	/**
	 * Indica si la copia es igual a otra 
	 * @param otra Otra copia. otra != null.
	 * @return true si son iguales, false en caso contrario
	 */
	public boolean esIgualA(Copia otra)
    {
        return tituloPelicula.equals(otra.tituloPelicula) && codigo == otra.codigo;
    }
    
    /**
     * Alias para el método esIgualA para mantener compatibilidad con las pruebas
     * <b>pre: </b> otra != null.
     * @param otra Otra copia. otra != null.
     * @return true si son iguales, false en caso contrario
     */
    public boolean esIgual(Copia otra)
    {
        return esIgualA(otra);
    }
}