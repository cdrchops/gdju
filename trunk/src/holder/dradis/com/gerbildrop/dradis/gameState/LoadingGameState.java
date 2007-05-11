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

import java.net.URL;

import com.gerbildrop.dradis.gameState.base.BasicGameState;
import com.gerbildrop.dradis.gameState.base.StandardGameState;
import com.jme.test.loading.LoadingManager;
import com.jme.test.loading.LoadingObject;

/**
 * modified by gerbildrop
 *
 * @author Matthew D. Hicks
 * @author vivaldi
 * @version $Id: LoadingGameState.java,v 1.15 2007/04/04 14:29:06 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class LoadingGameState extends StandardGameState {
    /** the final name of this GameState */
    private static final String STATE_NAME = "loading";

    /** the final name of the parent GameState */
    private static final String PARENT_NAME = "menu";

    private Runnable toRun;
    private String loaderName;
    private BasicGameState toPassOffTo;

    public LoadingGameState(Runnable toRun, String loaderName, BasicGameState toPassOffTo) {
        super(STATE_NAME);
        this.toRun = toRun;
        this.loaderName = loaderName;
        this.toPassOffTo = toPassOffTo;
    }

    public void initGameState() {
        LoadingManager.getInstance().addLoader(new LoadingObject(loaderName, null, toRun, toPassOffTo));
        URL url = ClassLoader.getSystemResource("dradis/displays/loading/loadBackground.png");
//        URL url2 = ClassLoader.getSystemResource("dradis/displays/HUDS/hud2.png");
        URL url2 = ClassLoader.getSystemResource("dradis/displays/loading/loadingComplete.png");
        LoadingManager.getInstance().runLoader(loaderName, url, url2);
    }

    public void stateUpdate(float time) {
        super.stateUpdate(time);
        LoadingManager.getInstance().checkState(loaderName);
    }

    protected String getStateName() {
        return STATE_NAME;
    }

    protected String getParentName() {
        return PARENT_NAME;
    }
}
