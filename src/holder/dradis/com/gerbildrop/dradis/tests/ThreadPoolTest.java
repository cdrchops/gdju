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

/**
 * borrowed from brackeen
 */
package com.gerbildrop.dradis.tests;

import com.gerbildrop.dradis.threads.ThreadPool;

public class ThreadPoolTest {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Tests the ThreadPool task.");
            System.out.println(
                    "Usage: java ThreadPoolTest numTasks numThreads");
            System.out.println(
                    "  numTasks - integer: number of task to run.");
            System.out.println(
                    "  numThreads - integer: number of threads " +
                    "in the thread pool.");
            args = new String[2];
            args[0] = "10";
            args[1] = "10";
        }
        int numTasks = Integer.parseInt(args[0]);
        int numThreads = Integer.parseInt(args[1]);

        // create the thread pool
        ThreadPool threadPool = new ThreadPool(numThreads);

        // run example tasks
        for (int i = 0; i < numTasks; i++) {
            threadPool.runTask(createTask(i));
        }

        // close the pool and wait for all tasks to finish.
        threadPool.join();
    }


    /** Creates a simple Runnable that prints an ID, waits 500 milliseconds, then prints the ID again. */
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
                System.out.println("Task " + taskID + ": start");

                // simulate a long-running task
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    //no op
                }

                System.out.println("Task " + taskID + ": end");
            }
        };
    }

}
