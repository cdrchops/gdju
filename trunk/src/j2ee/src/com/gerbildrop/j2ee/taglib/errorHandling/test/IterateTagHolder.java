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

package com.gerbildrop.j2ee.taglib.errorHandling.test;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * IterateTag extends TagSupport. The TagSupport class in JSP 1.2 implements the IterationTag
 */
public class IterateTagHolder extends TagSupport {
    //    private Iterator iterator;
    private List _collection;
    private int iterationNumber = 0;

    public int doStartTag() throws JspTagException {
        if (_collection == null) {
            throw new JspTagException("No collection with name "
                    + _collection
                    + " found");
        }
        IterateTagHolder it = (IterateTagHolder) findAncestorWithClass(this, IterateTagHolder.class);
        int returner = SKIP_BODY;
        int size = _collection.size();
        while (iterationNumber < size) {
            System.out.println("size: " + size);
            System.out.println("iterationNumber: " + iterationNumber);
            pageContext.setAttribute("item", _collection.get(iterationNumber));
            iterationNumber++;
            if (it != null) {
                System.out.println("parent not null");
                //                it.setIterationNumber(iterationNumber);
            } else {
                //                setIterationNumber(iterationNumber);
            }
            returner = EVAL_BODY_INCLUDE;
        }
        return returner;
    }

    public int doAfterBody() {
        IterateTagHolder it = (IterateTagHolder) findAncestorWithClass(this, IterateTagHolder.class);
        int returner = SKIP_BODY;
        int size = _collection.size();
        while (iterationNumber < size) {
            System.out.println("asize: " + size);
            System.out.println("aiterationNumber: " + iterationNumber);
            pageContext.setAttribute("item", _collection.get(iterationNumber));
            iterationNumber++;
            if (it != null) {
                System.out.println("aparent not null");
                //                it.setIterationNumber(iterationNumber);
            } else {
                //                setIterationNumber(iterationNumber);
            }
            returner = EVAL_BODY_INCLUDE;
        }
        return returner;
    }

    public void setCollection(List collection) {
        this._collection = collection;
    }

    public void setIterationNumber(int _iterationNumber) {
        iterationNumber = _iterationNumber;
    }
}