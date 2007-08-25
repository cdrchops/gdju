package com.gerbildrop.mask.tests.measurements;

import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingSphere;
import com.jme.input.FirstPersonHandler;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jme.scene.Text;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.LightState;

public class PickGame extends SimpleGame {
    // This will be my mouse
    AbsMouse am = new AbsMouse();

    // This will be the box in the middle
    Box b;

    float dS1 = .3f;

    float dS2 = -.3f;

    float velocity = .02f;
    float velocityY = .05f;

    Spatial s1;

    Spatial s2;

    Text text;
    Text textBlocks;

    Distance d = new Distance();

    protected void simpleInitGame() {
        // Create a new mouse. Restrict its movements to the display screen.
        am.initMouse(rootNode, input);

        // Create scene
        initScene();

        // Remove all the lightstates so we can see the per-vertex colors
        lightState.detachAll();

        ((FirstPersonHandler) input).getMouseLookHandler().setEnabled(false);

        initText();
    }


    protected void simpleUpdate() {
        am.update(rootNode, textBlocks);

        updateSphereMovement();

        updateSphereDistance();
    }

    protected void updateSphereMovement() {
        Vector3f s1Loc = s1.getLocalTranslation();
        Vector3f s2Loc = s2.getLocalTranslation();

        //System.out.println("s1Loc="+s1Loc + "dS1="+dS1 +"\n");
        //System.out.println("s2Loc="+s2Loc + "dS2="+dS2 +"\n");
        //update position
        s1Loc.x += dS1 * velocity;
        s1Loc.y += dS1 * velocityY;

        s2Loc.x += dS2 * velocity;
        s2Loc.y += dS2 * velocityY;

        if (s1Loc.x > -2) {
            s1Loc.x = -2;
            //switch directions;
            dS1 *= -1;
        } else if (s1Loc.x < -20) {
            s1Loc.x = -20;
            //switch directions;
            dS1 *= -1;
        }
        if (s2Loc.x > 20) {
            s2Loc.x = 20;
            //switch directions;
            dS2 *= -1;
        } else if (s2Loc.x < 2) {
            s2Loc.x = 2;
            //switch directions;
            dS2 *= -1;
        }

    }


    protected void updateSphereDistance() {
        float distance = d.getDistance(s1, s2);
        text.print("Distance between s1 and s2 = " + distance);
    }

    protected void initText() {
        text = new Text("LabelText", "Distance=");
        textBlocks = new Text("BlockText", "Distance=");
        text.setLocalTranslation(new Vector3f(1, 60, 0));
        textBlocks.setLocalTranslation(new Vector3f(1, 30, 0));
        fpsNode.attachChild(text);
        fpsNode.attachChild(textBlocks);
    }

    protected void initScene() {
        // Create the box in the middle. Give it a bounds
        b = new Box("My Box NEAR", new Vector3f(-1, -1, -1), new Vector3f(1, 1, 1));
        //b.setLocalScale(4);
        b.setModelBound(new BoundingSphere());
        b.updateModelBound();
        b.setLightCombineMode(LightState.OFF);
        rootNode.attachChild(b);

        //Create second box over 15 units away
        b = new Box("My Box FAR", new Vector3f(-1, -1, -1), new Vector3f(1, 1, 1));
        //b.setLocalScale(4);
        b.setModelBound(new BoundingSphere());
        b.updateModelBound();
        b.setLightCombineMode(LightState.OFF);
        b.setLocalTranslation(new Vector3f(30, 0, -100));
        rootNode.attachChild(b);

        //create sphere 1
        s1 = new Sphere("s1", 16, 16, 1);
        s2 = new Sphere("s2", 16, 16, 1);

        s1.setModelBound(new BoundingSphere());
        s1.updateModelBound();
        s1.setLightCombineMode(LightState.OFF);
        s1.setLocalTranslation(new Vector3f(-5, 10, -25));
        rootNode.attachChild(s1);

        s2.setModelBound(new BoundingSphere());
        s2.updateModelBound();
        s2.setLightCombineMode(LightState.OFF);
        //set this one back z direction to provide a more thorough example of distance calculation
        s2.setLocalTranslation(new Vector3f(5, 10, -40));

        rootNode.attachChild(s2);

        d.init(rootNode, s1, s2);

//        System.out.println("INIT ______ s1Loc=" + s1.getLocalTranslation()
//                           + "dS1=" + dS1 + "\n");

    }

//    public float distanceTo(Entity entity) {
//        float centerDistance = getPosition().distance(entity.getPosition());
//        BoundingVolume myBound = getSpatial().getWorldBound();
//        BoundingVolume tBound = entity.getSpatial().getWorldBound();
//        float myDistanceFromEdge = myBound.distanceToEdge(tBound.getCenter());
//        float tDistanceFromEdge = tBound.distanceToEdge(myBound.getCenter());
//        return centerDistance-(centerDistance-myDistanceFromEdge)-(centerDistance-tDistanceFromEdge);
//    }
}