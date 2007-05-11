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

/*
 * Copyright (c) 2003-2006 jMonkeyEngine
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
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
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

package com.gerbildrop.dradis.gameState;

import com.gerbildrop.dradis.gameState.base.StandardGameState;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.input.FirstPersonHandler;
import com.jme.input.InputHandler;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

/**
 * @author Per Thulin
 * @author vivaldi
 * @version $Id: IngameState.java,v 1.3 2007/04/04 14:31:00 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class IngameState extends StandardGameState {

    private InputHandler input;
    /** the final name of this GameState */
    private static final String STATE_NAME = "ingame";
    /** the final name of the parent GameState */
    private static final String PARENT_NAME = "menu";

    public IngameState() {
        super(STATE_NAME);
    }

    /** Gets called every time the game state manager switches to this game state. Sets the window title. */
    public void onActivate() {
        DisplaySystem.getDisplaySystem().setTitle("Test Game State System - Ingame State");
        super.onActivate();
    }

    /**
     * Gets called from super constructor. Sets up the input handler that let us walk around using the w,s,a,d keys and
     * mouse.
     */
    protected void initInput() {
        input = new FirstPersonHandler(getCamera(), 10, 1);
    }

    protected void stateUpdate(float tpf) {
        super.stateUpdate(tpf);
        input.update(tpf);
    }

    protected String getParentName() {
        return PARENT_NAME;
    }

    protected String getStateName() {
        return STATE_NAME;
    }

    protected void initGameState() {
        // Move the camera a bit.
        getCamera().setLocation(new Vector3f(0, 10, 0));
        getCamera().update();

        initInput();

        // Create a Quad.
        Quad q = new Quad("Quad", 200, 200);
        q.setModelBound(new BoundingBox());
        q.updateModelBound();
        q.setLocalRotation(new Quaternion(new float[]{90 * FastMath.DEG_TO_RAD, 0, 0}));

        // Apply a texture to it.
        TextureState ts =
                DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        Texture texture =
                TextureManager.loadTexture(
                        IngameState.class.getClassLoader().getResource(
                                "jmetest/data/texture/dirt.jpg"),
                        Texture.MM_LINEAR_LINEAR,
                        Texture.FM_LINEAR);
        texture.setWrap(Texture.WM_WRAP_S_WRAP_T);
        ts.setTexture(texture);
        ts.setEnabled(true);
        q.setRenderState(ts);

        // Add it to the scene.
        rootNode.attachChild(q);
    }

}