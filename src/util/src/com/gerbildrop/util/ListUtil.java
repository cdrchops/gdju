package com.gerbildrop.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static List<Object> appendList(List<Object>[] listArr) {
        List<Object> lst = new ArrayList<Object>();
        for (List<Object> list : listArr) {
            lst.addAll(list);
        }

        return lst;
    }

    public static boolean contains(List<Object> lst, Object obj) {
        return lst.contains(obj);
    }

    public static boolean contains(List<Object> lst, Object[] obj) {
        boolean returnValue = false;

        for (Object o : obj) {
            if (lst.contains(o)) {
                returnValue = true;
                break;
            }
        }

        return returnValue;
    }
}