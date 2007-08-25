package com.gerbildrop.engine.math;

import java.nio.FloatBuffer;

import com.jme.intersection.IntersectionRecord;
import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.scene.Line;
import com.jme.scene.Spatial;
import com.jme.util.geom.BufferUtils;

public class Measurement {
    Line l;

    public Vector3f getDirection(Spatial s1) {
        Ray ray = new Ray();
        ray.setOrigin(s1.getLocalTranslation());

        return null;
    }

    public float getDistance(Spatial s1, Spatial s2) {

        Vector3f s1Coords = (Vector3f) s1.getWorldTranslation().clone();

        Vector3f s2Coords = (Vector3f) s2.getWorldTranslation().clone();

        Ray s1Tos2 = new Ray(s1Coords, s2Coords.subtractLocal(s1Coords).normalizeLocal());

        //Get s2's surface position of model bound
        IntersectionRecord rayToS2 = s2.getWorldBound().intersectsWhere(s1Tos2);

        Vector3f s2SurfacePos = rayToS2.getIntersectionPoint(rayToS2.getFarthestPoint());

        //Get s1's surface position
        IntersectionRecord rayToS1 = s1.getWorldBound().intersectsWhere(s1Tos2);

        Vector3f s1SurfacePos = rayToS1.getIntersectionPoint(rayToS1.getFarthestPoint());

        //Draw line between the two objects
        FloatBuffer lineBuf = BufferUtils.createFloatBuffer(s2SurfacePos, s1SurfacePos);

        l.setVertexBuffer(0, lineBuf);

        return s1SurfacePos.distance(s2SurfacePos);
    }
}
