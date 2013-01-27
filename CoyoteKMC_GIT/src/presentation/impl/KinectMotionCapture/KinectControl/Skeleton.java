/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.io.Serializable;
import java.util.Iterator;
import org.OpenNI.Point3D;
import org.OpenNI.SkeletonJoint;

/**
 *
 * @author lucas
 * @since 2012
 * @version 1.0
 */
public class Skeleton implements Serializable {

    private long timeCapture;

    public long getTimeCapture() {
        return timeCapture;
    }

    public void setTimeCapture(long timeCapture) {
        this.timeCapture = timeCapture;
    }
    private PointJoint[] ske = new PointJoint[25];

    public Skeleton(SkeletonUser skeUser) {
        if (skeUser != null) {
            Iterator<PointJoint> it = skeUser.getListPoints().iterator();
            while (it.hasNext()) {
                PointJoint pjT = it.next();
                ske[pjT.getCod()] = pjT;
            }
            this.timeCapture = System.currentTimeMillis();
        }
    }

    public Skeleton(PointJoint head, PointJoint neck, PointJoint torso, PointJoint left_shoulder,
            PointJoint left_elbow, PointJoint left_hand, PointJoint right_shoulder, PointJoint right_elbow, PointJoint right_hand,
            PointJoint left_hip, PointJoint left_knee, PointJoint left_foot, PointJoint right_hip, PointJoint right_knee, PointJoint right_foot) {

        ske[1] = head;
        ske[2] = neck;
        ske[3] = torso;
        ske[6] = left_shoulder;
        ske[7] = left_elbow;
        ske[9] = left_hand;
        ske[12] = right_shoulder;
        ske[13] = right_elbow;
        ske[15] = right_hand;
        ske[17] = left_hip;
        ske[18] = left_knee;
        ske[20] = left_foot;
        ske[21] = right_hip;
        ske[22] = right_knee;
        ske[24] = right_foot;
        this.timeCapture = System.currentTimeMillis();
    }

    public PointJoint[] getSke() {
        return ske;
    }

    public PointJoint getHead() { // Cabeça
        return ske[1];
    }

    public PointJoint getNeck() { // Pescoço
        return ske[2];
    }

    public PointJoint getTorso() { //torso
        return ske[3];
    }

    public PointJoint getLeftShoulder() { //Ombro esquerdo
        return ske[6];
    }

    public PointJoint getLeftElbow() { //cotovelo esquerdo
        return ske[7];
    }

    public PointJoint getLeftHand() { //Mão esquerda
        return ske[9];
    }

    public PointJoint getRightShoulder() { //Ombro direito
        return ske[12];
    }

    public PointJoint getRightElbow() { //Cotovelo direito 
        return ske[13];
    }

    public PointJoint getRightHand() { //Mão Direita
        return ske[15];
    }

    public PointJoint getLeftHip() { //quadril esquerdo
        return ske[17];
    }

    public PointJoint getLeftKnee() { //joelho esquerdo
        return ske[18];
    }

    public PointJoint getLeftFoot() { //pé esquerdo
        return ske[20];
    }

    public PointJoint getRightHip() { //quadril direito
        return ske[21];
    }

    public PointJoint getRightKnee() { //joelho direito
        return ske[22];
    }

    public PointJoint getRightFoot() { //pé direito
        return ske[24];
    }

    @Override
    public String toString() {
        return ske[1].toString() + ske[2].toString() + ske[3].toString() + ske[6].toString()
                + ske[7].toString() + ske[9].toString() + ske[12].toString() + ske[13].toString()
                + ske[15].toString() + ske[17].toString() + ske[18].toString() + ske[20].toString()
                + ske[21].toString() + ske[22].toString() + ske[24].toString();
    }
}
