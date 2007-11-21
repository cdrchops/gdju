/*
	Copyright 2003 Mark Burton
*/

package com.gerbildrop.gc.fsuipc;

/**
 * Abstract navigation radio
 */
public abstract class FSNavRadio extends FSUIPC {
    protected int iStandbyFreq;
    protected int iFreq;
    protected int iID;
    protected int iName;
    protected int iSwap;
    protected int iLocNeedle;
    protected int iGlideSlope;

    public FSNavRadio() {
        super();
    }

    /**
     * Radio stand by frequency
     */
    public short standByFreq() {
        return getShort(iStandbyFreq);
    }

    /**
     * Stand by frequency of radio as a string
     */
    public String standByFreqAsString() {
        int freq = standByFreq();
        int dig1 = (freq >> 12 & 0x0f);
        int dig2 = (freq >> 8 & 0x0f);
        int dig3 = (freq >> 4 & 0x0f);
        int dig4 = (freq & 0x0f);
        return getAsString(dig1, dig2, dig3, dig4, 0);
    }

    /**
     * Current frequency
     */
    public short freq() {
        return getShort(iFreq);
    }

    /**
     * Frequency as a string
     */
    public String freqAsString() {
        int freq = freq();
        int dig1 = (freq >> 12 & 0x0f);
        int dig2 = (freq >> 8 & 0x0f);
        int dig3 = (freq >> 4 & 0x0f);
        int dig4 = (freq & 0x0f);
        return getAsString(dig1, dig2, dig3, dig4, 0);
    }

    /**
     * Identity of the station
     */
    public String identity() {
        return getString(iID, 6);
    }

    /**
     * Full name of the radio station
     */
    public String name() {
        return getString(iName, 25);
    }

    /**
     * Swap over frequencies
     */
    public void swapFrequencies() {
        byte[] data = new byte[1];
        data[0] = 2;
        fsuipc_wrapper.writeData(iSwap, 1, data);
    }

    /**
     * Localiser needle position
     */
    public byte localiserNeedle() {
        return getByte(iLocNeedle);
    }

    /**
     * Glideslope indicatior
     */
    public byte glideSlope() {
	return getByte(iGlideSlope);
	}

    public String getAsString(int dig1, int dig2, int dig3, int dig4, int dig5) {
        StringBuilder sb = new StringBuilder(1);
        sb.append(dig1).append(dig2).append(".").append(dig3).append(dig4);

        return sb.toString();
    }
}