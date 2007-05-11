package com.gerbildrop.engine.state;

import java.util.concurrent.Callable;

import com.gerbildrop.engine.input.mouse.MousePointer;
import com.gerbildrop.engine.menu.IBackdrop;
import com.gerbildrop.engine.state.controller.BuiTransitionMenuController;
import com.jme.input.InputHandler;
import com.jme.input.MouseInput;
import com.jme.renderer.ColorRGBA;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.bss.BStyleSheetUtil;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameState;

public abstract class BuiMenuGameState extends BasicGameState implements IGameState {
    private static BuiMenuGameState instance;
    protected StandardGame _game;
    protected BuiTransitionMenuController controller;
    protected InputHandler input = new InputHandler();

    //    if you want a graphical mouse as opposed to the desktop mouse
    MousePointer mouse = new MousePointer();
    public boolean wantsGraphicalMouse;

    private IBackdrop backdrop;

    protected BWindow mainMenuInstance;

    protected ActionListener listener = new ActionListener() {
        public void actionPerformed(final ActionEvent event) {
            GameTaskQueueManager.getManager().update(new Callable<Object>() {
                    public Object call() throws Exception {
                        handleEvent(event);
                        return null;
                    }
                });

        }
    };

    public BuiMenuGameState(final StandardGame game, final boolean wantGraphicalMouse, final IBackdrop _backdrop) {
        super("Menu");
        _game = game;
        instance = this;
        this.backdrop = _backdrop;
        initBackdrop();

        BuiSystem.init(BStyleSheetUtil.getStyleSheetFromFile("/dradis/styles/styles.properties"));
        wantsGraphicalMouse = wantGraphicalMouse;
        buildUI();
    }

    private void initBackdrop() {
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                if (backdrop != null) {
                    backdrop.buildBackdrop(getRootNode(), _game.getCamera().getLocation());
                }
                return null;
            }
        });
    }

    private void buildUI() {


        if (wantsGraphicalMouse) {
            mouse.init(input);

            rootNode.attachChild(mouse.getMouse());
        }

        final ColorRGBA color = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
        controller = new BuiTransitionMenuController(2.0f, color);
        controller.setActive(false);
//        getDesktop().addController(controller);

        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                buildMenu();

                showMainMenuBase();
                setMenuInstance();

                return null;
            }
        });

        rootNode.attachChild(BuiSystem.getRootNode());
    }

    protected abstract void setMenuInstance();

    protected abstract void buildMenu();

    public abstract void handleEvent(ActionEvent evt);

    private void showMainMenuBase() {
        if (!wantsGraphicalMouse) {
            MouseInput.get().setCursorVisible(true);
        }
    }

    public void hideMainMenu() {
        controller.reset(mainMenuInstance, new Runnable() {
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

    public void showMainMenu() {
        showMainMenuBase();

        controller.reset(null, mainMenuInstance);
    }

    public void update(final float tpf) {
        super.update(tpf);
        if (backdrop != null) {
            backdrop.update(tpf);
        }

    }

    public static BuiMenuGameState getInstance() {
        return instance;
    }
}