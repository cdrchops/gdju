package com.gerbildrop.engine.spatial.weaponry;


import java.util.ArrayList;
import java.util.List;

import com.jme.scene.Node;

public class Gun implements IWeapon {
    private List<Barrel> barrels = new ArrayList<Barrel>();

    public void addBarrel(Barrel barrel) {
        barrels.add(barrel);
    }

    public void fire(Node rootNode) {
        for (Barrel barrel : barrels) {
            barrel.fire(rootNode);
        }
    }
}