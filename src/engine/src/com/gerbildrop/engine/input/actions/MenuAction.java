package com.gerbildrop.engine.input.actions;

import java.util.concurrent.Callable;

import com.gerbildrop.engine.state.BuiMenuGameState;
import com.gerbildrop.engine.state.IGameState;
import com.gerbildrop.engine.state.IPlayableGameState;
import com.gerbildrop.engine.state.MenuGameState;
import com.gerbildrop.engine.state.SpaceGameState;
import com.jme.util.GameTaskQueueManager;

/** @author Matthew D. Hicks */
public class MenuAction implements Runnable {
//    private IGameState igs;
//    private IPlayableGameState sgi;
//
//    public MenuAction(final IGameState _igs, final IPlayableGameState _sgi) {
//        igs = _igs;
//        sgi = _sgi;
//    }

    public void run() {
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                IGameState igs = BuiMenuGameState.getInstance() != null ? BuiMenuGameState.getInstance() : MenuGameState.getInstance() != null ? MenuGameState.getInstance() : null;
                IPlayableGameState sgi = SpaceGameState.getInstance();

                if (igs != null) {
                    if (igs.isActive()) {
                        if (sgi == null) {
                            return null;
                        }

                        sgi.setControllersActive(true);
                        sgi.setHUDActive(true);
                        igs.hideMainMenu();
                    } else {
                        //                    JButton button = GameMenu.getInstance().getNewGameButton();
                        //                    button.setText("Disconnect Game");
                        //                    button.setName("Disconnect Game");

                        sgi.setControllersActive(false);
                        sgi.setHUDActive(false);
                        igs.setActive(true);
                        igs.showMainMenu();
                    }
                }

                return null;
            }
        });
    }
}