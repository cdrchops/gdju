package com.gerbildrop.dradis.state;

import java.awt.event.ActionEvent;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.gerbildrop.dradis.state.action.MenuGameStateAction;
import com.gerbildrop.dradis.state.menu.DradisGameMenu;
import com.gerbildrop.engine.input.controls.GameControls;
import com.gerbildrop.engine.state.MenuGameState;
import com.gerbildrop.engine.state.menu.ControlsMenu;
import com.gerbildrop.engine.state.menu.MenuBackdrop;
import com.gerbildrop.engine.state.menu.SettingsMenu;
import com.jme.input.MouseInput;
import com.jme.input.controls.GameControlManager;
import com.jme.util.GameTaskQueueManager;
import com.jmex.game.StandardGame;

public class DradisMenuGameState extends MenuGameState {
    public DradisMenuGameState(StandardGame game) {
        super(game);
    }

    public void buildMenu() {
        DradisGameMenu.getInstance().buildMainMenu(desktop, this);
        ControlsMenu.getInstance().buildControlsMenu(desktop, this);
        SettingsMenu.getInstance().buildSettingsMenu(desktop, _game.getSettings(), this);

        MenuBackdrop.getInstance().buildBackdrop(getRootNode(), _game.getCamera().getLocation());
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JButton) {
            JButton button = (JButton) evt.getSource();
            String buttonName = button.getName();

            if (buttonName.equals("New Game")) {
                MenuGameStateAction.newGame(_game, controller, this);
            } else if (buttonName.equals("Disconnect Game")) {
                MenuGameStateAction.disconnectGame(_game);
            } else if (buttonName.equals("Controls")) {
                ControlsMenu.getInstance().getControlEditor().reset();
                controller.reset(DradisGameMenu.getInstance().getMainMenu(), ControlsMenu.getInstance().getControlsPanel());
            } else if (buttonName.equals("Settings")) {
                controller.reset(DradisGameMenu.getInstance().getMainMenu(), SettingsMenu.getInstance().getSettingsPanel());
            } else if (buttonName.equals("controlsToMain")) {
                controller.reset(ControlsMenu.getInstance().getControlsPanel(), DradisGameMenu.getInstance().getMainMenu());
            } else if (buttonName.equals("Quit")) {
                controller.setGame(_game);
                controller.reset(DradisGameMenu.getInstance().getMainMenu(), (JPanel) null);
            } else if (buttonName.equals("controlsDefaults")) {
                GameControls.getInstance().assignDefaultControls();
                ControlsMenu.getInstance().getControlEditor().reset();
            } else if (buttonName.equals("controlsSave")) {
                ControlsMenu.getInstance().getControlEditor().apply();
                GameControlManager.save(GameControls.getInstance().getControlManager(), _game.getSettings());
            } else if (buttonName.equals("settingsToMain")) {
                controller.reset(SettingsMenu.getInstance().getSettingsPanel(), DradisGameMenu.getInstance().getMainMenu());
            } else if (buttonName.equals("settingsDefaults")) {
                SettingsMenu.getInstance().getGameSettingsPanel().defaults();
            } else if (buttonName.equals("settingsSave")) {
                SettingsMenu.getInstance().getGameSettingsPanel().apply();
                // TODO handle resolution changes (reposition locations) and figure out where the skybox goes
                // TODO request input to verify screen is okay or revert in 10 seconds
                _game.recreateGraphicalContext();
            }
        }
    }

    public void showMainMenu() {
        controller.reset(null, DradisGameMenu.getInstance().getMainMenu());
    }

    public void hideMainMenu() {
        controller.reset(DradisGameMenu.getInstance().getMainMenu(), new Runnable() {
            public void run() {
                setActive(false);    // Disable menu

                GameTaskQueueManager.getManager().update(new Callable<Object>() {
                    public Object call() throws Exception {
                        MouseInput.get().setCursorVisible(false);
                        return null;
                    }
                });
            }
        });
    }

    public void update(float tpf) {
        super.update(tpf);
        MenuBackdrop.getInstance().update(tpf);
    }

}
