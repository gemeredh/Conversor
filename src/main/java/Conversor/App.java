package Conversor;

import Conversor.Gui.*;

import javax.swing.SwingUtilities;
public class App extends Inicio{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Inicio conversor = new Inicio();
                conversor.iniciar();
            }
        });

    }
}

