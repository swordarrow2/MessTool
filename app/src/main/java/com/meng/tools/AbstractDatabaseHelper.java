package com.meng.tools;

import android.content.*;

import java.util.*;

public abstract class AbstractDatabaseHelper {

    private static ArrayList<AbstractDatabaseHelper> list = new ArrayList<>();

    public void init(Context context) {
        list.add(this);
    }

    public static void releaseAll() {
        for (AbstractDatabaseHelper helper : list) {
            helper.close();
        }
    }

    public abstract void close();
}
