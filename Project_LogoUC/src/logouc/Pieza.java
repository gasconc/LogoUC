/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logouc;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 *
 * @author gasconc
 */
public class Pieza extends JLabel {

  private int fila;
  private int columna;
  private int giro;
  private int ubicacion;
  private ImageIcon imagenes[];

  public Pieza(int fila, int columna, int ubicacion, ImageIcon imagen) {
    super();
    this.fila = fila;
    this.columna = columna;
    this.ubicacion = ubicacion;
    imagenes = new ImageIcon[4];
    giro = 0;
    for (int i = 0; i < imagenes.length; i++) {
      if ( i == 0){
        imagenes[i] = imagen;
      }else{
        imagenes[i] = rotateIcon( imagenes[i-1], 90);
      }  
    }
  }

  public ImageIcon rotateIcon(ImageIcon icon, int angle) {
    int w = icon.getIconWidth();
    int h = icon.getIconHeight();
    int type = BufferedImage.TYPE_INT_RGB;
    BufferedImage image = new BufferedImage(h, w, type);
    Graphics2D g2 = image.createGraphics();
    double x = (h - w) / 2.0;
    double y = (w - h) / 2.0;
    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
    at.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
    g2.drawImage(icon.getImage(), at, this);
    g2.dispose();
    icon = new ImageIcon(image);
    return icon;
  }

  @Override
  protected void paintComponent(Graphics grphcs) {
    super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
    grphcs.drawImage(imagenes[getGiro()].getImage(), 0, 0,getWidth(), getHeight(), null);
  
  }

  /**
   * @return the giro
   */
  public int getGiro() {
    return giro;
  }
  
  public void intercambiar(Pieza obj){
    int aux;
    aux = fila;
    fila = obj.fila;
    obj.fila = aux;
    
    aux = columna;
    columna = obj.columna;
    obj.columna = aux;
    
    aux = giro;
    giro = obj.giro;
    obj.giro = aux;
    
    ImageIcon imagenesAUX[] = imagenes;
    imagenes = obj.imagenes;
    obj.imagenes = imagenesAUX;
    
    repaint();
    obj.repaint();
  }

  /**
   * @param giro the giro to set
   */
  public void setGiro(int giro) {
    this.giro = giro;
    repaint();
  }
  
  public void Ordenar(boolean b){
      
  }
  
  
  
  
}
