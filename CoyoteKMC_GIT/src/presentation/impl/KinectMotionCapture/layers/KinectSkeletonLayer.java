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
import java.awt.Stroke;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.KinectPlayerSkeleton;
import presentation.impl.KinectMotionCapture.KinectControl.KinectRecorder;
import presentation.impl.KinectMotionCapture.KinectControl.PointJoint;
import presentation.impl.KinectMotionCapture.KinectControl.Skeleton;

/**
 *
 * @author
 */
public class KinectSkeletonLayer extends ALayerShape {

    Skeleton ske;

    public KinectSkeletonLayer() throws Exception {
        super();

        this.coordinate = new Point[2];
        this.coordinate[0] = new Point(0, 0);
        this.coordinate[1] = new Point(0, 0);
        this.setTimestampBegin(-1);
        this.setTimestampEnd(-1);
    }

    private void drawLine(Graphics g2d, PointJoint j1, PointJoint j2) {
        ((Graphics2D) g2d).setBackground(Color.RED);
        ((Graphics2D) g2d).setStroke(new BasicStroke(7.0f));
        try {
            PointJoint p1 = j1.getX() != 0.0f || j1.getY() != 0.0f ? j1 : null;
            PointJoint p2 = j2.getX() != 0.0f || j2.getY() != 0.0f ? j2 : null;

            g2d.drawLine((int) p1.getX(), (int) p1.getY(),
                    (int) p2.getX(), (int) p2.getY());

        } catch (Exception ex) {
        }
    }

    @Override
    public void draw(Graphics g2d) {

        try {
            //  ske = new Skeleton(KinectAccess.getSkeletons());
            //  KinectRecorder.recorderSkeleton();
            // System.out.println("chamou");
            ske = KinectAccess.getSkeletons();
            // System.out.println("Cabeça: "+ske.getHead().getX());
        } catch (Exception ex) {
        }


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
