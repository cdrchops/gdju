/*
    Copyright 2003 Mark Burton
*/
package com.gerbildrop.gc.jni;

/**
 * Wrapper class for fsuipcJava.dll modified from original Mark Burton code... he might have known about C/C++, but
 * didn't know shit about java
 *
 * @author timo
 * @since 13Jul06
 */
public class FsuipcWrapper {
    public static final int SIM_ANY = 0;
    public static final int SIM_FS98 = 1;
    public static final int SIM_FS2K = 2;
    public static final int SIM_CFS2 = 3;
    public static final int SIM_CFS1 = 4;
    public static final int SIM_FS2K2 = 6;

    /** Automatically load the dll */
    static {
        // load library
        System.loadLibrary("fsuipcJava");
    }

    /**
     * Connect to FS.
     *
     * @param aFlightSim Version of flightsim to try and connect to.
     *
     * @return 0 if connection failed
     */
    public static synchronized native int open(int aFlightSim);

    /**
     * close the connection
     */
    public static synchronized native void close();

    /**
     * Read bytes from FS
     */
    public static synchronized native void readData(int aOffset, int aCount, byte[] aData);

    /**
     * Write byte to FS
     */
    public static synchronized native void writeData(int aOffset, int aCount, byte[] aData);

    /**
     * process the commands
     */
    public static synchronized native void process();
}