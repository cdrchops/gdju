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
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.List;

import com.jme.scene.Node;

/**
 * @author vivaldi
 * @version $Id: NIOServer.java,v 1.9 2007/04/04 14:29:13 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class NIOServer {

    public final static int DEFAULT_PORT = 3011;
    public final static int MAX_PACKET_SIZE = 256;

    private static final GameObject masterGameObject = new GameObject();

    public static void main(String[] args) {
        System.out.println("*****************************************************");
        System.out.println("*****************************************************");
        System.out.println("*******************JautOGL SERVER********************");
        System.out.println("*****************************************************");
        System.out.println("*****************************************************\n\n\n");

        int port = NIOServer.DEFAULT_PORT;

        try {
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(port);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocateDirect(NIOServer.MAX_PACKET_SIZE);
            SocketAddress client1 = null;
            SocketAddress client2 = null;

            //GET THE ADDRESSES-INITIALISATION PHASE
            while (client1 == null) {
                client1 = channel.receive(buffer);
                buffer.flip();
                buffer.clear();
            }
            while (client2 == null) {
                client2 = channel.receive(buffer);
                buffer.flip();
                buffer.clear();
            }

            //SERVER NOW KNOWS THE TWO PLAYERS
            System.out.print("***PLAYER 1 IS: " + client1.toString() + "***\n");
            System.out.println("***PLAYER 2 IS: " + client2.toString() + "***\n\n");

            //MAIN LOOP-SERVER IS WORKING FOR THE PLAYERS
            while (true) {
                SocketAddress client = channel.receive(buffer);
                if (client.equals(client1)) {
                    buffer.flip();
                    channel.send(buffer, client2);
                    System.out.print("***PLAYER 1***	");
                    buffer.flip();
                    int command = buffer.getInt();
                    NIOServer.statistics(command);
                    buffer.clear();
                } else {
                    buffer.flip();
                    channel.send(buffer, client1);
                    System.out.print("***PLAYER 2***	");
                    buffer.flip();
                    int command = buffer.getInt();
                    NIOServer.statistics(command);
                    buffer.clear();
                }
            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public static void addPlayer(Node player) {
        masterGameObject.addPlayer(player);
    }

    public static void addEnemy(Node enemy) {
        masterGameObject.addEnemy(enemy);
    }

    public static List getPlayers() {
        return masterGameObject.getPlayers();
    }

    public static List getEnemies() {
        return masterGameObject.getEnemies();
    }

    //A METHOD FOR PRINTING SATISTICS
    public static void statistics(int comm) {
        switch (comm) {
            case 1:
                System.out.println("is moving forward...");
                break;
            case 2:
                System.out.println("is moving forward and turning left...");
                break;
            case 3:
                System.out.println("is moving forward and turning right...");
                break;
            case 4:
                System.out.println("is moving backward...");
                break;
            case 5:
                System.out.println("is moving backward and turning left...");
                break;
            case 6:
                System.out.println("is moving backward and turning right...");
                break;
        }
    }
}
