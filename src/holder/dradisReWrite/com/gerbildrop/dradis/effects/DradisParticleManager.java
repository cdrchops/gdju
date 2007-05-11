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

package com.gerbildrop.dradis.effects;

import java.util.ArrayList;
import java.util.List;

import com.gerbildrop.dradis.bullets.TimerController;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.effects.particles.ParticleController;
import com.jmex.effects.particles.ParticleMesh;

/**
 * @author vivaldi
 * @version $Id: DradisParticleManager.java,v 1.4 2007/04/04 14:31:08 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisParticleManager {
    private static final DradisParticleManager _INSTANCE = new DradisParticleManager();

    private ParticleController manager;
    private ParticleMesh particles;
    private List<Node> lst = new ArrayList<Node>();

    private DradisParticleManager() {
        createParticleManager();
    }

    public static DradisParticleManager getInstance() {
        return _INSTANCE;
    }

    public void processHit(Vector3f hit, Node hitted) {
        createParticles();
        particles.setLocalTranslation(hit);
        lst.add(new ExplosionEffect(hitted, hit));
    }

    private void createParticleManager() {
        AlphaState alpha;
        TextureState partTex;
        alpha = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        alpha.setBlendEnabled(true);
        alpha.setSrcFunction(4);
        alpha.setDstFunction(1);
        alpha.setTestEnabled(true);
        alpha.setTestFunction(4);
        alpha.setEnabled(true);
        partTex = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        partTex.setTexture(TextureManager.loadTexture(getClass().getClassLoader().getResource("jmetest/data/texture/flare1.png"), 6, 1));
        alpha.setBlendEnabled(true);
        alpha.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        alpha.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);

        particles = new ParticleMesh("particles", 300);
        particles.setEmissionDirection(new Vector3f(0.0F, 1.0F, 0.0F));
        particles.setMaximumAngle(2.111848F);
        particles.setMinimumAngle(0.0F);

        particles.setMinimumLifeTime(100F);
        particles.setMaximumLifeTime(200F);
        particles.setStartSize(0.1F);
        particles.setEndSize(1F);
        particles.setStartColor(new ColorRGBA(0.8F, 0.2F, 0.0F, 1.0F));
        particles.setEndColor(new ColorRGBA(0.0F, 0.0F, 0.0F, 0.0F));
        particles.setReleaseRate(30);

        particles.setInitialVelocity(0.1F);
        particles.setParticleSpinSpeed(0.36F);
        manager = new ParticleController(particles);
        manager.setSpeed(0.2F);
        manager.setControlFlow(true);
        manager.setReleaseVariance(0.0F);
        manager.getParticles().addController(manager);

        ZBufferState zstate = DisplaySystem.getDisplaySystem().getRenderer().createZBufferState();
        zstate.setEnabled(true);
        zstate.setWritable(false);
        manager.getParticles().setRenderState(zstate);
        manager.getParticles().setRenderState(partTex);
        manager.getParticles().setRenderState(alpha);
    }

    private void createParticles() {
        final Node myNode = new Node("Particle Nodes");
        myNode.attachChild(manager.getParticles());
        myNode.updateRenderState();
        myNode.addController(new TimerController(3) {
            public void fireTimer() {
                myNode.removeFromParent();
                myNode.removeController(this);
            }
        });

        lst.add(myNode);
        manager.setRepeatType(0);
        myNode.setLocalScale(.25F);
    }

    public void update(float time) {
        manager.update(time);
    }

    public List getParticleList() {
        return lst;
    }

    public void attachParticlesToScene(Node rootNode) {
        for (Object aLst : lst) {
            Node Node = (Node) aLst;
            rootNode.attachChild(Node);
        }
    }
}
