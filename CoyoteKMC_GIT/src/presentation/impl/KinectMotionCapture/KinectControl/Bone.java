/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import presentation.ALayerShape;

/**
 *
 * @author lucas
 */
public class Bone extends ALayerShape {
    
    private EBone id;
    private PointJoint j1;
    private PointJoint j2;
    // private Color color = Color.RED;
    private float density = 7.0f;
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Bone(EBone id, PointJoint j1, PointJoint j2) throws Exception {
        this.id = id;
        this.j1 = j1;
        this.j2 = j2;
    }
    
    public PointJoint getJ1() {
        return j1;
    }
    
    public void setJ1(PointJoint j1) {
        this.j1 = j1;
    }
    
    public PointJoint getJ2() {
        return j2;
    }
    
    public EBone getId() {
        return id;
    }
    
    public void setJ2(PointJoint j2) {
        this.j2 = j2;
    }
    
    public void setJoints(PointJoint j1, PointJoint j2) {
        this.setJ1(j1);
        this.setJ2(j2);
    }
    
    public float getDensity() {
        return density;
    }
    
    public void setDensity(float density) {
        this.density = density;
    }
    
    @Override
    public void draw(Graphics g) {
        if (isVisible()) {
            g.setColor(this.color);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(density));
            g.drawLine((int) j1.getX(), (int) j1.getY(), (int) j2.getX(), (int) j2.getY());
        }
    }
    
    @Override
    public String toString() {
        return this.getLabel();
    }
}
