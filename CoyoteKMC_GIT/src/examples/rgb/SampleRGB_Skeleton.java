/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.rgb;

import java.awt.Frame;
import presentation.impl.KinectMotionCapture.KinectControl.EBone;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.layers.LayerRGB;
import presentation.impl.KinectMotionCapture.layers.LayerSkeletonBone;
import presentation.impl.KinectMotionCapture.layers.ScreenPanel;

/**
 *
 * @author lucas
 */
public class SampleRGB_Skeleton {

    public static void main(String[] args) throws Exception {
        ScreenPanel p = new ScreenPanel();
        LayerRGB rgb = new LayerRGB();
        LayerSkeletonBone ske = new LayerSkeletonBone();
        ske.setVisibleBoneByID(EBone.ALL);
        p.addLayerShape(rgb);
        p.addLayerShape(ske);
        Frame f = new Frame();
        f.add(p);
        f.setSize(KinectAccess.getPrefSize());
        f.setVisible(true);
    }
}
