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

import com.gerbildrop.dradis.cameras.BaseGamePlayCamera;
import com.gerbildrop.dradis.cameras.CameraManager;
import com.gerbildrop.dradis.cameras.CockpitCamera;
import com.gerbildrop.dradis.gui.PlayerLocator;
import com.gerbildrop.dradis.gui.StaticHud;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.ships.Battlestar;
import com.gerbildrop.dradis.ships.HeavyRaider;
import com.gerbildrop.dradis.ships.Raider;
import com.gerbildrop.dradis.ships.Raptor;
import com.gerbildrop.dradis.ships.Viper2;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.input.InputHandler;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.scene.Node;
import com.jme.system.DisplaySystem;

/**
 * @author vivaldi
 * @version $Id: WorldMap.java,v 1.3 2007/04/04 14:31:18 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class WorldMap {
    private static Viper2 v2 = null;
    private static Battlestar galactica = null;
    private static Battlestar pegasus = null;
    private static Raider[] raider = null;
    private static HeavyRaider[] heavyRaider = null;
    private static Raptor[] raptor = null;

    private static final DisplaySystem display = DisplaySystem.getDisplaySystem();
    private Node rootNode;
    private BaseGamePlayCamera cockpitCamera;
//    private BaseGamePlayCamera rearViewCamera;
    private InputHandler cih;
    private Node worldMapNode;
    private BaseGamePlayCamera currentCamera;

    private static boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void load(Node rootNode, Camera cam) {
        worldMapNode = new Node("World Map Node");

        this.rootNode = rootNode;
        cockpitCamera = new CockpitCamera();
        cockpitCamera.init(CameraManager.getInstance().getCamera(CameraManager.CameraView.MAIN), "Cockpit Camera Node");
//        rearViewCamera = new RearViewCamera();
//        rearViewCamera.init(CameraFactory.getInstance().get("topCam"), "Rear View Camera");

        currentCamera = cockpitCamera;
        loadViper2();
//        rearViewCamera.setTarget(v2.getNode());
        loadPlayerLocator();
        loadGalactica();
//        loadPegasus();
        loadRaiders();
        loadHeavyRaiders();
        loadRaptors();
        loadStaticHud();

        this.rootNode.attachChild(worldMapNode);

        KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();

        keyboard.set("cockpitCamera", KeyInput.KEY_F9);
        keyboard.set("rearViewCamera", KeyInput.KEY_F8);
        display.getRenderer().setCamera(cockpitCamera.getCamera());
        loaded = true;
    }

    public void update(float time) {
        currentCamera.update(time);
//        if (KeyBindingManager.getKeyBindingManager().isValidCommand("rearViewCamera", false)) {
//            currentCamera = rearViewCamera;
//            currentCamera.update(time);
////            v2.getFiringSound().setCamera(currentCamera.getCamera());
//            display.getRenderer().setCamera(currentCamera.getCamera());
//        }

        if (KeyBindingManager.getKeyBindingManager().isValidCommand("cockpitCamera", false)) {
            currentCamera = cockpitCamera;
            currentCamera.update(time);

            display.getRenderer().setCamera(currentCamera.getCamera());
        }

        v2.update(time);
        if (PlayerLocator.getInstance().isLoaded()) {
            if (raider != null) {
                for (Raider tmpRaider : raider) {
                    PlayerLocator.getInstance().update(tmpRaider.getNode().getName(), tmpRaider.getNode().getLocalTranslation(), time);
                }
            }

            if (heavyRaider != null) {
                for (HeavyRaider tmpRaider : heavyRaider) {
                    PlayerLocator.getInstance().update(tmpRaider.getNode().getName(), tmpRaider.getNode().getLocalTranslation(), time);
                }
            }
            if (raptor != null) {
                for (Raptor tmpRaider : raptor) {
                    PlayerLocator.getInstance().update(tmpRaider.getNode().getName(), tmpRaider.getNode().getLocalTranslation(), time);
                }
            }

            PlayerLocator.getInstance().update(v2.getNode().getName(), v2.getNode().getLocalTranslation(), time);
        }

        if (raider != null) {
            for (Raider tmpRaider : raider) {
                tmpRaider.update(time);
            }
        }

        StaticHud.getInstance().update(time);
    }

    public Viper2 getPlayer() {
        return v2;
    }

    private void loadGalactica() {
        galactica = new Battlestar();
        galactica.load();
        //looking down landing bay
        galactica.getNode().setLocalTranslation(new Vector3f(-20, 1.25f, -10));
        worldMapNode.attachChild(galactica.getNode());
    }

    private void loadPegasus() {
        pegasus = new Battlestar();
        pegasus.load(Battlestar.BattlestarType.PEGASUS);
        pegasus.getNode().setLocalTranslation(new Vector3f(-20, 1.25f, 50));
        worldMapNode.attachChild(pegasus.getNode());
    }

    private void loadViper2() {
        v2 = new Viper2();
        v2.load(worldMapNode);
        v2.getNode().setLocalTranslation(new Vector3f(0, -.25f, 1));
        NodeRotator.rotate(currentCamera.getCameraNode(), Model.PITCH, (float) Math.toRadians(90));
        v2.getNode().attachChild(currentCamera.getCameraNode());
//        v2.getFiringSound().setCamera(currentCamera.getCamera());
        worldMapNode.attachChild(v2.getNode());
    }

    private void loadHeavyRaiders() {
        heavyRaider = new HeavyRaider[3];
        for (int i = 0; i < heavyRaider.length; i++) {
            HeavyRaider heavyRaider1 = new HeavyRaider();
            heavyRaider1.load(i);
            heavyRaider[i] = heavyRaider1;

            heavyRaider1.getNode().setLocalTranslation(new Vector3f(5 * i, 5 * i, 5 * i));
            worldMapNode.attachChild(heavyRaider1.getNode());

            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.YELLOW, heavyRaider1.getNode().getName(), heavyRaider1.getNode().getLocalTranslation());
        }
    }

    private void loadRaiders() {
        raider = new Raider[3];
        for (int i = 0; i < raider.length; i++) {
            Raider tmpRaider = new Raider();
            tmpRaider.load(i);
//            RaiderAI rai = new RaiderAI(tmpRaider, worldMapNode, v2);
//            tmpRaider.setRai(rai);
            tmpRaider.getNode().setLocalTranslation(new Vector3f(0, -.50f * i, -1));
            worldMapNode.attachChild(tmpRaider.getNode());
            raider[i] = tmpRaider;

            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.RED, tmpRaider.getNode().getName(), tmpRaider.getNode().getLocalTranslation());
        }
    }

    private void loadRaptors() {
        raptor = new Raptor[5];
        for (int i = 0; i < raptor.length; i++) {
            Raptor raptor1 = new Raptor();
            raptor1.load(i);
            raptor[i] = raptor1;

            raptor1.getNode().setLocalTranslation(new Vector3f(-5 * i, -5 * i, -5 * i));
            worldMapNode.attachChild(raptor1.getNode());
            PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.BROWN, raptor1.getNode().getName(), raptor1.getNode().getLocalTranslation());
        }
    }

    private void loadPlayerLocator() {
        PlayerLocator.getInstance().load(worldMapNode, v2.getNode());
        PlayerLocator.getInstance().createNubbin(PlayerLocator.NubbinType.BLUE, v2.getNode().getName(), v2.getNode().getLocalTranslation());
        worldMapNode.attachChild(PlayerLocator.getInstance().getNode());
    }

    private void loadStaticHud() {
        //todo: this is where I want all of the nodes to be created from
        //      for a map level.
        //      it will center the Galactica on the screen (initially), then enemies, etc will be placed
        //      from here... later adding the timer induced stuff (like DRADIS contact....) ,etc.

        StaticHud.getInstance().load();
        StaticHud.getInstance().getNode().setIsCollidable(false);
        //todo: fix so that this doesn't display when the rest of the dradis display is up
        worldMapNode.attachChild(StaticHud.getInstance().getNode());
    }

    public Node getNode() {
        return worldMapNode;
    }
}