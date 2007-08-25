package com.gerbildrop.engine.displays;

import com.jme.scene.Node;
import com.jmex.game.StandardGame;

public abstract class StateDisplay {
    protected StandardGame game;
    protected Node rootNode;

    public void init(final StandardGame _game, final Node _rootNode) {
        this.game = _game;
        this.rootNode = _rootNode;

        init();
    }

    public abstract void init();

    public abstract void update(final float time);
}
