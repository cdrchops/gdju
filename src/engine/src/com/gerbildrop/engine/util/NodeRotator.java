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

import com.jme.math.Matrix3f;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * @author shingoki
 * @author vivaldi
 * @version $Id: NodeRotator.java,v 1.1 2007/01/25 16:19:44 vivaldi Exp $
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
        incr.fromAngleAxis((float) angle, lockAxis);
        rotateByIncr(Node);
    }

    /**
     * Rotate a Node around one of its axes
     *
     * @param Node           The Node to rotate
     * @param localAxisIndex The index of the local axis around which to rotate
     */
    public static void rotate(Spatial Node, int localAxisIndex, double angle) {
        incr.fromAngleAxis((float) angle, Node.getLocalRotation().getRotationColumn(localAxisIndex,
                                                                                    tempVa));
        rotateByIncr(Node);
    }

    /**
     * Rotate a Node by the current setting of incr matrix
     *
     * @param Node The Node to rotate
     */
    private static void rotateByIncr(Spatial Node) {
        Node.getLocalRotation().fromRotationMatrix(
                incr.mult(Node.getLocalRotation().toRotationMatrix(tempMa),
                          tempMb));
        Node.getLocalRotation().normalize();
    }
}