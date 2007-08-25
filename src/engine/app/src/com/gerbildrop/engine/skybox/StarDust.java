package com.gerbildrop.engine.skybox;

/*
 * Created on Oct 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.security.SecureRandom;
import java.util.Random;

import com.jme.bounding.BoundingBox;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.Point;
import com.jme.scene.state.LightState;
import com.jme.scene.state.MaterialState;
import com.jme.system.DisplaySystem;

/**
 * @author zen
 *         <p/>
 *         TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style -
 *         Code Templates
 */
public class StarDust {

    public static final int numStars = 30;
    public static final int blockSize = 50;
    Point p[][][] = new Point[3][3][3];
    int old_sec_x = 0;
    int old_sec_y = 0;
    int old_sec_z = 0;
    public Node node;

    public void init(Node rootNode) {
        node = new Node("stardust");
        //
        // A star field
        //
        // in this first edition, just use the standard 'point' class
        // but in future would like to have a custom drawn one - where intensity
        // is related to distance?
        Random r = new SecureRandom();

        Vector3f[] vertexes = new Vector3f[numStars];
        for (int x = 0; x < numStars; ++x) {
            vertexes[x] = new Vector3f((r.nextFloat()) * blockSize,
                                       (r.nextFloat()) * blockSize,
                                       (r.nextFloat()) * blockSize);
        }

        // all dust particles are white
        MaterialState ms = DisplaySystem.getDisplaySystem().getRenderer().createMaterialState();
        ms.setEmissive(ColorRGBA.white);
        ms.setDiffuse(ColorRGBA.white);
        ms.setEnabled(true);


        for (int k = 0; k < 3; ++k) {
            for (int j = 0; j < 3; ++j) {
                for (int i = 0; i < 3; ++i) {
                    p[i][j][k] = new Point("stardust " + i + "" + j + "" + k, vertexes, null, null, null);
                    p[i][j][k].setLocalTranslation(
                            new Vector3f((i - 1) * blockSize, (j - 1) * blockSize, (k - 1) * blockSize));
                    p[i][j][k].setModelBound(new BoundingBox());
                    p[i][j][k].updateModelBound();
                    node.attachChild(p[i][j][k]);
                }
            }
        }

        // We don't want the light to affect our dust
        LightState lightState = DisplaySystem.getDisplaySystem().getRenderer().createLightState();
        lightState.setEnabled(false);
        node.setRenderState(lightState);
        node.setLightCombineMode(LightState.REPLACE);
        node.updateWorldBound();

        rootNode.attachChild(node);
        rootNode.updateWorldBound();
        rootNode.updateRenderState();
    }

    // ensure the viewer is surrounded by stars!
    public void update(Vector3f viewer) {
        // note: funny things happen when scaling things about the origin,
        // so for our purposes we compensate. (we could have used -0.5..0.5)
        // what we want is: -1000..0 -> -1
        //                  0..1000  -> 0
        //                  1000..2000 -> 1
        int sec_x = (int) ((viewer.x / blockSize) + ((viewer.x > 0) ? 0 : -1));
        int sec_y = (int) ((viewer.y / blockSize) + ((viewer.y > 0) ? 0 : -1));
        int sec_z = (int) ((viewer.z / blockSize) + ((viewer.z > 0) ? 0 : -1));

        // reduce garbage collection...
        if (sec_x != old_sec_x || sec_y != old_sec_y || sec_z != old_sec_z) {
            node.setLocalTranslation(new Vector3f(sec_x * blockSize, sec_y * blockSize, sec_z * blockSize));
            old_sec_x = sec_x;
            old_sec_y = sec_y;
            old_sec_z = sec_z;
        }

    }

}