/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import presentation.ALayerShape;
import presentation.AShape3D;
import presentation.impl.KinectMotionCapture.KinectControl.PointJoint;
import presentation.impl.KinectMotionCapture.KinectControl.Skeleton;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.KinectPlayerSkeleton;

/**
 *
 * @author lucas
 */
public class SkeletonShapeImpl extends ALayerShape {
    
    private int idUser;
    Skeleton ske = KinectAccess.getSkeletons();
    
    public SkeletonShapeImpl() throws Exception {
    }
    
    private void drawLine(Graphics g2d, PointJoint j1, PointJoint j2) {
        PointJoint p1 = j1.getX() != 0.0f || j1.getY() != 0.0f ? j1 : null;
        PointJoint p2 = j2.getX() != 0.0f || j2.getY() != 0.0f ? j2 : null;
        if ((p1 != null) && (p2 != null)) {
            g2d.drawLine((int) p1.getX(), (int) p1.getY(),
                    (int) p2.getX(), (int) p2.getY());
        }
    }
    
    @Override
    public void draw(Graphics g2d) {
        //cabeca e tronco
        drawLine(g2d, ske.getHead(), ske.getNeck()); //cabeca e pescoco
        drawLine(g2d, ske.getNeck(), ske.getLeftShoulder()); //pescoço ombro esquerdo
        drawLine(g2d, ske.getNeck(), ske.getRightShoulder()); //pescoço ombro direito
        drawLine(g2d, ske.getLeftShoulder(), ske.getTorso()); //ombro esquerdo torso
        drawLine(g2d, ske.getRightShoulder(), ske.getTorso()); //ombro direito torso

        //braço esquerdo
        drawLine(g2d, ske.getLeftShoulder(), ske.getLeftElbow()); //ombro cotovelo
        drawLine(g2d, ske.getLeftElbow(), ske.getLeftHand()); //cotovelo mão

        //braço direito
        drawLine(g2d, ske.getRightShoulder(), ske.getRightElbow()); //ombro cotovelo
        drawLine(g2d, ske.getRightElbow(), ske.getRightHand()); //cotovelo mão

        //tronco e cintura
        drawLine(g2d, ske.getTorso(), ske.getLeftHip()); //torso quadril esquerdo
        drawLine(g2d, ske.getTorso(), ske.getRightHip()); //torso quadril direito
        drawLine(g2d, ske.getLeftHip(), ske.getRightHip()); //quadril esquerdo e direito

        //perna esquerda
        drawLine(g2d, ske.getLeftHip(), ske.getLeftKnee()); //quadril joelho
        drawLine(g2d, ske.getLeftKnee(), ske.getLeftFoot()); //joelho pe

        //perna direita
        drawLine(g2d, ske.getRightHip(), ske.getRightKnee()); //quadril joelho
        drawLine(g2d, ske.getRightKnee(), ske.getRightFoot()); //joelho pe
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   
}
