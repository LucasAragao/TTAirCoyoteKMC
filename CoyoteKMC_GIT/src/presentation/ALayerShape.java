//Source file: C:\\_COYOTEMPEG7\\Rose_Player_API\\CoyotePlayer\\src\\presentation\\ALayerShape.java
package presentation;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.util.ResourceBundle;

/**
 * Classe abstrata de desenho. Esta classe é a responsável pelos desenhos que
 * irão aparecer na tela.
 */
public abstract class ALayerShape {

    static ResourceBundle res = ResourceBundle.getBundle("presentation.Res");
    public Color color;
    public Point[] coordinate;
    private int timestampBegin;
    private int timestampEnd;
    private String label;
    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Inicializa o shape (timeStamps == -1, será desenhado até o fim do vídeo)
     *
     * @roseuid 41DF39D80018
     */
    public ALayerShape() throws Exception {
        this.setTimestampBegin(-1);
        this.setTimestampEnd(-1);
        this.setLabel("");
        this.color = Color.RED;
    }

    /**
     * Responsável por desenhar o shape na tela
     *
     * @param g
     * @roseuid 41BF8B3601B5
     */
    public abstract void draw(Graphics g);

    /**
     * Método para transformar o shape em uma string
     *
     * @return String
     * @roseuid 41BF8B4E02DE
     */
    public abstract String toString();

    /**
     * Configura o tempo inicial em que vai aparecer o shape
     *
     * @param timeStamp
     * @roseuid 41C0C46C0228
     */
    public void setTimestampBegin(int timeStamp) throws Exception {
        if (timeStamp < - 2) {
            throw new Exception("Valores devem ser maior ou igual a -2");
        }
        this.timestampBegin = timeStamp;
    }

    /**
     * Configura o tempo final. Tempo em que irá sumir o shape
     *
     * @param timeStamp
     * @roseuid 41C0C4930116
     */
    public void setTimestampEnd(int timeStamp) throws Exception {
        if (timeStamp < -1) {
            throw new Exception("Valores devem ser maior ou igual a -1");
        }
        this.timestampEnd = timeStamp;
    }

    /**
     * Configura o titulo do shape
     *
     * @param label
     * @roseuid 41C0C52D01CC
     */
    public void setLabel(String label) throws Exception {
        if (label == null) {
            throw new Exception("Valor do label nao pode ser nulo");
        }
        this.label = label;
    }

    /**
     * Retorna o tempo inicio do shape
     *
     * @return int
     * @roseuid 41C0C53D02C9
     */
    public int getTimestampBegin() {
        return this.timestampBegin;
    }

    /**
     * Retorna o tempo fim do Shape
     *
     * @return int
     * @roseuid 41C0C55002A8
     */
    public int getTimestampEnd() {
        return this.timestampEnd;
    }

    /**
     * Retorna o t�tulo do shape
     *
     * @return String
     * @roseuid 41C0C55C0115
     */
    public String getLabel() {
        return this.label;
    }
}
