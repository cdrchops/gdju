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

package com.gerbildrop.core.errorHandling;

/**
 * used to hold an object representation of an error
 */
public class ErrorInfo {
    private int thisguid = -1;
    private String localName = null;

    /**
     * category is what the tag on the page maps to as well as the link to the anchor at the top in the error block
     */
    private String localCategory = null;
    private String localErrorDisplay = null;

    public ErrorInfo(int guid,
                     String name,
                     String category,
                     String errorDisplay) {
        localCategory = category;
        localErrorDisplay = errorDisplay;
        thisguid = guid;
        localName = name;
    }

    public void setErrorDisplay(String errorDisplay) {
        localErrorDisplay = errorDisplay;
    }

    public int getGuid() {
        return thisguid;
    }

    public String getName() {
        return localName;
    }

    public String getCategory() {
        return localCategory;
    }

    public String getErrorDisplay() {
        return localErrorDisplay;
    }
}