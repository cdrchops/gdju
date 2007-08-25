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

package com.gerbildrop.j2ee.taglib.errorHandling;

import com.gerbildrop.core.errorHandling.ErrorInfo;
import com.gerbildrop.core.errorHandling.ErrorMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ErrorTag extends javax.servlet.jsp.tagext.TagSupport {
    private String name = null;
    private boolean showTag = false;

    public ErrorTag() {
        super();
    }

    public void release() {
        name = null;
    }

    public int doStartTag() throws JspTagException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        ErrorMap em = (ErrorMap) request.getAttribute("errorListener");
        Iterator itr = !em.isEmpty()
                ? ((List) em.getErrorList()).iterator()
                : null;

        setShowTag(itr != null);

        if (showTag) {
            for (Iterator iterator = em.getErrorList().iterator(); iterator.hasNext();) {
                ErrorInfo errorInfo = (ErrorInfo) iterator.next();
                if (name.equals(errorInfo.getCategory())) {
                    writeOut("<a name=\"" + name + "\"><font class=errorText>");
                    break;
                }
            }
        }

        release();
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspTagException {
        if (showTag) {
            writeOut("</font>");
        }
        return EVAL_BODY_INCLUDE;
    }

    public void writeOut(String writeOut) {
        JspWriter out = pageContext.getOut();
        try {
            out.print(writeOut);
            out.flush();
        } catch (IOException ioError) {
            System.out.println("IO Error writting error to page in ErrorTag.java.");
        }
    }

    //BEGIN THE DEFAULT GETTERS AND SETTERS FOR THIS TAGLIB
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowTag() {
        return showTag;
    }

    public void setShowTag(boolean showTag) {
        this.showTag = showTag;
    }
}
