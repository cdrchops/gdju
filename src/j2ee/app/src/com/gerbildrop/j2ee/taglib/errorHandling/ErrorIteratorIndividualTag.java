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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class ErrorIteratorIndividualTag extends javax.servlet.jsp.tagext.TagSupport {
    public ErrorIteratorIndividualTag() {
        super();
    }

    public void release() {
    }

    public int doStartTag() throws JspTagException {
        //get the individual error out of the pagecontext, then display it with a link to an anchor somewhere in the page
        ErrorInfo newString = (ErrorInfo) pageContext.getAttribute("error");

        //stub anchor... could be anywhere like a messageFormat object that we pull from a properties file or something else
        String outer = "<a href=\"#" + newString.getCategory() + "\">" + newString.getErrorDisplay() + "</a>";

        JspWriter out = pageContext.getOut();
        try {
            out.print(outer);
            out.flush();
        } catch (IOException ioError) {
            System.out.println("IO Error writing errorDisplay from ErrorIteratorIndividualTag");
        }

        release();

        return EVAL_BODY_INCLUDE;
    }
    //BEGIN THE DEFAULT GETTERS AND SETTERS FOR THIS TAGLIB
}