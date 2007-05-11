package com.gerbildrop.engine.input.actions;

import com.gerbildrop.engine.spatial.base.BaseModelObject;

public class FireAction implements Runnable {
    private BaseModelObject ship;

    public FireAction(BaseModelObject ship) {
        this.ship = ship;
    }

    public void run() {
        ship.fire();
    }
}