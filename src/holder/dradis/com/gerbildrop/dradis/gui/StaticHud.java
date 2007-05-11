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
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

/**
 * @author vivaldi
 * @version $Id: StaticHud.java,v 1.11 2007/04/04 14:29:00 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class StaticHud extends BaseHud {

    private static final StaticHud _INSTANCE = new StaticHud();

    public static StaticHud getInstance() {
        return _INSTANCE;
    }

    private StaticHud() {
    }

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void load() {
        hudNode = new Node("hudNode");
        Quad hudQuad = new Quad("hud", 40f, 40f);
        hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        hudQuad.setLocalTranslation(new Vector3f(display.getWidth() / 2, display.getHeight() / 2, 0));

        /* does not work to disable light under v0.10 */
        //LightState ls = display.getRenderer().createLightState();
        //ls.setEnabled(false);
        //modelQuad.setRenderState(ls);

        hudQuad.setLightCombineMode(LightState.OFF);
        hudQuad.updateRenderState();

        hudNode.attachChild(hudQuad);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(StaticHud.class.getClassLoader()
                .getResource("dradis/displays/HUDS/hud1.png"), Texture.MM_LINEAR,
                                                               Texture.FM_LINEAR, 1.0f, true));
        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();
        ts.setEnabled(true);


        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        texCoords.put(getUForPixel(0)).put(getVForPixel(40));
        texCoords.put(getUForPixel(40)).put(getVForPixel(40));
        texCoords.put(getUForPixel(40)).put(getVForPixel(0));
        hudQuad.setTextureBuffer(0, texCoords);
        hudQuad.setRenderState(ts);

        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);

        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(false);
        as.setEnabled(true);
        hudQuad.setRenderState(as);

        loaded = true;
    }

    public void update(float time) {
        if (isLoaded()) {
            getNode().updateWorldData(time);
        }
    }
}
