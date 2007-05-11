/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.groovy.io;
/**
 * @author vivaldi
 * @version $Id: BinaryWriter.groovy,v 1.2 2007/04/26 17:35:26 vivaldi Exp $
 * @since Oct 12, 2006
 */
public static final byte BEGIN_FILE = 0;
public static final byte START_TAG = 1;
public static final byte END_TAG = 2;
public static final byte END_FILE = 2;
public static final byte KEY_MAPPING = 10;
public static final byte CHARACTER = 11;
public static final byte ACTION = 12;
public static final byte DISPLAY = 13;

file = new File("input.gdf");
bout = new FileOutputStream(file);
out = new DataOutputStream(bout);

out.writeByte(BEGIN_FILE);
out.writeByte(START_TAG);
out.writeByte(KEY_MAPPING);
out.writeByte(CHARACTER);
out.writeInt(76);
out.writeByte(ACTION);
out.writeUTF("playAudio(1)");
out.writeByte(DISPLAY);
out.writeUTF("PLAY AUDIO ONE");
out.writeByte(CHARACTER);
out.writeInt(77);
out.writeByte(ACTION);
out.writeUTF("playAudio(2)");
out.writeByte(DISPLAY);
out.writeUTF("PLAY AUDIO TWO");
out.writeByte(END_TAG);
out.writeByte(END_FILE);



bin = new FileInputStream(file);

inner = new DataInputStream(bin);
sentinel = true;
while(sentinel) {
    flag = inner.readByte();
    if (flag == END_FILE) {
        sentinel = false;
    } else if (flag == START_TAG) {
        println("start tag");
        flag = inner.readByte();
        if (flag == KEY_MAPPING) {
            sentinel2 = true;
            while (sentinel2) {
                flag = inner.readByte();

                if (flag == END_TAG) {
                    sentinel2 = false;
                } else if (flag == CHARACTER) {
                    println("character: ");
                    int krktr = inner.readInt();
                    if (krktr == 76) {
                        println("L");
                    } else if (krktr == 77) {
                        println("M");
                    }
                } else if (flag == ACTION) {
                    println("action: ");
                    println(inner.readUTF());
                } else if (flag == DISPLAY) {
                    println("display: ");
                    println(inner.readUTF());
                }
            }
        }
    }
}