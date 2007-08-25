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

package com.gerbildrop.j2ee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gerbildrop.io.FileIO;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

public class J2eeFileIO extends FileIO {
    public static List readFileIn(HttpServletRequest request) {
        return readFileIn(request.getRealPath(request.getParameter("path")), request.getParameter("file"));
    }

    public static List getFileFromServerAsList(HttpServletRequest request) {
        return getFileFromServerAsList(request.getParameter("path") + request.getParameter("file"));
    }

    public static List getFileFromInputStreamAsList(HttpServletRequest request) {
        // first check if the upload request coming in is a multipart request
        boolean isMultipart = FileUpload.isMultipartContent(request);

        DiskFileUpload upload = new DiskFileUpload();

        // parse this request by the handler
        // this gives us a list of items from the request
        List items = new ArrayList();
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        Iterator itr = items.iterator();

        List lst = new ArrayList();

        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            // check if the current item is a form field or an uploaded file
            if (item.isFormField()) {

                // get the name of the field
                String fieldName = item.getFieldName();

                // if it is name, we can set it in request to thank the user
                if (fieldName.equals("name")) {
                    request.setAttribute("msg", "Thank You: " + item.getString());
                }

            } else {
                try {
                    InputStream uploadedStream = item.getInputStream();

                    BufferedReader in = new BufferedReader(new InputStreamReader(uploadedStream));

                    try {
                        for (String inner = in.readLine(); inner != null; inner = in.readLine()) {
                            lst.add(inner);
                        }

                        in.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                    uploadedStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lst;
    }
}