package com.gerbildrop.util;

public class TypeParser {
    public static boolean parseBoolean(String str) {
        return TypeParser.parseBoolean(str, false);
    }

    public static boolean parseBoolean(String str, boolean defaultValue) {

        boolean returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = str.equalsIgnoreCase("true")
                              || str.equalsIgnoreCase("1")
                              || str.equalsIgnoreCase("yes")
                              || str.equalsIgnoreCase("y")
                              || str.equalsIgnoreCase("t");
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static int parseInt(String str) {
        return TypeParser.parseInt(str, -1);
    }

    public static int parseInt(String str, int defaultValue) {

        int returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = Integer.parseInt(str);
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static long parseLong(String str) {
        return TypeParser.parseLong(str, -1);
    }

    public static long parseLong(String str, long defaultValue) {

        long returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = Long.parseLong(str);
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static float parseFloat(String str) {
        return TypeParser.parseFloat(str, -1);
    }

    public static float parseFloat(String str, float defaultValue) {

        float returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = Float.parseFloat(str);
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static double parseDouble(String str) {
        return TypeParser.parseDouble(str, -1);
    }

    public static double parseDouble(String str, double defaultValue) {

        double returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = Double.parseDouble(str);
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static short parseShort(String str) {
        return TypeParser.parseShort(str, (short) -1);
    }

    public static short parseShort(String str, short defaultValue) {

        short returnValue;

        if (StringUtil.hasLen(str)) {
            try {
                returnValue = Short.parseShort(str);
            } catch (Exception e) {
                returnValue = defaultValue;
            }
        } else {
            returnValue = defaultValue;
        }

        return returnValue;
    }
}
