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

package com.gerbildrop.loader.gui;

import com.jme.image.Texture;
import com.jme.input.AbsoluteMouse;
import com.jme.input.InputHandler;
import com.jme.input.Mouse;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

/**
 * pulled out of a class in the JME core and modified for my use
 *
 * @author vivaldi
 * @version $Id: Cursor.java,v 1.3 2007/04/04 19:50:11 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Cursor extends Node {
    private Cursor() {
        super();
    }

    public Cursor(String string) {
        super(string);
    }

    private static Mouse mouse;
    private static final DisplaySystem display = DisplaySystem.getDisplaySystem();

    public void load(InputHandler input) {
        load(new AbsoluteMouse("Mouse Input", display.getWidth(), display.getHeight()), input);
    }

    public void load(Mouse mouse, InputHandler input) {
        Cursor.mouse = mouse;

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(ClassLoader.getSystemResource("/jmetest/data/cursor/cursor1.png"), Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true));
        ts.setEnabled(true);

        AlphaState alpha = display.getRenderer().createAlphaState();
        alpha.setBlendEnabled(true);
        alpha.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alpha.setDstFunction(AlphaState.DB_ONE);
        alpha.setTestEnabled(true);
        alpha.setTestFunction(AlphaState.TF_GREATER);
        alpha.setEnabled(true);

        mouse.setRenderState(ts);
        mouse.setRenderState(alpha);
        mouse.setLocalScale(new Vector3f(1, 1, 1));
        mouse.registerWithInputHandler(input);
        this.attachChild(mouse);
    }

    public Vector3f getHotSpotPosition() {
        return mouse.getHotSpotPosition();
    }
}
