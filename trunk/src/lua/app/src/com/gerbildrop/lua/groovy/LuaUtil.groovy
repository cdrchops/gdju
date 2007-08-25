package com.gerbildrop.lua.groovy;

public class LuaUtil {
    public static def doFile = {String fileName ->
        com.gerbildrop.lua.LuaUtil.doFile(fileName);
    }

    public static def doString = {String luaString ->
        com.gerbildrop.lua.LuaUtil.doString(luaString);
    }
}