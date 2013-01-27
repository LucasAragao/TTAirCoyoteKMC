/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.OpenNI.StatusException;

/**
 *
 * @author lucas
 */
public class KinectRecorder {

    private static String uriRGB;
    private static String uriRGBUser;
    private static String uriDepht;
    private static String uriSkeletonUser;
    private static ObjectKMC objRGB;
    private static ObjectKMC objDepht;
    private static ObjectKMC objUser;
    private static ObjectKMC objSkeleton;
    private static LayerRecorder lr = new LayerRecorder();
    private static int tamBuffer = 200;
    private static long time_init = 0;
    //------------------------------------
    private static LinkedList<ObjectKMC> list_obj_rgb = new LinkedList<ObjectKMC>();


    public static void recRGB2(){
        
        
    }
    
    public static synchronized void setUriRGB(String uri) {
        uriRGB = uri;
        objRGB = new ObjectKMC(uri);

    }

    public static void setUriDepht(String uri) {
        uriDepht = uri;
        objDepht = new ObjectKMC(uri);
    }

    public static void setUriSkeleton(String uri) {
        uriSkeletonUser = uri;
        objSkeleton = new ObjectKMC(uri);
    }

    public static void setUriRGBUser(String uri) {
        uriRGBUser = uri;
        objUser = new ObjectKMC(uri);
    }

    public static String getUriRGBUser() {
        return uriRGBUser;
    }

    public static String getUriRGB() {
        return uriRGB;
    }

    public static String getUriDepht() {
        return uriDepht;
    }

    public static String getUriSkeletonUser() {
        return uriSkeletonUser;
    }

    private static void startRecorder() {
        if (time_init == 0) {
            time_init = System.currentTimeMillis();
            lr.setTime_init(time_init);
        }
    }

    private static byte[] imageToArray(BufferedImage image) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", buffer);
        } catch (IOException ex) {
            Logger.getLogger(KinectRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buffer.toByteArray();
    }

    public static synchronized void recorderImageRGB() {

        //        KinectManager.getContext().waitAnyUpdateAll();
        startRecorder();
        lr.setRgbList(imageToArray((BufferedImage) KinectAccess.getImageRGB24()));
        bufferingRGB();
    }

    private static void bufferingRGB() {
        if (lr.getRgbListSize() >= tamBuffer) {
            tamBuffer += 200;
            recRGB();
        }
    }

    private static void bufferingDepht() {
        if (lr.getDephtListSize() >= tamBuffer) {
            tamBuffer += 200;
            recDepht();
        }
    }

    private static void bufferingUser() {
        if (lr.getUserListSize() >= tamBuffer) {
            tamBuffer += 200;
            recUser();
        }
    }

    private static void bufferingSkeleton() {
        if (lr.getSkeletonListSize() >= tamBuffer) {
            tamBuffer += 200;
            recSkeleton();
        }
    }

    private static void recRGB() {

        objRGB.reset();
        objRGB.rewrite();
        objRGB.writeOBJ(lr);
        objRGB.closeFile();
        System.out.println("ARQUIVO GRAVADO COM SUCESSO!");
    }

    private static void recDepht() {
        objDepht.reset();
        objDepht.rewrite();
        objDepht.writeOBJ(lr);
        objDepht.closeFile();
        System.out.println("ARQUIVO GRAVADO COM SUCESSO!");
    }

    private static void recUser() {
        objUser.reset();
        objUser.rewrite();
        objUser.writeOBJ(lr);
        objUser.closeFile();
        System.out.println("ARQUIVO GRAVADO COM SUCESSO!");
    }

    private static void recSkeleton() {
        objSkeleton.reset();
        objSkeleton.rewrite();
        objSkeleton.writeOBJ(lr);
        objSkeleton.closeFile();
        System.out.println("ARQUIVO GRAVADO COM SUCESSO!");
    }

    public static synchronized void recorderImageDepht() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
            startRecorder();
            lr.setDephtList(imageToArray((BufferedImage) KinectAccess.getDephtImageWithUser()), KinectAccess.getHistogramDepht());
            bufferingDepht();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
            objRGB.closeFile();
        }
    }

    public static synchronized void recorderImageUser() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
            startRecorder();
            lr.setUserList(imageToArray((BufferedImage) KinectAccess.getScreenUsers(null)));
            bufferingUser();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
            objUser.closeFile();
        }
    }

    public static synchronized void recorderSkeleton() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
            startRecorder();
            Skeleton su = KinectAccess.getSkeletons();
            // System.out.println("Teste"+su.getListPoints().get(0).getX());
            lr.setSkeletonList(su);
            bufferingSkeleton();
        } catch (Exception ex) {
            System.out.println("Não foi possível realizar update Skeleton");
            objSkeleton.closeFile();
        }
    }

    public static synchronized void closeFileDepht() {
        try {
            recDepht();
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o arquivo");
        }
    }

    public static synchronized void closeFileRGB() {
        try {
            recRGB();
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o arquivo");
        }
    }

    public static synchronized void closeFileUser() {
        try {
            recUser();
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o arquivo");
        }
    }

    public static synchronized void closeFileSkeleton() {
        try {
            recSkeleton();
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o arquivo");
        }
    }

    public static synchronized void stopRecorderRGB() {
        //TODO
    }

    public static synchronized Image getScreenUsers() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        return KinectManager.screenUsers(null);
    }

    public static synchronized Image getDephtImageWithUser() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        return KinectManager.getUserDepthsImage();
    }

    public static synchronized Dimension getPrefSize() {
        return KinectManager.getPrefSize();
    }

    public static synchronized void closeAccess() {
        KinectManager.closeDown();
    }
}
