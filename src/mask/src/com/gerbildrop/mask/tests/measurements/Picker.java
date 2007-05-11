package com.gerbildrop.mask.tests.measurements;

import com.jme.input.AbsoluteMouse;
import com.jme.intersection.BoundingPickResults;
import com.jme.intersection.PickData;
import com.jme.intersection.PickResults;
import com.jme.math.Ray;
import com.jme.math.Vector2f;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Text;
import com.jme.system.DisplaySystem;

public class Picker {
    PickResults pr = null;

    public void doMousePick(AbsMouse abs, DisplaySystem display, Node rootNode, Text textBlocks) {
        if (pr == null) {
            pr = new BoundingPickResults();
            pr.setCheckDistance(true);
        }
        AbsoluteMouse am = abs.getAm();
        Vector2f screenPos = new Vector2f();
        // Get the position that the mouse is pointing to
        screenPos.set(am.getHotSpotPosition().x, am.getHotSpotPosition().y);
        // Get the world location of that X,Y value
        Vector3f worldCoords = display.getWorldCoordinates(screenPos, 0);
        Vector3f worldCoords2 = display.getWorldCoordinates(screenPos, 1);
//        System.out.println("w0 = " + worldCoords);
//        System.out.println("w1 = " + worldCoords2);
//        System.out.println("w1 - w0 = "
//                           + worldCoords2.subtractLocal(worldCoords));
        // Create a ray starting from the camera, and going in the direction
        // of the mouse's location
        Ray mouseRay = new Ray(worldCoords, worldCoords2.subtractLocal(
                worldCoords).normalizeLocal());
        // Does the mouse's ray intersect the box's world bounds?
        pr.clear();
        rootNode.findPick(mouseRay, pr);
        for (int i = 0; i < pr.getNumber(); i++) {
            PickData pd = pr.getPickData(i);
            float distance;
            String nodeParentName = pd.getTargetMesh().getParentGeom().getName();

            distance = pd.getDistance();
            if (distance >= 100) {
                textBlocks.print("Node[" + nodeParentName + "] : DISTANCE(" + distance + ") >= 100 PLAY LOW SOUND");
            } else {
                textBlocks.print("Node[" + nodeParentName + "] : DISTANCE(" + distance + ") < 100 PLAY HIGH SOUND");
            }

        }
    }

}
