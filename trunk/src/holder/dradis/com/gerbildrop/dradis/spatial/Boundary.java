package com.gerbildrop.dradis.spatial;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class Boundary extends Node {
    private static final long serialVersionUID = 1L;

    public Boundary() {
        super("Boundary");
        Sphere boundary = new Sphere("BoundarySphere", 50, 50, 1000.0f);
        attachChild(boundary);

        AlphaState as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        as.setEnabled(true);
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        boundary.setRenderState(as);

        MaterialState ms = DisplaySystem.getDisplaySystem().getRenderer().createMaterialState();
        ms.setEnabled(true);
        ms.setDiffuse(new ColorRGBA(0.0f, 0.0f, 1.0f, 0.50f));
        boundary.setRenderState(ms);

        TextureState ts1 = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        Texture t1 = TextureManager.loadTexture(getClass().getResource("/water20.gif"), Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR);
        t1.setWrap(Texture.WM_WRAP_S_WRAP_T);
        t1.setTranslation(new Vector3f());
        ts1.setTexture(t1);
        boundary.setRenderState(ts1);

        boundary.setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);

        boundary.updateRenderState();
    }
}
