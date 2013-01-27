/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.layers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import org.OpenNI.DepthGenerator;
import org.OpenNI.Point3D;
import org.OpenNI.StatusException;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.Bone;
import presentation.impl.KinectMotionCapture.KinectControl.EBone;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.PointJoint;
import presentation.impl.KinectMotionCapture.KinectControl.Skeleton;
import presentation.impl.KinectMotionCapture.KinectControl.SkeletonBone;

/**
 *
 * @author lucas
 */
public class KMCHandsLayer extends ALayerShape {

    Skeleton ske;

    public KMCHandsLayer() throws Exception {
        super();

        this.coordinate = new Point[2];
        this.coordinate[0] = new Point(0, 0);
        this.coordinate[1] = new Point(0, 0);
        this.setTimestampBegin(-1);
        this.setTimestampEnd(-1);
    }

    private void drawLine(Graphics g2d, PointJoint j1, PointJoint j2) {
        ((Graphics2D) g2d).setColor(Color.RED);
        ((Graphics2D) g2d).setStroke(new BasicStroke(40.0f));
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


        drawLine(g2d, ske.getLeftHand(), ske.getLeftHand()); //cotovelo mão

        

        drawLine(g2d, ske.getRightHand(), ske.getRightHand()); //cotovelo mão



    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
