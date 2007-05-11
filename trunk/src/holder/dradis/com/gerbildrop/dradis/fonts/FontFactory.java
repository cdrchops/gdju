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

package com.gerbildrop.dradis.fonts;

import java.awt.*;

import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.Text;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
//todo: NOT EVEN CLOSE TO DONE, need to take this and the ttf tests, etc and look at my
// font builder to ensure that this is going to be ok.

/**
 * @author vivaldi
 * @version $Id: FontFactory.java,v 1.9 2007/04/04 14:29:13 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class FontFactory {
    private FontFactory() {
    }

    private static final FontFactory _INSTANCE = new FontFactory();

    public static FontFactory getInstance() {
        return _INSTANCE;
    }

    Node guiNode;
    Text text;

    public Text createText() {
        Renderer renderer = DisplaySystem.getDisplaySystem().getRenderer();
        text = new Text("Testing Text", "");
        text.setLocalTranslation(new Vector3f(1, 60, 0));
        TextureState ts = renderer.createTextureState();
        ts.setEnabled(true);
        ts.setTexture(TextureManager.loadTexture(FontFactory.class.getResource("data/Font/font.png"), Texture.MM_LINEAR, Texture.FM_LINEAR));
        text.setRenderState(ts);
        AlphaState as1 = renderer.createAlphaState();
        as1.setBlendEnabled(true);
        as1.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as1.setDstFunction(AlphaState.DB_ONE);
        as1.setTestEnabled(true);
        as1.setTestFunction(AlphaState.TF_GREATER);
        text.setRenderState(as1);
        return text;
        //text.print("My String here");
    }

    public void print(String displaytext) {

    }

    public Node createFont() {
        Font font = new Font("Serif", Font.PLAIN, 32);

        long tstarttime = System.currentTimeMillis();
        TTFont text = new TTFont(font.deriveFont(Font.PLAIN), 64, 0);
        long tendtime = System.currentTimeMillis() - tstarttime;

        System.out.println("Finished loading fonts in " + tendtime);
        Node playertext = text.createText("Look at me!", 2, new ColorRGBA(1, 1, 0.1f, 1f), true);
        playertext.setLocalTranslation(new Vector3f(200, 200, 0));
        playertext.getLocalScale().set(16, 16, 1);
//        rootNode.setCullMode(Node.CULL_NEVER);
        playertext.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        playertext.setModelBound(new BoundingBox());
        playertext.updateModelBound();
        playertext.updateWorldBound();
        return playertext;
    }
}
