package com.gerbildrop.dradis.state.game;

import com.gerbildrop.dradis.displays.TargetingHud;
import com.gerbildrop.engine.util.TextFactory;
import com.gerbildrop.engine.util.TextNode;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.SceneElement;
import com.jme.scene.state.LightState;
import com.jme.system.DisplaySystem;
import com.jmex.game.state.BasicGameState;

/**
 * Displays HUD information for the ship
 *
 * @author Matthew D. Hicks
 */
public class HUDGameState extends BasicGameState {
    private TextNode speedText;

    public HUDGameState() {
        super("HUDGameState");

        init();
    }

    private void init() {
        final DisplaySystem display = DisplaySystem.getDisplaySystem();
        // Display everything in ortho
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.setCullMode(SceneElement.CULL_NEVER);

        // Cross-hairs
        final TargetingHud tg = new TargetingHud();
        tg.init();
        rootNode.attachChild(tg.getHudQuad());

        speedText = TextFactory.createText("SpeedText", "0");
        rootNode.attachChild(speedText);
        speedText.setLocalTranslation(new Vector3f((display.getWidth() / 2.0f) - 150.0f, (display.getHeight() / 2.0f) - 7.0f, 0.0f));

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    public void setSpeed(int speed) {
        speedText.setText(String.valueOf(speed));
    }
}
