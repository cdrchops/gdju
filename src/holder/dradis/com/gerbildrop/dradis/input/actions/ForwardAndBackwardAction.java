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

import com.gerbildrop.dradis.ships.BaseModelObject;
import com.jme.input.action.InputActionEvent;
import com.jme.input.action.KeyInputAction;

/**
 * @author vivaldi
 * @version $Id: ForwardAndBackwardAction.java,v 1.10 2007/04/04 14:29:04 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class ForwardAndBackwardAction extends KeyInputAction {
    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;

    //the Node to manipulate
    private BaseModelObject baseModelObject;
    private int direction;

    /**
     * The vehicle to accelerate is supplied during construction.
     *
     * @param node      the vehicle to speed up.
     * @param direction Constant either FORWARD or BACKWARD
     */
    public ForwardAndBackwardAction(BaseModelObject node, int direction) {
        this.baseModelObject = node;
        this.direction = direction;
    }

    /** the action calls the vehicle's accelerate or brake command which adjusts its velocity. */
    public void performAction(InputActionEvent evt) {
        System.out.println("called input action " + direction);
        if (direction == FORWARD) {
            baseModelObject.brake(evt.getTime());
        } else if (direction == BACKWARD) {
            baseModelObject.accelerate(evt.getTime());
        }
    }
}