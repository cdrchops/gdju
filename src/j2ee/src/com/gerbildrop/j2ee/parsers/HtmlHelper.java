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

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class HtmlHelper {
    public static String formatLink(String link, String popupDisplay) {
        return formatLink(link, popupDisplay, true);
    }

    public static String formatLink(String link, String popupDisplay, boolean showBr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<a href=\"" + link + "\"\n" +
                "   onmouseover=\"return overlib('" + popupDisplay + "', BGCOLOR, 'gray', FGCOLOR, 'black', TEXTCOLOR, 'gray');\"\n" +
                "   onmouseout=\"return nd();\"><font class=\"linker\">" + popupDisplay + "</font></a>");
        if (showBr) {
            sb.append("<br>");
        }
        return sb.toString();
    }

    public static String formatNormalLink(String link, String display, boolean showBr, boolean showLink) {
        if (showLink) {
            StringBuffer sb = new StringBuffer();
            sb.append("<a class=\"regLink\" href=\"");
            sb.append(link);
            sb.append("\">");
            sb.append(display);
            sb.append("</a>");
            if (showBr) {
                sb.append("<br>");
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String escapeLinebreaks(String str) {
        if (str == null) return null;
        if (str.length() == 0) return str;
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\r') continue;
            if (c[i] == '\n')
                sb.append("\\n");
            else
                sb.append(c[i]);
        }
        return sb.toString();
    }

    /**
     * Takes the all of the request parameters and puts them on a request string
     *
     * @param request
     *
     * @return
     */
    public static String requestParamsToURLRequest(HttpServletRequest request) {
        return requestParamsToURLRequest(request, null);
    }

    /**
     * Takes the all of the request parameters and puts them on a request string Takes the optional string array of
     * params to omit
     *
     * @param request
     * @param omitList
     *
     * @return
     */
    public static String requestParamsToURLRequest(HttpServletRequest request, String[] omitList) {
        Enumeration e = request.getParameterNames();
        StringBuffer buf = new StringBuffer();
        if (e != null) {
            String paramName = null;
            boolean first = true;
            while (e.hasMoreElements()) {
                paramName = (String) e.nextElement();
                if (omitList != null) {
                    boolean foundIt = false;
                    for (int i = 0; i < omitList.length; i++) {
                        if (paramName.equalsIgnoreCase(omitList[i])) {
                            foundIt = true;
                            break;
                        }
                    }

                    //if found what we are looking for then skip the section below and continue in the while loop
                    if (foundIt)
                        continue;
                }

                String[] vals = request.getParameterValues(paramName);
                if (vals != null) {
                    for (int i = 0; i < vals.length; i++) {
                        if (!first) {
                            buf.append("&");
                        } else {
                            first = false;
                        }

                        buf.append(paramName).append("=").append(vals[i]);
                    }
                }
            }
        }

        return buf.toString();
    }

    /**
     * Returns hidden form elements from the request params
     *
     * @param request
     *
     * @return
     */
    public static String requestParamsToHiddenForm(HttpServletRequest request) {
        return requestParamsToHiddenForm(request, null);
    }

    /**
     * Returns hidden form elements from the request params Omits any elements in the omitlist
     *
     * @param request
     * @param omitList
     *
     * @return
     */
    public static String requestParamsToHiddenForm(HttpServletRequest request, String[] omitList) {
        Enumeration e = request.getParameterNames();
        StringBuffer buf = new StringBuffer();
        if (e != null) {
            String paramName = null;
            while (e.hasMoreElements()) {
                paramName = (String) e.nextElement();
                if (omitList != null) {
                    boolean foundIt = false;
                    for (int i = 0; i < omitList.length; i++) {
                        if (paramName.equalsIgnoreCase(omitList[i])) {
                            foundIt = true;
                            break;
                        }
                    }

                    //if found what we are looking for then skip the section below and continue in the while loop
                    if (foundIt)
                        continue;
                }

                buf.append("<input type=hidden name=\"").append(paramName);
                buf.append("\" value=\"").append(request.getParameter(paramName)).append("\">\n");
            }
        }

        return buf.toString();
    }

    public static String processFormElement(String formName, String formValue) {
        return processFormElement(formName, formValue, false);
    }

    public static String processFormElement(String formName, String formValue, boolean isHidden) {
        return processFormElement(formName, formValue, null, null, isHidden, false);
    }

    public static String processFormElement(String formName, String formValue, String formSize,
                                            String maxLength, boolean isHidden, boolean isDisplay) {
        StringBuffer sb = new StringBuffer();
        sb.append("<INPUT TYPE=\"");

        if (!isHidden) {
            sb.append("text");
        } else {
            sb.append("hidden");
        }

        sb.append("\" name=\"" + formName + "\" value=\"");
        sb.append(formValue);
        sb.append("\"");
        if (!isDisplay) {
            sb.append(" size=\"" + formSize + "\"");
            if (maxLength != null) {
                sb.append(" maxlength=\"" + maxLength + "\"");
            }
        }
        sb.append("\">");
        if (isDisplay) {
            sb.append(formValue);
        }
        return sb.toString();
    }
}
