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

package com.gerbildrop.engine.input.config;

import java.lang.reflect.Field;

import com.jme.input.KeyInput;
import com.jme.input.controls.binding.MouseAxisBinding;

public class InputHelper {
    public static int characterDetection(String keyName) {

        if (keyName == null
            || keyName.length() <= 0) {
            return -1;
        }

        String vkName;
        KeyInput c = KeyInput.get();

        if (keyName.indexOf("KEY_") != -1) {
            vkName = keyName;
            /*
        this is for future support of other languages... not used right now
    } else if (InputConfigFile.getInstance().getLanguage().equals("de")) {
        vkName = "KEY_" + GermanMap.determineKeyInput(keyName);*/
        } else {
            vkName = "KEY_" + keyName;
        }

        int key = 0;
        Field field[] = c.getClass().getFields();
        for (Field field1 : field) {
            if (field1.getName().equals(vkName)) {
                try {
                    key = field1.getInt(Integer.class);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return key;
    }

    public static int mouseAxisDetection(String keyName) {

        if (keyName == null
            || keyName.length() <= 0) {
            return -1;
        }

        String vkName;

        if (keyName.indexOf("AXIS_") != -1) {
            vkName = keyName;
        } else {
            vkName = "AXIS_" + keyName;
        }

        int key = 0;

        if (vkName.equals("AXIS_Y")) {
            key = MouseAxisBinding.AXIS_Y;
        } else if (vkName.equals("AXIS_W")) {
            key = MouseAxisBinding.AXIS_W;
        } else if (vkName.equals("AXIS_X")) {
            key = MouseAxisBinding.AXIS_X;
        }

        return key;
    }
}