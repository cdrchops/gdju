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

package com.gerbildrop.dradis.displays.clock;

import java.net.URL;

public class Hours {
    private static final String BASE_URL = "/dradis/displays/clock/";
    private static final URL HOUR_12 = Hours.class.getResource(BASE_URL + "hours/hours12.png");
    private static final URL HOUR_11 = Hours.class.getResource(BASE_URL + "hours/hours11.png");
    private static final URL HOUR_10 = Hours.class.getResource(BASE_URL + "hours/hours10.png");
    private static final URL HOUR_9 = Hours.class.getResource(BASE_URL + "hours/hours9.png");
    private static final URL HOUR_8 = Hours.class.getResource(BASE_URL + "hours/hours8.png");
    private static final URL HOUR_7 = Hours.class.getResource(BASE_URL + "hours/hours7.png");
    private static final URL HOUR_6 = Hours.class.getResource(BASE_URL + "hours/hours6.png");
    private static final URL HOUR_5 = Hours.class.getResource(BASE_URL + "hours/hours5.png");
    private static final URL HOUR_4 = Hours.class.getResource(BASE_URL + "hours/hours4.png");
    private static final URL HOUR_3 = Hours.class.getResource(BASE_URL + "hours/hours3.png");
    private static final URL HOUR_2 = Hours.class.getResource(BASE_URL + "hours/hours2.png");
    private static final URL HOUR_1 = Hours.class.getResource(BASE_URL + "hours/hours1.png");
    private static final URL HOUR_0 = Hours.class.getResource(BASE_URL + "hours/hours0.png");

    private static final URL HOUR_ONE_NUMBER_0 = Hours.class.getResource(BASE_URL + "numbers/zero.png");
    private static final URL HOUR_ONE_NUMBER_1 = Hours.class.getResource(BASE_URL + "numbers/one.png");
    private static final URL HOUR_ONE_NUMBER_2 = Hours.class.getResource(BASE_URL + "numbers/two.png");
    private static final URL HOUR_ONE_NUMBER_3 = Hours.class.getResource(BASE_URL + "numbers/three.png");
    private static final URL HOUR_ONE_NUMBER_4 = Hours.class.getResource(BASE_URL + "numbers/four.png");
    private static final URL HOUR_ONE_NUMBER_5 = Hours.class.getResource(BASE_URL + "numbers/five.png");
    private static final URL HOUR_ONE_NUMBER_6 = Hours.class.getResource(BASE_URL + "numbers/six.png");
    private static final URL HOUR_ONE_NUMBER_7 = Hours.class.getResource(BASE_URL + "numbers/seven.png");
    private static final URL HOUR_ONE_NUMBER_8 = Hours.class.getResource(BASE_URL + "numbers/eight.png");
    private static final URL HOUR_ONE_NUMBER_9 = Hours.class.getResource(BASE_URL + "numbers/nine.png");

    private static final URL HOUR_TWO_NUMBER_0 = Hours.class.getResource(BASE_URL + "numbers/zero.png");
    private static final URL HOUR_TWO_NUMBER_1 = Hours.class.getResource(BASE_URL + "numbers/one.png");
    private static final URL HOUR_TWO_NUMBER_2 = Hours.class.getResource(BASE_URL + "numbers/two.png");
    private static final URL HOUR_TWO_NUMBER_3 = Hours.class.getResource(BASE_URL + "numbers/three.png");
    private static final URL HOUR_TWO_NUMBER_4 = Hours.class.getResource(BASE_URL + "numbers/four.png");
    private static final URL HOUR_TWO_NUMBER_5 = Hours.class.getResource(BASE_URL + "numbers/five.png");
    private static final URL HOUR_TWO_NUMBER_6 = Hours.class.getResource(BASE_URL + "numbers/six.png");
    private static final URL HOUR_TWO_NUMBER_7 = Hours.class.getResource(BASE_URL + "numbers/seven.png");
    private static final URL HOUR_TWO_NUMBER_8 = Hours.class.getResource(BASE_URL + "numbers/eight.png");
    private static final URL HOUR_TWO_NUMBER_9 = Hours.class.getResource(BASE_URL + "numbers/nine.png");

    public static URL getHour(int hour) {
        switch (hour) {
            case 0:
                return HOUR_0;
            case 1:
                return HOUR_1;
            case 2:
                return HOUR_2;
            case 3:
                return HOUR_3;
            case 4:
                return HOUR_4;
            case 5:
                return HOUR_5;
            case 6:
                return HOUR_6;
            case 7:
                return HOUR_7;
            case 8:
                return HOUR_8;
            case 9:
                return HOUR_9;
            case 10:
                return HOUR_10;
            case 11:
                return HOUR_11;
            case 12:
                return HOUR_12;
            default:
                return HOUR_0;
        }
    }

    public static URL getHourOne(int hour) {
        switch (hour) {
            case 0:
                return HOUR_ONE_NUMBER_0;
            case 1:
                return HOUR_ONE_NUMBER_1;
            case 2:
                return HOUR_ONE_NUMBER_2;
            case 3:
                return HOUR_ONE_NUMBER_3;
            case 4:
                return HOUR_ONE_NUMBER_4;
            case 5:
                return HOUR_ONE_NUMBER_5;
            case 6:
                return HOUR_ONE_NUMBER_6;
            case 7:
                return HOUR_ONE_NUMBER_7;
            case 8:
                return HOUR_ONE_NUMBER_8;
            case 9:
                return HOUR_ONE_NUMBER_9;
            default:
                return HOUR_ONE_NUMBER_0;
        }
    }

    public static URL getHourTwo(int hour) {
        switch (hour) {
            case 0:
                return HOUR_TWO_NUMBER_0;
            case 1:
                return HOUR_TWO_NUMBER_1;
            case 2:
                return HOUR_TWO_NUMBER_2;
            case 3:
                return HOUR_TWO_NUMBER_3;
            case 4:
                return HOUR_TWO_NUMBER_4;
            case 5:
                return HOUR_TWO_NUMBER_5;
            case 6:
                return HOUR_TWO_NUMBER_6;
            case 7:
                return HOUR_TWO_NUMBER_7;
            case 8:
                return HOUR_TWO_NUMBER_8;
            case 9:
                return HOUR_TWO_NUMBER_9;
            default:
                return HOUR_TWO_NUMBER_0;
        }
    }

    public static int[] checkHours(int hour) {
        int hours[] = new int[2];

        if (hour > 9 && hour < 20) {
            hours[0] = 1;
            hours[1] = hour - 10;
        } else if (hour > 19) {
            hours[0] = 2;
            hours[1] = hour - 20;
        } else {
            hours[0] = 0;
            hours[1] = hour;
        }

        return hours;
    }
}