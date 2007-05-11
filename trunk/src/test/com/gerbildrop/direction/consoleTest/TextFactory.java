package com.gerbildrop.direction.consoleTest;

import com.jme.image.Texture;
import com.jme.scene.Text;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.game.StandardGame;

public class TextFactory {
    private static AlphaState as;
    private static TextureState font;
    private static final String FONT_LOCATION = "/com/jme/app/defaultfont.tga";

    private static final TextFactory _INSTANCE = new TextFactory();

    private TextFactory() {
        init();
    }

    public static TextFactory getInstance() {
        return _INSTANCE;
    }

    private void init() {
		as = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
		as.setBlendEnabled(true);
		as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
		as.setDstFunction(AlphaState.DB_ONE);
		as.setTestEnabled(true);
		as.setTestFunction(AlphaState.TF_GREATER);
		as.setEnabled(true);

		font = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
		font.setTexture(TextureManager.loadTexture(StandardGame.class.getResource(FONT_LOCATION), Texture.MM_LINEAR, Texture.FM_LINEAR));
		font.setEnabled(true);
    }

    public Text createText(String name, String value, float xPosition, float yPosition) {
		Text text = new Text(name, value);
		text.setTextureCombineMode(TextureState.REPLACE);
		text.setRenderState(as);
		text.setRenderState(font);
		text.updateGeometricState(0.0f, true);
		text.setLocalTranslation(xPosition, yPosition, 0.0f);

		return text;
	}
}
