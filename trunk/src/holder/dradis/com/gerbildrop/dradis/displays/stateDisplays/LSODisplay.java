package com.gerbildrop.dradis.displays.stateDisplays;

import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.gerbildrop.engine.displays.Displays;
import com.gerbildrop.dradis.resources.DradisResources;
import com.gerbildrop.engine.util.NodeTranslator;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.state.LightState;
import com.jme.renderer.Renderer;
import com.jmex.game.StandardGame;

public class LSODisplay implements Displays {

    public void init(final StandardGame game, final Node rootNode) {
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.setCullMode(SceneElement.CULL_NEVER);

        final DradisDisplayBase tg = new DradisDisplayBase();
        tg.init("clockNode", DradisResources.DradisBackground, .5f);
        rootNode.attachChild(tg.getHudQuad());

        load(rootNode);

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    public void update(final float time, final Node rootNode) {
    }

    private DradisDisplayBase lsoOverLayBottom = new DradisDisplayBase();
    private DradisDisplayBase viperSideView = new DradisDisplayBase();
    private DradisDisplayBase viperTopView = new DradisDisplayBase();
    private DradisDisplayBase dradisLSOGrid = new DradisDisplayBase();
    private DradisDisplayBase dradisLSOViperIconSide = new DradisDisplayBase();
    private DradisDisplayBase dradisLSOViperIconTop = new DradisDisplayBase();

    public void load(Node rootNode) {
        lsoOverLayBottom.init("lsoOverlayBottom", LSODisplay.class.getResource("/dradis/displays/LSO/dradisLSOBottom.png"), .5f);
        rootNode.attachChild(lsoOverLayBottom.getHudQuad());

        NodeTranslator.translate(lsoOverLayBottom.getHudQuad(), BaseModelObject.YAW, 50);
        NodeTranslator.translate(lsoOverLayBottom.getHudQuad(), BaseModelObject.PITCH, 30);

        viperSideView.init("viperSideView", LSODisplay.class.getResource("/dradis/displays/LSO/lsoSide.png"), .75f);
        rootNode.attachChild(viperSideView.getHudQuad());

        NodeTranslator.translate(viperSideView.getHudQuad(), BaseModelObject.YAW, 60);
        NodeTranslator.translate(viperSideView.getHudQuad(), BaseModelObject.PITCH, 105);

        viperTopView.init("viperTopView", LSODisplay.class.getResource("/dradis/displays/LSO/lsoTop.png"), .7f);
        rootNode.attachChild(viperTopView.getHudQuad());

        NodeTranslator.translate(viperTopView.getHudQuad(), BaseModelObject.YAW, 58);
        NodeTranslator.translate(viperTopView.getHudQuad(), BaseModelObject.PITCH, 475);

        dradisLSOGrid.init("dradisLSOGrid", LSODisplay.class.getResource("/dradis/displays/LSO/dradisLSOGrid.png"), .5f);
        rootNode.attachChild(dradisLSOGrid.getHudQuad());

        NodeTranslator.translate(dradisLSOGrid.getHudQuad(), BaseModelObject.YAW, 110);
        NodeTranslator.translate(dradisLSOGrid.getHudQuad(), BaseModelObject.PITCH, 30);

        dradisLSOViperIconSide.init("dradisLSOViperIconSideGrid", LSODisplay.class.getResource("/dradis/displays/LSO/viperGlideSlopeIcon.png"), .75f);
        rootNode.attachChild(dradisLSOViperIconSide.getHudQuad());

        NodeTranslator.translate(dradisLSOViperIconSide.getHudQuad(), BaseModelObject.YAW, 340);
        NodeTranslator.translate(dradisLSOViperIconSide.getHudQuad(), BaseModelObject.PITCH, 150);

        dradisLSOViperIconTop.init("dradisLSOViperIconTopGrid", LSODisplay.class.getResource("/dradis/displays/LSO/viperGlideSlopeIcon2.png"), .75f);
        rootNode.attachChild(dradisLSOViperIconTop.getHudQuad());

        NodeTranslator.translate(dradisLSOViperIconTop.getHudQuad(), BaseModelObject.YAW, 160);
        NodeTranslator.translate(dradisLSOViperIconTop.getHudQuad(), BaseModelObject.PITCH, 150);
    }
}
