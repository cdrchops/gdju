package com.gerbildrop.engine.spatial.base;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gerbildrop.engine.spatial.weaponry.Barrel;
import com.gerbildrop.engine.spatial.weaponry.Gun;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.CullState;
import com.jme.system.DisplaySystem;
import com.jmex.sound.openAL.SoundSystem;

public class BaseModelObject extends Node {
    public static final int FIRING_SOUND = 3;
    protected int snode;
    protected int firingSound;
    protected Node rootNode;
    protected TextureObject textureObject;
    protected URL _modelPath = null;

    protected Quad modelQuad;
    protected List<Gun> gunList;
    protected float damageLevel;
    protected boolean damaged;
    protected boolean destroyed;
    protected boolean friendly;
    protected boolean active;
    protected float powerLevel;
    protected boolean on;
    protected boolean moving;
    protected boolean launched;
    protected boolean armed;
    protected boolean dradisRange;
    protected boolean inSpace;
    protected float gravitationalForce;
    protected int maxAmmo;
    protected int ammoCount;
    protected float firingRange;
    protected boolean inFiringRange;
    protected int numberOfGuns;
    private float acceleration;
    private float braking;
//    private OpenALSoundNode music;

    /** X */
    public final static int PITCH = 0;//x
    /** Y */
    public final static int YAW = 1;//y
    /** Z */
    public final static int ROLL = 2;//z

    public static final Vector3f tempVa = new Vector3f();

    public BaseModelObject() {
        super();
        init();
    }

    public BaseModelObject(Node rootNode, String string) {
        this(string);
        this.rootNode = rootNode;
        init();
    }

    public BaseModelObject(String string) {
        super(string);
        init();
    }

    private void init() {
        gunList = new ArrayList<Gun>();

//        initializeWeaponry();
    }

    public URL getModelPath() {
        return _modelPath;
    }

    public void setModelPath(final URL modelPath) {
        _modelPath = modelPath;
    }

    public TextureObject getTextureObject() {
        return textureObject;
    }

    public void setTextureObject(final TextureObject textureObject) {
        this.textureObject = textureObject;
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


    public void setLocalValues() {
        setLocalTranslation(new Vector3f(0.0F, 0.0F, 0F));
        setLocalScale(0.1F);
        updateGeometricState(0.0F, true);

        if (getChild(0).getControllers().size() != 0) {
            getChild(0).getController(0).setSpeed(20);
        }

        CullState cullState = DisplaySystem.getDisplaySystem().getRenderer().createCullState();
        cullState.setCullMode(CullState.CS_BACK);
    }

    public void fire() {
        for (Gun gun : gunList) {
            gun.fire(rootNode);
        }

        updateRenderState();
        playFiringSound(FIRING_SOUND);
    }

    protected void removeGun(Gun gun) {
        gunList.remove(gun);
    }

    protected void removeGun(int index) {
        gunList.remove(index);
    }

    private void playFiringSound(int sound) {
        SoundSystem.setSamplePosition(firingSound, localTranslation.x + 5, localTranslation.y, localTranslation.z);
        SoundSystem.onEvent(sound);
    }

    public void initializeWeaponry() {
        Gun gun = new Gun();
        gun.addBarrel(new Barrel(new Vector3f(.15f, .10f, 0), this, rootNode));

        Gun gun2 = new Gun();
        gun2.addBarrel(new Barrel(new Vector3f(-.15f, .10f, 0), this, rootNode));

        gunList.add(gun);
        gunList.add(gun2);
    }
}
