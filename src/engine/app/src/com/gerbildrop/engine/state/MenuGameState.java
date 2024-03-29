package com.gerbildrop.engine.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JDesktopPane;

import com.gerbildrop.engine.state.controller.TransitionMenuController;
import com.gerbildrop.engine.state.swing.MainGameMenu;
import com.jme.input.MouseInput;
import com.jme.renderer.ColorRGBA;
import com.jme.util.GameTaskQueueManager;
import com.jmex.awt.swingui.JMEDesktopState;
import com.jmex.game.StandardGame;

public abstract class MenuGameState extends JMEDesktopState implements ActionListener, IGameState {
    private static MenuGameState instance;
    protected StandardGame _game;
    protected JDesktopPane desktop;
    protected TransitionMenuController controller;

    public MenuGameState(StandardGame game) {
        super();
        _game = game;
        instance = this;
    }

    protected void buildUI() {
        final ColorRGBA color = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
        getDesktop().setDefaultColor(color);
        desktop = getDesktop().getJDesktop();
        desktop.setLayout(null);
        controller = new TransitionMenuController(2.0f, color);
        controller.setActive(false);
        getDesktop().addController(controller);
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                buildMenu();
                MouseInput.get().setCursorVisible(true);
                return null;
            }
        });
    }

    public abstract void buildMenu();

    public abstract void actionPerformed(ActionEvent evt);

    public void showMainMenu() {
        controller.reset(MainGameMenu.getInstance().getMenu(), new Runnable() {
            public void run() {
                // Disable menu
                setActive(false);

                GameTaskQueueManager.getManager().update(new Callable<Object>() {
                    public Object call() throws Exception {
                        MouseInput.get().setCursorVisible(false);
                        return null;
                    }
                });
            }
        });
    }

    public void hideMainMenu() {
        controller.reset(null, MainGameMenu.getInstance().getMenu());
        MouseInput.get().setCursorVisible(true);
    }

    public void update(float tpf) {
        super.update(tpf);
    }

    public static MenuGameState getInstance() {
        return instance;
    }
}