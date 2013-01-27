/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.depth;

import java.awt.Frame;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.layers.LayerDepth;
import presentation.impl.KinectMotionCapture.layers.ScreenPanel;

/**
 *
 * @author lucas
 */
public class SampleDepth {

    public static void main(String[] args) throws Exception {
        ScreenPanel p = new ScreenPanel();
        LayerDepth depth = new LayerDepth();
        p.addLayerShape(depth);

        Frame f = new Frame();
        f.add(p);
        f.setSize(KinectAccess.getPrefSize());
        f.setVisible(true);
    }
}
