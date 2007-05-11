/*
 * Copyright (c) 2003-2006, GerbilDrop Java Utilities
 * http://gerbildrop.com
 * http://sourceforge.net/projects/gerbildrop
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Gerbildrop, GDJU, Gerbildrop Game Engine, Austin, StandTrooper, nor the
 * names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot;
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Copyright (c) 2006, Your Corporation. All Rights Reserved.
 */

package com.gerbildrop.io.properties;

import com.gerbildrop.io.util.IoUtil;
import junit.framework.TestCase;
import junit.textui.TestRunner;

public class PropertiesIoTest extends TestCase {
    public static void main(String[] args) {
        TestRunner.run(PropertiesIoTest.class);
    }

    private static final String fileName = "rph/keyConfig_en.properties";

    public void testProperties() {
        PropertiesReader p = PropertiesReader.getInstance(fileName);
        printResults(p);
    }

    public void testPropertiesSplitNameAndPath() {
        PropertiesReader p = PropertiesReader.getInstance("rph/", "keyConfig_en.properties");
        printResults(p);
    }

    public void testPropertiesInputStream() {
        PropertiesReader p = PropertiesReader.getInstance("keyConfig_en.properties", IoUtil.loadResourceAsStream(fileName));
        printResults(p);
    }

    private void printResults(PropertiesReader p) {
        System.out.println(p.getProperty("language"));
        System.out.println(p.getProperty("forward"));
        System.out.println(p.getProperty("backward"));
        System.out.println(p.getProperty("turnleft"));
        System.out.println(p.getProperty("turnright"));
        System.out.println(p.getProperty("strafeleft"));
        System.out.println(p.getProperty("straferight"));
        System.out.println(p.getProperty("moveup"));
        System.out.println(p.getProperty("movedown"));
        System.out.println(p.getProperty("turnup"));
        System.out.println(p.getProperty("turndown"));
        System.out.println(p.getProperty("screenshot"));
    }
}
