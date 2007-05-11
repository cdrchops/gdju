package com.gerbildrop.engine.menu;

import com.jme.math.Vector3f;
import com.jme.scene.Node;

public interface IBackdrop {
    public void buildBackdrop(Node rootNode, Vector3f location);

    public void update(float tpf);
}
