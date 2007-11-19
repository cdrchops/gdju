package com.gerbildrop.engine.audio;

public class Sound {
    private String name;
    private int boundEvent;
    private int snode;
    private int sound;

    public Sound() {}

    public int getBoundEvent() {
        return boundEvent;
    }

    public void setBoundEvent(final int _boundEvent) {
        this.boundEvent = _boundEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(final String _name) {
        this.name = _name;
    }

    public int getSnode() {
        return snode;
    }

    public void setSnode(final int _snode) {
        this.snode = _snode;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(final int _sound) {
        this.sound = _sound;
    }
}
