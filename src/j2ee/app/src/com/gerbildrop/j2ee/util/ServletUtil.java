package com.gerbildrop.j2ee.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.gerbildrop.logging.Debug;

/**
 * @author timo
 * @version 1.0
 * @see
 * @since Nov 28, 2004 -- 1:52:34 PM
 */
public class ServletUtil {
    public static void forward(String url,
                               HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
    }

    public static void include(String url,
                               HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).include(request, response);
    }

    public static void namedDispatcherForward(String servletName,
                                              HttpServletRequest request,
                                              HttpServletResponse response,
                                              ServletContext context) throws ServletException, IOException {
        context.getNamedDispatcher(servletName).forward(request, response);
    }

    public static void namedDispatcherInclude(String servletName,
                                              HttpServletRequest request,
                                              HttpServletResponse response,
                                              ServletContext context) throws ServletException, IOException {
        context.getNamedDispatcher(servletName).include(request, response);
    }

    public static void namedDispatcherForward(String servletName,
                                              PageContext pageContext)  throws ServletException, IOException {
        pageContext.getServletContext().getNamedDispatcher(servletName).forward(pageContext.getRequest(), pageContext.getResponse());
    }

    public static void namedDispatcherInclude(String servletName,
                                              PageContext pageContext)  throws ServletException, IOException {
        pageContext.getServletContext().getNamedDispatcher(servletName).include(pageContext.getRequest(), pageContext.getResponse());
    }

    public static void callFilter(HttpServletRequest request, HttpServletResponse response, String className) {
        try {
            Class[] types = new Class[] { ServletRequest.class, ServletResponse.class, FilterChain.class };
            Class annoclass = Class.forName(className);
            Method method = annoclass.getMethod("filter", types);

            String nextPage = (String) method.invoke(annoclass.newInstance(), new Object[] {request, response, null});
        } catch (ClassNotFoundException e) {
            Debug.error(e);
        } catch (NoSuchMethodException e) {
            Debug.error(e);
        } catch (IllegalAccessException e) {
            Debug.error(e);
        } catch (InvocationTargetException e) {
            Debug.error(e);
        } catch (InstantiationException e) {
            Debug.error(e);
        }
    }

    public static void callServlet(HttpServletRequest request, HttpServletResponse response, String className) {
        callServlet(request, response, className, "doGet");
    }

    public static void callServlet(HttpServletRequest request, HttpServletResponse response, String className, String methodName) {
        try {
            Class[] types = new Class[] { HttpServletRequest.class, HttpServletResponse.class };
            Class annoclass = Class.forName(className);
            Method method = annoclass.getMethod(methodName, types);

            String nextPage = (String) method.invoke(annoclass.newInstance(), new Object[] {request, response});
        } catch (ClassNotFoundException e) {
            Debug.error(e);
        } catch (NoSuchMethodException e) {
            Debug.error(e);
        } catch (IllegalAccessException e) {
            Debug.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
//            Debug.error(e);
        } catch (InstantiationException e) {
            Debug.error(e);
        }
    }
}