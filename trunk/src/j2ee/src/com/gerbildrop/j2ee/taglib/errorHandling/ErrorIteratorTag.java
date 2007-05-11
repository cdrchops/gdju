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

import com.gerbildrop.core.errorHandling.ErrorMap;

import javax.servlet.jsp.JspTagException;
import java.util.Iterator;
import java.util.List;

public class ErrorIteratorTag extends javax.servlet.jsp.tagext.TagSupport {
    private Iterator iterator;
    private List _collection;

    public void setCollection(List collection) {
        this._collection = collection;
    }

    public int doStartTag() throws JspTagException {
        //if we're on the error page, which we should be, there should be errorStrings to get
        // should put this in a try...catch if it blows out we (skip the body?)
        //this says if there aren't any errors throw an exception
        setCollection(((ErrorMap) (pageContext.getRequest()).getAttribute("errorListener")).getErrorList());

        if (_collection == null) {
            throw new JspTagException("No collection with name "
                    + _collection
                    + " found");
        }

        iterator = _collection.iterator();

        if (iterator.hasNext()) {
            //set the individual error item to the pageContext, to use
            pageContext.setAttribute("error", iterator.next());
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    public int doAfterBody() {
        if (iterator.hasNext()) {
            //set the individual error item to the pageContext, to use
            pageContext.setAttribute("error", iterator.next());
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }
}