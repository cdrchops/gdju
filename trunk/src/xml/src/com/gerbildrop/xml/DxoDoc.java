package com.gerbildrop.xml;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gerbildrop.util.ReflectionHelper;
import com.gerbildrop.util.TypeParser;
import electric.xml.Attribute;
import electric.xml.Document;
import electric.xml.Element;
import electric.xml.Elements;
import electric.xml.ParseException;
import org.pf.reflect.ReflectUtil;

/**
 * This class is to give a little more freedom to adding Xml Objects and make it easier to add objects and get back a
 * class or list of elements populate an object from elements or populate elements from an uncomplicated object -- no
 * interfaces
 * <p/>
 * It is basically a wrapper for an Exml Document
 * <p/>
 * <p/>
 * Ported from an Open Source project at SourceForge called GerbilDrop Java Utilities... with permission
 *
 * @author timo
 * @version $Id: DxoDoc.java,v 1.4 2007/05/01 18:08:18 vivaldi Exp $ $Copyright: Copyright 2002-2005 Hotels.com, L.P.
 *          All rights reserved. $
 * @since 14Aug06
 */
public class DxoDoc {
    //current standard docType
    private String _docType = "<?xml version=\"1.0\" ?>";
    private static final boolean showDebug = false;

    //formatting constants for toStringAll
    public static final int NO_FORMATTING = 0;
    public static final int NO_EMPTY_INLINE_VALUES = 1;
    public static final int FORMATTED = 2;
    public static final int FORMATTED_NO_EMPTY_INLINE_VALUES = 3;

    //document we're creating and adding elements to
    private Document _doc = new Document();

    //root element
    private Dxo _root;

    /** default constructor */
    public DxoDoc() {
        this("root");
    }

    /**
     * convenience constructor
     *
     * @param doc Exml Document
     */
    public DxoDoc(Document doc) {
        _doc = doc;
        _root = new Dxo(doc.getRoot());
    }

    /**
     * convenience constructor
     *
     * @param root Dxo for the Root element
     */
    public DxoDoc(Dxo root) {
        setRoot(root);
    }

    /**
     * convenience constructor
     *
     * @param name String name of the root element
     */
    public DxoDoc(String name) {
        this(new Dxo(name));
    }

    /**
     * convenience constructor
     *
     * @param element Exml elemnt to set as the root
     */
    public DxoDoc(Element element) {
        this(new Dxo(element));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value int value of the root element
     */
    public DxoDoc(String name,
                  int value) {
        this(name, String.valueOf(value));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value long value of the root element
     */
    public DxoDoc(String name,
                  long value) {
        this(name, String.valueOf(value));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value double value of the root element
     */
    public DxoDoc(String name,
                  double value) {
        this(name, String.valueOf(value));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value float value of the root element
     */
    public DxoDoc(String name,
                  float value) {
        this(name, String.valueOf(value));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value boolean value of the root element
     */
    public DxoDoc(String name,
                  boolean value) {
        this(name, String.valueOf(value));
    }

    /**
     * convenience constructor
     *
     * @param name  String name of the root element
     * @param value String value of the root element
     */
    public DxoDoc(String name,
                  String value) {
        setRoot(new Dxo(name, value));
    }

    /**
     * convenience constructor
     *
     * @param doc  Exml Document as this document
     * @param root Dxo as the root element of the document
     */
    public DxoDoc(Document doc,
                  Dxo root) {
        _doc = doc;
        setRoot(root);
    }

    /**
     * convenience constructor
     *
     * @param doc  Exml Document as this document
     * @param root Exml Element as the root element of the document
     */
    public DxoDoc(Document doc,
                  Element root) {
        this(doc, new Dxo(root));
    }

    /**
     * convenience constructor
     *
     * @param doc  Exml Document as this document
     * @param name String name of the root element for the document
     */
    public DxoDoc(Document doc,
                  String name) {
        this(doc, new Dxo(name));
    }

    /**
     * convenience constructor
     *
     * @param doc   Exml Document as this document
     * @param name  String name of the root element for the document
     * @param value String value of the root element of the document
     */
    public DxoDoc(Document doc,
                  String name,
                  String value) {
        this(doc, new Dxo(name, value));
    }

    /** @param element Dxo to set as the root of this document */
    public void setRoot(Dxo element) {
        _root = element;
        _doc.setRoot(element.getElement());
    }

    /** @param elem Exml Element to set as the root of this document */
    public void setRoot(Element elem) {
        setRoot(new Dxo(elem));
    }

    /** @return String of the doucment type for the header of this XML document */
    public String getDocType() {
        return _docType;
    }

    /** @param docType String to set the document type for the header of this XML document */
    public void setDocType(String docType) {
        _docType = docType;
    }

    /** @param attr Exml Attribute to add to the root element */
    public void addAttribute(Attribute attr) {
        _root.addAttribute(attr);
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value int value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             int value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value long value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             long value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value double value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             double value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value float value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             float value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value boolean value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             boolean value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the attribute to add to the root element
     * @param value String value of the attribute to add to the root element
     */
    public void addAttribute(String name,
                             String value) {
        _root.addAttribute(name, value);
    }

    /** @param lst Collection to add as attributes to the root element */
    public void addAttributes(Collection lst) {
        _root.addAttributes(lst);
    }

    /** @param attributes Exml Attribute[] to add as attributes to the root element */
    public void addAttributes(Attribute[] attributes) {
        _root.addAttributes(attributes);
    }

    /** @param attributes Map of attributes to add as attributes to the root element */
    public void addAttributes(Map attributes) {
        _root.addAttributes(attributes);
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value int value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         int value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value long value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         long value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value double value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         double value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value float value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         float value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value boolean value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         boolean value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * @param name  String name of the Dxo to add to the root element of this document
     * @param value int value of the Dxo element to add to the root element of this document
     */
    public void addChild(String name,
                         String value) {
        _root.addChild(name, value);
    }

    /** @param element Dxo to add as a child of the root element of this document */
    public void addChild(Dxo element) {
        _root.addChild(element);
    }

    /** @param element Exml Element to add as a child of the root element of this document */
    public void addChild(Element element) {
        _root.addChild(element);
    }

    /** @param lst Collection of elements to add as a child of the root element of this document */
    public void addChildren(Collection lst) {
        _root.addChildren(lst);
    }

    /** @param elements Dxo[] to add as a child of the root element of this document */
    public void addChildren(Dxo[] elements) {
        _root.addChildren(elements);
    }

    /** @param elems Exml Element[] to add as a child of the root element of this document */
    public void addChildren(Element[] elems) {
        _root.addChildren(elems);
    }

    /** @param hm Map of Elements to add as a child of the root element of this document */
    public void addChildren(Map hm) {
        _root.addChildren(hm);
    }

    /**
     * todo: this probably won't work with interfaces and other items... straight primitives and Strings for now?
     *
     * @param obj
     */
    public void addChildren(Object obj) {
        ReflectUtil r = ReflectUtil.current();
        List lst = r.getFieldsOf(obj);
        for (Object aLst : lst) {
            Field o = (Field) aLst;
            Dxo dx = new Dxo(o.getName());

            Class type = o.getType();
            if (type.equals(Integer.TYPE)
                || type.equals(Boolean.TYPE)
                || type.equals(Long.TYPE)
                || type.equals(Double.TYPE)
                || type.equals(Float.TYPE)
                || type.equals(String.class)) {
                try {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                addChild(dx);
            }
        }
    }

    /** @return Dxo the root of this document */
    public Dxo getRoot() {
        return _root;
    }

    /** @return String this document to a String value */
    public String toString() {
        return _doc.toString();
    }

    /**
     * @param doc DynamicXmlDocument to return as a String
     *
     * @return String value of the document passed in
     */
    public static String toString(DxoDoc doc) {
        return doc.toString();
    }

    /**
     * take a document and return it as a toString
     *
     * @param doc
     *
     * @return
     */
    public static String toStringAll(DxoDoc doc) {
        return DxoDoc.toStringAll(doc, new int[]{DxoDoc.FORMATTED});
    }

    /**
     * overloaded toString
     *
     * @param doc
     *
     * @return
     */
    public static String toStringAll(Document doc) {
        return DxoDoc.toStringAll(new DxoDoc(doc), new int[]{DxoDoc.FORMATTED});
    }

    /**
     * overloaded toString method
     *
     * @param doc
     * @param attributes
     *
     * @return
     */
    public static String toStringAll(DxoDoc doc,
                                     int[] attributes) {
        StringBuffer sb = new StringBuffer(doc.getDocType());
        for (int attribute : attributes) {
            switch (attribute) {
                case DxoDoc.NO_EMPTY_INLINE_VALUES:
                    sb.append(DxoDoc.removeEmptyInlineValues(doc));
                    break;
                case DxoDoc.FORMATTED:
                    sb.append("\n");
                    sb.append(DxoDoc.toString(doc));
                    break;
                case DxoDoc.FORMATTED_NO_EMPTY_INLINE_VALUES:
                    sb.append("\n");
                    sb.append(DxoDoc.removeEmptyInlineValuesAndFormat(doc.toString()));
                    break;
                default:
                    sb.append(DxoDoc.removeFormatting(doc));
            }
        }

        return sb.toString();
    }

    /**
     * @param doc
     *
     * @return
     */
    public static String removeFormatting(DxoDoc doc) {
        return doc.toString().replace('\n', ' ').replace('\r', ' ').replaceAll("  ", "");
    }

    /**
     * overload the remove empty inline values
     *
     * @param doc
     *
     * @return
     */
    public static String removeEmptyInlineValues(DxoDoc doc) {
        return DxoDoc.removeEmptyInlineValues(DxoDoc.removeFormatting(doc));
    }

    /**
     * overload the remove empty inline values
     *
     * @param doc
     *
     * @return
     */
    public static String removeEmptyInlineValues(String doc) {
        return DxoDoc.removeEmptyInlineValues(doc, false);
    }

    /**
     * overload the remove empty inline values
     *
     * @param doc
     *
     * @return
     */
    public static String removeEmptyInlineValuesAndFormat(DxoDoc doc) {
        return DxoDoc.removeEmptyInlineValuesAndFormat(doc.toString());
    }

    /**
     * overload the remove empty inline values
     *
     * @param doc
     *
     * @return
     */
    public static String removeEmptyInlineValuesAndFormat(String doc) {
        return DxoDoc.removeEmptyInlineValues(doc, true);
    }

    /**
     * remove empty inline values -- if an element is empty remove it from the elemental array
     *
     * @param doc
     * @param format
     *
     * @return
     */
    public static String removeEmptyInlineValues(String doc,
                                                 boolean format) {
        StringBuffer sb = new StringBuffer();

        if (doc.indexOf("/>") != -1) {
            StringTokenizer tokens = new StringTokenizer(doc, "<");
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();
                sb.append("<");
                if (token.indexOf("/>") != -1) {
                    String tokenStart = token.substring(0, token.indexOf("/>"));
                    sb.append(tokenStart).append(">").append("</").append(tokenStart).append(">");
                    if (format) {
                        sb.append("\n");
                    }
                } else {
                    sb.append(token);
                }
            }
        } else {
            sb.append(doc);
        }

        return sb.toString();
    }

    /**
     * set an object from elements in the Root
     *
     * @param obj
     */
    public void setObjectFromElements(Object obj) {
        setObjectFromElement(_root.getElement(), obj);
    }

    /**
     * set an object from the Element
     *
     * @param elem
     * @param obj
     */
    public void setObjectFromElement(Element elem,
                                     Object obj) {
        DxoDoc.recurse(elem, obj, obj, null);
    }

    /**
     * set an object from Elements
     *
     * @param obj
     * @param hm
     */
    public void setObjectFromElements(Object obj,
                                      Map<String, Object> hm) {
        DxoDoc.setObjectFromElement(_root.getElement(), obj, hm);
    }

    /**
     * set an object from an Element
     *
     * @param elem
     * @param obj
     * @param hm
     */
    public static void setObjectFromElement(Element elem,
                                            Object obj,
                                            Map<String, Object> hm) {
        DxoDoc.recurse(elem, obj, obj, hm);
    }

    private static int count = 0;

    /**
     * Recurse through an element to set an object
     *
     * @param elem
     * @param parent
     * @param obj
     * @param hm
     */
    private static void recurse(Element elem,
                                Object parent,
                                Object obj,
                                Map<String, Object> hm) {
        DxoDoc.count++;

        if (DxoDoc.showDebug) {
            System.out.println("count " + DxoDoc.count);
        }

        if (elem.hasElements()) {
            Elements elems = elem.getElements();
            while (elems.hasMoreElements()) {
                Element elem1 = elems.next();

                if (DxoDoc.showDebug) {
                    System.out.println("elem1 " + elem1);
                }

                Class c = null;

                //todo: fix so that attributes can be set as well as elements
                //      the same way
                if (elem1.hasAttributes()) {
                    Attribute attr = elem1.getAttribute("classname") != null
                                     ? elem1.getAttribute("classname")
                                     : elem1.getAttribute("className") != null
                                       ? elem1.getAttribute("className")
                                       : null;
                    if (DxoDoc.showDebug) {
                        System.out.println("attr " + attr.getValue());
                    }

                    try {
                        c = Class.forName(attr.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (hm != null
                           && hm.containsKey(elem1.getName())) {
                    c = (Class) hm.get(elem1.getName());
                }

                if (!parent.equals(obj) && c == null) {
                    if (DxoDoc.showDebug) {
                        System.out.println("!parent equals obj");
                    }

                    DxoDoc.recurse(elem1, parent, obj, hm);
                } else if (c != null) {
                    if (DxoDoc.showDebug) {
                        System.out.println("c != null");
                    }

                    if (!c.getName().equals(obj.getClass().getName())) {
                        try {
                            if (DxoDoc.showDebug) {
                                System.out.println("!c.getName().equals(obj.getClass().getName()");
                            }
                            DxoDoc.recurse(elem1, obj, c.newInstance(), hm);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (DxoDoc.showDebug) {
                            System.out.println("c.getName().equals(obj.getClass().getName()");
                        }

                        DxoDoc.recurse(elem1, parent, obj, hm);
                    }
                } else {
                    if (DxoDoc.showDebug) {
                        System.out.println("c == null || parent = obj && c == null");
                    }

                    if (DxoDoc.showDebug) {
                        System.out.println("in first recursiveElements");
                    }

                    DxoDoc.recursiveElements(elem1, obj);
                }
            }

            ReflectUtil r = ReflectUtil.current();
            List lst = r.getMethodsOf(parent);
            for (Object aLst : lst) {
                Method method = (Method) aLst;
                Class[] cl = method.getParameterTypes();
                boolean setThis = false;
                for (Class aClass : cl) {
                    if (aClass.equals(obj.getClass())) {
                        setThis = true;
                        break;
                    }
                }

                if (setThis && parent != null) {
                    if (DxoDoc.showDebug) {
                        System.out.println("parent " + parent.getClass().getName());
                    }

                    parent = ReflectionHelper.runMethodInvocation(parent,
                                                                  method,
                                                                  new Class[]{obj.getClass()},
                                                                  new Object[]{obj});
                }
            }

            if (hm != null) {
                //rule out a generic Object.  This Object that is put into the hashmap,
                // MUST be a non generic Object
                if (obj.toString().indexOf("java.lang.Object") == -1) {
                    if (obj instanceof DxoIfc) {
                        hm.put(((DxoIfc) obj).getId(), obj);
                    } else {
                        if (showDebug) {
                            System.out.println("object not instance of DxoIfc");
                            System.out.println("obj.getClass().getName()" + obj.getClass().getName());
                        }
                    }
                }
            }
        } else {
            if (DxoDoc.showDebug) {
                System.out.println("in second recursiveElements");
            }

            DxoDoc.recursiveElements(elem, obj);
        }
    }

    /**
     * Recurse through element to set an object
     *
     * @param elem
     * @param obj
     */
    private static void recursiveElements(Element elem,
                                          Object obj) {
        ReflectUtil r = ReflectUtil.current();
        List lst = r.getFieldsOf(obj);

        for (Object aLst : lst) {
            Field o = (Field) aLst;

            if (o.getName().equals(elem.getName())) {
                try {
                    if (showDebug) {
                        System.out.println("o.getName() " + o.getName());
                        System.out.println("o.getType() " + o.getType());
                    }
                    if (o.getType().equals(Integer.TYPE)) {
                        r.setValueOf(obj, o.getName(), TypeParser.parseInt(elem.getTextString(), -1));
                    } else if (o.getType().equals(Boolean.TYPE)) {
                        r.setValueOf(obj, o.getName(), TypeParser.parseBoolean(elem.getTextString(), false));
                    } else if (o.getType().equals(Long.TYPE)) {
                        r.setValueOf(obj, o.getName(), TypeParser.parseLong(elem.getTextString(), -1));
                    } else if (o.getType().equals(Double.TYPE)) {
                        r.setValueOf(obj, o.getName(), TypeParser.parseDouble(elem.getTextString(), -1));
                    } else if (o.getType().equals(Float.TYPE)) {
                        r.setValueOf(obj, o.getName(), TypeParser.parseFloat(elem.getTextString(), -1));
                    } else if (o.getType().equals(String.class)) {
                        r.setValueOf(obj, o.getName(), elem.getTextString());
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DxoDoc parseFile(String str) {
        Document doc = null;

        try {
            doc = new Document(ClassLoader.getSystemResourceAsStream(str));
        } catch (Exception e) {
            try {
                doc = new Document(DxoDoc.class.getResourceAsStream(str));
            } catch (ParseException e1) {
                e1.printStackTrace();
                e.printStackTrace();
            }
        }

        return doc == null ? null : new DxoDoc(doc);
    }

    /**
     * Parse a file for the Xml Elements
     *
     * @param str
     *
     * @return
     */

    public static DxoDoc parseFile(URL url) {
        Document doc = null;

        try {
            doc = new Document(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc == null ? null : new DxoDoc(doc);
    }

    /**
     * Parse a String for the Xml Elements
     *
     * @param str
     *
     * @return
     */
    public static DxoDoc parse(String str) {
        Document doc = null;

        try {
            doc = new Document(new ByteArrayInputStream(str.getBytes()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new DxoDoc(doc);
    }

    /**
     * ONLY works for one level of mapping, not multiple levels todo: ensure child elements are put into the correct
     * place rather than one element bumping out a map
     *
     * @param doc
     *
     * @return
     */
    public static Map<String, Object> getDocumentAsMap(DxoDoc doc) {
        return DxoDoc.getElementAsMap(doc.getRoot());
    }

    /**
     * works for more than one level of mapping, object must implement the DxoIfc
     *
     * @param Dxo
     *
     * @return
     */
    public static Map<String, Object> getElementAsMap(Dxo dxo) {
        Map<String, Object> hm = new HashMap<String, Object>();

        Object tmpObject = new Object();

        DxoDoc.recurse(dxo.getElement(), tmpObject, tmpObject, hm);

        return hm;
    }

    /**
     * works for more than one level of mapping, object must implement the DxoIfc
     *
     * @param doc
     *
     * @return
     */
    public static Map<String, Object> getElementAsMap(DxoDoc doc) {
        return DxoDoc.getElementAsMap(doc._root);
    }

    /**
     * ONLY works for one level of mapping, not multiple levels
     *
     * @param Dxo
     *
     * @return
     */
    public static Map getSingleElementAsMap(Dxo dxo) {
        Map<String, Object> hm = new HashMap<String, Object>();

        DxoDoc.recurseForMap(dxo.getElement(), hm);

        return hm;
    }

    /**
     * ONLY works for one level of mapping, not multiple levels
     *
     * @param doc
     *
     * @return
     */
    public static Map getSingleElementAsMap(DxoDoc doc) {
        Map<String, Object> hm = new HashMap<String, Object>();

        DxoDoc.recurseForMap(doc._root.getElement(), hm);

        return hm;
    }

    /**
     * ONLY works for one level of mapping, not multiple levels
     *
     * @param elem
     * @param tmpMap
     */
    public static void recurseForMap(Element elem,
                                     Map<String, Object> tmpMap) {
        if (elem.hasElements()) {
            Elements elems = elem.getElements();
            while (elems.hasMoreElements()) {
                Element elem1 = elems.next();
                DxoDoc.recurseForMap(elem1, tmpMap);
            }
        } else {
            DxoDoc.recursiveMapElement(elem, tmpMap);
        }
    }

    /**
     * adds an element to a map called from the recurseForMap
     *
     * @param elem
     * @param tmpMap
     */
    public static void recursiveMapElement(Element elem,
                                           Map<String, Object> tmpMap) {
        tmpMap.put(elem.getName(), elem.getTextString());
    }
}
