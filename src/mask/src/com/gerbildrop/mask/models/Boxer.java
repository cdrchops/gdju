package com.gerbildrop.mask.models;

import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class Boxer {
    private boolean liftable = false;
    private Node staticBoxNode2 = new Node("boxy Node");
    private float x = 0;
    private float y = 0;
    private float z = 0;

    public void create(Node rootNode, Vector3f location, boolean liftable) {
        x = location.x;
        y = location.y;
        z = location.z;

        Box box2 = new Box("boxNode", new Vector3f(), .5f, .5f, .5f);
        box2.setLocalScale(.5f);
        box2.setModelBound(new BoundingBox());
        box2.updateModelBound();
        box2.setRenderQueueMode(Renderer.QUEUE_OPAQUE);

        rootNode.attachChild(staticBoxNode2);
        staticBoxNode2.attachChild(box2);
        staticBoxNode2.getLocalTranslation().y = 255;
        staticBoxNode2.updateWorldBound(); // We do this to allow the camera setup
        // access to the world bound in our
        // setup code.
        AlphaState alpha2;

        alpha2 = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        alpha2.setBlendEnabled(true);
        alpha2.setSrcFunction(4);
        alpha2.setDstFunction(1);
        alpha2.setEnabled(true);

        alpha2.setBlendEnabled(true);
        alpha2.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alpha2.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        TextureState ts3 = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        ts3.setEnabled(true);
        ts3.setTexture(TextureManager.loadTexture(
                Boxer.class.getResource("/jmetest/data/images/Monkey.tga"), Texture.MM_LINEAR,
                Texture.FM_LINEAR));
        staticBoxNode2.setRenderState(ts3);
        staticBoxNode2.setLocalTranslation(location);
        staticBoxNode2.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
    }

    public boolean isLiftable() {
        return liftable;
    }

    public void setLiftable(final boolean liftable) {
        this.liftable = liftable;
    }

    public Node getStaticBoxNode2() {
        return staticBoxNode2;
    }

    public void setStaticBoxNode2(final Node staticBoxNode2) {
        this.staticBoxNode2 = staticBoxNode2;
    }

    public float getX() {
        return x;
    }

    public void setX(final float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(final float z) {
        this.z = z;
    }
}