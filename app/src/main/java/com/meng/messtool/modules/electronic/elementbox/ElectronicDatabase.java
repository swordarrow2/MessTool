package com.meng.messtool.modules.electronic.elementbox;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.graphics.*;

import com.meng.messtool.system.debug.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.io.*;
import java.util.*;

import static com.meng.messtool.ApplicationHolder.*;

class ElectronicDatabase extends AbstractDatabaseHelper {

    /*
     *@author 清梦
     *@date 2024-07-01 15:54:50
     */
    public static final String TAG = "ElectronicDatabase";
    private final String DATABASE_NAME = "electronic";
    private final int DATABASE_VERSION = 2;
    private final String TABLE_DATA_MAIN = "data_main";
    private final String TABLE_USE_ELEMENT = "use_element";
    // id, name, type, describe, package, slot_id, shop_name, id_in_shop,  picture file

    private static ElectronicDatabase instance;

    private SQLiteOpenHelper sqLiteOpenHelper;

    public static ElectronicDatabase getInstance() {
        if (instance == null) {
            instance = new ElectronicDatabase();
        }
        return instance;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        if (sqLiteOpenHelper != null) {
            Debuger.addLog(TAG, "dbHelper has already init.");
        }
        sqLiteOpenHelper = new SQLiteOpenHelper(context, FileTool.getAppFile(FileSavePath.DATABASE, DATABASE_NAME, "db").getAbsolutePath(), null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table if not exists " + TABLE_DATA_MAIN + "( " +
                        "_id integer primary key autoincrement , " +
                        "_name varchar(255), " +
                        "_print varchar(255), " +
                        "_describe varchar(512)," +
                        "_package varchar(255)," +
                        "_slot_id varchar(255)," +
                        "_shop_name varchar(255)," +
                        "_id_in_shop varchar(255)," +
                        "_rest integer," +
                        "_picture mediumblob," +
                        "_brand varchar(255) " +
                        ");");
                db.execSQL("create table if not exists " + TABLE_USE_ELEMENT + "( " +
                        "_id integer primary key autoincrement, " +
                        "_element_id integer," +
                        "_count integer," +
                        "_way varchar(512)," +
                        "_time long )");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                switch (newVersion) {
                    case 2:
                        db.execSQL("alter table " + TABLE_DATA_MAIN + " add column _brand varchar(255)");
                }
            }
        };
    }

    public boolean addELement(Element element) {
        if (element._name == null || element._brand == null) {
            throw new IllegalArgumentException("name or brand can't be null");
        }
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s where _name = '%s' and _brand = '%s'", TABLE_DATA_MAIN, element._name, element._brand), null);
        if (cursor.getCount() > 0) {
            cursor.close();
            String msg = "已经添加过" + element.toString();
            showToast(msg);
            return false;
        }
        ContentValues cv = new ContentValues();

        cv.put("_name", element._name);
        cv.put("_print", element._print);
        cv.put("_describe", element._describe);
        cv.put("_package", element._package);
        cv.put("_slot_id", element._slot_id);
        cv.put("_shop_name", element._shop_name);
        cv.put("_id_in_shop", element._id_in_shop);
        cv.put("_rest", element._rest);
        cv.put("_brand", element._brand);
        if (element._picture != null && element._picture.length > 1_048_576) {
            showToast(String.format(Locale.CHINA, "picture too large %dB,compress.", element._picture.length));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeByteArray(element._picture, 0, element._picture.length);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            cv.put("_picture", baos.toByteArray());
        } else {
            cv.put("_picture", element._picture);
        }
        db.insert(TABLE_DATA_MAIN, null, cv);
        return true;
    }

    public long addUse(Use use) {
        ContentValues cv = new ContentValues();

        cv.put("_element_id", use._element_id);
        cv.put("_count", use._count);
        cv.put("_way", use._way);
        cv.put("_time", use._time);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.insert(TABLE_USE_ELEMENT, null, cv);
    }

    public int getElementCount() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_DATA_MAIN), null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public int getUseCount() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_USE_ELEMENT), null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public Element getElement(String element_name) {
        Element result = null;
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format(Locale.CHINA, "select * from %s where _name = '%s'", TABLE_DATA_MAIN, element_name), null);
        cursor.moveToFirst();
        result = createElement(cursor);
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<Element> getAllELement() {
        ArrayList<Element> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_DATA_MAIN), null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(createElement(cursor));
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<Use> getUse(int element_id) {
        ArrayList<Use> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format(Locale.CHINA, "select * from %s where _element_id = %d", TABLE_USE_ELEMENT, element_id), null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(createUse(cursor));
        }
        cursor.close();
        db.close();
        return result;
    }

    private Element createElement(Cursor cursor) {
        return new Element(
                cursor.getInt(0),//_id integer primary key autoincrement
                cursor.getString(1),//_name varchar(255)
                cursor.getString(2),//_print varchar(255)
                cursor.getString(3),//_describe varchar(512)
                cursor.getString(4),//_package varchar(255)
                cursor.getString(5),//_slot_id varchar(255)
                cursor.getString(6),//_shop_name varchar(255)
                cursor.getString(7),//_id_in_shop varchar(255)
                cursor.getInt(8),//_rest integer
                cursor.getBlob(9),//_picture_file mediumblob
                cursor.getString(10)//_brand varchar(255)
        );
    }

    private Use createUse(Cursor cursor) {
        return new Use(
                cursor.getInt(0),//_id integer primary key autoincrement
                cursor.getInt(1),//_element_id integer
                cursor.getInt(2),//_count integer
                cursor.getString(3),//_way vchar(512)
                cursor.getLong(4)//_time long
        );
    }
//    public Use getElementLastUse(int element_id) {
//        Use result = null;
//        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(String.format(Locale.CHINA, "select * from %s where _element_id = %d and _id= (SELECT MAX(_id) FROM %s)", TABLE_USE_ELEMENT, element_id, TABLE_USE_ELEMENT), null);
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            result = new Use(
//                    cursor.getInt(0),//_id integer primary key autoincrement
//                    cursor.getInt(1),//_element_id integer
//                    cursor.getInt(2),//_count integer
//                    cursor.getInt(3),//_rest integer
//                    cursor.getString(4),//_way vchar(512)
//                    cursor.getLong(5)//_time long
//            );
//        }
//        cursor.close();
//        db.close();
//        return result;
//    }

    public ArrayList<Use> getAllUse() {
        ArrayList<Use> result = new ArrayList<>();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_USE_ELEMENT), null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(createUse(cursor));
        }
        cursor.close();
        db.close();
        return result;
    }

    public long updateELementById(Element element) {
        if (element._name == null) {
            throw new IllegalArgumentException();
        }
        ContentValues cv = new ContentValues();

        cv.put("_name", element._name);
        cv.put("_print", element._print);
        cv.put("_describe", element._describe);
        cv.put("_package", element._package);
        cv.put("_slot_id", element._slot_id);
        cv.put("_shop_name", element._shop_name);
        cv.put("_id_in_shop", element._id_in_shop);
        cv.put("_rest", element._rest);
        cv.put("_brand", element._brand);


        if (element._picture != null && element._picture.length > 1_048_576) {
            showToast("picture too large " + element._picture.length + "B,compress.");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeByteArray(element._picture, 0, element._picture.length);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            cv.put("_picture", baos.toByteArray());
        } else {
            cv.put("_picture", element._picture);
        }
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.update(TABLE_DATA_MAIN, cv, " _id= " + element._id, null);
    }

//    public long updateUse(Use use) {
//        ContentValues cv = new ContentValues();

//        cv.put("_element_id", use._element_id);
//        cv.put("_count", use._count);
//        cv.put("_rest", use._rest);
//        cv.put("_time", use._time);
//        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
//        return db.update(TABLE_USE_ELEMENT, cv, " _id= " + use._id, null);
//    }

    //    public void deleteElement(int _id) {
//        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
//        db.delete(TABLE_DATA_MAIN, "_id= " + _id, null);
//    }
//
//    public void deleteUse(int _id) {
//        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
//        db.delete(TABLE_USE_ELEMENT, "_id= " + _id, null);
//    }
    @Override
    public void close() {
        if (sqLiteOpenHelper != null) {
            sqLiteOpenHelper.close();
        }
    }
}
