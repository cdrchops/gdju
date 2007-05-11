package com.gerbildrop.dradis.state.controller;

import com.gerbildrop.dradis.state.game.HUDGameState;
import com.jme.input.controls.controller.ThrottleController;
import com.jme.scene.Controller;

public class HUDController extends Controller {
    private static final long serialVersionUID = 133871133903323852L;

    private HUDGameState hud;
    private ThrottleController throttle;
    private float speedMultiplier;

    public HUDController(HUDGameState hud, ThrottleController throttle, float speedMultiplier) {
        this.hud = hud;
        this.throttle = throttle;
        this.speedMultiplier = speedMultiplier;
    }


    public void update(float time) {
        hud.setSpeed(Math.round(throttle.getCurrentThrottle() * speedMultiplier));
    }
}
