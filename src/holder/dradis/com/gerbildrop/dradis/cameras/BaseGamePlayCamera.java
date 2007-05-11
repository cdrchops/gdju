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

import com.jme.input.ChaseCamera;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.scene.CameraNode;
import com.jme.scene.Node;
import com.jme.scene.Spatial;

/**
 * @author vivaldi
 * @version $Id: BaseGamePlayCamera.java,v 1.14 2007/04/04 14:28:59 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class BaseGamePlayCamera extends Node {
    protected CameraNode cameraNode = null;
    protected Camera camera = null;
    protected ChaseCamera chaser = null;

    public void init(ChaseCamera chaser, String nodeName) {
        this.chaser = chaser;
        init(chaser.getCamera(), nodeName);
    }

    public void init(Camera camera, String nodeName) {
        this.camera = camera;
        cameraNode = new CameraNode(nodeName, getCamera());
        cameraNode.setCullMode(Spatial.CULL_NEVER);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public CameraNode getCameraNode() {
        return cameraNode;
    }

    public void setCameraNode(CameraNode cameraNode) {
        this.cameraNode = cameraNode;
    }

    public void setTarget(Spatial spatial) {
        if (chaser != null) {
            chaser.setTarget(spatial);
        }
    }

    public void setTargetOffset(Vector3f offset) {
        if (chaser != null) {
            chaser.setTargetOffset(offset);
        }
    }

    public void update(float timer) {
        super.updateWorldBound();
        super.updateWorldData(timer);
        super.updateRenderState();
    }
}
