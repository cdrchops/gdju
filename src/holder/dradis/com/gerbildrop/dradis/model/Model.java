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

package com.gerbildrop.dradis.model;

import java.net.MalformedURLException;
import java.net.URL;

import com.gerbildrop.dradis.logging.Debug;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;

/**
 * The basic information that a jme model loaded into memory needs
 *
 * @author vivaldi
 * @version $Id: Model.java,v 1.17 2007/04/04 14:29:03 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Model implements Cloneable {
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

    public Object clone() throws CloneNotSupportedException {
        Model tmp = (Model) super.clone();
//        CloneImportExport ie = new CloneImportExport();
//
//        CloneConfiguration cc = new CloneConfiguration(new String[] {},
//                                                       new String[] {"vertBuf","colorBuf", "texBuf","normBuf", "colorBuf", "texBuf"});
//
//        ie.applyConfiguration(cc);
//
//        Node tmpRootNode = null;
//        Quad tmpQuad = null;
//        assert rootNode != null;
//        ie.saveClone(rootNode);
//
//        tmpRootNode = (Node) ie.loadClone();
//        assert modelQuad != null;
//        ie.saveClone(modelQuad);
//        tmpQuad = (Quad) ie.loadClone();
//
//        assert tmpRootNode != null;
//        assert tmpQuad != null;

//        tmp.setRootNode(tmpRootNode);
//        tmp.setModelQuad(tmpQuad);

        tmp.setRootNode(rootNode);
        tmp.setModelQuad(modelQuad);        

        try {
            tmp.setTexturePath(new URL(this._texturePath.toString()));
            tmp.setModelPath(new URL(this._modelPath.toString()));
        } catch (MalformedURLException e) {
            Debug.error(e);
        }

        tmp.textureHeight = this.textureHeight;
        tmp.textureWidth = this.textureWidth;

        return tmp;
    }
}
