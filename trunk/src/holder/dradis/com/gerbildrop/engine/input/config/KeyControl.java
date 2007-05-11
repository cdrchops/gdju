package com.gerbildrop.engine.input.config;

public class KeyControl {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }


    public String toString() {
        return "KeyControl{" +
               "name='" + name + '\'' +
               ", value='" + value + '\'' +
               '}';
    }
}
