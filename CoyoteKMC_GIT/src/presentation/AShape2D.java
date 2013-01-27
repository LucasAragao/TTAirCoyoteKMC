/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ResourceBundle;

/**
 *
 * @author lucas
 */
public abstract class AShape2D {
    static ResourceBundle res = ResourceBundle.getBundle("presentation.Res");
  public Color color;
   public Point[] coordinate;
   private int timestampBegin ;
   private int timestampEnd ;
   private String label;

   /**
    * Inicializa o shape (timeStamps == -1, ser� desenhado at� o fim do v�deo)
    * @roseuid 41DF39D80018
    */
   public AShape2D()throws Exception
   {
     this.setTimestampBegin(-1);
     this.setTimestampEnd(-1);
     this.setLabel("");
     this.color = Color.white;
   }

   /**
    * Respons�vel por desenhar o shape na tela
    * @param g
    * @roseuid 41BF8B3601B5
    */
   public  abstract void draw(Graphics2D  g);

   /**
    * M�todo para transformar o shape em uma string
    * @return String
    * @roseuid 41BF8B4E02DE
    */
   public abstract String toString();

   /**
    * Configura o tempo inicial em que vai aparecer o shape
    * @param timeStamp
    * @roseuid 41C0C46C0228
    */
   public void setTimestampBegin(int timeStamp)throws Exception
   {
     if (timeStamp < - 2) {
       throw new Exception("Valores devem ser maior ou igual a -2");
     }
     this.timestampBegin = timeStamp;
   }

   /**
    * Configura o tempo final. Tempo em que ir� sumir o shape
    * @param timeStamp
    * @roseuid 41C0C4930116
    */
   public void setTimestampEnd(int timeStamp)throws Exception
   {
     if (timeStamp < -1) {
       throw new Exception("Valores devem ser maior ou igual a -1");
     }
     this.timestampEnd = timeStamp;
   }

   /**
    * Configura o titulo do shape
    * @param label
    * @roseuid 41C0C52D01CC
    */
   public void setLabel(String label)throws Exception
   {
     if (label==null) {
       throw new Exception("Valor do label nao pode ser nulo");
     }
     this.label = label;
   }

   /**
    * Retorna o tempo inicio do shape
    * @return int
    * @roseuid 41C0C53D02C9
    */
   public int getTimestampBegin()
   {
    return this.timestampBegin;
   }

   /**
    * Retorna o tempo fim do Shape
    * @return int
    * @roseuid 41C0C55002A8
    */
   public int getTimestampEnd()
   {
    return this.timestampEnd;
   }

   /**
    * Retorna o t�tulo do shape
    * @return String
    * @roseuid 41C0C55C0115
    */
   public String getLabel()
   {
    return this.label;
   }

   public abstract void setCoordinates(String coords, String split)throws Exception;
}
