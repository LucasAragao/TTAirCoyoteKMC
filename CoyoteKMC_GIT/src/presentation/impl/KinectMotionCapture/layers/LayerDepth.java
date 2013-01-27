/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.Graphics;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.KinectPlayerDepht;
import presentation.impl.KinectMotionCapture.KinectControl.KinectRecorder;

/**
 *
 * @author lucas
 */
public class LayerDepth extends ALayerShape {

    int width = KinectAccess.getPrefSize().width;
    int height = KinectAccess.getPrefSize().height;
int i = 0;
    public LayerDepth() throws Exception {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(KinectAccess.getDephtImageWithUser(), 0, 0, width, height, null);
        
        /*KinectRecorder.recorderImageDepht();
        i++;
        if(i >= 100){
            i = 0;
            KinectRecorder.closeFileDepht();
        }*/
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
