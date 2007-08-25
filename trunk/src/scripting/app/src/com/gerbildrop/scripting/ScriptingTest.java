package com.gerbildrop.scripting;

import com.gerbildrop.groovy.util.GroovyUtilities;

public class ScriptingTest {
    public static void main(String[] args) {
        GroovyUtilities.gclRunner(System.getProperty("user.dir") + "/src/dradis/com/gerbildrop/scripting/ScriptingTest.groovy", new Object[]{}, "");
    }
}
