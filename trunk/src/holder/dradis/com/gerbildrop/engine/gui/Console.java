package com.gerbildrop.engine.gui;

import java.util.List;

import bsh.EvalError;
import com.captiveimagination.game.console.GameConsole;
import com.captiveimagination.game.console.command.BasicCommandProcessor;
import com.captiveimagination.game.console.command.JavaCommandProcessor;
import com.jme.input.KeyInput;
import com.jmex.game.state.GameStateManager;

public class Console {
    private static final Console _INSTANCE = new Console();

    private Console() {}

    public static Console getInstance() {
        return _INSTANCE;
    }

    public void initConsole(List<ConsoleObject> cobb) {
        int rows = 5;
        GameConsole console = new GameConsole(KeyInput.KEY_GRAVE, rows, true);

        BasicCommandProcessor processor1 = new BasicCommandProcessor();
        processor1.registerCommand(cobb.get(0).getRegistryObject());

        JavaCommandProcessor processor2 = new JavaCommandProcessor(console);
        try {
            processor2.register("console", console);

            for (ConsoleObject consoleObject : cobb) {
                if (!consoleObject.getRegistryKey().equals("testConsole")) {
                    processor2.register(consoleObject.getRegistryKey(), consoleObject.getRegistryObject());
                }
            }
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        }

        console.registerCommandProcessor("command", processor1);
        console.registerCommandProcessor("java", processor2);

        GameStateManager.getInstance().attachChild(console);
        console.setActive(true);
    }


}
