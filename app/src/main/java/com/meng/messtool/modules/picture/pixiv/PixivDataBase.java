package com.meng.messtool.modules.picture.pixiv;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

import com.meng.messtool.*;
import com.meng.tools.*;

import java.util.*;

class PixivDataBase extends AbstractDatabaseHelper {

    /*
     *@author 清梦
     *@date 2024-07-01 15:54:50
     */
    public static final String TAG = "PixivDataBase";
    private final String DATABASE_NAME = "pixiv_record";
    private final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "op_log";

    private static PixivDataBase instance;

    private SQLiteOpenHelper sqLiteOpenHelper;

    public static PixivDataBase getInstance() {
        if (instance == null) {
            instance = new PixivDataBase();
        }
        return instance;
    }

    public void init(Context context) {
        if (sqLiteOpenHelper != null) {
            throw new IllegalStateException("dbHelper has already init.");
        }
        regist();
        sqLiteOpenHelper = new SQLiteOpenHelper(context, FileTool.getAppFile(FunctionSavePath.database, DATABASE_NAME, "db").getAbsolutePath(), null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table if not exists " + TABLE_NAME + "( _id integer primary key autoincrement , _op varchar(200),_time long)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                switch (newVersion) {
                    case 2:
                        //
                    case 3:
                        //
                    default:
                        break;
                }
                //   db.execSQL("drop table record");
                //onCreate(db);
            }
        };
    }

    public long addFailed(String picture) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_op", picture);
        values.put("_time", Calendar.getInstance(Locale.CHINA).getTimeInMillis());
        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<String> getAllFailed() {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        int index = cursor.getColumnIndex("_op");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(cursor.getString(index));

        }
        cursor.close();
        db.close();
        return result;
    }

    public void deleteFailed(String pixivId) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "_op = ?", new String[]{pixivId});
    }

    @Override
    public void close() {
        if (sqLiteOpenHelper != null) {
            sqLiteOpenHelper.close();
        }
    }
}
