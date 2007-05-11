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

def armor = Scripting.armor;
def shield = Scripting.shield;
def weapon = Scripting.weapon;

def eq = Scripting.eq;
def checkHealth = Scripting.checkHealth;
def lte = Scripting.lte;
def gte = Scripting.gte;
def lt = Scripting.lt;
def gt = Scripting.gt;
def blankLine = {Scripting.blankLine();}
def x = Scripting.x;
def isActor = Scripting.isActor;
def attack = Scripting.attack;
def hasArmor = Scripting.hasArmor;
def hasShield = Scripting.hasShield;
def hasWeapon = Scripting.hasWeapon;
def or = Scripting.or;
def and = Scripting.and;

def itarAndOr = Scripting.itarAndOr;
def itarX = Scripting.itarX;
def itarHealth = Scripting.itarHealth;

g1 = new Grunt(attributes:armor | weapon | shield, health:7);
g2 = new Grunt(attributes:weapon)

//if our grunt hasWeapon && health >=5 we want to attack

if(checkHealth(g1, 5, gte)) {
    //need to consolidate these two items into one check
    println x.call(g1, hasWeapon, attack);
}