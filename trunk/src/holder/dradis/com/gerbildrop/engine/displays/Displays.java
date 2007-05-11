package com.gerbildrop.engine.displays;

import com.jmex.game.StandardGame;
import com.jme.scene.Node;

public interface Displays {
    public void init(final StandardGame game, final Node rootNode);
    public void update(final float time, final Node rootNode);
}
