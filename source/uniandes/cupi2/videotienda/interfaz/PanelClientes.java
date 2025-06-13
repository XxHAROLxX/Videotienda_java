/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelClientes.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import uniandes.cupi2.videotienda.mundo.Cliente;

/**
 * Panel para el manejo de los clientes
 */
public class PanelClientes extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando para ver información de un cliente
     */
    private static final String VER = "ver";

    /**
     * Comando para afiliar un cliente
     */
    private static final String AFILIAR = "afiliar";

    /**
     * Comando para alquilar una película
     */
    private static final String ALQUILAR = "alquilar";

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
     * Lista de clientes
     */
    private JList listaClientes;

    /**
     * Botón para ver información de un cliente
     */
    private JButton botonVer;

    /**
     * Botón para afiliar un cliente
     */
    private JButton botonAfiliar;

    /**
     * Botón para alquilar una película
     */
    private JButton botonAlquilar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de clientes
     * @param ventana Ventana principal de la aplicación. ventana != null.
     */
    public PanelClientes(InterfazVideotienda ventana)
    {
        ventanaPrincipal = ventana;

        setBorder(BorderFactory.createTitledBorder("Listado de Clientes"));
        setLayout(new BorderLayout());

        // Lista de clientes
        listaClientes = new JList();
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaClientes);
        scroll.setPreferredSize(new Dimension(300, 150));
        add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        botonVer = new JButton("Ver");
        botonVer.setActionCommand(VER);
        botonVer.addActionListener(this);
        panelBotones.add(botonVer);

        botonAfiliar = new JButton("Afiliar");
        botonAfiliar.setActionCommand(AFILIAR);
        botonAfiliar.addActionListener(this);
        panelBotones.add(botonAfiliar);

        botonAlquilar = new JButton("Alquilar");
        botonAlquilar.setActionCommand(ALQUILAR);
        botonAlquilar.addActionListener(this);
        panelBotones.add(botonAlquilar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza el listado de clientes
     * @param clientes Listado de clientes. clientes != null.
     */
    public void actualizarClientes(ArrayList clientes)
    {
        String[] lista = new String[clientes.size()];
        Cliente c;

        for(int i = 0; i < clientes.size(); i++)
        {
            c = (Cliente)clientes.get(i);
            lista[i] = c.darCedula() + "-" + c.darNombre();
        }

        listaClientes.setListData(lista);
    }

    /**
     * Retorna la cédula del cliente seleccionado
     * @return cédula cliente seleccionado o null si no hay selección
     */
    public String darClienteSeleccionado()
    {
        String cliente = (String)listaClientes.getSelectedValue();
        if(cliente == null)
            return null;
        return cliente.substring(0, cliente.indexOf("-"));
    }

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed(ActionEvent evento)
    {
        String comando = evento.getActionCommand();
        String cliente = darClienteSeleccionado();

        if(comando.equals(VER))
        {
            if(cliente == null)
                return;
            ventanaPrincipal.mostrarCliente(cliente);
        }
        else if(comando.equals(AFILIAR))
        {
            ventanaPrincipal.afiliarCliente();
        }
        else if(comando.equals(ALQUILAR))
        {
            ventanaPrincipal.alquilarPelicula();
        }
    }
}
