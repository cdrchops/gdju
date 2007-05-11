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

import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingSphere;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Box;


/**
 * TestClass to test the Intercept Controller for functionality
 *
 * @author Yves Tanas
 * @version 0.1
 */
public class InterceptTest extends SimpleGame {

    /** This is the handler of our simple Chase and Evade Test */
    InterceptController ai;

    //// These are standard inputs not related to AI
    public static void main(String[] args) {
        InterceptTest app = new InterceptTest();
        app.setDialogBehaviour(ALWAYS_SHOW_PROPS_DIALOG);
        app.start();
    }

    Box box, box2;

    protected void simpleInitGame() {
        display.setTitle("Intercept Chase Example");
        box = new Box("my box", new Vector3f(), 2, 2, 2);
        box.setLocalTranslation(new Vector3f(0, 0, 0));
        box.setModelBound(new BoundingSphere());
        box.updateModelBound();
        rootNode.attachChild(box);

        box2 = new Box("my box", new Vector3f(), 2, 2, 2);
        box2.setLocalTranslation(new Vector3f(-10.0f, 0.0f, -20.0f));
        box2.setModelBound(new BoundingSphere());
        box2.updateModelBound();
        rootNode.attachChild(box2);
        //// End of Standard Inputs not realted to AI

        // This is adding the Controller to the Objects
//      ai = new InterceptController(box, box2);

    }

    protected void simpleUpdate() {
        try {
            ai.updateCycle(tpf);
        } catch (AlreadyGotcha ag) {
            System.out.println(ag.getMessage());
        }
    }
}