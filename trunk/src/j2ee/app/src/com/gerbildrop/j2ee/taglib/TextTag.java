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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gerbildrop.j2ee.parsers.UpdatedContent;
import com.gerbildrop.util.StringUtil;

/**
 * Created by IntelliJ IDEA. User: timo Date: Mar 11, 2005 Time: 1:26:48 PM To change this template use File | Settings
 * | File Templates.
 */
public class TextTag extends BodyTagSupport {
    String page = null;
    String title = null;
    String file = null;
    String domain = null;
    String direct = null;
    boolean isDirect = false;

    public TextTag() {
        super();
    }

    public void release() {
        page = null;
        title = null;
        file = null;
        domain = null;
        direct = null;
        isDirect = false;
    }

    public int doStartTag() throws JspException {
        StringBuffer sb = new StringBuffer();

        sb.append("?title=" + title);

        if (StringUtil.hasLen(file)) {
            sb.append("&file=" + file + "&domain=" + domain);
        } else {
            sb.append("&direct=" + direct);
        }

        if (isDirect) {
            JspWriter out = pageContext.getOut();
            try {
                String output = UpdatedContent.getContent((HttpServletRequest) pageContext.getRequest(), domain, file).toString();

                out.print(output);
                out.flush();
            } catch (IOException ioError) {
                System.out.println("IO Error writting directly to page in TextTag.java.");
            }
        } else {
            try {
                pageContext.include(page + sb);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        if (bodyContent != null) {
//            sb.append(" ")
//              .append(bodyContent.getString());
//        }

        release();

        return EVAL_BODY_INCLUDE;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setIsDirect(boolean direct) {
        isDirect = direct;
    }
}