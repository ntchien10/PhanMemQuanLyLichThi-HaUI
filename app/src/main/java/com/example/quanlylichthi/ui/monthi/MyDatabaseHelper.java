package com.example.quanlylichthi.ui.monthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QLLichThi";

    private static final String TABLE_MONTHI = "QLMonThi";
    private static final String COLUMN_MONTHI_BAC ="MonThi_BacDaoTao";
    private static final String COLUMN_MONTHI_KIHOC ="MonThi_KiHoc";
    private static final String COLUMN_MONTHI_KHOA ="MonThi_Khoa";
    private static final String COLUMN_MONTHI_MAHOCPHAN = "MonThi_MaHP";
    private static final String COLUMN_MONTHI_TENHOCPHAN ="MonThi_TenHP";
    private static final String COLUMN_MONTHI_SOTC ="MonThi_SoTC";
    private static final String COLUMN_MONTHI_THOILUONG ="MonThi_ThoiLuong";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + TABLE_MONTHI + "("
                + COLUMN_MONTHI_BAC + " TEXT,"
                + COLUMN_MONTHI_KIHOC + " TEXT,"
                + COLUMN_MONTHI_KHOA + " TEXT,"
                + COLUMN_MONTHI_MAHOCPHAN + " TEXT PRIMARY KEY,"
                + COLUMN_MONTHI_TENHOCPHAN + " TEXT,"
                + COLUMN_MONTHI_SOTC + " TEXT,"
                + COLUMN_MONTHI_THOILUONG + " TEXT" + ")";
        // Execute script.
        db.execSQL(SQLQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHI);
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultMonThiIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            MonThiModel mon1 = new MonThiModel("Đại học","Học kì 1","K13","HP01","Học phần 1","2 tín","60 phút");
            MonThiModel mon2 = new MonThiModel("Đại học","Học kì 2","K13","HP02","Học phần 2","2 tín","60 phút");
            MonThiModel mon3 = new MonThiModel("Đại học","Học kì phụ","K13","HP03","Học phần 3","2 tín","60 phút");

            this.addMonThi(mon1);
            this.addMonThi(mon2);
            this.addMonThi(mon3);
        }
    }


    public void addMonThi(MonThiModel monthi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONTHI_BAC, monthi.getBacDaoTao());
        values.put(COLUMN_MONTHI_KIHOC, monthi.getKiHoc());
        values.put(COLUMN_MONTHI_KHOA, monthi.getKhoa());
        values.put(COLUMN_MONTHI_MAHOCPHAN, monthi.getMaHocPhan());
        values.put(COLUMN_MONTHI_TENHOCPHAN, monthi.getTenHocPhan());
        values.put(COLUMN_MONTHI_SOTC, monthi.getSoTC());
        values.put(COLUMN_MONTHI_THOILUONG, monthi.getThoiLuong());

        // Inserting Row
        db.insert(TABLE_MONTHI, null, values);

        // Closing database connection
        db.close();
    }


    public MonThiModel getMonThi(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MONTHI, new String[] {
                        COLUMN_MONTHI_BAC, COLUMN_MONTHI_KIHOC,COLUMN_MONTHI_KHOA,
                        COLUMN_MONTHI_MAHOCPHAN , COLUMN_MONTHI_TENHOCPHAN,
                        COLUMN_MONTHI_SOTC, COLUMN_MONTHI_THOILUONG}, COLUMN_MONTHI_MAHOCPHAN + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MonThiModel monthi = new MonThiModel(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));
        // return note
        return monthi;
    }

    public ArrayList<MonThiModel> getChonMonThi(String BacDaoTao, String HocKi,String Khoa) {

        ArrayList<MonThiModel> monthiList = new ArrayList<>();
        // Select All Query
        //String selectQuery = " SELECT * FROM " + TABLE_MONTHI;
        String selectQuery = "SELECT * FROM " + TABLE_MONTHI + " WHERE " +COLUMN_MONTHI_BAC +" LIKE '%"+BacDaoTao+"%' AND "+ COLUMN_MONTHI_KIHOC +" LIKE '%"+ HocKi + "%' AND " +COLUMN_MONTHI_KHOA +" LIKE '%"+Khoa+"%'";
        System.out.println(selectQuery);
        System.out.println(getNotesCount());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MonThiModel monthi = new MonThiModel();
                monthi.setBacDaoTao(cursor.getString(0));
                monthi.setKiHoc(cursor.getString(1));
                monthi.setKhoa(cursor.getString(2));
                monthi.setMaHocPhan(cursor.getString(3));
                monthi.setTenHocPhan(cursor.getString(4));
                monthi.setSoTC(cursor.getString(5));
                monthi.setThoiLuong(cursor.getString(6));
                // Adding note to list
                monthiList.add(monthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return monthiList;
    }

    public ArrayList<MonThiModel> getAllMonThi() {

        ArrayList<MonThiModel> monthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MONTHI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MonThiModel monthi = new MonThiModel();
                monthi.setBacDaoTao(cursor.getString(0));
                monthi.setKiHoc(cursor.getString(1));
                monthi.setKhoa(cursor.getString(2));
                monthi.setMaHocPhan(cursor.getString(3));
                monthi.setTenHocPhan(cursor.getString(4));
                monthi.setSoTC(cursor.getString(5));
                monthi.setThoiLuong(cursor.getString(6));
                // Adding note to list
                monthiList.add(monthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return monthiList;
    }

    public int getNotesCount() {

        String countQuery = "SELECT  * FROM " + TABLE_MONTHI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateMonThi(MonThiModel monthi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MONTHI_BAC, monthi.getBacDaoTao());
        values.put(COLUMN_MONTHI_KIHOC, monthi.getKiHoc());
        values.put(COLUMN_MONTHI_KHOA, monthi.getKhoa());
        values.put(COLUMN_MONTHI_TENHOCPHAN, monthi.getTenHocPhan());
        values.put(COLUMN_MONTHI_SOTC, monthi.getSoTC());
        values.put(COLUMN_MONTHI_THOILUONG, monthi.getThoiLuong());

        // updating row
        return db.update(TABLE_MONTHI, values, COLUMN_MONTHI_MAHOCPHAN + " = ?",
                new String[]{String.valueOf(monthi.getMaHocPhan())});
    }

    public void deleteMonThi(MonThiModel monthi) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MONTHI, COLUMN_MONTHI_MAHOCPHAN + " = ?",
                new String[] { String.valueOf(monthi.getMaHocPhan()) });
        db.close();
    }

}

