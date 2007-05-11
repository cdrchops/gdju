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

package com.gerbildrop.dradis.ships;

//import com.gerbildrop.dradis.audio.OpenALSoundNode;

import com.gerbildrop.dradis.loaders.DradisModelStorage;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.ships.basic.Barrel;
import com.gerbildrop.dradis.ships.basic.Gun;
import com.gerbildrop.dradis.ships.basic.Vehicle;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;

/**
 * @author vivaldi
 * @version $Id: Viper2.java,v 1.29 2007/04/04 14:29:01 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Viper2 {
    private static final Vector3f tempVa = new Vector3f();

//    private static final OpenALSoundNode music;
//    static {
//        music = new OpenALSoundNode("Viper2FiringNode");
//    }

    Vehicle viperModel;
    Node rootNode;
    Gun gun = new Gun();

    public void load() {
        load(null);
    }

    public void load(Node rootNode) {
        if (rootNode != null) {
            this.rootNode = rootNode;
        }

        viperModel = DradisModelStorage.getVehicle("viper2");

//        viperModel.getNode().updateCollisionTree();

        if (viperModel.getNode().getChild(0).getControllers().size() != 0) {
            viperModel.getNode().getChild(0).getController(0).setSpeed(20);
        }

        viperModel.getNode().setName("viper Hoff");

        viperModel.setAcceleration(15);
        viperModel.setBraking(15);
        viperModel.setTurnSpeed(.25f);
        viperModel.setWeight(25);
        viperModel.setMaxSpeed(25);
        viperModel.setMinSpeed(5);

        viperModel.getNode().setRenderQueueMode(Renderer.QUEUE_OPAQUE);

        NodeRotator.rotate(viperModel.getNode(), Model.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(viperModel.getNode(), Model.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(viperModel.getNode(), Model.PITCH, (float) Math.toRadians(90));
        //viper laying on landing bay runway facing down landing bay
        viperModel.getNode().setLocalTranslation(new Vector3f(0, -.25f, 2));

        loadSound();
        Barrel left = new Barrel(.25f, viperModel.getNode(), rootNode);
        Barrel right = new Barrel(-.25f, viperModel.getNode(), rootNode);
        gun.addBarrel(left);
        gun.addBarrel(right);
    }

    public void loadSound() {
//        music.setSampleAddress("dradis/sounds/Firing.wav"); //Address as a String
//
//        music.setLoop(false); //If you want looping (by default is false)
////         music.play(); //Now listen!
//        getNode().attachChild(music);
////         music.stop(); //Easy to understand!
    }

//    public OpenALSoundNode getFiringSound() {
//        return music;
//    }

    public void update(float time) {
        viperModel.getNode().getLocalTranslation().addLocal(viperModel.getNode().getLocalRotation().getRotationColumn(Model.YAW, tempVa).multLocal(viperModel.getVelocity() * time));
    }

    public Node getNode() {
        return viperModel.getNode();
    }

    public void brake(float time) {
        viperModel.brake(time);
    }

    public void accelerate(float time) {
        viperModel.accelerate(time);
    }

    public void fire(Node rootNode) {
        gun.fire(rootNode);
//        getFiringSound().play();
    }
}