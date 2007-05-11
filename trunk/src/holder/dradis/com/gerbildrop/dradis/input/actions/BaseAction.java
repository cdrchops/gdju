package com.gerbildrop.dradis.input.actions;

import com.gerbildrop.dradis.state.DradisSpaceGameState;
import com.jme.input.controls.GameControl;
import com.jme.input.controls.controller.GameControlAction;
import com.jme.input.MouseInput;

public class BaseAction implements GameControlAction {
    protected DradisSpaceGameState _space;

    public BaseAction(DradisSpaceGameState space) {
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
