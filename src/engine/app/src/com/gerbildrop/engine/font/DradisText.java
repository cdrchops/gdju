package com.gerbildrop.engine.font;

import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.engine.util.NodeTranslator;
import com.jme.image.Texture;
import com.jme.scene.Text;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import jmetest.renderer.TestText;

public class DradisText {
    private Text text;
    private String name;
    private static AlphaState as;
    private static TextureState ts;

    public DradisText(String _name) {
        this.name = _name;
        init();
    }

    private void init() {
        as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE);
        as.setTestEnabled(true);
        as.setTestFunction(AlphaState.TF_GREATER);
        as.setEnabled(true);

        ts = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        ts.setTexture(
                TextureManager.loadTexture(
                        TestText.class.getClassLoader().getResource("jmetest/data/font/font.png"),
                        com.jme.image.Texture.MM_LINEAR,
                        Texture.FM_LINEAR));
        ts.setEnabled(true);
    }

    public Text print(String textPrint) {
        return print(textPrint, .5f);
    }

    public Text print(String textPrint, float scale) {
        text = new Text(name, textPrint);
        text.setRenderState(ts);
        text.setRenderState(as);
        text.setLocalScale(scale);
        return text;
    }

    public void translate(float pitch, float yaw) {
        if (text != null) {
            NodeTranslator.translate(text, BaseModelObject.YAW, yaw);
            NodeTranslator.translate(text, BaseModelObject.PITCH, pitch);
        }
    }
}
