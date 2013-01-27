/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author lucas
 */
public class ImageKMC implements Serializable {

    private byte[] image;
    private long time;

    public ImageKMC(byte[] image) {
        this.image = image;
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public byte[] getImage() {
        return image;
    }
}
