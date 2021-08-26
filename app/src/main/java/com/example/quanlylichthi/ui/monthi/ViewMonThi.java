package com.example.quanlylichthi.ui.monthi;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlylichthi.R;

import java.util.ArrayList;

public class ViewMonThi extends AppCompatActivity {
    ListView lv_viewMonThi;
    ArrayList<MonThiModel> monThiList;
    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);
    ListViewMonThiAdapter lvPhongThi_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mon_thi);

        lv_viewMonThi=(ListView) findViewById(R.id.lv_viewmonthi);
        monThiList=dbManager.getAllMonThi();

        lvPhongThi_Adapter=new ListViewMonThiAdapter(monThiList);
        lv_viewMonThi.setAdapter(lvPhongThi_Adapter);
        lv_viewMonThi.deferNotifyDataSetChanged();
    }

}