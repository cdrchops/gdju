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

package com.gerbildrop.engine.input.actions;

import com.jme.math.Vector3f;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
//import com.captiveimagination.gb.bullets.CollisionManager;

/**
 * @author vivaldi
 * @version $Id: BulletMover.java,v 1.1 2007/04/04 14:27:22 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class BulletMover extends Controller {
    private float timePassed;
    private float lifeTime = 2;

    private Spatial s;
    private Vector3f origin;
    private Vector3f direction;
    private float speed;
    private float damp;

    private Node scene;

//    private BulletPool tmpBullet;

    public BulletMover(Spatial s, Vector3f origin, Vector3f direction, Node scene) {//], BulletPool tmpBullet) {
        timePassed = 0.0f;
        speed = 4f;
        damp = 1.0F;
        this.s = s;
        this.origin = origin;
        this.direction = direction.normalizeLocal();

        this.scene = scene;
//        this.tmpBullet = tmpBullet;
    }

    public void update(float time) {
        timePassed += time;
        lifeTime -= time;

        /** If the bullets life is over, remove it */
        if (lifeTime < 0) {
            s.removeFromParent();
//            tmpBullet.returnToPool((Node) s);
            return;
        }

//        CollisionManager.getInstance().update(time, scene, (Node) s);

        if (!isActive()) {
            return;
        }

        s.getLocalTranslation().x = origin.x + direction.x * timePassed * speed;
        s.getLocalTranslation().z = origin.z + direction.z * timePassed * speed;
        s.getLocalTranslation().y = origin.y + direction.y * timePassed * speed;

        speed *= damp;
        if ((double) speed < 0.01D) {
            s.removeController(this);
        }

        scene.updateRenderState();
    }
}