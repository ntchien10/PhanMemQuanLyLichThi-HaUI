package com.example.quanlylichthi.ui.chonmonthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDateBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SapXepLichThi";
    private static final String TABLE_LICHTHI = "LichThi";
    private static final String COLUMN_SAPXEP_BACDAOTAO = "BacDaoTao";
    private static final String COLUMN_SAPXEP_KHOA ="Khoa";
    private static final String COLUMN_SAPXEP_HOCKY = "HocKy";
    private static final String COLUMN_SAPXEP_MAHP ="MaHP";
    private static final String COLUMN_SAPXEP_TENHP ="TenHP";
    private static final String COLUMN_SAPXEP_PHONGTHI ="PhongThi";
    private static final String COLUMN_SAPXEP_CATHI ="CaThi";
    private static final String COLUMN_SAPXEP_NGAYTHI ="NgayThi";
    private static final String COLUMN_SAPXEP_CANBO1 ="CanBo1";
    private static final String COLUMN_SAPXEP_CANBO2 ="CanBo2";

    public MyDateBaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + TABLE_LICHTHI + "("
                + COLUMN_SAPXEP_BACDAOTAO + " TEXT,"
                + COLUMN_SAPXEP_KHOA + " TEXT,"
                + COLUMN_SAPXEP_HOCKY + " TEXT,"
                + COLUMN_SAPXEP_MAHP + " TEXT PRIMARY KEY,"
                + COLUMN_SAPXEP_TENHP + " TEXT,"
                + COLUMN_SAPXEP_PHONGTHI + " TEXT,"
                + COLUMN_SAPXEP_CATHI + " TEXT,"
                + COLUMN_SAPXEP_NGAYTHI + " TEXT,"
                + COLUMN_SAPXEP_CANBO1 + " TEXT,"
                + COLUMN_SAPXEP_CANBO2 + " TEXT" + ")";
        // Execute script.
        db.execSQL(SQLQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LICHTHI);
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
//    public void createDefaultMonThiIfNeed()  {
//        int count = this.getCanBoCount();
//        if(count ==0 ) {
//            LichThiModel lt1 = new LichThiModel("CB01","Cán bộ 1","CNTT",5345345);
//            this.addLichThi(lt1);
//        }
//    }

    public void addLichThi(LichThiModel lichthi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SAPXEP_BACDAOTAO, lichthi.getBacDaoTao());
        values.put(COLUMN_SAPXEP_KHOA, lichthi.getKhoa());
        values.put(COLUMN_SAPXEP_HOCKY, lichthi.getHocKy());
        values.put(COLUMN_SAPXEP_MAHP, lichthi.getMaHP());
        values.put(COLUMN_SAPXEP_TENHP, lichthi.getTenHP());
        values.put(COLUMN_SAPXEP_PHONGTHI, lichthi.getPhongThi());
        values.put(COLUMN_SAPXEP_CATHI, lichthi.getCaThi());
        values.put(COLUMN_SAPXEP_NGAYTHI, lichthi.getNgayThi());
        values.put(COLUMN_SAPXEP_CANBO1, lichthi.getCB1());
        values.put(COLUMN_SAPXEP_CANBO2, lichthi.getCB2());

        // Inserting Row
        db.insert(TABLE_LICHTHI, null, values);

        // Closing database connection
        db.close();
    }

    public LichThiModel getLichThi(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LICHTHI, new String[] {COLUMN_SAPXEP_BACDAOTAO,
                        COLUMN_SAPXEP_KHOA, COLUMN_SAPXEP_HOCKY ,COLUMN_SAPXEP_MAHP,
                        COLUMN_SAPXEP_TENHP,COLUMN_SAPXEP_PHONGTHI,COLUMN_SAPXEP_CATHI,
                        COLUMN_SAPXEP_NGAYTHI,COLUMN_SAPXEP_CANBO1,COLUMN_SAPXEP_CANBO2}, COLUMN_SAPXEP_MAHP + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        LichThiModel lichthi = new LichThiModel(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9));
        // return note
        return lichthi;
    }

    public List<String> getMonThi() {

        List<String> lichthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT "+COLUMN_SAPXEP_TENHP+" FROM " + TABLE_LICHTHI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding note to list
                lichthiList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return lichthiList;
    }

    public ArrayList<LichThiModel> getAllLichThi() {

        ArrayList<LichThiModel> lichthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LICHTHI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LichThiModel lichthi = new LichThiModel();
                lichthi.setBacDaoTao(cursor.getString(0));
                lichthi.setKhoa(cursor.getString(1));
                lichthi.setHocKy(cursor.getString(2));
                lichthi.setMaHP(cursor.getString(3));
                lichthi.setTenHP(cursor.getString(4));
                lichthi.setPhongThi(cursor.getString(5));
                lichthi.setCaThi(cursor.getString(6));
                lichthi.setNgayThi(cursor.getString(7));
                lichthi.setCB1(cursor.getString(8));
                lichthi.setCB2(cursor.getString(9));
                // Adding note to list
                lichthiList.add(lichthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return lichthiList;
    }

    public ArrayList<LichThiModel> searchLichThi(String TenHP) {

        ArrayList<LichThiModel> lichthiList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LICHTHI +" WHERE "+COLUMN_SAPXEP_TENHP+ " LIKE '%"+TenHP+"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LichThiModel lichthi = new LichThiModel();
                lichthi.setBacDaoTao(cursor.getString(0));
                lichthi.setKhoa(cursor.getString(1));
                lichthi.setHocKy(cursor.getString(2));
                lichthi.setMaHP(cursor.getString(3));
                lichthi.setTenHP(cursor.getString(4));
                lichthi.setPhongThi(cursor.getString(5));
                lichthi.setCaThi(cursor.getString(6));
                lichthi.setNgayThi(cursor.getString(7));
                lichthi.setCB1(cursor.getString(8));
                lichthi.setCB2(cursor.getString(9));
                // Adding note to list
                lichthiList.add(lichthi);
            } while (cursor.moveToNext());
        }
        db.close();
        // return monthi list
        return lichthiList;
    }

    public int getCanBoCount() {

        String countQuery = "SELECT  * FROM " + TABLE_LICHTHI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateCanBo(LichThiModel lichthi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SAPXEP_BACDAOTAO, lichthi.getBacDaoTao());
        values.put(COLUMN_SAPXEP_KHOA, lichthi.getKhoa());
        values.put(COLUMN_SAPXEP_HOCKY, lichthi.getHocKy());
        values.put(COLUMN_SAPXEP_TENHP, lichthi.getTenHP());
        values.put(COLUMN_SAPXEP_PHONGTHI, lichthi.getPhongThi());
        values.put(COLUMN_SAPXEP_CATHI, lichthi.getCaThi());
        values.put(COLUMN_SAPXEP_NGAYTHI, lichthi.getNgayThi());
        values.put(COLUMN_SAPXEP_CANBO1, lichthi.getCB1());
        values.put(COLUMN_SAPXEP_CANBO2, lichthi.getCB2());

        // updating row
        return db.update(TABLE_LICHTHI, values, COLUMN_SAPXEP_MAHP + " = ?",
                new String[]{String.valueOf(lichthi.getMaHP())});
    }

    public void deleteMonThi(LichThiModel lichthi) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LICHTHI, COLUMN_SAPXEP_MAHP + " = ?",
                new String[] { String.valueOf(lichthi.getMaHP()) });
        db.close();
    }

}
