package com.gerbildrop.engine.font;

import com.jme.image.Texture;
import com.jme.scene.Node;
import com.jme.scene.Text;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.game.StandardGame;

public class TextNode extends Node {
    private static final long serialVersionUID = 3726888084021083555L;
    private static final String FONT_LOCATION = "/com/jme/app/defaultfont.tga";

    private Text text;

    public TextNode(String name, String s) {
        super(name);

        AlphaState as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE);
        as.setTestEnabled(true);
        as.setTestFunction(AlphaState.TF_GREATER);
        as.setEnabled(true);

        text = new Text(name + ":Text", s);
        text.setTextureCombineMode(TextureState.REPLACE);
        attachChild(text);

        TextureState font = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        font.setTexture(TextureManager.loadTexture(StandardGame.class.getResource(FONT_LOCATION),
                                                   Texture.MM_LINEAR, Texture.FM_LINEAR));
        font.setEnabled(true);
        setRenderState(font);
        setRenderState(as);
        updateGeometricState(0.0f, true);
        updateRenderState();
    }

    public void setText(String s) {
        text.print(s);
    }
}
