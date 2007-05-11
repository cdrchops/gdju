package com.gerbildrop.engine.util;

import java.awt.Font;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.jme.math.Vector3f;
import com.jme.util.GameTaskQueueManager;
import com.jmex.font3d.Font3D;
import com.jmex.font3d.Text3D;

public class TextFactory {
    private static final Font font = new Font("Arial", Font.PLAIN, 24);

    public static Text3D createFlatText(final String text, final float scale) {
        Future<Text3D> f = GameTaskQueueManager.getManager().update(new Callable<Text3D>() {
            public Text3D call() throws Exception {
                Font3D font3d = new Font3D(font, 0.001f, true, true, true);
                Text3D text3d = font3d.createText(text, scale, 0);
                text3d.setLocalScale(new Vector3f(scale / 10.0f, scale / 10.0f, 0.01f));
                text3d.updateRenderState();
                return text3d;
            }
        });

        try {
            return f.get();
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    public static TextNode createText(String name, String text) {
        return new TextNode(name, text);
    }
}
