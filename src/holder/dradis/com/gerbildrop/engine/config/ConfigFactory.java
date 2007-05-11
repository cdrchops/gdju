package com.gerbildrop.engine.config;

import java.util.logging.Logger;

import com.gerbildrop.engine.util.ReflectionHelper;
import com.gerbildrop.engine.util.StringUtil;
import com.gerbildrop.engine.xml.DxoDoc;

public class ConfigFactory {
    private static final ConfigFactory INSTANCE = new ConfigFactory();
    private static final Logger logger = Logger.getLogger(ConfigFactory.class.getName());

    private ConfigFactory() {}

    public static ConfigFactory getInstance() {
        return ConfigFactory.INSTANCE;
    }

    public Config createConfig(String className) {
        return createConfig(className, null);
    }

    public Config createConfig(String className, String fileName) {
        Config cf = null;

        try {
            Class c = Class.forName(className);
            cf = createConfig(c, fileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cf;
    }

    public Config createConfig(Class className, String fileName) {
        Config cf = null;

        try {
            Object o = className.newInstance();
            cf = createConfig(o, fileName);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return cf;
    }

    public Config createConfig(Object className, String fileName) {
        String str = null;
        if (!StringUtil.hasLen(fileName)) {
            str = ReflectionHelper.stringGetter(className, "getConfigFile");
        } else {
            str = fileName;
        }

        DxoDoc dxo = DxoDoc.parseFile(str);
        ReflectionHelper.runMethodInvocation(className, "setResources", new Class[]{DxoDoc.class}, new Object[]{dxo});

        return (Config) className;
    }
}
