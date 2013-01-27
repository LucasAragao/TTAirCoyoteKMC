

package presentation;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;



/**
 * Interface com metodos pardoes de exibicao e desenho na tela
 */
public interface IDisplay
{
   public Point[] getPoits(); //implementado no AControlPlayer
   //public void paint(Graphics g);
   public void addShape(ALayerShape shape); //implementado no AControlPlayer
   public void setShapes(ArrayList vShapes); //implementado no AControlPlayer
   public ArrayList getShapes(); //implementado no AControlPlayer
   public Image getFrameImage();
   public Dimension getPreferredSize();
}
