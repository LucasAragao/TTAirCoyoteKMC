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
public class KinectPlayerUser implements Runnable{
    
      private static ObjectKMC objUser;
    private static String uriUser;
    private static LayerRecorder objLayer;
    private static ArrayList<ImageKMC> rgbUser = new ArrayList<ImageKMC>();
    private static Iterator<ImageKMC> userIterator;
    private static int contUser = 0;
    private static Image RGB;
    private static KinectPlayerUser kp = new KinectPlayerUser();
    private static int velocity = 1;

    public static synchronized void setUriUser(String uri) {
        uriUser = uri;
        objUser = new ObjectKMC(uri);
        System.out.println("Abriu o arquivo: " + objUser.reset());
        objLayer = (LayerRecorder) objUser.readOBJ();
        rgbUser = objLayer.getUserList();
        userIterator = objLayer.getUserList().iterator();
        System.out.println("Tam" + rgbUser.size());
    }

    public KinectPlayerUser() {
        new Thread(this).start();
    }

    public static String getUriUser() {
        return uriUser;
    }

    private static Image arrayToImage(byte[] array) {
        InputStream inStream = new ByteArrayInputStream(array);
        Image image = new ImageIcon(array).getImage();
        return image;
    }

    private static void updateUser() {
        try {
            if (objLayer.getUserList().get(contUser) != null) {
                RGB = arrayToImage(objLayer.getUserList().get(contUser).getImage());
                
            }
            contUser++;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível realizar updateRGB");
            objUser.closeFile();
        }

    }

    public static synchronized Image playImageUser(int velocity) {
        KinectPlayerUser.velocity = velocity;
        return RGB;
    }

    private long getTime() {
        velocity = velocity > 10 ? 10 : velocity;
        velocity = velocity < -10 ? -10 : velocity;
        long t1 = 0;
        long t2 = 0;
        if (velocity != 0) {
            if (userIterator.hasNext()) {
                t1 = userIterator.next().getTime();
            }
            if (userIterator.hasNext()) {
                t2 = userIterator.next().getTime();
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
                    updateUser();
                }
            } catch (Exception ex) {
            }
        }
    }
    
}
