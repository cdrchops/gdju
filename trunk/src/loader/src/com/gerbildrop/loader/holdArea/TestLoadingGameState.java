package com.gerbildrop.loader.holdArea;

import java.net.URL;
import java.util.logging.Level;

import com.jme.app.AbstractGame;
import com.jme.app.BaseGame;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.MouseInput;
import com.jme.input.joystick.JoystickInput;
import com.jme.system.DisplaySystem;
import com.jme.system.JmeException;
import com.jme.util.LoggingSystem;
import com.jme.util.Timer;
import com.jmex.game.state.GameStateManager;
import com.gerbildrop.loader.holdArea.StandaloneLoader;
import com.gerbildrop.loader.holdArea.MyClassToLoad;

/**
 * a different implementation of the TestLoadingGameState that allows you to load your resources while displaying a
 * simple loading message when complete the LoadingManager passes you off to the next GameState that you've defined in
 * your LoadingObject referenced by name
 *
 * @author vivaldi
 * @version $Id: TestLoadingGameState.java,v 1.3 2007/04/04 19:50:20 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class TestLoadingGameState extends BaseGame {
    /** Only used in the static exit method. */
    private static AbstractGame instance;

    /** High resolution timer for jME. */
    private Timer timer;

    /** Simply an easy way to get at timer.getTimePerFrame(). */
    private float tpf;

    private static int screenshotCounter = 0;
    private StandaloneLoader sl = new StandaloneLoader("slNode");

    /**
     * This is called every frame in BaseGame.start()
     *
     * @param interpolation unused in this implementation
     *
     * @see com.jme.app.AbstractGame#update(float interpolation)
     */
    protected final void update(float interpolation) {
        // Recalculate the framerate.
        timer.update();
        tpf = timer.getTimePerFrame();

        // Update the current game state.
        GameStateManager.getInstance().update(tpf);

        if (KeyBindingManager.getKeyBindingManager().isValidCommand("screen_shot", false)) {
            display.getRenderer().takeScreenShot("SimpleGameScreenShot" + screenshotCounter);
            screenshotCounter++;
        }

//        LoadingManager.getInstance().checkState("gameStart");
        if (!sl.isDoneLoading()) {
            sl.update(interpolation);
            sl.checkState();
        } else {
            sl.detachAllChildren();
            System.out.println("done loading");
        }
    }

    /**
     * This is called every frame in BaseGame.start(), after update()
     *
     * @param interpolation unused in this implementation
     *
     * @see com.jme.app.AbstractGame#render(float interpolation)
     */
    protected final void render(float interpolation) {
        // Clears the previously rendered information.
        display.getRenderer().clearBuffers();
        // Render the current game state.
        GameStateManager.getInstance().render(tpf);
        sl.render(interpolation);
    }

    /**
     * Creates display, sets  up camera, and binds keys.  Called in BaseGame.start() directly after the dialog box.
     *
     * @see com.jme.app.AbstractGame#initSystem()
     */
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

        /** Get a high resolution timer for FPS updates. */
        timer = Timer.getTimer();

        KeyBindingManager.getKeyBindingManager().set("screen_shot", KeyInput.KEY_P);
        URL url = ClassLoader.getSystemResource("/dradis/displays/loading/loadBackground.png");
        URL url2 = ClassLoader.getSystemResource("/dradis/displays/loading/loadingComplete.png");

        sl.init(url, url2, new MyClassToLoad.MyResourceLoaderThread());
    }

    /**
     * Called in BaseGame.start() after initSystem().
     *
     * @see com.jme.app.AbstractGame#initGame()
     */
    protected final void initGame() {
        instance = this;
        display.setTitle("Test Loading Game State -- test");

        //todo: fix loading so that initially, it displays a screen until the DradisNodeStorage is finished loading
        // todo: fix a loading state that displays on the screen
        // Creates the GameStateManager. Only needs to be called once.
        GameStateManager.create();
//        LoadingObject lo = new LoadingObject("gameStart", null, new MyClassToLoad.MyResourceLoaderThread(), new MenuState("menu"));

//        LoadingManager.getInstance().addLoader(lo);
////        URL url = ClassLoader.getSystemResource("dradis/displays/loading/loadBackground.png");
////        URL url2 = ClassLoader.getSystemResource("dradis/displays/loading/loadingComplete.png");
//
////        LoadingManager.getInstance().runLoader("gameStart", url, url2);
//        TextureDefinition background = new TextureDefinition(ClassLoader.getSystemResource("dradis/displays/loading/loadBackground.png"));
//        TextureDefinition border = null;//new TextureDefinition(ClassLoader.getSystemResource("dradis/displays/HUDS/borderImage.png"));
////        TextureDefinition overlay = new TextureDefinition(ClassLoader.getSystemResource("dradis/displays/HUDS/overlayImage.png"));
//        TextureDefinition overlay = new TextureDefinition(ClassLoader.getSystemResource("dradis/displays/loading/loadingComplete.png"));
//
//        LoadingManager.getInstance().runLoader("gameStart", background, border, overlay);
        sl.run();

    }

    /**
     * Empty.
     *
     * @see com.jme.app.AbstractGame#reinit()
     */
    protected void reinit() {}

    /**
     * Cleans up the keyboard and game state system.
     *
     * @see com.jme.app.AbstractGame#cleanup()
     */
    protected void cleanup() {
        LoggingSystem.getLogger().log(Level.INFO, "Cleaning up resources.");

        // Performs cleanup on all loaded game states.
        GameStateManager.getInstance().cleanup();

        KeyInput.destroyIfInitalized();
        MouseInput.destroyIfInitalized();
        JoystickInput.destroyIfInitalized();
    }

    /** Static method to finish this application. */
    public static void exit() {
        instance.finish();
    }

    public static void main(String[] args) {
        TestLoadingGameState app = new TestLoadingGameState();
        app.setDialogBehaviour(ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }
}