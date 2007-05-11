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

package com.gerbildrop.engine.effects;

import com.jme.bounding.BoundingSphere;
import com.jme.intersection.BoundingCollisionResults;
import com.jme.intersection.CollisionData;
import com.jme.math.Vector3f;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.shape.Sphere;

/**
 * @author vivaldi
 * @version $Id: ExplosionEffect.java,v 1.1 2007/04/04 14:27:27 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class ExplosionEffect extends Node {
    private class EEController extends Controller {
        public EEController() {
            results = new BoundingCollisionResults() {
                public void processCollisions() {
                    if (getNumber() > 0) {
//                        System.out.println("collision yes");
                    } else {
//                        System.out.println("Collision: NO");
                    }
                }
            };
        }

        public void update(float f) {
            if (!isActive())
                return;
            timeLived += f;
            results.clear();
            s.calculateCollisions(scene, results);
            for (int i = 0; i < results.getNumber(); i++) {
                CollisionData data = results.getCollisionData(i);
//                Node n = data.getTargetMesh().getParent().getParent().getParent();
//                if (n instanceof PlayerModel) {
////                    PlayerModel p = (PlayerModel) n;
//                    Vector3f c = instance.getWorldTranslation();
//                    c.y = terrain.getHeight(c.x, c.z);
//                    Vector3f v = p.getWorldTranslation();
//                    v = v.subtract(c);
//                    float distQ = v.length();
//                    v.y++;
//                    float effect = 1.0F - Math.min(distQ / radius, 1.0F);
//                    p.hurt((int) (50F * effect));
//                    v.normalizeLocal();
////                    BallisticController b = new TerrainBallisticController(n, n.getLocalTranslation(), v, terrain);
////                    b.speed = 50F * effect;
////                    b.gravity = 8F;
////                    b.damp = 0.9F;
////                    n.addController(b);
//                }
            }

            if (results.getNumber() > 0
                || timeLived > timeToLive) {
                setActive(false);
                instance.removeController(this);
                instance.removeFromParent();
            }
        }

        BoundingCollisionResults results;
        float timeLived;
    }


    public ExplosionEffect(Node scene, Vector3f location) {
        super("ExplosionEffect");
        timeToLive = 1.5F;
        radius = 50F;
        setLocalTranslation(location);
        this.scene = scene;
        instance = this;
        s = new Sphere("ExplosionEffect", 6, 6, getRadius());
        s.setModelBound(new BoundingSphere());
        s.setCullMode(2);
        s.setRenderQueueMode(2);
        s.updateModelBound();
        attachChild(s);
        addController(new EEController());
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    Node scene;
    ExplosionEffect instance;
    Sphere s;
    float timeToLive;
    private float radius;
}