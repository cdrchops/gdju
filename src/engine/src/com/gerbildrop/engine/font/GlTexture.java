package com.gerbildrop.engine.font;


import org.lwjgl.opengl.GL11;

/**
 * A texture to be bound within LWJGL. This object is responsible for keeping track of a given OpenGL texture and for
 * calculating the texturing mapping coordinates of the full image.
 * <p/>
 * Since textures need to be powers of 2 the actual texture may be considerably bigged that the source image and hence
 * the texture mapping coordinates need to be adjusted to matchup drawing the sprite against the texture.
 *
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class GlTexture {
    /** The GL target type */
    private int target;
    /** The GL texture ID */
    private int textureID;
    /** The height of the image */
    private int height;
    /** The width of the image */
    private int width;
    /** The width of the texture */
    private int texWidth;
    /** The height of the texture */
    private int texHeight;
    /** The ratio of the width of the image to the texture */
    private float widthRatio;
    /** The ratio of the height of the image to the texture */
    private float heightRatio;

    /**
     * Create a new texture
     *
     * @param _target int The GL target
     * @param _textureID int The GL texture ID
     */
    public GlTexture(int _target, int _textureID) {
        this.target = _target;
        this.textureID = _textureID;
    }

    /**
     * Bind the specified GL context to a texture
     */
    public void bind() {
        GL11.glBindTexture(target, textureID);
    }

    /**
     * Set the height of the image
     *
     * @param _height int The height of the image
     */
    public void setHeight(int _height) {
        this.height = _height;
        setHeight();
    }

    /**
     * Set the width of the image
     *
     * @param _width int The width of the image
     */
    public void setWidth(int _width) {
        this.width = _width;
        setWidth();
    }

    /**
     * Get the height of the original image
     *
     * @return The height of the original image
     */
    public int getImageHeight() {
        return height;
    }

    /**
     * Get the width of the original image
     *
     * @return The width of the original image
     */
    public int getImageWidth() {
        return width;
    }

    /**
     * Get the height of the physical texture
     *
     * @return The height of physical texture
     */
    public float getHeight() {
        return heightRatio;
    }

    /**
     * Get the width of the physical texture
     *
     * @return The width of physical texture
     */
    public float getWidth() {
        return widthRatio;
    }

    /**
     * Set the height of this texture
     *
     * @param _texHeight The height of the texture
     */
    public void setTextureHeight(int _texHeight) {
        this.texHeight = _texHeight;
        setHeight();
    }

    /**
     * Set the width of this texture
     *
     * @param _texWidth The width of the texture
     */
    public void setTextureWidth(int _texWidth) {
        this.texWidth = _texWidth;
        setWidth();
    }

    /** Set the height of the texture. This will update the ratio also. */
    private void setHeight() {
        if (texHeight != 0) {
            heightRatio = ((float) height) / texHeight;
        }
    }

    /** Set the width of the texture. This will update the ratio also. */
    private void setWidth() {
        if (texWidth != 0) {
            widthRatio = ((float) width) / texWidth;
        }

    }

    public int getTarget() {
        return target;
    }

    public void setTarget(final int _target) {
        target = _target;
    }

    public int getTextureID() {
        return textureID;
    }
}