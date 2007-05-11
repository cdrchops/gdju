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

package com.gerbildrop.dradis.ships.basic;

import com.jme.math.Vector3f;
import com.jme.math.FastMath;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;

/**
 * @author vivaldi
 * @version $Id: Vehicle.java,v 1.3 2007/04/04 14:30:52 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Vehicle extends BaseObject {
    protected float damageLevel;
    protected boolean damaged;
    protected boolean destroyed;
    protected boolean friendly;
    protected boolean active;
    protected float powerLevel;
    protected boolean on;
    protected float speed;
    protected Vector3f direction;
    protected boolean moving;
    protected boolean launched;
    protected boolean armed;
    protected boolean dradisRange;
    protected float maxTurnSpeed;
    protected float minSpeed;
    protected float maxSpeed;
    protected boolean inSpace;
    protected float gravitationalForce;
    protected int maxAmmo;
    protected int ammoCount;
    protected float firingRange;
    protected boolean inFiringRange;
    protected int numberOfGuns;
    private float weight;
    private float velocity;
    private float acceleration;
    private float braking;
    private float turnSpeed;

    public static final Vector3f tempVa = new Vector3f();

    /** X */
    public final static int PITCH = 0;//x
    /** Y */
    public final static int YAW = 1;//y
    /** Z */
    public final static int ROLL = 2;//z

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getBraking() {
        return braking;
    }

    public void setBraking(float braking) {
        this.braking = braking;
    }

    public float getTurnSpeed() {
        return turnSpeed;
    }

    public void setTurnSpeed(float turnSpeed) {
        this.turnSpeed = turnSpeed;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public void setAmmoCount(int ammoCount) {
        this.ammoCount = ammoCount;
    }

    public float getFiringRange() {
        return firingRange;
    }

    public void setFiringRange(float firingRange) {
        this.firingRange = firingRange;
    }

    public boolean isInFiringRange() {
        return inFiringRange;
    }

    public void setInFiringRange(boolean inFiringRange) {
        this.inFiringRange = inFiringRange;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public Quad getModelQuad() {
        return modelQuad;
    }

    public void setModelQuad(Quad modelQuad) {
        this.modelQuad = modelQuad;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public float getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(float damageLevel) {
        this.damageLevel = damageLevel;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public boolean isDradisRange() {
        return dradisRange;
    }

    public void setDradisRange(boolean dradisRange) {
        this.dradisRange = dradisRange;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public float getGravitationalForce() {
        return gravitationalForce;
    }

    public void setGravitationalForce(float gravitationalForce) {
        this.gravitationalForce = gravitationalForce;
    }

    public boolean isInSpace() {
        return inSpace;
    }

    public void setInSpace(boolean inSpace) {
        this.inSpace = inSpace;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxTurnSpeed() {
        return maxTurnSpeed;
    }

    public void setMaxTurnSpeed(float maxTurnSpeed) {
        this.maxTurnSpeed = maxTurnSpeed;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(float powerLevel) {
        this.powerLevel = powerLevel;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean vehicleIsMoving() {
        return velocity > FastMath.FLT_EPSILON
               || velocity < -FastMath.FLT_EPSILON;
    }

    /**
     * brake adjusts the velocity of the vehicle based on the braking speed. If the velocity reaches 0, braking will put
     * the vehicle in reverse up to the minimum speed.
     *
     * @param time the time between frames.
     */
    public void brake(float time) {
        velocity -= time * braking;
        if (velocity < -minSpeed) {
            velocity = -minSpeed;
        }
    }

    /**
     * accelerate adjusts the velocity of the vehicle based on the acceleration. The velocity will continue to raise
     * until maxSpeed is reached, at which point it will stop.
     *
     * @param time the time between frames.
     */
    public void accelerate(float time) {
        velocity += time * acceleration;
        if (velocity > maxSpeed) {
            velocity = maxSpeed;
        }
    }
}