package com.gerbildrop.mask;

import java.util.HashMap;

import javax.swing.ImageIcon;

import com.gerbildrop.engine.spatial.base.BModelFactory;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.mask.powerUps.PowerUpBox;
import com.gerbildrop.mask.util.NodeRotator;
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
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.terrain.TerrainPage;
import com.jmex.terrain.util.FaultFractalHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;
import jmetest.input.TestThirdPersonController;
import jmetest.terrain.TestTerrain;

public class CameraMonitorGame extends SimpleGame {

    private float lastRend = 1;

    public Node scene;

    public Node m_character;

    public TerrainPage page;

    public ChaseCamera chaser;

    public ThirdPersonHandler input;

    public Geometry target;

    //    private CameraMonitor monitor = new CameraMonitor();
    private Node staticBoxNode;
    public Geometry box;
    PowerUpBox pub = new PowerUpBox();

    protected void cleanup() {
        super.cleanup();
//        monitor.cleanup();
    }

    protected void simpleUpdate() {
//        monitor.update();
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

//        monitor.render(scene, m_character, renderMonitor);
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

        BaseModelObject v2 = BModelFactory.getInstance().createModelTest("data/buildings/", "data/buildings/house.jme");
//        v2.getRootNode().setLocalTranslation(new Vector3f(0, -.25f, 1));
        NodeRotator.rotate(v2, BaseModelObject.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.PITCH, (float) Math.toRadians(90));
        v2.updateWorldBound();
        v2.setLocalScale(1);

        v2.setLocalTranslation(m_character.getLocalTranslation());
        v2.updateWorldBound();
        rootNode.attachChild(v2);
        rootNode.updateRenderState();

//        setupBoxNode();
        pub.create(rootNode, m_character.getLocalTranslation());
        rootNode.updateRenderState();
//        NightVision nv = new NightVision();
//        nv.create(rootNode);
//        monitor.init(rootNode, m_character);
    }

    private void setupBoxNode() {
        box = new Box("boxNode", new Vector3f(), .5f, .5f, .5f);
        box.setLocalScale(10);
        box.setModelBound(new BoundingBox());
        box.updateModelBound();

        staticBoxNode = new Node("boxy Node");
        scene.attachChild(staticBoxNode);
        staticBoxNode.attachChild(box);
        staticBoxNode.getLocalTranslation().y = 255;
        staticBoxNode.updateWorldBound(); // We do this to allow the camera setup
        // access to the world bound in our
        // setup code.
        AlphaState alpha;

        alpha = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        alpha.setBlendEnabled(true);
        alpha.setSrcFunction(4);
        alpha.setDstFunction(1);
        alpha.setEnabled(true);

        alpha.setBlendEnabled(true);
        alpha.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alpha.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        ts.setTexture(TextureManager.loadTexture(
                CameraMonitorGame.class.getResource("/jmetest/data/images/Monkey.tga"), Texture.MM_LINEAR,
                Texture.FM_LINEAR));
        staticBoxNode.setRenderState(ts);
        staticBoxNode.setLocalTranslation(m_character.getLocalTranslation());
//        staticBoxNode.setRenderState(alpha);
    }

    private void setupCharacter() {
        target = new Box("box", new Vector3f(), .5f, .5f, .5f);
        target.setLocalScale(10);
        target.setModelBound(new BoundingBox());
        target.updateModelBound();

        m_character = new Node("char Node");
        scene.attachChild(m_character);
        m_character.attachChild(target);
//        m_character.getLocalTranslation().y = 255;
        m_character.updateWorldBound(); // We do this to allow the camera setup
        // access to the world bound in our
        // setup code.

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        ts.setTexture(TextureManager.loadTexture(
                TestThirdPersonController.class.getResource(
                        "/jmetest/data/images/Monkey.tga"), Texture.MM_LINEAR,
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

//        CullState cs = display.getRenderer().createCullState();
//        cs.setCullMode(CullState.CS_BACK);
//        cs.setEnabled(true);
//        rootNode.setRenderState(cs);

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
        pt.addTexture(new ImageIcon(TestTerrain.class.getResource("/jmetest/data/texture/grassb.png")), -128, 0, 128);
        pt.addTexture(new ImageIcon(TestTerrain.class
                .getResource("/jmetest/data/texture/dirt.jpg")), 0, 128, 255);
        pt.addTexture(new ImageIcon(TestTerrain.class
                .getResource("/jmetest/data/texture/highest.jpg")), 128, 255,
                                                                    384);

        pt.createTexture(512);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        Texture t1 = TextureManager.loadTexture(pt.getImageIcon().getImage(),
                                                Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR, true);
        ts.setTexture(t1, 0);

        Texture t2 = TextureManager.loadTexture(TestThirdPersonController.class
                .getResource("/jmetest/data/texture/Detail.jpg"),
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

//        FogState fs = display.getRenderer().createFogState();
//        fs.setDensity(0.5f);
//        fs.setEnabled(true);
//        fs.setColor(new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
//        fs.setEnd(1000);
//        fs.setStart(500);
//        fs.setDensityFunction(FogState.DF_LINEAR);
//        fs.setApplyFunction(FogState.AF_PER_VERTEX);
//        rootNode.setRenderState(fs);
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