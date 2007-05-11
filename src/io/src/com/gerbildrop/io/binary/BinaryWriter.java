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

package com.gerbildrop.io.binary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class BinaryWriter {

    public BinaryWriter() {
    }

    public static void main(String args[]) {
        String fileName = "input.gdf";
        (new BinaryWriter()).write(fileName);
        (new BinaryWriter()).read(fileName);
    }

    public void write(String fileName) {
        File file = new File(fileName);
        FileOutputStream bout = null;

        try {
            bout = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        out = new DataOutputStream(bout);
        try {
            out.writeByte(0);
            out.writeByte(1);
            out.writeByte(10);
            out.writeByte(11);
            out.writeInt(76);
            out.writeByte(12);
            out.writeUTF("playAudio(1)");
            out.writeByte(13);
            out.writeUTF("PLAY AUDIO ONE");
            out.writeByte(11);
            out.writeInt(77);
            out.writeByte(12);
            out.writeUTF("playAudio(2)");
            out.writeByte(13);
            out.writeUTF("PLAY AUDIO TWO");
            out.writeByte(2);
            out.writeByte(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(String fileName) {
        File file = new File(fileName);
        FileInputStream bin = null;

        try {
            bin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        in = new DataInputStream(bin);
        try {
            for (byte flag = in.readByte(); flag != 2; flag = in.readByte()) {
                if (flag == 1) {
                    System.out.println("start tag");
                    flag = in.readByte();
                    if (flag == 10) {
                        for (flag = in.readByte(); flag != 2; flag = in.readByte()) {
                            if (flag == 11) {
                                System.out.println("character: ");
                                int krktr = in.readInt();
                                if (krktr == 76) {
                                    System.out.println("L");
                                    continue;
                                }
                                if (krktr == 77)
                                    System.out.println("M");
                                continue;
                            }
                            if (flag == 12) {
                                System.out.println("action: ");
                                System.out.println(in.readUTF());
                                continue;
                            }
                            if (flag == 13) {
                                System.out.println("display: ");
                                System.out.println(in.readUTF());
                            }
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final byte BEGIN_FILE = 0;
    public static final byte START_TAG = 1;
    public static final byte END_TAG = 2;
    public static final byte END_FILE = 2;
    public static final byte KEY_MAPPING = 10;
    public static final byte CHARACTER = 11;
    public static final byte ACTION = 12;
    public static final byte DISPLAY = 13;
    DataOutputStream out;
    DataInputStream in;
}
