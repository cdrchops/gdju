package com.jme.test.loading;

import java.net.URL;
import java.nio.FloatBuffer;

import com.gerbildrop.dradis.ships.basic.BaseObject;
import com.jme.image.Texture;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

//todo: fix so it works with myloadinggame state

/**
 * @author vivaldi
 * @version $Id: TextureDefinition.java,v 1.3 2007/04/04 14:30:55 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class TextureDefinition extends BaseObject {
    private FloatBuffer textureCoords;
    private TextureState textureState;

    private float xLoc;
    private float yLoc;

    public TextureDefinition(URL imagePath) {
        this(imagePath, -1, -1);
    }

    public TextureDefinition(URL imagePath, float xLoc, float yLoc) {
        load(imagePath, xLoc, yLoc);
    }

    public void load(URL imagePath, float xLoc, float yLoc) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();
        textureState = display.getRenderer().createTextureState();
//        textureState.setTexture(TextureManager.loadTexture(imagePath, Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true));
        Texture tex = TextureManager.loadTexture(imagePath, Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true);
        textureState.setTexture(tex);
        textureWidth = textureState.getTexture().getImage().getWidth();
        textureHeight = textureState.getTexture().getImage().getHeight();
        textureState.setEnabled(true);

        if (xLoc > -1) {
            this.xLoc = xLoc;
        } else {
            this.xLoc = textureWidth;
        }

        if (yLoc > -1) {
            this.yLoc = yLoc;
        } else {
            this.yLoc = textureHeight;
        }

        textureCoords = BufferUtils.createVector2Buffer(4);
        textureCoords.put(getUForPixel(0)).put(getVForPixel(0));
        textureCoords.put(getUForPixel(0)).put(getVForPixel(textureHeight));
        textureCoords.put(getUForPixel(textureWidth)).put(getVForPixel(textureHeight));
        textureCoords.put(getUForPixel(textureWidth)).put(getVForPixel(0));
    }

//    public void updateDynamicTextureCoords(float relCoord) {
//        textureCoords = BufferUtils.createVector2Buffer(4);
//        textureCoords.put(getUForPixel(relCoord)).put(getVForPixel(xLoc));
//        textureCoords.put(getUForPixel(relCoord)).put(getVForPixel(yLoc));
//        textureCoords.put(getUForPixel(relCoord + textureWidth)).put(getVForPixel(yLoc));
//        textureCoords.put(getUForPixel(relCoord + textureWidth)).put(getVForPixel(xLoc));
//    }

    protected float getUForPixel(float xPixel) {
        return xPixel / textureWidth;
    }

    protected float getVForPixel(float yPixel) {
        return 1f - yPixel / textureHeight;
    }

    public FloatBuffer getTextureCoords() {
        return textureCoords;
    }

    public void setTextureCoords(FloatBuffer textureCoords) {
        this.textureCoords = textureCoords;
    }

    public TextureState getTextureState() {
        return textureState;
    }

    public void setTextureState(TextureState textureState) {
        this.textureState = textureState;
    }

    public float getXLoc() {
        return xLoc;
    }

    public void setXLoc(float xLoc) {
        this.xLoc = xLoc;
    }

    public float getYLoc() {
        return yLoc;
    }

    public void setYLoc(float yLoc) {
        this.yLoc = yLoc;
    }
}