package com.gerbildrop.groovy.test;

import com.gerbildrop.groovy.util.GroovyUtilities;

public class GroovyTest {
    public static void main(String[] args) {
        new GroovyTest().run();
    }

    public void run() {
        GroovyUtilities.gseRunner("DRADIS2/src/dradis/com/gerbildrop/lua/groovy/", "LuaTest");
    }
}
