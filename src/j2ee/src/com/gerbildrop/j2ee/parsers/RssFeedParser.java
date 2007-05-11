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

/*
 * Copyright (c) 2006, Your Corporation. All Rights Reserved.
 */
package com.gerbildrop.j2ee.parsers;

import com.gerbildrop.j2ee.J2eeFileIO;

/**
 * @author timo
 * @version 1.0
 * @see
 * @since Jan 26, 2005 -- 11:43:27 AM
 */
public class RssFeedParser {
    public static StringBuffer parseRSS() {
        return parseRSS("http://cobradoc.blogspot.com/atom.xml");
    }

    public static StringBuffer parseRSS(String fileName) {
        StringBuffer sb = J2eeFileIO.getFileFromServerAsStringBuffer(fileName);
        StringBuffer sb2 = new StringBuffer();

        /*
        look for <created>
        then starting at that position look for <link href="
        starting there get everything to the next "
        starting there look for the next created.
        */
        String toParse = sb.toString();

        String firstLook = "<created>";
        String secondLook = "<link href=\"";

        int thisOne = toParse.indexOf(firstLook, 0);

        while (thisOne > -1) {
            int nextStep = toParse.indexOf(secondLook, thisOne);
            nextStep += secondLook.length();
            int thirdStep = toParse.indexOf("\" rel=\"", nextStep);
            String sub = toParse.substring(nextStep, thirdStep);
            String blogLink = "http://cobradoc.blogspot.com/2005/01/";
            String subDisplay = sub.substring(blogLink.length());
            subDisplay = subDisplay.substring(0, subDisplay.length() - 5);
            sb2.append("<a href=\"" + sub + "\">" + subDisplay + "</a><br>");

            thisOne = toParse.indexOf(firstLook, nextStep);
        }
        return sb2;
    }
}