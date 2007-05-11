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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gerbildrop.util.StringUtil;

public class FileUploadOptionTag extends BodyTagSupport {
    private String _submitted = null;
    private String _process = null;

    public void release() {
        super.release();
        _process = null;
        _submitted = null;
    }

    public int doStartTag() throws JspException {
        int result = SKIP_BODY;
        FileUploadSelectTag parent = (FileUploadSelectTag) findAncestorWithClass(this, FileUploadSelectTag.class);

        if (parent == null) {
            throw new JspTagException("fileupload-option tag must be nested within an fileupload-select tag.");
        }

        if (parent.isDefaultDone()) {
            if (_submitted == null && _process == null) {
                throw new JspTagException("Only one default fileupload-option (i.e. no 'submitted' attribute specified) " +
                        "is allowed within an fileupload-select tag.");
            } else {
                throw new JspTagException("Default fileupload-option tag must be the last fileupload-option " +
                        "tag nested within an fileupload-select tag.");
            }
        }

        if (!parent.isSelectionMade()) {
            if (_submitted == null && _process == null) {
                // we must be the default option
                parent.setSelectionMade(true);
                result = EVAL_BODY_INCLUDE;
            } else {
                if (StringUtil.isEqual(_submitted, "true")) {//group matches something
                    parent.setSelectionMade(true);
                    result = EVAL_BODY_INCLUDE;
                } else {
                    if (_process == null) {
                    } else {
                        if (StringUtil.isEqual(_process, "true")) {
                            parent.setSelectionMade(true);
                            result = EVAL_BODY_INCLUDE;
                        } else {
                            // skip our body
                        }
                    }
                }
            }
        } else {
            // a peer already matched and executed so skip our body
        }

        if (_submitted == null && _process == null) {
            parent.setDefaultDone(true);
        }

        return result;
    }

    public String getFromRequest(String temp) {
        return (pageContext.getRequest()).getParameter(temp);
    }

    public void setSubmitted(String submitted) {
        _submitted = submitted != null ? submitted : "false";
    }

    public void setProcess(String process) {
        _process = process != null ? process : "false";
    }
}