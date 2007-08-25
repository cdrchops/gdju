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
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gerbildrop.util.StringUtil;

public class FileUploadTag extends BodyTagSupport {
    String action = null;
    String formName = null;
    String submitButtonText = null;
    boolean override = false;
    String formText = null;
    boolean multipart = false;
    String location = null;
    boolean createThumbnails = false;

    public FileUploadTag() {
        super();
    }

    public void release() {
    }

    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        StringBuffer sb = new StringBuffer();

        if (formName == null) {
            formName = "myform";
        }

        sb.append("<form name=\"" + formName + "\"");

        if (multipart) {
            if (action == null) {
                action = "";
            }

            String tempLocationString = request.getParameter("location");
            MessageFormat fmt = new MessageFormat("&location=/{0}/");
            String locationString = StringUtil.hasLen(location)
                    ? fmt.format(new Object[]{location})
                    : StringUtil.hasLen(tempLocationString)
                    ? fmt.format(new Object[]{tempLocationString})
                    : null;

            String tempNumFiles = request.getParameter("fileNumbers");
            int numFiles = StringUtil.hasLen(tempNumFiles) ? Integer.parseInt(tempNumFiles) : 1;

            String adminOverride = request.getParameter("adminOverride");
            String adminString = StringUtil.hasLen(adminOverride) ? "&adminOverride=" + adminOverride : "";

            sb.append(" action=\"?process=true" + action + locationString + adminString + "\"");
            sb.append("  method=\"post\" enctype=\"multipart/form-data\"\">\n");
            sb.append(formText + "<br>");
            for (int i = 0; i < numFiles; i++) {
                sb.append("<input type=\"file\" name=\"myfile" + i + "\"><br>");
            }
        } else {
            sb.append(" method=\"get\"><br>\n");
            sb.append(formText + "<br>");
            String tmpLocation = request.getParameter("location");
            String tmpFormLocation = StringUtil.hasLen(tmpLocation) ? tmpLocation : "";
            sb.append("<input type=\"text\" name=\"location\" value=\"" + tmpLocation + "\"><br>\n");
            sb.append("How many Files do you want to upload*?<br>\n");
            sb.append("<input type=\"text\" name=\"fileNumbers\" value=\"\"><br>\n");

            if (override) {
                sb.append("<input type=\"hidden\" name=\"adminOverride\" value=\"" + override + "\">\n");
            }

            sb.append("<input type=\"hidden\" name=\"submitted\" value=\"true\">\n");
        }

        sb.append("<input type=\"submit\"");

        if (submitButtonText != null) {
            sb.append(" value=" + submitButtonText);
        }

        sb.append("><br>\n");

        sb.append("</form>\n");

        if (!multipart) {
            sb.append("* the exact number of files you need to upload can be less than the amount provided");
        }

        JspWriter out = pageContext.getOut();
        try {
            out.print(sb);
            out.flush();
        } catch (IOException ioError) {
            System.out.println("IO Error writting upload to page in FileUploadTag.java.");
        }

        release();

        return EVAL_BODY_INCLUDE;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getSubmitButtonText() {
        return submitButtonText;
    }

    public void setSubmitButtonText(String submitButtonText) {
        this.submitButtonText = submitButtonText;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }

    public boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(boolean multipart) {
        this.multipart = multipart;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}