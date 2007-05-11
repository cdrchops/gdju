/**
 *
 */
package com.gerbildrop.engine.state;

import java.util.concurrent.Callable;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.skybox.SkyBoxFactory;
import com.gerbildrop.engine.skybox.StarDust;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.jme.bounding.BoundingBox;
import com.jme.light.PointLight;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.SceneElement;
import com.jme.scene.Skybox;
import com.jme.scene.state.LightState;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueueManager;
import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameStateNode;
import com.jmex.game.state.load.Loader;

/** @author Matthew D. Hicks */
public abstract class SpaceGameState extends BasicGameStateNode {
    private static SpaceGameState instance;

    protected StandardGame _game;

    private StarDust sd = new StarDust();
    private Skybox stars;


    public SpaceGameState(StandardGame game, Loader loader) {
        super("Space Game");
        this._game = game;
        initSpaceGameState(loader);
        init(loader);
        instance = this;
    }

    protected abstract void init(Loader loader);

    public abstract void setControllersActive(boolean _active);

    private void initSpaceGameState(Loader loader) {
        loader.setProgress(0.1f, "Initializing Space");
        initSpace(loader);
    }

    private void initSpace(Loader loader) {
        // Configure Stars
        stars = SkyBoxFactory.createSkybox("Space", Resources.STARS_TEXTURE, 50.0f, true);
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                stars.lockBounds();
                stars.lockMeshes();
                stars.lockShadows();
                return null;
            }
        });

        stars.setModelBound(new BoundingBox());
        stars.updateModelBound();
        stars.setCullMode(SceneElement.CULL_NEVER);
        sd.init(rootNode);

        loader.setProgress(0.15f);
        // Setup lighting for space
        PointLight light = new PointLight();
        light.setDiffuse(new ColorRGBA(0.75f, 0.75f, 0.75f, 0.75f));
        light.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
        light.setLocation(new Vector3f(100.0f, 100.0f, 100.0f));
        light.setEnabled(true);

        loader.setProgress(0.2f);
        LightState lightState = DisplaySystem.getDisplaySystem().getRenderer().createLightState();
        lightState.setEnabled(true);
        lightState.detachAll();
        lightState.setGlobalAmbient(new ColorRGBA(0.6f, 0.6f, 0.6f, 1.0f));
        lightState.attach(light);
        rootNode.setRenderState(lightState);
    }

    public abstract void setHUDActive(boolean _active);

    public static SpaceGameState getInstance() {
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void update(float tpf, BaseModelObject bmo) {
        super.update(tpf);
        sd.update(bmo.getLocalTranslation());
        stars.setLocalTranslation(bmo.getLocalTranslation());
        stars.updateGeometricState(tpf, true);
    }

    public void render(float tpf) {
        super.render(tpf);
        DisplaySystem.getDisplaySystem().getRenderer().draw(stars);
    }

    public abstract void setShipControllersActive(boolean _active);

}
