package com.gerbildrop.engine.state;

import java.util.concurrent.Callable;

import com.gerbildrop.engine.input.actions.FireAction;
import com.gerbildrop.engine.input.actions.MouseAction;
import com.gerbildrop.engine.input.config.InputConstants;
import com.gerbildrop.engine.input.controls.GameControls;
import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.skybox.SkyBoxFactory;
import com.gerbildrop.engine.skybox.StarDust;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.jme.bounding.BoundingBox;
import com.jme.input.controls.GameControlManager;
import com.jme.input.controls.controller.ActionController;
import com.jme.input.controls.controller.ActionRepeatController;
import com.jme.input.controls.controller.Axis;
import com.jme.input.controls.controller.CameraController;
import com.jme.input.controls.controller.RotationController;
import com.jme.input.controls.controller.ThrottleController;
import com.jme.input.controls.controller.camera.FixedCameraPerspective;
import com.jme.light.PointLight;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.SceneElement;
import com.jme.scene.Skybox;
import com.jme.scene.state.LightState;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.TextureManager;
import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameStateNode;
import com.jmex.game.state.load.Loader;
import com.jmex.sound.openAL.SoundSystem;

public abstract class SpaceGameState extends BasicGameStateNode implements InputConstants, IPlayableGameState {
    private static SpaceGameState instance;

    protected StandardGame _game;

    private StarDust sd = new StarDust();
    private Skybox stars;

    protected ThrottleController throttleController;
    protected RotationController pitchForwardReverseController;
    protected RotationController pitchLeftRightController;
    protected RotationController rollController;
    protected ThrottleController strafeController;
    protected ActionController mouseController;
    protected CameraController cameraController;

    public SpaceGameState(StandardGame game, Loader loader) {
        super("Space Game");

        this._game = game;

        initSpaceGameState(loader);

        init(loader);

        initSound();

        instance = this;
    }

    private void init(Loader loader) {
        loader.setProgress(0.80f, "Loading Textures");

        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                TextureManager.preloadCache(DisplaySystem.getDisplaySystem().getRenderer());
                return null;
            }
        });

        loader.setProgress(0.4f, "Loading Models");
        initModels(loader);

        loader.setProgress(0.5f, "Loading HUD");
        initHUD();

        loader.setProgress(0.9f, "Configuring Control System");
        initControlSystem(loader);

        rootNode.updateRenderState();
        rootNode.updateWorldBound();
        loader.setProgress(1.0f, "Loaded Successfully");
    }

    private void initSpaceGameState(Loader loader) {
        loader.setProgress(0.1f, "Initializing Space");
        initSpace(loader);
    }

    private void initControlSystem(Loader loader) {
        GameControlManager manager = GameControls.getInstance().getControlManager();

        BaseModelObject ship = getShipToControl();

        // Throttle
        throttleController = new ThrottleController(ship,
                                                    manager.getControl(FORWARD),
                                                    1.5f,
                                                    manager.getControl(BACKWARD),
                                                    -0.5f,
                                                    0.01f,
                                                    0.7f,
                                                    1.0f,
                                                    false,
                                                    Axis.Z);

        rootNode.addController(throttleController);
        loader.setProgress(0.825f);

        // Pitch Forward and Reverse
        pitchForwardReverseController = new RotationController(ship, manager.getControl(PITCH_FORWARD), manager.getControl(PITCH_BACKWARD), 0.5f, Axis.X);
        rootNode.addController(pitchForwardReverseController);
        loader.setProgress(0.85f);

        // Pitch Left and Right
        pitchLeftRightController = new RotationController(ship, manager.getControl(PITCH_LEFT), manager.getControl(PITCH_RIGHT), 0.5f, Axis.Y);
        rootNode.addController(pitchLeftRightController);
        loader.setProgress(0.875f);

        // Rolling
        rollController = new RotationController(ship, manager.getControl(ROLL_RIGHT), manager.getControl(ROLL_LEFT), 0.5f, Axis.Z);
        rootNode.addController(rollController);
        loader.setProgress(0.9f);

        // Strafing
        strafeController = new ThrottleController(ship, manager.getControl(STRAFE_LEFT), 10.0f, manager.getControl(STRAFE_RIGHT), -10.0f, 0.01f, 0.5f, 1.0f, true, Axis.X);
        rootNode.addController(strafeController);
        loader.setProgress(0.925f);

        initStateControllers(loader, manager);

        // Mouse Cursor Visibility
        mouseController = new ActionController(manager.getControl(SHOW_MOUSE), new MouseAction(this));
        rootNode.addController(mouseController);
        loader.setProgress(0.95f);

        final ActionRepeatController fireController = new ActionRepeatController(manager.getControl(PRIMARY_WEAPON), 100, new FireAction(ship));
        rootNode.addController(fireController);

        // Configure Camera
        cameraController = new CameraController(ship, _game.getCamera(), manager.getControl(CAMERA_PERSPECTIVE));

        //FixedCameraPerspective abovePerspective = new FixedCameraPerspective(new Vector3f(0.0f, 5.0f, -10.0f));
        //cameraController.addPerspective(abovePerspective);
        FixedCameraPerspective cockpitPerspective = new FixedCameraPerspective(new Vector3f(0.0f, 0.0f, -0.01f), true);
        cameraController.addPerspective(cockpitPerspective);

        FixedCameraPerspective followingPerspective = new FixedCameraPerspective(new Vector3f(0.0f, 0.0f, -15.0f));
        cameraController.addPerspective(followingPerspective);

        FixedCameraPerspective leftSidePerspective = new FixedCameraPerspective(new Vector3f(15.0f, 0.0f, 0.0f));
        cameraController.addPerspective(leftSidePerspective);

        FixedCameraPerspective rightSidePerspective = new FixedCameraPerspective(new Vector3f(-15.0f, 0.0f, 0.0f));
        cameraController.addPerspective(rightSidePerspective);

        FixedCameraPerspective frontPerspective = new FixedCameraPerspective(new Vector3f(0.0f, 0.0f, 15.0f));
        cameraController.addPerspective(frontPerspective);

        rootNode.addController(cameraController);

        loader.setProgress(0.975f);
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

    private void initSound() {
        SoundSystem.init(DisplaySystem.getDisplaySystem().getRenderer().getCamera(), SoundSystem.OUTPUT_DEFAULT);
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

    public void setShipControllersActive(boolean _active) {
        throttleController.setActive(_active);
        pitchForwardReverseController.setActive(_active);
        pitchLeftRightController.setActive(_active);
        rollController.setActive(_active);
        strafeController.setActive(_active);
        cameraController.setActive(_active);
        mouseController.setActive(!_active);
    }

    public abstract void setActivity(String className, boolean _active);

    public abstract BaseModelObject getShipToControl();

    public abstract void initStateControllers(Loader loader, GameControlManager manager);

    public abstract void setControllersActive(boolean _active);

    public abstract void initModels(Loader loader);

    public abstract void initHUD();
}
