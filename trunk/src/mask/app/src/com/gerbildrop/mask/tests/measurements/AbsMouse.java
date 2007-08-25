package com.gerbildrop.mask.tests.measurements;

import java.net.URL;

import com.jme.image.Texture;
import com.jme.input.AbsoluteMouse;
import com.jme.input.InputHandler;
import com.jme.input.MouseInput;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Text;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class AbsMouse {
    AbsoluteMouse am;
    Picker picker = new Picker();
    boolean mouseDown = false;

    public void initMouse(Node rootNode, InputHandler input) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();
        am = new AbsoluteMouse("The Mouse", display.getWidth(), display
                .getHeight());

        // Get a picture for my mouse.
        TextureState ts = display.getRenderer().createTextureState();
        URL cursorLoc;
        cursorLoc = PickExample.class.getClassLoader().getResource(
                "/jmetest/data/cursor/cursor1.png");
        Texture t = TextureManager.loadTexture(cursorLoc, Texture.MM_LINEAR,
                                               Texture.FM_LINEAR);
        ts.setTexture(t);
        am.setRenderState(ts);

        // Make the mouse's background blend with what's already there
        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(true);
        as.setTestFunction(AlphaState.TF_GREATER);
        am.setRenderState(as);

        // Get the mouse input device and assign it to the AbsoluteMouse
        // Move the mouse to the middle of the screen to start with
        am.setLocalTranslation(new Vector3f(display.getWidth() / 2, display
                .getHeight() / 2, 0));
        // Assign the mouse to an input handler
        am.registerWithInputHandler(input);
        rootNode.attachChild(am);
    }

    public AbsoluteMouse getAm() {
        return am;
    }

    public void update(Node rootNode, Text textBlocks) {

        // Get the mouse input device from the jME mouse
        // Is button 0 down? Button 0 is left click
        if (MouseInput.get().isButtonDown(0)) {
            if (!mouseDown) {
                mouseDown = true;
                DisplaySystem display = DisplaySystem.getDisplaySystem();
                picker.doMousePick(this, display, rootNode, textBlocks);
            }
        } else {
            mouseDown = false;
        }
    }
}
