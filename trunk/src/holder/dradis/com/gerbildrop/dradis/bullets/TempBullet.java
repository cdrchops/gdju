/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.bullets;

import java.util.ArrayList;
import java.util.List;

import com.gerbildrop.dradis.input.actions.BulletMover;
import com.gerbildrop.dradis.logging.Debug;
import com.jme.bounding.BoundingSphere;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Sphere;

//todo: fix bullets -- when so many bullets are fired, they quit displaying

/**
 * @author vivaldi
 * @version $Id: TempBullet.java,v 1.15 2007/04/04 14:29:08 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class TempBullet {
    private int numBullets;
    private static int MAX_BULLETS = 150;
    private static List<Node> bulletPool = new ArrayList<Node>(MAX_BULLETS);

    public TempBullet() {
        load();
    }

    public void load() {
        for (int i = 0; i < MAX_BULLETS; i++) {
            bulletPool.add(make());
        }
    }

    public void get(Node Node, float offset, Node scene) {
        Node returnNode;
        if (!bulletPool.isEmpty()) {
            returnNode = bulletPool.remove(0);
            Debug.debug("bulletPool size " + bulletPool.size());
        } else {
            returnNode = make();
            Debug.debug("made new bullet " + bulletPool.size());
        }

        Vector3f cloc = new Vector3f(Node.getWorldTranslation()).add(new Vector3f(offset, 0, 0));
        Quaternion q = Node.getWorldRotation();
        Vector3f direction = new Vector3f(0.0F, -1.0F, 0.0F);
        q.multLocal(direction);
        returnNode.setLocalTranslation(cloc);
        returnNode.addController(new BulletMover(returnNode, cloc, direction, scene, this));
        scene.attachChild(returnNode);
        returnNode.updateCollisionTree();
        returnNode.updateRenderState();
    }

    private Node make() {
        numBullets++;
        Sphere bu = new Sphere("bulletSphere" + numBullets, 6, 6, .05F);
        bu.setModelBound(new BoundingSphere());
        bu.updateModelBound();
        Node bullet = new Node("bullet" + numBullets);
        bullet.setIsCollidable(true);
        bullet.attachChild(bu);

        return bullet;
    }

    public void returnToPool(Node Node) {
        bulletPool.add(Node);
        Debug.debug("added Bullet bulletPool size is now: " + bulletPool.size());
    }
}
