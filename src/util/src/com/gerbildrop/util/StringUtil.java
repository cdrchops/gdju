package com.gerbildrop.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {

    public StringUtil() {
    }

    public static boolean indexOf(String str, String str2) {
        return str.indexOf(str2) != -1;
    }

    public static boolean indexOfIgnoreCase(String str, String str2) {
        return StringUtil.hasLen(str) && StringUtil.hasLen(str2) && str.toUpperCase().indexOf(str2.toUpperCase()) != -1;
    }

    public static String processAsTernary(String str, String defaultValue) {
        return StringUtil.hasLen(str) ? str : defaultValue;
    }

    public static boolean hasLen(int len, String str, boolean trimString) {
        boolean hasLen = false;
        if (str != null) {
            if (trimString)
                str = str.trim();
            if (!str.equalsIgnoreCase("null"))
                hasLen = str.length() > len;
        }
        return hasLen;
    }

    public static boolean hasLenNoTrim(int len, String str) {
        return StringUtil.hasLen(len, str, false);
    }

    public static boolean hasLen(int len, String str) {
        return StringUtil.hasLen(len, str, true);
    }

    public static boolean hasLen(String str) {
        return StringUtil.hasLen(0, str, false);
    }

    public static boolean hasLen(String str[]) {
        return str != null && str.length > 0;
    }

    public static boolean hasLenTrim(String str) {
        return StringUtil.hasLen(0, str, true);
    }

    public static boolean isEqual(String str, String str2) {
        return StringUtil.hasLen(str) && StringUtil.hasLen(str2) && str.equals(str2);
    }

    public static boolean isEqualIgnoreCase(String str, String str2) {
        return StringUtil.hasLen(str) && StringUtil.hasLen(str2) && str.equalsIgnoreCase(str2);
    }

    public static String[] getStringArray(String str) {
        StringTokenizer tokens = new StringTokenizer(str, " ");

        String starr[] = new String[tokens.countTokens()];

        int count = 0;
        while (tokens.hasMoreTokens()) {
            starr[count] = tokens.nextToken();
            count++;
        }

        return starr;
    }

    public static String removeEnding(String str, int len) {
        return str.substring(0, str.length() - len);
    }

    public static String removeEnding(String str) {
        return StringUtil.removeEnding(str, 1);
    }

    public static void parseDelimitedString(String string, String delimiter, boolean bln, List<String> appendTo) {
        StringTokenizer tokens = new StringTokenizer(string, delimiter);

        while (tokens.hasMoreTokens()) {
            appendTo.add(tokens.nextToken().trim());
            if (bln) {
                appendTo.add("\n");
            }
        }
    }

    public static String procStrNull(String txt) {
        if (StringUtil.hasLen(txt)) {
            return txt;
        } else {
            return "";
        }
    }

    public static String checkReq(String txtToCheck, String falseReply) {
        return StringUtil.checkReq(txtToCheck, txtToCheck, falseReply);
    }

    public static String checkReq(String txtToCheck, String trueReply, String falseReply) {
        return StringUtil.hasLen(txtToCheck) ? trueReply : falseReply;
    }

    public static String processPipeSemiColon(String medrepLine3) {
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(medrepLine3, ":");
        String mrl3a[] = new String[st.countTokens()];
        int counter = 0;

        while (st.hasMoreTokens()) {
            mrl3a[counter] = st.nextToken();
            counter++;
        }

        st = new StringTokenizer(mrl3a[1], ";");
        mrl3a = new String[st.countTokens()];
        counter = 0;
        while (st.hasMoreTokens()) {
            mrl3a[counter] = st.nextToken();
            counter++;
        }

        for (String aMrl3a : mrl3a) {
            sb.append("<input type=\"text\" name=\"mrline3\" value=\"").append(aMrl3a).append("\"><br>");
        }

        return sb.toString();
    }

    public static String processToPipeSemiColon(String[] starr) {
        StringBuffer sb = new StringBuffer();
        sb.append(starr.length).append(":");

        for (int i = 0; i < starr.length; i++) {
            sb.append(starr[i]);
            int a = i + 1;
            if (a != starr.length) {
                sb.append(";");
            }
        }

        return sb.toString();
    }

    public static String[] processKey(String key) {
        String keys[] = new String[3];
        keys[0] = key.substring(3, 4);
        keys[1] = key.substring(6);
        keys[2] = key.substring(4, 6);
        return keys;
    }

    public static int stringToInteger(String str) {
        int newNumber = -1;
        try {
            newNumber = Integer.parseInt(str);
        } catch (Exception e) {
            //do nothing
        }

        return newNumber;
    }

    public static boolean isBoolean(String strBl) {
        return TypeParser.parseBoolean(strBl);
    }

    public static String titleCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String orderStringArray(String starr[], boolean forward, String htmlBegin, String htmlEnd) {
        if (forward) {
            return StringUtil.orderStringArrayForward(starr, htmlBegin, htmlEnd);
        } else {
            return StringUtil.orderStringArrayReverse(starr, htmlBegin, htmlEnd);
        }
    }

    public static String orderStringArray(String starr[], boolean forward) {
        return StringUtil.orderStringArray(starr, forward, null, null);
    }

    public static String orderStringArrayForward(String starr[], String htmlBegin, String htmlEnd) {
        StringBuffer sb = new StringBuffer();

        for (String aStarr : starr) {
            if (StringUtil.hasLen(htmlBegin)) {
                sb.append(htmlBegin);
            }
            sb.append(aStarr).append("\n");
            if (StringUtil.hasLen(htmlEnd)) {
                sb.append(htmlEnd);
            }
        }

        return sb.toString();
    }

    public static String orderStringArrayReverse(String starr[], String htmlBegin, String htmlEnd) {
        StringBuffer sb = new StringBuffer();

        for (int i = starr.length - 1; i >= 0; i--) {
            if (StringUtil.hasLen(htmlBegin)) {
                sb.append(htmlBegin);
            }
            sb.append(starr[i]).append("\n");
            if (StringUtil.hasLen(htmlEnd)) {
                sb.append(htmlEnd);
            }
        }

        return sb.toString();
    }

    public static boolean hasLenObj(Object[] objArr) {
        return objArr != null && objArr.length > 0;
    }

    public static String readFile(String fileName) {
        StringBuffer buffer = new StringBuffer();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        Reader in = null;
        try {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis, "UTF8");
            in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
//            Debug.error(e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (isr != null) {
                    isr.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String stripCharacters(String str, String charactersToStrip) {
        String returnValue = null;
        if (StringUtil.hasLen(str)) {
            if (StringUtil.hasLen(charactersToStrip)) {
                StringBuffer tmp = new StringBuffer(charactersToStrip.length());
                StringBuffer sb = new StringBuffer(str.length());

                int matchingPosition = 0;

                char[] c1 = charactersToStrip.toCharArray();
                char[] c2 = str.toCharArray();

                int c1Len = c1.length;
                int c2Len = c2.length;

                for (int i = 0; i < c2Len; i++) {
                    if (c2[i] == c1[matchingPosition]) {
                        if (matchingPosition == c1Len - 1) {
                            matchingPosition = 0;
                            tmp.setLength(0);
                        } else {
                            matchingPosition++;
                            tmp.append(c2[i]);
                        }
                    } else {
                        if (matchingPosition > 0) {
                            sb.append(tmp);
                            matchingPosition = 0;
                            tmp.setLength(0);
                        }
                        sb.append(c2[i]);
                    }
                }

                if (tmp.length() > 0) {
                    sb.append(tmp);
                }

                returnValue = sb.toString();
            } else {
                returnValue = str;
            }
        }

        return returnValue;
    }

    public static String setTernary(String str, String defaultValue) {
        return StringUtil.hasLen(str) ? str : defaultValue;
    }
}
