

package presentation;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import presentation.impl.KinectMotionCapture.layers.KMCImageRGBLayer;



/**
 * Classe abstrata com todas as funcionalidades do Player. Qualquer novo player a
 * ser utilizado no ambiente CANNOT dever�  implementar esta classe.
 */
public abstract class AControlPlayer implements IControlPlayer, IDisplay
{
  //Tempo inicial e final do comentario
    static ResourceBundle res = ResourceBundle.getBundle("presentation.Res");

    private int timestampBegin;
    private int timestampEnd;

    //Coordenadas da selecao do video
    private Point[] coordenadas;

    //Lista de Shapes
    private ArrayList vShapes;
    private ALayerShape currentShape;
    private int indexCurrentShape = -1;

    private boolean playOnlyInTimestamp = false;

  /**
    * @roseuid 41C5680B0319
    */
   public AControlPlayer()
   {
     this.setShapes(null);

   }

   /**
    * @return int
    * @roseuid 41C0BF43032D
    */


   public int getTimeBegin()throws Exception
   {
    return this.timestampBegin;
   }

   public int getTimeEnd()throws Exception
   {
    return this.timestampEnd;
   }



   /**
    * @param time
    * @roseuid 41C0C01D00F6
    */
   public void setTimeStampBegin(int timeStamp) throws Exception {
     if (timeStamp < -1) {
       throw new Exception(res.getString("Valores_devem_ser"));
     }
     this.timestampBegin = timeStamp;
   }

   /**
    * @param time
    * @roseuid 41C0C03B030C
    */
   public void setTimeStampEnd(int timeStamp) throws Exception {
     if (timeStamp < -1) {
       throw new Exception(res.getString("Valores_devem_ser"));
     }
     this.timestampEnd = timeStamp;
   }


   /**
    * @return Poit[]
    * @roseuid 41C569AC00E7
    */
   public Point[] getPoits()
   {
    return this.getCurretShape().coordinate;
   }


   /**
    * @param shape
    * @roseuid 41C569AC0141
    */
   public void addShape(ALayerShape shape)
   {
     if ( (shape.coordinate[0].x >= 0) && (shape.coordinate[0].y >= 0) &&
         (shape.coordinate[1].x >= 0) && (shape.coordinate[1].y >= 0)) {
       //shape.color = Color.red;
       this.getShapes().add(shape);
       this.setCurrentShape(this.getShapes().size() -1);
     }
   }


   /**
    * @param vShapes
    * @roseuid 41C569AC01CE
    */
   public void setShapes(ArrayList vShapes)
   {
     this.vShapes = vShapes;
     if ((vShapes==null)||(vShapes.size() <=0)) {
       this.vShapes = new ArrayList();
       this.currentShape = null;
       this.indexCurrentShape = -1;
     }else {
       this.setCurrentShape(this.getShapes().size()-1);
     }

   }

   /**
    * @return Logical View::player::ALayerShape[]
    * @roseuid 41C569AC0246
    */
   public ArrayList getShapes()
   {
    return this.vShapes;
   }

   public ALayerShape getCurretShape(){
     try {
       return this.currentShape;
     }
     catch (Exception ex) {
       //ex.printStackTrace();
       return null;
     }
   }

   //TODO Todos os shapes sao desenhados como branco e o shape atual é desenhado como vermelho
   public boolean setCurrentShape(int index){
     try {
       if ((index>=0)&&(index<this.getShapes().size())) {
         if (this.getCurretShape()!=null){
             this.getCurretShape().color = Color.white;
           }
         this.currentShape = (ALayerShape)this.getShapes().get(index);
         this.getCurretShape().color = Color.red;
         this.indexCurrentShape = index;
         return true;
       }else {
         return false;
       }
     }
     catch (Exception ex) {
       return false;
     }

   }

   public boolean nextShape(){
     int i = this.indexCurrentShape;
     if (i>=this.getShapes().size()) {
       return false;
     }
     i++;
     return this.setCurrentShape(i);
   }
   public boolean backShape(){
     int i = this.indexCurrentShape;
     if (i<=0) {
       return false;
     }
     i--;
     return this.setCurrentShape(i);
   }

   public void setPlayOnlyInTimestamp(boolean b)throws Exception {
     this.playOnlyInTimestamp = b;
   }
   public boolean isToPlayOnlyInTimestamp(){
     return this.playOnlyInTimestamp;
   }
   public abstract JComponent getVisualDisplay()throws Exception;
   public abstract JComponent getVisualControl()throws Exception;
   public abstract void executeCommand(int command)throws Exception;



}
