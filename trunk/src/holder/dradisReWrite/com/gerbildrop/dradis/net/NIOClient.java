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

package com.gerbildrop.dradis.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author vivaldi
 * @version $Id: NIOClient.java,v 1.3 2007/04/04 14:31:11 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class NIOClient {
    public final static int DEFAULT_PORT = 3011;
    int port = NIOClient.DEFAULT_PORT;

    SocketAddress remote;
    DatagramChannel channel;
    Selector selector;
    ByteBuffer buffer1;
    ByteBuffer buffer2;

    public NIOClient() {//88.218.49.63 MITSOU
        //192.168.0.3 LAPTOP
        //193.92.234.156 kitsios
        //"localhost"
        try {
            remote = new InetSocketAddress("192.168.0.3", port);
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(remote);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            buffer1 = ByteBuffer.allocate(4);
            buffer2 = ByteBuffer.allocate(4);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void send(int command) {
        try {
            selector.select(6000);
            Set readyKeys = selector.selectedKeys();
            Iterator iterator = readyKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                if (key.isWritable()) {
                    buffer1.clear();
                    buffer1.putInt(command);
                    buffer1.flip();
                    channel.write(buffer1);
                    System.out.print("***LOCAL PLAYER ");
                    NIOClient.statistics(command);
                }
            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public int receive() {
        int r = 0;
        try {
            selector.select(6000);
            Set readyKeys2 = selector.selectedKeys();
            Iterator iterator2 = readyKeys2.iterator();
            if (iterator2.hasNext()) {
                SelectionKey key2 = (SelectionKey) iterator2.next();
                iterator2.remove();
                if (key2.isReadable()) {
                    buffer2.clear();
                    channel.read(buffer2);
                    buffer2.flip();
                    int command = buffer2.getInt();
                    System.out.print("***OPPONENT PLAYER ");
                    NIOClient.statistics(command);
                    r = command;
                }

            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        return r;
    }

    //A METHOD FOR PRINTING SATISTICS
    public static void statistics(int comm) {
        switch (comm) {
            case 1:
                System.out.println("is moving forward...***");
                break;
            case 2:
                System.out.println("is moving forward and turning left...***");
                break;
            case 3:
                System.out.println("is moving forward and turning right...***");
                break;
            case 4:
                System.out.println("is moving backward...***");
                break;
            case 5:
                System.out.println("is moving backward and turning left...***");
                break;
            case 6:
                System.out.println("is moving backward and turning right...***");
                break;
        }
    }
}
