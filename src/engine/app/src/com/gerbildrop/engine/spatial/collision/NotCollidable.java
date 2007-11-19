package com.gerbildrop.engine.spatial.collision;

import java.util.HashSet;

public final class NotCollidable extends HashSet<String> {
    private static final NotCollidable _INSTANCE = new NotCollidable();

    public static NotCollidable getInstance() {
        return _INSTANCE;
    }

    private NotCollidable() {}

    static {
        _INSTANCE.add("bullet");
        _INSTANCE.add("skyNode");
        _INSTANCE.add("TDS Scene");
        _INSTANCE.add("Camera Node");
        _INSTANCE.add("viper Hoff");
        _INSTANCE.add("camNode");
        _INSTANCE.add("worldNode");
        _INSTANCE.add("Particle Nodes");
        _INSTANCE.add("up");
        _INSTANCE.add("down");
        _INSTANCE.add("north");
        _INSTANCE.add("south");
        _INSTANCE.add("east");
        _INSTANCE.add("west");
        _INSTANCE.add("skybox");
        _INSTANCE.add("ExplosionEffect");
        _INSTANCE.add("Particle Nodes");
        _INSTANCE.add(": RootNode");
        _INSTANCE.add("rootNode");
    }
}
