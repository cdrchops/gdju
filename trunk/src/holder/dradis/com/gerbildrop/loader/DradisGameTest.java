package com.gerbildrop.loader;

import java.net.URL;
import java.util.logging.Level;

import com.jme.app.BaseGame;
import com.jme.app.AbstractGame;
import com.jme.util.Timer;
import com.jme.util.LoggingSystem;
import com.jme.input.joystick.JoystickInput;
import com.jme.input.KeyInput;
import com.jme.input.MouseInput;
import com.jme.system.JmeException;
import com.jme.system.DisplaySystem;
import com.jmex.game.state.GameStateManager;
import com.gerbildrop.dradis.displays.DradisResourceLoader;
import com.gerbildrop.loader.holdArea.LoadingObject;
import com.gerbildrop.loader.holdArea.LoadingManager;
import com.gerbildrop.loader.holdArea.CameraManager;
import jmetest.game.state.MenuState;


/**
 * The Base Game for all Dradis gameplay
 *
 * @author vivaldi
 * @version $Id: DradisGameTest.java,v 1.1 2007/04/04 14:27:13 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisGameTest extends BaseGame {
    private static AbstractGame instance;

    /**
     * update our instance with the abstract method from the super class update our Time, GameStateManager and
     * LoadingManager
     *
     * @param interpolation float - not used
     */
    protected final void update(float interpolation) {
        Timer.getTimer().update();
        GameStateManager.getInstance().update(Timer.getTimer().getTimePerFrame());
        LoadingManager.getInstance().checkState("gameStart");
    }

    /**
     * render our instance with the abstract method from the super class render our display, GameStateManager and Timer
     * update again
     *
     * @param interpolation
     */
    protected final void render(float interpolation) {
        Timer.getTimer().update();

        // Clears the previously rendered information.
        display.getRenderer().clearBuffers();

        // Render the current game state.
        GameStateManager.getInstance().render(Timer.getTimer().getTimePerFrame());
    }

    /**
     * initialize the system so we have a DisplaySystem, Timer, GameStateManager created, and CameraManager created
     * <p/>
     * initialize the LoadingManager
     */
    protected final void initSystem() {
        LoggingSystem.getLoggingSystem().setLevel(Level.OFF);
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
        } catch (JmeException e) {
            /** If the displaysystem can't be initialized correctly, exit instantly. */
            e.printStackTrace();
            System.exit(1);
        }

        //init a singleton timer for gameplay times
        Timer.getTimer();

        //init the gamestate manager
        GameStateManager.create();

        //init the cameramanager for future use in the game
        CameraManager.getInstance().create();

//        LoadingManager.getInstance().addLoader(new LoadingObject("gameStart", null, new DradisResourceLoader.DradisResourceLoaderThread(), new MenuState("menuState")));
        LoadingManager.getInstance().addLoader(new LoadingObject("gameStart", null, new DradisResourceLoader.DradisResourceLoaderThread(), new MenuState("menuState")));
    }

    /**
     * intialize our game This is where the game actually begins running from
     * <p/>
     * set our abstract instance to this so we can exit later set our display title
     * <p/>
     * Put our loading manager together and run
     */
    protected final void initGame() {
        Timer.getTimer().reset();
        instance = this;
        display.setTitle("XBG - Combat Simulator");

        URL url = DradisGameTest.class.getResource("/dradis/displays/loading/loadBackground.png");
        URL url2 = DradisGameTest.class.getResource("/dradis/displays/loading/loadingComplete.png");
        LoadingManager.getInstance().runLoader("gameStart", url, url2);
    }

    /** not used */
    protected void reinit() {}

    /** cleanup everything we started with */
    protected void cleanup() {
        GameStateManager.getInstance().cleanup();
        KeyInput.destroyIfInitalized();
        MouseInput.destroyIfInitalized();
        JoystickInput.destroyIfInitalized();
    }

    /** allows us to close our game from a GameState as defined */
    public static void exit() {
        instance.finish();
    }

    public static void main(String[] args) {
        DradisGameTest app = new DradisGameTest();
        app.setDialogBehaviour(FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }
}