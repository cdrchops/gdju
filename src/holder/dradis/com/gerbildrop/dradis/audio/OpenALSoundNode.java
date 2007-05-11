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

/*
 * Created on 29-mar-2006
 * @author Alberto Plebani
 *
 * This class is based on the OpenAL LWJGL tutorial. It is adapted to allow
 * the creation of a com.jme.scene.Node which can be attached to other Node
 * istances. The sound, obviously, will follow the Node itself!
 *
 * The following is the right way to operate with an OpenALSoundNode:
 *
 * Node aSimpleNode=new Node("NodeName");
 * ...
 *
 * OpenALSoundNode music=new OpenALSoundNode("MusicNode");
 * music.setSampleAddress("turn.wav"); //Address as a String
 * music.setCamera(this.cam); //Our cam becomes our ears!
 * music.setLoop(true); //If you want looping (by default is false)
 * music.play(); //Now listen!
 * aSimpleNode.attachChild(music);
 *
 * ...
 * music.stop() //Easy to understand!
 *
 * There are also a few methods to retrieve and change the state of the sample.
 *
 * If you want to have your sound used as a background sound, you should
 * create an OpenALSoundNode and attach it to your cameraNode. By this
 * way your sound Node will always follow your camera and will always face
 * it the same way: so you'll have your soundtrack. Maybe if i'll have
 * time i'll create a method which easily configure this without the need
 * of a CameraNode.
 *
 * This is still a basic implementation, but makes its job well!
 * Only one note: actually this class is able to load only .wav sound file.
 *
 * FEEL FREE TO MODIFY THIS CLASS FOR ANY PURPOSE AND REMEMBER TO TEST THIS
 * CLASS WHICH HAS NOT BEEN FULLY TESTED!
 *
 * TODO:
 * Implement various sound format reading
 * Implement event triggers (actually you have to start playing manually)
 * Implement soundtrack feature (as previously explained)
 *
 */
package com.gerbildrop.dradis.audio;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/** @author Alberto Plebani */
public class OpenALSoundNode extends Node {

    private Vector3f lastPosition;
    private String sampleAddress;
    private boolean loop = false;
    private Camera camera;
    private Vector3f lastCameraLocation, lastCameraDirection, lastCameraUp;

    public static final int PLAYING = 0;
    public static final int PAUSED = 1;
    public static final int STOPPED = 2;
    public static final int UNDEFINED = 3;

    private IntBuffer buffer = BufferUtils.createIntBuffer(1);
    private IntBuffer source = BufferUtils.createIntBuffer(1);
    private FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
    private FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
    private FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
    private FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
    private FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});

    /**
     * This is the only costructor. Use this every time you want to instantiate a new OpenALSoundNode (long class name
     * only because SoundNode is already in use by two different classes!)
     *
     * @param nodeName: the name of the Node that you want to assign.
     */
    public OpenALSoundNode(String nodeName) {
        super(nodeName);
        this.lastPosition = this.getWorldTranslation().add(this.getLocalTranslation());
        initAL();
        initFlip();
        initPos();
    }

    /**
     * This method is used to set ear position. Because usually we want to set ears in the exact place of the camera and
     * with the same orientation, the easiest way to obtain this is to use the setCamera method.
     *
     * @param camera: the camera we are actually using.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
        this.lastCameraLocation = this.camera.getLocation();
        this.lastCameraDirection = this.camera.getDirection();
        this.lastCameraUp = this.camera.getUp();
        adjustCameraData();
    }

    /**
     * Users should never call this method manually! This method is responsible for setting data regarding ears position
     * and orientation (equivalent to the cam).
     */
    private void adjustCameraData() {
        // TODO Auto-generated method stub
        listenerPos.put(0, camera.getLocation().x);
        listenerPos.put(1, camera.getLocation().y);
        listenerPos.put(2, camera.getLocation().z);

        listenerOri.put(0, camera.getDirection().x);
        listenerOri.put(1, camera.getDirection().y);
        listenerOri.put(2, camera.getDirection().z);
        listenerOri.put(3, camera.getUp().x);
        listenerOri.put(4, camera.getUp().y);
        listenerOri.put(5, camera.getUp().z);
        AL10.alListener(AL10.AL_POSITION, listenerPos);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
    }

    /** Users should never call this method manually! This method is responsible for basic inizialization. */
    private void initPos() {
        // TODO Auto-generated method stub
        AL10.alListener(AL10.AL_POSITION, listenerPos);
        AL10.alListener(AL10.AL_VELOCITY, listenerVel);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
    }

    /** Users should never call this method manually! This method is responsible for basic inizialization. */
    private void initFlip() {
        // TODO Auto-generated method stub
        sourcePos.flip();
        sourceVel.flip();
        listenerPos.flip();
        listenerVel.flip();
        listenerOri.flip();
    }

    /**
     * This method returns the last location of the Node.
     *
     * @return the lastPosition.
     */
    public Vector3f getLastPosition() {
        return lastPosition;
    }

    /**
     * This method is used to load the sample. Call this passing it a valid address String.
     *
     * @param sampleAddress The sample address to load.
     */
    public int setSampleAddress(String sampleAddress) {
        this.sampleAddress = sampleAddress;

        // Load wav data into a buffer.
        AL10.alGenBuffers(buffer);

        if (AL10.alGetError() != AL10.AL_NO_ERROR)
            return AL10.AL_FALSE;

        WaveData waveFile = WaveData.create(sampleAddress);

        AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();

        // Bind the buffer with the source.
        AL10.alGenSources(source);

        if (AL10.alGetError() != AL10.AL_NO_ERROR)
            return AL10.AL_FALSE;

        AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buffer.get(0));
        AL10.alSourcef(source.get(0), AL10.AL_PITCH, 1.0f);
        AL10.alSourcef(source.get(0), AL10.AL_GAIN, 1.0f);
        AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos);
        AL10.alSource(source.get(0), AL10.AL_VELOCITY, sourceVel);

        if (this.loop) {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
        } else {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_FALSE);
        }

        // Do another error check and return.
        if (AL10.alGetError() == AL10.AL_NO_ERROR)
            return AL10.AL_TRUE;

        return AL10.AL_FALSE;
    }


    /**
     * This method is used to make the sample playing in loop
     *
     * @param lo: set to true if you want loop or false if not.
     */
    public void setLoop(boolean lo) {
        this.loop = lo;
        if (this.loop) {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
        } else {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_FALSE);
        }
    }

    /** Users should never call this method manually! This method is responsible for basic inizialization. */
    private void initAL() {
        // TODO Auto-generated method stub
        if (!AL.isCreated()) {
            try {
                AL.create(null, 15, 22050, true);
            } catch (LWJGLException le) {
                le.printStackTrace();
                return;
            }
            AL10.alGetError();
        }
    }


    /**
     * This method draw the Node if it is a geometric. In this special Node implementation, method drwaw simply adjusts
     * the sound source location and/or the ear location and orientation, but only if one of this has changed since last
     * draw. This method is called through the various update chain.
     *
     * @see com.jme.scene.Spatial#draw(com.jme.renderer.Renderer)
     */
    public void draw(Renderer r) {
        //System.out.println("Check");
        Vector3f actualPosition = this.getWorldTranslation().add(this.getLocalTranslation());
        //System.out.println(actualPosition+":"+this.lastPosition);
        if (!actualPosition.equals(this.lastPosition)) {
            //System.out.println("DifferentPosition");
            this.lastPosition = new Vector3f(actualPosition);
            this.updateLocation();
        }
        Vector3f camActualLocation = this.camera.getLocation();
        Vector3f camActualDirection = this.camera.getDirection();
        Vector3f camActualUp = this.camera.getUp();

        if ((!(camActualDirection.equals(this.lastCameraDirection))) || (!(camActualLocation.equals(this.lastCameraLocation))) || (!(camActualUp.equals(this.lastCameraUp))))
        {
            //System.out.println("DifferentEars");
            this.lastCameraLocation = new Vector3f(this.camera.getLocation());
            this.lastCameraDirection = new Vector3f(this.camera.getDirection());
            this.lastCameraUp = new Vector3f(this.camera.getUp());
            adjustCameraData();
        }

    }

    /** Users should never call this method manually! This method is responsible for updating the source location. */
    private void updateLocation() {
        // TODO Auto-generated method stub
        sourcePos.put(0, this.lastPosition.x);
        sourcePos.put(1, this.lastPosition.y);
        sourcePos.put(2, this.lastPosition.z);
        AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos);
    }

    /** This method plays the sample. */
    public void play() {
        AL10.alSourcePlay(source.get(0));
    }

    /** This method stops the sample. */
    public void stop() {
        AL10.alSourceStop(source.get(0));
    }

    /** This method pauses the sample. Another call to play() makes the sample playing from where it was paused. */
    public void pause() {
        AL10.alSourcePause(source.get(0));
    }

    /**
     * This method check if the sample is playing.
     *
     * @return true if the sample is actually playing
     */
    public boolean isPlaying() {
        int result = AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE);
        if (result == AL10.AL_PLAYING) {
            return true;
        }
        return false;
    }

    /**
     * This method check if the sample is stopped.
     *
     * @return true if the sample is actually stopped
     */
    public boolean isStopped() {
        int result = AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE);
        if (result == AL10.AL_STOPPED) {
            return true;
        }
        return false;
    }

    /**
     * This method check if the sample is paused.
     *
     * @return true if the sample is actually paused
     */
    public boolean isPaused() {
        int result = AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE);
        if (result == AL10.AL_PAUSED) {
            return true;
        }
        return false;
    }

    /**
     * This method check the state. There are three states allowed: playing, stopped and paused, which refers to the
     * state of the sample. There is another state which is undefined. This is used when the state of the sample is not
     * in one of the other states.
     *
     * @return The state by the use of the constants defined in this class.
     */
    public int checkState() {
        int result = AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE);
        if (result == AL10.AL_PAUSED) {
            return OpenALSoundNode.PAUSED;
        }
        if (result == AL10.AL_PLAYING) {
            return OpenALSoundNode.PLAYING;
        }
        if (result == AL10.AL_STOPPED) {
            return OpenALSoundNode.STOPPED;
        }
        return OpenALSoundNode.UNDEFINED;
    }

    public void setPitch(float pitch) {
        AL10.alSourcef(source.get(0), AL10.AL_PITCH, pitch);
    }

    public void setVolume(float volume) {
        AL10.alSourcef(source.get(0), AL10.AL_GAIN, volume);
    }

    public void setRollOff(float rollOff) {
        AL10.alSourcef(source.get(0), AL10.AL_ROLLOFF_FACTOR, rollOff);
    }

}
