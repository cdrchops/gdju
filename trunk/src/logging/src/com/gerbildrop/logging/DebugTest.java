package com.gerbildrop.logging;

import junit.textui.TestRunner;
import junit.framework.TestCase;

public class DebugTest extends TestCase {
    /**
     * our inline test class
     * not necessarily a test case
     */
    public static void main(String[] args) {
        TestRunner.run(DebugTest.class);
    }

    public void testDebug() {
        Debug.setDisplay(true);
        Debug.debug("this is a test");
        Debug.setDisplay(false);
        Debug.setLogMessage(true);
        Debug.debug("log this");
        Debug.error("logging this");
    }
}