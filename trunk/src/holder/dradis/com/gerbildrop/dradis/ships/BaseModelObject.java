package com.gerbildrop.dradis.ships;

import java.net.URL;

import com.gerbildrop.dradis.ships.basic.Gun;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.ai.AI;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.CullState;
import com.jme.system.DisplaySystem;

public class BaseModelObject {
    protected URL _texturePath = null;
    protected URL _modelPath = null;
    protected Node rootNode;
    protected int textureWidth;
    protected int textureHeight;
    protected Quad modelQuad;
    protected Gun gun = new Gun();
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
    private AI rai;

    /** X */
    public final static int PITCH = 0;//x
    /** Y */
    public final static int YAW = 1;//y
    /** Z */
    public final static int ROLL = 2;//z

    public static final Vector3f tempVa = new Vector3f();

    public URL getModelPath() {
        return _modelPath;
    }

    public void setModelPath(final URL modelPath) {
        _modelPath = modelPath;
    }

    public URL getTexturePath() {
        return _texturePath;
    }

    public void setTexturePath(final URL texturePath) {
        _texturePath = texturePath;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(final float acceleration) {
        this.acceleration = acceleration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public void setAmmoCount(final int ammoCount) {
        this.ammoCount = ammoCount;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(final boolean armed) {
        this.armed = armed;
    }

    public float getBraking() {
        return braking;
    }

    public void setBraking(final float braking) {
        this.braking = braking;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(final boolean damaged) {
        this.damaged = damaged;
    }

    public float getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(final float damageLevel) {
        this.damageLevel = damageLevel;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(final boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(final Vector3f direction) {
        this.direction = direction;
    }

    public boolean isDradisRange() {
        return dradisRange;
    }

    public void setDradisRange(final boolean dradisRange) {
        this.dradisRange = dradisRange;
    }

    public float getFiringRange() {
        return firingRange;
    }

    public void setFiringRange(final float firingRange) {
        this.firingRange = firingRange;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(final boolean friendly) {
        this.friendly = friendly;
    }

    public float getGravitationalForce() {
        return gravitationalForce;
    }

    public void setGravitationalForce(final float gravitationalForce) {
        this.gravitationalForce = gravitationalForce;
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(final Gun gun) {
        this.gun = gun;
    }

    public boolean isInFiringRange() {
        return inFiringRange;
    }

    public void setInFiringRange(final boolean inFiringRange) {
        this.inFiringRange = inFiringRange;
    }

    public boolean isInSpace() {
        return inSpace;
    }

    public void setInSpace(final boolean inSpace) {
        this.inSpace = inSpace;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(final boolean launched) {
        this.launched = launched;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(final int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(final float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxTurnSpeed() {
        return maxTurnSpeed;
    }

    public void setMaxTurnSpeed(final float maxTurnSpeed) {
        this.maxTurnSpeed = maxTurnSpeed;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(final float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Quad getModelQuad() {
        return modelQuad;
    }

    public void setModelQuad(final Quad modelQuad) {
        this.modelQuad = modelQuad;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(final boolean moving) {
        this.moving = moving;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(final int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(final boolean on) {
        this.on = on;
    }

    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(final float powerLevel) {
        this.powerLevel = powerLevel;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(final Node rootNode) {
        this.rootNode = rootNode;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(final int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(final int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public float getTurnSpeed() {
        return turnSpeed;
    }

    public void setTurnSpeed(final float turnSpeed) {
        this.turnSpeed = turnSpeed;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(final float velocity) {
        this.velocity = velocity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float weight) {
        this.weight = weight;
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

    public void setLocalValues() {
        rootNode.setLocalTranslation(new Vector3f(0.0F, 0.0F, 0F));
        rootNode.setLocalScale(0.1F);
        rootNode.updateGeometricState(0.0F, true);

        if (rootNode.getChild(0).getControllers().size() != 0) {
            rootNode.getChild(0).getController(0).setSpeed(20);
        }

        CullState cullState = DisplaySystem.getDisplaySystem().getRenderer().createCullState();
        cullState.setCullMode(CullState.CS_BACK);
    }

    public void update(float time) {
        rootNode.getLocalTranslation().addLocal(rootNode.getLocalRotation().getRotationColumn(Model.YAW, tempVa).multLocal(getVelocity() * time));
        rootNode.updateRenderState();
    }

    public void update() {
        rootNode.updateRenderState();
    }

    public void fire(Node rootNode) {
        gun.fire(rootNode);
        rootNode.updateCollisionTree();
        rootNode.updateRenderState();
//        getFiringSound().play();
    }

    public AI getAi() {
        return rai;
    }

    public void setAi(AI rai) {
        this.rai = rai;
        getRootNode().addController(rai);
    }
}
