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

import com.gerbildrop.dradis.state.DradisMenuGameState;
import com.gerbildrop.engine.game.GameBase;
import com.jmex.game.state.GameStateManager;
import com.jmex.sound.openAL.SoundSystem;

/**
 * The Base Game for all Dradis gameplay
 *
 * @author vivaldi
 * @version $Id: DradisGame.java,v 1.22 2007/04/04 14:27:26 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisGame extends GameBase {

    public void run() throws Exception {
        super.run("DRADIS -- XBG Combat Simulator (MMORPG)", "/dradis/config/controls.xml", "GlobalGameState");

//        ColorScheme scheme = new AquaColorScheme() {
//            public Color getForegroundColor() {
//                return Color.WHITE;
//            }
//        };
//
//        SubstanceTheme theme = new SubstanceTheme(scheme, "Custom", ThemeKind.DARK);
//        UIManager.put(SubstanceLookAndFeel.NO_EXTRA_ELEMENTS, Boolean.TRUE);
//        SubstanceLookAndFeel.setCurrentTheme(theme);
//
//        UIManager.setLookAndFeel(new SubstanceLookAndFeel());

        // Instantiate Main Menu
        DradisMenuGameState menu = new DradisMenuGameState(game);
        GameStateManager.getInstance().attachChild(menu);
        menu.setActive(true);
    }

    public static void main(String[] args) {
        try {
            new DradisGame().run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SoundSystem.stopAllSamples();
        }
    }
}

//init
//LoadingManager.getInstance().addLoader(new LoadingObject("gameStart", null, new DradisResourceLoader.DradisResourceLoaderThread(), new MenuState()));

//protected final void initGame() {
//        Timer.getTimer().reset();
//        instance = this;
//        display.setTitle("XBG - Combat Simulator");
//
//        URL url = ClassLoader.getSystemResource("dradis/displays/loading/loadBackground.png");
//        URL url2 = ClassLoader.getSystemResource("dradis/displays/loading/loadingComplete.png");
//        LoadingManager.getInstance().runLoader("gameStart", url, url2);
//    }

//update
//LoadingManager.getInstance().checkState("gameStart");