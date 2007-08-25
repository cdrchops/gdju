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

package com.gerbildrop.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gerbildrop.config.ConfigFactory;
import com.gerbildrop.logging.Debug;

public class DataConnection {
    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static ResultSet rs = null;

    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String pass = null;


    public DataConnection() {
        conn = null;
    }

    public static void init() {
        if (driver == null
            || url == null
            || user == null
            || pass == null) {
            DatabaseConfig dbc = (DatabaseConfig) ConfigFactory.getInstance().createConfig(new DatabaseConfig(), "dbConfig.xml");
            driver = dbc.getDriver();
            url = dbc.getUrl();
            user = dbc.getUser();
            pass = dbc.getPass();
        }
    }

    public static void startConnection() {
        init();

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, pass);
        } catch (InstantiationException e) {
            Debug.error(e);
        } catch (IllegalAccessException e) {
            Debug.error(e);
        } catch (ClassNotFoundException e) {
            Debug.error(e);
        } catch (SQLException e) {
            Debug.error(e);
        }
    }

    public static Statement startStatement() {
        Statement stmt = null;

        try {
            startConnection();
            stmt = conn.createStatement();
        } catch (Exception e) {
            Debug.error(e);
        }

        return stmt;
    }

    public ResultSet connection(String query) {
        ResultSet rs = null;

        try {
            Statement stmt = startStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            Debug.error(e);
        }

        return rs;
    }

    public ResultSet connection(Statement stmt, String query) {
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            Debug.error(e);
        }

        return rs;
    }

    public int getSize(ResultSet rs) {
        int dbsize = 0;

        try {
            while (rs != null && rs.next())
                dbsize++;
            dbsize++;
        } catch (Exception e) {
            Debug.error(e);
        }

        return dbsize;
    }

    public static void insert(String query) {
//        String returner = null;
        try {
            Statement stmt = startStatement();
            stmt.executeQuery(query);
            conn.close();
            stmt.close();
//            returner = "insert completed successfully";
        } catch (Exception e) {
            Debug.error(e);
        }

//        return returner;
    }

    public static void update(String query) {
        try {
            Statement stmt = startStatement();
            stmt.executeQuery(query);
            conn.close();
            stmt.close();
        } catch (Exception e) {
            Debug.error(e);
        }
    }

    public static void preparedStatement() {
        CallableStatement cStmt;

        try {
            startConnection();
            cStmt = conn.prepareCall("{call demoSp(?, ?)}");
            cStmt.setString(1, "abcdefg");
        } catch (SQLException e) {
            Debug.error(e);
        } finally {
            closeConnection();
        }
    }

    public static void closeConnection() {
        try {
            if (rs != null)
                rs.close();

            if (conn != null)
                conn.close();

            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            Debug.error(e);
        }
    }
}