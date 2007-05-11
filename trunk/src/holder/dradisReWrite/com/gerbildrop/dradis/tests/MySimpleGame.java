package com.gerbildrop.dradis.tests;

import com.gerbildrop.dradis.model.BModelFactory;
import com.gerbildrop.dradis.ships.BaseModelObject;
import com.jme.app.SimpleGame;
import com.jme.math.Vector3f;

public class MySimpleGame extends SimpleGame {
    private static final String TEXTURE_PATH = "dradis/models/";
    protected void simpleInitGame() {
        BaseModelObject galactica = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/galactica.jme");

        galactica.getRootNode().setLocalTranslation(new Vector3f(-20, 1.25f, -10));

        rootNode.attachChild(galactica.getRootNode());
    }

    public static void main(String[] args) {
        MySimpleGame app = new MySimpleGame();
        app.setDialogBehaviour(FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }
}
