/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JPanel;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;

/**
 *
 * @author lucas
 */
public class ScreenPanel extends JPanel {

    Vector shapes = new Vector();
    int width = KinectAccess.getPrefSize().width;
    int height = KinectAccess.getPrefSize().height;

    public void addLayerShape(ALayerShape shape){
        this.shapes.add(shape);
    }
    

    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        for (int i = 0; i < shapes.size(); i++) {
            ALayerShape shape = (ALayerShape) shapes.elementAt(i);
            shape.draw(g);
        }
        this.repaint();
    }
}
