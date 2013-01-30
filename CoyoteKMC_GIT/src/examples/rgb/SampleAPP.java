/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.rgb;

import java.awt.Color;
import java.awt.Frame;
import presentation.ALayerShape;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.KinectRecorderSeqImagesUser;
import presentation.impl.KinectMotionCapture.layers.LayerRGB;
import presentation.impl.KinectMotionCapture.layers.ScreenPanel;

/**
 *
 * @author lucas
 */
public class SampleAPP {

    public static void main(String[] args) {
        //1º Cria um ScreenPanel
        ScreenPanel scp = new ScreenPanel();
        //2º Cria uma variável do tipo ALayerShape e instancia a Layer que deseja exibir. 
        //Esta por sua vez lança uma exceção que pode se tratada.
        ALayerShape rgb = null;
        try {
            rgb = new LayerRGB();
        } catch (Exception ex) {
            System.out.println("Não foi possível inicializar a Layer");
        }
        //3º Adionamos a Layer no ScreenPanel
        scp.addLayerShape(rgb);
        //4º Criamos um Frame
        Frame f = new Frame();
        //5º logo após inserimos o ScreenPanel no Frame
        f.add(scp);
        //6º Setamos o tamanho do frame, a biblioteca possui um método que retorna
        //a configuração corrente quanto ao tamanho da tela.
        f.setSize(KinectAccess.getPrefSize());
        //7º Setamos o frame como visível
        f.setVisible(true);
    }
}
