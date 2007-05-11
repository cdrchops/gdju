package com.gerbildrop.engine.audio;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;

import com.jme.math.Vector3f;
import com.jmex.sound.openAL.SoundSystem;

public class SoundManager {
    public static final int FIRING_SOUND = 3;

    private static final SoundManager _INSTANCE = new SoundManager();

    private static Map<String, Sound> soundMap = new HashMap<String, Sound>();

    private SoundManager() {}

    public static SoundManager getInstance() {
        return _INSTANCE;
    }

    public void playSound(String name, Vector3f localTranslation) {
        Sound sound = soundMap.get(name);
        SoundSystem.setSamplePosition(sound.getSound(), localTranslation.x + 5, localTranslation.y, localTranslation.z);
        SoundSystem.onEvent(sound.getSnode());
    }

    public void initSound(URL soundSample, String soundName, int bindingEvent) {
        Sound sound = new Sound();
        sound.setName(soundName);
        sound.setBoundEvent(bindingEvent);
        sound.setSnode(SoundSystem.createSoundNode());
        sound.setSound(SoundSystem.create3DSample(soundSample));

        SoundSystem.bindEventToSample(sound.getSound(), sound.getBoundEvent());

        SoundSystem.setSampleMaxAudibleDistance(sound.getSound(), 5000);
        SoundSystem.addSampleToNode(sound.getSound(), sound.getSnode());

        soundMap.put(sound.getName(), sound);
    }
}