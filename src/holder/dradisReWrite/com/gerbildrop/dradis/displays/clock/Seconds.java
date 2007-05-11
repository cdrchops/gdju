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

package com.gerbildrop.dradis.displays.clock;

import java.util.Calendar;

import com.gerbildrop.dradis.loaders.DradisNodeStorage;
import com.gerbildrop.dradis.util.DradisNode;
import com.jme.math.Vector3f;
import com.jme.scene.Node;

public class Seconds {
    private static final DradisNode[] seconds = new DradisNode[61];

    public void load() {
        for (int i = 0; i < seconds.length; i++) {
            seconds[i] = DradisNodeStorage.get("seconds" + i + "Node");
        }

        drawSeconds(Calendar.getInstance().get(Calendar.SECOND));
    }

    private Node currentSecondNode;

    public void drawSeconds(int second) {
        if (second == 0) {
            second = 60;
        }

        currentSecondNode = seconds[second].getNode();
        currentSecondNode.setLocalTranslation(new Vector3f(411, 160, 0));//new Vector3f(389, 150, 0));
    }

    public void drawTimerSeconds(int second) {
        if (second == 0) {
            second = 60;
        }

        if (second < 0) {
            second = 0;
        }

        currentSecondNode = seconds[second].getNode();
        currentSecondNode.setLocalTranslation(new Vector3f(411, 160, 0));//new Vector3f(389, 150, 0));
    }

    public Node getNode() {
        return currentSecondNode;
    }

    public void update(float second, Node Node) {
        Node.detachChildNamed(currentSecondNode.getName());
        drawSeconds((int) second);
        Node.attachChild(currentSecondNode);
//        currentSecondNode.updateRenderState();
    }

    public void updateTimer(float second, Node Node) {
        Node.detachChildNamed(currentSecondNode.getName());
        drawTimerSeconds((int) second);
        Node.attachChild(currentSecondNode);
//        currentSecondNode.updateRenderState();
    }
}