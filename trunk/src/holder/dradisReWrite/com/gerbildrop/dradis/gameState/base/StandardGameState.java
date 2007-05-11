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

package com.gerbildrop.dradis.gameState.base;

import com.gerbildrop.dradis.cameras.CameraManager;
import com.jme.input.InputHandler;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.renderer.Camera;
import com.jmex.game.state.GameStateManager;

/**
 * Dradis StandardGameState that all Dradis GameStates inherit from this gives the user default functionality for
 * screenshots and exiting that game state it also gives them a way to load their objects only once instead of each time
 * that the GameState is activated
 *
 * @author vivaldi
 * @version $Id: StandardGameState.java,v 1.4 2007/04/04 14:30:54 vivaldi Exp $
 * @since Oct 12, 2006
 */
public abstract class StandardGameState extends BasicGameState {
    /**
     * screenshotCounter increments by one each time you do a screenshot.  By default it starts at zero if you want to
     * keep your screenshots, you'll need to change the name or something else
     */
    private static int screenshotCounter = 0;

    /** GameState InputHandler manages key bindings for the GameState */
    private InputHandler input;

    /**
     * gives us a local variable to tell our inheriting classes if they have already loaded objects for this GameState
     * or not
     */
    protected boolean loaded = false;

    /**
     * constructor
     *
     * @param name String - prefix of the rootNode and name of this gameState
     */
    public StandardGameState(String name) {
        super(name);
    }

    /**
     * implementation for superclass
     * <p/>
     * onActivate called by setActive if you want to load things once this is the place to do it
     * <p/>
     * this method isn't needed for game operation, so we've stubbed it in, if someone wants to use it, they override it
     * in their GameState subclass
     *
     * @see {@link com.gerbildrop.dradis.cameras.StandardGameStateDefaultCamera#setActive(boolean)}
     */
    protected void onActivate() {
        // Bind the exit action to the escape key.
        KeyBindingManager.getKeyBindingManager().set("exit", KeyInput.KEY_ESCAPE);
        KeyBindingManager.getKeyBindingManager().set("screen_shot", KeyInput.KEY_F1);

        initGameState();
        initInput();

        // Remember to update the rootNode before you get going.
        rootNode.updateGeometricState(0, true);
        rootNode.updateRenderState();
        loaded = true;
    }

    /**
     * implementation for superclass
     * <p/>
     * update the input
     */
    protected void inputUpdate(float tpf) {
        if (input != null) {
            input.update(tpf);
        }

        if (KeyBindingManager.getKeyBindingManager().isValidCommand("exit", false)) {
            // Here we switch to the menu state which is already loaded
            GameStateManager.getInstance().activateChildNamed(getParentName());
            // And remove this state, because we don't want to keep it in memory.
            GameStateManager.getInstance().detachChild(getStateName());
        }

        if (KeyBindingManager.getKeyBindingManager().isValidCommand("screen_shot", false)) {
            display.getRenderer().takeScreenShot("SimpleGameScreenShot" + screenshotCounter);
            screenshotCounter++;
        }
    }

    /**
     * implementation for superclass
     * <p/>
     * this method isn't needed for game operation, so we've stubbed it in, if someone wants to use it, they override it
     * in their GameState subclass
     * <p/>
     * update the input
     */
    protected void initInput() {}

    /** @return Camera - the currentCamera in the CameraManager */
    public Camera getCamera() {
        return CameraManager.getInstance().getCurrentCamera();
    }

    /**
     * basic InputHandler setter
     *
     * @param ih - InputHandler set our current input to the inputHandler of our choice
     */
    protected void setInput(InputHandler ih) {
        input = ih;
    }

    /**
     * basic InputHandler getter
     *
     * @return InputHandler this version of the InputHandler
     */
    protected InputHandler getInput() {
        return input;
    }

    /**
     * implementation for superclass
     * <p/>
     * this method isn't needed for game operation, so we've stubbed it in, if someone wants to use it, they override it
     * in their GameState subclass
     * <p/>
     * initialize the GameState
     */
    protected void initGameState() {}

    /**
     * implementation for superclass
     * <p/>
     * this method isn't needed for game operation, so we've stubbed it in, if someone wants to use it, they override it
     * in their GameState subclass
     * <p/>
     * initialize the GameState
     *
     * @param tpf float - time per frame
     */
    protected void stateRender(float tpf) {}

    /**
     * abstract for subclasses
     * <p/>
     * onDeactivate called by setActive if you want to unload things this is the place to do it
     * <p/>
     * this method isn't needed for game operation, so we've stubbed it in, if someone wants to use it, they override it
     * in their GameState subclass
     *
     * @see {@link com.gerbildrop.dradis.cameras.StandardGameStateDefaultCamera#setActive(boolean)}
     */
    protected void onDeactivate() {}
}