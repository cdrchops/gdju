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

package com.gerbildrop.scripting;

import com.gerbildrop.scripting.Actor;
import com.gerbildrop.scripting.Grunt;

class Scripting {
    static final int BASE = 1;
    static final int armor = 2;
    static final int shield = 4;
    static final int weapon = 8;

    public static def checkAttributes = {int staticAttr, int attrToCheck -> return (staticAttr & attrToCheck) == attrToCheck;}
    public static def hasArmor = {Actor g -> return checkAttributes.call(g.attributes, armor)}
    public static def hasShield = {Actor g -> return checkAttributes.call(g.attributes, shield)}
    public static def hasWeapon = {Actor g -> return checkAttributes.call(g.attributes, weapon)}
    public static def isActor = {g -> g instanceof Actor}
    public static def attack = {
        //this should process some sort of attack, damage points, something
        "attacking"
    }
    public static def eq = {int g, int value -> g == value}
    public static def lt = {int g, int value -> g < value}
    public static def lte = {int g, int value -> g <= value}
    public static def gt = {int g, int value -> g > value}
    public static def gte = {int g, int value -> g >= value}

    public static def checkHealth = {Actor g, int value, Closure c -> c.call(g.health, value)}
    public static def x = {Actor g, Closure check, Closure action -> if (check.call(g)) {return action.call()}}

    public static def blankLine = {println ""}

    public static def or = {Actor g, Object o->
         //set our returnValue
        boolean returnValue = false;
        //cast our Object to a Closure[]
        Closure[] cl = (Closure[]) o;
        //set the maxSize for the for...loop
        def maxSize = cl.size() - 1;

        //run the for loop
        for(i in 0..maxSize) {
            //if this closure call is false, then we want to break out, b/c we're not going to do whatever
            // the final script closure calls for
            if (cl[i](g)) {
                returnValue = true;
                break;
            }
        }

        return returnValue;
    }

    public static def and = {Actor g, Object o ->
         //set our returnValue
        boolean returnValue = true;
        //cast our Object to a Closure[]
        Closure[] cl = (Closure[]) o;
        //set the maxSize for the for...loop
        def maxSize = cl.size() - 1;

        //run the for loop
        for(i in 0..maxSize) {
            //if this closure call is false, then we want to break out, b/c we're not going to do whatever
            // the final script closure calls for
            if(!cl[i](g)) {
                returnValue = false;
                break;
            }
        }

        return returnValue;
    }

    /*attempt to consolidate all iteration calls -- failed miserably
    private static def rootItar = {Object o, Closure closure, Object o2, Closure c, int value, String type ->
            Actor[] act = (Actor[]) o;
            Closure[] clarr = (Closure[]) closure;

            int maxSize = act.size() - 1;

            for (i in 0..maxSize) {
                if (type == "itarAndOr") {
                    if (closure(act[i], clarr)) {
                        println c();
                    } else {
                        println "false";
                    }
                } else if (type == "itarHealth") {
                    println checkHealth(act[i], value, c);
                } else if (type == "itarX") {
                    x(act[i], closure, c);
                }
            }
        }
    */

    public static def itarAndOr = {Object o, Closure c1, Object o2, Closure c ->
            Actor[] act = (Actor[]) o;
            Closure[] cl = (Closure[]) o2;

            int maxSize = act.size() -1;

            for (i in 0..maxSize) {
                if(c1(act[i], cl)) {
                    println c();
                } else {
                    println "false";
                }
            }
    }

    public static def itarX = {Object o, Closure c, Closure c2 ->
        Actor[] act = (Actor[]) o;

        int maxSize = act.size() -1;

        for (i in 0..maxSize) {
           x.call(act[i], c, c2)
        }
    }

    public static def itarHealth = {Object o, int c, Closure c2 ->
        Actor[] act = (Actor[]) o;

        int maxSize = act.size() - 1;

        for (i in 0..maxSize) {
            checkHealth.call(act[i], c, c2)
        }
    }
}