package com.example.quanlylichthi.ui.taodotthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlylichthi.ui.monthi.MonThiModel;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";
    private static final String DATABASE_NAME = "laplichthi";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dotthi";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BAC = "bacdaotao";
    private static final String COLUMN_KHOA = "khoa";
    private static final String COLUMN_HOCKY = "hocky";
    private static final String COLUMN_BATDAU = "ngaybd";
    private static final String COLUMN_KETTHUC = "ngaykt";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( " +
            COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            COLUMN_BAC+" TEXT NOT NULL," +
            COLUMN_KHOA+" TEXT NOT NULL," +
            COLUMN_HOCKY+" TEXT NOT NULL," +
            COLUMN_BATDAU+" TEXT NOT NULL," +
            COLUMN_KETTHUC+" TEXT NOT NULL" +
            ")";
    private static MyDatabaseHelper sInstance;
    public static MyDatabaseHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new MyDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }
    private MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e(TAG, "onCreate ");
        try{
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //Lấy toàn bô danh sách các đơt thi
    public List<DotThi> getAllDT(){
        SQLiteDatabase db = getReadableDatabase();
        List<DotThi> dts = new ArrayList<DotThi>();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                DotThi dt = new DotThi();
                dt.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                dt.setBacDaoTao(cursor.getString(cursor.getColumnIndex(COLUMN_BAC)));
                dt.setKhoa(cursor.getString(cursor.getColumnIndex(COLUMN_KHOA)));
                dt.setHocKy(cursor.getString(cursor.getColumnIndex(COLUMN_HOCKY)));
                dt.setNgayBatDau(cursor.getString(cursor.getColumnIndex(COLUMN_BATDAU)));
                dt.setNgayKetThuc(cursor.getString(cursor.getColumnIndex(COLUMN_KETTHUC)));
                dts.add(dt);
                Log.e("sql: ", dt.getId()+"");
            }while(cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return dts;
    }

    public DotThi getDT(int id){
        DotThi dt = new DotThi();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = "+String.valueOf(id);
//        Log.e("sql: ", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            dt.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            dt.setBacDaoTao(cursor.getString(cursor.getColumnIndex(COLUMN_BAC)));
            dt.setKhoa(cursor.getString(cursor.getColumnIndex(COLUMN_KHOA)));
            dt.setHocKy(cursor.getString(cursor.getColumnIndex(COLUMN_HOCKY)));
            dt.setNgayBatDau(cursor.getString(cursor.getColumnIndex(COLUMN_BATDAU)));
            dt.setNgayKetThuc(cursor.getString(cursor.getColumnIndex(COLUMN_KETTHUC)));
        }
        db.close();
        return dt;
    }

    //Thêm dợt thi mới
    public boolean insertDT(DotThi dt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BAC, dt.getBacDaoTao());
        values.put(COLUMN_KHOA, dt.getKhoa());
        values.put(COLUMN_HOCKY, dt.getHocKy());
        values.put(COLUMN_BATDAU, dt.getNgayBatDau());
        values.put(COLUMN_KETTHUC, dt.getNgayKetThuc());
        long rowEffect = db.insert(TABLE_NAME, null, values);
        db.close();
        if(rowEffect != -1){
            return true;
        }
        return false;
    }
    //Cập nhât đơt thi
    public long updateDT(DotThi dt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BAC, dt.getBacDaoTao());
        values.put(COLUMN_KHOA, dt.getKhoa());
        values.put(COLUMN_HOCKY, dt.getHocKy());
        values.put(COLUMN_BATDAU, dt.getNgayBatDau());
        values.put(COLUMN_KETTHUC, dt.getNgayKetThuc());
        long rowEffect = db.update(TABLE_NAME, values, COLUMN_ID+" = ?", new String[]{String.valueOf(dt.getId())});
        db.close();
        return rowEffect;
    }
    //Xóa 1 đơt thi theo id
    public int deleteDT(DotThi dt){
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{String.valueOf(dt.getId())});
        db.close();
        return rowEffect;
    }
    //Xóa toàn bô tất cả các đợt thi
    public int deleteAllDT(){
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_NAME, null, null);
        db.close();
        return rowEffect;
    }

}
