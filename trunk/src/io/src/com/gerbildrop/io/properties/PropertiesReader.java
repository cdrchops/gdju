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

package com.gerbildrop.io.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import com.gerbildrop.io.util.IoUtil;


public class PropertiesReader extends Properties {
    private static Hashtable<String, PropertiesReader> instance;

    private PropertiesReader() { }

    public static synchronized PropertiesReader getInstance(String fileName) {
        return getInstance(fileName.substring(0, fileName.lastIndexOf("/")), fileName.substring(fileName.lastIndexOf("/")));
    }

    public static synchronized PropertiesReader getInstance(String propertiesFilePath, String propertiesFileName) {
        return PropertiesReader.getInstance(propertiesFileName, IoUtil.loadResourceAsStream(propertiesFilePath + propertiesFileName));
    }
    public static synchronized PropertiesReader getInstance(String fileName, InputStream is) {
        if (instance == null) {
            instance = new Hashtable<String, PropertiesReader>();
        }

        if (instance.get(fileName) == null) {
            PropertiesReader pr = new PropertiesReader();
            try {
                pr.load(is);
                instance.put(fileName, pr);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        return instance.get(fileName);
    }

    public static void refreshReader() {
//        Debug.debug("refreshing propertiesReader");
        instance = null;
    }
}