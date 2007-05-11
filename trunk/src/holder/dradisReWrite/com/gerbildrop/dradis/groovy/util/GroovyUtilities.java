/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.groovy.util;

import java.io.File;
import java.io.IOException;

import com.gerbildrop.dradis.logging.Debug;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.codehaus.groovy.control.CompilationFailedException;

/**
 * Utility to call groovy scripts from inside a Java class
 * this class acts as the mediator so you don't have to put the same calls in every class
 * 
 * @author vivaldi
 * @version $Id: GroovyUtilities.java,v 1.3 2007/04/04 14:31:12 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class GroovyUtilities {
    //todo: where there is a binding, work on a way to set the variable and argument for each binding that isn't
    //      intrusive -- timo 28Jun06

    //gse = GroovyScriptEngine --- utilities for parsing a gse
    public static void gseRunner(String rootPath, String scriptName) {
        gseRunner(new String[]{rootPath}, scriptName, new Binding());
    }

    public static void gseRunner(String[] rootPath, String scriptName, Binding binding) {
        gseRunner(rootPath, scriptName, "groovy", binding);
    }

    public static void gseRunner(String[] rootPath, String scriptName, String scriptExtension, Binding binding) {
        try {
            GroovyScriptEngine gse = new GroovyScriptEngine(rootPath);
            gse.run(scriptName + "." + scriptExtension, binding);
        } catch (IOException e) {
            Debug.error(e);
        } catch (ResourceException e) {
            Debug.error(e);
        } catch (ScriptException e) {
            Debug.error(e);
        }
    }


    //gsh = GroovyShell -- utilities for running a groovy shell
    public static Object gshRunner(String evaluation) {
        return gshRunner(evaluation, new Binding());
    }

    public static Object gshRunner(String evaluation, Binding binding) {
        GroovyShell shell = new GroovyShell(binding);

        Object value = null;
        try {
            value = shell.evaluate(evaluation);
        } catch (CompilationFailedException e) {
            Debug.error(e);
        }

        return value;
    }


    //gcl = GroovyClassLoader -- utilities for running a groovy class loader
    public static void gclRunner(String path, String fileName, String methodName) {
        gclRunner(path + fileName, methodName);
    }

    public static void gclRunner(String fileName, String methodName) {
        gclRunner(fileName, new Object[0], methodName);
    }

    public static void gclRunner(String fileName, Object[] args, String methodName) {
        GroovyClassLoader loader = new GroovyClassLoader(GroovyUtilities.class.getClassLoader());
        Class groovyClass = null;
        try {
            groovyClass = loader.parseClass(new File(fileName));
        } catch (CompilationFailedException e) {
            Debug.error(e);
        } catch (IOException e) {
            Debug.error(e);
        }

        GroovyObject groovyObject;
        try {
            groovyObject = (GroovyObject) groovyClass.newInstance();
            groovyObject.invokeMethod(methodName, args);
        } catch (InstantiationException e) {
            Debug.error(e);
        } catch (IllegalAccessException e) {
            Debug.error(e);
        } catch (NullPointerException e) {
            Debug.error(e);
        }
    }


    //implement an interface in a groovy script
    /*
        usage in java code
        MyInterface myObject = (MyInterface) ascript;
        myObject.interfaceMethod();
    */
    public static Object gclInterfaceRunner(String fileName) {
        GroovyClassLoader gcl = new GroovyClassLoader();
        Object aScript = null;

        try {
            Class clazz = gcl.parseClass(new File(fileName));
            aScript = clazz.newInstance();
        } catch (CompilationFailedException e) {
            Debug.error(e);
        } catch (IOException e) {
            Debug.error(e);
        } catch (InstantiationException e) {
            Debug.error(e);
        } catch (IllegalAccessException e) {
            Debug.error(e);
        }

        return aScript;
    }
}