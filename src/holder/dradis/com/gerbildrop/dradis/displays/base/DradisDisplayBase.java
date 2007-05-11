package com.gerbildrop.dradis.displays.base;

import java.net.URL;
import java.nio.FloatBuffer;

import com.gerbildrop.engine.gui.BaseHud;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

public class DradisDisplayBase extends BaseHud {
    public void init(String quadName, URL imagePath, float scaleSize) {
        init(quadName, imagePath, scaleSize, 4);
    }

    public void init(String quadName, URL imagePath, float scaleSize, float divBy) {
        init(quadName, imagePath, scaleSize, divBy, null);
    }

    public void init(String quadName, URL imagePath, float scaleSize, float divBy, Vector3f vector) {
        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(imagePath, Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true));
        ts.setEnabled(true);

        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();

        hudQuad = new Quad(quadName, textureWidth, textureHeight);
        hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        hudQuad.setLightCombineMode(LightState.OFF);
        hudQuad.updateRenderState();

        if (vector != null) {
            hudQuad.setLocalTranslation(vector);
        } else {
            hudQuad.setLocalTranslation(new Vector3f(textureWidth / divBy, textureHeight / divBy, 0));
        }

        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        texCoords.put(getUForPixel(0)).put(getVForPixel(textureHeight));
        texCoords.put(getUForPixel(textureWidth)).put(getVForPixel(textureHeight));
        texCoords.put(getUForPixel(textureWidth)).put(getVForPixel(0));

        hudQuad.setTextureBuffer(0, texCoords);
        hudQuad.setRenderState(ts);

        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(false);
        as.setEnabled(true);

        hudQuad.setLocalScale(scaleSize);

        hudQuad.setRenderState(as);
    }
}
