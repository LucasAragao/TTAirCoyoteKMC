/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.util.Iterator;

/**
 *
 * @author lucas
 */
public class KinectPlayerSkeleton implements Runnable {

    private static ObjectKMC objSkeleton;
    private static String uriSkeleton;
    private static LayerRecorder objLayer;
    // private static ArrayList<Skeleton> skeletonUser = new ArrayList<Skeleton>();
    private static Iterator<Skeleton> skeletonIterator;
    private static int contSkeleton = 0;
    private static Skeleton skeleton;
    private static KinectPlayerSkeleton kp = new KinectPlayerSkeleton();
    private static int velocity = 1;
    private static boolean lock = false;

    public static synchronized void setUriSkeleton(String uri) {
        uriSkeleton = uri;
        objSkeleton = new ObjectKMC(uri);
        System.out.println("Abriu o arquivo: " + objSkeleton.reset());
        objLayer = (LayerRecorder) objSkeleton.readOBJ();
        //   skeletonUser = objLayer.getSkeletonList();
        skeletonIterator = objLayer.getSkeletonList().iterator();
        System.out.println("Tam" + objLayer.getSkeletonList().size());
    }

    public KinectPlayerSkeleton() {
        new Thread(this).start();
    }

    public static String getUriUser() {
        return uriSkeleton;
    }

    private static void updateSkeleton() {
        try {

            if (objLayer.getSkeletonList().get(contSkeleton) != null) {
                skeleton = objLayer.getSkeletonList().get(contSkeleton);
                //  System.out.println("AKI");
//                System.out.println("Entrando X da cabeça: "+contSkeleton+"  "+objLayer.getSkeletonList().get(contSkeleton).getListPoints().get(1).getX());
            }
            contSkeleton++;
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Não foi possível realizar updateSkeleton");
            //objSkeleton.closeFile();
        }

    }

    public static synchronized Skeleton playSkeleton(int velocity) {
        KinectPlayerSkeleton.velocity = velocity;
        return skeleton;
    }

    private long getTime() {
        velocity = velocity > 10 ? 10 : velocity;
        velocity = velocity < -10 ? -10 : velocity;
        long t1 = 0;
        long t2 = 0;
        if (velocity != 0) {
            if (skeletonIterator.hasNext()) {
                t1 = skeletonIterator.next().getTimeCapture();
            }
            if (skeletonIterator.hasNext()) {
                t2 = skeletonIterator.next().getTimeCapture();
            }
            if (!lock) {
                t1 = objLayer.getTime_init();
                lock = true;
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
                    updateSkeleton();
                }
            } catch (Exception ex) {
            }
        }
    }
}
