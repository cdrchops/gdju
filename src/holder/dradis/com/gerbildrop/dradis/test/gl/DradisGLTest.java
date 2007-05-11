package com.gerbildrop.dradis.test.gl;

import com.gerbildrop.dradis.displays.dradisGL.NdArc;
import com.jme.app.SimpleGame;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.math.FastMath;
import com.jme.scene.shape.Box;

public class DradisGLTest extends SimpleGame {
    private NdArc arc2 = new NdArc(640, 480);

    protected void simpleInitGame() {
        rootNode.attachChild(arc2);

        Box box = new Box("The Box", new Vector3f(-1, -1, -1), new Vector3f(3f, 3f, 3f));

        Quaternion rot = new Quaternion();
        rot.fromAngles(FastMath.DEG_TO_RAD * 25, FastMath.DEG_TO_RAD * 25, 0.0f);

        box.setLocalRotation(rot);

        rootNode.attachChild(box);
    }

    public static void main(String[] args) {
        DradisGLTest app = new DradisGLTest();
        app.setDialogBehaviour(FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }


}