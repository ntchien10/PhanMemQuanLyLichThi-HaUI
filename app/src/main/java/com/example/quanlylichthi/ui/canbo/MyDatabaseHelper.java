package com.example.quanlylichthi.ui.canbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QLCanBo";
    private static final String TABLE_CANBO = "CanBo";
    private static final String COLUMN_CANBO_MACB ="CanBo_MaCB";
    private static final String COLUMN_CANBO_TENCB = "CanBo_TenCB";
    private static final String COLUMN_CANBO_KHOA ="CanBo_Khoa";
    private static final String COLUMN_CANBO_SDT ="CanBo_SDT";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + TABLE_CANBO + "("
                + COLUMN_CANBO_MACB + " TEXT PRIMARY KEY,"
                + COLUMN_CANBO_TENCB + " TEXT,"
                + COLUMN_CANBO_KHOA + " TEXT,"
                + COLUMN_CANBO_SDT + " INT" + ")";
        // Execute script.
        db.execSQL(SQLQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANBO);
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultMonThiIfNeed()  {
        int count = this.getCanBoCount();
        if(count ==0 ) {
            CanBoModel cb1 = new CanBoModel("CB01","Cán bộ 1","CNTT",5345345);
            CanBoModel cb2 = new CanBoModel("CB02","Cán bộ 2","QTKD",134534);
            this.addCanBo(cb1);
            this.addCanBo(cb2);
        }
    }

    public void addCanBo(CanBoModel canbo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANBO_MACB, canbo.getMaCanBo());
        values.put(COLUMN_CANBO_TENCB, canbo.getTenCanBo());
        values.put(COLUMN_CANBO_KHOA, canbo.getKhoa());
        values.put(COLUMN_CANBO_SDT, canbo.getSDT());

        // Inserting Row
        db.insert(TABLE_CANBO, null, values);

        // Closing database connection
        db.close();
    }


    public CanBoModel getCanBo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CANBO, new String[] {
                        COLUMN_CANBO_MACB, COLUMN_CANBO_TENCB ,
                        COLUMN_CANBO_KHOA,COLUMN_CANBO_SDT}, COLUMN_CANBO_MACB + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        CanBoModel canbo = new CanBoModel(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        // return note
        return canbo;
    }


    public ArrayList<CanBoModel> getAllCanBo() {

        ArrayList<CanBoModel> canboList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CANBO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CanBoModel canbo = new CanBoModel();
                canbo.setMaCanBo(cursor.getString(0));
                canbo.setTenCanBo(cursor.getString(1));
                canbo.setKhoa(cursor.getString(2));
                canbo.setSDT(cursor.getInt(3));
                // Adding note to list
                canboList.add(canbo);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return canboList;
    }

    public int getCanBoCount() {

        String countQuery = "SELECT  * FROM " + TABLE_CANBO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateCanBo(CanBoModel canbo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANBO_TENCB, canbo.getTenCanBo());
        values.put(COLUMN_CANBO_KHOA, canbo.getKhoa());
        values.put(COLUMN_CANBO_SDT, canbo.getSDT());

        // updating row
        return db.update(TABLE_CANBO, values, COLUMN_CANBO_MACB + " = ?",
                new String[]{String.valueOf(canbo.getMaCanBo())});
    }

    public void deleteMonThi(CanBoModel canbo) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CANBO, COLUMN_CANBO_MACB + " = ?",
                new String[] { String.valueOf(canbo.getMaCanBo()) });
        db.close();
    }

}

