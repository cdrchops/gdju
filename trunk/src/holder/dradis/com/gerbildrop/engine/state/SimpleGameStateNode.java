package com.gerbildrop.engine.state;

import com.jmex.game.state.BasicGameStateNode;
import com.jmex.game.StandardGame;
import com.gerbildrop.engine.displays.Displays;

public class SimpleGameStateNode extends BasicGameStateNode {
    private Displays displays;
    public SimpleGameStateNode(String string, StandardGame game, Displays _displays) {
        super(string);
        _displays.init(game, rootNode);
        this.displays = _displays;
    }

    public void update(float v) {
        super.update(v);
        displays.update(v, rootNode);
    }
}
