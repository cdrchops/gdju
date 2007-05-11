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
package com.gerbildrop.dradis.ai.basics;

import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.ships.Viper2;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

/**
 * Controller for Intercept Chase and Evade
 *
 * @author Yves Tanas
 * @version 0.1
 */
public class InterceptController extends AIBasics {

    /** Float for Velocity */
    private float predvel = .75f;
    /**
     * The direction our prey moves
     */
//    Vector3f preymove;// = new Vector3f(-0.25f,0.55f,0.15f).normalize();

    /**
     * Constructor for setting up the new Controller
     *
     * @param predator Spatial The Chaser in here
     * @param prey     Spatial The Evader in here
     */
    public InterceptController(Spatial predator, Viper2 prey) {
        this.predator = predator;
        this.prey = prey;
    }

    public void updateCycle(float time) throws AlreadyGotcha {

        // Get The Positions of our Spatials
        predpos.set(predator.getLocalTranslation());
        preypos.set(prey.getNode().getLocalTranslation());
        // Check for Equality
        if (preypos.equals(predpos)) throw new AlreadyGotcha();
//        prey.setLocalTranslation(preypos.add(preymove.mult(preyvel)));

        doIntercept(time);
    }

    private static final Vector3f tempVa = new Vector3f();

    private void doIntercept(float time) {
        Spatial prey2 = prey.getNode();
        Vector3f preyLocal = prey2.getLocalTranslation();
        Quaternion preyQuat = prey2.getLocalRotation();

        //figure out which direction the prey is moving
        // rotate to match that direction

        System.out.println("prey x " + preyQuat.x);
        System.out.println("prey y " + preyQuat.y);
        System.out.println("prey z " + preyQuat.z);

        Quaternion predQuat = predator.getLocalRotation();

        System.out.println("pred x " + predQuat.x); // * -1 means forward
        System.out.println("pred y " + predQuat.y);
        System.out.println("pred z " + predQuat.z);

        float x = preyQuat.x - predQuat.x;
        float y = preyQuat.y - predQuat.y;
        float z = preyQuat.z - predQuat.z;

        System.out.println("x " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);

        if (x == 0) {
            NodeRotator.rotate(predator, Model.PITCH, Math.toRadians(180));
            predvel *= -1;
        }

        predator.getLocalTranslation().addLocal(predator.getLocalRotation().getRotationColumn(Model.YAW, tempVa).multLocal(predvel * time));

//        Vector3f u = prey.getNode().getLocalTranslation().subtract(predpos).normalize();
//        Vector3f vr = preymove.mult(prey.getVelocity()).subtract(u.mult(predvel));
//        Vector3f sr = preypos.subtract(predpos);
//        float tc = sr.distance(vr);
//        Vector3f st = preypos.add(preymove.mult(prey.getVelocity()).mult(tc));

//        u = st.subtract(predpos).normalize();

//        predator.setLocalTranslation(predpos.add(u.mult(predvel)));
    }
}