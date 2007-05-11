package com.gerbildrop.dradis.bullets;

import java.util.HashSet;

public final class NotCollidable extends HashSet<String> {
    private static final NotCollidable _INSTANCE = new NotCollidable();

    public static NotCollidable getInstance() {
        return _INSTANCE;
    }

    private NotCollidable() {}

    static {
        getInstance().add("bullet");
        getInstance().add("skyNode");
        getInstance().add("TDS Scene");
        getInstance().add("Camera Node");
        getInstance().add("worldNode");
        getInstance().add("Particle Nodes");
        getInstance().add("up");
        getInstance().add("down");
        getInstance().add("north");
        getInstance().add("south");
        getInstance().add("east");
        getInstance().add("west");
        getInstance().add("skybox");
        getInstance().add("ExplosionEffect");
        getInstance().add("Particle Nodes");
    }
}
