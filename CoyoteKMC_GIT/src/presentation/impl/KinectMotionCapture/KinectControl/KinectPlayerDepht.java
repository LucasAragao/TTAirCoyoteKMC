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

/**
 *
 * @author lucas
 */
public class KinectPlayerDepht implements Runnable {

    private static ObjectKMC objDepht;
    private static String uriRGB;
    private static LayerRecorder objLayer;
    private static ArrayList<ImageKMC_Depth> dephtList = new ArrayList<ImageKMC_Depth>();
    //  private static ArrayList<ImageKMC> dephtList = new ArrayList<ImageKMC>();
    //   private static ArrayList<SkeletonUser> skeletonList = new ArrayList<SkeletonUser>();
    private static Iterator<ImageKMC_Depth> dephtIterator;
    private static int contDepht = 0;
    private static Image Depht;
    private static KinectPlayerDepht kp = new KinectPlayerDepht();
    private static int velocity = 1;

    public static synchronized void setUriDepht(String uri) {
        uriRGB = uri;
        objDepht = new ObjectKMC(uri);
        System.out.println("Abriu o arquivo: " + objDepht.reset());
        objLayer = (LayerRecorder) objDepht.readOBJ();
        dephtList = objLayer.getDephtList();
        dephtIterator = objLayer.getDephtList().iterator();
        System.out.println("Tam" + dephtList.size());
    }

    public KinectPlayerDepht() {
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

    private static void updateDepht() {
        try {
            if (objLayer.getDephtList().get(contDepht) != null) {
                Depht = arrayToImage(objLayer.getDephtList().get(contDepht).getImage());
            }
            contDepht++;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível realizar updateRGB");
            objDepht.closeFile();
        }

    }

    public static synchronized Image playImageDepht(int velocity) {
        KinectPlayerDepht.velocity = velocity;
        return Depht;
    }

    private long getTime() {
        velocity = velocity > 10 ? 10 : velocity;
        velocity = velocity < -10 ? -10 : velocity;
        long t1 = 0;
        long t2 = 0;
        if (velocity != 0) {
            if (dephtIterator.hasNext()) {
                t1 = dephtIterator.next().getTime();
            }
            if (dephtIterator.hasNext()) {
                t2 = dephtIterator.next().getTime();
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
                    updateDepht();
                }
            } catch (Exception ex) {
            }
        }
    }
}
