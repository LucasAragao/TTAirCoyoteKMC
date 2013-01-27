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
public class LayerRecorder implements Serializable, Cloneable {

    private ArrayList<ImageKMC> rgbList = new ArrayList<ImageKMC>();
    private ArrayList<ImageKMC_Depth> dephtList = new ArrayList<ImageKMC_Depth>();
    private ArrayList<ImageKMC> userList = new ArrayList<ImageKMC>();
    private ArrayList<Skeleton> skeletonList = new ArrayList<Skeleton>();
    private long time_init;

    public long getTime_init() {
        return time_init;
    }

    public void setTime_init(long time_init) {
        this.time_init = time_init;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    
    public ArrayList<ImageKMC> getUserList() {
        return userList;
    }

    public void setUserList(byte[] image) {
        userList.add(new ImageKMC(image));
    }

    public int getUserListSize() {
        return userList.size();
    }

    public void setRgbList(byte[] image) {
        rgbList.add(new ImageKMC(image));
    }

    public void setDephtList(byte[] img, float[] depth) {
        dephtList.add(new ImageKMC_Depth(img,depth));
    }

    public void setSkeletonList(Skeleton skeleton) {
        skeletonList.add(skeleton);
    }

    public int getRgbListSize() {
        return rgbList.size();
    }

    public int getDephtListSize() {
        return dephtList.size();
    }

    public int getSkeletonListSize() {
        return skeletonList.size();
    }

    public ArrayList<ImageKMC> getRgbList() {
        return rgbList;
    }

    public ArrayList<ImageKMC_Depth> getDephtList() {
        return dephtList;
    }

    public ArrayList<Skeleton> getSkeletonList() {
        return skeletonList;
    }
}
