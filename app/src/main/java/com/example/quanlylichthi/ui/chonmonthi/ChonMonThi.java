package com.example.quanlylichthi.ui.chonmonthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.monthi.ListViewMonThiAdapter;
import com.example.quanlylichthi.ui.monthi.MonThiModel;
import com.example.quanlylichthi.ui.monthi.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ChonMonThi extends AppCompatActivity{
    Button btnNext;

    ListView lv_viewMonThi;
    ArrayList<MonThiModel> monThiList;
    MyDatabaseHelper dbManager=new MyDatabaseHelper(this);
    ListViewMonThiAdapter lvMonThi_Adapter;

    Intent intent=null;
     static ArrayList<MonThiModel> listMonThi= new ArrayList<>();

    int mSlectedItem=0;
    static String NgayBD, NgayKT, Bac, Khoa, HocKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chon_mon_thi);

        getWitget();

        intent = this.getIntent();
        NgayBD = intent.getStringExtra("ngaybd");
        NgayKT = intent.getStringExtra("ngaykt");
        Bac = intent.getStringExtra("bacdt");
        Khoa= intent.getStringExtra("khoa");
        HocKi = intent.getStringExtra("hocki");

        monThiList=dbManager.getChonMonThi(Bac,HocKi,Khoa);
//        monThiList=dbManager.getAllMonThi();
        System.out.println(monThiList);
        lvMonThi_Adapter=new ListViewMonThiAdapter(monThiList);
        lv_viewMonThi.setAdapter(lvMonThi_Adapter);
        lvMonThi_Adapter.notifyDataSetChanged();

        next();
        lv_viewMonThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean co=false;
                for(int i=0;i<listMonThi.size();i++){
                    if (listMonThi.get(i).equals(monThiList.get(position))){
                        listMonThi.remove(i);
                        view.setBackgroundColor(Color.WHITE);
                        co=true;
                    }
                }
                if (co==false){
                    listMonThi.add(monThiList.get(position));
                    view.setBackgroundColor(Color.parseColor("#3399CC"));
                }
                System.out.println(position);
            }

        });

    }

    public void next(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMonThi.size()<=0){
                    Toast.makeText(ChonMonThi.this, "Chưa chọn môn thi", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent=new Intent(getBaseContext(),ChonPhongThi.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void getWitget(){
        lv_viewMonThi=(ListView) findViewById(R.id.lv_chonmonthi);
        btnNext=(Button)findViewById(R.id.btn_chonmonthi_next);
    }
}