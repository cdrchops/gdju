package com.gerbildrop.dradis.fonts;

import java.awt.*;

import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;

/**
 * <code>TestTruetypeFont</code>
 *
 * @author Jeremy Adams
 */
public class TTFTest extends SimpleGame {

    public static void main(String[] args) {
        TTFTest app = new TTFTest();
        app.setDialogBehaviour(ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }

    protected void simpleInitGame() {
        display.setTitle("A Truetype Font Test");

        Font font = new Font("Serif", Font.PLAIN, 32);

        long tstarttime = System.currentTimeMillis();
        TTFont text = new TTFont(font.deriveFont(Font.PLAIN), 64, 0);
        long tendtime = System.currentTimeMillis() - tstarttime;

        System.out.println("Finished loading fonts in " + tendtime);
        Node playertext = text.createText("Look at me!", 2, new ColorRGBA(1, 1, 0.1f, 1f), true);
        playertext.setLocalTranslation(new Vector3f(200, 200, 0));
        playertext.getLocalScale().set(16, 16, 1);
        rootNode.setCullMode(Node.CULL_NEVER);
        playertext.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        playertext.setModelBound(new BoundingBox());
        playertext.updateModelBound();
        playertext.updateWorldBound();


        rootNode.attachChild(playertext);
    }
}