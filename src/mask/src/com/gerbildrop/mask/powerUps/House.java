package com.gerbildrop.mask.powerUps;

import com.gerbildrop.engine.spatial.base.BModelFactory;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.mask.util.NodeRotator;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.state.AlphaState;
import com.jme.system.DisplaySystem;

public class House {
    BaseModelObject v2;

    public void create(Node rootNode) {
        v2 = BModelFactory.getInstance().createModelTest("/buildings/", "/buildings/house.jme");

        NodeRotator.rotate(v2, BaseModelObject.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(v2, BaseModelObject.PITCH, (float) Math.toRadians(90));
        v2.updateWorldBound();
        v2.setLocalScale(.1f);
        v2.updateWorldBound();

        v2.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        rootNode.attachChild(v2);
    }

    public static boolean transparent = false;

    public void changeTransparency() {
        if (transparent) {
            AlphaState alphaState = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
            alphaState.setBlendEnabled(true);
            alphaState.setSrcFunction(AlphaState.SB_DST_COLOR);
            alphaState.setDstFunction(AlphaState.DB_SRC_COLOR);
            alphaState.setSrcFunction(AlphaState.SB_SRC_ALPHA);
            alphaState.setDstFunction(AlphaState.DB_ONE);
            alphaState.setTestFunction(AlphaState.TF_GREATER);
            alphaState.setTestEnabled(true);
            alphaState.setEnabled(true);

            v2.setRenderState(alphaState);

            v2.setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);
            v2.updateRenderState();
        } else {
            // Create an alpha state.
            AlphaState as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
            // Blend between the source and destination functions.
            as.setBlendEnabled(true);
            // Set the source function setting.
            as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
            // Set the destination function setting.
            as.setDstFunction(AlphaState.SB_ONE_MINUS_SRC_ALPHA);
            // Enable the test (?  yea, I don't know ?)
            as.setTestEnabled(true);
            // Set the test function to TF_GREATER.
            as.setTestFunction(AlphaState.TF_GREATER);
            // Assign the alpha state to the powerEnclosure's render state.
            v2.setRenderState(as);
            v2.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
            v2.updateRenderState();
        }
    }
}
