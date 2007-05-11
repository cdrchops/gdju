package com.gerbildrop.lua;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class LuaUtil {
    private static final LuaState INSTANCE = LuaStateFactory.newLuaState();

    static {
        INSTANCE.openBasicLibraries();
    }

    public static LuaState getInstance() {
        return INSTANCE;
    }

    public static void doFile(String fileName) {
        getInstance().doFile(System.getProperty("user.dir") + "/src/dradis/com/gerbildrop/lua/" + fileName + ".lua");
    }

    public static void doString(String luaString) {
        getInstance().doString(luaString);
    }
}