package com.gerbildrop.lua.groovy;


//LUA
//working with LUA for BF and BFII mods
//don't really know why this is here
//don't really know how to apply it since the mods written in LUA won't help here
// but it's staying for now
def doFile = {String fileName ->
    LuaUtil.doFile.call(fileName);
}

def doString = {String luaString ->
    LuaUtil.doString.call(luaString);
}

doFile "hello";
doFile "hello2";

doString "print \"Hello World from Lua!\"";

println "Hello World from Java!";