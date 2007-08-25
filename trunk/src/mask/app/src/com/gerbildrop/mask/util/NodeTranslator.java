package com.gerbildrop.mask.util;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * Will rotate a Node around an axis. Has its own temporary variables for this.
 *
 * @author shingoki
 * @author vivaldi
 * @version $Id: NodeTranslator.java,v 1.1 2007/04/17 21:44:41 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class NodeTranslator {

    private static final Vector3f tempVa = new Vector3f();

    /**
     * Translate a Node around along an axis
     *
     * @param Node     Node to move
     * @param axis     Axis to move along
     * @param distance Distance to move
     */
    public static void translate(Spatial Node, int axis, double distance) {
        Vector3f loc = Node.getLocalTranslation();
        loc.addLocal(Node.getLocalRotation().getRotationColumn(axis, NodeTranslator.tempVa)
                .multLocal((float) distance));
        Node.setLocalTranslation(loc);
    }

    /**
     * Make a translation vector, along an axis of the specified Node, by a specified distance
     *
     * @param Node     The Node to get axes from
     * @param axis     The axis index, 0,1,2 for x,y,z
     * @param distance The distance along the axis
     * @param vector   The vector in which to store the result, if null a new vector will be made
     *
     * @return The translation vector - either that specified by vector parameter, or a new vector if this is null
     */
    public static Vector3f makeTranslationVector(Spatial Node, int axis, double distance, Vector3f vector) {
        if (vector == null) {
            vector = new Vector3f();
        }
        Node.getLocalRotation().getRotationColumn(axis, vector);
        vector.multLocal((float) distance);
        return vector;
    }

}
