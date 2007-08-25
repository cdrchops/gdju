package com.gerbildrop.engine.spatial.weaponry;

import com.jme.math.Vector3f;

public abstract class Weapon implements IWeapon {
    private Vector3f location;

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(final Vector3f location) {
        this.location = location;
    }
}
