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

package com.gerbildrop.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.gerbildrop.logging.Debug;

/**
 * serialization library for ser beans to xml objects and decoding from xml to a ser object
 */
public final class XmlBeanUtil {
    public static final XmlBeanUtil INSTANCE = new XmlBeanUtil();

    private XmlBeanUtil() { }

    public static XmlBeanUtil getInstance() {
        return INSTANCE;
    }

    public Object decodeFromXml(byte bytes[]) {
        return decodeFromXml(new ByteArrayInputStream(bytes));
    }

    public Object decodeFromXml(InputStream inputStream) {
        XMLDecoder xmlDecoder = null;
        Object obj = null;
        try {
            xmlDecoder = new XMLDecoder(inputStream);
            obj = xmlDecoder.readObject();
        } catch (Exception e) {
            Debug.error(e);
        } finally {
            if (xmlDecoder != null) {
                xmlDecoder.close();
            }
        }
        return obj;
    }

    public void encodeAsXml(Object o, OutputStream outputStream) {
        XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
        xmlEncoder.writeObject(o);
        xmlEncoder.close();
    }

    public byte[] encodeAsXml(Object o) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeAsXml(o, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Object loadFromXmlFile(String filename) throws IOException {
        return decodeFromXml(new BufferedInputStream(new FileInputStream(filename)));
    }

    public void saveToXmlFile(Object object, String filename) throws IOException {
        encodeAsXml(object, new BufferedOutputStream(new FileOutputStream(filename)));
    }

    public Object loadFromSerFile(String filename) throws IOException {
        return decodeFromXml(new BufferedInputStream(new FileInputStream(filename)));
    }

    public void saveToSerFile(Object object, String filename) throws IOException {
        encodeAsXml(object, new BufferedOutputStream(new FileOutputStream(filename)));
    }

    public byte[] encode(Object object) {
        return encodeAsXml(object);
    }

    public Object parse(InputStream inputStream, int contentLength) {
        return decodeFromXml(inputStream);
    }
}