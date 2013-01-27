/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class SkeletonBone {

    private Bone[] bones = new Bone[21];
    private ArrayList<PointJoint> listPoints = new ArrayList<PointJoint>();
    private int idUser;
    private long timeCapture;
    private boolean flag = false;
    private PointJoint[] skeJoints = new PointJoint[25];

    public SkeletonBone(SkeletonUser skeUser) throws Exception {
        if (!flag) {
            this.initBones();
            flag = true;
        }
        if (skeUser != null) {
            Iterator<PointJoint> it = skeUser.getListPoints().iterator();
            while (it.hasNext()) {
                PointJoint pjT = it.next();
                skeJoints[pjT.getCod()] = pjT;
            }
            this.createAllBones();
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
        bones[1].setJoints(skeJoints[1], skeJoints[2]);
        bones[2].setJoints(skeJoints[2], skeJoints[12]);
        bones[3].setJoints(skeJoints[2], skeJoints[6]);
        bones[4].setJoints(skeJoints[12], skeJoints[13]);
        bones[5].setJoints(skeJoints[6], skeJoints[7]);
        bones[6].setJoints(skeJoints[13], skeJoints[15]);
        bones[7].setJoints(skeJoints[7], skeJoints[9]);
        bones[8].setJoints(skeJoints[12], skeJoints[3]);
        bones[9].setJoints(skeJoints[6], skeJoints[3]);
        bones[10].setJoints(skeJoints[3], skeJoints[21]);
        bones[11].setJoints(skeJoints[3], skeJoints[17]);
        bones[12].setJoints(skeJoints[21], skeJoints[17]);
        bones[13].setJoints(skeJoints[21], skeJoints[22]);
        bones[14].setJoints(skeJoints[17], skeJoints[18]);
        bones[15].setJoints(skeJoints[22], skeJoints[24]);
        bones[16].setJoints(skeJoints[18], skeJoints[20]);

    }

    private void initBones() throws Exception {
        bones[1] = new Bone(EBone.NECK, skeJoints[1], skeJoints[2]);
        bones[2] = new Bone(EBone.RIGHT_CLAVICLE, skeJoints[2], skeJoints[12]);
        bones[3] = new Bone(EBone.LEFT_CLAVICLE, skeJoints[2], skeJoints[6]);
        bones[4] = new Bone(EBone.RIGHT_ARM, skeJoints[12], skeJoints[13]);
        bones[5] = new Bone(EBone.LEFT_ARM, skeJoints[6], skeJoints[7]);
        bones[6] = new Bone(EBone.RIGHT_FOREARM, skeJoints[13], skeJoints[15]);
        bones[7] = new Bone(EBone.LEFT_FOREARM, skeJoints[7], skeJoints[9]);
        bones[8] = new Bone(EBone.RIGHT_RIBS, skeJoints[12], skeJoints[3]);
        bones[9] = new Bone(EBone.LEFT_RIBS, skeJoints[6], skeJoints[3]);
        bones[10] = new Bone(EBone.RIGHT_WAIST, skeJoints[3], skeJoints[21]);
        bones[11] = new Bone(EBone.LEFT_WAIST, skeJoints[3], skeJoints[17]);
        bones[12] = new Bone(EBone.PELVIS, skeJoints[21], skeJoints[17]);
        bones[13] = new Bone(EBone.RIGHT_FEMORAL, skeJoints[21], skeJoints[22]);
        bones[14] = new Bone(EBone.LEFT_FEMORAL, skeJoints[17], skeJoints[18]);
        bones[15] = new Bone(EBone.RIGHT_SHIN, skeJoints[22], skeJoints[24]);
        bones[16] = new Bone(EBone.LEFT_SHIN, skeJoints[18], skeJoints[20]);
        for (int i = 1; i <= 16; i++) {
            bones[i].setVisible(false);
        }

    }

    public Bone getBone(EBone id) {
        return bones[id.ordinal()];
    }

    public Bone[] getBones() {
        return bones;
    }
}
