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

package com.gerbildrop.loader.holdArea;

import com.gerbildrop.dradis.DradisGame;
import com.gerbildrop.dradis.displays.DradisNodeStorage;
import com.jme.input.InputHandler;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;
import jmetest.game.state.IngameState;

/**
 * The input handler we use to navigate the menu. E.g. has an absolute mouse. If the escape key is pressed the
 * application will be ended using the static exit method of TestGameStateSystem.
 *
 * @author Per Thulin modified vivaldi
 * @version $Id: MenuHandler.java,v 1.1 2007/04/04 14:27:21 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class MenuHandler extends InputHandler {
    private static GameState _myState;

    public MenuHandler(GameState myState) {
        setKeyBindings();
        _myState = myState;
    }

    /**
     * sets up keybindings for the MenuGameState
     * these keys are in addition to the ones setup by the default GameState
     *
     * exit is duplicated here, if we exit out of the main menu, then we're shutting down the application too
     */
    private void setKeyBindings() {
        KeyBindingManager kb = KeyBindingManager.getKeyBindingManager();

        kb.set("exit", KeyInput.KEY_ESCAPE);
        kb.set("screen_shot", KeyInput.KEY_F1);
        kb.set("parallel_projection", KeyInput.KEY_F2);
        kb.set("toggle_depth", KeyInput.KEY_F3);
        kb.set("clockShow", KeyInput.KEY_F4);
        kb.set("dradisShow", KeyInput.KEY_F5);
        kb.set("lsoShow", KeyInput.KEY_F6);
        kb.set("timerState", KeyInput.KEY_F8);
        kb.set("loginState", KeyInput.KEY_F9);
        kb.set("loadingState", KeyInput.KEY_F10);
        kb.set("gameState", KeyInput.KEY_F11);
        kb.set("loggingSwitch", KeyInput.KEY_F12);
        kb.set("enter", KeyInput.KEY_RETURN);

        addAction(new ExitAction(), "exit", false);
        addAction(new EnterAction(), "enter", false);
        addAction(new LoginAction(), "loginState", false);
        addAction(new LSOAction(), "lsoShow", false);
        addAction(new ClockAction(), "clockShow", false);
        addAction(new DradisAction(), "dradisShow", false);
        addAction(new GameAction(), "gameState", false);
//        addAction(new LoadingAction(), "loadingState", false);
        addAction(new TimerAction(), "timerState", false);
    }

    private static class ExitAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            DradisGame.exit();
        }
    }

    private static class LSOAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            GameState ingame = LSOGameState.getInstance();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

    private static class DradisAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            GameState ingame = DradisGameState.getInstance();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

    private static boolean clockLoaded = false;

    /**
     * runs a thread to load the clock images
     * when that is done we'll show the ClockGameState singleton
     */
    private static class ClockAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            if (!clockLoaded) {
                Thread t = new Thread(new DradisNodeStorage.DradisNodeStorageClockThread());
                t.start();
                while (t.getState() != Thread.State.TERMINATED) {

                }

                clockLoaded = true;
            }

            GameState ingame = ClockGameState.getInstance();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

    private static boolean modelsLoaded = false;
    private static class GameAction extends InputAction {
        public void performAction(InputActionEvent evt) {
             if (!modelsLoaded) {
                 //todo: this HAS to be fixed.  I think this is why models are pinkish when
                 //      loading the game on a slower machine.  The models aren't given
                 //      enough time to fully load
                Thread t = new Thread(new DradisModelStorage.DradisModelStorageThread());
                t.start();
                while (t.getState() != Thread.State.TERMINATED) {

                }

                modelsLoaded = true;
            }

            GameState ingame = PlayGameState.getInstance();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

//    private class LoadingAction extends InputAction {
//        public void performAction(InputActionEvent evt) {
//            GameState ingame = new LoadingGameState();
//            ingame.setActive(true);
//            GameStateManager.getInstance().attachChild(ingame);
//            myState.setActive(false); // Deactivate this (the menu) state.
//        }
//    }

    private static class LoginAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            GameState ingame = new LoginState();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

    private static boolean timerLoaded = false;

//    private static StandaloneLoader sl = new StandaloneLoader("slNode");
    /**
     * runs a thread to load the timer images
     * when that is done we'll show the TimerGameState singleton
     */
    private static class TimerAction extends InputAction {
        public void performAction(InputActionEvent evt) {

//            URL url = ClassLoader.getSystemResource("dradis/displays/loading/loadBackground.png");
//            URL url2 = ClassLoader.getSystemResource("dradis/displays/loading/loadingComplete.png");
//
//            sl.init(url, url2, new DradisNodeStorage.DradisNodeStorageTimerThread());
//            sl.run();
//
//            while (!sl.isDoneLoading()) {
//                sl.update(Time.getInstance().getTpf());
//                sl.checkState();
//            }

            if (!timerLoaded) {
                Thread t = new Thread(new DradisNodeStorage.DradisNodeStorageTimerThread());
                t.start();
                while (t.getState() != Thread.State.TERMINATED) {

                }

                timerLoaded = true;
            }

            GameState ingame = TimerGameState.getInstance();
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }

    private static class EnterAction extends InputAction {
        public void performAction(InputActionEvent evt) {
            GameState ingame = new IngameState("ingame");
            ingame.setActive(true);
            GameStateManager.getInstance().attachChild(ingame);
            _myState.setActive(false); // Deactivate this (the menu) state.
        }
    }


}