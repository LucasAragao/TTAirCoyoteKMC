/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.skeleton;

import java.awt.Frame;
import presentation.impl.KinectMotionCapture.KinectControl.EBone;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.layers.LayerSkeletonBone;
import presentation.impl.KinectMotionCapture.layers.ScreenPanel;

/**
 *
 * @author lucas
 */
public class SampleSkeleton {

    public static void main(String[] args) throws Exception {
        ScreenPanel p = new ScreenPanel();
        LayerSkeletonBone ske = new LayerSkeletonBone();
        ske.setVisibleBoneByID(EBone.ALL);
        p.addLayerShape(ske);

        Frame f = new Frame();
        f.add(p);
        f.setSize(KinectAccess.getPrefSize());
        f.setVisible(true);
    }
}
