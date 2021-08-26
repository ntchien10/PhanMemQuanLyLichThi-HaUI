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
import com.example.quanlylichthi.ui.canbo.CanBoModel;
import com.example.quanlylichthi.ui.canbo.ListViewCanBoAdapter;
import com.example.quanlylichthi.ui.monthi.MonThiModel;
import com.example.quanlylichthi.ui.phongthi.ListViewPhongThiAdapter;
import com.example.quanlylichthi.ui.canbo.MyDatabaseHelper;
import com.example.quanlylichthi.ui.phongthi.PhongThiThiModel;


import java.util.ArrayList;
import java.util.List;

import static com.example.quanlylichthi.ui.chonmonthi.ChonMonThi.listMonThi;
import static com.example.quanlylichthi.ui.chonmonthi.ChonPhongThi.listPhongThi;

public class ChonCanBoCoiThi extends AppCompatActivity {
    Button btnSapXep;

    ListView lv_viewChonCanBo;
    ArrayList<CanBoModel> canBoList;
    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);
    ListViewCanBoAdapter lvCanBo_Adapter;

    static List<CanBoModel> listCanBo=new ArrayList<>();

    Intent intent=getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chon_can_bo_coi_thi);

        getWitget();

        canBoList=dbManager.getAllCanBo();

        lvCanBo_Adapter=new ListViewCanBoAdapter(canBoList);
        lv_viewChonCanBo.setAdapter(lvCanBo_Adapter);
        lv_viewChonCanBo.deferNotifyDataSetChanged();

        lv_viewChonCanBo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean co=false;
                for(int i=0;i<listCanBo.size();i++){
                    if (listCanBo.get(i).equals(canBoList.get(position))){
                        listCanBo.remove(i);
//                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                        view.setBackgroundColor(Color.WHITE);
//                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                        co=true;
                    }
                }
                if (co==false){
                    listCanBo.add(canBoList.get(position));
                    view.setBackgroundColor(Color.parseColor("#3399CC"));
//                    lv_viewMonThi.getChildAt(position).setBackgroundColor(Color.parseColor("#FF3300"));
                }
//                lv_viewMonThi.getChildAt(position).setBackgroundColor(Color.parseColor("#3399CC"));
                System.out.println(position);

            }
        });

        sapXep();
    }
    public void getWitget(){
        lv_viewChonCanBo=(ListView) findViewById(R.id.lv_choncanbo);
        btnSapXep=(Button)findViewById(R.id.btn_choncanbo_next);
    }
    public void sapXep(){
        btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMonThi.size()<listMonThi.size()){
                    Toast.makeText(ChonCanBoCoiThi.this, "Chưa đủ giáo viên coi thi", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent=new Intent(getBaseContext(),SapXep.class);
                    startActivity(intent);
                }
            }
        });
    }
}