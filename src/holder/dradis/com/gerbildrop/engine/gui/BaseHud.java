package com.gerbildrop.engine.gui;

import com.jme.scene.shape.Quad;
import com.jme.system.DisplaySystem;

public abstract class BaseHud {
    protected Quad hudQuad;
    protected static final DisplaySystem display = DisplaySystem.getDisplaySystem();

    protected int textureWidth;
    protected int textureHeight;

    protected float getUForPixel(int xPixel) {
        return (float) xPixel / textureWidth;
    }

    protected float getVForPixel(int yPixel) {
        return 1f - (float) yPixel / textureHeight;
    }

    public Quad getHudQuad() {
        return hudQuad;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(final int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(final int textureWidth) {
        this.textureWidth = textureWidth;
    }
}
