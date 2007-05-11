package com.gerbildrop.engine.input.actions;

import java.util.concurrent.Callable;

import com.gerbildrop.engine.state.MenuGameState;
import com.gerbildrop.engine.state.SpaceGameState;
import com.jme.input.MouseInput;
import com.jme.util.GameTaskQueueManager;

/** @author Matthew D. Hicks */
public class MenuAction implements Runnable {
    
    public void run() {
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                if (MenuGameState.getInstance().isActive()) {

                    if (SpaceGameState.getInstance() == null) {
                        return null;
                    }

                    SpaceGameState.getInstance().setControllersActive(true);
                    SpaceGameState.getInstance().setHUDActive(true);
                    MenuGameState.getInstance().hideMainMenu();
                    MouseInput.get().setCursorVisible(false);
                } else {
//                    JButton button = GameMenu.getInstance().getNewGameButton();
//                    button.setText("Disconnect Game");
//                    button.setName("Disconnect Game");

                    SpaceGameState.getInstance().setControllersActive(false);
                    SpaceGameState.getInstance().setHUDActive(false);
                    MenuGameState.getInstance().setActive(true);
                    MenuGameState.getInstance().showMainMenu();
                    MouseInput.get().setCursorVisible(true);
                }

                return null;
            }
        });
    }
}
