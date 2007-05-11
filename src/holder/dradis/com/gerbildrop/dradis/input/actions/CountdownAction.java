package com.gerbildrop.dradis.input.actions;

import com.gerbildrop.dradis.state.DradisSpaceGameState;
import com.jme.input.controls.GameControl;

public class CountdownAction extends BaseAction {
    public CountdownAction(DradisSpaceGameState space) {
        super(space);
    }

    public void pressed(GameControl control, float time) {
        super.pressed(control, time);
        _space.setCountdownDisplay(true);
    }

    public void released(GameControl control, float time) {
        super.released(control, time);
        _space.setCountdownDisplay(false);
    }
}