package com.example.quanlylichthi.ui.phongthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QLPhongThi";
    private static final String TABLE_PHONGTHI = "QLPhongThi";
    private static final String COLUMN_PHONGTHI_NGAYBATDAU ="MonThi_Ngaybd";
    private static final String COLUMN_PHONGTHI_NGAYKETTHUC ="MonThi_Ngaykt";
    private static final String COLUMN_PHONGTHI_TENPHONG ="MonThi_TenPhong";
    private static final String COLUMN_PHONGTHI_SUCCHUA ="MonThi_SucChua";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + TABLE_PHONGTHI + "("
                + COLUMN_PHONGTHI_NGAYBATDAU + " TEXT,"
                + COLUMN_PHONGTHI_NGAYKETTHUC + " TEXT,"
                + COLUMN_PHONGTHI_TENPHONG + " TEXT PRIMARY KEY,"
                + COLUMN_PHONGTHI_SUCCHUA + " TEXT" + ")";
        // Execute script.
        db.execSQL(SQLQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONGTHI);
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultMonThiIfNeed()  {
        int count = this.getPhongThiCount();
        if(count ==0 ) {
            PhongThiThiModel p1 = new PhongThiThiModel("20/10/2020","30/10/2020","101A8","35");
            PhongThiThiModel p2 = new PhongThiThiModel("20/8/2020","10/10/2020","104A9","40");
            this.addPhongThi(p1);
            this.addPhongThi(p2);
        }
    }

    public void addPhongThi(PhongThiThiModel phongthi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONGTHI_NGAYBATDAU, phongthi.getNgayBatDau());
        values.put(COLUMN_PHONGTHI_NGAYKETTHUC, phongthi.getNgayKetThuc());
        values.put(COLUMN_PHONGTHI_TENPHONG, phongthi.getTenPhong());
        values.put(COLUMN_PHONGTHI_SUCCHUA, phongthi.getSucChua());

        // Inserting Row
        db.insert(TABLE_PHONGTHI, null, values);

        // Closing database connection
        db.close();
    }

    public PhongThiThiModel getPhongThi(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PHONGTHI, new String[] {
                        COLUMN_PHONGTHI_NGAYBATDAU,COLUMN_PHONGTHI_NGAYKETTHUC,
                        COLUMN_PHONGTHI_SUCCHUA}, COLUMN_PHONGTHI_TENPHONG + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PhongThiThiModel phongthi = new PhongThiThiModel(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        // return note
        return phongthi;
    }

    public ArrayList<PhongThiThiModel> getChonPhongThi(String NgayBD, String NgayKT) {

        ArrayList<PhongThiThiModel> phonthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHONGTHI +" WHERE " +COLUMN_PHONGTHI_NGAYBATDAU +" LIKE '%"+NgayBD+"%' AND "+COLUMN_PHONGTHI_NGAYKETTHUC +" LIKE '%"+NgayKT+"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PhongThiThiModel phongthi = new PhongThiThiModel();
                phongthi.setNgayBatDau(cursor.getString(0));
                phongthi.setNgayKetThuc(cursor.getString(1));
                phongthi.setTenPhong(cursor.getString(2));
                phongthi.setSucChua(cursor.getString(3));
                // Adding note to list
                phonthiList.add(phongthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return phonthiList;
    }

    public ArrayList<PhongThiThiModel> getAllPhongThi() {

        ArrayList<PhongThiThiModel> phonthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHONGTHI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PhongThiThiModel phongthi = new PhongThiThiModel();
                phongthi.setNgayBatDau(cursor.getString(0));
                phongthi.setNgayKetThuc(cursor.getString(1));
                phongthi.setTenPhong(cursor.getString(2));
                phongthi.setSucChua(cursor.getString(3));
                // Adding note to list
                phonthiList.add(phongthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return phonthiList;
    }

    public int getPhongThiCount() {

        String countQuery = "SELECT  * FROM " + TABLE_PHONGTHI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updatePhongThi(PhongThiThiModel phongthi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONGTHI_NGAYBATDAU, phongthi.getNgayBatDau());
        values.put(COLUMN_PHONGTHI_NGAYKETTHUC, phongthi.getNgayKetThuc());
        values.put(COLUMN_PHONGTHI_SUCCHUA, phongthi.getSucChua());

        // updating row
        return db.update(TABLE_PHONGTHI, values, COLUMN_PHONGTHI_TENPHONG + " = ?",
                new String[]{String.valueOf(phongthi.getTenPhong())});
    }

    public void deletePhongThi(PhongThiThiModel phongthi) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHONGTHI, COLUMN_PHONGTHI_TENPHONG + " = ?",
                new String[] { String.valueOf(phongthi.getTenPhong()) });
        db.close();
    }
}

