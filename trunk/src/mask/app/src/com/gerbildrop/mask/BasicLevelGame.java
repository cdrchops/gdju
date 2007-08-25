package com.gerbildrop.mask;

import com.gerbildrop.mask.models.Boxer;
import com.gerbildrop.mask.powerUps.PowerUpBox;
import com.gerbildrop.mask.powerUps.PowerUpSphere;
import com.gerbildrop.mask.tests.measurements.Distance;
import com.gerbildrop.mask.vision.InfraRedVision;
import com.gerbildrop.mask.vision.LifterVisionAction;
import com.gerbildrop.mask.vision.NightVision;
import com.gerbildrop.mask.vision.NightVisionAction;
import com.gerbildrop.mask.vision.TransparentAction;
import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.scene.Text;

/**
 * this is my basic proof of concept for a MASK game the idea is that I want a few different things to play with
 * <p/>
 * DONE -- 14Dec06 there is a house with a sphere inside
 * <p/>
 * DONE -- 14Dec06 outside there is a box with a sphere in it and two regular boxes
 * <p/>
 * DONE -- 14Dec06 when you press M it turns on your infrared (which is x ray for this purpose)
 * <p/>
 * DONE -- 14Dec06 and you can view inside the house... it turns everything red
 * <p/>
 * the next thing to do is add a lifter function when an item is in view, pressing a key will lift all liftable objects
 * into the air never moving them higher than your view point where you move the mouse to view then release the button
 * slowly lowers the objects once the objects are back on the ground nature takes over and they either rest on the
 * ground or topple (if they're on a cliff or something)
 * <p/>
 * once that is all done this proof of concept will be done and I can move on to other aspects of the game
 */
public class BasicLevelGame extends SimpleGame {
    public static boolean nvg = false;
    private NightVision nv = null;
    //    private House house = new House();
    public static boolean irg = false;
    private InfraRedVision ir = null;
    private Boxer b1 = new Boxer();
    private Boxer b2 = new Boxer();
    Distance d = new Distance();

    //    AbsMouse abs = new AbsMouse();
    Text text;
    Text textBlocks;

    private float x = 40;
    private float y = 1;
    private float z = 5;
    private long startTime = 0;

    MaskTerrain mt = new MaskTerrain();

    protected void simpleInitGame() {

        cam.setLocation(new Vector3f(0, 10, 0));
        cam.update();

//        abs.initMouse(rootNode, input);
        mt.createTerrain(rootNode);

//        house.create(rootNode);

        new PowerUpBox().create(rootNode, new Vector3f(20, 1, 0));
        new PowerUpSphere().create(rootNode, new Vector3f(0, 1, 0));

        b1.create(rootNode, new Vector3f(40, 1, 5), true);
        b2.create(rootNode, new Vector3f(30, 1, 5), false);

        initInput();

        initText();
    }

    protected void simpleUpdate() {
        super.simpleUpdate();

        if (nvg && nv == null) {
            nv = new NightVision();
            nv.create(rootNode);
        } else if (!nvg && nv != null) {
            nv = null;
            rootNode.detachChildNamed("nightVision");
        }

        if (irg && ir == null) {
            ir = new InfraRedVision();
            ir.create(rootNode);
        } else if (!irg && ir != null) {
            ir = null;
            rootNode.detachChildNamed("irVision");
        }

        checkLifter();
//        abs.update(rootNode, textBlocks);
//        house.changeTransparency();
    }

    /**
     * objective is to lift only an object in the view. selectable object would be nice... but for now we only have one
     * object if that object is in view, then lift it when the view moves, move the object when lowering the object, it
     * should lower to wherever the view is.
     * <p/>
     * this lifts and lowers the object todo: work this so that the object moves with the camera... so the x, y and z
     * will change
     * <p/>
     * I do exactly that in my (unreleased) system. I use a tool-based approach. The tool is connected to the
     * mouseInputListener, mouse events are routed to the tool object. The tool can pick an object. It remembers the
     * current distance of the camera to the object. When the camera is moved (by mouse), the tool gets a new camera
     * pick ray in absolute space. The tool calculates the point on this ray with the saved distance to the camera, and
     * sets the position of the picked object to this position.
     */
    private void checkLifter() {
        boolean update = false;
        Ray ray = new Ray(cam.getLocation(), cam.getDirection());

        b1.getStaticBoxNode2().setModelBound(new BoundingBox());

        b1.getStaticBoxNode2().updateModelBound();
//        System.out.println("distance " + cam.getLocation().subtract(b1.getStaticBoxNode2().getLocalTranslation()));
//        if (b1.getStaticBoxNode2().getWorldBound().intersects(ray)) {
        if (KeyBindingManager.getKeyBindingManager().isValidCommand("lifterup")
            && (startTime == 0 || System.currentTimeMillis() - startTime >= 300)) {
            startTime = System.currentTimeMillis();
            if (y < 5) {
                y++;
            }

            update = true;
            //this next code is just to figure out where we are in lifting and lowering
            // an object in view
            // todo: when the camera moves to a different direction, we need to
            //       add that direction to our box to move it with the camera
            Vector3f v1 = new Vector3f(cam.getDirection());
            Vector3f v2 = new Vector3f(b1.getStaticBoxNode2().getLocalTranslation());
            Vector3f v3 = v1.add(v2);

//                System.out.println("v1 = " + v1);
//                System.out.println("v2 = " + v2);
//                System.out.println("v3 " + v3);

            //determines if we're in the vicinity
/*                if (((v3.x == v2.x)
                      || (v3.x + 2 >= v2.x)
                      || (v3.x - 2 <= v2.x))
                    || ((v3.y == v2.y)
                        || (v3.y + 2 >= v2.y)
                        || (v3.y - 2 <= v2.y))
                    || ((v3.z == v2.z)
                        || (v3.z + 2 >= v2.z)
                        || (v3.z - 2 <= v2.z))    ) {
                    System.out.println("yeah baby");
                }*/
            x = v1.x + 1;
            z = v1.z;
        } else if (KeyBindingManager.getKeyBindingManager().isValidCommand("lifterdown")
                   && (startTime == 0 || System.currentTimeMillis() - startTime >= 300)
                   && y > 1) {
            startTime = System.currentTimeMillis();
            update = true;
            y--;
        }

        if (update) {
            b1.setY(y);
            b1.setX(b1.getX() + x);
//                b1.setZ(b1.getZ() + z);

            b1.getStaticBoxNode2().setLocalTranslation(new Vector3f(b1.getX(), b1.getY(), b1.getZ()));
        }
//        }
    }

    protected void initText() {
        text = new Text("LabelText", "Distance=");
        textBlocks = new Text("BlockText", "Distance=");
        text.setLocalTranslation(new Vector3f(1, 60, 0));
        textBlocks.setLocalTranslation(new Vector3f(1, 30, 0));
        fpsNode.attachChild(text);
        fpsNode.attachChild(textBlocks);
    }

    protected void initInput() {
        KeyBindingManager.getKeyBindingManager().set("nvg", KeyInput.KEY_N);
        input.addAction(new NightVisionAction(), "nvg", false);

        KeyBindingManager.getKeyBindingManager().set("lifterup", KeyInput.KEY_V);
        input.addAction(new LifterVisionAction(), "lifterup", false);

        KeyBindingManager.getKeyBindingManager().set("lifterdown", KeyInput.KEY_K);
        input.addAction(new LifterVisionAction(), "lifterdown", false);

        KeyBindingManager.getKeyBindingManager().set("trans", KeyInput.KEY_M);
        input.addAction(new TransparentAction(), "trans", false);
    }
}