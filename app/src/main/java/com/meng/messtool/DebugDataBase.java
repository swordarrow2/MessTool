package com.meng.messtool;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.*;

import com.meng.tools.*;

import java.util.*;

public class DebugDataBase extends AbstractDatabaseHelper {

    /*`
     *@author 清梦
     *@date 2024-07-01 15:54:50
     */
    public static final String TAG = "DebugDataBase";
    private final String DATABASE_NAME = "debug_log";
    private final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "op_log";

    private static DebugDataBase instance;

    private SQLiteOpenHelper sqLiteOpenHelper;

    public static DebugDataBase getInstance() {
        if (instance == null) {
            instance = new DebugDataBase();
        }
        return instance;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        if (sqLiteOpenHelper != null) {
            Debuger.addLog(TAG, "dbHelper has already init.");
        }
        sqLiteOpenHelper = new SQLiteOpenHelper(context, FileTool.getAppFile(FunctionSavePath.database, TABLE_NAME, "db").getAbsolutePath(), null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table if not exists " + TABLE_NAME + "( _id integer primary key autoincrement , _time long, _op varchar(512))");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
    }

    public long addOperate(String _op) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_op", _op);
        values.put("_time", Calendar.getInstance(Locale.CHINA).getTimeInMillis());
        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Pair<String, String>> getAllLog() {
        ArrayList<Pair<String, String>> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(new Pair<>(String.valueOf(cursor.getLong(1)), cursor.getString(2)));
        }
        cursor.close();
        db.close();
        return result;
    }

    @Override
    public void close() {
        if (sqLiteOpenHelper != null) {
            sqLiteOpenHelper.close();
        }
    }
}
