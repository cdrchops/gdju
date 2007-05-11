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

import com.jme.scene.Node;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;
import com.jmex.game.state.GameState;

/**
 * rewritten BasicGameState to do what I wanted it to do for whatever reason portions of the BasicGameState in JME core
 * did not do everything that I needed.
 *
 * @author vivaldi
 * @version $Id: BasicGameState.java,v 1.13 2007/04/04 14:29:01 vivaldi Exp $
 * @since Oct 12, 2006
 */
public abstract class BasicGameState extends GameState {
    /* DisplaySystem from our DisplaySystem singleton */
    protected static final DisplaySystem display = DisplaySystem.getDisplaySystem();

    /* the rootnode that all other objects in this gamestate will be attached to*/
    protected Node rootNode;

    /**
     * Constructor for a BasicGameState object
     *
     * @param name String name - prefix of the rootNode and name of this gameState
     */
    public BasicGameState(String name) {
        this.name = name;
        rootNode = new Node(name + ": RootNode");

        initZBuffer();
        initGameState();

        rootNode.updateGeometricState(0.0f, true);
        rootNode.updateRenderState();
    }

    /**
     * update method to tell us to update the rootNode with the times per frame
     *
     * @param tpf float - time per frame
     */
    public void update(float tpf) {
        inputUpdate(tpf);
        rootNode.updateGeometricState(tpf, true);
    }

    /**
     * render method to tell us to render the display with the rootNode
     *
     * @param tpf float - time per frame *not used*
     */
    public void render(float tpf) {
        stateRender(tpf);
        display.getRenderer().draw(rootNode);
    }

    /**
     * local call to update our input and rootNode
     *
     * @param tpf float - time per frame
     */
    protected void stateUpdate(float tpf) {
        update(tpf);
    }

    /** cleanup method does nothing */
    public void cleanup() {
        //do nothing
    }

    /**
     * convenience getter for our root node
     *
     * @return Node - root node in this game state
     */
    public Node getRootNode() {
        return rootNode;
    }

    /**
     * initialize the ZBuffer and set that RenderState to the rootNode
     */
    protected void initZBuffer() {
        ZBufferState buf = DisplaySystem.getDisplaySystem().
                getRenderer().createZBufferState();
        buf.setEnabled(true);
        buf.setFunction(ZBufferState.CF_LEQUAL);
        rootNode.setRenderState(buf);
    }

    /**
     * calls onActivate or onDeactivate
     * onActivate if the state is active
     * onDeactivate if the state is no longer active
     * set the active state value into the super class
     *
     * @param active boolean - active or not
     */
    public void setActive(boolean active) {
        if (active) onActivate();
        else onDeactivate();
        super.setActive(active);
    }

    /**
     * abstract for subclasses
     *
     * initialize the GameState
     */
    protected abstract void initGameState();

    /**
     * abstract for subclasses
     *
     * initialize the input
     */
    protected abstract void initInput();

    /**
     * abstract for subclasses
     *
     * update the input
     *
     * @param tpf float - time per frame
     */
    protected abstract void inputUpdate(float tpf);

    /**
     * abstract for subclasses
     *
     * render the GameState
     *
     * @param tpf float - time per frame
     */
    protected abstract void stateRender(float tpf);

    /**
     * abstract for subclasses
     *
     * onActivate called by setActive
     * if you want to load things once this is the place to do it
     *
     * @see {@link com.gerbildrop.dradis.cameras.StandardGameStateDefaultCamera#setActive(boolean)}
     */
    protected abstract void onActivate();

    /**
     * abstract for subclasses
     *
     * onDeactivate called by setActive
     * if you want to unload things this is the place to do it
     *
     * @see {@link com.gerbildrop.dradis.cameras.StandardGameStateDefaultCamera#setActive(boolean)}
     */
    protected abstract void onDeactivate();

    /**
     * Every GameState in the Dradis Game has a final String set for the PARENT_NAME
     * and a getter and setter setup to return that final value
     *
     * abstract for subclasses
     *
     * @return String name of the parent gameState that this will go back to once no longer active
     */
    protected abstract String getParentName();

    /**
     * Every GameState in the Dradis Game has a final String set for the STATE_NAME
     * and a getter and setter setup to return that final value
     *
     * abstract for subclasses
     *
     * @return String name of the GameState
     */
    protected abstract String getStateName();
}