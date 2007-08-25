package com.gerbildrop.mask;

import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class MaskTerrain {
    public void createTerrain(Node rootNode) {
        // Create a Quad.
        Quad q = new Quad("TerrainQuad", 200, 200);
        q.setModelBound(new BoundingBox());
        q.updateModelBound();
        q.setLocalRotation(new Quaternion(new float[]{90 * FastMath.DEG_TO_RAD, 0, 0}));

        // Apply a texture to it.
        TextureState ts =
                DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        Texture texture =
                TextureManager.loadTexture(
                        BasicLevelGame.class.getResource(
                                "/jmetest/data/texture/dirt.jpg"),
                        Texture.MM_LINEAR_LINEAR,
                        Texture.FM_LINEAR);
        texture.setWrap(Texture.WM_WRAP_S_WRAP_T);
        ts.setTexture(texture);
        ts.setEnabled(true);
        q.setRenderState(ts);

        // Add it to the scene.
        rootNode.attachChild(q);

        // Remember to update the rootNode before you get going.
        rootNode.updateGeometricState(0, true);
        rootNode.updateRenderState();
    }
}
