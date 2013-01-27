/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author lucas
 */
public class Hands {

    private Bone[] bones = new Bone[21];
    private ArrayList<PointJoint> listPoints = new ArrayList<PointJoint>();
    private int idUser;
    private long timeCapture;

    public Hands(ArrayList<PointJoint> joints, int idUser) {
        this.timeCapture = System.currentTimeMillis();
        this.listPoints = joints;
        this.idUser = idUser;
    }
    private PointJoint[] ske = new PointJoint[25];

    public Hands(SkeletonUser skeUser) {
        if (skeUser != null) {
            Iterator<PointJoint> it = skeUser.getListPoints().iterator();
            while (it.hasNext()) {
                PointJoint pjT = it.next();
                ske[pjT.getCod()] = pjT;
            }
            this.timeCapture = System.currentTimeMillis();
        }
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

    private void createAllBones() throws Exception {
        bones[1] = new Bone(EBone.NECK, ske[1], ske[2]);
        bones[2] = new Bone(EBone.RIGHT_CLAVICLE, ske[2], ske[12]);
        bones[3] = new Bone(EBone.LEFT_CLAVICLE, ske[2], ske[6]);
        bones[4] = new Bone(EBone.RIGHT_ARM, ske[12], ske[13]);
        bones[5] = new Bone(EBone.LEFT_ARM, ske[6], ske[7]);
        bones[6] = new Bone(EBone.RIGHT_FOREARM, ske[13], ske[15]);
        bones[7] = new Bone(EBone.LEFT_FOREARM, ske[7], ske[9]);
        bones[8] = new Bone(EBone.RIGHT_RIBS, ske[12], ske[3]);
        bones[9] = new Bone(EBone.LEFT_RIBS, ske[6], ske[3]);
        bones[10] = new Bone(EBone.RIGHT_WAIST, ske[3], ske[21]);
        bones[11] = new Bone(EBone.LEFT_WAIST, ske[3], ske[17]);
        bones[12] = new Bone(EBone.PELVIS, ske[21], ske[17]);
        bones[13] = new Bone(EBone.RIGHT_FEMORAL, ske[21], ske[22]);
        bones[14] = new Bone(EBone.LEFT_FEMORAL, ske[17], ske[18]);
        bones[15] = new Bone(EBone.RIGHT_SHIN, ske[22], ske[24]);
        bones[16] = new Bone(EBone.LEFT_SHIN, ske[18], ske[20]);

    }

    public Bone getBone(EBone id) {
        return bones[id.ordinal() + 1];
    }
}
