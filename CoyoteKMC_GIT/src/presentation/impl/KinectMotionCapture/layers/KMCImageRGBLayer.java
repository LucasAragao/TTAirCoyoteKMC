/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.impl.KinectMotionCapture.layers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;

/**
 *
 * @author almerindo
 */
public class KMCImageRGBLayer extends ALayerShape{

    Dimension pSize = null;
    Image img = null;
    ImageObserver imgObs = null;

   public KMCImageRGBLayer(Image img, ImageObserver imgObs, Dimension pSize)throws Exception {
       super();
       this.setImage(img);
       this.setImageObserver(imgObs);
       this.setPreferedSize(pSize);
       this.coordinate = new Point[2];
       this.coordinate[0] = new Point(0,0);
       this.coordinate[1] = new Point(0,0);
   }

   public void setImage(Image img){
       this.img = img;
   }
   
   public void setImageObserver(ImageObserver imgObs){
       this.imgObs = imgObs;
   }

   public void setPreferedSize(Dimension pSize){
       this.pSize = pSize;
   }
    @Override
    public void draw(Graphics g) {
         this.setImage(KinectAccess.getImageRGB24());;
         g.drawImage(img, 0, 0, pSize.width, pSize.height,this.imgObs);
    }


    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
