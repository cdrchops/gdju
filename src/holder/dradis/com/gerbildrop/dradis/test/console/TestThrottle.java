package com.gerbildrop.dradis.test.console;

import com.captiveimagination.game.control.physics.PhysicsThrustController;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.input.KeyInput;
import com.jme.input.controls.GameControl;
import com.jme.input.controls.GameControlManager;
import com.jme.input.controls.binding.KeyboardBinding;
import com.jme.input.controls.controller.Axis;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Box;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jmetest.physics.TestStressPhysics;
import com.jmex.game.StandardGame;
import com.jmex.game.state.DebugGameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.physics.DynamicPhysicsNode;
import com.jmex.physics.util.states.PhysicsGameState;

public class TestThrottle {
    public static void main(String[] args) {
        StandardGame game = new StandardGame("Test Throttle");
        game.getSettings().setVerticalSync(false);        // We want to see what FPS we're running at
        game.start();

        // Create a DebugGameState to give us the toys we want
        DebugGameState debug = new DebugGameState();
        debug.setActive(true);
        GameStateManager.getInstance().attachChild(debug);

        // Create our GameState
        PhysicsGameState physics = new PhysicsGameState("PhysicsState");
//		physics.getPhysicsSpace().setAutoDisableThreshold(0.5f);
//		physics.getPhysicsSpace().set
        GameStateManager.getInstance().attachChild(physics);
        physics.getRootNode().setCullMode(Spatial.CULL_NEVER);
        physics.setActive(true);

        // Create our Boxes
        TextureState ts = game.getDisplay().getRenderer().createTextureState();
        Texture t = TextureManager.loadTexture(TestStressPhysics.class.getClassLoader().getResource("com/jmetest/physics/resources/crate.png"), Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR);
        t.setWrap(Texture.WM_WRAP_S_WRAP_T);
        ts.setTexture(t);
        game.lock();
        Box box1 = new Box("Box", new Vector3f(), 5.0f, 5.0f, 5.0f);
        box1.setModelBound(new BoundingBox());
        box1.updateModelBound();
        box1.setRenderState(ts);
        DynamicPhysicsNode node1 = physics.getPhysicsSpace().createDynamicNode();
        node1.setAffectedByGravity(false);
        node1.getLocalTranslation().set(-20.0f, 20.0f, -150.0f);
        node1.attachChild(box1);
        node1.generatePhysicsGeometry();
        node1.updateGeometricState(0.0f, true);
        physics.getRootNode().attachChild(node1);

        physics.getRootNode().updateRenderState();

        // Create Friction on the first box
//		FrictionCallback callback = new FrictionCallback();
//		callback.add(node1, 0.5f, 0.5f);
//		physics.getPhysicsSpace().addToUpdateCallbacks(callback);

        GameControlManager manager = new GameControlManager();
        GameControl forward = manager.addControl("Forward");
        forward.addBinding(new KeyboardBinding(KeyInput.KEY_W));
        GameControl reverse = manager.addControl("Reverse");
        reverse.addBinding(new KeyboardBinding(KeyInput.KEY_S));
        GameControl afterburner = manager.addControl("Afterburner");
        afterburner.addBinding(new KeyboardBinding(KeyInput.KEY_TAB));
        final PhysicsThrustController controller = new PhysicsThrustController(node1, Axis.Z, forward, reverse, 0.3f, 50.0f, 2500.0f, 100.0f);
        controller.setAfterburner(afterburner, 5.0f, 2.0f);
        //ThrottleController controller = new ThrottleController(forward, reverse, 0.3f);
        physics.getRootNode().addController(controller);

        game.unlock();

        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("Speed: " + controller.getSpeed() + ", Afterburner: " + controller.getAfterburner());
                        Thread.sleep(500);
                    } catch (Exception exc) {
                    }
                }
            }
        };
        thread.start();
    }
}
