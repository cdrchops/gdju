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
package com.gerbildrop.dradis.threads;

import java.util.LinkedList;

/**
 * A thread pool is a group of a limited number of threads that are used to execute tasks.
 *
 * @author brackeen
 * @author vivaldi
 * @version $Id: ThreadPool.java,v 1.3 2007/04/04 14:31:17 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class ThreadPool extends ThreadGroup {

    private boolean isAlive;
    private LinkedList<Runnable> taskQueue;
    private int threadID;
    private static int threadPoolID;

    /**
     * Creates a new ThreadPool.
     *
     * @param numThreads The number of threads in the pool.
     */
    public ThreadPool(int numThreads) {
        super("ThreadPool-" + (threadPoolID++));
        setDaemon(true);

        isAlive = true;

        taskQueue = new LinkedList<Runnable>();
        for (int i = 0; i < numThreads; i++) {
            new PooledThread().start();
        }
    }


    /**
     * Requests a new task to run. This method returns immediately, and the task executes on the next available idle
     * thread in this ThreadPool. <p>Tasks start execution in the order they are received.
     *
     * @param task The task to run. If null, no action is taken.
     *
     * @throws IllegalStateException if this ThreadPool is already closed.
     */
    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }

    }


    protected synchronized Runnable getTask()
            throws InterruptedException {
        while (taskQueue.size() == 0) {
            if (!isAlive) {
                return null;
            }
            wait();
        }
        return taskQueue.removeFirst();
    }


    /**
     * Closes this ThreadPool and returns immediately. All threads are stopped, and any waiting tasks are not executed.
     * Once a ThreadPool is closed, no more tasks can be run on this ThreadPool.
     */
    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }


    /** Closes this ThreadPool and waits for all running threads to finish. Any waiting tasks are executed. */
    public void join() {
        // notify all waiting threads that this ThreadPool is no
        // longer alive
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        // wait for all threads to finish
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                //no op
            }
        }
    }


    /** A PooledThread is a Thread in a ThreadPool group, designed to run tasks (Runnables). */
    private class PooledThread extends Thread {


        public PooledThread() {
            super(ThreadPool.this,
                  "PooledThread-" + (threadID++));
        }


        public void run() {
            while (!isInterrupted()) {

                // get a task to run
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException ex) {
                    //no op
                }

                // if getTask() returned null or was interrupted,
                // close this thread by returning.
                if (task == null) {
                    return;
                }

                // run the task, and eat any exceptions it throws
                try {
                    task.run();
                }
                catch (Throwable t) {
                    uncaughtException(this, t);
                }
            }
        }
    }
}