/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelTarifa.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para el manejo de la tarifa
 */
public class PanelTarifa extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando para cambiar la tarifa
     */
    private static final String CAMBIAR = "cambiar";

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
     * Etiqueta valor de la tarifa
     */
    private JLabel etiquetaTarifa;

    /**
     * Campo para el valor de la tarifa
     */
    private JTextField campoTarifa;

    /**
     * Botón para cambiar la tarifa
     */
    private JButton botonCambiar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de la tarifa
     * @param ventana Ventana principal de la aplicación. ventana != null.
     */
    public PanelTarifa(InterfazVideotienda ventana)
    {
        ventanaPrincipal = ventana;

        setLayout(new FlowLayout(FlowLayout.CENTER));

        etiquetaTarifa = new JLabel("Valor de tarifa");
        add(etiquetaTarifa);

        campoTarifa = new JTextField(10);
        add(campoTarifa);

        botonCambiar = new JButton("Cambiar");
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);
        add(botonCambiar);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Asigna el valor de la tarifa en el campo
     * @param tarifa Valor de la tarifa. tarifa > 0.
     */
    public void asignarTarifa(int tarifa)
    {
        campoTarifa.setText(Integer.toString(tarifa));
    }

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed(ActionEvent evento)
    {
        String comando = evento.getActionCommand();

        if(comando.equals(CAMBIAR))
        {
            try
            {
                int tarifa = Integer.parseInt(campoTarifa.getText());
                if(tarifa <= 0)
                {
                    JOptionPane.showMessageDialog(this, "La tarifa debe ser mayor que cero", "Cambio de Tarifa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ventanaPrincipal.cambiarTarifa(tarifa);
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this, "La tarifa debe ser un valor entero", "Cambio de Tarifa", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
