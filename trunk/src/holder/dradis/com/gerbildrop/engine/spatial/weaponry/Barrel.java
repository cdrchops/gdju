package com.gerbildrop.engine.spatial.weaponry;


import com.gerbildrop.engine.spatial.bullets.BulletFactory;
import com.jme.math.Vector3f;
import com.jme.scene.Node;

/**
 * @author vivaldi
 * @version $Id: Barrel.java,v 1.1 2007/04/04 14:27:14 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Barrel {
    private Vector3f location;
    private Node rootNode;
    private Node modelNode;
    private BulletFactory tb;

    public Barrel(Vector3f location, Node modelNode, Node rootNode) {
        this.location = location;
        this.modelNode = modelNode;
        this.rootNode = rootNode;
        this.tb = new BulletFactory();
    }

    public void fire(Node rootNode) {
        tb.get(modelNode, location, rootNode);
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public Node getModelNode() {
        return modelNode;
    }

    public void setModelNode(Node modelNode) {
        this.modelNode = modelNode;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void update() {
        rootNode.updateRenderState();
    }
}