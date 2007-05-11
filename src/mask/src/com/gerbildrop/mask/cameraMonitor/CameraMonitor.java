package com.gerbildrop.mask.cameraMonitor;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.renderer.TextureRenderer;
import com.jme.scene.CameraNode;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;

public class CameraMonitor {
    private Node monitorNode;
    private TextureRenderer tRenderer;
    private Texture fakeTex;
    private CameraNode camNode;

    public void init(Node rootNode, Node m_character) {

        tRenderer = DisplaySystem.getDisplaySystem().createTextureRenderer(256, 256, 0);
        camNode = new CameraNode("Camera Node", gettRenderer().getCamera());
        camNode.setLocalTranslation(new Vector3f(0, 255, 0));
        camNode.updateGeometricState(0, true);
        rootNode.attachChild(camNode);

        setupSecurityMonitor(m_character);
    }

    public void setupSecurityMonitor(Node m_character) {
        monitorNode = new Node("Monitor Node");
        monitorNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        Quad quad = new Quad("Monitor");
        quad.getBatch(0).setZOrder(1);
        quad.initialize(150, 150);
        quad.setLocalTranslation(new Vector3f(90f, 110f, 0));
        monitorNode.attachChild(quad);

        Quad quad2 = new Quad("Monitor");
        quad2.getBatch(0).setZOrder(2);
        quad2.initialize(160f, 160f);
        quad2.getLocalTranslation().set(quad.getLocalTranslation());
        monitorNode.attachChild(quad2);

        // Ok, now lets create the Texture object that our scene will be
        // rendered to.
        tRenderer.setBackgroundColor(new ColorRGBA(0f, 0f, 0f, 1f));
        fakeTex = new Texture();
        fakeTex.setRTTSource(Texture.RTT_SOURCE_RGBA);
        tRenderer.setupTexture(fakeTex);
        TextureState screen = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        screen.setTexture(fakeTex);
        screen.setEnabled(true);
        quad.setRenderState(screen);
        monitorNode.updateGeometricState(0.0f, true);
        monitorNode.updateRenderState();
        camNode.lookAt(m_character.getWorldTranslation(), Vector3f.UNIT_Y);
    }

    public void update() {
        monitorNode.updateGeometricState(0.0f, true);
    }

    public void cleanup() {
        tRenderer.cleanup();
    }

    public void render(Node scene, Node m_character, boolean renderMonitor) {
        if (renderMonitor) {
            camNode.lookAt(m_character.getWorldTranslation(), Vector3f.UNIT_Y);
            tRenderer.render(scene, fakeTex);
        }

        DisplaySystem.getDisplaySystem().getRenderer().draw(getMonitorNode());
    }

    public Node getMonitorNode() {
        return monitorNode;
    }

    public Texture getFakeTex() {
        return fakeTex;
    }

    public void setFakeTex(Texture fakeTex) {
        this.fakeTex = fakeTex;
    }

    public TextureRenderer gettRenderer() {
        return tRenderer;
    }

    public void settRenderer(TextureRenderer tRenderer) {
        this.tRenderer = tRenderer;
    }
}
