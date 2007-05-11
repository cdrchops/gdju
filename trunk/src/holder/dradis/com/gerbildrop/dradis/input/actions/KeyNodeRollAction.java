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

package com.gerbildrop.dradis.input.actions;

import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.input.action.InputActionEvent;
import com.jme.input.action.KeyInputAction;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * @author vivaldi
 * @version $Id: KeyNodeRollAction.java,v 1.8 2007/04/04 14:29:04 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class KeyNodeRollAction extends KeyInputAction {

    //the Node to manipulate
    private Spatial Node;
    //an optional axis to lock, preventing rolling on this axis.
    private Vector3f lockAxis;

    float factor = 1;
    public static final int UP = -1;
    public static final int DOWN = 1;

    /**
     * Constructor instantiates a new <code>KeyNodeRotateLeftAction</code> object using the Node and speed parameters
     * for it's attributes.
     *
     * @param Node  the Node that will be affected by this action.
     * @param speed the speed at which the Node can move.
     */
    public KeyNodeRollAction(Spatial Node, float speed, int direction) {
        this.Node = Node;
        this.speed = speed;
        factor = direction;
    }

    /**
     * <code>setLockAxis</code> allows a certain axis to be locked, meaning the camera will always be within the plane
     * of the locked axis. For example, if the Node is a first person camera, the user might lock the Node's up vector.
     * This will keep the Node vertical with the ground.
     *
     * @param lockAxis the axis to lock.
     */
    public void setLockAxis(Vector3f lockAxis) {
        this.lockAxis = lockAxis;
    }

    /**
     * <code>performAction</code> rolls the camera about it's forward vector or lock axis at a speed of movement speed *
     * time. Where time is the time between frames and 1 corresponds to 1 second.
     *
     * @see com.jme.input.action.KeyInputAction#performAction(com.jme.input.action.InputActionEvent)
     */
    public void performAction(InputActionEvent evt) {
        float angle = factor * speed * evt.getTime();
        if (lockAxis == null) {
            NodeRotator.rotate(Node, Model.PITCH, angle);
        } else {
            NodeRotator.rotate(Node, lockAxis, angle);

        }

//        Node.getLocalRotation().normalize();
    }
}