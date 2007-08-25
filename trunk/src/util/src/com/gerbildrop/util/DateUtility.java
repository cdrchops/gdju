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

package com.gerbildrop.util;

/**
 * @author timo
 * @version 1.0
 * @see
 * @since Mar 5, 2005 -- 9:32:35 AM
 */
public final class DateUtility {
    private DateUtility() { }

    public static final int JAN = 0;
    public static final int FEB = 1;
    public static final int MAR = 2;
    public static final int APR = 3;
    public static final int MAY = 4;
    public static final int JUN = 5;
    public static final int JUL = 6;
    public static final int AUG = 7;
    public static final int SEP = 8;
    public static final int OCT = 9;
    public static final int NOV = 10;
    public static final int DEC = 11;

    public static final int[] MONTHS_INT = new int[]{JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC};
    public static final String[] MONTHS_STR = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG",
                                                           "SEP", "OCT", "NOV", "DEC"};

    public static String lookupMonthStr(int month) {
        return (month < 0 || month > MONTHS_STR.length) ? null : MONTHS_STR[month];
    }

    public static int lookupMonth(String str) {
        int returnValue = -1;
        for (int i = 0; i < MONTHS_STR.length; i++) {
            if (str.equalsIgnoreCase(MONTHS_STR[i])) {
                returnValue = MONTHS_INT[i];
                break;
            }
        }

        return returnValue;
    }

    public static String reverseLookupMonth(int month) {
        month--;

        return month < 0 || month > MONTHS_INT.length ? null : MONTHS_STR[MONTHS_INT[month]];
    }

    public static void main(String[] args) {
        System.out.println(DateUtility.reverseLookupMonth(12));
        System.out.println(DateUtility.lookupMonth("BOB"));
        System.out.println(DateUtility.lookupMonth("MAR"));
        System.out.println(DateUtility.lookupMonthStr(11));
        System.out.println(DateUtility.lookupMonthStr(12));
    }
}
