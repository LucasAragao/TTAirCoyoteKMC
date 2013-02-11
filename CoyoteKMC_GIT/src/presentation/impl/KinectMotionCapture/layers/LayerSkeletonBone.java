/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.Bone;
import presentation.impl.KinectMotionCapture.KinectControl.EBone;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.SkeletonBone;

/**
 *
 * @author lucas
 */
public class LayerSkeletonBone extends ALayerShape {

    SkeletonBone ske;
    EBone bone;
    boolean[] bones = new boolean[17];
    List<EBone> listBones;
    float densityLine = 7.0f;
    Color colorLine = Color.RED;

    public LayerSkeletonBone() throws Exception {
    }

    public boolean[] getBones() {
        return bones;
    }

    @Override
    public void draw(Graphics g2d) {
        try {
            ske = KinectAccess.getSkeletonsBone();
            if (this.isVisible()) {
                for (int i = 1; i <= ske.getBones().length; i++) {
                    ske.getBones()[i].setVisible(bones[i]);
                    ske.getBones()[i].draw(g2d);
                }
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public String toString() {
        return this.getLabel();
    }

    public void setVisibleBoneByID(EBone eBone) {
        if (eBone == EBone.ALL) {
            for (int i = 1; i <= 16; i++) {
                bones[i] = true;
            }
        } else {
            bones[eBone.ordinal()] = true;
        }
    }

    public void setInvisibleBoneByID(EBone eBone) {
        if (eBone == EBone.ALL) {
            for (int i = 1; i <= 16; i++) {
                bones[i] = false;
            }
        } else {
            bones[eBone.ordinal()] = false;
        }
    }
}
