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


package com.gerbildrop.dradis.terrain;

import com.jme.image.Texture;
import com.jme.renderer.Camera;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.util.TextureManager;

/**
 * Creates the SkyBox for the Dradis Game and handles updating when called on to update
 *
 * @author vivaldi
 * @version $Id: DradisSkyBox.java,v 1.4 2007/04/04 14:31:09 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisSkyBox {
    private Skybox skybox;

    private Node skyBoxNode;

    /**
     * loads all information for this object like an initializer, however, we're not only initializing
     * values, but we're loading objects... therefore, this was named load
     *
     * @param rootNode Node - the Node this will be attached to
     */
    public void load() {
        skyBoxNode = new Node("SkyBox Node");

        skybox = new Skybox("skybox", 10, 10, 10);

        Texture north = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/north.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture south = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/south.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture east = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/east.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture west = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/west.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture up = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/top.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture down = TextureManager.loadTexture(
                DradisSkyBox.class.getClassLoader().getResource(
                        "dradis/skybox/bottom.jpg"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        skybox.setTexture(Skybox.NORTH, north);
        skybox.setTexture(Skybox.WEST, west);
        skybox.setTexture(Skybox.SOUTH, south);
        skybox.setTexture(Skybox.EAST, east);
        skybox.setTexture(Skybox.UP, up);
        skybox.setTexture(Skybox.DOWN, down);
        skybox.preloadTextures();
        skyBoxNode.attachChild(skybox);
    }

    /**
     * update our skybox
     *
     * @param time float - the time per frame
     * @param cam Camera - the Camera object we're using to view the skyBox
     */
    public void update(float time, Camera cam) {
        skybox.setLocalTranslation(cam.getLocation());
    }

    /**
     * Get the skybox node
     * @return Node skyBoxNode - the Node that contains all of the attached children related to the skyBox
     */
    public Node getNode() {
        return skyBoxNode;
    }
}
