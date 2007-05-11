package com.gerbildrop.direction.consoleTest;

import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Box;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jmetest.physics.TestStressPhysics;
import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

/** @author Matthew D. Hicks */
public class TestConsole2 {
    public String exit() {
        return "I would exit, but I don't know how.";
    }

    public void quit() {
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        StandardGame game = new StandardGame("Test Console");
        game.getSettings().clear();
        game.start();

        TextureState ts = game.getDisplay().getRenderer().createTextureState();
        Texture t = TextureManager.loadTexture(TestStressPhysics.class.getClassLoader().getResource("jmetest/data/texture/cloud_land.jpg"), Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR);
        t.setWrap(Texture.WM_WRAP_S_WRAP_T);
        ts.setTexture(t);
        Box box = new Box("Box", new Vector3f(), 10.0f, 10.0f, 10.0f);
        box.setModelBound(new BoundingBox());
        box.updateModelBound();
        box.setRenderState(ts);
        BasicGameState debug = new BasicGameState("Basic");
        debug.getRootNode().attachChild(box);
        debug.getRootNode().updateRenderState();

        GameStateManager.getInstance().attachChild(debug);
        debug.setActive(true);

        GameConsole2 console = new GameConsole2(KeyInput.KEY_GRAVE, 5, true);
//    	GameConsole console = new GameConsole(KeyInput.KEY_GRAVE, rows, true,
//				new float[]{8, 8, 8, 8},
//				new Vector2f(100, -16),
//				new Vector2f(100, GameConsole.calculateHeight(rows) + 16),
//				DisplaySystem.getDisplaySystem().getWidth() - 200);


//        BasicCommandProcessor processor1 = new BasicCommandProcessor();
//        processor1.registerCommand(new TestConsole2());
//
//        JavaCommandProcessor processor2 = new JavaCommandProcessor(console);
//        processor2.register("console", console);
//        processor2.register("game", game);
//        processor2.register("state", debug);
//        processor2.importPackage("com.jme.math.Vector3f");
//        processor2.register("box", box);
//
//        console.registerCommandProcessor("command", processor1);
//        console.registerCommandProcessor("java", processor2);

        GameStateManager.getInstance().attachChild(console);
        console.setActive(true);
    }
}