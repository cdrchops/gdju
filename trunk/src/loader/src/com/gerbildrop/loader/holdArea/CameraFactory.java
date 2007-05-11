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

package com.gerbildrop.loader.holdArea;

import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.CameraNode;
import com.jme.system.DisplaySystem;
import com.gerbildrop.loader.holdArea.CameraManager;

/**
 * @author vivaldi
 * @version $Id: CameraFactory.java,v 1.3 2007/04/04 19:50:21 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class CameraFactory {
    private static final CameraFactory _INSTANCE = new CameraFactory();

    private static final DisplaySystem display = DisplaySystem.getDisplaySystem();

    private static final float farView = 2000;

    public static CameraFactory getInstance() {
        return _INSTANCE;
    }

    private CameraFactory() {
    }

    public void createStandardCamera() {
        /**
         * Create a camera specific to the DisplaySystem that works with the
         * display's width and height
         */
        Camera cam = display.getRenderer().createCamera(display.getWidth(),
                                                        display.getHeight());

        /** Set a black background. */
        display.getRenderer().setBackgroundColor( ColorRGBA.black );

        /** Set up how our camera sees. */
        cameraPerspective(cam);

        cameraFrame(cam);

        /** Signal that we've changed our camera's location/frustum. */
        cam.update();

        CameraNode egpc = new CameraNode("mainCamera", cam);

        CameraManager.getInstance().add(CameraManager.CameraView.MAIN, egpc);
        CameraManager.getInstance().add(CameraManager.CameraView.CURRENT, egpc);
    }

    private void cameraPerspective(Camera camera) {
        camera.setFrustumPerspective(60.0f, (float)display.getWidth() / (float)display.getHeight(), 1.0f, 1000.0f);
        camera.setParallelProjection(false);
        camera.update();
    }

    private void cameraFrame(Camera camera) {
        Vector3f loc = new Vector3f(0.0f, 0.0f, 25.0f);
        Vector3f left = new Vector3f(-1.0f, 0.0f, 0.0f);
        Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f dir = new Vector3f(0.0f, 0.0f, -1.0f);
        camera.setFrame(loc, left, up, dir);
    }

    private void cameraParallel(Camera cam) {
        cam.setParallelProjection(true);
        float aspect = (float) display.getWidth() / display.getHeight();
        cam.setFrustum(-100, 1000, -50 * aspect, 50 * aspect, -50, 50);
        cam.update();
    }
}
