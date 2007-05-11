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

package com.gerbildrop.dradis.board;

import com.gerbildrop.dradis.cameras.CameraManager;
import com.gerbildrop.dradis.gui.PlayerLocator;
import com.gerbildrop.dradis.gui.StaticHud;
import com.gerbildrop.dradis.model.BModelFactory;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.ships.BaseModelObject;
import com.gerbildrop.dradis.ships.basic.Barrel;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.bounding.OrientedBoundingBox;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;

/**
 * @author vivaldi
 * @version $Id: WorldMap.java,v 1.36 2007/04/04 14:29:19 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class WorldMap {
    private static BaseModelObject v2 = null;
    private static BaseModelObject galactica = null;
    private static BaseModelObject pegasus = null;
    private static BaseModelObject[] raider = null;
    private static BaseModelObject[] heavyRaider = null;
    private static BaseModelObject[] raptor = null;

    private Node rootNode;

    private static boolean loaded = false;

    private static final String TEXTURE_PATH = "dradis/models/";

    public boolean isLoaded() {
        return loaded;
    }

    public void load(Node rootNode) {
        this.rootNode = rootNode;

        loadViper2();
        loadPlayerLocator();
//        loadGalactica();
//        loadPegasus();
        loadRaiders();
//        loadHeavyRaiders();
//        loadRaptors();
        loadStaticHud();

        loaded = true;

        this.rootNode.updateRenderState();
    }

    public void update(float time) {
        v2.update(time);
        if (PlayerLocator.getInstance().isLoaded()) {
            if (raider != null) {
                for (BaseModelObject tmpRaider : raider) {
                    PlayerLocator.getInstance().update(tmpRaider.getRootNode().getName(), tmpRaider.getRootNode().getLocalTranslation(), time);
                }
            }

            if (heavyRaider != null) {
                for (BaseModelObject tmpRaider : heavyRaider) {
                    PlayerLocator.getInstance().update(tmpRaider.getRootNode().getName(), tmpRaider.getRootNode().getLocalTranslation(), time);
                }
            }

            if (raptor != null) {
                for (BaseModelObject tmpRaider : raptor) {
                    PlayerLocator.getInstance().update(tmpRaider.getRootNode().getName(), tmpRaider.getRootNode().getLocalTranslation(), time);
                }
            }

            PlayerLocator.getInstance().update(v2.getRootNode().getName(), v2.getRootNode().getLocalTranslation(), time);
        }

        if (raider != null) {
            for (BaseModelObject tmpRaider : raider) {
                tmpRaider.update(time);
            }
        }

        StaticHud.getInstance().update(time);
        rootNode.updateWorldData(time);
        rootNode.updateRenderState();
    }

    public BaseModelObject getPlayer() {
        return v2;
    }

    private void loadGalactica() {
        galactica = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/galactica.jme");

        galactica.getRootNode().setLocalTranslation(new Vector3f(-20, 1.25f, -10));

//        galactica.getRootNode().updateCollisionTree();
        NodeRotator.rotate(galactica.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(galactica.getRootNode(), Model.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(galactica.getRootNode(), Model.PITCH, (float) Math.toRadians(90));
        rootNode.attachChild(galactica.getRootNode());
        rootNode.updateRenderState();
    }

    private void loadPegasus() {

        pegasus = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/pegasus.jme");

        NodeRotator.rotate(pegasus.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(pegasus.getRootNode(), Model.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(pegasus.getRootNode(), Model.PITCH, (float) Math.toRadians(90));

        pegasus.getRootNode().setLocalTranslation(new Vector3f(-100, 1.25f, 50));
//        pegasus.getRootNode().updateCollisionTree();

        rootNode.attachChild(pegasus.getRootNode());
    }

    private void loadViper2() {
        v2 = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/viper2.jme");
        v2.getRootNode().setLocalTranslation(new Vector3f(0, -.25f, 1));

//        v2.getRootNode().updateCollisionTree();
        if (v2.getRootNode().getChild(0).getControllers().size() != 0)
            v2.getRootNode().getChild(0).getController(0).setSpeed(20);

        v2.setAcceleration(15);
        v2.setBraking(15);
        v2.setTurnSpeed(.25f);
        v2.setWeight(25);
        v2.setMaxSpeed(25);
        v2.setMinSpeed(5);

        v2.getRootNode().setRenderQueueMode(Renderer.QUEUE_OPAQUE);

        NodeRotator.rotate(v2.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(v2.getRootNode(), Model.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(v2.getRootNode(), Model.PITCH, (float) Math.toRadians(90));
        //viper laying on landing bay runway facing down landing bay
        v2.getRootNode().setLocalTranslation(new Vector3f(0, -.25f, 2));

        Barrel left = new Barrel(.25f, v2.getRootNode(), rootNode);
        Barrel right = new Barrel(-.25f, v2.getRootNode(), rootNode);
        v2.getGun().addBarrel(left);
        v2.getGun().addBarrel(right);

        NodeRotator.rotate(CameraManager.getInstance().getCurrentCameraNode(), Model.PITCH, (float) Math.toRadians(90));
        v2.getRootNode().attachChild(CameraManager.getInstance().getCurrentCameraNode());
        rootNode.attachChild(v2.getRootNode());
        rootNode.updateRenderState();
    }

    private void loadHeavyRaiders() {
        heavyRaider = new BaseModelObject[3];
        for (int i = 0; i < heavyRaider.length; i++) {
            BaseModelObject heavyRaider1 = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/heavyRaider.jme");

            heavyRaider1.getRootNode().setName("heavyRaider" + i);
            NodeRotator.rotate(heavyRaider1.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
            NodeRotator.rotate(heavyRaider1.getRootNode(), Model.YAW, (float) Math.toRadians(180));
            NodeRotator.rotate(heavyRaider1.getRootNode(), Model.PITCH, (float) Math.toRadians(90));

//            OrientedBoundingBox bx = new OrientedBoundingBox();
//            bx.setCenter(heavyRaider1.getRootNode().getLocalTranslation());
//            bx.setExtent(new Vector3f(.1f, .1f, .1f));
//
//            heavyRaider1.getRootNode().setModelBound(bx);
//            heavyRaider1.getRootNode().updateModelBound();
//            heavyRaider1.getRootNode().updateCollisionTree();
//            heavyRaider1.getRootNode().setIsCollidable(true);
            heavyRaider1.getRootNode().setLocalTranslation(new Vector3f(5 * i, 5 * i, 5 * i));

            heavyRaider[i] = heavyRaider1;
            rootNode.attachChild(heavyRaider1.getRootNode());
            rootNode.updateRenderState();
            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.YELLOW, heavyRaider1.getRootNode().getName(), heavyRaider1.getRootNode().getLocalTranslation());
        }
    }

    private void loadRaiders() {
        raider = new BaseModelObject[3];
        for (int i = 0; i < raider.length; i++) {
            BaseModelObject tmpRaider = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/raider.jme");

            tmpRaider.getRootNode().setName("raider" + i);
            NodeRotator.rotate(tmpRaider.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
            NodeRotator.rotate(tmpRaider.getRootNode(), Model.YAW, (float) Math.toRadians(180));
            NodeRotator.rotate(tmpRaider.getRootNode(), Model.PITCH, (float) Math.toRadians(90));

            OrientedBoundingBox bx = new OrientedBoundingBox();

            bx.setCenter(tmpRaider.getRootNode().getLocalTranslation());
            bx.setExtent(new Vector3f(.1f, .1f, .1f));
            tmpRaider.getRootNode().setModelBound(bx);
            tmpRaider.getRootNode().updateModelBound();
            tmpRaider.getRootNode().setIsCollidable(true);
//            tmpRaider.getRootNode().updateCollisionTree();

            tmpRaider.getRootNode().setLocalTranslation(new Vector3f(0, -.50f * i, -1));
//            tmpRaider.setAi(new RaiderAI(tmpRaider, rootNode, v2));
            raider[i] = tmpRaider;

            rootNode.attachChild(tmpRaider.getRootNode());
            rootNode.updateRenderState();

            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.RED, tmpRaider.getRootNode().getName(), tmpRaider.getRootNode().getLocalTranslation());
        }
    }

    private void loadRaptors() {
        raptor = new BaseModelObject[5];
        for (int i = 0; i < raptor.length; i++) {
            BaseModelObject raptor1 = BModelFactory.getInstance().createModelTest(TEXTURE_PATH, "dradis/models/raptor.jme");

            raptor1.getRootNode().setName("raptor" + i);
            NodeRotator.rotate(raptor1.getRootNode(), Model.ROLL, (float) Math.toRadians(180));
            NodeRotator.rotate(raptor1.getRootNode(), Model.YAW, (float) Math.toRadians(180));
            NodeRotator.rotate(raptor1.getRootNode(), Model.PITCH, (float) Math.toRadians(90));
            raptor1.getRootNode().setIsCollidable(true);
//            OrientedBoundingBox bx = new OrientedBoundingBox();
//
//            bx.setCenter(raptor1.getRootNode().getLocalTranslation());
//            bx.setExtent(new Vector3f(.1f, .1f, .1f));
//            raptor1.getRootNode().setModelBound(bx);
//            raptor1.getRootNode().updateModelBound();
//            raptor1.getRootNode().setIsCollidable(true);
//            raptor1.getRootNode().updateCollisionTree();

            raptor1.getRootNode().setLocalTranslation(new Vector3f(-5 * i, -5 * i, -5 * i));

            raptor[i] = raptor1;

            rootNode.attachChild(raptor1.getRootNode());
            rootNode.updateRenderState();
            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.BROWN, raptor1.getRootNode().getName(), raptor1.getRootNode().getLocalTranslation());
        }
    }

    private void loadPlayerLocator() {
        PlayerLocator.getInstance().load(rootNode, v2.getRootNode());
        PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.BLUE, v2.getRootNode().getName(), v2.getRootNode().getLocalTranslation());
        rootNode.attachChild(PlayerLocator.getInstance().getNode());
        rootNode.updateRenderState();
    }

    private void loadStaticHud() {
        //todo: this is where I want all of the nodes to be created from
        //      for a map level.
        //      it will center the Galactica on the screen (initially), then enemies, etc will be placed
        //      from here... later adding the timer induced stuff (like DRADIS contact....) ,etc.

        StaticHud.getInstance().load();
        StaticHud.getInstance().getNode().setIsCollidable(false);
        //todo: fix so that this doesn't display when the rest of the dradis display is up
        rootNode.attachChild(StaticHud.getInstance().getNode());
        rootNode.updateRenderState();
    }

    public Node getRootNode() {
        return rootNode;
    }
}