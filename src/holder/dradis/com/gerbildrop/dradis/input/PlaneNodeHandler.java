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

package com.gerbildrop.dradis.input;

import com.gerbildrop.dradis.input.actions.FireBullet;
import com.gerbildrop.dradis.input.actions.ForwardAndBackwardAction;
import com.gerbildrop.dradis.input.actions.KeyNodeRollAction;
import com.gerbildrop.dradis.ships.BaseModelObject;
import com.jme.input.InputHandler;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.action.KeyNodeRotateLeftAction;
import com.jme.input.action.KeyNodeRotateRightAction;
import com.jme.scene.Node;
import com.jme.scene.Spatial;

/**
 * @author vivaldi
 * @version $Id: PlaneNodeHandler.java,v 1.11 2007/04/04 14:29:18 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class PlaneNodeHandler extends InputHandler {
    public PlaneNodeHandler(BaseModelObject Node, Node rootNode) {
        setKeyBindings();
        setUpMouse(Node, 1);
        setActions(Node, rootNode, 15f, .5f, 1f);
    }

    public PlaneNodeHandler(BaseModelObject Node,
                            Node rootNode,
                            float moveSpeed,
                            float turnSpeed,
                            float rollSpeed,
                            float mouseSpeed) {

        setKeyBindings();
        setUpMouse(Node, mouseSpeed);
        setActions(Node, rootNode, moveSpeed, turnSpeed, rollSpeed);
    }

    private void setKeyBindings() {
        KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();

        keyboard.set("forward", KeyInput.KEY_A);
        keyboard.set("backward", KeyInput.KEY_Z);
        keyboard.set("fire", KeyInput.KEY_V);
        keyboard.set("turnRight", KeyInput.KEY_RIGHT);
        keyboard.set("turnLeft", KeyInput.KEY_LEFT);
        keyboard.set("pullUp", KeyInput.KEY_UP);
        keyboard.set("pushDown", KeyInput.KEY_DOWN);
        keyboard.set("allStop", KeyInput.KEY_M);

//todo: some of these will come in handy for hovering, eventually... will need to work out that logic later
//        keyboard.set("turnLeft", KeyInput.KEY_X);
//        keyboard.set("turnRight", KeyInput.KEY_C);
//        keyboard.set("lookUp", KeyInput.KEY_UP);
//        keyboard.set("lookDown", KeyInput.KEY_DOWN);
//        keyboard.set("rollRight", KeyInput.KEY_RIGHT);
//        keyboard.set("rollLeft", KeyInput.KEY_LEFT);
//        keyboard.set("strafeRight", KeyInput.KEY_Q);
//        keyboard.set("strafeLeft", KeyInput.KEY_E);
    }

    private void setUpMouse(BaseModelObject Node,
                            float mouseSpeed) {
//        RelativeMouse mouse = new RelativeMouse("Mouse Input");
//        mouse.registerWithInputHandler( this );
//
//        NodeMouseLook mouseLook = new NodeMouseLook(mouse, Node, 0.1f);
//        mouseLook.setSpeed( mouseSpeed );
//        addAction(mouseLook);

    }

    private void setActions(BaseModelObject v2,
                            Node rootNode,
                            float moveSpeed,
                            float turnSpeed,
                            float rollSpeed) {
        Spatial Node = v2.getRootNode();
        addAction(new ForwardAndBackwardAction(v2, ForwardAndBackwardAction.BACKWARD), "backward", true);
        addAction(new ForwardAndBackwardAction(v2, ForwardAndBackwardAction.FORWARD), "forward", true);
        addAction(new KeyNodeRotateRightAction(Node, turnSpeed), "turnRight", true);
        addAction(new KeyNodeRotateLeftAction(Node, turnSpeed), "turnLeft", true);
        //todo: when using a joystick these variables need to be switched... pulling back pushes up not down
        addAction(new KeyNodeRollAction(Node, rollSpeed, KeyNodeRollAction.UP), "pullUp", true);
        addAction(new KeyNodeRollAction(Node, rollSpeed, KeyNodeRollAction.DOWN), "pushDown", true);
        addAction(new FireBullet(v2), "fire", true);
//        addAction(new )
        //todo: some of these will come in handy for hovering, eventually... will need to work out that logic later
//        addAction(new KeyNodeForwardAction(Node, moveSpeed), "forward", true);
//        addAction(new KeyNodeBackwardAction(Node, moveSpeed), "backward", true);
//        addAction(new KeyNodeStrafeLeftAction(Node, moveSpeed), "strafeLeft", true);
//        addAction(new KeyNodeStrafeRightAction(Node, moveSpeed), "strafeRight", true);
    }
}
