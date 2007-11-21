/*
Copyright 2002 Mark Burton
*/

package com.gerbildrop.gc.fsuipc;


public class FSADF extends FSNavRadio {
    public FSADF() {
        super();
        iFreq = 0x34c;
        iID = 0x0303e;
        iName = 0x03044;
    }

    public String freqAsString() {
        short freq = getShort(0x34c);
        int dig1 = (freq >> 8 & 0x0f);
        int dig2 = (freq >> 4 & 0x0f);
        int dig3 = (freq & 0x0f);

        short freq2 = getShort(0x356);
        int dig4 = (freq2 >> 12 & 0x0f);
        int dig5 = (freq2 & 0x0f);

       return getAsString(dig1, dig2, dig3, dig4, dig5);
    }

    @Override
    public String getAsString(int dig1, int dig2, int dig3, int dig4, int dig5) {
       StringBuilder sb = new StringBuilder();
       sb.append(dig4).append(dig1).append(dig2).append(dig3).append(".").append(dig5);

       return sb.toString();
   }
}
