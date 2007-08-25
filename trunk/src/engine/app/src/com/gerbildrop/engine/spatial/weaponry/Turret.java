package com.gerbildrop.engine.spatial.weaponry;

import java.util.ArrayList;
import java.util.List;

public class Turret implements IWeapon {
    private List<Weapon> gunList;

    public Turret() {
        gunList = new ArrayList<Weapon>();
    }
}
