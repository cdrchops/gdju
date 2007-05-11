package com.gerbildrop.dradis.tests.cameraManagerTest;

import com.gerbildrop.dradis.cameras.CameraManager;

import com.jme.app.AbstractGame;
import com.jme.app.BaseGame;
import com.jme.input.KeyInput;
import com.jme.input.MouseInput;
import com.jme.input.joystick.JoystickInput;
import com.jme.scene.Node;
import com.jme.system.DisplaySystem;
import com.jme.system.JmeException;
import com.jme.util.Timer;

public class CmTest extends BaseGame {
    private static AbstractGame instance;
    private Node rootNode = new Node("rootNode");

    protected final void update(float interpolation) {
        Timer.getTimer().update();
        CameraManager.getInstance().update(interpolation);
        rootNode.updateRenderState();
    }

    protected final void render(float interpolation) {
        Timer.getTimer().update();
        // Clears the previously rendered information.
        display.getRenderer().clearBuffers();
    }

    protected final void initSystem() {
//        LoggingSystem.getLoggingSystem().setLevel(Level.OFF);
        try {
            /** Get a DisplaySystem acording to the renderer selected in the startup box. */
            display = DisplaySystem.getDisplaySystem(properties.getRenderer());
            /** Create a window with the startup box's information. */
            display.createWindow(
                    properties.getWidth(),
                    properties.getHeight(),
                    properties.getDepth(),
                    properties.getFreq(),
                    properties.getFullscreen());
            /** Create a camera specific to the DisplaySystem that works with
             * the display's width and height*/
        } catch (JmeException e) {
            /** If the displaysystem can't be initialized correctly, exit instantly. */
            e.printStackTrace();
            System.exit(1);
        }

        Timer.getTimer();

        //init the cameramanager for future use in the game
        CameraManager.getInstance().create();
    }

    protected final void initGame() {
        Timer.getTimer().reset();
        instance = this;
        display.setTitle("Camera Manager Test");
        rootNode.attachChild(CameraManager.getInstance().get(CameraManager.CameraView.CURRENT));
    }

    protected void reinit() {
    }

    protected void cleanup() {
        KeyInput.destroyIfInitalized();
        MouseInput.destroyIfInitalized();
        JoystickInput.destroyIfInitalized();
    }

    public static void exit() {
        instance.finish();
    }

    public static void main(String[] args) {
        CmTest app = new CmTest();
        app.setDialogBehaviour(FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }
}