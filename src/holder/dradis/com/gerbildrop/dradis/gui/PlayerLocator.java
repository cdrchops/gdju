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

package com.gerbildrop.dradis.gui;

import java.nio.FloatBuffer;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

/**
 * @author vivaldi
 * @version $Id: PlayerLocator.java,v 1.13 2007/04/04 14:29:00 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class PlayerLocator extends BaseHud {
    private PlayerLocator() {
    }

    private static final PlayerLocator _INSTANCE = new PlayerLocator();

    public static PlayerLocator getInstance() {
        return _INSTANCE;
    }

    Node playerNode;

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void load(Node rootNode, Node playerNode) {
        final DisplaySystem display = DisplaySystem.getDisplaySystem();
        this.playerNode = playerNode;

        //todo: this is where I want the initial DRADIS to be showing a pixel for each
        //      type of vehicle out there
        hudNode = new Node("hudNode");
        Quad hudQuad = new Quad("hud", 275f, 266f);
        hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        hudQuad.setLocalTranslation(new Vector3f(275 / 2, 266 / 2, 0));

        hudQuad.setLightCombineMode(LightState.OFF);
        hudQuad.updateRenderState();

        hudNode.attachChild(hudQuad);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(ClassLoader.getSystemResource("dradis/displays/HUDS/radar.png"), Texture.MM_LINEAR,
                                                 Texture.FM_LINEAR, 1.0f, true));
        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();
        ts.setEnabled(true);


        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        texCoords.put(getUForPixel(0)).put(getVForPixel(266));
        texCoords.put(getUForPixel(275)).put(getVForPixel(266));
        texCoords.put(getUForPixel(275)).put(getVForPixel(0));
        hudQuad.setTextureBuffer(0, texCoords);
        hudQuad.setRenderState(ts);

        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);

        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(false);
        as.setEnabled(true);
        hudQuad.setRenderState(as);
//        createNubbin(NubbinType.BLUE, new Vector3f(275/2 + 5, 266/2 + 5, 0));
        loaded = true;
    }

    //float time, Camera cam,
    public void update(String name, Vector3f updatedLocation, float time) {
        if (isLoaded()) {
            Quad hq = (Quad) hudNode.getChild(name);
            final Vector3f loca = new Vector3f(275 / 2, 0, 266 / 2);
            Vector3f newLoc = new Vector3f(loca.add(updatedLocation));
            Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);

            hq.setLocalTranslation(displayLocation);

            hudNode.updateWorldData(time);
        }
    }

    public static enum NubbinType {
        BROWN, GRAY, RED, YELLOW, WHITE, BLUE}

    /**
     * brown = raptor grey = galactica or pegasus red = raider, basestar or heavy raider yellow or white = civillian
     * ships blue = player
     */
    public void createNubbin(NubbinType nt, String name, Vector3f location) {
        if (isLoaded()) {
            Quad hudQuad = new Quad(name, 4, 4);
            hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);
            final Vector3f loca = new Vector3f(275 / 2, 0, 266 / 2);
            Vector3f newLoc = new Vector3f(loca.add(location));
            Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);

            hudQuad.setLocalTranslation(displayLocation);
            setColor(hudQuad, nt);
            hudQuad.setLightCombineMode(LightState.OFF);
            hudQuad.updateRenderState();

            hudNode.attachChild(hudQuad);
        }
    }

    private void setColor(Quad hudQuad, NubbinType nt) {
        ColorRGBA cr;

        switch (nt) {
            case BLUE:
                cr = ColorRGBA.blue;
                break;
            case BROWN:
                cr = ColorRGBA.brown;
                break;
            case GRAY:
                cr = ColorRGBA.gray;
                break;
            case RED:
                cr = ColorRGBA.red;
                break;
            case YELLOW:
                cr = ColorRGBA.yellow;
                break;
            case WHITE:
                cr = ColorRGBA.white;
                break;
            default:
                cr = ColorRGBA.yellow;

        }

        hudQuad.setDefaultColor(cr);
    }
}