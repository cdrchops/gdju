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

package com.gerbildrop.dradis.util;

import java.net.URL;
import java.nio.FloatBuffer;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
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
 * @version $Id: DradisNode.java,v 1.5 2007/04/04 14:31:07 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisNode implements Cloneable {
    protected URL _texturePath = null;
    protected URL _modelPath = null;
    protected Node rootNode;
    protected int textureWidth;
    protected int textureHeight;
    protected Quad modelQuad;

    /** X */
    public final static int PITCH = 0;//x
    /** Y */
    public final static int YAW = 1;//y
    /** Z */
    public final static int ROLL = 2;//z

    public URL getModelPath() {
        return _modelPath;
    }

    public void setModelPath(URL modelPath) {
        _modelPath = modelPath;
    }

    public URL getTexturePath() {
        return _texturePath;
    }

    public void setTexturePath(URL texturePath) {
        _texturePath = texturePath;
    }

    public Node getNode() {
        return rootNode;
    }

    public void setNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    protected float getUForPixel(int xPixel) {
        return (float) xPixel / textureWidth;
    }

    protected float getVForPixel(int yPixel) {
        return 1f - (float) yPixel / textureHeight;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(final Node rootNode) {
        this.rootNode = rootNode;
    }

    public Quad getModelQuad() {
        return modelQuad;
    }

    public void setModelQuad(final Quad modelQuad) {
        this.modelQuad = modelQuad;
    }

    public String getName() {
        return rootNode.getName();
    }

    public void load(
            String nodeName,
            String quadName,
            String imagePath) {
        load(nodeName, quadName, imagePath, 0.8f);
    }

    public void load(
            String nodeName,
            String quadName,
            String imagePath,
            float scaleSize) {
//        TextureManager.clearCache();//might help with disappearing stuff
        final DisplaySystem display = DisplaySystem.getDisplaySystem();
        rootNode = new Node(nodeName);
        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(getClass().getClassLoader().getResource(imagePath), Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true));
        ts.setEnabled(true);

        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();

        modelQuad = new Quad(quadName, textureWidth, textureHeight);
        modelQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        modelQuad.setLightCombineMode(LightState.OFF);
        modelQuad.updateRenderState();

        modelQuad.setLocalTranslation(new Vector3f(textureWidth / 2, textureHeight / 2, 0));

        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        texCoords.put(getUForPixel(0)).put(getVForPixel(textureHeight));
        texCoords.put(getUForPixel(textureWidth)).put(getVForPixel(textureHeight));
        texCoords.put(getUForPixel(textureWidth)).put(getVForPixel(0));

        modelQuad.setTextureBuffer(0, texCoords);
        modelQuad.setRenderState(ts);

        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(false);
        as.setEnabled(true);

        modelQuad.setRenderState(as);

        rootNode.setLocalScale(scaleSize);
        rootNode.attachChild(modelQuad);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
