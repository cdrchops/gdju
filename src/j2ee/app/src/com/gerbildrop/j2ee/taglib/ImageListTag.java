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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ImageListTag extends BodyTagSupport {

    private List lst = null;
    private String home = null;
    private String homeDisplay = null;
    private String first = null;
    private String next = null;
    private String last = null;
    private String previous = null;
    private String path = null;
    private String current = null;
    private boolean isCentered = false;

    public ImageListTag() {
        super();
    }

    public void release() {
    }

    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuffer sb = new StringBuffer();

        List lst = getFileList(request.getRealPath(path));
        int size = lst.size();
        String starr[] = (String[]) lst.toArray(new String[size]);

        String First = "<a href=\"{4}&imgNum={0}&path={5}\">" + first + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        String Previous = "<a href=\"{4}&imgNum={1}&path={5}\">" + previous + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        String Home = "<a href=\"{4}&path={5}\">{6}</a>";

        String Next = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{4}&imgNum={2}&path={5}\">" + next + "</a>";
        String Last = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"{4}&imgNum={3}&path={5}\">" + last + "</a>";

        String tempFirst = null;
        String tempNext = null;
        String tempLast = null;
        String tempPrevious = null;

        for (int j = 0; j < starr.length; j++) {
            String next = starr[j];

            if (j == 0) {
                tempFirst = next;
            } else if (j == starr.length - 1) {
                tempLast = next;
            }

            if (next.equals(current)) {
                if (j + 1 < starr.length) {
                    tempNext = starr[j + 1];
                } else {
                    tempNext = starr[starr.length - 1];
                }

                if (j - 1 > 0) {
                    tempPrevious = starr[j - 1];
                } else {
                    tempPrevious = starr[0];
                }
            }
        }

        Object obj[] = new Object[]{tempFirst, tempPrevious, tempNext, tempLast, home, path, homeDisplay};
        MessageFormat mf;

        if (isCentered) {
            sb.append("<center>");
        }

        mf = new MessageFormat(First);
        sb.append(mf.format(obj));

        mf = new MessageFormat(Previous);
        sb.append(mf.format(obj));

        mf = new MessageFormat(Home);
        sb.append(mf.format(obj));

        mf = new MessageFormat(Next);
        sb.append(mf.format(obj));

        mf = new MessageFormat(Last);
        sb.append(mf.format(obj));

        if (isCentered) {
            sb.append("</center>");
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

/*    public static String getFirst(List lst) {
    return (String)lst.get(0);
}

public static String getLast(List lst) {
    return (String) lst.get(lst.size());
}

public static String getNext(List lst, int i) {
    return (String) lst.get(i + 1);
}

public static String getPrevious(List lst, int i) {
    return (String) lst.get(i - 1);
}*/

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
            if (_child.indexOf("jpg") > -1
                    || _child.indexOf("gif") > -1
                    || _child.indexOf("JPG") > -1
                    || _child.indexOf("GIF") > -1) {
                lst.add(_child);
            }
        }

        return lst;
    }


    public List getLst() {
        return lst;
    }

    public void setLst(List lst) {
        this.lst = lst;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomeDisplay() {
        return homeDisplay;
    }

    public void setHomeDisplay(String homeDisplay) {
        this.homeDisplay = homeDisplay;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public boolean isCentered() {
        return isCentered;
    }

    public void setCentered(boolean centered) {
        isCentered = centered;
    }
}