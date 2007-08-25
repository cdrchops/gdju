package com.gerbildrop.mask.util;

import com.jme.math.Matrix3f;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * @author shingoki
 * @author vivaldi
 * @version $Id: NodeRotator.java,v 1.2 2007/04/18 21:53:28 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class NodeRotator {

    //temporary variables to handle rotation
    private static final Matrix3f incr = new Matrix3f();

    private static final Matrix3f tempMa = new Matrix3f();

    private static final Matrix3f tempMb = new Matrix3f();

    private static final Vector3f tempVa = new Vector3f();

    /**
     * Rotate a Node around a lock axies
     *
     * @param Node
     * @param lockAxis
     */
    public static void rotate(Spatial Node, Vector3f lockAxis, double angle) {
        NodeRotator.incr.fromAngleAxis((float) angle, lockAxis);
        NodeRotator.rotateByIncr(Node);
    }

    /**
     * Rotate a Node around one of its axes
     *
     * @param Node           The Node to rotate
     * @param localAxisIndex The index of the local axis around which to rotate
     */
    public static void rotate(Spatial Node, int localAxisIndex, double angle) {
        NodeRotator.incr.fromAngleAxis((float) angle, Node.getLocalRotation().getRotationColumn(localAxisIndex,
                                                                                                NodeRotator.tempVa));
        NodeRotator.rotateByIncr(Node);
    }

    /**
     * Rotate a Node by the current setting of incr matrix
     *
     * @param Node The Node to rotate
     */
    private static void rotateByIncr(Spatial Node) {
        Node.getLocalRotation().fromRotationMatrix(
                NodeRotator.incr.mult(Node.getLocalRotation().toRotationMatrix(NodeRotator.tempMa),
                                      NodeRotator.tempMb));
        Node.getLocalRotation().normalize();
    }
}
