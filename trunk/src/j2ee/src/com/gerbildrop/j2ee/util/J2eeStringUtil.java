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

package com.gerbildrop.j2ee.util;

import javax.servlet.http.HttpServletRequest;

import com.gerbildrop.util.StringUtil;
import com.gerbildrop.util.TypeParser;

public class J2eeStringUtil extends StringUtil {
    public static String setTernaryFromRequest(HttpServletRequest request, String str, String strDefault) {
        return setTernary(request.getParameter(str), strDefault);
    }

    public static String setTernaryFromRequest(HttpServletRequest request, String str) {
        return setTernaryFromRequest(request, str, null);
    }

    public static float getFloatFromRequest(HttpServletRequest request, String str, String strDefault) {
        return TypeParser.parseFloat(setTernaryFromRequest(request, str, strDefault));
    }

    public static double getDoubleFromRequest(HttpServletRequest request, String str, String strDefault) {
        return TypeParser.parseDouble(setTernaryFromRequest(request, str, strDefault));
    }

    public static int getIntFromRequest(HttpServletRequest request, String str, String strDefault) {
        return TypeParser.parseInt(setTernaryFromRequest(request, str, strDefault));
    }

    public static long getLongFromRequest(HttpServletRequest request, String str, String strDefault) {
        return TypeParser.parseLong(setTernaryFromRequest(request, str, strDefault));
    }

    public static boolean getBooleanFromRequest(HttpServletRequest request, String str, String strDefault) {
        return TypeParser.parseBoolean(setTernaryFromRequest(request, str, strDefault));
    }

    public static String processString(HttpServletRequest request, String txt) {
        if (hasLen(request.getParameter(txt))) {
            return request.getParameter(txt);
        } else {
            return "";
        }
    }

    public static String formatLink(String link, String addOn) {
        StringBuffer sb = new StringBuffer(link);
        if (StringUtil.hasLen(addOn)) {
            if (link.indexOf("?") > -1) {
                sb.append("&");
            } else {
                sb.append("?");
            }

            sb.append(addOn);
        }
        return sb.toString();
    }
}