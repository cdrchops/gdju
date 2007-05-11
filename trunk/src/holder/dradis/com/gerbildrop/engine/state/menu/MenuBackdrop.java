package com.gerbildrop.engine.state.menu;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.skybox.SkyBoxFactory;
import com.jme.bounding.BoundingBox;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Skybox;

public class MenuBackdrop {
    private Skybox stars;
    private Quaternion q;

    private static final MenuBackdrop _INSTANCE = new MenuBackdrop();

    private MenuBackdrop() {}

    public static MenuBackdrop getInstance() {
        return _INSTANCE;
    }

    public void buildBackdrop(Node rootNode, Vector3f location) {
        // Configure Stars
        stars = SkyBoxFactory.createSkybox("Space", Resources.STARS_TEXTURE, 100.0f, true);
//		URL front = getClass().getResource("moon/mA_F.bmp");
//		URL back = getClass().getResource("moon/mA_B.bmp");
//		URL up = getClass().getResource("moon/mA_U.bmp");
//		URL down = getClass().getResource("moon/mA_D.bmp");
//		URL left = getClass().getResource("moon/mA_L.bmp");
//		URL right = getClass().getResource("moon/mA_R.bmp");
//		URL front = getClass().getResource("green/wwB_F.bmp");
//		URL back = getClass().getResource("green/wwB_B.bmp");
//		URL up = getClass().getResource("green/wwB_U.bmp");
//		URL down = getClass().getResource("green/wwB_D.bmp");
//		URL left = getClass().getResource("green/wwB_L.bmp");
//		URL right = getClass().getResource("green/wwB_R.bmp");
//		stars = Utility.createSkybox("Space", up, down, front, back, left, right, 200.0f, true);
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
        // Rotate skybox
        if (stars != null) {
            if (q == null) q = new Quaternion();
            q.fromAngleAxis(FastMath.PI * (tpf * 0.015f), new Vector3f(0.0f, 1.0f, 0.0f));
            stars.getLocalRotation().multLocal(q);
            //game.getCamera().getDirection().multLocal(new Vector3f(0.0f, FastMath.PI * (tpf * 0.1f), 0.0f));
        }
    }
}