/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.OpenNI.AlternativeViewpointCapability;
import org.OpenNI.Context;
import org.OpenNI.DepthGenerator;
import org.OpenNI.DepthMetaData;
import org.OpenNI.GeneralException;
import org.OpenNI.IRGenerator;
import org.OpenNI.IRMetaData;
import org.OpenNI.ImageGenerator;
import org.OpenNI.ImageMetaData;
import org.OpenNI.License;
import org.OpenNI.MapOutputMode;
import org.OpenNI.PixelFormat;
import org.OpenNI.SceneMetaData;
import org.OpenNI.StatusException;
import org.OpenNI.UserGenerator;

/**
 *
 * @author lucas
 */
public final class KinectManager implements Serializable {

    private static Context context;
    private static ImageGenerator imageGen;
    private static DepthGenerator depthGen;
    private static DepthMetaData depthMD;
    private static SceneMetaData sceneMD;
    //------------------------------------
    private static IRGenerator irGen;
    private static final int MIN_8_BIT = 0;
    private static final int MAX_8_BIT = 255;
    private static BufferedImage imageIR = null;
    private static boolean securityMode = false;
    //  private static IRMetaData irMD;
    //-------------------------------------
    private static int imWidth;
    private static int imHeight;
    private static int fps;
    //------------------------------------ 
    private static boolean started = false;
    private static final KinectManager INSTANCE = new KinectManager();
    private static EKinectModeView configuration;
    private static Dimension dimension;
    //----------------------------------
    private static byte[] imgbytes;
    private static float histogram[];        // for the depth values

    public static float[] getHistogram() {
        return histogram;
    }
    private static int maxDepth = 0;
    private static final int MAX_DEPTH_SIZE = 10000;
    private static Color USER_COLORS[] = {
        Color.RED, Color.BLUE, Color.CYAN, Color.GREEN,
        Color.MAGENTA, Color.PINK, Color.YELLOW, Color.WHITE};
    //private static Skeletons skels;
    //---------------------------------
    private static int[] cameraPixels = new int[imWidth * imHeight];
    private static int hideBGPixel;
    private static BufferedImage cameraImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
    private static AlternativeViewpointCapability altViewCap;
    private static Skeletons skels;
    //-------------------------------Hands

    private static void setColorUser(Color color_user) {
        int rowStart = 0;
        int bbIdx;
        int i = 0;
        int rowLen = imWidth * 3;
        for (int row = 0; row < imHeight; row++) {
            bbIdx = rowStart;
            for (int col = 0; col < imWidth; col++) {
                cameraPixels[i++] =
                        color_user.getRGB();
            }
            rowStart += rowLen;
        }
    }

    protected static EKinectModeView getConfiguration() {
        return configuration;
    }

    private KinectManager() {
        configuration = KinectAccess.getConfiguration();
        securityMode = KinectAccess.isSecurityMode();
        initialize(configuration);
        System.out.println("Carregou");
    }

    private void initialize(EKinectModeView mode) {
        if (!securityMode) {
            if (mode == null) {
                mode = EKinectModeView.RES_640_480_30;
                System.out.println("CONFIGURAÇÃO NULA OU INVÁLIDA, MODO PADRÃO ATIVADO (640,480,30)");
            }

            switch (mode) {
                case RES_640_480_30:
                    imWidth = 640;
                    imHeight = 480;
                    fps = 30;
                    break;
                case RES_1280_1024_15:
                    imWidth = 1280;
                    imHeight = 1024;
                    fps = 15;
                    break;
                default:
                    imWidth = 640;
                    imHeight = 480;
                    fps = 30;
            }
            dimension = new Dimension(imWidth, imHeight);
            histogram = new float[MAX_DEPTH_SIZE];
            imgbytes = new byte[imWidth * imHeight * 3];
            configOpenNI();
            start();
        } else {
            configOpenNISecurity();
            dimension = new Dimension(imWidth, imHeight);
        }
    }

    private void configOpenNISecurity() // create context
    {
        try {
            context = new Context();
// add the NITE License
            License license = new License("PrimeSense",
                    "0KOIk2JeIBYClPWVnMoRKn5cdY4=");
            context.addLicense(license);

            irGen = IRGenerator.create(context);

            MapOutputMode mapMode = new MapOutputMode(640, 480, 30);


            irGen.setMapOutputMode(mapMode);

            // set Mirror mode for all
            context.setGlobalMirror(true);
            context.startGeneratingAll();
            System.out.println("Started context generating...");
            IRMetaData irMD = irGen.getMetaData();
            imWidth = irMD.getFullXRes();
            imHeight = irMD.getFullYRes();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    } // end of configOpenNI()

    private static void configOpenNI() // create context and imageIR generator
    {
        try {
            context = new Context();


            // add the NITE Licence
            License licence = new License("PrimeSense", "0KOIk2JeIBYClPWVnMoRKn5cdY4=");   // vendor, key
            context.addLicense(licence);

            imageGen = ImageGenerator.create(context);

            depthGen = DepthGenerator.create(context);


            MapOutputMode mapMode = new MapOutputMode(imWidth, imHeight, fps);   // xRes, yRes, FPS

            imageGen.setMapOutputMode(mapMode);
            imageGen.setPixelFormat(PixelFormat.RGB24);


            boolean hasAltView =
                    depthGen.isCapabilitySupported("AlternativeViewPoint");   // returns true
            if (hasAltView) {
                altViewCap = depthGen.getAlternativeViewpointCapability();
                altViewCap.setViewpoint(imageGen);

            }


            depthGen.setMapOutputMode(new MapOutputMode(640, 480, 30));

            // set Mirror mode for all
            context.setGlobalMirror(true);

            depthMD = depthGen.getMetaData();


            UserGenerator userGen = UserGenerator.create(context);
            sceneMD = userGen.getUserPixels(0);

            skels = new Skeletons(userGen, depthGen);


        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }  // end of configOpenNI()

    protected static SkeletonUser getSkels() {
        return skels.getSkeletonUser();
    }

    protected static Graphics2D getGraphicsSkeleton(Graphics2D g2d) {
        return skels.draw(g2d);
    }

    protected static SkeletonUser getSkelUser(int idUser) {
        return skels.getSkeletonUser(idUser);
    }

    private static BufferedImage createGrayIm(ShortBuffer irSB,
            int minIR, int maxIR) {
// create a grayscale imageIR
        BufferedImage image = new BufferedImage(imWidth, imHeight,
                BufferedImage.TYPE_BYTE_INDEXED);
// access the imageIR's data buffer
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        float displayRatio = (float) (MAX_8_BIT - MIN_8_BIT) / (maxIR - minIR);
// scale the converted IR data over the grayscale range;
        int i = 0;
        while (irSB.remaining() > 0) {
            int irVal = irSB.get();
            int out;
            if (irVal <= minIR) {
                out = MIN_8_BIT;
            } else if (irVal >= maxIR) {
                out = MAX_8_BIT;
            } else {
                out = (int) ((irVal - minIR) * displayRatio);
            }
            data[i++] = (byte) out;
// store in the data buffer
        }
        return image;
    } // end of createGrayIm()

    protected static Image getIRImage() {
        updateIRImage();
        return imageIR;
    }

    private static void updateIRImage() {
        try {
            ShortBuffer irSB = irGen.getIRMap().createShortBuffer();
// scan the IR data, storing the min and max values
            int minIR = irSB.get();
            int maxIR = minIR;
            while (irSB.remaining() > 0) {
                int irVal = irSB.get();
                if (irVal > maxIR) {
                    maxIR = irVal;
                }
                if (irVal < minIR) {
                    minIR = irVal;
                }
            }
            irSB.rewind();
// convert the IR values into 8-bit grayscales
            imageIR = createGrayIm(irSB, minIR, maxIR);
        } catch (GeneralException e) {
            System.out.println(e);
        }
    } // end of updateIRImage()

    protected static Image screenUsers(Color color_user) {
        // store the Kinect RGB image as a pixel array in cameraPixels
        try {
            ByteBuffer imageBB = imageGen.getImageMap().createByteBuffer();
            convertToPixels(imageBB, cameraPixels);
        } catch (GeneralException e) {
            System.out.println(e);
        }
        if (color_user != null) {
            setColorUser(color_user);
        }
        hideBackground(cameraPixels);

        // change the modified pixels into an image
        cameraImage.setRGB(0, 0, imWidth, imHeight, cameraPixels, 0, imWidth);
        return cameraImage;
    }  // end of getScreenUsers()

    private static void hideBackground(int[] cameraPixels) /* assign the "hide BG" value to any image pixels used for non-users
     thereby making it transparent
     */ {
        depthMD = depthGen.getMetaData();    // reassignment to avoid a flickering viewpoint

        // update the user ID map
        ShortBuffer usersBuf = sceneMD.getData().createShortBuffer();
        /* each pixel holds an user ID (e.g. 1, 2, 3), or 0 to 
         denote that the pixel is part of the background.  */

        while (usersBuf.remaining() > 0) {
            int pos = usersBuf.position();
            short userID = usersBuf.get();
            if (userID == 0) // if not a user (i.e. is part of the background)
            {
                cameraPixels[pos] = hideBGPixel;   // make pixel transparent
            }
        }
    }  // end of hideBackground()

    private static void convertToPixels(ByteBuffer pixelsRGB, int[] cameraPixels) /* Transform the ByteBuffer of pixel data into a pixel array
     Converts RGB bytes to ARGB ints with no transparency. 
     */ {
        int rowStart = 0;
        // rowStart will index the first byte (red) in each row;
        // starts with first row, and moves down

        int bbIdx;               // index into ByteBuffer
        int i = 0;               // index into pixels int[]
        int rowLen = imWidth * 3;    // number of bytes in each row
        for (int row = 0; row < imHeight; row++) {
            bbIdx = rowStart;
            // System.out.println("bbIdx: " + bbIdx);
            for (int col = 0; col < imWidth; col++) {
                int pixR = pixelsRGB.get(bbIdx++);
                int pixG = pixelsRGB.get(bbIdx++);
                int pixB = pixelsRGB.get(bbIdx++);
                cameraPixels[i++] =
                        0xFF000000 | ((pixR & 0xFF) << 16)
                        | ((pixG & 0xFF) << 8) | (pixB & 0xFF);
            }
            rowStart += rowLen;   // move to next row
        }
    }  // end of convertToPixels()

    /**
     * captura os dados da imageIR e transforma em uma Image
     */
    protected static Image updateImage() // get imageIR data as bytes; convert to an imageIR
    {
        try {
            ByteBuffer imageBB = imageGen.getImageMap().createByteBuffer();
            return bufToImage(imageBB);
        } catch (GeneralException e) {
            return null;
        }
    }  // end of getImageRGB24()

    /**
     * Transforma os pixels em BufferedImage Metodo auxilar ao UpdateImage
     *
     * @param pixelsRGB
     * @return
     */
    private static BufferedImage bufToImage(ByteBuffer pixelsRGB) /* Transform the ByteBuffer of pixel data into a BufferedImage
     Converts RGB bytes to ARGB ints with no transparency.
     */ {
        int[] pixelInts = new int[imWidth * imHeight];

        int rowStart = 0;
        // rowStart will index the first byte (red) in each row;
        // starts with first row, and moves down

        int bbIdx;               // index into ByteBuffer
        int i = 0;               // index into pixels int[]
        int rowLen = imWidth * 3;    // number of bytes in each row
        for (int row = 0; row < imHeight; row++) {
            bbIdx = rowStart;
            // System.out.println("bbIdx: " + bbIdx);
            for (int col = 0; col < imWidth; col++) {
                int pixR = pixelsRGB.get(bbIdx++);
                int pixG = pixelsRGB.get(bbIdx++);
                int pixB = pixelsRGB.get(bbIdx++);
                pixelInts[i++] =
                        0xFF000000 | ((pixR & 0xFF) << 16)
                        | ((pixG & 0xFF) << 8) | (pixB & 0xFF);
            }
            rowStart += rowLen;   // move to next row
        }

        // create a BufferedImage from the pixel data
        BufferedImage im =
                new BufferedImage(imWidth, imHeight, BufferedImage.TYPE_INT_ARGB);
        im.setRGB(0, 0, imWidth, imHeight, pixelInts, 0, imWidth);
        return im;
    }  // end of bufToImage()

    private static void calcHistogram(ShortBuffer depthBuf) {
        // reset histogram
        for (int i = 0; i <= maxDepth; i++) {
            histogram[i] = 0;
        }

        // record number of different depths in histogram[]
        int numPoints = 0;
        maxDepth = 0;
        while (depthBuf.remaining() > 0) {
            short depthVal = depthBuf.get();
            if (depthVal > maxDepth) {
                maxDepth = depthVal;
            }
            if ((depthVal != 0) && (depthVal < MAX_DEPTH_SIZE)) {      // skip histogram[0]
                histogram[depthVal]++;
                numPoints++;
            }
        }
        // System.out.println("No. of numPoints: " + numPoints);
        // System.out.println("Maximum depth: " + maxDepth);

        // convert into a cummulative depth count (skipping histogram[0])
        for (int i = 1; i <= maxDepth; i++) {
            histogram[i] += histogram[i - 1];
        }

        /* convert cummulative depth into the range 0.0 - 1.0f
         which will later be used to modify a color from USER_COLORS[] */
        if (numPoints > 0) {
            for (int i = 1; i <= maxDepth; i++) // skipping histogram[0]
            {
                histogram[i] = 1.0f - (histogram[i] / (float) numPoints);
            }
        }
    }  // end of calcHistogram()

    private static void updateUserDepths() /* build a histogram of 8-bit depth values, and convert it to
     depth imageIR bytes where each user is coloured differently */ {
        ShortBuffer depthBuf = depthMD.getData().createShortBuffer();
        calcHistogram(depthBuf);
        depthBuf.rewind();

        // use user IDs to colour the depth map
        ShortBuffer usersBuf = sceneMD.getData().createShortBuffer();
        /* usersBuf is a labeled depth map, where each pixel holds an
         user ID (e.g. 1, 2, 3), or 0 to denote that the pixel is
         part of the background.  */

        while (depthBuf.remaining() > 0) {
            int pos = depthBuf.position();
            short depthVal = depthBuf.get();
            short userID = usersBuf.get();

            imgbytes[3 * pos] = 0;     // default colour is black when there's no depth data
            imgbytes[3 * pos + 1] = 0;
            imgbytes[3 * pos + 2] = 0;

            if (depthVal != 0) {   // there is depth data
                // convert userID to index into USER_COLORS[]
                int colorIdx = userID % (USER_COLORS.length - 1);   // skip last color

                if (userID == 0) // not a user; actually the background
                {
                    colorIdx = USER_COLORS.length - 1;
                }
                // use last index: the position of white in USER_COLORS[]

                // convert histogram value (0.0-1.0f) to a RGB color
                float histValue = histogram[depthVal];
                imgbytes[3 * pos] = (byte) (histValue * USER_COLORS[colorIdx].getRed());
                imgbytes[3 * pos + 1] = (byte) (histValue * USER_COLORS[colorIdx].getGreen());
                imgbytes[3 * pos + 2] = (byte) (histValue * USER_COLORS[colorIdx].getBlue());
            }
        }
    }  // end of updateUserDepths()

    protected static Image getUserDepthsImage() /* Create BufferedImage using the depth imageIR bytes
     and a color model, then draw it */ {
        try {
            // define an 8-bit RGB channel color model
            altViewCap.resetViewpoint();
        } catch (StatusException ex) {
            Logger.getLogger(KinectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateUserDepths();
        ColorModel colorModel = new ComponentColorModel(
                ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8},
                false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);

        // fill the raster with the depth imageIR bytes
        DataBufferByte dataBuffer = new DataBufferByte(imgbytes, imWidth * imHeight * 3);

        WritableRaster raster = Raster.createInterleavedRaster(dataBuffer, imWidth,
                imHeight, imWidth * 3, 3, new int[]{0, 1, 2}, null);

        // combine color model and raster to create a BufferedImage
        BufferedImage image = new BufferedImage(colorModel, raster, false, null);
        return image;
    }  // end of drawUserDepths()

    protected static Context getContext() {
        return context;
    }

    protected static Dimension getPrefSize() {
        return dimension;
    }

    // close down
    protected static void closeDown() {
        try {
            context.stopGeneratingAll();
        } catch (StatusException e) {
            context.release();
            System.exit(1);
        } // end of run()
    }

    protected static boolean started() {
        return started;
    }

    private void start() {
        try {
            context.startGeneratingAll();
            System.out.println("Started context generating...");
            ImageMetaData imageMD = imageGen.getMetaData();
            imWidth = imageMD.getFullXRes();
            imHeight = imageMD.getFullYRes();
            started = true;
        } catch (Exception ex) {
            System.out.println("Exceção no run do KinectManager");
        }

    }
}
