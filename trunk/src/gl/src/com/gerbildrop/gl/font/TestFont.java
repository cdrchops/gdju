package com.gerbildrop.gl.font;

import java.net.URL;

import com.jme.app.AbstractGame;
import com.jme.app.SimpleGame;
import com.jme.image.Texture;
import com.jme.scene.Text;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class TestFont extends SimpleGame {

    public static void main(String[] args) {
        TestFont app = new TestFont();
        app.setDialogBehaviour(AbstractGame.ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }

    protected void simpleInitGame() {

        URL url;
        url = TestFont.class.getResource("/fonts/Arial.png");

        TextureState ts = DisplaySystem.getDisplaySystem().getRenderer()
                .createTextureState();
        TextureState.forceNonPowerOfTwoTextureSizeUsage();
        Texture t = TextureManager.loadTexture(url,
                                               Texture.MM_LINEAR, Texture.MM_LINEAR, 1, true);
        ts.setTexture(t);

        Text text = Text.createDefaultTextLabel("", "hello");
        text.getLocalTranslation().set(display.getWidth() / 2,
                                       display.getHeight() / 2, 0);
        fpsNode.attachChild(text);
        text.setRenderState(ts);
        text.updateRenderState();
        text.updateGeometricState(0, false);
    }

}

//apt-get install ttf-larabie-deco ttf-larabie-straight ttf-larabie-uncommon