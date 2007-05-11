package com.gerbildrop.engine.spatial;

import java.util.LinkedList;
import java.util.List;

import com.gerbildrop.engine.spatial.base.TextureObject;

public class SequentialElement {

    /**
     * purpose to provide a base image, with cycling images... e.g. a progress bar with dynamic elements(like glowing
     * arrows) TextureAnimationController? -- kind of moves the animation but doesn't do what I want
     */

    private int _numberOfImages;
    private List<TextureObject> _textureObjectList = new LinkedList<TextureObject>();

    public void addTextureObject(TextureObject to) {
        _textureObjectList.add(to);
    }

    public int getNumberOfImages() {
        return _numberOfImages;
    }

    public void setNumberOfImages(final int numberOfImages) {
        _numberOfImages = numberOfImages;
    }

    public List<TextureObject> getTextureObjectList() {
        return _textureObjectList;
    }

    public void setTextureObjectList(final List<TextureObject> textureObjectList) {
        _textureObjectList = textureObjectList;
    }
}
