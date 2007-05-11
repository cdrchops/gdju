package com.gerbildrop.engine.input.actions;

import com.gerbildrop.engine.state.SpaceGameState;
import com.jme.input.MouseInput;
import com.jme.input.controls.GameControl;
import com.jme.input.controls.controller.GameControlAction;

/** @author Matthew D. Hicks */
public class MouseAction implements GameControlAction {
    protected SpaceGameState _space;

    public MouseAction(SpaceGameState space) {
        _space = space;
    }

    public void pressed(GameControl control, float time) {
        MouseInput.get().setCursorVisible(true);
        _space.setShipControllersActive(false);
    }

    public void released(GameControl control, float time) {
        MouseInput.get().setCursorVisible(false);
        _space.setShipControllersActive(true);
    }
}
