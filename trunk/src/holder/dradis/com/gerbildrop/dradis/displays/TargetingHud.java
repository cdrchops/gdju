package com.gerbildrop.dradis.displays;

import java.nio.FloatBuffer;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.gui.BaseHud;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

public class TargetingHud extends BaseHud {
    public void init() {
        hudQuad = new Quad("hud", 40.0f, 40.0f);

        hudQuad.setLocalTranslation(new Vector3f(display.getWidth() / 2.0f, display.getHeight() / 2.0f, 0.0f));
        hudQuad.updateRenderState();

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(Resources.TARGETING_HUD,
                                                 Texture.MM_LINEAR,
                                                 Texture.FM_LINEAR,
                                                 1.0f,
                                                 true));
        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();
        ts.setEnabled(true);

        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        texCoords.put(getUForPixel(0)).put(getVForPixel(40));
        texCoords.put(getUForPixel(40)).put(getVForPixel(40));
        texCoords.put(getUForPixel(40)).put(getVForPixel(0));
        hudQuad.setTextureBuffer(0, texCoords);
        hudQuad.setRenderState(ts);

        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);

        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(false);
        as.setEnabled(true);

        hudQuad.setRenderState(as);
    }
}
