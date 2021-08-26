package com.example.quanlylichthi.ui.trangchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.canbo.CanBoController;
import com.example.quanlylichthi.ui.login.LoginController;
import com.example.quanlylichthi.ui.monthi.MonThiController;
import com.example.quanlylichthi.ui.phongthi.PhongThiController;
import com.example.quanlylichthi.ui.taikhoan.TaiKhoanController;
import com.example.quanlylichthi.ui.taodotthi.DotThiController;

public class TrangChuController extends AppCompatActivity {
    Button btnMonThi,btnPhongThi,btnCanBo, btnTaiKhoan,btnDotThi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_controller);

        getWitget();

        monThi();
        phongThi();
        canBo();
        taiKhoan();
        dotThi();
    }
    private void getWitget(){
        btnMonThi=(Button)findViewById(R.id.btnMonThi_TrangChu);
        btnPhongThi=(Button)findViewById(R.id.btnPhongThi_TrangChu);
        btnCanBo=(Button)findViewById(R.id.btnCanBo_TrangChu);
        btnTaiKhoan=(Button)findViewById(R.id.btnTaiKhoan_TrangChu);
        btnDotThi=(Button)findViewById(R.id.btnTaoDotThi_TrangChu);
    }
    private void monThi(){
        btnMonThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), MonThiController.class);
                startActivity(intent);
            }
        });
    }
    private void phongThi(){
        btnPhongThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), PhongThiController.class);
                startActivity(intent);
            }
        });
    }
    private void canBo(){
        btnCanBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), CanBoController.class);
                startActivity(intent);
            }
        });
    }
    private void taiKhoan(){
        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), TaiKhoanController.class);
                startActivity(intent);
            }
        });
    }
    private void dotThi(){
        btnDotThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), DotThiController.class);
                startActivity(intent);
            }
        });
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
        Intent intent=new Intent(TrangChuController.this,LoginController.class);
        startActivity(intent);
    }
}