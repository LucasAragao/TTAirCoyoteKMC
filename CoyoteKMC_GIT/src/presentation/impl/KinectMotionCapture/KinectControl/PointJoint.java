/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.impl.KinectMotionCapture.KinectControl;

import java.io.Serializable;
import org.OpenNI.Point3D;
import org.OpenNI.SkeletonJoint;

/**
 *
 * @author lucas
 * @since 2012
 * @version 1.0
 */
public class PointJoint implements Serializable {

    /**
     * Código referente ao ENUM "SkeletonJoint.java" definido pela biblioteca do            
     * OpenNI
     */
    private int cod;
    

    /**
     * variáveis referentes a localização da junta no plano cartesiano,
     * coordenadas x, y, z
     */
    private float x = 0;
    private float y = 0;
    private float z = 0;

    /**
     * Caso a junta não seja reconhecida, os dados cartesianos referentes a
     * mesma serão zerados para todos os pontos. (0.0)
     *
     * @param skeletonJoint
     * @param point
     */
    public PointJoint(SkeletonJoint skeletonJoint, Point3D point) {
        try {
            this.cod = skeletonJoint.toNative();
            this.x = point.getX();
            this.y = point.getY();
            this.z = point.getZ();
        } catch (NullPointerException ex) {
         //   System.out.println("\n>>>>>>>>>>>>>>>>>Exceção disparada<<<<<<<<<<<<<<<<<<<<<<<\n");
        }

    }

    

    public SkeletonJoint getJoint() {
        return SkeletonJoint.fromNative(this.cod);
    }

    public int getCod() {
        return cod;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public PointJoint getPoint() {
        return this;
    }

    @Override
    public String toString() {
        //  return "Joint: " + SkeletonJoint.fromNative(this.cod) + "   X: " + this.x + "   Y: " + this.y + "   Z: " + this.z;
        return this.cod + "      " + this.x + "  " + this.y + "  " + this.z + " || ";
    }
}
