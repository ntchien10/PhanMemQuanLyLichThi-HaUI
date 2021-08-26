package com.example.quanlylichthi.ui.taikhoan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlylichthi.R;

import java.util.ArrayList;
import java.util.List;
import com.example.quanlylichthi.ui.taikhoan.MyDatabaseHelper;


public class DSUserActivity extends AppCompatActivity {
    ListView lvUser;
    ArrayAdapter<User> adapter = null;
    List<User> listUser = null;
    MyDatabaseHelper db;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_user);
        lvUser = findViewById(R.id.lvUser);
        db = MyDatabaseHelper.getInstance(this);
        listUser = new ArrayList<User>();
        loadData();
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DSUserActivity.this);
                builder.setTitle("Question?");
                builder.setMessage("Bạn có muốn xóa tài khoản này?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = (User) adapter.getItem(position);
                        db.deleteUser(user);
                        Toast.makeText(DSUserActivity.this, "Đã xóa tài khoản", Toast.LENGTH_LONG).show();
                        //Load lại danh sách sau khi xóa
                        loadData();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });
    }
    public void loadData(){
        try {
            listUser = db.getAllUser();
            adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, listUser);
            lvUser.setAdapter(adapter);
        }
        catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.mn_register:
                openRegister(); break;
            case R.id.mn_exit:
                exitApp(); break;
        }
    }

    public void openRegister(){
        Intent myInttent = new Intent(DSUserActivity.this, TaiKhoanController.class);
        startActivity(myInttent);
    }


    public void exitApp(){
        this.finish();
    }
}
