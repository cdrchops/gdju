package com.gerbildrop.mask.tests;

import javax.swing.ImageIcon;

import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.SharedMesh;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Pyramid;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import com.jmex.terrain.TerrainBlock;
import com.jmex.terrain.util.MidPointHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;

/**
 * <code>TestTerrain</code>
 *
 * @author Mark Powell
 * @version $Id: TestTerrainTrees.java,v 1.2 2007/04/18 21:53:29 vivaldi Exp $
 */
public class TestTerrainTrees extends SimpleGame {

    /**
     * Entry point for the test,
     *
     * @param args
     */
    public static void main(String[] args) {
        TestTerrainTrees app = new TestTerrainTrees();
        app.setDialogBehaviour(ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }

    /**
     * builds the trimesh.
     *
     * @see com.jme.app.SimpleGame#initGame()
     */
    protected void simpleInitGame() {
        rootNode.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        fpsNode.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        display.setTitle("Terrain Test");
        cam.setLocation(new Vector3f(64 * 5, 250, 64 * 5));
        cam.setFrustumFar(2000);
        cam.update();

        FogState fs = display.getRenderer().createFogState();
        fs.setEnabled(false);
        rootNode.setRenderState(fs);

        CullState cs = display.getRenderer().createCullState();
        cs.setCullMode(CullState.CS_BACK);
        cs.setEnabled(true);

        lightState.setTwoSidedLighting(true);

        MidPointHeightMap heightMap = new MidPointHeightMap(128, 1.9f);
        Vector3f terrainScale = new Vector3f(5, 1, 5);
        TerrainBlock tb = new TerrainBlock("Terrain", heightMap.getSize(),
                                           terrainScale, heightMap.getHeightMap(), new Vector3f(0, 0, 0),
                                           false);
        tb.setDetailTexture(1, 4);
        tb.setModelBound(new BoundingBox());
        tb.updateModelBound();
        tb.setLocalTranslation(new Vector3f(0, 0, 0));
        rootNode.attachChild(tb);
        rootNode.setRenderState(cs);

        ProceduralTextureGenerator pt = new ProceduralTextureGenerator(
                heightMap);
        pt.addTexture(new ImageIcon(TestTerrainTrees.class
                .getResource("/jmetest/data/texture/grassb.png")), -128, 0, 128);
        pt.addTexture(new ImageIcon(TestTerrainTrees.class.
                getResource("/jmetest/data/texture/dirt.jpg")), 0, 128, 255);
        pt.addTexture(new ImageIcon(TestTerrainTrees.class.
                getResource("/jmetest/data/texture/highest.jpg")), 128, 255,
                                                                   384);

        pt.createTexture(512);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        Texture t1 = TextureManager.loadTexture(pt.getImageIcon().getImage(),
                                                Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR, true);
        ts.setTexture(t1, 0);

        Texture t2 = TextureManager.loadTexture(TestTerrainTrees.class
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

        TextureState treeTex = display.getRenderer().createTextureState();
        treeTex.setEnabled(true);
        Texture tr = TextureManager.loadTexture(
                TestTerrainTrees.class.getClassLoader().getResource(
                        "jmetest/data/texture/grass.jpg"), Texture.MM_LINEAR_LINEAR,
                                                           Texture.FM_LINEAR);
        treeTex.setTexture(tr);

        Pyramid p = new Pyramid("Pyramid", 10, 20);
        p.setModelBound(new BoundingBox());
        p.updateModelBound();
        p.setRenderState(treeTex);
        p.setTextureCombineMode(TextureState.REPLACE);

        for (int i = 0; i < 500; i++) {
            Spatial s1 = new SharedMesh("tree" + i, p);
            float x = (float) Math.random() * 128 * 5;
            float z = (float) Math.random() * 128 * 5;
            s1.setLocalTranslation(new Vector3f(x, tb.getHeight(x, z) + 10, z));
            rootNode.attachChild(s1);
        }

        rootNode.lock();
    }
}
