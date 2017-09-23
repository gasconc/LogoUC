/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logouc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author macuartin
 */
public class Tablero extends JFrame {

    private LogoUC logo1;

    public Tablero() {
        super("Logo UC");
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        logo1 = new LogoUC();
        contenedor.add(logo1);
        setSize(400, 400);
        setVisible(true);
    }

}
