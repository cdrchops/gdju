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

package com.gerbildrop.io.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlIoUtil {
    private UrlIoUtil() { }

    public static URL getNameAsURL(String fileName) {
        return getNameAsURL(fileName, UrlIoUtil.class);
    }

    public static URL getNameAsURL(String fileName, Class clazz) {
        clazz.getResource(fileName);
        URL url = clazz.getResource(fileName);
        if (url == null) {
            try {
                url = new URL("file", "", fileName);
            } catch (Exception urlException) {
                throw new RuntimeException("Unable to aquire resource: " + fileName + "\n" + urlException.getMessage());
            }
        }

        return url;
    }

    public void init(String[] args) {
        setBaseDirectory();
    }

    public static void setBaseDirectory() {
        if (baseDirectory == null) {
            try {
                String localDir = checkDirectory("\\data\\properties\\");
                File theDir = new File(localDir);
                baseDirectory = theDir.toURL();
            } catch (MalformedURLException e) {
                //"UrlIO:constructor : Unable to convert local directory to URL"
                e.printStackTrace();
            }
        }
    }

    public static URL getBaseDirectory() {
        if (baseDirectory == null) {
            setBaseDirectory();
        }

        return baseDirectory;
    }

    public static String checkDirectoryWithRoot(String directoryToAppend) {
        return directoryToAppend;
    }

    public static String checkDirectory(String directoryToAppend) {
        return "C:\\fromDesktop\\gdroplibs\\files\\" + directoryToAppend;
    }

    private static URL baseDirectory = null;
}