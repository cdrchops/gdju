package com.gerbildrop.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.pf.reflect.ReflectUtil;

/**
 * This class gives more freedom to write less code by using reflection to invoke a method and either get or set a value
 * safeSingleMethodInvocation is generally for setters runMethodInvocation is to get a value, but is recursively called
 * by the safeSingleMethodInvocation
 *
 * @author timo
 * @version $Id: ReflectionHelper.java,v 1.2 2007/05/01 15:27:57 vivaldi Exp $ $Copyright: Copyright 2000-2006
 *          GerbilDrop Software All rights reserved. $
 * @since 26Sep06
 */
public class ReflectionHelper {
    //standard object methods that every object contains.  When doing some reflection calls, you don't want to
    // get these values, expecially when doing recursive reflection calls
    private static final Set<String> objectMethods = new HashSet<String>();

    static {
        ReflectionHelper.objectMethods.add("toString");
        ReflectionHelper.objectMethods.add("clone");
        ReflectionHelper.objectMethods.add("wait");
        ReflectionHelper.objectMethods.add("equals");
        ReflectionHelper.objectMethods.add("notify");
        ReflectionHelper.objectMethods.add("notifyAll");
        ReflectionHelper.objectMethods.add("finalize");
        ReflectionHelper.objectMethods.add("hashCode");
        ReflectionHelper.objectMethods.add("registerNatives");
    }

    /**
     * @param obj    Object the object that contains the value and method that we're going to try to get
     * @param method Method to lookup for our request
     *
     * @return new Object of the method we've requested
     */
    public static Object runMethodInvocation(Object obj, Method method) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{}, new Object[]{});
    }

    /**
     * @param obj    Object the object that contains the value and method that we're going to try to get
     * @param method String value of the method that we're going to try to set
     *
     * @return new Object of the method we've requested
     */
    public static Object runMethodInvocation(Object obj, String method) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{}, new Object[]{});
    }

    /**
     * run a method invocation on an object with a primitive value
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  int value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, int value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{Integer.TYPE}, new Object[]{value});
    }

    /**
     * run a method invocation on an object with a primitive value
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  float value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, float value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{Float.TYPE}, new Object[]{value});
    }

    /**
     * run a method invocation on an object with a primitive value
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  double value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, double value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{Double.TYPE}, new Object[]{value});
    }

    /**
     * run a method invocation on an object with a primitive value
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  long value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, long value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{Long.TYPE}, new Object[]{value});
    }

    /**
     * run a method invocation on an object with a primitive value
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  boolean value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, boolean value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{Boolean.TYPE}, new Object[]{value});
    }

    /**
     * run a method invocation on an object
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  Object value of the argument that we're going to try to set
     *
     * @return new Object with the value we've set
     */
    public static Object runMethodInvocation(Object obj, String method, Object value) {
        return ReflectionHelper.runMethodInvocation(obj, method, new Class[]{value.getClass()}, new Object[]{value});
    }

    /**
     * overloaded method of our core function method that actually does the method invocation
     *
     * @param obj      Object the object that contains the value and method that we're going to try to invoke
     * @param method   Method value of the method that we're going to try to invoke
     * @param argList  Class[] argument classes for the method that we're going to try to invoke
     * @param argsList Object[] argument list values for the method that we're going to try to invoke
     *
     * @return new Object with the value we've set or the Object value we've requested from a method
     */
    public static Object runMethodInvocation(Object obj, Method method, Class[] argList, Object[] argsList) {
        return ReflectionHelper.runMethodInvocation(obj, method.getName(), argList, argsList);
    }

    public static Object runMethodInvocationSetter(Object obj, Method method, Class[] argList, Object[] argsList) {
        return ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method.getName()), argList, argsList);
    }

    /**
     * overloaded method of our core function method that actually does the method invocation
     *
     * @param obj      Object the object that contains the value and method that we're going to try to invoke
     * @param method   String value of the method that we're going to try to invoke
     * @param argList  Class[] argument classes for the method that we're going to try to invoke
     * @param argsList Object[] argument list values for the method that we're going to try to invoke
     *
     * @return new Object with the value we've set or the Object value we've requested from a method
     */
    public static Object runMethodInvocation(Object obj, String method, Class[] argList, Object[] argsList) {
        Object o = null;

        Method m2 = ReflectUtil.current().findMethod(obj.getClass(), method, argList);

        if (m2 != null) {
            try {
                o = m2.invoke(obj, argsList);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
//            System.out.println("m2 is null ");
        }

        return o;
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  Object value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, Object value) {
        if (value != null
            && StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  String value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, String value) {
        if (StringUtil.hasLen(value)
            && StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  int value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, int value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  long value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, long value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  float value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, float value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  double value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, double value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  boolean value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentMethodInvocation(Object obj, String method, boolean value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, method, value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  Object value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, Object value) {
        if (value != null
            && StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  String value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, String value) {
        if (StringUtil.hasLen(value)
            && StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  int value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, int value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  long value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, long value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  float value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, float value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  double value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, double value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @param value  boolean value of the argument that we're going to try to set
     */
    public static void safeSingleArgumentSetter(Object obj, String method, boolean value) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.setMethod(method), value);
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return int value returned from getter
     */
    public static int intGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return (Integer) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return -1;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return long value returned from getter
     */
    public static long longGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return (Long) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return -1;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return float value returned from getter
     */
    public static float floatGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return (Float) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return -1;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return double value returned from getter
     */
    public static double doubleGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return (Double) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return -1;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return Object value returned from getter
     */
    public static Object objectGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return null;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return boolean value returned from getter
     */
    public static boolean booleanGetter(Object obj, String method) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            return (Boolean) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
        } else {
            return false;
        }
    }

    //todo: refactor all getters and setters for this same type logic

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj                  Object the object that contains the value and method that we're going to try to set
     * @param method               String value of the method that we're going to try to set
     * @param methodDoesContainGet boolean does the method name already containg "get" or do we need to add it
     *
     * @return value boolean value of the argument that we're going to try to set
     */
    public static String stringGetter(Object obj, String method, boolean methodDoesContainGet) {
        if (StringUtil.hasLen(method)
            && obj != null) {
            if (methodDoesContainGet) {
                return (String) ReflectionHelper.runMethodInvocation(obj, method);
            } else {
                return (String) ReflectionHelper.runMethodInvocation(obj, ReflectionHelper.getMethod(method));
            }
        } else {
            return null;
        }
    }

    /**
     * Set a single value into a setter with a single argument based on the standard types that are possible.  Aleviates
     * extra checks in the code and handles them all in one convenient location
     *
     * @param obj    Object the object that contains the value and method that we're going to try to set
     * @param method String value of the method that we're going to try to set
     * @return String value returned from getter
     */
    public static String stringGetter(Object obj, String method) {
        return ReflectionHelper.stringGetter(obj, method, false);
    }

    /**
     * takes a methodName and returns getMethodName where methodName is the name of the method we're looking up
     *
     * @param methodName String methodName that we want to convert to titlecase and lookup via reflection
     *
     * @return String get and titlecase methodName
     */
    public static String getMethod(String methodName) {
        return "get" + ReflectionHelper.methodNameToTitleCase(methodName);
    }

    /**
     * takes a methodName and returns setMethodName where methodName is the name of the method we're looking up
     *
     * @param methodName String methodName that we want to convert to titlecase and lookup via reflection
     *
     * @return String set and titlecase methodName
     */
    public static String setMethod(String methodName) {
        return "set" + ReflectionHelper.methodNameToTitleCase(methodName);
    }

    /**
     * converts a String to a titleCase
     *
     * @param methodName String methodName that we want to convert
     *
     * @return String title case value of methodName
     */
    private static String methodNameToTitleCase(String methodName) {
        return methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
    }

    public static boolean methodNotStandard(Class c) {
        return !ReflectionHelper.objectMethods.contains(c.getName());
    }
}
