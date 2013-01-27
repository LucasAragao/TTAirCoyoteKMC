/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.user;

import java.awt.Color;
import java.awt.Frame;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.layers.LayerUserRGB;
import presentation.impl.KinectMotionCapture.layers.ScreenPanel;

/**
 *
 * @author lucas
 */
public class SampleUser {

    public static void main(String[] args) throws Exception {
        ScreenPanel p = new ScreenPanel();
        LayerUserRGB user = new LayerUserRGB();
        user.color = Color.BLACK;
        p.addLayerShape(user);
        Frame f = new Frame();
        f.add(p);
        f.setSize(KinectAccess.getPrefSize());
        f.setVisible(true);
    }
}
