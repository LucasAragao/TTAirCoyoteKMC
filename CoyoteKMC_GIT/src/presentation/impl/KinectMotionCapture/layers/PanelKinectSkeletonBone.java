/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.Bone;
import presentation.impl.KinectMotionCapture.KinectControl.EBone;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.SkeletonBone;

/**
 *
 * @author lucas
 */
public class PanelKinectSkeletonBone extends ALayerShape {

    SkeletonBone ske;

    public PanelKinectSkeletonBone() throws Exception {
        super();
        this.coordinate = new Point[2];
        this.coordinate[0] = new Point(0, 0);
        this.coordinate[1] = new Point(0, 0);
        this.setTimestampBegin(-1);
        this.setTimestampEnd(-1);
    }

    private void drawLine(Graphics g, Bone bone) {
        //bone.draw(g2d);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(7.0f));
        bone.setVisible(true);
        bone.setColor(Color.RED);
        bone.draw(g2d);
    }

    @Override
    public void draw(Graphics g2d) {
        try {
            ske = KinectAccess.getSkeletonsBone();
        } catch (Exception ex) {
            //  System.out.println("Erro na obtenção do SkeletonBone");
        }

        drawLine(g2d, ske.getBone(EBone.NECK));
        drawLine(g2d, ske.getBone(EBone.RIGHT_CLAVICLE));
        drawLine(g2d, ske.getBone(EBone.LEFT_CLAVICLE));
        drawLine(g2d, ske.getBone(EBone.RIGHT_ARM));
        drawLine(g2d, ske.getBone(EBone.LEFT_ARM));
        drawLine(g2d, ske.getBone(EBone.RIGHT_FOREARM));
        drawLine(g2d, ske.getBone(EBone.LEFT_FOREARM));
        drawLine(g2d, ske.getBone(EBone.RIGHT_RIBS));
        drawLine(g2d, ske.getBone(EBone.LEFT_RIBS));
        drawLine(g2d, ske.getBone(EBone.RIGHT_WAIST));
        drawLine(g2d, ske.getBone(EBone.LEFT_WAIST));
        drawLine(g2d, ske.getBone(EBone.PELVIS));
        drawLine(g2d, ske.getBone(EBone.RIGHT_FEMORAL));
        drawLine(g2d, ske.getBone(EBone.LEFT_FEMORAL));
        drawLine(g2d, ske.getBone(EBone.RIGHT_SHIN));
        drawLine(g2d, ske.getBone(EBone.LEFT_SHIN));
        // this.repaint();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
}
