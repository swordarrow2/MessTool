package com.meng.tools;

import java.util.*;

/*
 *package  com.meng.tools
 *@author  清梦
 *@date    2024/8/1 12:50
 */
public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T t) {
        list.add(t);
    }

    public T pop() {
        return list.removeLast();
    }

    public T peek() {
        return list.getLast();
    }

    public void clear() {
        list.clear();
    }
}
