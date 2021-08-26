package com.example.quanlylichthi.ui.taodotthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlylichthi.R;
import com.example.quanlylichthi.ui.chonmonthi.ChonMonThi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DotThiController extends AppCompatActivity {

    ImageView  btnDate1, btnDate2;
    ListView lvDotThi;
    TextView txtshow;
    EditText txtNgayBatDau, txtNgayKetThuc;
    final String[] bacDaoTao = {"Đại học", "Cao đẳng","Thạc si"};
    final String[] khoa = {"Khóa 11", "Khóa 12", "Khóa 13", "Khóa 14", "Khóa 15"};
    final String[] hocKi = {"Học kì 1", "Học kì 2", "Học kì phụ"};
    //ArrayList<DotThi> listDotThi;
    Spinner spinnerBac;
    Spinner spinnerKhoa;
    Spinner spinnerHocKi;
    ArrayAdapter<DotThi> adapterDotThi = null;
    int vitri = -1;
    int idFirstItem;
    List<DotThi> dsDT = new ArrayList<DotThi>();
    MyDatabaseHelper db = null;

    String str1= "";
    String str2= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotthi);

        db =  MyDatabaseHelper.getInstance(this);
        getWidget();

        ArrayAdapter<String> adapterBac = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bacDaoTao);
        spinnerBac.setAdapter(adapterBac);
        ArrayAdapter<String> adapterKhoa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, khoa);
        spinnerKhoa.setAdapter(adapterKhoa);
        ArrayAdapter<String> adapterHocKi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hocKi);
        spinnerHocKi.setAdapter(adapterHocKi);

        //listDotThi = new ArrayList<DotThi>();
        //adapterDotThi = new ArrayAdapter<DotThi>(this, android.R.layout.simple_list_item_1, listDotThi);
        lvDotThi.setAdapter(adapterDotThi);

        lvDotThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DotThiController.this);
                builder.setTitle("Question?");
                builder.setMessage("Bạn có muốn xóa đợt thi này?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //listDotThi.remove(position);
                        //adapterDotThi.notifyDataSetChanged()
                        DotThi dt = (DotThi) adapterDotThi.getItem(position);
                        dt.setId(dt.getId());
                        db.deleteDT(dt);
                        Toast.makeText(DotThiController.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        //Load lại danh sách sau khi xóa đợt thi
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
        //Xử lý sự kiện nhấn vào 1 item trên listView => dữ liệu trong listview sẽ được tải lên các ô text
        lvDotThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DotThi dt = db.getDT(position+idFirstItem);//listDotThi.get(position);
                //Log.e("dot thi : ", dt.toString());
                int a = adapterBac.getPosition(dt.getBacDaoTao());
                int b = adapterKhoa.getPosition(dt.getKhoa());
                int c = adapterHocKi.getPosition(dt.getHocKy());
                spinnerBac.setSelection(a);
                spinnerKhoa.setSelection(b);
                spinnerHocKi.setSelection(c);
                txtNgayBatDau.setText(dt.getNgayBatDau()+"");
                txtNgayKetThuc.setText(dt.getNgayKetThuc()+"");
                vitri = position+idFirstItem;

            }
        });
        //Sự kiên click vào nút ... của ô ngày bắt đầu => hiện lên bảng chọn ngày
        btnDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        txtNgayBatDau.setText(day + "/" + month  + "/" + year);
                        str1= day + "/" + month  + "/" + year;
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(DotThiController.this, dateSetListener, 2021, 1,1 );
                datePickerDialog.show();
            }
        });
        btnDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        txtNgayKetThuc.setText(day + "/" + month  + "/" + year);
                        str2= day + "/" + month  + "/" + year;
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(DotThiController.this, dateSetListener, 2021, 1,1);
                datePickerDialog.show();

            }
        });

//        fakeData();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu_dotthi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void getWidget(){
        lvDotThi = findViewById(R.id.listViewDotThi);
        txtshow = findViewById(R.id.textShow);
        spinnerBac = findViewById(R.id.spnBac);
        spinnerKhoa = findViewById(R.id.spnKhoa);
        txtNgayBatDau = findViewById(R.id.txtNgayBatDau);
        txtNgayKetThuc = findViewById(R.id.txtNgayKetThuc);
        spinnerHocKi = findViewById(R.id.spnHocKi);
        btnDate1 = (ImageView)findViewById(R.id.btnDate1);
        btnDate2 = (ImageView)findViewById(R.id.btnDate2);
    }

    public void loadData(){
        try{
            dsDT = db.getAllDT();
            idFirstItem = dsDT.get(0).getId();
            adapterDotThi = new ArrayAdapter<DotThi>(DotThiController.this, android.R.layout.simple_list_item_1, dsDT);
            lvDotThi.setAdapter(adapterDotThi);
        }catch (Exception e){
            Log.e("Có lỗi: ",e.toString());
        }
    }
    //Hàm chèn VD đơt thi vào
    public void fakeData(){
        db.insertDT(new DotThi("Đại học", "Khóa 13", "Học kì phụ", "07/12/2020", "23/12/2020"));
        db.insertDT(new DotThi("Cao đẳng", "Khóa 12", "Học kì 2", "05/06/2020", "23/06/2020"));
        db.insertDT(new DotThi("Đại học", "Khóa 14", "Học kì 1", "07/08/2019", "14/08/2019"));
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.mn_them:
                addItem(); break;
            case R.id.mn_sua:
                updateItem(); break;
            case R.id.mn_chitiet:
                chitiet(); break;
            case R.id.mn_dong:
                exitApp(); break;
        }
    }

    public void addItem(){
        DotThi dt = new DotThi();
        dt.setBacDaoTao(spinnerBac.getSelectedItem() + "");
        dt.setKhoa(spinnerKhoa.getSelectedItem() + "");
        dt.setHocKy(spinnerHocKi.getSelectedItem() + "");
        dt.setNgayBatDau(txtNgayBatDau.getText() + "");
        dt.setNgayKetThuc(txtNgayKetThuc.getText() + "");
        //listDotThi.add(dt);
        db.insertDT(dt);
        loadData();
    }

    public void updateItem(){
        DotThi dt = new DotThi();
        dt.setId(vitri);
        dt.setBacDaoTao(spinnerBac.getSelectedItem()+"");
        dt.setKhoa(spinnerKhoa.getSelectedItem()+"");
        dt.setHocKy(spinnerHocKi.getSelectedItem()+"");
        dt.setNgayBatDau(txtNgayBatDau.getText()+"");
        dt.setNgayKetThuc(txtNgayKetThuc.getText()+"");
        long check = db.updateDT(dt);
        if(check > 0){
            Toast.makeText(DotThiController.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
            loadData();
        }else{
            Toast.makeText(DotThiController.this, "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
        }
    }
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DotThiController.this);
        builder.setTitle("Question?");
        builder.setMessage("Bạn có muốn thoát?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DotThiController.this.finish();
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
    }
    public void chitiet(){
        String ngaybd = txtNgayBatDau.getText().toString();
        String ngaykt =txtNgayKetThuc.getText().toString();
        String bacdt = spinnerBac.getSelectedItem().toString();
        String khoa = spinnerKhoa.getSelectedItem().toString();
        String hocki = spinnerHocKi.getSelectedItem().toString();

        Intent intent=new Intent(getBaseContext(), ChonMonThi.class);
        intent.putExtra("ngaybd",ngaybd);
        intent.putExtra("ngaykt",ngaykt);
        intent.putExtra("bacdt",bacdt);
        intent.putExtra("khoa",khoa);
        intent.putExtra("hocki",hocki);
        startActivity(intent);
    }
}
