package com.gerbildrop.engine.spatial.base;

import java.net.URL;

import com.jme.scene.Node;

public abstract class ShipBase extends BaseModelObject {
    public ShipBase(Node _rootNode, String _name) {
        super(_rootNode, _name);
        try {
            initHull();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        initSound();
        initializeWeaponry();
    }

    public abstract void initHull() throws Exception;

    public abstract URL getResource();

    protected abstract void initSound();
}