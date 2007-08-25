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

import java.io.File;

import com.gerbildrop.xml.util.XmlUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * uses log 4 j logging file to implement
 */
public class Log4JLogger implements Logging {
    private static final String LOGGING_CONF_FILE ="c:/logs/data/logging/log4j.xml";

    private static Category cat;

    static {
        cat = Category.getInstance((Log4JLogger.class).getName());
    }

    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz);
    }

    public static synchronized void setConfigurator() {
        configureFromResource();
    }

    public void log(Priority priority, String message) {
        cat.log(priority, message);
    }

    public void log(Priority priority, Exception message) {
        cat.log(priority, message);
    }

    private static void configureLogging(String fileName) {
        File file = new File(fileName);

        if (file.exists() && file.isFile()) {
            String fn = file.getAbsolutePath();
            if (fn.endsWith(".xml")) {
                DOMConfigurator.configure(fn);
                Debug.info("Configured Log4J from XML file '" + file.getAbsolutePath() + "'.");
            } else {
                PropertyConfigurator.configure(fn);
                Debug.info("Configured Log4J from properties file '" + file.getAbsolutePath() + "'.");
            }
        } else {
            Debug.warn("Log4J configuration file not found at '" + file.getAbsolutePath() + "'");
        }
    }

    private static void configureFromResource() {
        org.w3c.dom.Document doc = XmlUtil.configureJDomDocFromResource(LOGGING_CONF_FILE);
        Log log = LogFactory.getLog(Debug.class);
        if (doc != null) {
            try {
                DOMConfigurator.configure(doc.getDocumentElement());
                log.info("Configured Log4J from resource '" + LOGGING_CONF_FILE + "'.");

            } catch (Exception e) {
                System.out.println("Exception while configuring Log4J from XML.");
                e.printStackTrace(System.out);
                log.warn("Exception while configuring Log4J from XML.", e);
            }
        } else {
            log.warn("Could not find '" + LOGGING_CONF_FILE + "' in the classpath.  Unable to configure Log4J.");
        }
    }

    public void setPath(String path, String fileName, String extension) {
        //ignored in this implementation since log4j pulls from the config file
    }
}
