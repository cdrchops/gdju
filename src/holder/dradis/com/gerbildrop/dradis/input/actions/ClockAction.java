package com.gerbildrop.dradis.input.actions;

import com.gerbildrop.dradis.state.DradisSpaceGameState;
import com.jme.input.controls.GameControl;

public class ClockAction extends BaseAction {
    public ClockAction(DradisSpaceGameState space) {
        super(space);
    }

    public void pressed(GameControl gameControl, float v) {
        super.pressed(gameControl, v);
        _space.setClockDisplay(true);
    }

    public void released(GameControl control, float time) {
        super.released(control, time);
        _space.setClockDisplay(false);
    }
}