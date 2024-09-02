package com.meng.messtool.modules.media.pixiv_2;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

import com.meng.messtool.modules.media.pixiv_2.pojo.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.util.*;

class PixivDatabase extends AbstractDatabaseHelper {

    /*
     *@author 清梦
     *@date 2024-07-01 15:54:50
     */
    public static final String TAG = "PixivDatabase";
    private final String DATABASE_NAME = "pixiv_record";
    private final int DATABASE_VERSION = 2;
    private final String TABLE_NAME_FAILED = "op_log";
    private final String TABLE_NAME_FAVORITE = "op_favorite";

    private static PixivDatabase instance;

    private SQLiteOpenHelper sqLiteOpenHelper;

    public static PixivDatabase getInstance() {
        if (instance == null) {
            instance = new PixivDatabase();
        }
        return instance;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        if (sqLiteOpenHelper != null) {
            return;
        }
        sqLiteOpenHelper = new SQLiteOpenHelper(context, FileTool.getAppFile(FileSavePath.DATABASE, DATABASE_NAME, "db").getAbsolutePath(), null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table if not exists " + TABLE_NAME_FAILED + "( _id integer primary key autoincrement , _op varchar(200),_time long)");
                db.execSQL("create table if not exists " + TABLE_NAME_FAVORITE + "( _id integer primary key autoincrement , _op varchar(200))");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                switch (newVersion) {
                    case 2:
                        db.execSQL("create table if not exists " + TABLE_NAME_FAVORITE + "( _id integer primary key autoincrement , _op varchar(200))");
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

    public long addFailedId(String picture) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_op", picture);
        values.put("_time", Calendar.getInstance(Locale.CHINA).getTimeInMillis());
        return db.insert(TABLE_NAME_FAILED, null, values);
    }

    public long addFavorite(String picture) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_op", picture);
        return db.insert(TABLE_NAME_FAVORITE, null, values);
    }

    public ArrayList<String> getAllFailedId() {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_FAILED, null);
        int index = cursor.getColumnIndex("_op");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return result;
    }

    public PojoLocalFavorite getFavorite() {
        PojoLocalFavorite favorite = new PojoLocalFavorite();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_FAVORITE, null);
        int index = cursor.getColumnIndex("_op");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            favorite.info.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return favorite;
    }

    public void deleteFailedId(String pixivId) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(TABLE_NAME_FAILED, "_op = ?", new String[]{pixivId});
    }

    public void deleteFavorite(String pixivId) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(TABLE_NAME_FAVORITE, "_op = ?", new String[]{pixivId});
    }

    @Override
    public void close() {
        if (sqLiteOpenHelper != null) {
            sqLiteOpenHelper.close();
        }
    }
}
