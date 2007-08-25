package com.gerbildrop.engine.input.actions;

import com.gerbildrop.engine.state.SpaceGameState;
import com.jme.input.MouseInput;
import com.jme.input.controls.GameControl;
import com.jme.input.controls.controller.GameControlAction;

public class BaseAction implements GameControlAction {
    protected SpaceGameState _space;
    private String activity;

    public BaseAction(SpaceGameState space, String _activity) {
        _space = space;
        activity = _activity;
    }

    public void pressed(GameControl control, float time) {
        MouseInput.get().setCursorVisible(true);
        _space.setShipControllersActive(false);
        _space.setActivity(activity, true);
    }

    public void released(GameControl control, float time) {
        MouseInput.get().setCursorVisible(false);
        _space.setShipControllersActive(true);
        _space.setActivity(activity, false);
    }
}
