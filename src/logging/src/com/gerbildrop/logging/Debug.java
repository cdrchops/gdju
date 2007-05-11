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

package com.gerbildrop.logging;

import org.apache.log4j.Priority;

/**
 * All you have to do is set your path for the config file
 * then in your class call Debug.whateverMethodYouWant(MESSAGE) and it will do whatever you have selected
 *
 * Basically a handler to tell our debug, error, info, warn messages whether to display and/or write out to a log
 * or other file.  It gives us one place for all Debug messages to go to.
 *
 * If you set a DebugMessenger object then the messages will be put into that object for your Observer Pattern contract
 */
public class Debug {
    private static DebugMessenger messenger = null;
    private static boolean displayMessage = false;
    private static boolean logMessage = true;
    private static Logging cat;

    static {
        cat = DebugFactory.getLog4JLogger();
    }

    /**
     * @param _messenger DebugMessenger that the message will be passed back to for our Observer pattern
     */
    public static synchronized void setMessengerBoy(DebugMessenger _messenger) {
        messenger = _messenger;
    }

    /**
     * pass in the conditional... if true, we'll printDisplayMessage a message, otherwise, we'll not.
     * this would be a conditional-printDisplayMessage method.  If we just need to check specific conditions.
     *
     * e.g.
     * String bob = null;
     * printDisplayMessage(bob == null, "bob is null");
     *
     * @param conditional boolean conditional check statement
     * @param message String message to printDisplayMessage out if the conditional check statement is true
     */
    public static synchronized void print(boolean conditional, String message) {
        if (conditional) {
            debug(message);
        }
    }

    /**
     * convenience method to push an exception error to the logging mechanism
     * @param e Exception to put into the logging mechanism
     */
    public static synchronized void error(Exception e) {
        cat.log(Priority.ERROR, e);
    }

    /**
     * convenience method to push an exception error message to the logging mechanism
     * @param message String error to put into the logging mechanism
     */
    public static synchronized void error(String message) {
        printLogMessage(Priority.ERROR, message);
    }

    /**
     * convenience method to push a warning message to the logging mechanism
     * @param message String warning message to put into the logging mechanism
     */
    public static synchronized void warn(String message) {
        printLogMessage(Priority.WARN, message);
    }

    /**
     * convenience method to push a fatal message to the logging mechanism
     * @param message String fatal message to put into the logging mechanism
     */
    public static synchronized void fatal(String message) {
        printLogMessage(Priority.FATAL, message);
    }

    /**
     * convenience method to push an info message to the logging mechanism
     * @param message String info message to put into the logging mechanism
     */
    public static synchronized void info(String message) {
        printLogMessage(Priority.INFO, message);
    }

    /**
     * convenience method to push an exception message to the logging mechanism
     * @param message String message to put into the logging mechanism
     */
    public static synchronized void debug(String message) {
        printLogMessage(Priority.DEBUG, message);
    }

    /**
     * convenience method for pushing the stackTrace into the logging mechanism
     */
    public static synchronized void stackTrace() {
        stackTrace((new Throwable()).getStackTrace().toString());
    }

    /**
     * convenience method for pushing the stackTrace into the logging mechanism
     */
    public static synchronized void stackTraceLine() {
        stackTrace((new Throwable()).getStackTrace()[0].toString());
    }

    /**
     * convenience method for pushing the stackTrace to a log file
     * @param message String message from the StackTrace that will be put into the logging mechanism
     */
    public static synchronized void stackTrace(String message) {
        error(message);
    }

    /**
     *
     * @param category
     * @param message
     */
    private static synchronized void printLogMessage(Priority category, String message) {
        if (logMessage) {
            printDisplayMessage(message);
            cat.log(category, message);
        } else {
            printDisplayMessage(message);
        }
    }

    /**
     * these messages don't rely on logging.  you can printDisplayMessage out a message, but don't need to log it
     * @param message String message to printDisplayMessage to the logging mechanism
     */
    private static synchronized void printDisplayMessage(String message) {
        if (displayMessage) {
            if (messenger == null) {
                System.out.println(message);
            } else {
                messenger.displayMessage(message);
            }
        }
    }

    /**
     * do we want to display our message to the console?
     * @param _displayMessage boolean true goes to console
     */
    public static void setDisplay(boolean _displayMessage) {
        displayMessage = _displayMessage;
    }

    /**
     * do we want to enable logging?
     * @param _logMessage boolean true means we're logging messages
     */
    public static void setLogMessage(boolean _logMessage) {
        logMessage = _logMessage;
    }

    /**
     * set our path info for log files, etc
     * @param path String path to the log file
     * @param fileName String fileName of the log file
     * @param extension String extension of the log file
     */
    public static void setPathInfo(String path, String fileName, String extension) {
        cat.setPath(path, fileName, extension);
    }
}