/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.engine.util;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * Will rotate a Node around an axis. Has its own temporary variables for this.
 *
 * @author shingoki
 * @author vivaldi
 * @version $Id: NodeTranslator.java,v 1.1 2007/04/04 14:27:33 vivaldi Exp $
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
        loc.addLocal(Node.getLocalRotation().getRotationColumn(axis, tempVa)
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