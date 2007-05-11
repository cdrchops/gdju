package com.gerbildrop.lua;

public class LuaTest {
    public static void main(String[] args) {
        LuaUtil.doFile("hello");
        LuaUtil.doFile("hello2");
        LuaUtil.doString("print \"Hello World from Lua!\"");
        System.out.println("Hello World from Java!");
    }
}