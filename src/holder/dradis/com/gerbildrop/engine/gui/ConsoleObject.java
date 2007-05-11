package com.gerbildrop.engine.gui;

public class ConsoleObject {
    private String registryKey;
    private Object registryObject;

    public ConsoleObject(final String registryKey, final Object registryObject) {
        this.registryKey = registryKey;
        this.registryObject = registryObject;
    }

    public String getRegistryKey() {
        return registryKey;
    }

    public void setRegistryKey(final String registryKey) {
        this.registryKey = registryKey;
    }

    public Object getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(final Object registryObject) {
        this.registryObject = registryObject;
    }
}
