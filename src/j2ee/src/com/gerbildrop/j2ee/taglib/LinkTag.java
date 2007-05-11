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

package com.gerbildrop.j2ee.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by IntelliJ IDEA. User: timo Date: Mar 11, 2005 Time: 1:26:48 PM To change this template use File | Settings
 * | File Templates.
 */
public class LinkTag extends BodyTagSupport {
    String href = null;
    String display = null;
    boolean showOverLib = true;
    boolean showBr = true;

    String bgcolor = "BGCOLOR";
    String bgColor = "gray";
    String fgcolor = "FGCOLOR";
    String fgColor = "black";
    String textcolor = "TEXTCOLOR";
    String textColor = "gray";

    public LinkTag() {
        super();
    }

    public void release() {
        href = null;
        display = null;
        showOverLib = true;
        showBr = true;
    }

    public int doStartTag() throws JspException {
        StringBuffer sb = new StringBuffer();
        sb.append("<a href=\"")
                .append(href)
                .append("\"\n");

        if (showOverLib) {
            sb.append(" onmouseover=\"return overlib('")
                    .append(display)
                    .append("',")
                    .append(bgcolor)
                    .append(", '")
                    .append(bgColor)
                    .append("', ")
                    .append(fgcolor)
                    .append(", '")
                    .append(fgColor)
                    .append("', ")
                    .append(textcolor)
                    .append(", '")
                    .append(textColor)
                    .append("');\"\n")
                    .append(" onmouseout=\"return nd();\">");
        }

        sb.append("<font class=\"linker\">")
                .append(display)
                .append("</font></a>");

        if (bodyContent != null) {
            sb.append(" ")
                    .append(bodyContent.getString());
        }

        if (showBr) {
            sb.append("<br>");
        }

        JspWriter out = pageContext.getOut();
        try {
            out.print(sb);
            out.flush();
        } catch (IOException ioError) {
            System.out.println("IO Error writting link to page in LinkTag.java.");
        }

        release();

        return EVAL_BODY_INCLUDE;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isShowBr() {
        return showBr;
    }

    public void setBreak(boolean showBr) {
        this.showBr = showBr;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isShowOverLib() {
        return showOverLib;
    }

    public void setShowOverLib(boolean showOverLib) {
        this.showOverLib = showOverLib;
    }
}