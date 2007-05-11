package com.gerbildrop.mask.vision;

import com.jme.image.Texture;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class NightVision {
    boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void create(Node rootNode) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();

        Quad fullScreenQuad = new Quad("nightVision", display.getWidth(), display.getHeight());
        fullScreenQuad.getLocalRotation().set(0, 0, 0, 1);
        fullScreenQuad.getLocalTranslation().set(display.getWidth() / 2, display.getHeight() / 2, 0);
        fullScreenQuad.getLocalScale().set(1, 1, 1);
        fullScreenQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        fullScreenQuad.setCullMode(SceneElement.CULL_NEVER);
        fullScreenQuad.setTextureCombineMode(TextureState.REPLACE);
        fullScreenQuad.setLightCombineMode(LightState.OFF);

//either with a color like this:
        //fullScreenQuad.setSolidColor(new ColorRGBA(0.1f,1.0f,0.1f,1.0f));
//or with a texture like this:
        Texture tex = TextureManager.loadTexture(
                NightVision.class.getResource("/vision/nightvision7ri.png"),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        ts.setTexture(tex, 0);
        fullScreenQuad.setRenderState(ts);

        AlphaState alphaState = display.getRenderer().createAlphaState();
        alphaState.setBlendEnabled(true);
        alphaState.setSrcFunction(AlphaState.SB_DST_COLOR);
        alphaState.setDstFunction(AlphaState.DB_SRC_COLOR);
        alphaState.setTestEnabled(false);
        alphaState.setEnabled(true);
        fullScreenQuad.setRenderState(alphaState);

        fullScreenQuad.updateRenderState();
        rootNode.attachChild(fullScreenQuad);
    }
}
