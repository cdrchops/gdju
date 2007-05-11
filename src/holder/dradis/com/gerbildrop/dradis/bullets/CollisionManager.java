package com.gerbildrop.dradis.bullets;

import java.util.List;
import java.util.ArrayList;

import com.jme.scene.Spatial;
import com.jme.scene.Node;
import com.jme.intersection.TriangleCollisionResults;
import com.gerbildrop.dradis.effects.DradisParticleManager;

public class CollisionManager {

    private List<Node> removeList = new ArrayList<Node>();
    private TriangleCollisionResults pickResults = new TriangleCollisionResults();

    private static final CollisionManager _INSTANCE = new CollisionManager();

    public static CollisionManager getInstance() {
        return _INSTANCE;
    }

    private CollisionManager() {}

    //todo: put collision detection into its own thread so it doesn't slow down the current state
    public void checkCollision(Spatial bullet, Node scene) {
        List children = scene.getChildren();
        for (int x = children.size(); --x >= 0;) {
            Object item = children.get(x);
            if (item instanceof Spatial) {
                Spatial tmp = (Spatial) item;
                if (!NotCollidable.getInstance().contains(tmp.getName())
                    && tmp.getName().indexOf("bullet") == -1) {
                    pickResults.clear();
                    tmp.calculateCollisions(scene, pickResults);
                    if (tmp.hasCollision(bullet, false)) {
                        removeList.add((Node) item);
                        DradisParticleManager.getInstance().processHit(bullet.getLocalTranslation(), (Node) tmp);
                    }
                }
            }
        }
    }

    public void update(float time, Node scene, Node bullet) {
        checkCollision(bullet, scene);

        DradisParticleManager.getInstance().update(time);

        updateScene(bullet, scene);
    }

    public void updateScene(Node bullet, Node scene) {
        DradisParticleManager.getInstance().attachParticlesToScene(scene);

        for (Node node : removeList) {
            scene.detachChild(node);
        }
    }

    public List<Node> getRemoveList() {
        return removeList;
    }

    public void addToRemoveList(Node node) {
        removeList.add(node);
    }
}
