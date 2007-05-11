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
package com.gerbildrop.dradis.ai;

import java.util.List;

import com.gerbildrop.dradis.ai.basics.AlreadyGotcha;
import com.gerbildrop.dradis.ai.basics.InterceptController;
import com.gerbildrop.dradis.ships.Raider;
import com.gerbildrop.dradis.ships.Viper2;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.Spatial;

/**
 * @author vivaldi
 * @version $Id: RaiderAI.java,v 1.10 2007/04/04 14:29:18 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class RaiderAI extends Controller {
    Raider raider;
    Node rootNode;
    InterceptController ai = null;
    Viper2 v2;

    public RaiderAI(Raider raider, Node rootNode, Viper2 v2) {
        this.raider = raider;
        this.rootNode = rootNode;
        this.v2 = v2;
    }

    public void update(float time) {
        checkCollision();
        if (ai != null) {
            try {
                ai.updateCycle(time);
            } catch (AlreadyGotcha alreadyGotcha) {
                alreadyGotcha.printStackTrace();
            }
        }
    }

    private static boolean showName = true;

    public void checkCollision() {
        List children = rootNode.getChildren();
        float minDistance = 0;
        Viper2 nearestNode = null;
        for (int x = children.size(); --x >= 0;) {
            Object item = children.get(x);
            if (item instanceof Spatial) {
                Spatial tmp = (Spatial) item;
                String name = tmp.getName().toLowerCase();

                if (showName) {
                    System.out.println("name " + name);
                }

                /*if (name.indexOf("galactica") != -1) {
                    float distance = tmp.getLocalTranslation().distance(raider.getNode().getLocalTranslation());
                    if (ai == null
                        && distance < minDistance) {
                        minDistance = distance;
                        nearestNode = (Node) tmp;
                    }
//                    if (distance < 1000) {
//                        System.out.println("raider is in range of galactica");
//                    }
                } else */
//                if (name.indexOf("viper") != -1) {
//                    float distance = tmp.getLocalTranslation().distance(raider.getNode().getLocalTranslation());
//                    if (ai == null
//                        && distance < minDistance) {
//                        minDistance = distance;
//
//                    }
//                }
                /* else if (name.indexOf("raptor") != -1) {
                    float distance = tmp.getLocalTranslation().distance(raider.getNode().getLocalTranslation());
                    if (ai == null
                        && distance < minDistance) {
                        minDistance = distance;
                        nearestNode = (Node) tmp;
                    }
                }*/
//                if (!notCollidable.contains(tmp.getName())
//                        && tmp.getName().indexOf("bullet") == -1) {
//                    pickResults.clear();
//                    tmp.calculateCollisions(scene, pickResults);
//                    if (tmp.hasCollision(s, false)) {
//                        removeList.add((Node) item);
//                        DradisParticleManager.getInstance().processHit(tmp.getLocalTranslation(), (Node) tmp);
//                    }
//                }

            }
        }

        if (ai == null) {
            if (v2 != null) {//nearestNode
                ai = new InterceptController(raider.getNode(), v2);
            }
        }

        showName = false;
    }
}