package com.lantone.es.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className: com.lantone.es.util-> ListUtil
 * @description: list工具类
 * @author: kongwz
 * @createDate: 2021-09-22 14:46
 * @version: 1.0
 * @todo:
 */
public class ListUtil {
    public static final int FIRST = 0;

    private ListUtil() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList();
    }

    public static boolean isEmpty(List list) {
        if (null == list) {
            return Boolean.TRUE;
        } else if (list.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return list.size() < 1 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    public static <E> E firstEntity(List<E> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    public static <E> ArrayList<E> arrayToList(E[] strArray) {
        ArrayList<E> arrayList = new ArrayList(strArray.length);
        Collections.addAll(arrayList, strArray);
        return arrayList;
    }

    public static void main(String[] args) throws Exception {
        String[] i = new String[]{"A", "B"};
        List<String> o = arrayToList(i);
        System.out.println("输入参数:" + i);
        System.out.println("输出参数:" + o);
    }
}
