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

import java.util.HashMap;
import java.util.Map;

import com.jme.renderer.Camera;
import com.jme.scene.CameraNode;
import com.jme.system.DisplaySystem;

/**
 * @author vivaldi
 * @version $Id: CameraManager.java,v 1.1 2007/04/04 14:27:20 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class CameraManager {
    public static enum CameraView {
        MAIN, CURRENT, TOP, LEFT, RIGHT, FIRST_PERSON, THIRD_PERSON, CHASER, BOTTOM}

    private static final CameraManager _INSTANCE = new CameraManager();

    private static final Map<String, CameraNode> camMap = new HashMap<String, CameraNode>();

    private CameraManager() {}

    static {
        CameraFactory.getInstance().createStandardCamera();
    }

    public static CameraManager getInstance() {
        return _INSTANCE;
    }

    public void create() {
        DisplaySystem.getDisplaySystem().getRenderer().setCamera(getCurrentCamera());
    }

    public Camera getCurrentCamera() {
        return getCurrentCameraNode().getCamera();
    }

    public CameraNode getCurrentCameraNode() {
        return get(CameraView.CURRENT);
    }

    public CameraNode get(CameraView view) {
        return camMap.get(view.toString());
    }

    public Camera getCamera(CameraView view) {
        return get(view).getCamera();
    }

    public void setCurrentCamera(CameraView view) {
        camMap.put(CameraView.CURRENT.toString(), camMap.get(view));
        DisplaySystem.getDisplaySystem().getRenderer().setCamera(getCurrentCamera());
    }

    public CameraNode get(String view) {
        return camMap.get(view);
    }

    public Camera getCamera(String view) {
        return get(view).getCamera();
    }

    public void setCurrentCamera(String view) {
        camMap.put(CameraView.CURRENT.toString(), camMap.get(view));
        DisplaySystem.getDisplaySystem().getRenderer().setCamera(getCurrentCamera());
    }

    public void add(CameraView view, CameraNode cnode) {
        camMap.put(view.toString(), cnode);
    }

    public void update(float time) {
        camMap.get(CameraView.CURRENT.toString()).updateRenderState();
    }
}
