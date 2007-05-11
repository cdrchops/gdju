package com.gerbildrop.engine.spatial.base;

import java.net.URL;

public class TextureObject {
    protected int textureWidth;
    protected int textureHeight;
    protected URL _texturePath = null;

    public URL getTexturePath() {
        return _texturePath;
    }

    public void setTexturePath(final URL texturePath) {
        _texturePath = texturePath;
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

    public float getUForPixel(int xPixel) {
        return (float) xPixel / textureWidth;
    }

    public float getVForPixel(int yPixel) {
        return 1f - (float) yPixel / textureHeight;
    }
}
