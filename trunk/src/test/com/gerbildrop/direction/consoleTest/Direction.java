package com.gerbildrop.direction.consoleTest;

import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.jme.app.SimpleGame;

public class Direction extends SimpleGame  {
    protected void simpleInitGame() {

//        GameConsole gc = new GameConsole(KeyInput.KEY_GRAVE, 5, true, new float[]{0, 20, 0, 0}, new Vector2f(0, 0), new Vector2f(0, (((5 + 1) * 14.0f) + 10.0f + 5)), DisplaySystem.getDisplaySystem().getWidth());


        DradisDisplayBase db = new DradisDisplayBase();
        db.init("background", Direction.class.getResource("/dradis/displays/backgrounds/loginMessage.png"), 1f);
        db.getHudQuad().setLocalTranslation(300, 300, 0);

        rootNode.attachChild(db.getHudQuad());

        TextField entry = new TextField("Console Entry", "", 123.0f, 368);
        TextualCursor cursor = new TextualCursor("Cursor", 113.0f, 368, entry, true);

        rootNode.attachChild(entry.getText());
        rootNode.attachChild(cursor.getText());

        TextField entry2 = new TextField("Console Entry", "", 123.0f, 320);
        TextualCursor cursor2 = new TextualCursor("Cursor", 113.0f, 320, entry, false);

        rootNode.attachChild(entry2.getText());
        rootNode.attachChild(cursor2.getText());

        rootNode.updateRenderState();
    }

    public static void main(String[] args) {
        Direction app = new Direction();
        app.setDialogBehaviour(NEVER_SHOW_PROPS_DIALOG);
        app.start();
    }
}