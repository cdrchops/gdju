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
import com.gerbildrop.dradis.util.DradisNode;
import com.jme.math.Vector3f;

/**
 * @author vivaldi
 * @version $Id: LoginState.java,v 1.14 2007/04/04 14:29:06 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class LoginState extends StandardGameState {
    /** the final name of this GameState */
    private static final String STATE_NAME = "login";

    /** the final name of the parent GameState */
    private static final String PARENT_NAME = "menu";

    private static DradisNode login = new DradisNode();

    public LoginState() {
        super(STATE_NAME);
    }

    public void initGameState() {


        login.load("loginNode", "loginQuad", "dradis/displays/backgrounds/loginMessage.png");

        int x = display.getWidth() / 2 - login.getTextureWidth() / 2;
        int y = display.getHeight() / 2 - login.getTextureHeight() / 2;

        login.getNode().setLocalTranslation(new Vector3f(x, y, 0));

        // Add it to the scene.
        rootNode.attachChild(login.getNode());
    }

    public void stateUpdate(float time) {
        super.stateUpdate(time);
//        dlso.update(time);
    }

    protected String getStateName() {
        return STATE_NAME;
    }

    protected String getParentName() {
        return PARENT_NAME;
    }
}
