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

package com.gerbildrop.dradis.input.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gerbildrop.dradis.bullets.TempBullet;
import com.gerbildrop.dradis.effects.DradisParticleManager;
import com.jme.intersection.TriangleCollisionResults;
import com.jme.math.Vector3f;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.Spatial;

/**
 * @author vivaldi
 * @version $Id: BulletMover.java,v 1.8 2007/04/04 14:29:04 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class BulletMover extends Controller {
    private TriangleCollisionResults pickResults = new TriangleCollisionResults();

    Spatial s;
    float timePassed;
    Vector3f origin;
    Vector3f direction;
    float speed;
    float gravity;
    float damp;
    float lifeTime = 2;
    Node scene;
    TempBullet tmpBullet;

    public BulletMover(Spatial s, Vector3f origin, Vector3f direction, Node scene, TempBullet tmpBullet) {
        timePassed = 0.0f;
        speed = 10F;
        gravity = 9F;
        damp = 1.0F;
        this.s = s;
        this.origin = origin;
        this.direction = direction;
        this.direction.normalizeLocal();
        this.scene = scene;
        this.tmpBullet = tmpBullet;
    }

    public void update(float time) {
        timePassed += time;
        lifeTime -= time;

        /** If the bullets life is over, remove it */
        if (lifeTime < 0) {
            tmpBullet.returnToPool((Node) s);
            return;
        }

        DradisParticleManager.getInstance().update(time);

        checkCollision();

        updateScene();

        if (!isActive()) {
            return;
        }
        s.getLocalTranslation().x = origin.x + direction.x * timePassed * speed;
        s.getLocalTranslation().z = origin.z + direction.z * timePassed * speed;
        s.getLocalTranslation().y = origin.y + direction.y * timePassed * speed;
//        s.getLocalTranslation().y -= FastMath.sqr((float)timePassed) * gravity;
        speed *= damp;
        if ((double) speed < 0.01D) {
            s.removeController(this);
        }
    }

    private static final Set<String> notCollidable = new HashSet<String>();

    static {
        notCollidable.add("bullet");
        notCollidable.add("skyNode");
        notCollidable.add("TDS Scene");
        notCollidable.add("Camera Node");
        notCollidable.add("worldNode");
        notCollidable.add("Particle Nodes");
        notCollidable.add("up");
        notCollidable.add("down");
        notCollidable.add("north");
        notCollidable.add("south");
        notCollidable.add("east");
        notCollidable.add("west");
        notCollidable.add("skybox");
        notCollidable.add("ExplosionEffect");
        notCollidable.add("Particle Nodes");
    }

    public void updateScene() {
        DradisParticleManager.getInstance().attachParticlesToScene(scene);

        for (Node Node : removeList) {
            scene.detachChild(Node);
        }
    }

    List<Node> removeList = new ArrayList<Node>();

    public void checkCollision() {
        List children = scene.getChildren();
        for (int x = children.size(); --x >= 0;) {
            Object item = children.get(x);
            if (item instanceof Spatial) {
                Spatial tmp = (Spatial) item;
                if (!notCollidable.contains(tmp.getName())
                    && tmp.getName().indexOf("bullet") == -1) {
                    pickResults.clear();
                    tmp.calculateCollisions(scene, pickResults);
                    if (tmp.hasCollision(s, false)) {
                        removeList.add((Node) item);
                        DradisParticleManager.getInstance().processHit(s.getLocalTranslation(), (Node) tmp);
                    }
                }
            }
        }
    }
}