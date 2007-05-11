package com.gerbildrop.engine.spatial.bullets;

import com.gerbildrop.engine.input.actions.BulletMover;
import com.jme.bounding.BoundingSphere;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Sphere;

//todo: fix bullets -- when so many bullets are fired, they quit displaying

/**
 * @author vivaldi
 * @version $Id: BulletFactory.java,v 1.1 2007/04/04 14:27:22 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class BulletFactory {
    private int numBullets;

    public void get(Node modelNode, Vector3f offset, Node scene) {
        Node returnNode = make();
        Vector3f cloc = new Vector3f(modelNode.getWorldTranslation().subtract(offset));
        Quaternion q = modelNode.getWorldRotation();
        Vector3f direction = new Vector3f(0.0F, 0.0F, 1.0F);
        q.multLocal(direction);
        returnNode.setLocalTranslation(cloc);
        returnNode.addController(new BulletMover(returnNode, cloc, direction, scene));//, this
        scene.attachChild(returnNode);
        scene.updateRenderState();
    }

    private Node make() {
        numBullets++;
        Sphere bu = new Sphere("bulletSphere" + numBullets, 10, 10, .10F);
        bu.setModelBound(new BoundingSphere());
        bu.updateModelBound();
        Node bullet = new Node("bullet" + numBullets);
        bullet.setIsCollidable(true);
        bullet.attachChild(bu);

        return bullet;
    }
}
