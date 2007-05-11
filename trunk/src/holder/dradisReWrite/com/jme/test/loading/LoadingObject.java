package com.jme.test.loading;

import java.net.URL;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;

/**
 * an object to hold your loader information this is where the loading is done and eventually passes off to your next
 * GameState
 *
 * @author vivaldi
 * @version $Id: LoadingObject.java,v 1.3 2007/04/04 14:30:55 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class LoadingObject {
    private String name;
    private Runnable primary;
    private Runnable secondary;
    private Thread primaryThread;
    private Thread secondaryThread;

    private BasicLoadingGameState loading;
    private ConsolidatedBasicLoadingGameState loadingtd;

    private boolean doneLoading = false;

    private GameState nextState;

    public LoadingObject(String name, Runnable primary, Runnable secondary, GameState nextState) {
        this.name = name;

        if (primary == null) {
            this.primary = new LoaderThread();
        } else {
            this.primary = primary;
        }

        this.secondary = secondary;
        this.nextState = nextState;
    }

    public void checkState() {
        if (!doneLoading) {
            if (secondaryThread != null && secondaryThread.getState() == Thread.State.TERMINATED) {
                if (primaryThread != null && primaryThread.getState() == Thread.State.TERMINATED) {
                    doneLoading = true;

                    GameStateManager.getInstance().attachChild(nextState);
                    nextState.setActive(true);

                    if (loading != null) {
                        loading.setActive(false);
                    } else if (loadingtd != null) {
                        loadingtd.setActive(false);
                    }

                    finishedLoading = true;
                }
            }
        }
    }

    private static boolean finishedLoading = false;

    public boolean isFinishedLoading() {
        return finishedLoading;
    }

    public void runLoader() {
        runLoader(null, null);
    }

    public void runLoader(TextureDefinition background, TextureDefinition border, TextureDefinition overlay) {
        // Create LoadingGameState and enable
        loadingtd = new ConsolidatedBasicLoadingGameState(border, overlay);

        if (background != null) {
            initBackgroundTd(background);
        }

        GameStateManager.getInstance().attachChild(loadingtd);
        loadingtd.setActive(true);

        // Start our slow loading test
        primaryThread = new Thread(primary);
        primaryThread.start();
    }

    public void runLoader(URL imagePath, URL img2) {
        // Create LoadingGameState and enable
        loading = new BasicLoadingGameState(img2);

        if (imagePath != null) {
            initBackground(imagePath);
        }

        GameStateManager.getInstance().attachChild(loading);
        loading.setActive(true);

        // Start our slow loading test
        primaryThread = new Thread(primary);
        primaryThread.start();
    }

    private void initBackground(URL imagePath) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();
        Quad background = new Quad("Background", display.getWidth(), display.getHeight());
        background.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        background.setColorBuffer(0, null);
//        background.setDefaultColor(color);
//        background.setRenderState(alphaState);
        background.setLocalTranslation(new Vector3f(display.getWidth() / 2, display.getHeight() / 2, 0.0f));

        TextureState texState = display.getRenderer().createTextureState();
        Texture tex = TextureManager.loadTexture(imagePath, Texture.MM_LINEAR, Texture.FM_LINEAR);
        texState.setTexture(tex);
        background.setRenderState(texState);

        background.updateRenderState();
        loading.rootNode.attachChildAt(background, 0);
    }

    private void initBackgroundTd(TextureDefinition backgroundImage) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();
        Quad background = new Quad("Background", display.getWidth(), display.getHeight());
        background.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        background.setColorBuffer(0, null);
//        background.setDefaultColor(color);
//        background.setRenderState(alphaState);
        background.setLocalTranslation(new Vector3f(display.getWidth() / 2, display.getHeight() / 2, 0.0f));

        TextureState texState = backgroundImage.getTextureState();
        background.setRenderState(texState);

        background.updateRenderState();
        loadingtd.rootNode.attachChildAt(background, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    class LoaderThread implements Runnable {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    // no op
                }

                if (i == 0) {
                    secondaryThread = new Thread(secondary);
                    secondaryThread.start();
                }

                if (loading != null) {
                    loading.setProgress((float) i / 100.0f, null);
                } else if (loadingtd != null) {
                    loadingtd.setProgress((float) i / 100.0f, null);
                }
            }
        }
    }
}