package com.example.quanlylichthi.ui.phongthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.quanlylichthi.ui.monthi.MonThiModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhongThiController extends AppCompatActivity {

    ImageView btnTime1,btnTime2;
    ListView lvPhongThi;
    TextView time1,time2;
    Spinner spinnerTenPhong,spinnerSucChua;

    final String[] Phong = {"101A8","102A8","103A9", "104A9","201A10","202A10","203A10", "204A9",
                                "301A8","302A8","303A10", "304A10","401C1","402C1","403C1", "404A8",};
    final  String[] So = {"20","25","30","35","40"};

    ArrayList<PhongThiThiModel> arrayList=null;
    List<PhongThiThiModel> list=null;

    ListViewPhongThiAdapter listViewPhongThiAdapter;
    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);

    int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongthi);

        getWidget();
        setSpinner();
        dbManager.createDefaultMonThiIfNeed();
        arrayList=new ArrayList<>();

        listViewPhongThiAdapter=new ListViewPhongThiAdapter(arrayList);
        lvPhongThi.setAdapter(listViewPhongThiAdapter);

        list=  dbManager.getAllPhongThi();
        arrayList.addAll(list);

        btnTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        time1.setText(day + "/" + month  + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(PhongThiController.this, dateSetListener, 2021, 8,1 );
                datePickerDialog.show();
            }
        });
        btnTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        time2.setText(day + "/" + month  + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(PhongThiController.this, dateSetListener, 2021, 8,1 );
                datePickerDialog.show();
            }
        });

        lvPhongThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhongThiThiModel phongthi = (PhongThiThiModel) listViewPhongThiAdapter.getItem(position);

                time1.setText(arrayList.get(position).NgayBatDau);
                time2.setText(arrayList.get(position).NgayKetThuc);

                for (int i=0;i<spinnerTenPhong.getAdapter().getCount();i++){
                    if (spinnerTenPhong.getAdapter().getItem(i).equals(arrayList.get(position).TenPhong)){
                        spinnerTenPhong.setSelection(i);
                    }
                }

                for (int i=0;i<spinnerSucChua.getAdapter().getCount();i++){
                    if (spinnerSucChua.getAdapter().getItem(i).equals(arrayList.get(position).SucChua)){
                        spinnerSucChua.setSelection(i);
                    }
                }

//                Toast.makeText(PhongThiController.this,phongthi.TenPhong, Toast.LENGTH_LONG).show();
                vitri=position;
            }

        });

        lvPhongThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(PhongThiController.this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn thoát ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (arrayList.size() > 0) {
                                    PhongThiThiModel phongthi = (PhongThiThiModel) listViewPhongThiAdapter.getItem(position);
                                    arrayList.remove(position);
                                    dbManager.deletePhongThi(phongthi);
                                    listViewPhongThiAdapter.notifyDataSetChanged();
                                    Toast.makeText(PhongThiController.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(PhongThiController.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                alertDialog.show();
                return false;
            }
        });

    }
    public void getWidget(){
        lvPhongThi = findViewById(R.id.lv_PhongThi);
        spinnerTenPhong = findViewById(R.id.spinnerTenPhong);
        spinnerSucChua = findViewById(R.id.spinnerSucChua);
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        btnTime1 = findViewById(R.id.btntime1);
        btnTime2 = findViewById(R.id.btntime2);
    }
    private void setSpinner(){
        ArrayAdapter<String> adapterPhong = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Phong);
        spinnerTenPhong.setAdapter(adapterPhong);
        ArrayAdapter<String> adapterSucChua = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, So);
        spinnerSucChua.setAdapter(adapterSucChua);
    }
    private void deletePhongThi(PhongThiThiModel phongthi)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deletePhongThi(phongthi);
        this.arrayList.remove(phongthi);
        // Refresh ListView.
        this.listViewPhongThiAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu_phongthi, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_them:
                them();
                break;
            case R.id.nav_sua:
                sua();
                break;
            case R.id.nav_dong:
                thoat();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void thoat(){
        AlertDialog alertDialog = new AlertDialog.Builder(PhongThiController.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn thoát ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PhongThiController.this, "Thoát không thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
    private void sua(){
        PhongThiThiModel phongThi=new PhongThiThiModel(
                time1.getText().toString(),
                time2.getText().toString(),
                spinnerTenPhong.getSelectedItem().toString(),
                spinnerSucChua.getSelectedItem().toString()
        );
        arrayList.set(vitri,phongThi);
        dbManager.updatePhongThi(phongThi);
        listViewPhongThiAdapter.notifyDataSetChanged();
        Toast.makeText(PhongThiController.this, "sửa thành công", Toast.LENGTH_SHORT).show();

    }
    private void them(){
        PhongThiThiModel phongThi=new PhongThiThiModel(
                time1.getText().toString(),
                time2.getText().toString(),
                spinnerTenPhong.getSelectedItem().toString(),
                spinnerSucChua.getSelectedItem().toString()
        );
        arrayList.add(phongThi);
        dbManager.addPhongThi(phongThi);
        listViewPhongThiAdapter.notifyDataSetChanged();
        Toast.makeText(PhongThiController.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }
}

