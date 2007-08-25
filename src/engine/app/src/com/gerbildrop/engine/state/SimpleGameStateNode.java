package com.gerbildrop.engine.state;

import com.gerbildrop.engine.displays.StateDisplay;
import com.jmex.game.StandardGame;
import com.jmex.game.state.BasicGameStateNode;

public class SimpleGameStateNode extends BasicGameStateNode {
    private StateDisplay displays;

    public SimpleGameStateNode(String string, StandardGame game, StateDisplay _displays) {
        super(string);
        _displays.init(game, rootNode);
        this.displays = _displays;
    }

    public void update(float v) {
        super.update(v);
        displays.update(v);
    }
}
