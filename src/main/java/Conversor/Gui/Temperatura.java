package Conversor.Gui;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Temperatura extends JPanel{
    private JTextField textFieldCantidad;
    private JComboBox<String> comboBoxTempOrigen;
    private JComboBox<String> comboBoxTempDestino;
    private JLabel labelResultado;


    public Temperatura() {
        setSize(400,200);
        setLayout(new GridLayout(4, 2));

        JLabel labelCantidad = new JLabel("Cantidad:");
        textFieldCantidad = new JTextField();
        JLabel labelTempOrigen = new JLabel("Temperatura en ");
        comboBoxTempOrigen = new JComboBox<>(new String[]{"Cº","Fº","Kº"});
        JLabel labelTempDestino= new JLabel("Temperatura en ");
        comboBoxTempDestino = new JComboBox<>(new String[]{"Cº","Fº","Kº"});
        JButton buttonConvertir = new JButton("Convertir");
        labelResultado = new JLabel("");

        buttonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirTemp();
            }
        });

        add(labelCantidad);
        add(textFieldCantidad);
        add(labelTempOrigen);
        add(comboBoxTempOrigen);
        add(labelTempDestino);
        add(comboBoxTempDestino);
        add(buttonConvertir);
        add(labelResultado);

    }

    private void convertirTemp() {
        double cantidad = Double.parseDouble(textFieldCantidad.getText());
        String tempOrigen = (String) comboBoxTempOrigen.getSelectedItem();
        String tempDestino = (String) comboBoxTempDestino.getSelectedItem();

        double resultado = resultadoDestino(tempOrigen,tempDestino,cantidad);

        labelResultado.setText(String.format("%.2f %s = %.2f %s", cantidad, tempOrigen,resultado, tempDestino));
    }
    private Double resultadoDestino(String tempOrigen, String tempDestino,double cantidad){
        if (tempOrigen.equals("Cº") && tempDestino.equals("Fº")){
            return ((cantidad * 1.8) +32);
        }else if (tempOrigen.equals("Cº") && tempDestino.equals("Kº")){
            return cantidad + 273.15;
        }else if (tempOrigen.equals("Fº") && tempDestino.equals("Cº")){
            return (cantidad-32)/1.8;
        }else if (tempOrigen.equals("Fº") && tempDestino.equals("Kº")){
            return (cantidad-32)/1.8+273.15;
        }else if (tempOrigen.equals("Kº") && tempDestino.equals("Cº")){
            return (cantidad- 273.15);
        }else if (tempOrigen.equals("Kº") && tempDestino.equals("Fº")){
        }
        return 0.00;
    }
    public void iniciartemp(){
        setVisible(true);
    }
}
