package com.gerbildrop.loader;

import java.net.URL;
import java.nio.FloatBuffer;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;
import com.jmex.font2d.Text2D;
import com.jmex.game.state.load.Loader;

/**
 * @author vivaldi
 * @version $Id: StandaloneLoader.java,v 1.1 2007/04/12 20:42:46 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class StandaloneLoader extends Node implements Loader {
    protected Node rootNode;
    protected Text2D statusText;
    protected Quad progressBar;
    protected Text2D percentageText;
    protected ColorRGBA color;
    protected AlphaState alphaState;

    private int steps = 100;
    private int current = 0;

    private static final float MAXIMUM = 100f;
    private Node hudNode = new Node("hudNode");
    private Quad gauge;

    private int textureWidth = 182;
    private int textureHeight = 24;
    private float xLoc = 0;
    private float yLoc = textureHeight;
    private Thread secondaryThread;
    private Thread primaryThread;
    private Runnable primary = new LoaderThread();
    private Runnable runner;

    public StandaloneLoader(String string) {
        super(string);
    }

    public StandaloneLoader() {
        super();
    }

    private void initBackground(URL imagePath, DisplaySystem display) {
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
        rootNode.attachChildAt(background, 0);
    }

    public void run() {
        // Start our slow loading test
        primaryThread = new Thread(primary);
        primaryThread.start();
    }

    public void init(URL background, URL imagePath, Runnable runner, DisplaySystem display) {
        this.runner = runner;
        color = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);

        alphaState = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        alphaState.setBlendEnabled(true);
        alphaState.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alphaState.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        alphaState.setTestEnabled(false);
        alphaState.setEnabled(true);

        rootNode = new Node();
        rootNode.setCullMode(SceneElement.CULL_NEVER);
        rootNode.setLightCombineMode(LightState.OFF);

        ZBufferState zbs = display.getRenderer().createZBufferState();
        zbs.setFunction(ZBufferState.CF_ALWAYS);

        Quad hudQuad = new Quad("hud", 182f, 24f);
        gauge = new Quad("gauge", 180f, 22f);

        hudNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        hudNode.setLocalTranslation(new Vector3f(display.getWidth() / 2, display.getHeight() / 2, 0));

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(imagePath, Texture.MM_LINEAR, Texture.FM_LINEAR, 1.0f, true));
        textureWidth = ts.getTexture().getImage().getWidth();
        textureHeight = ts.getTexture().getImage().getHeight();
        ts.setEnabled(true);

        hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        hudQuad.setColorBuffer(0, null);
        hudQuad.setDefaultColor(color);
        hudQuad.setRenderState(alphaState);
        hudQuad.updateRenderState();

        hudNode.setLightCombineMode(LightState.OFF);
        hudNode.updateRenderState();

        hudNode.attachChild(hudQuad);
        hudNode.attachChild(gauge);

//        hudNode.setRenderState(loading.getTextureState());
        hudNode.setRenderState(ts);
        hudNode.setRenderState(alphaState);
        hudNode.updateRenderState();

        rootNode.attachChild(hudNode);
        initBackground(background, display);
    }

    public void update(float tpf) {
        rootNode.updateGeometricState(tpf, true);
    }

    public void render(float tpf, DisplaySystem display) {
        if (rootNode != null) {
            display.getRenderer().draw(rootNode);
        }
    }

    public void cleanup() {
    }

    public void setProgress(float progress) {
        int percentage = (int) (progress * 100);

        //todo: fix so that this can work with a border and overlay like the HUD tut
        //todo: fix for masking part of the loading image
        //todo: fix so that the texture info is stored in it's own class and not statically here
        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        float relCoord = textureWidth - ((float) percentage / MAXIMUM) * textureWidth;

        texCoords.put(getUForPixel(relCoord)).put(getVForPixel(xLoc));
        texCoords.put(getUForPixel(relCoord)).put(getVForPixel(yLoc));
        texCoords.put(getUForPixel(relCoord + textureWidth)).put(getVForPixel(yLoc));
        texCoords.put(getUForPixel(relCoord + textureWidth)).put(getVForPixel(xLoc));

        gauge.setTextureBuffer(0, texCoords);
    }

    protected float getUForPixel(float xPixel) {
        return xPixel / textureWidth;
    }

    protected float getVForPixel(float yPixel) {
        return 1f - yPixel / textureHeight;
    }

    public void setProgress(float progress, String activity) {
        if (statusText != null) {
            statusText.setText(activity);
        } else {
            setProgress(progress);
        }
    }

    protected void setAlpha(float alpha) {
        color.a = alpha;
    }

    public float increment() {
        return increment(1);
    }

    public float increment(int steps) {
        current += steps;
        float percent = (float) current / (float) this.steps;
        setProgress(percent);
        return percent;
    }

    public float increment(String activity) {
        float percent = increment();
        setProgress(percent, activity);
        return percent;
    }

    public float increment(int steps, String activity) {
        float percent = increment(steps);
        setProgress(percent, activity);
        return percent;
    }

    private boolean doneLoading = false;

    public void checkState() {
        if (!doneLoading) {
            if (secondaryThread != null && secondaryThread.getState() == Thread.State.TERMINATED) {
                if (primaryThread != null && primaryThread.getState() == Thread.State.TERMINATED) {
                    doneLoading = true;
                }
            }
        }
    }

    public boolean isDoneLoading() {
        return doneLoading;
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
                    secondaryThread = new Thread(runner);
                    secondaryThread.start();
                }


                setProgress((float) i / 100.0f, null);
            }
        }
    }
}
