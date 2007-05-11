package com.gerbildrop.gc.fonts;

import javax.media.opengl.GL;

/**
 * A texture to be bound within JOGL. This object is responsible for
 * keeping track of a given OpenGL texture and for calculating the
 * texturing mapping coordinates of the full image.
 *
 * Since textures need to be powers of 2 the actual texture may be
 * considerably bigged that the source image and hence the texture
 * mapping coordinates need to be adjusted to matchup drawing the
 * sprite against the texture.
 *
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class Texture {
    /** The GL target type */
    public int target;
    /** The GL texture ID */
    public int textureID;
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
    // The texture ratio against the running resolution
    private float textureratio;
    private GL gl;

    /**
     * Create a new texture
     *
     * @param target The GL target
     * @param textureID The GL texture ID
     */
    public Texture(GL gl, int target,int textureID,float whatratio) {
        this.gl = gl;
       this.textureratio = whatratio;
        this.target = target;
        this.textureID = textureID;
    }

    /**
     * Bind the specified GL context to a texture
     *
     * @param gl The GL context to bind to
     */
    public void bind() {
      gl.glBindTexture(target, textureID);
    }

    /**
     * Set the height of the image
     *
     * @param height The height of the image
     */
    public void setHeight(int height) {
        this.height = height;
        setHeight();
    }

    /**
     * Set the width of the image
     *
     * @param width The width of the image
     */
    public void setWidth(int width) {
        this.width = width;
        setWidth();
    }

    /**
     * Get the height of the original image
     *
     * @return The height of the original image
     */
    public int getImageHeight() {
        return (int)(height * textureratio);
    }

    /**
     * Get the width of the original image
     *
     * @return The width of the original image
     */
    public int getImageWidth() {
        return (int)(width * textureratio);
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
     * @param texHeight The height of the texture
     */
    public void setTextureHeight(int texHeight) {
        this.texHeight = texHeight;
        setHeight();
    }

    /**
     * Set the width of this texture
     *
     * @param texWidth The width of the texture
     */
    public void setTextureWidth(int texWidth) {
        this.texWidth = texWidth;
        setWidth();
    }

    /**
     * Set the height of the texture. This will update the
     * ratio also.
     */
    private void setHeight() {
        if (texHeight != 0) {
            heightRatio = ((float) height)/texHeight;
        }
    }

    /**
     * Set the width of the texture. This will update the
     * ratio also.
     */
    private void setWidth() {
        if (texWidth != 0) {
            widthRatio = ((float) width)/texWidth;
        }
    }
}