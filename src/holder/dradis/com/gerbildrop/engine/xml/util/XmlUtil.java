package com.gerbildrop.engine.xml.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import electric.xml.Document;
import electric.xml.ParseException;

public class XmlUtil {

    public XmlUtil() {
    }

    public static String loadFile(String fileName) {
        return XmlUtil.loadResource(fileName).toString();
    }

    public static Document loadXml(String fileName) throws XmlProcessorException {
        Document doc;
        try {
            doc = XmlUtil.loadXml(ClassLoader.getSystemResourceAsStream(fileName));
        } catch (Exception e) {
            throw new XmlProcessorException("bombed out loading " + fileName, e);
        }
        return doc;
    }

    public static Document loadXml(InputStream is) throws XmlProcessorException {
        Document doc;
        try {
            doc = new Document(is);
        } catch (ParseException e) {
            throw new XmlProcessorException("bombed out loading inputStream", e);
        }

        return doc;
    }

    public static void writeXml(String fileName, Document doc)
            throws XmlProcessorException {
        try {
            XmlUtil.writeXml(fileName, doc.getBytes());
        } catch (UnsupportedEncodingException e) {
            throw new XmlProcessorException("bombed out writing xml", e);
        }
    }

    public static void writeXml(String fileName, byte bArr[])
            throws XmlProcessorException {
        try {
            File file = new File(fileName);
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bArr);
            fo.close();
        } catch (Exception e) {
            throw new XmlProcessorException("bombed out writing xml", e);
        }
    }


    public static URL loadResource(final String fileName) {
        ClassLoader c = Thread.currentThread().getContextClassLoader();

        return c.getResource(fileName);
    }

    public static org.w3c.dom.Document configureJDomDocFromResource(String fileName) {
        org.w3c.dom.Document doc = null;
        InputStream in = ClassLoader.getSystemResourceAsStream(fileName);

        if (in != null) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(false);
                factory.setNamespaceAware(false);
                doc = factory.newDocumentBuilder().parse(in);
            } catch (Exception e) {
//                Debug.error(e);
            }
        } else {
//            Debug.error("could not find file " + fileName);
        }

        return doc;
    }
}
