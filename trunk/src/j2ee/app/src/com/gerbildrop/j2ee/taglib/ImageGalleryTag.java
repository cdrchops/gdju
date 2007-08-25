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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gerbildrop.j2ee.imaging.ImageUtil;
import com.gerbildrop.util.StringUtil;

public class ImageGalleryTag extends BodyTagSupport {
    private String path = null;
    private String url = null;

    public ImageGalleryTag() {
        super();
    }

    public void release() {
    }

    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        List lst = getFileList(request.getRealPath(path + "tn/"));
        Iterator itr = lst.iterator();

        StringBuffer sb = new StringBuffer();

        int i = 1;

        while (itr.hasNext()) {
            String next = (String) itr.next();
            boolean temp = StringUtil.hasLen(url);
            if (temp) {
                sb.append("<a href=\"")
                        .append(url)
                        .append(next)
                        .append("&path=")
                        .append(path)
                        .append("\">");
            }

            sb.append("<img src=\"")
                    .append(path)
                    .append("tn/")
                    .append(next)
                    .append("\">");

            if (temp) {
                sb.append(i)
                        .append("</a>");
            }

            i++;
        }

//        if (bodyContent != null) {
//            sb.append(" ")
//              .append(bodyContent.getString());
//        }
        try {
            JspWriter out = pageContext.getOut();
            out.print(sb);
            out.flush();
        } catch (IOException ioError) {
            System.out.println("IO Error writting image to page in ImageListTag.java.");
        }

        release();

        return EVAL_BODY_INCLUDE;
    }

    public static List getFileList(String fileName) {
        List lst = new ArrayList();

        File dir = new File(fileName);

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        };

        String[] children = dir.list(filter);

        for (int i = 0; i < children.length; i++) {
            String _child = children[i];
            if (ImageUtil.endsWithValidImageExtension(_child)) {
                lst.add(_child);
            }
        }

        return lst;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}