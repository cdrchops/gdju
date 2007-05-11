package com.gerbildrop.engine.input.mouse;

import com.jme.image.Texture;
import com.jme.input.AbsoluteMouse;
import com.jme.input.InputHandler;
import com.jme.input.Mouse;
import com.jme.math.Vector3f;
import com.jme.scene.SceneElement;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class MousePointer {
    AbsoluteMouse mousePointer;

    public void init(InputHandler input) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();
        mousePointer = new AbsoluteMouse("mouse.pointer", display.getWidth(), display.getHeight());
        TextureState mouseCursor = display.getRenderer().createTextureState();

        mouseCursor.setTexture(TextureManager.loadTexture(MousePointer.class.getResource("/dradis/styles/cursor.png"), Texture.MM_LINEAR, Texture.FM_LINEAR));
        mousePointer.setRenderState(mouseCursor);

        AlphaState alpha = display.getRenderer().createAlphaState();
        alpha.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alpha.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        alpha.setTestFunction(AlphaState.TF_GREATER);
        alpha.setBlendEnabled(true);
        mousePointer.setRenderState(alpha);
        mousePointer.setCullMode(SceneElement.CULL_NEVER);
        mousePointer.setLocalTranslation(new Vector3f(display.getWidth() / 2, display
                .getHeight() / 2, 0));
        // z order currently has no effect
        mousePointer.setZOrder(Integer.MAX_VALUE);
        mousePointer.updateRenderState();
        //        input.addAction(toggleMenu, "toggle menu", KeyInput.KEY_ESCAPE, false);
        mousePointer.registerWithInputHandler(input);
    }

    public Mouse getMouse() {
        return mousePointer;
    }
}
