package com.gerbildrop.engine.input.config;

public class MouseControl {
    String name;
    String value;
    boolean reverse;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(final boolean reverse) {
        this.reverse = reverse;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }


    public String toString() {
        return "MouseControl{" +
               "name='" + name + '\'' +
               ", value='" + value + '\'' +
               ", reverse=" + reverse +
               '}';
    }
}
