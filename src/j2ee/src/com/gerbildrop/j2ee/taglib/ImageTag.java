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
import java.util.StringTokenizer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gerbildrop.j2ee.imaging.ImageObject;
import com.gerbildrop.j2ee.util.HtmlFormatUtil;
import com.gerbildrop.util.StringUtil;

public class ImageTag  extends TagSupport {
    ImageObject _imageObject = null;

    /**
     * constructor
     */
    public ImageTag() {
        super();
        _imageObject = new ImageObject();
    }

    /**
     * what to do when the tag is released
     */
    public void release() {
        _imageObject = null;
    }

    /**
     * main processing of the tag
     *
     * @return EVAL_BODY_INCLUDE
     */
    public int doStartTag() {
        StringBuffer sb = new StringBuffer();

        if (!StringUtil.indexOf(getSource(), "null")) {
            //if source is null, do lookup from db?
            // keep this simple... maybe two tags... one from db one that everything is passed in?
            sb.append("<img src=\"")
                    .append(getSource())
                    .append("\"");

            HtmlFormatUtil.append("name", getName(), sb);

            //always inthe width then height order
            if (StringUtil.hasLen(getAttrs())) {
                StringTokenizer tokens = new StringTokenizer(getAttrs(), "|");
                int i = 0;

                while (tokens.hasMoreTokens()) {
                    if (i == 0) {
                        HtmlFormatUtil.append("width", tokens.nextToken(), sb);
                    } else if (i == 1) {
                        HtmlFormatUtil.append("height", tokens.nextToken(), sb);
                    }

                    sb.append(tokens.nextToken()).append("\"");

                    i++;
                }
            } else {
                HtmlFormatUtil.append("width", getWidth(), sb);
                HtmlFormatUtil.append("height", getWidth(), sb);
            }

            HtmlFormatUtil.append("alt", getAlt(), sb);

            HtmlFormatUtil.append("longdesc", getLongDesc(), sb);

            HtmlFormatUtil.append("border", getBorder(), sb);

            sb.append(">");

            JspWriter out = pageContext.getOut();
            try {
                out.print(sb);
                out.flush();
            } catch (IOException ioError) {
                System.out.println("IO Error writting image to page in ImageTag.java.");
            }
        }

        release();

        return EVAL_BODY_INCLUDE;
    }

    //getters and setters
    public String getSource() {
        return _imageObject.getSource();
    }

    public void setSrc(String source) {
        _imageObject.setSource(source);
    }

    public void setSource(String source) {
        _imageObject.setSource(source);
    }

    public String getAlt() {
        return _imageObject.getAlt();
    }

    public void setAlt(String alt) {
        _imageObject.setAlt(alt);
    }

    public String getLongDesc() {
        return _imageObject.getLongDesc();
    }

    public void setLongDesc(String longDesc) {
        _imageObject.setLongDesc(longDesc);
    }

    public String getName() {
        return _imageObject.getName();
    }

    public void setName(String name) {
        _imageObject.setName(name);
    }

    public int getHeight() {
        return _imageObject.getHeight();
    }

    public void setHeight(int height) {
        _imageObject.setHeight(height);
    }

    public int getWidth() {
        return _imageObject.getWidth();
    }

    public void setWidth(int width) {
        _imageObject.setWidth(width);
    }

    public boolean getUsemap() {
        return _imageObject.getUsemap();
    }

    public void setUsemap(boolean usemap) {
        _imageObject.setUsemap(usemap);
    }

    public boolean getIsmap() {
        return _imageObject.getIsmap();
    }

    public void setIsmap(boolean ismap) {
        _imageObject.setIsmap(ismap);
    }

    public String getAttrs() {
        return _imageObject.getAttrs();
    }

    public void setAttrs(String attrs) {
        _imageObject.setAttrs(attrs);
    }

    public int getBorder() {
        return _imageObject.getBorder();
    }

    public void setBorder(int border) {
        _imageObject.setBorder(border);
    }
}
