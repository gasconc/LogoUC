/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logouc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author gasconc
 */
public class LogoUC extends JPanel {

    //Atributos de la clase LogoUC
    private Pieza[] arreglo;
    private Pieza[] arregloEsclavo;
    private Pieza aux = null;
    private JTextField tf1,tf2;
    private JLabel etq1, etq2;
    private JButton btn1;
    private JPanel panelSuperior, panelCentral, panelInferior;
    

    //metodo constructor de LogoUC
    public LogoUC() {
        super();
        
        //Panel Superior
        panelSuperior = new JPanel(new FlowLayout());

        //Etiqueta 1: Intercambios
        etq1 = new JLabel("Intercambios");
        etq1.setHorizontalAlignment(SwingConstants.LEFT);

        //Campo de Texto 1: intercambios
        tf1 = new JTextField("0");
        tf1.setHorizontalAlignment(SwingConstants.LEFT);
        tf1.setColumns(3);
        tf1.setEditable(false);

        //Etiqueta 2: Giros
        etq2 = new JLabel("Giros");
        etq2.setHorizontalAlignment(SwingConstants.LEFT);

        //Campo de Texto 2: giros
        tf2 = new JTextField("0");
        tf2.setHorizontalAlignment(SwingConstants.LEFT);
        tf2.setColumns(3);
        tf2.setEditable(false);

        //Agregando elementos en panel superior
        panelSuperior.add(etq1);
        panelSuperior.add(tf1);
        panelSuperior.add(etq2);
        panelSuperior.add(tf2);

        //Panel Inferior
        panelInferior = new JPanel(new FlowLayout());

        //Boton de ordenar
        btn1 = new JButton("ORDENAR");
        btn1.setHorizontalAlignment(SwingConstants.CENTER);

        //Agregando elementos en el panel inferior
        panelInferior.add(btn1);
        ManejadorEventos manejador = new ManejadorEventos();
        btn1.addActionListener(manejador);

        //Panel Central
        panelCentral = new JPanel(new GridLayout(3,3));
       
        
        //PANEL PRINCIPAL
        setLayout(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
        add(panelSuperior,BorderLayout.NORTH);
        add(panelInferior,BorderLayout.SOUTH);
        
        
        //LOGICA DEL PROGRAMA
        arreglo = new Pieza[9];
        arregloEsclavo = new Pieza[9];
        int i = 0;
        //creacion de Arreglo con piezas
        for (int f = 1; f < 4; f++) {
            for (int c = 1; c < 4; c++) {
                String ruta = "/imagenes/LogoUC_" + String.valueOf(f) + "_" + String.valueOf(c) + ".jpg";
                ImageIcon imagen = new javax.swing.ImageIcon(getClass().getResource(ruta));
                arreglo[i] = new Pieza(f - 1, c - 1, i, imagen);
                arregloEsclavo[i] = new Pieza(f - 1, c - 1, i, imagen);
                arregloEsclavo[i].setGiro(aleatorio(0, 3));
                arregloEsclavo[i].addMouseListener(new Oyente1());
                i++;
            }
        }
        ArrayList<Integer> posiciones = generaVector(arreglo.length, 0, arreglo.length - 1);
        int k = 0;
        for (int j = 0; j < posiciones.size(); j++) {
            panelCentral.add(arregloEsclavo[posiciones.get(j)]);
        }

    }


    public static int aleatorio(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

    public static ArrayList<Integer> generaVector(int n, int a, int b) {
        ArrayList<Integer> resp;
        resp = new ArrayList();
        do {
            int tmp = aleatorio(a, b);
            if (resp.indexOf(tmp) == -1) {
                resp.add(tmp);
            }
        } while (resp.size() < n);
        return resp;
    }

    private class Oyente1 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {

            Pieza actual = (Pieza) e.getSource();
            if (e.isMetaDown()) {
                //boton derecho
                actual.setGiro((actual.getGiro() + 1) % 4);
                String yy = "" + (Integer.parseInt(tf2.getText()) + 1);
                tf2.setText(yy);
            } else //boton izquierdo
            if (aux == null) {
                aux = actual;
                aux.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            } else {
                aux.setBorder(BorderFactory.createEmptyBorder());
                aux.intercambiar(actual);
                String xx = "" + (Integer.parseInt(tf1.getText()) + 1);
                tf1.setText(xx);
                aux = null;
            }

        }
    }
    
        private class ManejadorEventos implements ActionListener { // Inicio ManejadorEventos

        public void actionPerformed(ActionEvent evento) {
            mostrarOrdenado();
            LogoUC.this.validate();
        }

    }
    

    public void mostrarOrdenado() {
        panelCentral.removeAll();
        for (int i = 0; i < arreglo.length; i++) {
             arreglo[i].setGiro(0);
             panelCentral.add(arreglo[i]);
        }
    }

}
