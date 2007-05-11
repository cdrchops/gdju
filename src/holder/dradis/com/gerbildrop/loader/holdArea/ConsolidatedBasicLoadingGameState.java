package com.gerbildrop.loader.holdArea;

import java.nio.FloatBuffer;

import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;
import com.jme.util.geom.BufferUtils;
import com.jmex.font2d.Text2D;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.game.state.load.Loader;
import com.jmex.scene.TimedLifeController;

/**
 * @author vivaldi
 * @version $Id: ConsolidatedBasicLoadingGameState.java,v 1.1 2007/04/04 14:27:20 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class ConsolidatedBasicLoadingGameState extends GameState implements Loader {
    protected Node rootNode;
    protected Text2D statusText;
    protected Quad progressBar;
    protected Text2D percentageText;
    protected ColorRGBA color;
    protected AlphaState alphaState;

    private int steps;
    private int current;

    private static final float MAXIMUM = 100f;
    private Node hudNode = new Node("hudNode");
    private Quad gauge;
    TextureDefinition border;
    TextureDefinition overlay;

    public ConsolidatedBasicLoadingGameState(TextureDefinition border, TextureDefinition overlay) {
        this(100, border, overlay);
    }

    public ConsolidatedBasicLoadingGameState(int steps, TextureDefinition border, TextureDefinition overlay) {
        this.steps = steps;
        current = 0;
        init(border, overlay);
    }

    protected void init(TextureDefinition border, TextureDefinition overlay) {
        this.border = border;
        this.overlay = overlay;

        DisplaySystem display = DisplaySystem.getDisplaySystem();
        color = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);

        alphaState = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        alphaState.setBlendEnabled(true);
        alphaState.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alphaState.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        alphaState.setTestEnabled(false);
        alphaState.setEnabled(true);

        rootNode = new Node();
        rootNode.setCullMode(Spatial.CULL_NEVER);
        rootNode.setLightCombineMode(LightState.OFF);

        ZBufferState zbs = display.getRenderer().createZBufferState();
        zbs.setFunction(ZBufferState.CF_ALWAYS);

        Quad hudQuad = new Quad("hud", 182f, 24f);
        gauge = new Quad("gauge", 180f, 22f);

        hudNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);

        hudNode.setLocalTranslation(new Vector3f(display.getWidth() / 2, display.getHeight() / 2, 0));

        TextureState ts = null;

        if (border != null) {
            ts = border.getTextureState();

            //todo: fix so that there can be a border and overlay like the HUD example
            FloatBuffer texCoords = border.getTextureCoords();

            hudQuad.setTextureBuffer(0, texCoords);

        } else {
            ts = overlay.getTextureState();
        }

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

        hudNode.setRenderState(ts);
        hudNode.setRenderState(alphaState);
        hudNode.updateRenderState();

        rootNode.attachChild(hudNode);
    }

    public void update(float tpf) {
        rootNode.updateGeometricState(tpf, true);
    }

    public void render(float tpf) {
        DisplaySystem.getDisplaySystem().getRenderer().draw(rootNode);
    }

    public void cleanup() {
    }

    private static enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, FADE_RIGHT_TO_LEFT, FADE_LEFT_TO_RIGHT}

    private static Direction direction = Direction.LEFT_TO_RIGHT;

    public void setProgress(float progress) {
        int percentage = (int) (progress * 100);

        //todo: fix so that this can work with a border and overlay like the HUD tut
        //todo: fix for masking part of the loading image
        //todo: fix so that the texture info is stored in it's own class and not statically here

        FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        float relCoord = 0;//
        if (direction == Direction.LEFT_TO_RIGHT) {
            relCoord = overlay.getTextureWidth() - ((float) percentage / MAXIMUM) * overlay.getTextureWidth();

            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getXLoc()));
            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getYLoc()));
            texCoords.put(getUForPixel(relCoord + overlay.getTextureWidth())).put(getVForPixel(overlay.getYLoc()));
            texCoords.put(getUForPixel(relCoord + overlay.getTextureWidth())).put(getVForPixel(overlay.getXLoc()));
        } else if (direction == Direction.RIGHT_TO_LEFT) {
            relCoord = overlay.getTextureWidth() - ((float) percentage / MAXIMUM) * overlay.getTextureWidth();

            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getXLoc()));
            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getYLoc()));
            texCoords.put(getUForPixel(relCoord - overlay.getTextureWidth())).put(getVForPixel(overlay.getYLoc()));
            texCoords.put(getUForPixel(relCoord - overlay.getTextureWidth())).put(getVForPixel(overlay.getXLoc()));
        } else if (direction == Direction.FADE_RIGHT_TO_LEFT) {
            relCoord = 0.5f - ((float) percentage / MAXIMUM) * 0.5f;

            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getTextureWidth()));
            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getTextureHeight()));
            texCoords.put(getUForPixel(relCoord + 0.5f)).put(getVForPixel(overlay.getTextureHeight()));
            texCoords.put(getUForPixel(relCoord + 0.5f)).put(getVForPixel(overlay.getTextureWidth()));
        } else if (direction == Direction.FADE_LEFT_TO_RIGHT) {
            relCoord = 0.5f + ((float) percentage / MAXIMUM) * 0.5f;

            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getTextureWidth()));
            texCoords.put(getUForPixel(relCoord)).put(getVForPixel(overlay.getTextureHeight()));
            texCoords.put(getUForPixel(relCoord - 0.5f)).put(getVForPixel(overlay.getTextureHeight()));
            texCoords.put(getUForPixel(relCoord - 0.5f)).put(getVForPixel(overlay.getTextureWidth()));
        }

        if (border != null) {
            TextureState ts = overlay.getTextureState();
            ts.setEnabled(true);
            gauge.setRenderState(ts);
        }

        gauge.setTextureBuffer(0, texCoords);

        if (percentage == 100) {
            LoaderFadeOut fader = new LoaderFadeOut(2.0f, this);
            rootNode.addController(fader);
            fader.setActive(true);
        }
    }

    protected float getUForPixel(float xPixel) {
        return xPixel / overlay.getTextureWidth();
    }

    protected float getVForPixel(float yPixel) {
        return 1f - yPixel / overlay.getTextureWidth();
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

    //todo: fix to allow fade out with custom percentage or time
    class LoaderFadeOut extends TimedLifeController {
        private static final long serialVersionUID = 1L;

        private ConsolidatedBasicLoadingGameState loading;

        public LoaderFadeOut(float lifeInSeconds, ConsolidatedBasicLoadingGameState loading) {
            super(lifeInSeconds);
            this.loading = loading;
        }

        public void updatePercentage(float percentComplete) {
            loading.setAlpha(1.0f - percentComplete);
            if (percentComplete == 1.0f) {
                loading.setActive(false);
                GameStateManager.getInstance().detachChild(loading);
            }
        }
    }

    //todo: fix to allow fade in with custom percentage or time
    class TransitionFadeIn extends TimedLifeController {
        private static final long serialVersionUID = 1L;

        /** Game state which is fading away */
        private GameState leadIn;

        private ConsolidatedBasicLoadingGameState transition;

        public TransitionFadeIn(float lifeInSeconds, GameState leadIn, ConsolidatedBasicLoadingGameState transition) {
            super(lifeInSeconds);
            this.leadIn = leadIn;
            this.transition = transition;
        }

        public void updatePercentage(float percentComplete) {

            transition.setAlpha(percentComplete);

            if (percentComplete == 1.0f) {
                leadIn.setActive(false);
            }
        }
    }
}
