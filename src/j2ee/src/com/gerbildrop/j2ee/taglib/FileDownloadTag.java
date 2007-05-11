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
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gerbildrop.util.StringUtil;

public class FileDownloadTag extends BodyTagSupport {
    private static final int BUFSIZE = 2048;

    private String fileIn = null;
    private String fileOut = null;

    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String queryString = request.getQueryString();
        String file = request.getParameter("file");
        String path = request.getParameter("path");
        String pt = request.getParameter("pt");
        String pageTemplate = pt == null ? "template.jsp" : pt.equals("true") ? "sitcomTemplate.jsp" : "headerFormat.jsp";

        fileIn = request.getRealPath(file);

        int indexer = 0;
        if (StringUtil.indexOf(file, "html")) {
            indexer = file.indexOf("html");
        } else if (StringUtil.indexOf(file, "txt")) {
            indexer = file.indexOf("txt");
        }

        fileOut = file.substring(0, indexer) + request.getParameter("type");

        try {
            if (!StringUtil.indexOf(fileOut, ".jsp")) {
                doDownload(file, path, pageTemplate);
            }
        } catch (IOException e) {
            //throw new JspException("COULD NOT DOWNLOAD FILE", e);
        }

        return EVAL_BODY_INCLUDE;
    }

    /**
     * Sends a file to the ServletResponse output stream.  Typically you want the browser to receive a different name
     * than the name the file has been saved in your local database, since your local names need to be unique.
     */
    //todo: fix output so that the header is also downloaded at the same time
    private void doDownload(String file, String path, String pageTemplate) throws IOException {
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        int length = 0;
        ServletOutputStream op = response.getOutputStream();
        ServletContext context = pageContext.getServletConfig().getServletContext();
        String mimetype = context.getMimeType(fileIn);

        //  Set the response and go!
        //
        //  Yes, I know that the RFC says 'attachment'.  Unfortunately, IE has a typo
        //  in it somewhere, and Netscape seems to accept this typing as well.
        response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
        response.setHeader("Content-Disposition", "attachement; filename=\"" + fileOut + "\"");

//        String loader = "http://" + J2eeStaticVariables.getServer() + "/tk203/scripts/" + pageTemplate + "?file=" + file + "&path=" + path + "&pn=1";
//        String html = loadHtmlSource(loader);
//
//        op.write(html.getBytes(), 0, html.length());

        op.flush();
        op.close();
    }

    public String loadHtmlSource(String strLocation) throws IOException {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(strLocation);
            URLConnection urlConn = url.openConnection();
            InputStream urlIn = urlConn.getInputStream();
            int iRead = urlIn.read();
            while (iRead != -1) {
                sb.append((char) iRead);
                iRead = urlIn.read();
            }
        } catch (java.io.IOException iox) {
            throw iox;
        }
        return sb.toString();
    }
}