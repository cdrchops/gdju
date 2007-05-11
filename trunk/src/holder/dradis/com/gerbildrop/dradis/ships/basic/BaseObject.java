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

package com.gerbildrop.dradis.ships.basic;

import java.net.URL;

import com.jme.scene.Node;
import com.jme.scene.shape.Quad;

/**
 * @author vivaldi
 * @version $Id: BaseObject.java,v 1.11 2007/04/04 14:28:56 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class BaseObject implements Cloneable {
    protected URL texturePath = null;
    protected URL modelPath = null;
    protected int textureWidth;
    protected int textureHeight;
    protected Quad modelQuad;
    protected Node rootNode;

    public Node getRootNode() {
        return rootNode;
    }

    public Node getNode() {
        return rootNode;
    }

    public void setRootNode(final Node rootNode) {
        this.rootNode = rootNode;
    }

    public URL getModelPath() {
        return modelPath;
    }

    public void setModelPath(URL modelPath) {
        this.modelPath = modelPath;
    }

    public URL getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(URL texturePath) {
        this.texturePath = texturePath;
    }

    public Quad getModelQuad() {
        return modelQuad;
    }

    public void setModelQuad(Quad modelQuad) {
        this.modelQuad = modelQuad;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    protected void update(float timer) {
        rootNode.updateWorldData(timer);
        rootNode.updateRenderState();
    }

    protected float getUForPixel(float xPixel) {
        return xPixel / textureWidth;
    }

    protected float getVForPixel(float yPixel) {
        return 1f - yPixel / textureHeight;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
