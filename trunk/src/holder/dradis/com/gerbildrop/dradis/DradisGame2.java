package com.gerbildrop.dradis;

import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.game.state.DebugGameState;
import com.jmex.editors.swing.settings.GameSettingsPanel;
import com.jme.scene.shape.Box;
import com.jme.math.Vector3f;
import com.jme.bounding.BoundingSphere;

public class DradisGame2 {
    private StandardGame game;
    private static BasicGameState globalState;

    public DradisGame2() throws Exception {
        // Instantiate StandardGame
        game = new StandardGame("A Simple Test");
        // Show settings screen
        GameSettingsPanel.prompt(game.getSettings());
        // Start StandardGame, it will block until it has initialized successfully, then return
        game.start();

        // Create a DebugGameState - has all the built-in features that SimpleGame provides
        // NOTE: for a distributable game implementation you'll want to use something like
        // BasicGameState instead and provide control features yourself.
        DebugGameState state = new DebugGameState();
        // Put our box in it
        Box box = new Box("my box", new Vector3f(0, 0, 0), 2, 2, 2);
        box.setModelBound(new BoundingSphere());
        box.updateModelBound();
        // We had to add the following line because the render thread is already running
        // Anytime we add content we need to updateRenderState or we get funky effects
        box.updateRenderState();
        state.getRootNode().attachChild(box);
        // Add it to the manager
        GameStateManager.getInstance().attachChild(state);
        // Activate the game state
        state.setActive(true);
    }

    public static void main(String[] args) throws Exception {
        new DradisGame2();
    }

    public static BasicGameState getGlobalState() {
        return globalState;
    }

    public String exit() {
        return "I would exit, but I don't know how.";
    }

    public void quit() {
        System.exit(0);
    }
}
