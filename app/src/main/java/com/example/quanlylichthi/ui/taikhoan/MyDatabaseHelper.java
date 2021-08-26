package com.example.quanlylichthi.ui.taikhoan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";
    private static final String DATABASE_NAME = "qluser";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_USERNAME = "bacdaotao";
    private static final String COLUMN_PASSWORD = "khoa";
    private static final String COLUMN_ADMIN = "hocky";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( " +
            COLUMN_USERNAME+" TEXT NOT NULL PRIMARY KEY," +
            COLUMN_PASSWORD+" TEXT NOT NULL," +
            COLUMN_ADMIN+" INTEGER NOT NULL" +
            ")";
    private static MyDatabaseHelper sInstance;
    public static MyDatabaseHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new MyDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public MyDatabaseHelper(Context context){
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
    public List<User> getAllUser(){
        SQLiteDatabase db = getReadableDatabase();
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                User user = new User();
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_ADMIN)));
                users.add(user);
            }while(cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return users;
    }

    public User getUser(String username){
        User user = new User();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_USERNAME+" = '"+username+"';";
//        Log.e("sql: ", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            user.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_ADMIN)));
            db.close();
            return user;
        }
        return null;
    }

    //Thêm dợt thi mới
    public boolean insertUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ADMIN, user.getAdmin());
        long rowEffect = db.insert(TABLE_NAME, null, values);
        db.close();
        if(rowEffect != -1){
            return true;
        }
        return false;
    }
    //Cập nhât đơt thi
//    public long updateDT(DotThi dt){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BAC, dt.getBacDaoTao());
//        values.put(COLUMN_KHOA, dt.getKhoa());
//        values.put(COLUMN_HOCKY, dt.getHocKy());
//        values.put(COLUMN_TENDT, dt.getTenDotThi());
//        values.put(COLUMN_BATDAU, dt.getNgayBatDau());
//        values.put(COLUMN_KETTHUC, dt.getNgayKetThuc());
//        long rowEffect = db.update(TABLE_NAME, values, COLUMN_ID+" = ?", new String[]{String.valueOf(dt.getId())});
//        db.close();
//        return rowEffect;
//    }
    //Xóa 1 đơt thi theo id
    public int deleteUser(User user){
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_NAME, COLUMN_USERNAME+" = ?", new String[]{user.getUsername()});
        db.close();
        return rowEffect;
    }
    //Xóa toàn bô tất cả các đợt thi
    public int deleteAllUser(){
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_NAME, null, null);
        db.close();
        return rowEffect;
    }
}
