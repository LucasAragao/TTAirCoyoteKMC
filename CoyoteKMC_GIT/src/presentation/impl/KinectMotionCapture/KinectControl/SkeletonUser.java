/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class SkeletonUser implements Serializable {

    private ArrayList<PointJoint> listPoints = new ArrayList<PointJoint>();
    private int idUser;
    private long timeCapture;

    public SkeletonUser(ArrayList<PointJoint> joints, int idUser) {
        this.timeCapture = System.currentTimeMillis();
        this.listPoints = joints;
        this.idUser = idUser;
    }

    public long getTimeCapture() {
        return timeCapture;
    }

    public ArrayList<PointJoint> getListPoints() {
        return listPoints;
    }

    public int getIdUser() {
        return idUser;
    }
}
