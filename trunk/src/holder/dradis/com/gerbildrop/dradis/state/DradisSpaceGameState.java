package com.gerbildrop.dradis.state;

import java.util.concurrent.Callable;

import com.gerbildrop.dradis.displays.stateDisplays.LSODisplay;
import com.gerbildrop.dradis.displays.stateDisplays.WaterDisplay;
import com.gerbildrop.dradis.displays.stateDisplays.DradisDisplay;
import com.gerbildrop.dradis.displays.stateDisplays.ClockDisplay;
import com.gerbildrop.dradis.displays.stateDisplays.CountdownDisplay;
import com.gerbildrop.dradis.input.DradisInputConstants;
import com.gerbildrop.dradis.input.actions.ClockAction;
import com.gerbildrop.dradis.input.actions.CountdownAction;
import com.gerbildrop.dradis.input.actions.DradisAction;
import com.gerbildrop.dradis.input.actions.LsoAction;
import com.gerbildrop.dradis.input.actions.WaterAction;
import com.gerbildrop.dradis.spatial.ships.Battlestar;
import com.gerbildrop.dradis.spatial.ships.Viper2;
import com.gerbildrop.dradis.state.controller.HUDController;
import com.gerbildrop.dradis.state.game.HUDGameState;
import com.gerbildrop.engine.state.SimpleGameStateNode;
import com.gerbildrop.engine.input.actions.FireAction;
import com.gerbildrop.engine.input.actions.MouseAction;
import com.gerbildrop.engine.input.controls.GameControls;
import com.gerbildrop.engine.state.SpaceGameState;
import com.jme.input.controls.GameControlManager;
import com.jme.input.controls.controller.ActionController;
import com.jme.input.controls.controller.ActionRepeatController;
import com.jme.input.controls.controller.Axis;
import com.jme.input.controls.controller.CameraController;
import com.jme.input.controls.controller.RotationController;
import com.jme.input.controls.controller.ThrottleController;
import com.jme.input.controls.controller.camera.FixedCameraPerspective;
import com.jme.math.Vector3f;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.TextureManager;
import com.jmex.game.StandardGame;
import com.jmex.game.state.load.Loader;
import com.jmex.sound.openAL.SoundSystem;

public class DradisSpaceGameState extends SpaceGameState implements DradisInputConstants {

    private Viper2 ship;
    private HUDGameState hudGameState;

    private ThrottleController throttleController;
    private RotationController pitchForwardReverseController;
    private RotationController pitchLeftRightController;
    private RotationController rollController;
    private ThrottleController strafeController;
    private ActionController mouseController;
    private CameraController cameraController;
    private HUDController hudController;

    private SimpleGameStateNode clockGameState;
    private ActionController clockController;

    private SimpleGameStateNode countdownGameState;
    private ActionController countdownController;

    private SimpleGameStateNode dradisGameState;
    private ActionController dradisController;

    private SimpleGameStateNode lsoGameState;
    private ActionController lsoController;

    private SimpleGameStateNode waterGameState;
    private ActionController waterController;



    public DradisSpaceGameState(StandardGame game, Loader loader) {
        super(game, loader);
    }

    protected void init(Loader loader) {
        loader.setProgress(0.4f, "Loading Models");
        initModels(loader);

        loader.setProgress(0.5f, "Loading HUD");
        initHUD();

        loader.setProgress(0.6f, "Loading Clock");
        initClock();

        loader.setProgress(0.65f, "Loading Dradis");
        initDradis();

        loader.setProgress(0.70f, "Loading LSO");
        initLso();

        loader.setProgress(0.75f, "Loading Countdown");
        initCountdown();

        loader.setProgress(0.77f, "Loading Water");
        initWater();

        loader.setProgress(0.80f, "Loading Textures");
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                TextureManager.preloadCache(DisplaySystem.getDisplaySystem().getRenderer());
                return null;
            }
        });

        loader.setProgress(0.9f, "Configuring Control System");
        initControlSystem(loader);

//        LoadingManager.getInstance().addLoader(new LoadingObject("gameStart", null, new DradisResourceLoader.DradisResourceLoaderThread(), clockGameState));
//
//        URL url = DradisSpaceGameState.class.getResource("/dradis/displays/loading/loadBackground.png");
//        URL url2 = DradisSpaceGameState.class.getResource("/dradis/displays/loading/loadingComplete.png");
//        LoadingManager.getInstance().runLoader("gameStart", url, url2);


        rootNode.updateRenderState();
        rootNode.updateWorldBound();
        loader.setProgress(1.0f, "Loaded Successfully");
    }

    private void initHUD() {
        hudGameState = new HUDGameState();
        attachChild(hudGameState);
        hudGameState.setActive(true);
    }

    private void initClock() {
        clockGameState = new SimpleGameStateNode("clockGameState", _game, new ClockDisplay());
        attachChild(clockGameState);
        clockGameState.setActive(false);
    }

    private void initCountdown() {
        countdownGameState = new SimpleGameStateNode("countdownGameState", _game, new CountdownDisplay());
        attachChild(countdownGameState);
        countdownGameState.setActive(false);
    }

    private void initDradis() {
        dradisGameState = new SimpleGameStateNode("dradisGameState", _game, new DradisDisplay());

        attachChild(dradisGameState);
        dradisGameState.setActive(false);
    }

    private void initLso() {
        lsoGameState = new SimpleGameStateNode("waterGameState", _game, new LSODisplay());
        attachChild(lsoGameState);
        lsoGameState.setActive(false);
    }

    private void initWater() {
        waterGameState = new SimpleGameStateNode("waterGameState", _game, new WaterDisplay());
        attachChild(waterGameState);
        waterGameState.setActive(false);
    }

    private void initModels(Loader loader) {
        SoundSystem.init(DisplaySystem.getDisplaySystem().getRenderer().getCamera(), SoundSystem.OUTPUT_DEFAULT);

//        final Sun sun = new Sun();
//        sun.setLocalTranslation(new Vector3f(100.0f, 100.0f, -100.0f));
//        rootNode.attachChild(sun);
//        loader.setProgress(0.5f);

        ship = new Viper2(rootNode, "viper2");
        ship.setIsCollidable(true);
        rootNode.attachChild(ship);
        loader.setProgress(0.6f);

        Battlestar ship2 = new Battlestar(rootNode, "galactica");
        ship2.setLocalTranslation(new Vector3f(50.0f, 0.0f, 15.0f));

        Viper2 ship3 = new Viper2(rootNode, "viper2a");
        ship2.setLocalTranslation(new Vector3f(50.0f, 0.0f, -15.0f));
        rootNode.attachChild(ship3);
        rootNode.attachChild(ship2);
        loader.setProgress(0.7f);
    }

    private void initControlSystem(Loader loader) {
        GameControlManager manager = GameControls.getInstance().getControlManager();

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

        // HUD Monitor
        hudController = new HUDController(hudGameState, throttleController, 100.0f);
        rootNode.addController(hudController);

        // Mouse Cursor Visibility
        mouseController = new ActionController(manager.getControl(SHOW_MOUSE), new MouseAction(this));
        rootNode.addController(mouseController);
        loader.setProgress(0.95f);

        clockController = new ActionController(manager.getControl(SHOW_CLOCK), new ClockAction(this));
        rootNode.addController(clockController);
        loader.setProgress(0.95f);

        countdownController = new ActionController(manager.getControl(SHOW_COUNTDOWN), new CountdownAction(this));
        rootNode.addController(countdownController);
        loader.setProgress(0.95f);

        dradisController = new ActionController(manager.getControl(SHOW_DRADIS), new DradisAction(this));
        rootNode.addController(dradisController);
        loader.setProgress(0.95f);

        lsoController = new ActionController(manager.getControl(SHOW_LSO), new LsoAction(this));
        rootNode.addController(lsoController);
        loader.setProgress(0.95f);

        waterController = new ActionController(manager.getControl(SHOW_WATER), new WaterAction(this));
        rootNode.addController(waterController);
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
        rootNode.addController(cameraController);
        FixedCameraPerspective frontPerspective = new FixedCameraPerspective(new Vector3f(0.0f, 0.0f, 15.0f));
        cameraController.addPerspective(frontPerspective);
        loader.setProgress(0.975f);
    }

    public void setShipControllersActive(boolean _active) {
        throttleController.setActive(_active);
        pitchForwardReverseController.setActive(_active);
        pitchLeftRightController.setActive(_active);
        rollController.setActive(_active);
        strafeController.setActive(_active);
        cameraController.setActive(_active);
    }

    public void setControllersActive(boolean _active) {
        setShipControllersActive(_active);

        mouseController.setActive(_active);
        hudController.setActive(_active);
    }

    public void setHUDActive(boolean _active) {
        if (hudGameState != null) {
            hudGameState.setActive(_active);
        }
    }

    public void update(float tpf) {
        super.update(tpf, ship);
//        LoadingManager.getInstance().checkState("gameStart");
//        if (LoadingManager.getInstance().isFinishedLoading("gameStart")) {
//                    GameTaskQueueManager.getManager().update(new Callable<Object>() {
//            public Object call() throws Exception {
//                 clockGameState.load();
//                return null;
//            }
//        });
//
//        }
    }

    public void setClockDisplay(boolean _active) {
        if (clockGameState != null) {
            clockGameState.setActive(_active);
        }
    }

    public void setDradisDisplay(boolean _active) {
        if (dradisGameState != null) {
            dradisGameState.setActive(_active);
        }
    }

    public void setLsoDisplay(boolean _active) {
        if (lsoGameState != null) {
            lsoGameState.setActive(_active);
        }
    }

    public void setWaterDisplay(boolean _active) {
        if (waterGameState != null) {
            waterGameState.setActive(_active);
        }
    }

    public void setCountdownDisplay(boolean _active) {
        if (countdownGameState != null) {
            countdownGameState.setActive(_active);
        }
    }
}
