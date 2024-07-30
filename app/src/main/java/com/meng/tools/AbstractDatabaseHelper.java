package com.meng.tools;

import java.util.*;

public abstract class AbstractDatabaseHelper {

    private static ArrayList<AbstractDatabaseHelper> list = new ArrayList<>();

    public final void regist() {
        list.add(this);
    }

    public static void releaseAll() {
        for (AbstractDatabaseHelper helper : list) {
            helper.close();
        }
    }

    public abstract void close();
}
