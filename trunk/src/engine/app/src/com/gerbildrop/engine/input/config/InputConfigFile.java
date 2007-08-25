package com.gerbildrop.engine.input.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gerbildrop.xml.DxoDoc;

public class InputConfigFile {
    private static List<KeyControl> keyConfigs = new ArrayList<KeyControl>();
    private static List<MouseControl> mouseConfigs = new ArrayList<MouseControl>();

    private static final InputConfigFile _INSTANCE = new InputConfigFile();

    private InputConfigFile() {}

    public static InputConfigFile getInstance() {
        return _INSTANCE;
    }

    public void addKeyConfig(KeyControl config) {
        keyConfigs.add(config);
    }

    public void addMouseControl(MouseControl config) {
        mouseConfigs.add(config);
    }

    public List<KeyControl> getKeyConfigs() {
        return keyConfigs;
    }

    public List<MouseControl> getMouseConfigs() {
        return mouseConfigs;
    }

    public String[] getNamesOfControls() {
        List<String> lst = new ArrayList<String>();
        for (KeyControl keyControl : keyConfigs) {
            lst.add(keyControl.getName());
        }

        for (MouseControl mouseControl : mouseConfigs) {
            lst.add(mouseControl.getName());
        }

        return lst.toArray(new String[]{});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (KeyControl keyControl : keyConfigs) {
            sb.append(keyControl);
        }

        for (MouseControl mouseControl : mouseConfigs) {
            sb.append(mouseControl);
        }

        return sb.toString();
    }

    private static final Map<String, Object> ALL_CONFIGS = new HashMap<String, Object>();

    static {
        ALL_CONFIGS.put("keyControl", KeyControl.class);
        ALL_CONFIGS.put("mouseControl", MouseControl.class);
    }

    public static void parseConfigs(DxoDoc doc) {
        doc.setObjectFromElements(InputConfigFile.getInstance(), ALL_CONFIGS);
    }
}