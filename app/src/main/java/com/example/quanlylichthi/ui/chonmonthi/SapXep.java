package com.example.quanlylichthi.ui.chonmonthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.canbo.CanBoModel;
import com.example.quanlylichthi.ui.monthi.ListViewMonThiAdapter;
import com.example.quanlylichthi.ui.monthi.MonThiController;
import com.example.quanlylichthi.ui.monthi.MonThiModel;
import com.example.quanlylichthi.ui.phongthi.PhongThiThiModel;
import com.example.quanlylichthi.ui.trangchu.TrangChuController;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.quanlylichthi.ui.chonmonthi.ChonCanBoCoiThi.listCanBo;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.Bac;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.HocKi;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.Khoa;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.NgayBD;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.NgayKT;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.listMonThi;
import static com.example.quanlylichthi.ui.chonmonthi.ChonPhongThi.listPhongThi;

public class SapXep extends AppCompatActivity {
    ArrayList<LichThiModel>  list=new ArrayList<>();
    ListViewLichThiAdapter lv_LichThiAdapter;
    ListView lvLichThi;

    MyDateBaseHelper db=new MyDateBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sap_xep);

        lvLichThi=(ListView)findViewById(R.id.lv_sapxep);

        lv_LichThiAdapter=new ListViewLichThiAdapter(sapXep());
        lvLichThi.setAdapter(lv_LichThiAdapter);
    }
    public ArrayList<LichThiModel> sapXep() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = dateFormat.parse(NgayBD);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int day= date.getDate();
        int month= date.getMonth()+1;
        int year=date.getYear()+1900;
        int thang=month;
        int maxDay=0;

        if(thang==1||thang==3||thang==5||thang==7||thang==8||thang==10||thang==12){
            maxDay=31;
        }
        else if(thang==4||thang==6||thang==9||thang==11){
            maxDay=30;
        }
        else if(thang==2){
            maxDay=28;
        }

        System.out.println(day+month+year);
        System.out.println(date);
        int count=0;
        String CaThi="";
        int phong=0;
        int canbo=0;
        for (int i=0;i<listMonThi.size();i++){
            if (day>maxDay){
                day=1;
                thang++;
                month++;
            }
            if (thang>12){
                year++;
                thang=1;
                day=1;
            }
            if (canbo==listCanBo.size()){
                canbo=0;
            }
            if(count%2==0){
                CaThi="7h15";
                LichThiModel lichthi=new LichThiModel(
                        Bac,
                        Khoa,
                        HocKi,
                        listMonThi.get(i).getMaHocPhan(),
                        listMonThi.get(i).getTenHocPhan().toString(),
                        listPhongThi.get(phong).getTenPhong().toString(),
                        CaThi,
                        day+"/"+month+"/"+year,
                        listCanBo.get(canbo).getTenCanBo().toString(),
                        listCanBo.get(canbo+1).getTenCanBo().toString());
                list.add(lichthi);
                db.addLichThi(lichthi);
                canbo++;
            }
            else {
                CaThi="14h15";
                LichThiModel lichthi=new LichThiModel(
                        Bac,
                        Khoa,
                        HocKi,
                        listMonThi.get(i).getMaHocPhan(),
                        listMonThi.get(i).getTenHocPhan().toString(),
                        listPhongThi.get(phong).getTenPhong().toString(),
                        CaThi,
                        day+"/"+month+"/"+year,
                        listCanBo.get(canbo).getTenCanBo().toString(),
                        listCanBo.get(canbo+1).getTenCanBo().toString());
                list.add(lichthi);
                db.addLichThi(lichthi);
                phong++;
                canbo++;
                day++;
            }
            canbo++;
            count++;
        }
        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_sapxep, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_sapxep_thoat:
                thoat();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void thoat(){
        AlertDialog alertDialog = new AlertDialog.Builder(SapXep.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn đóng")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(SapXep.this, TrangChuController.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Đóng không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
}