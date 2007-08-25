package com.gerbildrop.engine.spatial;

import com.jme.bounding.BoundingSphere;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class Sun extends Sphere {
    private static final long serialVersionUID = 1L;

    public Sun() {
        super("Sun", 50, 50, 150.0f);

        AlphaState as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        as.setEnabled(true);
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        setRenderState(as);

        MaterialState ms = DisplaySystem.getDisplaySystem().getRenderer().createMaterialState();
        ms.setEnabled(true);
        setRenderState(ms);

        TextureState ts1 = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        Texture t1 = TextureManager.loadTexture(getClass().getResource("/firetile1.jpg"), Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR);
        t1.setWrap(Texture.WM_WRAP_S_WRAP_T);
        t1.setTranslation(new Vector3f());
        ts1.setTexture(t1);
        setRenderState(ts1);

        setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);
        setModelBound(new BoundingSphere());
        updateModelBound();
        updateRenderState();
//        GameTaskQueueManager.getManager().update(new Callable<Object>() {
//            public Object call() throws Exception {
//                lock();
//                return null;
//            }
//        });
    }
}
