package com.gerbildrop.hiero;

import groovy.sql.Sql;

public class SqlObject {
    public String url = "jdbc:mysql://localhost:3306/hiero";
    public String user= "root";
    public String pass= "imation";
    public String driver = "com.mysql.jdbc.Driver";//"org.gjt.mm.mysql.Driver";//"com.caucho.jdbc.mysql.Driver";

    public SqlObject() {}

    public SqlObject(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    private def sql = Sql.newInstance(url,
                                      user,
                                      pass,
                                      driver);

    public static def insertSql = {query, param  ->
        //firstName = "yue"
        //lastName = "wu"
        //sql.execute("insert into people (firstName, lastName) " + " values ('${firstName}', ${lastName})")
        //sql.execute("insert into people (firstName, lastName) " + " values (?,?)", [firstName, lastName])
        sql.execute(query, param);
    }

    public static def updateSql = {query, param ->
        //comment = "Lazy bum"
        //"update people set comment = ? where id=002", [comment]
        sql.executeUpdate(query, param)
    }

    public static def deleteSql = {query, param ->
        //sql.execute("delete from word where word_id = ?" , [5])
        sql.execute(query, param);
    }
}