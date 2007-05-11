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

package com.gerbildrop.j2ee.imaging;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageObject implements Serializable {
    public ImageObject() {
        _uid = -1;
        _name = null;
        _source = null;
        _alt = null;
        _longDesc = null;
        _height = -1;
        _width = -1;
        _usemap = false;
        _ismap = false;
        _attrs = null;
        _border = -1;
        _active = true;
    }

    public ImageObject(HttpServletRequest request) {
        _uid = -1;
        _name = null;
        _source = null;
        _alt = null;
        _longDesc = null;
        _height = -1;
        _width = -1;
        _usemap = false;
        _ismap = false;
        _attrs = null;
        _border = -1;
        _active = true;
        _uid = Integer.parseInt(request.getParameter("uid"));
        _name = request.getParameter("name");
        _source = request.getParameter("source");
        _alt = request.getParameter("alt");
        _longDesc = request.getParameter("longDesc");
        _height = Integer.parseInt(request.getParameter("height"));
        _width = Integer.parseInt(request.getParameter("width"));
        _usemap = (new Boolean(request.getParameter("usemap"))).booleanValue();
        _ismap = (new Boolean(request.getParameter("ismap"))).booleanValue();
        _attrs = request.getParameter("attrs");
        _border = Integer.parseInt(request.getParameter("border"));
        _active = (new Boolean(request.getParameter("active"))).booleanValue();
    }

    public ImageObject(ResultSet rs) {
        _uid = -1;
        _name = null;
        _source = null;
        _alt = null;
        _longDesc = null;
        _height = -1;
        _width = -1;
        _usemap = false;
        _ismap = false;
        _attrs = null;
        _border = -1;
        _active = true;
        try {
            _uid = rs.getInt("uid");
            _name = rs.getString("name");
            _source = rs.getString("source");
            _alt = rs.getString("alt");
            _longDesc = rs.getString("longDesc");
            _height = rs.getInt("height");
            _width = rs.getInt("width");
            _usemap = (new Boolean(rs.getString("usemap"))).booleanValue();
            _ismap = (new Boolean(rs.getString("ismap"))).booleanValue();
            _attrs = rs.getString("attrs");
            _border = rs.getInt("border");
            _active = (new Boolean(rs.getString("active"))).booleanValue();
        }
        catch (SQLException e) {
            System.out.println("bombed out here");
        }
    }

    public void populate(HttpServletRequest httpservletrequest) {
    }

    public void populate(ResultSet resultset) {
    }

    public int getUid() {
        return _uid;
    }

    public void setUid(int uid) {
        _uid = uid;
    }

    public boolean isActive() {
        return _active;
    }

    public void setActive(boolean active) {
        _active = active;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getAlt() {
        return _alt;
    }

    public void setAlt(String alt) {
        _alt = alt;
    }

    public String getLongDesc() {
        return _longDesc;
    }

    public void setLongDesc(String longDesc) {
        _longDesc = longDesc;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int height) {
        _height = height;
    }

    public int getWidth() {
        return _width;
    }

    public void setWidth(int width) {
        _width = width;
    }

    public boolean getUsemap() {
        return _usemap;
    }

    public void setUsemap(boolean usemap) {
        _usemap = usemap;
    }

    public boolean getIsmap() {
        return _ismap;
    }

    public void setIsmap(boolean ismap) {
        _ismap = ismap;
    }

    public String getAttrs() {
        return _attrs;
    }

    public void setAttrs(String attrs) {
        _attrs = attrs;
    }

    public int getBorder() {
        return _border;
    }

    public void setBorder(int border) {
        _border = border;
    }

    private int _uid;
    private String _name;
    private String _source;
    private String _alt;
    private String _longDesc;
    private int _height;
    private int _width;
    private boolean _usemap;
    private boolean _ismap;
    private String _attrs;
    private int _border;
    private boolean _active;
}