package com.meng.tools;

import java.util.*;

/*
 *package  com.meng.tools
 *@author  清梦
 *@date    2024/7/30 21:10
 */
public class AppStack {
    private static LinkedList arrayList = new LinkedList();

    public static void push(Object o) {
        arrayList.add(o);
    }

    public static Object pop() {
        return arrayList.removeLast();
    }

    public static Object peek() {
        return arrayList.getLast();
    }

    public static void clear() {
        arrayList.clear();
    }
}
