/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.io.Serializable;

/**
 *
 * @author lucas
 */
public class ImageKMC_Depth implements Serializable{

    private byte[] image;
    private long time;
    private float[] depth;

    public ImageKMC_Depth(byte[] image, float[] depth) {
        this.image = image;
        this.depth = depth;
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public byte[] getImage() {
        return image;
    }

    public float[] getDepth() {
        return depth;
    }
}
