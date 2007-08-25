package com.gerbildrop.mask.tests;

import javax.swing.ImageIcon;

import com.gerbildrop.engine.spatial.base.BModelFactory;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.mask.powerUps.PowerUpBox;
import com.gerbildrop.mask.powerUps.PowerUpSphere;
import com.gerbildrop.mask.util.NodeRotator;
import com.jme.app.SimpleGame;
import com.jme.image.Texture;
import com.jme.input.NodeHandler;
import com.jme.light.DirectionalLight;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.CameraNode;
import com.jme.scene.Node;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jmex.terrain.TerrainPage;
import com.jmex.terrain.util.FaultFractalHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;

/**
 * <code>TestTerrainPage</code>
 *
 * @author Mark Powell
 * @version $Id: TestTerrainPage.java,v 1.2 2007/04/18 21:53:29 vivaldi Exp $
 */
public class TestTerrainPage extends SimpleGame {

    private CameraNode camNode;
    private TerrainPage page;
    private PowerUpBox pub = new PowerUpBox();
    private PowerUpSphere ps = new PowerUpSphere();
    private Node scene;

    /**
     * Entry point for the test,
     *
     * @param args
     */
    public static void main(String[] args) {
        TestTerrainPage app = new TestTerrainPage();
        app.setDialogBehaviour(NEVER_SHOW_PROPS_DIALOG);
        app.start();
    }

    /**
     * builds the trimesh.
     *
     * @see com.jme.app.SimpleGame#initGame()
     */
    protected void simpleInitGame() {
        scene = new Node("scene node");
        scene.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        fpsNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);

//    DirectionalLight dl = new DirectionalLight();
//    dl.setDiffuse(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
//    dl.setDirection(new Vector3f(1, -0.5f, 1));
//    dl.setEnabled(true);
//    lightState.attach(dl);

        cam.setFrustum(1.0f, 1000.0f, -0.55f, 0.55f, 0.4125f, -0.4125f);
        cam.update();

        camNode = new CameraNode("Camera Node", cam);
        camNode.setLocalTranslation(new Vector3f(0, 250, -20));
        camNode.updateWorldData(0);
        input = new NodeHandler(camNode, 150, 1);
        scene.attachChild(camNode);
        display.setTitle("Terrain Test");
        display.getRenderer().setBackgroundColor(new ColorRGBA(0.5f, 0.5f, 0.5f, 1));

        DirectionalLight dr = new DirectionalLight();
        dr.setEnabled(true);
        dr.setDiffuse(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
        dr.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
        dr.setDirection(new Vector3f(0.5f, -0.5f, 0));

        CullState cs = display.getRenderer().createCullState();
        cs.setCullMode(CullState.CS_BACK);
        cs.setEnabled(true);
        scene.setRenderState(cs);

        lightState.attach(dr);

        FaultFractalHeightMap heightMap = new FaultFractalHeightMap(257, 32, 0, 255,
                                                                    0.75f);
        Vector3f terrainScale = new Vector3f(10, 1, 10);
        heightMap.setHeightScale(0.001f);
        page = new TerrainPage("Terrain", 33, heightMap.getSize(), terrainScale,
                               heightMap.getHeightMap(), false);

        page.setDetailTexture(1, 16);
        scene.attachChild(page);

        ProceduralTextureGenerator pt = new ProceduralTextureGenerator(heightMap);
        pt.addTexture(new ImageIcon(ClassLoader.getSystemResource(
                "jmetest/data/texture/grassb.png")), -128, 0, 128);
        pt.addTexture(new ImageIcon(ClassLoader.getSystemResource(
                "jmetest/data/texture/dirt.jpg")), 0, 128, 255);
        pt.addTexture(new ImageIcon(ClassLoader.getSystemResource(
                "jmetest/data/texture/highest.jpg")), 128, 255, 384);

        pt.createTexture(512);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        Texture t1 = TextureManager.loadTexture(
                pt.getImageIcon().getImage(),
                Texture.MM_LINEAR_LINEAR,
                Texture.FM_LINEAR,
                true);
        ts.setTexture(t1, 0);

        Texture t2 = TextureManager.loadTexture(ClassLoader.getSystemResource(
                "jmetest/data/texture/Detail.jpg"),
                                                Texture.MM_LINEAR_LINEAR,
                                                Texture.FM_LINEAR);
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

        BaseModelObject v2 = BModelFactory.getInstance().createModelTest("data/buildings/", "data/buildings/house.jme");
        v2.setLocalTranslation(new Vector3f(0, 250, -20));
        NodeRotator.rotate(v2, BaseModelObject.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.PITCH, (float) Math.toRadians(90));
        v2.updateWorldBound();

        rootNode.attachChild(v2);
        ps.create(rootNode, new Vector3f(0, 250, -15));
        pub.create(rootNode, new Vector3f(0, 250, -10));

        FogState fs = display.getRenderer().createFogState();
        fs.setDensity(0.5f);
        fs.setEnabled(true);
        fs.setColor(new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
        fs.setEnd(1000);
        fs.setStart(500);
        fs.setDensityFunction(FogState.DF_LINEAR);
        fs.setApplyFunction(FogState.AF_PER_VERTEX);
        scene.setRenderState(fs);
        scene.setRenderState(ts);
        rootNode.attachChild(scene);
    }
}
