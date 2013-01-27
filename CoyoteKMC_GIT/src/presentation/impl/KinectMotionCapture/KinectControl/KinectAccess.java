/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Color;
import presentation.impl.KinectMotionCapture.layers.KMCHandsLayer;
import java.awt.Dimension;
import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.OpenNI.Context;
import org.OpenNI.StatusException;

/**
 *
 * @author lucas
 */
public class KinectAccess implements Serializable {

    private static EKinectModeView configuration;
    private static boolean securityMode = false;

    protected static boolean isSecurityMode() {
        return securityMode;
    }

    public static void setSecurityMode(boolean securityMode) {
        KinectAccess.securityMode = securityMode;
    }

    /**
     * Método utilizado pelo KinectManager
     *
     * @return
     */
    protected static EKinectModeView getConfiguration() {
        return configuration;
    }

    public static synchronized Skeleton getSkeletons() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        SkeletonUser s = KinectManager.getSkels() == null ? new SkeletonUser(null, 1) : KinectManager.getSkels();
        Skeleton sk = new Skeleton(s);

        //   System.out.println("Teste: SK "+sk.getHead().getZ());
        return sk;
    }

    public static synchronized SkeletonBone getSkeletonsBone() throws Exception {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        SkeletonUser s = KinectManager.getSkels() == null ? new SkeletonUser(null, 1) : KinectManager.getSkels();
        SkeletonBone sk = new SkeletonBone(s);

        //   System.out.println("Teste: SK "+sk.getHead().getZ());
        return sk;
    }

    public static synchronized SkeletonUser getSkeletonUser(int idUser) {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        return KinectAccess.getSkeletonUser(idUser);
    }

    public static synchronized Image getScreenUsers(Color color_user) {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        return KinectManager.screenUsers(color_user);
    }

    public static synchronized EKinectModeView getConfigurationManager() {
        return KinectManager.getConfiguration();
    }

    public static synchronized void setConfiguration(EKinectModeView configuration) {
        KinectAccess.configuration = configuration;
    }

    public static synchronized boolean startedKinectCommunication() {
        return KinectManager.started();
    }

    public static synchronized Image getNightVision() {
        if (securityMode) {
            try {
                KinectManager.getContext().waitAnyUpdateAll();
            } catch (StatusException ex) {
                System.out.println("Não foi possível realizar update");
            }
            return KinectManager.getIRImage();
        } else {
            try {
                KinectManager.getContext().waitAnyUpdateAll();
            } catch (StatusException ex) {
                System.out.println("Não foi possível realizar update");
            }
            return KinectManager.updateImage();
        }
    }

    public static synchronized Image getImageRGB24() { //retirada do synchronized
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            System.out.println("Não foi possível realizar update");
        }
        return KinectManager.updateImage();
    }

    public static synchronized Image getDephtImageWithUser() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            Logger.getLogger(KinectAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return KinectManager.getUserDepthsImage();
    }

    public static synchronized Dimension getPrefSize() {
        return KinectManager.getPrefSize();
    }

    public static synchronized float[] getHistogramDepht() {
        try {
            KinectManager.getContext().waitAnyUpdateAll();
        } catch (StatusException ex) {
            Logger.getLogger(KinectAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return KinectManager.getHistogram();
    }

    /**
     * Fechar acesso ao kinect
     */
    public static synchronized void closeAccess() {
        KinectManager.closeDown();
    }
}
