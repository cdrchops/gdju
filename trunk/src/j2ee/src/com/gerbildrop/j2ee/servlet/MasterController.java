package com.gerbildrop.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MasterController extends HttpServlet {
    public abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }
}