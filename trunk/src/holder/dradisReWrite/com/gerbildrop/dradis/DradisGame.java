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

package com.gerbildrop.dradis;

import com.gerbildrop.dradis.board.WorldMap;
import com.gerbildrop.dradis.cameras.CameraManager;
import com.gerbildrop.dradis.input.PlaneNodeHandler;
import com.jme.app.SimpleGame;

/**
 * The Base Game for all Dradis gameplay
 *
 * @author vivaldi
 * @version $Id: DradisGame.java,v 1.19 2007/04/04 14:31:06 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisGame extends SimpleGame {
    private WorldMap worldMap = new WorldMap();

    protected void simpleInitGame() {
//        LoggingSystem.getLoggingSystem().setLevel(Level.OFF);

        //always set light on
        lightState.setEnabled( !lightState.isEnabled() );

        CameraManager.getInstance().create();

        //change the display title
        display.setTitle("XBG - Combat Simulator");

        //load the worldmap
        worldMap.load(rootNode);

        //load the input
        input = new PlaneNodeHandler(worldMap.getPlayer(), rootNode);
    }

    protected void simpleUpdate() {
        worldMap.update(tpf);

        CameraManager.getInstance().update(tpf);
    }
}