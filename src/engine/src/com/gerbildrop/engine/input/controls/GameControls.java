package com.gerbildrop.engine.input.controls;

import com.gerbildrop.engine.input.config.InputConfigFile;
import com.gerbildrop.engine.input.config.InputHelper;
import com.gerbildrop.engine.input.config.KeyControl;
import com.gerbildrop.engine.input.config.MouseControl;
import com.gerbildrop.xml.DxoDoc;
import com.jme.input.controls.GameControlManager;
import com.jme.input.controls.binding.KeyboardBinding;
import com.jme.input.controls.binding.MouseAxisBinding;
import com.jmex.game.StandardGame;

public class GameControls {
    private static GameControlManager manager;

    private GameControls() {}

    private static final GameControls _INSTANCE = new GameControls();

    public static GameControls getInstance() {
        return _INSTANCE;
    }

    public void init(StandardGame game, String fileName) {
        // Configure Controls if not already configured
//        manager = GameControlManager.load(game.getSettings());
        if (manager == null) {
            manager = new GameControlManager();
            InputConfigFile.parseConfigs(DxoDoc.parseFile(GameControls.class.getResource(fileName)));
            manager.createControls(InputConfigFile.getInstance().getNamesOfControls());
            assignDefaultControls();
            GameControlManager.save(manager, game.getSettings());
        }
    }

    public void assignDefaultControls() {
        manager.clearBindings();

        for (KeyControl keyControl : InputConfigFile.getInstance().getKeyConfigs()) {
            manager.getControl(keyControl.getName()).addBinding(new KeyboardBinding(InputHelper.characterDetection(keyControl.getValue())));
        }

        for (MouseControl mouseControl : InputConfigFile.getInstance().getMouseConfigs()) {
            manager.getControl(mouseControl.getName()).addBinding(new MouseAxisBinding(InputHelper.mouseAxisDetection(mouseControl.getValue()), mouseControl.isReverse()));
        }
    }

    public GameControlManager getControlManager() {
        return manager;
    }
}
