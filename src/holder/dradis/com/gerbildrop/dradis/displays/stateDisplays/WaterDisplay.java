package com.gerbildrop.dradis.displays.stateDisplays;

import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.gerbildrop.engine.displays.Displays;
import com.gerbildrop.dradis.resources.DradisResources;
import com.gerbildrop.engine.font.DradisText;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.engine.util.NodeTranslator;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.state.LightState;
import com.jmex.game.StandardGame;

public class WaterDisplay implements Displays {
    public void init(final StandardGame game, final Node rootNode) {
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.setCullMode(SceneElement.CULL_NEVER);

        final DradisDisplayBase tg = new DradisDisplayBase();
        tg.init("clockNode", DradisResources.DradisBackground, .5f);
        rootNode.attachChild(tg.getHudQuad());

        final DradisDisplayBase tg2 = new DradisDisplayBase();
        tg2.init("TG2", WaterDisplay.class.getResource("/dradis/displays/water/waterDisplay.png"), .5f);
        rootNode.attachChild(tg2.getHudQuad());

        NodeTranslator.translate(tg2.getHudQuad(), BaseModelObject.YAW, 40);
        NodeTranslator.translate(tg2.getHudQuad(), BaseModelObject.PITCH, 25);

        final DradisDisplayBase dcc = new DradisDisplayBase();
        dcc.init("dradisWater", DradisResources.dradisWater, .65f, 1, new Vector3f(220, 165, 0));
        rootNode.attachChild(dcc.getHudQuad());

        DradisText text = new DradisText("text");
        rootNode.attachChild(text.print("PERCENT AVAILABLE"));
        text.translate(435, 262);

        DradisText text2 = new DradisText("text2");
        rootNode.attachChild(text2.print("CURRENT TRANSFER FLOW", .75f));
        text2.translate(240, 390);

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    public void update(final float time, final Node rootNode) {
    }
}
