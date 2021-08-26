package com.example.quanlylichthi.ui.trangchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.chonmonthi.LichThiModel;
import com.example.quanlylichthi.ui.chonmonthi.ListViewLichThiAdapter;
import com.example.quanlylichthi.ui.chonmonthi.MyDateBaseHelper;
import com.example.quanlylichthi.ui.login.LoginController;
import com.example.quanlylichthi.ui.monthi.ListViewMonThiAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrangChuForUser extends AppCompatActivity {
    ListViewLichThiAdapter lvLichThi_Adapter;
    ArrayList<LichThiModel> list=new ArrayList<>();
    MyDateBaseHelper db=new MyDateBaseHelper(this);
    ListView lv_LichThi_User;
    Button btnSearch;
    AutoCompleteTextView txtSearch;
    private List<String> monthi =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu_user);

        getWitget();

        list =db.getAllLichThi();
        lvLichThi_Adapter=new ListViewLichThiAdapter(list);
        lv_LichThi_User.setAdapter(lvLichThi_Adapter);

        monthi=db.getMonThi();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,monthi);
        txtSearch.setAdapter(adapter);
        txtSearch.setThreshold(1);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSearch.getText().toString().equals("")){
                    list =db.getAllLichThi();
                }
                else{
                    list =db.searchLichThi(txtSearch.getText().toString());
                }
                lvLichThi_Adapter=new ListViewLichThiAdapter(list);
                lv_LichThi_User.setAdapter(lvLichThi_Adapter);
            }
        });
    }
    public void getWitget(){
        lv_LichThi_User=(ListView)findViewById(R.id.lv_lichthi_user);
        btnSearch=(Button)findViewById(R.id.btn_timkiem_user);
        txtSearch=(AutoCompleteTextView)findViewById(R.id.edt_timkiem_user);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_trangchu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_trangchuadmin_thoat:
                dangxuat();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void dangxuat(){
        Intent intent=new Intent(TrangChuForUser.this, LoginController.class);
        startActivity(intent);
    }
}