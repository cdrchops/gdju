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

import com.gerbildrop.j2ee.J2eeFileIO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author timo
 * @version 1.0
 * @see
 * @since Jan 26, 2005 -- 1:10:37 PM
 */
public class UpdatedContent {
    public static StringBuffer getContent(HttpServletRequest request, String path, String fileName) {
        StringBuffer sb = new StringBuffer();
        String directory = request.getRealPath(path);
        List lst = J2eeFileIO.readFile(directory, fileName, false);
        String starr[] = (String[]) lst.toArray(new String[lst.size()]);
        for (int i = 0; i < starr.length; i++) {
            StringTokenizer token = new StringTokenizer(starr[i], "|");
            String update = null;
            String content = null;
            while (token.hasMoreTokens()) {
                update = token.nextToken();
                content = token.nextToken();
            }

            sb.append("<b>" + update + "</b><br>")
                    .append("&nbsp;&nbsp;" + content + "<br>");
        }
        return sb;
    }
}
