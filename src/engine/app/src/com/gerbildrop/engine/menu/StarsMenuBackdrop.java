package com.gerbildrop.engine.menu;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.skybox.SkyBoxFactory;
import com.jme.bounding.BoundingBox;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Skybox;

public class StarsMenuBackdrop implements IBackdrop {
    private Skybox stars;
    private Quaternion q;

    private static final StarsMenuBackdrop _INSTANCE = new StarsMenuBackdrop();

    private StarsMenuBackdrop() {}

    public static StarsMenuBackdrop getInstance() {
        return _INSTANCE;
    }

    public void buildBackdrop(Node rootNode, Vector3f location) {
        // Configure Stars
        stars = SkyBoxFactory.createSkybox("SpaceMenuSkybox", Resources.STARS_TEXTURE, 100.0f, true);
        stars.setLocalTranslation(location);
        stars.lockBounds();
        stars.lockMeshes();
        stars.lockShadows();
        stars.setModelBound(new BoundingBox());
        stars.updateModelBound();
        stars.updateRenderState();

        rootNode.attachChild(stars);
    }

    public void update(float tpf) {
        SkyBoxFactory.rotate(stars, q == null ? q = new Quaternion() : q, tpf);
    }
}