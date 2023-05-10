package Conversor.Gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Inicio extends JFrame{

    private JMenuItem temperaturaMenuItem;
    private JMenuItem divisasMenuItem;

    private JPanel contentPane;
    public Inicio() {
        setTitle("Menu Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        //Crea la barra de menú
        JMenuBar menuBar = new JMenuBar();

        //Crea el menú "Conversores"
        JMenu conversorMenu = new JMenu("Conversores");

        //Crea los elementos del menú

        temperaturaMenuItem = new JMenuItem("Conversor de Temperatura");
        divisasMenuItem = new JMenuItem("Conversor Divisas");

        //Agrega los elementos al menu
        conversorMenu.add(temperaturaMenuItem);
        conversorMenu.add(divisasMenuItem);

        //Agrega el menú a la barra de menú
        menuBar.add(conversorMenu);

        // Establece la barra de menú en el frame
        setJMenuBar(menuBar);

        //Crea el contenedor para los Conversores
        contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        add(contentPane, BorderLayout.CENTER);

        //Accion de los elementos del menú
        divisasMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"divisas");
            }
        }); 
        temperaturaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"temperatura");
           }
        }); 
        Divisas conversorDivisas = new Divisas();
        contentPane.add(conversorDivisas,"divisas");

        Temperatura conversorTemp = new Temperatura();
        contentPane.add(conversorTemp,"temperatura");
    }
  

    public void iniciar(){
        setVisible(true);
    }
}

