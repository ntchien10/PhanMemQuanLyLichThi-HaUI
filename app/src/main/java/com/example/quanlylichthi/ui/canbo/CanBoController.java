package com.example.quanlylichthi.ui.canbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlylichthi.R;

import java.util.ArrayList;
import java.util.List;

public class CanBoController extends AppCompatActivity {
    Spinner spnKhoa;
    EditText editten, editsdt;
    AutoCompleteTextView editma;
    Button btnThem, btnSua, btnDong,btnViewAll;
    ListView lv_canbo;
    List<String> lstKhoa;

    private String[] countries = {"CB01","CB02","CB03", "CB04","CB05"};

    ArrayList<CanBoModel> arrayList=null;

    ListViewCanBoAdapter listViewCanBoAdapter;
    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);

    int vitri=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlcanbo);

        anhXa();
        arrayList=new ArrayList<>();

        ArrayAdapter adapterCB = new ArrayAdapter(this,android.R.layout.simple_list_item_1,countries);
        editma.setAdapter(adapterCB);
        editma.setThreshold(1);

        listViewCanBoAdapter=new ListViewCanBoAdapter(arrayList);
        lv_canbo.setAdapter(listViewCanBoAdapter);

        setSpnKhoa();

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultMonThiIfNeed();
        List<CanBoModel> list=  db.getAllCanBo();
        this.arrayList.addAll(list);


        lv_canbo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CanBoModel canbo=(CanBoModel) listViewCanBoAdapter.getItem(position);

                editma.setText(arrayList.get(position).MaCanBo);
                editten.setText(arrayList.get(position).TenCanBo);
                for (int i=0;i<spnKhoa.getAdapter().getCount();i++){
                    if (spnKhoa.getAdapter().getItem(i).equals(arrayList.get(position).Khoa)){
                        spnKhoa.setSelection(i);
                    }
                }
                editsdt.setText(String.valueOf(arrayList.get(position).SDT));

                vitri=position;
            }
        });

        lv_canbo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(CanBoController.this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn xóa")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (arrayList.size() > 0) {
                                    CanBoModel canbo=(CanBoModel) listViewCanBoAdapter.getItem(position);

                                    arrayList.remove(position);
                                    dbManager.deleteMonThi(canbo);
                                    listViewCanBoAdapter.notifyDataSetChanged();
                                    Toast.makeText(CanBoController.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(CanBoController.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
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
    private void anhXa(){
        editma=(AutoCompleteTextView) findViewById(R.id.editMa);
        editten=(EditText)findViewById(R.id.editTen);
        editsdt=(EditText)findViewById(R.id.editSdt);

//        btnThem=(Button)findViewById(R.id.btnThem);
//        btnSua=(Button)findViewById(R.id.btnSua);
//        btnDong=(Button)findViewById(R.id.btnDong);
//        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        lv_canbo=(ListView)findViewById(R.id.lvDanhSach);
    }
    public void setSpnKhoa(){
        lstKhoa = new ArrayList<>();
        lstKhoa.add("CNTT");
        lstKhoa.add("QTKD");
        lstKhoa.add("Ô tô");
        lstKhoa.add("Cơ khí");

        spnKhoa = (Spinner) findViewById(R.id.spnKhoa);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, lstKhoa);

        spnKhoa.setAdapter(spinnerAdapter);

        //Bắt sự kiện cho Spinner, khi chọn phần tử nào thì hiển thị lên Toast
        spnKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //đối số postion là vị trí phần tử trong list Data
                String msg = "position :" + position + " value :" + lstKhoa.get(position);
//                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
//                Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteCanBo(CanBoModel canbo)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteMonThi(canbo);
        this.arrayList.remove(canbo);
        // Refresh ListView.
        this.listViewCanBoAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_item_canbo, menu);
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
                dong();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void them(){
        CanBoModel canbo=new CanBoModel(
                editma.getText().toString(),
                editten.getText().toString(),
                spnKhoa.getSelectedItem().toString(),
                Integer.parseInt(editsdt.getText().toString())
        );
        arrayList.add(canbo);
        dbManager.addCanBo(canbo);
        listViewCanBoAdapter.notifyDataSetChanged();
        Toast.makeText(CanBoController.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }
    private void sua(){
        CanBoModel canbo=new CanBoModel(
                editma.getText().toString(),
                editten.getText().toString(),
                spnKhoa.getSelectedItem().toString(),
                Integer.parseInt(editsdt.getText().toString())
        );
        if (vitri<0){
            Toast.makeText(CanBoController.this, "Chưa chọn cán bộ", Toast.LENGTH_SHORT).show();
        }
        else {
            arrayList.set(vitri,canbo);
            dbManager.updateCanBo(canbo);
            listViewCanBoAdapter.notifyDataSetChanged();
            Toast.makeText(CanBoController.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void dong(){
        AlertDialog alertDialog = new AlertDialog.Builder(CanBoController.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc chắn muốn đóng")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();

        alertDialog.show();
    }
}