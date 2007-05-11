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
//several ways to do it offcourse...
//sucky/hard ways:
//- changing light color parameters and/or materialsettings
//- rendering to a texture and using a shader to manipulate the colors when drawing the texture as a fullscreen quad
//
//the fastest way to achieve something like that i can come up right away is:
//
//draw a fullscreen quad(after everything else is drawn) with blending enabled and blendsettings like this:
//   alphaState.setSrcFunction( AlphaState.SB_DST_COLOR );
//   alphaState.setDstFunction( AlphaState.DB_SRC_COLOR );
//
//this way, the rendered colors will multiply with the fullscreenquad color...
//

//on the fullscreenquad you either set it's color(setSolidColor) to a green color, or even better, set a green texture with some noise in it to get a more lifelike effect(or even fade it out to black in the edges to simulate a monocular)...
public class InfraRedVision {
    public void create(Node rootNode) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();

        Quad fullScreenQuad = new Quad("irVision", display.getWidth(), display.getHeight());
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
                InfraRedVision.class.getResource("/vision/infrared.png"),
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
