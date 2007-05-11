package com.gerbildrop.dradis.displays.stateDisplays;

import java.util.concurrent.Callable;

import com.jme.renderer.Renderer;
import com.jme.scene.SceneElement;
import com.jme.scene.Node;
import com.jme.scene.state.LightState;
import com.jme.math.Vector3f;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.GameTaskQueue;
import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.gerbildrop.dradis.displays.dradisGL.NdArc;
import com.gerbildrop.engine.displays.Displays;
import com.gerbildrop.dradis.resources.DradisResources;
import com.jmex.game.StandardGame;

public class DradisDisplay implements Displays {
    public void init(final StandardGame game, final Node rootNode) {
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.setCullMode(SceneElement.CULL_NEVER);

        final DradisDisplayBase tg = new DradisDisplayBase();
        tg.init("clockNode", DradisResources.DradisBackground, .5f);
        rootNode.attachChild(tg.getHudQuad());

        final DradisDisplayBase do2 = new DradisDisplayBase();
        do2.init("dradisOverlay", DradisResources.dradisOverlay, .5f);
        rootNode.attachChild(do2.getHudQuad());
        do2.getHudQuad().setLocalTranslation(new Vector3f(327.25f, 226.0f, 0));

        GameTaskQueueManager.getManager().getQueue(GameTaskQueue.UPDATE).setExecuteAll(true);
        GameTaskQueueManager.getManager().update(new Callable<Object>() {
            public Object call() throws Exception {
                rootNode.attachChild(new NdArc(game.getSettings().getWidth(), game.getSettings().getHeight()));
                return null;
            }
        });

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    public void update(final float time, final Node rootNode) {
    }
}
