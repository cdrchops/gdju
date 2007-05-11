package com.gerbildrop.dradis.state.action;

import java.util.concurrent.Callable;

import javax.swing.JButton;

import com.gerbildrop.dradis.state.menu.DradisGameMenu;
import com.gerbildrop.dradis.state.DradisSpaceGameState;
import com.gerbildrop.engine.state.MenuGameState;
import com.gerbildrop.engine.state.SpaceGameState;
import com.gerbildrop.engine.state.controller.TransitionMenuController;
import com.jme.input.MouseInput;
import com.jme.util.GameTaskQueueManager;
import com.jmex.game.StandardGame;
import com.jmex.game.state.GameStateManager;
import com.jmex.game.state.load.LoadingGameState;

public class MenuGameStateAction {
    public static void newGame(final StandardGame _game, final TransitionMenuController controller, final MenuGameState menuGameState) {
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                MouseInput.get().setCursorVisible(false);
                return null;
            }
        });

        controller.reset(DradisGameMenu.getInstance().getMainMenu(), new Runnable() {
            public void run() {
                LoadingGameState loading = new LoadingGameState();
                GameStateManager.getInstance().attachChild(loading);
                loading.setActive(true);

                DradisSpaceGameState sectorOne = new DradisSpaceGameState(_game, loading);
                GameStateManager.getInstance().attachChild(sectorOne);
                menuGameState.setActive(false);    // Disable menu
                sectorOne.setActive(true);

//                        List<ConsoleObject> list = new LinkedList<ConsoleObject>();
//                        list.add(new ConsoleObject("testConsole", new TestConsole()));
//                        list.add(new ConsoleObject("game", game));
//                        list.add(new ConsoleObject("state", this));
//                        list.add(new ConsoleObject("sectorOne", sectorOne));
//
//                        Console.getInstance().initConsole(list);
            }
        });
    }

    public static void disconnectGame(final StandardGame _game) {
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                SpaceGameState.getInstance().cleanup();
                SpaceGameState.getInstance().setActive(false);
                GameStateManager.getInstance().detachChild(SpaceGameState.getInstance());
                SpaceGameState.clearInstance();
                MouseInput.get().setCursorVisible(true);
                return null;
            }
        });

        JButton newGame = DradisGameMenu.getInstance().getNewGameButton();
        newGame.setName("New Game");
        newGame.setText("New Game");
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                _game.resetCamera();
                return null;
            }
        });
    }
}
