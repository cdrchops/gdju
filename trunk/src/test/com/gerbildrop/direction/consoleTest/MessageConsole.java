package com.gerbildrop.direction.consoleTest;

import com.jme.app.SimpleGame;
import com.gerbildrop.dradis.displays.base.DradisDisplayBase;

public class MessageConsole extends SimpleGame {
    protected void simpleInitGame() {
        DradisDisplayBase db = new DradisDisplayBase();
        db.init("background", Direction.class.getResource("/dradis/displays/backgrounds/MessageFrame.png"), 1f);
        db.getHudQuad().setLocalTranslation(300, 300, 0);

        rootNode.attachChild(db.getHudQuad());

        TextField entry = new TextField("Console Entry", "", 123.0f, 368);
        TextualCursor cursor = new TextualCursor("Cursor", 113.0f, 368, entry, true);

        rootNode.attachChild(entry.getText());
        rootNode.attachChild(cursor.getText());

        rootNode.updateRenderState();
    }

    public static void main(String[] args) {
        Direction app = new Direction();
        app.setDialogBehaviour(NEVER_SHOW_PROPS_DIALOG);
        app.start();
    }
}
