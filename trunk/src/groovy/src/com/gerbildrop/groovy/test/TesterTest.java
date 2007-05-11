package com.gerbildrop.groovy.test;

import com.gerbildrop.groovy.util.GroovyUtilities;

public class TesterTest {
    public static void main(String[] args) {
        new TesterTest().run();
    }

    public void run() {
        System.out.println("Starting TestInterface test");
        TestInterface ifc = (TestInterface) GroovyUtilities.gclInterfaceRunner("DRADIS2/src/dradis/com/gerbildrop/groovy/test/Tester.groovy");
        ifc.printIt();

        System.out.println("\nStarting Tester2 test");
        GroovyUtilities.gseRunner("DRADIS2/src/dradis/com/gerbildrop/groovy/test/", "Tester2");

        System.out.println("\nStarting inline GroovyShell test");
        GroovyUtilities.gshRunner("def i=0; i++; println i");
    }
}