package com.gerbildrop.engine.game;

import com.gerbildrop.engine.input.actions.MenuAction;
import com.gerbildrop.engine.input.actions.ScreenshotAction;
import com.gerbildrop.engine.input.config.InputConstants;
import com.gerbildrop.engine.input.controls.GameControls;
import com.jme.input.controls.controller.ActionRepeatController;
import com.jmex.game.StandardGame;
import com.jmex.game.StandardGame.GameType;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.FPSGameState;
import com.jmex.game.state.GameStateManager;

public class GameBase implements InputConstants {
    protected static StandardGame game;

    public void run(String gameName, String controlsConfigFile, String basicGameStateName) throws Exception {
        game = new StandardGame(gameName, GameType.GRAPHICAL, null);
        game.getSettings().setHeight(1024);
        game.getSettings().setWidth(1280);
        game.start();

        // Configure Controls if not already configured
        //todo: tmp for reading input from an xml or serialized file -- timo 01Feb07
//        GameControls.getInstance().init(game, "configs/viperInputConfig.xml");
        GameControls.getInstance().init(game, controlsConfigFile);

        // Instantiate a global game state that will always be running
        final BasicGameState globalState = new BasicGameState(basicGameStateName);
        GameStateManager.getInstance().attachChild(globalState);
        globalState.setActive(true);

        // Configure global actions
//      Toggle FPS
        final FPSGameState fpsState = new FPSGameState();
        GameStateManager.getInstance().attachChild(fpsState);
        ActionRepeatController toggleFPSController = new ActionRepeatController(GameControls.getInstance().getControlManager().getControl(SHOW_FPS), 500, new Runnable() {
            public void run() {
                fpsState.setActive(!fpsState.isActive());
            }
        });

        globalState.getRootNode().addController(toggleFPSController);

        // Toggle Menu
        ActionRepeatController toggleMenuController = new ActionRepeatController(GameControls.getInstance().getControlManager().getControl(TOGGLE_MENU), 500, new MenuAction());
        globalState.getRootNode().addController(toggleMenuController);

        // Screenshot
        ActionRepeatController printScreenController = new ActionRepeatController(GameControls.getInstance().getControlManager().getControl(SCREENSHOT), 500, new ScreenshotAction("screenshot", true));
        globalState.getRootNode().addController(printScreenController);
    }
}