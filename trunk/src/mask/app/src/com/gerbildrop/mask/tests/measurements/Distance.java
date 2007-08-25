package com.gerbildrop.mask.tests.measurements;

import java.nio.FloatBuffer;

import com.jme.intersection.IntersectionRecord;
import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Line;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.state.LightState;
import com.jme.util.geom.BufferUtils;

public class Distance {
    Line l;

    public void init(Node rootNode, Spatial s1, Spatial s2) {
        Vector3f[] lineVertices = new Vector3f[]{
                new Vector3f(s1.getLocalTranslation().x, s1
                        .getLocalTranslation().y, s1.getLocalTranslation().z),
                new Vector3f(s2.getLocalTranslation().x, s2
                        .getLocalTranslation().y, s2.getLocalTranslation().z)

        };

        ColorRGBA[] colorVertices = new ColorRGBA[]{ColorRGBA.red,
                                                    ColorRGBA.green};

        //Line between two sphere representing the distance
        l = new Line("S1->S2", lineVertices, null, colorVertices, null);
        l.setLightCombineMode(LightState.OFF);
        rootNode.attachChild(l);

    }

    /**
     * Gets the distance between two model bounded spatials.
     *
     * @param s1
     * @param s2
     */
    public float getDistance(Spatial s1, Spatial s2) {
        Vector3f s1Coords = (Vector3f) s1.getWorldTranslation().clone();
        Vector3f s2Coords = (Vector3f) s2.getWorldTranslation().clone();
        Ray s1Tos2 = new Ray(s1Coords, s2Coords.subtractLocal(s1Coords)
                .normalizeLocal());

        //Get s2's surface position of model bound
        IntersectionRecord rayToS2 = s2.getWorldBound().intersectsWhere(s1Tos2);
        Vector3f s2SurfacePos = rayToS2.getIntersectionPoint(rayToS2
                .getFarthestPoint());

        //Get s1's surface position
        IntersectionRecord rayToS1 = s1.getWorldBound().intersectsWhere(s1Tos2);
        Vector3f s1SurfacePos = rayToS1.getIntersectionPoint(rayToS1
                .getFarthestPoint());

        //Draw line between the two objects
        FloatBuffer lineBuf = BufferUtils.createFloatBuffer(new Vector3f[]{
                s2SurfacePos, s1SurfacePos});
        l.setVertexBuffer(0, lineBuf);

        return s1SurfacePos.distance(s2SurfacePos);
    }
}
