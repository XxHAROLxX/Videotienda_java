/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelPeliculas.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import uniandes.cupi2.videotienda.mundo.Pelicula;

/**
 * Panel para el manejo de las películas
 */
public class PanelPeliculas extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando para ver información de una película
     */
    private static final String VER = "ver";

    /**
     * Comando para agregar una copia de una película
     */
    private static final String AGREGAR = "agregar";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazVideotienda ventanaPrincipal;

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Lista de películas
     */
    private JList listaPeliculas;

    /**
     * Botón para ver información de una película
     */
    private JButton botonVer;

    /**
     * Botón para agregar una copia de una película
     */
    private JButton botonAgregar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de películas
     * @param ventana Ventana principal de la aplicación. ventana != null.
     */
    public PanelPeliculas(InterfazVideotienda ventana)
    {
        ventanaPrincipal = ventana;

        setBorder(BorderFactory.createTitledBorder("Catálogo de Películas"));
        setLayout(new BorderLayout());

        // Lista de películas
        listaPeliculas = new JList();
        listaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaPeliculas);
        scroll.setPreferredSize(new Dimension(300, 150));
        add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        botonVer = new JButton("Ver");
        botonVer.setActionCommand(VER);
        botonVer.addActionListener(this);
        panelBotones.add(botonVer);

        botonAgregar = new JButton("Agregar Copia");
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        panelBotones.add(botonAgregar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza el listado de películas
     * @param peliculas Listado de películas. peliculas != null.
     */
    public void actualizarPeliculas(ArrayList peliculas)
    {
        String[] lista = new String[peliculas.size()];
        Pelicula p;

        for(int i = 0; i < peliculas.size(); i++)
        {
            p = (Pelicula)peliculas.get(i);
            lista[i] = p.darTitulo();
        }

        listaPeliculas.setListData(lista);
    }

    /**
     * Retorna el título de la película seleccionada
     * @return título película seleccionada o null si no hay selección
     */
    public String darPeliculaSeleccionada()
    {
        return (String)listaPeliculas.getSelectedValue();
    }

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed(ActionEvent evento)
    {
        String comando = evento.getActionCommand();
        String pelicula = darPeliculaSeleccionada();

        if(comando.equals(VER))
        {
            if(pelicula == null)
                return;
            ventanaPrincipal.mostrarPelicula(pelicula);
        }
        else if(comando.equals(AGREGAR))
        {
            if(pelicula == null)
                return;
            ventanaPrincipal.agregarCopia(pelicula);
        }
    }
}
