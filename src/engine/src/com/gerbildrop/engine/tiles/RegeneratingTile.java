/*
 * Copyright (c) 2003-2006, GerbilDrop Java Utilities
 * http://gerbildrop.com
 * http://sourceforge.net/projects/gerbildrop
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Gerbildrop, GDJU, Gerbildrop Game Engine, Austin, StandTrooper, nor the
 * names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot;
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Copyright (c) 2006, Your Corporation. All Rights Reserved.
 */

package com.gerbildrop.engine.tiles;

public class RegeneratingTile implements TileConstants {

    public RegeneratingTile() {
        attributes = 0;
        animate = false;
        forward = true;
        startTime = 0L;
        currentIndex = 0;
        length = 6;
        done = false;
    }

    public void start(int attributes, long time) {
        this.attributes = attributes;
        startTime = time;
    }

    public void update(long elapsedTime) {
        forward = (attributes & 4) == 4;
        animate = (attributes & 1) == 1;
        long tmpTime = elapsedTime - startTime;
        if (!forward && tmpTime == 2000L) {
            attributes |= 1;
            startTime = elapsedTime;
        } else if (forward && animate)
            forwardLoop();
        else if (animate)
            reverseLoop();
    }

    public void forwardLoop() {
        System.out.println("forwardLoop");
        System.out.println("currentIndex " + currentIndex);
        if (currentIndex < length) {
            currentIndex++;
        } else {
            attributes &= -5;
            attributes &= -2;
        }
    }

    public void reverseLoop() {
        System.out.println("reverseLoop");
        System.out.println("currentIndex " + currentIndex);
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            attributes |= 4;
            attributes &= -2;
            done = true;
        }
    }

    public boolean isDone() {
        return done;
    }

    int attributes;
    boolean animate;
    boolean forward;
    long startTime;
    int currentIndex;
    int length;
    boolean done;
}
