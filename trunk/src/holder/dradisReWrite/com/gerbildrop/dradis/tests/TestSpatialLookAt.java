/*
 * Copyright (c) 2003-2006 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.tests;

import javax.swing.*;

import java.util.HashMap;

import com.gerbildrop.dradis.tests.cameraManagerTest.CameraMonitor;
import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.input.ChaseCamera;
import com.jme.input.ThirdPersonHandler;
import com.jme.light.DirectionalLight;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Geometry;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jmex.terrain.TerrainPage;
import com.jmex.terrain.util.FaultFractalHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;
import jmetest.input.TestThirdPersonController;
import jmetest.renderer.loader.TestMilkJmeWrite;
import jmetest.terrain.TestTerrain;

/**
 * <code>TestCameraMan</code>
 *
 * @author Joshua Slack
 */
//todo: rework for my cameras and what I am wanting to do
public class TestSpatialLookAt extends SimpleGame {

    private float lastRend = 1;

    public Node scene;

    public Node m_character;

    public TerrainPage page;

    public ChaseCamera chaser;

    public ThirdPersonHandler input;

    public Geometry target;

    private CameraMonitor monitor = new CameraMonitor();

    /**
     * Entry point for the test,
     *
     * @param args
     */
    public static void main(String[] args) {
        TestSpatialLookAt app = new TestSpatialLookAt();
        app.setDialogBehaviour(ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }

    protected void cleanup() {
        super.cleanup();
        monitor.cleanup();
    }

    protected void simpleUpdate() {
        monitor.update();
    }

    private Vector3f normal = new Vector3f();

    protected void simpleRender() {
        input.update(tpf);
        chaser.update(tpf);
        float camMinHeight = page.getHeight(cam.getLocation()) + 2f;
        if (!Float.isInfinite(camMinHeight) && !Float.isNaN(camMinHeight)
            && cam.getLocation().y <= camMinHeight) {
            cam.getLocation().y = camMinHeight;
            cam.update();
        }

        float characterMinHeight = page.getHeight(m_character
                .getLocalTranslation()) + ((BoundingBox) m_character.getWorldBound()).yExtent;
        if (!Float.isInfinite(characterMinHeight) && !Float.isNaN(characterMinHeight)) {
            m_character.getLocalTranslation().y = characterMinHeight;
        }

        page.getSurfaceNormal(m_character.getLocalTranslation(), normal);
        if (normal != null)
            m_character.rotateUpTo(normal);

        boolean renderMonitor = false;

        lastRend += tpf;

        if (lastRend > .03f) {
            renderMonitor = true;
            lastRend = 0;
        }

        monitor.render(scene, m_character, renderMonitor);
    }

    /**
     * builds the trimesh.
     *
     * @see com.jme.app.SimpleGame#initGame()
     */
    protected void simpleInitGame() {
        display.setTitle("Spatial.lookAt Test");

        scene = new Node("rend_scene");
        rootNode.attachChild(scene);
        rootNode.setRenderQueueMode(Renderer.QUEUE_OPAQUE);

        setupCharacter();
        setupTerrain();
        setupChaseCamera();
        setupInput();
        monitor.init(rootNode, m_character);
    }

    private void setupCharacter() {
        target = new Box("box", new Vector3f(), .5f, .5f, .5f);
        target.setLocalScale(10);
        target.setModelBound(new BoundingBox());
        target.updateModelBound();
        m_character = new Node("char Node");
        scene.attachChild(m_character);
        m_character.attachChild(target);
        m_character.getLocalTranslation().y = 255;
        m_character.updateWorldBound(); // We do this to allow the camera setup
        // access to the world bound in our
        // setup code.

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        ts.setTexture(TextureManager.loadTexture(
                TestThirdPersonController.class.getClassLoader().getResource(
                        "jmetest/data/images/Monkey.tga"), Texture.MM_LINEAR,
                                                           Texture.FM_LINEAR));
        m_character.setRenderState(ts);
    }

    private void setupTerrain() {
        display.getRenderer().setBackgroundColor(
                new ColorRGBA(0.5f, 0.5f, 0.5f, 1));

        DirectionalLight dr = new DirectionalLight();
        dr.setEnabled(true);
        dr.setDiffuse(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
        dr.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
        dr.setDirection(new Vector3f(0.5f, -0.5f, 0));

        CullState cs = display.getRenderer().createCullState();
        cs.setCullMode(CullState.CS_BACK);
        cs.setEnabled(true);
        rootNode.setRenderState(cs);

        LightState lightState = display.getRenderer().createLightState();
        lightState.setEnabled(true);
        lightState.attach(dr);
        rootNode.setRenderState(lightState);

        FaultFractalHeightMap heightMap = new FaultFractalHeightMap(257, 32, 0,
                                                                    255, 0.75f);
        Vector3f terrainScale = new Vector3f(10, 1, 10);
        heightMap.setHeightScale(0.001f);
        page = new TerrainPage("Terrain", 33, heightMap.getSize(),
                               terrainScale, heightMap.getHeightMap(), false);

        page.setDetailTexture(1, 16);
        scene.attachChild(page);

        ProceduralTextureGenerator pt = new ProceduralTextureGenerator(
                heightMap);
        pt.addTexture(new ImageIcon(TestTerrain.class.getClassLoader()
                .getResource("jmetest/data/texture/grassb.png")), -128, 0, 128);
        pt.addTexture(new ImageIcon(TestTerrain.class.getClassLoader()
                .getResource("jmetest/data/texture/dirt.jpg")), 0, 128, 255);
        pt.addTexture(new ImageIcon(TestTerrain.class.getClassLoader()
                .getResource("jmetest/data/texture/highest.jpg")), 128, 255,
                                                                   384);

        pt.createTexture(512);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        Texture t1 = TextureManager.loadTexture(pt.getImageIcon().getImage(),
                                                Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR, true);
        ts.setTexture(t1, 0);

        Texture t2 = TextureManager.loadTexture(TestThirdPersonController.class
                .getClassLoader()
                .getResource("jmetest/data/texture/Detail.jpg"),
                                                Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR);
        ts.setTexture(t2, 1);
        t2.setWrap(Texture.WM_WRAP_S_WRAP_T);

        t1.setApply(Texture.AM_COMBINE);
        t1.setCombineFuncRGB(Texture.ACF_MODULATE);
        t1.setCombineSrc0RGB(Texture.ACS_TEXTURE);
        t1.setCombineOp0RGB(Texture.ACO_SRC_COLOR);
        t1.setCombineSrc1RGB(Texture.ACS_PRIMARY_COLOR);
        t1.setCombineOp1RGB(Texture.ACO_SRC_COLOR);
        t1.setCombineScaleRGB(1.0f);

        t2.setApply(Texture.AM_COMBINE);
        t2.setCombineFuncRGB(Texture.ACF_ADD_SIGNED);
        t2.setCombineSrc0RGB(Texture.ACS_TEXTURE);
        t2.setCombineOp0RGB(Texture.ACO_SRC_COLOR);
        t2.setCombineSrc1RGB(Texture.ACS_PREVIOUS);
        t2.setCombineOp1RGB(Texture.ACO_SRC_COLOR);
        t2.setCombineScaleRGB(1.0f);
        rootNode.setRenderState(ts);

        FogState fs = display.getRenderer().createFogState();
        fs.setDensity(0.5f);
        fs.setEnabled(true);
        fs.setColor(new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
        fs.setEnd(1000);
        fs.setStart(500);
        fs.setDensityFunction(FogState.DF_LINEAR);
        fs.setApplyFunction(FogState.AF_PER_VERTEX);
        rootNode.setRenderState(fs);
    }

    private void setupChaseCamera() {
        Vector3f targetOffset = new Vector3f();
        targetOffset.y = ((BoundingBox) m_character.getWorldBound()).yExtent * 1.5f;
        chaser = new ChaseCamera(cam, m_character);
        chaser.setTargetOffset(targetOffset);
    }

    private void setupInput() {
        HashMap<String, Object> handlerProps = new HashMap<String, Object>();
        handlerProps.put(ThirdPersonHandler.PROP_DOGRADUAL, "true");
        handlerProps.put(ThirdPersonHandler.PROP_TURNSPEED, ""
                                                            + (1.0f * FastMath.PI));
        handlerProps.put(ThirdPersonHandler.PROP_LOCKBACKWARDS, "false");
        handlerProps.put(ThirdPersonHandler.PROP_CAMERAALIGNEDMOVE, "true");
        input = new ThirdPersonHandler(m_character, cam, handlerProps);
        input.setActionSpeed(100f);
    }
}