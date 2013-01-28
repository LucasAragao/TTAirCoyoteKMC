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
public class LayerNightVision extends ALayerShape {

    int width = KinectAccess.getPrefSize().width;
    int height = KinectAccess.getPrefSize().height;

    public LayerNightVision() throws Exception {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(KinectAccess.getNightVision(), 0, 0, width, height, null);
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
