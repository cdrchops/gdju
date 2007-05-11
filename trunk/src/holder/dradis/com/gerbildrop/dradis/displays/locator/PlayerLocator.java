package com.gerbildrop.dradis.displays.locator;

import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.LightState;
import com.jme.scene.Node;
import com.gerbildrop.dradis.displays.base.DradisDisplayBase;

public class PlayerLocator {
    private Node playerLocatorNode;
    public PlayerLocator() {
        playerLocatorNode = new Node("playerLocator");
    }

    public void init() {
        DradisDisplayBase db = new DradisDisplayBase();
        db.init("playerLocator", PlayerLocator.class.getResource("/dradis/displays/HUDS/radar.png"), .5f, 1, new Vector3f(275 / 2, 266 / 2, 0));
    }
    public void update(String name, Vector3f updatedLocation, float time) {
        Quad hq = (Quad) playerLocatorNode.getChild(name);
        final Vector3f loca = new Vector3f(275 / 2, 0, 266 / 2);
        Vector3f newLoc = new Vector3f(loca.add(updatedLocation));
        Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);

        hq.setLocalTranslation(displayLocation);

        playerLocatorNode.updateWorldData(time);
    }

    public static enum NubbinType {BROWN, GRAY, RED, YELLOW, WHITE, BLUE}

    /**
     * brown = raptor grey = galactica or pegasus red = raider, basestar or heavy raider yellow or white = civillian
     * ships blue = player
     */
    public void createNubbin(NubbinType nt, String name, Vector3f location) {
        Quad hudQuad1 = new Quad(name, 4, 4);
        hudQuad1.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        final Vector3f loca = new Vector3f(275 / 2, 0, 266 / 2);
        Vector3f newLoc = new Vector3f(loca.add(location));
        Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);

        hudQuad1.setLocalTranslation(displayLocation);
        setColor(hudQuad1, nt);
        hudQuad1.setLightCombineMode(LightState.OFF);
        hudQuad1.updateRenderState();

        playerLocatorNode.attachChild(hudQuad1);
    }


    private void setColor(Quad hudQuad, NubbinType nt) {
        ColorRGBA cr;

        switch (nt) {
            case BLUE:
                cr = ColorRGBA.blue;
                break;
            case BROWN:
                cr = ColorRGBA.brown;
                break;
            case GRAY:
                cr = ColorRGBA.gray;
                break;
            case RED:
                cr = ColorRGBA.red;
                break;
            case YELLOW:
                cr = ColorRGBA.yellow;
                break;
            case WHITE:
                cr = ColorRGBA.white;
                break;
            default:
                cr = ColorRGBA.yellow;

        }

        hudQuad.setDefaultColor(cr);
    }
}