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

package com.gerbildrop.dradis.cameras;

import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.input.InputHandler;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;
import com.jme.renderer.Camera;
import com.jme.scene.Node;
import com.jme.system.DisplaySystem;

/**
 * @author vivaldi
 * @version $Id: CameraInputHandler.java,v 1.14 2007/04/04 14:28:59 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class CameraInputHandler extends InputHandler {
    BaseGamePlayCamera camera;
    Node rootNode;
    Camera cam;

    public CameraInputHandler(BaseGamePlayCamera camera, Camera cam, Node rootNode) {
        this.camera = camera;
        this.rootNode = rootNode;
        this.cam = cam;
        setKeyBindings();
        setActions();
    }

    public void setKeyBindings() {
        KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();

        keyboard.set("cockpitCamera", KeyInput.KEY_F9);
        keyboard.set("rearViewCamera", KeyInput.KEY_F8);
    }

    public void setActions() {
        addAction(new CockpitCameraAction(), "cockpitCamera", false);
//        addAction(new RearViewCameraAction(), "rearViewCamera", false);
    }

    private class CockpitCameraAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            camera = new CockpitCamera();
            camera.init(cam, "Cockpit Camera Node");
            NodeRotator.rotate(camera.getCameraNode(), Model.PITCH, (float) Math.toRadians(90));
            camera.update(evt.getTime());
            DisplaySystem.getDisplaySystem().getRenderer().setCamera(camera.getCamera());
        }
    }

//    private class RearViewCameraAction extends InputAction {
//        public void performAction(InputActionEvent evt) {
//            camera = new RearViewCamera();
//            camera.init(cam, "Rear View Camera Node");
//            NodeRotator.rotate(camera.getCameraNode(), Model.PITCH, (float) Math.toRadians(90));
//            NodeTranslator.translate(camera.getCameraNode(), Model.ROLL, 10);
//            camera.update(evt.getTime());
//            camera.getCameraNode().updateRenderState();
//            DisplaySystem.getDisplaySystem().getRenderer().setCamera(camera.getCamera());
//        }
//    }

//            protected void setCamera(int camNr) {
//
//            switch (camNr) {
//                case 1:
//                    cam.update();
//                    renderer.setCamera(cam);
//                    break;
//                case 2:
//                    camTop.update();
//                    renderer.setCamera(camTop);
//                    break;
//                default:
//                    renderer.setCamera(cam);
//                    break;
//            }
//    }
}