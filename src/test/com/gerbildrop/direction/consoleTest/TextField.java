package com.gerbildrop.direction.consoleTest;

import com.jme.scene.Text;

public class TextField {
    protected Text textEntry;
    protected String _name;
    protected String _value;
    protected float _x;
    protected float _y;

    protected TextField() {}

    public TextField(final String name, final String value, final float x, final float y) {
        _name = name;
        _value = value;
        _x = x;
        _y = y;

        init();
    }

    protected void init() {
        textEntry = TextFactory.getInstance().createText(_name, _value, _x, _y);
    }

    public Text getText() {
        return textEntry;
    }

    public void print(String text) {
        textEntry.print(text);
    }

    public void print(StringBuffer sb) {
        textEntry.print(sb);
    }
}
