/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.Graphics;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;

/**
 *
 * @author lucas
 */
public class LayerRGB extends ALayerShape {

    int width = KinectAccess.getPrefSize().width;
    int height = KinectAccess.getPrefSize().height;

    public LayerRGB() throws Exception {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(KinectAccess.getImageRGB24(), 0, 0, width, height, null);

    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
