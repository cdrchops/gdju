package com.gerbildrop.xml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import electric.xml.Attribute;
import electric.xml.Element;
import electric.xml.Elements;
import org.pf.reflect.ReflectUtil;

/**
 * This class is to give a little more freedom to adding Xml Objects and make it easier to add objects and get back a
 * class or list of elements populate an object from elements or populate elements from an uncomplicated object -- no
 * interfaces
 * <p/>
 * It is basically a wrapper for an Exml Element
 * <p/>
 * Ported from an Open Source project at SourceForge called GerbilDrop Java Utilities... with permission
 *
 * @author timo
 * @version $Id: Dxo.java,v 1.2 2007/04/26 17:35:31 vivaldi Exp $ $Copyright: Copyright 2002-2005 Hotels.com, L.P. All
 *          rights reserved. $
 * @since 14Aug06
 */
public class Dxo {
    //EXML Element
    Element _elem;

    /** constructor for an XmlObject -- an Element specifically */
    public Dxo() {
        _elem = new Element();
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param elem Exml Element to set as the root
     */
    public Dxo(Element elem) {
        _elem = elem;
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name String name of an element to set as the root
     */
    public Dxo(String name) {
        _elem = new Element(name);
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name String[] elements to set as the root
     */
    public Dxo(String[] name) {
        this(name[0], name[1]);
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value int value of the element
     */
    public Dxo(String name,
               int value) {
        this(name, String.valueOf(value));
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value long value of the element
     */
    public Dxo(String name,
               long value) {
        this(name, String.valueOf(value));
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value double value of the element
     */
    public Dxo(String name,
               double value) {
        this(name, String.valueOf(value));
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value float value of the element
     */
    public Dxo(String name,
               float value) {
        this(name, String.valueOf(value));
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value boolean value of the element
     */
    public Dxo(String name,
               boolean value) {
        this(name, String.valueOf(value));
    }

    /**
     * overloaded constructor for an xml object
     *
     * @param name  String name of the element
     * @param value String value of the element
     */
    public Dxo(String name,
               String value) {
        this(name);
        _elem.setText(value);
    }

    /**
     * set the name of the Element for the xml object
     *
     * @param name String name of the element
     */
    public void setName(String name) {
        if (_elem == null) {
            _elem = new Element(name);
        } else {
            _elem.setName(name);
        }
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value int value of the root element
     */
    public void setText(int value) {
        setText(String.valueOf(value));
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value long value of the root element
     */
    public void setText(long value) {
        setText(String.valueOf(value));
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value double value of the root element
     */
    public void setText(double value) {
        setText(String.valueOf(value));
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value float value of the root element
     */
    public void setText(float value) {
        setText(String.valueOf(value));
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value boolean value of the root element
     */
    public void setText(boolean value) {
        setText(String.valueOf(value));
    }

    /**
     * overloaded setter set the text value of the root element
     *
     * @param value String value of the root element
     */
    public void setText(String value) {
        if (_elem == null) {
            _elem = new Element();
        }

        _elem.setText(value);
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param attr Attribute attribute to add to the element
     */
    public void addAttribute(Attribute attr) {
        if (_elem == null) {
            _elem = new Element();
        }

        _elem.setAttribute(attr);
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value int value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             int value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value long value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             long value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value double value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             double value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value float value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             float value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value boolean value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             boolean value) {
        addAttribute(name, String.valueOf(value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param name  String name of the Attribute to add to the element
     * @param value String value of the Attribute to add to the element
     */
    public void addAttribute(String name,
                             String value) {
        addAttribute(new Attribute(name, value));
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param lst Collection of Attributes to add to the element
     */
    public void addAttributes(Collection lst) {
        for (Object aLst : lst) {
            Attribute attribute = (Attribute) aLst;
            addAttribute(attribute);
        }
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param attributes Attribute[] to add to the element
     */
    public void addAttributes(Attribute[] attributes) {
        for (Attribute attribute : attributes) {
            addAttribute(attribute);
        }
    }

    /**
     * overloaded method for adding an attribute to the Element
     *
     * @param attributes Map of Attributes to add to the element
     */
    public void addAttributes(Map attributes) {
        for (Object o1 : attributes.keySet()) {
            String s = (String) o1;
            Object o = attributes.get(s);

            if (o instanceof Attribute) {
                addAttribute((Attribute) o);
            } else if (o instanceof String) {
                addAttribute(s, (String) o);
            }
        }
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value int value of the child element
     */
    public void addChild(String name,
                         int value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value long value of the child element
     */
    public void addChild(String name,
                         long value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value double value of the child element
     */
    public void addChild(String name,
                         double value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value float value of the child element
     */
    public void addChild(String name,
                         float value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value boolean value of the child element
     */
    public void addChild(String name,
                         boolean value) {
        addChild(name, String.valueOf(value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param name  String name of the child element
     * @param value String value of the child element
     */
    public void addChild(String name,
                         String value) {
        addChild(new Dxo(name, value));
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param dxo DynamicXmlObject to add as a child to the root element
     */
    public void addChild(Dxo dxo) {
        if (dxo != null) {
            addChild(dxo.getElement());
        } else {
            addChild(new Dxo("error adding element since DynamicXmlObject was null").getElement());
        }
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param element Exml Element to add as a child to the root element
     */
    public void addChild(Element element) {
        if (_elem == null) {
            _elem = new Element();
        }

        _elem.addElement(element);
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param lst Collection of objects to add as Elements to the root element of this object
     */
    public void addChildren(Collection lst) {
        for (Object element : lst) {
            if (element instanceof Element) {
                addChild((Element) element);
            } else if (element instanceof Dxo) {
                addChild((Dxo) element);
            } else if (element instanceof String) {
                addChild(new Dxo((String) element));
            } else if (element instanceof String[]) {
                addChild(new Dxo((String[]) element));
            }
        }
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param DynamicXmlObject
     */
    public void addChildren(Dxo[] DynamicXmlObject) {
        for (Dxo dynamicXmlObject1 : DynamicXmlObject) {
            addChild(dynamicXmlObject1);
        }
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param elems
     */
    public void addChildren(Element[] elems) {
        for (Element element : elems) {
            addChild(element);
        }
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param hm
     */
    public void addChildren(Map hm) {
        for (Object o : hm.keySet()) {
            String s = (String) o;
            Object obj = hm.get(s);

            if (obj instanceof Dxo) {
                Dxo dx = new Dxo(s);
                dx.addChild((Dxo) obj);
                addChild(dx);
            } else if (obj instanceof Collection) {
                Dxo dx = new Dxo(s);
                dx.addChildren((Collection) obj);
                addChild(dx);
            } else if (obj instanceof String) {
                addChild(s, (String) obj);
            } else if (obj instanceof Element) {
                addChild((Element) obj);
            }
        }
    }

    /** @return Elements Exml Elements of the root element */
    public Elements getChildElements() {
        return getElement().getElements();
    }

    /** @return List of Elements of the Root element */
    public List<Element> getElementsAsList() {
        List<Element> lst = new ArrayList<Element>();
        Elements elems = getElement().getElements();

        while (elems.hasMoreElements()) {
            lst.add(elems.next());
        }

        return lst;
    }

    /**
     * getter for the elements of the root as a Map
     *
     * @return Map elements of the root element as a Map
     */
    public Map getElementsAsMap() {
        Map<String, String> hm = new HashMap<String, String>();

        Elements elems = getElement().getElements();

        while (elems.hasMoreElements()) {
            Element elem = elems.next();
            hm.put(elem.getName(), elem.getTextString());
        }

        return hm;
    }

    /**
     * getter for the root element
     *
     * @return Element Exml Element
     */
    public Element getElement() {
        return _elem;
    }

    /**
     * setter for the root element
     *
     * @param elem Exml Element to set as the root element
     */
    public void setElement(Element elem) {
        _elem = elem;
    }

    /**
     * overloaded method to add child elements to the root element of this xml object
     *
     * @param obj Object set generic text to a DynamicXmlObject
     */
    public void addChildren(Object obj) {
        ReflectUtil r = ReflectUtil.current();
        List lst = r.getFieldsOf(obj);
        for (Object aLst : lst) {
            Field o = (Field) aLst;
            Dxo dx = new Dxo(o.getName());

            try {
                if (o.getType() == Integer.TYPE) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } else if (o.getType() == Boolean.TYPE) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } else if (o.getType() == Long.TYPE) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } else if (o.getType() == Double.TYPE) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } else if (o.getType() == Float.TYPE) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                } else if (o.getType() == String.class) {
                    dx.setText(String.valueOf(r.getValueOf(obj, o.getName())));
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            addChild(dx);
        }
    }
}
