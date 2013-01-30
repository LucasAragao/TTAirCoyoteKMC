/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lucas
 */
public class KinectRecorderSeqImagesUser implements Runnable {

    private static Color clr = Color.BLACK;
    private static String uri = "";
    private static long time_wait = 1000;
    private static int maxQtdImages = 0;
    private static String formatImage = "png";
    private static int countImages = 0;
    private static boolean start = false;
    private static KinectRecorderSeqImagesUser rSeqImgUser = new KinectRecorderSeqImagesUser();
    private static Thread t;

    public KinectRecorderSeqImagesUser() {
        t = new Thread(this);
    }

    public static void setFormatImage(EformatImage formatImage) {
        switch (formatImage.ordinal() + 1) {
            case 1:
                KinectRecorderSeqImagesUser.formatImage = "png";
                break;
            case 2:
                KinectRecorderSeqImagesUser.formatImage = "jpg";
                break;
            case 3:
                KinectRecorderSeqImagesUser.formatImage = "bmp";
                break;
        }

    }

    public static void startRecorder() {
        t.start();
    }

    public static void supendRecord() {
        t.suspend();
    }

    public static void stopRecord() {
        t.stop();
    }

    public static String getUri() {
        return uri;
    }

    public static void setUri(String uri) {
        if (uri != null) {
            KinectRecorderSeqImagesUser.uri = uri + "//";
            File f = new File(uri);
            f.mkdirs();
        }
    }

    public static String getFormatImage() {
        return formatImage;
    }

    public static int getMaxQtdImages() {
        return maxQtdImages;
    }

    public static void setMaxQtdImages(int maxQtdImages) {
        KinectRecorderSeqImagesUser.maxQtdImages = maxQtdImages;
    }

    public static Color getClr() {
        return clr;
    }

    public static void setClr(Color clr) {
        KinectRecorderSeqImagesUser.clr = clr;
    }

    public static long getTime_wait() {
        return time_wait;
    }

    public static void setTime_wait(float time_wait) {
        KinectRecorderSeqImagesUser.time_wait = (long) (time_wait * 1000);
    }

    private static String nameFileDateFormat() {
        DateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return dFormat.format(calendar.getTime());
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    while (maxQtdImages > 0 ? countImages <= maxQtdImages : true) {
                        wait(getTime_wait());
                        BufferedImage image = (BufferedImage) KinectAccess.getScreenUsers(clr);
                        ImageIO.write(image, formatImage, new File(String.valueOf(uri + nameFileDateFormat())));
                        countImages++;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(KinectRecorder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    System.out.println("Erro na gravação da imagem");
                }
            }
        }
    }

    public enum EformatImage {

        PNG, JPG, BMP
    }
}
