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

package com.gerbildrop.util;

/**
 * things to do
 * add classes that don't have accessors (like map or list)
 * add accessors to classes (like map, or other things)
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//import com.gerbildrop.logging.Debug;
import org.pf.reflect.ReflectUtil;

/**
 * pull object values from getters and setters recurse through objects returned e.g. base class has a ToStringObject
 * that is returned ToStringObject has a ToStringObject2 that is returned ToStringObject2 has a ToStringObject3 that is
 * returned ToStringObject3 returns some Strings and ints we want to show all of the values through the tree
 * <p/>
 * will NOT get private members, only those items accessible via getters
 * <p/>
 * formatting will have to be adjusted to whatever is needed below is an example of what it prints out e.g. in
 * ToStringObject it found ToStringObject2 and gets that information and finds ToStringObject3 and gets that information
 * then goes back to ToStringObject2 and finishes off the rest of the information then goes back to ToStringObject and
 * finds ToStringObject3 getting that information then goes back to ToStringObject finishes off the rest of that
 * information it takes a little reading, but everywhere there is a "name" String on the far left side there is also a
 * gettter for the same object on the line above
 * <p/>
 * <com.gerbildrop.util.ToStringUtilTestObject> <getMyTestObject>class com.gerbildrop.util.toStringTest.TestObject</getMyTestObject>
 * <com.gerbildrop.util.toStringTest.TestObject> <getMyTestObject2>class com.gerbildrop.util.toStringTest.TestObject2</getMyTestObject2>
 * <com.gerbildrop.util.toStringTest.TestObject2> <getMyTestObject3>class com.gerbildrop.util.toStringTest.TestObject3</getMyTestObject3>
 * <com.gerbildrop.util.toStringTest.TestObject3> <isAvailable>false</isAvailable> <getBob>name's bob</getBob>
 * </com.gerbildrop.util.toStringTest.TestObject3> </com.gerbildrop.util.toStringTest.TestObject2>
 * <getMyTestObject3FromMyTestObject>class com.gerbildrop.util.toStringTest.TestObject3</getMyTestObject3FromMyTestObject>
 * <com.gerbildrop.util.toStringTest.TestObject3> <isAvailable>false</isAvailable> <getBob>name's bob</getBob>
 * </com.gerbildrop.util.toStringTest.TestObject3> </com.gerbildrop.util.toStringTest.TestObject>
 * </com.gerbildrop.util.ToStringUtilTestObject>
 */
public class ToStringUtil {
    public static void print(Object obj) {
        System.out.println(tryThis(obj));
    }

    public static StringBuffer tryThis(Object obj) {
        return tryThis(obj, 0);
    }

    public static StringBuffer tryThis(Object obj, int iterations) {
        StringBuffer sb = new StringBuffer();
        Class objClass = obj.getClass();
        String className = objClass.getName();
        sb.append(formatDisplay(iterations)).append("<").append(className).append(">");

        Method m[] = objClass.getMethods();
        for (Method method : m) {
            Class tmpClass = method.getReturnType();
            String methodName = method.getName();

            if (!tmpClass.isPrimitive()
                && !tmpClass.isInterface()
                && tmpClass != String.class
                && !methodName.equals("getClass")
                && !methodName.equals("clone")) {

                //only do objects and objects that aren't Strings
                sb.append(formatDisplay(iterations))
                        .append("\t<")
                        .append(methodName)
                        .append(">")
                        .append(tmpClass)
                        .append("</")
                        .append(methodName)
                        .append(">");

                Object o = runMethodInvocation(obj, method);

                if (o != null) {
                    sb.append(tryThis(o, iterations++));
                }
            } else if (!tmpClass.getName().equals("void")
                       && !tmpClass.isInterface()
                       && !methodName.equals("hashCode")
                       && !methodName.equals("equals")
                       && !methodName.equals("toString")
                       && !methodName.equals("getClass")
                       && !methodName.equals("clone")) {
                Object o = runMethodInvocation(obj, method);

                //don't want setters, nor the object inherited portions like wait, hashCode, equals, toString, and getClass
                sb.append(formatDisplay(iterations))
                        .append("\t<")
                        .append(methodName)
                        .append(">")
                        .append(o)
                        .append("</")
                        .append(methodName)
                        .append(">");
            }
            /* else if(name.equals("toString")) {
                //most of the time this isn't even used, but if this is left uncommented
                // it will print out every toString() with the hashCode that's with it, UNLESS the toString() is actually
                // implemented, then it will printout those values
                System.out.println("\t " + name + " was toString " + runMethodInvocation(obj, method));
            }*/
        }

        sb.append(formatDisplay(iterations))
                .append("</")
                .append(className)
                .append(">");
//        System.out.println("done with " + className);
        return sb;
    }

    private static Object runMethodInvocation(Object obj, Method method) {
        Object o = null;

        Class arglist[] = new Class[]{};
        Object argsList[] = new Object[]{};
        Method m2 = ReflectUtil.current().findMethod(obj.getClass(), method.getName(), arglist);

        if (m2 != null) {
            try {
                o = m2.invoke(obj, argsList);
            } catch (IllegalAccessException e) {
//                Debug.error(e);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                Debug.error(e);
                e.printStackTrace();
            }
        }

        return o;
    }

    private static String formatDisplay(int iterations) {
        StringBuffer sb = new StringBuffer();

        if (iterations == 0) {
            sb.append("\n");
        } else {
            for (int i = 0; i < iterations; i++) {
                sb.append("\n\t");
            }
        }

        return sb.toString();
    }
}
/*
Field fields[] = obj.getClass().getFields();
for (int i = 0; i < fields.length; i++) {
    Field field = fields[i];
    if (!field.getType().isPrimitive() && field.getType() != String.class) {
        System.out.println(field.getName() + " isObject");
    } else {
        System.out.println(field.getName() + "primitive or string");
    }
}
*/