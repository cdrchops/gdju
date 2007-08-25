package com.gerbildrop.engine.spatial.controllers;

import com.jme.scene.Controller;

/**
 * @author vivaldi
 * @version $Id: TimerController.java,v 1.1 2006/11/17 20:10:34 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class TimerController extends Controller {

    public TimerController(float seconds) {
        timePassed = 0.0F;
        this.seconds = seconds;
    }

    public void update(float time) {
        if (!isActive()) {
            return;
        }

        timePassed += time;
        if (timePassed >= seconds) {
            fireTimer();
            setActive(false);
        }
    }

    public void fireTimer() {
        System.out.println("Override TimerController.fireTimer() !");
    }

    float seconds;
    float timePassed;
}
