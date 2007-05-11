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

import com.gerbildrop.dradis.gameState.base.StandardGameState;
import com.gerbildrop.dradis.gameState.util.Cursor;
import com.gerbildrop.dradis.gameState.util.HoverButton;
import com.gerbildrop.dradis.loaders.DradisNodeStorage;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.state.LightState;

/**
 * MenuState is the main menu that the player will see when the game loads
 *
 * @author vivaldi
 * @version $Id: MenuState.java,v 1.24 2007/04/04 14:29:06 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class MenuState extends StandardGameState {
    private static final HoverButton options = new HoverButton("options");
    private static final HoverButton singleMission = new HoverButton("singleMission");
    private static final HoverButton campaign = new HoverButton("campaign");
    private static final HoverButton exit = new HoverButton("exit");
    private static final Cursor cursor = new Cursor("cursor");

    /** the final name of this GameState */
    private static final String STATE_NAME = "menu";
    /** the final name of the parent GameState */
    private static final String PARENT_NAME = "menu";

    /**
     * constructor
     * <p/>
     * calls superclass with the final String name of this GameState
     */
    public MenuState() {
        super(STATE_NAME);
    }

    /**
     * initialize our GameState
     * <p/>
     * set the title in our DisplaySystem, if needed
     */
    public void initGameState() {
        display.setTitle("Main Menu");
    }

    /** do a check for your loaded portions wrap those with a not loaded call then your objects are only loaded once */
    public void onActivate() {
        if (!loaded) {
            options.initialize(DradisNodeStorage.get("optionNode"), DradisNodeStorage.get("optionLitNode"), new Vector3f(26, 320, 0));
            singleMission.initialize(DradisNodeStorage.get("singleMissionNode"), DradisNodeStorage.get("singleMissionLitNode"), new Vector3f(26, 615 - 56 * 1.8f, 0));
            exit.initialize(DradisNodeStorage.get("exitNode"), DradisNodeStorage.get("exitLitNode"), new Vector3f(26, 320 - 56 * 1.8f, 0));
            campaign.initialize(DradisNodeStorage.get("campaignNode"), DradisNodeStorage.get("campaignLitNode"), new Vector3f(26, 515 - 56 * 1.8f, 0));
        }

        rootNode.attachChild(DradisNodeStorage.get("backgroundNode").getNode());
        rootNode.attachChild(exit);
        rootNode.attachChild(singleMission);
        rootNode.attachChild(campaign);
        rootNode.attachChild(options);

        super.onActivate();

        // must be done after the super.onActivate or the cursor will never display
        initCursor();

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.updateGeometricState(0, true);
        rootNode.updateRenderState();
    }


    /** Inits the input handler we will use for navigation of the menu. */
    protected void initInput() {
        setInput(new MenuHandler(this));
    }

    /** Creates a pretty cursor. */
    private void initCursor() {
        cursor.load(getInput());
        rootNode.attachChild(cursor);
    }

    /** @param tpf float - time per frame */
    protected void stateUpdate(float tpf) {
        super.stateUpdate(tpf);
        float x = cursor.getHotSpotPosition().x;
        float y = cursor.getHotSpotPosition().y;

        singleMission.selected(x, y);
        campaign.selected(x, y);
        options.selected(x, y);
        exit.selected(x, y);
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
     * implementation of the abstract method in the StandardGameState superclass
     *
     * @return String - final STATE_NAME
     */
    protected String getStateName() {
        return STATE_NAME;
    }
}