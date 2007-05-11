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

package com.gerbildrop.dradis.gameState;

import com.gerbildrop.dradis.board.WorldMap;
import com.gerbildrop.dradis.gameState.base.StandardGameState;
import com.gerbildrop.dradis.input.PlaneNodeHandler;
import com.gerbildrop.dradis.terrain.DradisSkyBox;

/**
 * Dradis 3d FlightSimulator portion of the DradisGame
 *
 * @author vivaldi
 * @version $Id: PlayGameState.java,v 1.20 2007/04/04 14:29:06 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class PlayGameState extends StandardGameState {
    private static final WorldMap worldMap = new WorldMap();
    private static final DradisSkyBox dradisSkyBox = new DradisSkyBox();

    /** the final name of this GameState */
    private static final String STATE_NAME = "game";

    /** the final name of the parent GameState */
    private static final String PARENT_NAME = "menu";

    /** our singleton instance of this class */
    private static final PlayGameState _INSTANCE = new PlayGameState();

    /** @return PlayGameState Singleton instance of the PlayGameState class */
    public static PlayGameState getInstance() {
        return _INSTANCE;
    }

    /** constructor for our PlayGameState singleton */
    private PlayGameState() {
        super(STATE_NAME);
    }

    /**
     * implementation for superclass
     * <p/>
     * onActivate called by setActive if you want to load things once this is the place to do it
     * <p/>
     *
     * @see {@link com.gerbildrop.dradis.cameras.StandardGameStateDefaultCamera#setActive(boolean)}
     */
    protected void onActivate() {
        if (!loaded) {
            worldMap.load(rootNode);
            dradisSkyBox.load(getRootNode());
        }

        rootNode.attachChild(dradisSkyBox.getNode());
        super.onActivate();
    }

    /**
     * implementation for superclass
     * <p/>
     * update the input
     */
    protected void initInput() {
        setInput(new PlaneNodeHandler(worldMap.getPlayer(), rootNode));
    }

    /**
     * Every GameState in the Dradis Game has a final String set for the STATE_NAME and a getter and setter setup to
     * return that final value
     * <p/>
     * implementation of superclass
     *
     * @return String name of the GameState
     */
    protected String getStateName() {
        return STATE_NAME;
    }

    /**
     * Every GameState in the Dradis Game has a final String set for the PARENT_NAME and a getter and setter setup to
     * return that final value
     * <p/>
     * implementation of super class
     *
     * @return String name of the parent gameState that this will go back to once no longer active
     */
    protected String getParentName() {
        return PARENT_NAME;
    }

    /**
     * local call to update our input and rootNode
     *
     * @param tpf float - time per frame
     */
    public void stateUpdate(float time) {
        worldMap.update(time);
        inputUpdate(time);
        dradisSkyBox.update(time, getCamera());
        super.stateUpdate(time);
    }
}
