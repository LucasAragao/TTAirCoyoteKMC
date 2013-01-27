/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import presentation.IControlPlayer;

/**
 *
 * @author lucas
 */
public class KinectPlayerRGB implements Runnable, IControlPlayer {

    private static ObjectKMC objRGB;
    private static String uriRGB;
    private static LayerRecorder objLayer;
    private static ArrayList<ImageKMC> rgbList = new ArrayList<ImageKMC>();
    //  private static ArrayList<ImageKMC> dephtList = new ArrayList<ImageKMC>();
    //   private static ArrayList<SkeletonUser> skeletonList = new ArrayList<SkeletonUser>();
    private static Iterator<ImageKMC> rgbIterator;
    private static int contRGB = 0;
    private static Image RGB;
    private static KinectPlayerRGB kp = new KinectPlayerRGB();
    private static int velocity = 1;

    public static synchronized void setUriRGB(String uri) {
        uriRGB = uri;
        objRGB = new ObjectKMC(uri);
        System.out.println("Abriu o arquivo: " + objRGB.reset());
        objLayer = (LayerRecorder) objRGB.readOBJ();
        rgbList = objLayer.getRgbList();
        rgbIterator = objLayer.getRgbList().iterator();
        System.out.println("Tam" + rgbList.size());
    }

    public KinectPlayerRGB() {
        new Thread(this).start();
    }

    public static String getUriRGB() {
        return uriRGB;
    }

    private static Image arrayToImage(byte[] array) {
        InputStream inStream = new ByteArrayInputStream(array);
        Image image = new ImageIcon(array).getImage();
        return image;
    }

    private static void updateRGB() {
        try {
            if (objLayer.getRgbList().get(contRGB) != null) {
                RGB = arrayToImage(objLayer.getRgbList().get(contRGB).getImage());
            }
            contRGB++;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível realizar updateRGB");
            objRGB.closeFile();
        }

    }

    public static synchronized Image playImageRGB(int velocity) {
        KinectPlayerRGB.velocity = velocity;
        return RGB;
    }

    private long getTime() {
        velocity = velocity > 10 ? 10 : velocity;
        velocity = velocity < -10 ? -10 : velocity;
        long t1 = 0;
        long t2 = 0;
        if (velocity != 0) {
            if (rgbIterator.hasNext()) {
                t1 = rgbIterator.next().getTime();
            }
            if (rgbIterator.hasNext()) {
                t2 = rgbIterator.next().getTime();
            }
            return velocity >= 1 ? (t2 - t1) / velocity : (t2 - t1) * -(velocity);
        }
        return Long.MAX_VALUE;
    }

    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait(getTime());
                    updateRGB();
                }
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void start() throws Exception {
        
    }

    @Override
    public void stop() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pause() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void back() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void next() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fastForward() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fastRewind() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void playWithRate(float rate) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFullTime() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTimeBegin() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTimeEnd() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCurrentTime() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void go2Time(int time) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTimeStampBegin(int timeStamp) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTimeStampEnd(int timeStamp) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPlayOnlyInTimestamp(boolean b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isToPlayOnlyInTimestamp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyIfIsToPlayInterval(int currentTime) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void open(String uri) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JComponent getVisualDisplay() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JComponent getVisualControl() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
