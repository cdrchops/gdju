package com.gerbildrop.engine.state.controller;

import com.jme.input.controls.GameControl;
import com.jme.input.controls.controller.ActionRepeatController;

public class FireController extends ActionRepeatController {
    public FireController(GameControl gameControl, long l, Runnable runnable) {
        super(gameControl, l, runnable);
    }
}
