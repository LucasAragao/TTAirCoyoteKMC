/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.rgb;

import presentation.impl.KinectMotionCapture.layers.ScreenPanel;
import java.awt.Frame;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.layers.LayerRGB;

/**
 *
 * @author lucas
 */
public class SampleRGB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       
        
        ScreenPanel p = new ScreenPanel();
        LayerRGB rgb = new LayerRGB();
        p.addLayerShape(rgb);

        Frame f = new Frame();
        f.add(p);
        f.setSize(KinectAccess.getPrefSize());
        f.setVisible(true);
    }
}
