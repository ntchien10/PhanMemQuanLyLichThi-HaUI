package com.example.quanlylichthi.ui.chonmonthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.monthi.MonThiModel;
import com.example.quanlylichthi.ui.phongthi.ListViewPhongThiAdapter;
import com.example.quanlylichthi.ui.phongthi.MyDatabaseHelper;
import com.example.quanlylichthi.ui.phongthi.PhongThiThiModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.NgayBD;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.NgayKT;
import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.listMonThi;

public class ChonPhongThi extends AppCompatActivity {
    Button btnNext;

    ListView lv_viewPhongThi;
    ArrayList<PhongThiThiModel> phongThiList;
    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);
    ListViewPhongThiAdapter lvPhongThi_Adapter;

    Intent intent=getIntent();
    static List<PhongThiThiModel> listPhongThi=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chon_phong_thi);

        getWitget();

        phongThiList=dbManager.getAllPhongThi();

        lvPhongThi_Adapter=new ListViewPhongThiAdapter(phongThiList);
        lv_viewPhongThi.setAdapter(lvPhongThi_Adapter);
        lv_viewPhongThi.deferNotifyDataSetChanged();

        next();

        lv_viewPhongThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean co=false;
                for(int i=0;i<listPhongThi.size();i++){
                    if (listPhongThi.get(i).equals(phongThiList.get(position))){
                        listPhongThi.remove(i);
//                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                        view.setBackgroundColor(Color.WHITE);
//                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                        co=true;
                    }
                }
                if (co==false){
                    listPhongThi.add(phongThiList.get(position));
                    view.setBackgroundColor(Color.parseColor("#3399CC"));
//                    lv_viewMonThi.getChildAt(position).setBackgroundColor(Color.parseColor("#FF3300"));
                }
//                lv_viewMonThi.getChildAt(position).setBackgroundColor(Color.parseColor("#3399CC"));
                System.out.println(position);

            }
        });
    }
    public void next(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMonThi.size()/listPhongThi.size()<=2){
                    Intent intent=new Intent(getBaseContext(),ChonCanBoCoiThi.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ChonPhongThi.this, "Chưa đủ phòng thi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void getWitget(){
        lv_viewPhongThi=(ListView) findViewById(R.id.lv_chonphongthi);
        btnNext=(Button)findViewById(R.id.btn_chonphongthi_next);
    }
}