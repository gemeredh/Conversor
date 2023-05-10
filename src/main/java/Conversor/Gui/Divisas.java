package Conversor.Gui;

import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Divisas extends JPanel{
    private JTextField textFieldCantidad;
    private JComboBox<String> comboBoxDivisaOrigen;
    private JComboBox<String> comboBoxDivisaDestino;
    private JLabel labelResultado;

    public Divisas() {
        setSize(400, 200);
        setLayout(new GridLayout(4, 2));

        JLabel labelCantidad = new JLabel("Cantidad:");
        textFieldCantidad = new JTextField();
        JLabel labelDivisaOrigen = new JLabel("Divisa Origen:");
        comboBoxDivisaOrigen = new JComboBox<>(new String[]{"USD", "EUR", "JPY","MXN","ARS"});
        JLabel labelDivisaDestino = new JLabel("Divisa Destino:");
        comboBoxDivisaDestino = new JComboBox<>(new String[]{"USD", "EUR", "JPY","MXN","ARS"});
        JButton buttonConvertir = new JButton("Convertir");
        labelResultado = new JLabel("");
 
        buttonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirDivisa();
            }
        });

        add(labelCantidad);
        add(textFieldCantidad);
        add(labelDivisaOrigen);
        add(comboBoxDivisaOrigen);
        add(labelDivisaDestino);
        add(comboBoxDivisaDestino);
        add(buttonConvertir);
        add(labelResultado);
    }

    private void convertirDivisa() {
        double cantidad = Double.parseDouble(textFieldCantidad.getText());
        String divisaOrigen = (String) comboBoxDivisaOrigen.getSelectedItem();
        String divisaDestino = (String) comboBoxDivisaDestino.getSelectedItem();
        double factorConversion = obtenerFactorConversion(divisaOrigen, divisaDestino);

        double resultado = cantidad * factorConversion;

        labelResultado.setText(String.format("%.2f %s = %.2f %s", cantidad, divisaOrigen, resultado, divisaDestino));
    }

    private double obtenerFactorConversion(String divisaOrigen, String divisaDestino) {
    String apiKey = "1e7b0177bac00cfd18ce62a6"; // Reemplaza con tu API key de ExchangeRate-API

    String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",apiKey, divisaOrigen, divisaDestino);
    try {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            double factorConversion = jsonObject.getDouble("conversion_rate");
            reader.close();

            return factorConversion;
        } else {
            System.out.println("Error en la conexión: " + responseCode);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return 0.0; // Valor de retorno temporal en caso de error
    }    
    public void iniciardivisas(){
        setVisible(true);
    }
}
