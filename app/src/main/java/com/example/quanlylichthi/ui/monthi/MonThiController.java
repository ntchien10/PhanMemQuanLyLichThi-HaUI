package com.example.quanlylichthi.ui.monthi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlylichthi.R;

import java.util.ArrayList;
import java.util.List;

public class MonThiController extends AppCompatActivity {
    EditText edtMaHP,edtTenHP;
    Spinner spinnerBacDaoTao,spinnerSoTC,spinnerThoiLuong , spinnerKiHoc,spinnerKhoa;
    ListView lvMonThi;

    ArrayList<MonThiModel> arrayList=null;

    MyDatabaseHelper dbManager= new MyDatabaseHelper(this);
    private List<String> list_bacdaotao;
    private List<String> list_kihoc;
    private List<String> list_khoa;
    private List<String> list_soTc;
    private List<String> list_thoiLuong;

    ListViewMonThiAdapter lvMonThi_Adapter;

    int vtri=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_monthi);

        anhXa();

        arrayList=new ArrayList<>();

        lvMonThi_Adapter=new ListViewMonThiAdapter(arrayList);
        lvMonThi.setAdapter(lvMonThi_Adapter);

        setSpinnerBacDaoTao();
        setSpinnerKiHoc();
        setSpinnerKhoa();
        setSpinnerSoTC();
        setSpinnerThoiLuong();

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultMonThiIfNeed();
        List<MonThiModel> list=  db.getAllMonThi();
        this.arrayList.addAll(list);

        lvMonThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonThiModel monthi = (MonThiModel) lvMonThi_Adapter.getItem(position);

                for (int i=0;i<spinnerBacDaoTao.getAdapter().getCount();i++){
                    if (spinnerBacDaoTao.getAdapter().getItem(i).equals(arrayList.get(position).BacDaoTao)){
                        spinnerBacDaoTao.setSelection(i);
                    }
                }

                for (int i=0;i<spinnerKiHoc.getAdapter().getCount();i++){
                    if (spinnerKiHoc.getAdapter().getItem(i).equals(arrayList.get(position).KiHoc)){
                        spinnerKiHoc.setSelection(i);
                    }
                }

                for (int i=0;i<spinnerSoTC.getAdapter().getCount();i++){
                    if (spinnerSoTC.getAdapter().getItem(i).equals(arrayList.get(position).SoTC)){
                        spinnerSoTC.setSelection(i);
                    }
                }

                for (int i=0;i<spinnerThoiLuong.getAdapter().getCount();i++){
                    if (spinnerThoiLuong.getAdapter().getItem(i).equals(arrayList.get(position).ThoiLuong)){
                        spinnerThoiLuong.setSelection(i);
                    }
                }

                edtMaHP.setText(arrayList.get(position).MaHocPhan);
                edtTenHP.setText(arrayList.get(position).TenHocPhan);

                //Toast.makeText(MonThiController.this, monthi.MaHocPhan, Toast.LENGTH_LONG).show();
                vtri=position;
            }
        });

        lvMonThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(MonThiController.this)
                        .setTitle("Th??ng b??o")
                        .setMessage("B???n c?? ch???c ch???n mu???n x??a")
                        .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (arrayList.size() > 0) {
                                    MonThiModel monthi = (MonThiModel) lvMonThi_Adapter.getItem(position);
                                    arrayList.remove(position);
                                    dbManager.deleteMonThi(monthi);
                                    lvMonThi_Adapter.notifyDataSetChanged();
                                    Toast.makeText(MonThiController.this, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                                    dbManager.updateMonThi(monthi);
                                }
                                else {
                                    Toast.makeText(MonThiController.this, "X??a kh??ng th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                alertDialog.show();
                return false;
            }
        });

        //menu

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu_monthi, menu);
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
            case R.id.nav_viewAll:
                viewAll();
                break;
            case R.id.nav_dong:
                dong();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void anhXa(){
        edtMaHP=(EditText)findViewById(R.id.edt_maHP);
        edtTenHP=(EditText)findViewById(R.id.edt_tenHP);
        spinnerBacDaoTao=(Spinner) findViewById(R.id.spinner_bacDaoTao);
        spinnerKiHoc=(Spinner) findViewById(R.id.spinner_kihoc);
        spinnerSoTC=(Spinner) findViewById(R.id.spinner_soTC);
        spinnerThoiLuong=(Spinner) findViewById(R.id.spinner_thoiLuong);
        spinnerKhoa=(Spinner) findViewById(R.id.spinner_khoa);

//        btnThem=(Button)findViewById(R.id.btn_them);
//        btnSua=(Button)findViewById(R.id.btn_sua);
//        btnDong=(Button)findViewById(R.id.btn_dong);
//        btnViewAll=(Button)findViewById(R.id.btnViewAll);

        lvMonThi=(ListView)findViewById(R.id.lv_monthi);
    }
    public void setSpinnerBacDaoTao(){
        list_bacdaotao = new ArrayList<>();
        list_bacdaotao.add("Cao ?????ng");
        list_bacdaotao.add("?????i h???c");
        list_bacdaotao.add("Li??n th??ng ?????i h???c");
        list_bacdaotao.add("Th???c s??");

        spinnerBacDaoTao = (Spinner) findViewById(R.id.spinner_bacDaoTao);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list_bacdaotao);

        spinnerBacDaoTao.setAdapter(spinnerAdapter);

        //B???t s??? ki???n cho Spinner, khi ch???n ph???n t??? n??o th?? hi???n th??? l??n Toast
        spinnerBacDaoTao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //?????i s??? postion l?? v??? tr?? ph???n t??? trong list Data
                String msg = "position :" + position + " value :" + list_bacdaotao.get(position);
                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
               // Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSpinnerKiHoc(){
        list_kihoc = new ArrayList<>();
        list_kihoc.add("H???c k?? 1");
        list_kihoc.add("H???c k?? 2");
        list_kihoc.add("H???c k??? ph???");

        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list_kihoc);

        spinnerKiHoc.setAdapter(spinnerAdapter);

        //B???t s??? ki???n cho Spinner, khi ch???n ph???n t??? n??o th?? hi???n th??? l??n Toast
        spinnerKiHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //?????i s??? postion l?? v??? tr?? ph???n t??? trong list Data
                String msg = "position :" + position + " value :" + list_kihoc.get(position);
               // Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSpinnerSoTC(){
        list_soTc = new ArrayList<>();
        list_soTc.add("1 t??n");
        list_soTc.add("3 t??n");
        list_soTc.add("4 t??n");
        list_soTc.add("5 t??n");

        spinnerSoTC = (Spinner) findViewById(R.id.spinner_soTC);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list_soTc);

        spinnerSoTC.setAdapter(spinnerAdapter);

        //B???t s??? ki???n cho Spinner, khi ch???n ph???n t??? n??o th?? hi???n th??? l??n Toast
        spinnerSoTC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //?????i s??? postion l?? v??? tr?? ph???n t??? trong list Data
                String msg = "position :" + position + " value :" + list_soTc.get(position);
                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSpinnerKhoa(){
        list_khoa = new ArrayList<>();
        list_khoa.add("Kh??a 12");
        list_khoa.add("Kh??a 13");
        list_khoa.add("Kh??a 14");
        list_khoa.add("Kh??a 15");

        spinnerKhoa = (Spinner) findViewById(R.id.spinner_khoa);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list_khoa);

        spinnerKhoa.setAdapter(spinnerAdapter);

        //B???t s??? ki???n cho Spinner, khi ch???n ph???n t??? n??o th?? hi???n th??? l??n Toast
        spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //?????i s??? postion l?? v??? tr?? ph???n t??? trong list Data
                String msg = "position :" + position + " value :" + list_khoa.get(position);
                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
               // Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSpinnerThoiLuong(){
        list_thoiLuong = new ArrayList<>();
        list_thoiLuong.add("60 ph??t");
        list_thoiLuong.add("90 ph??t");
        list_thoiLuong.add("120 ph??t");
        list_thoiLuong.add("150 ph??t");

        spinnerThoiLuong = (Spinner) findViewById(R.id.spinner_thoiLuong);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list_thoiLuong);

        spinnerThoiLuong.setAdapter(spinnerAdapter);

        //B???t s??? ki???n cho Spinner, khi ch???n ph???n t??? n??o th?? hi???n th??? l??n Toast
        spinnerThoiLuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //?????i s??? postion l?? v??? tr?? ph???n t??? trong list Data
                String msg = "position :" + position + " value :" + list_thoiLuong.get(position);
//                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
//                Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void them(){
        MonThiModel monThi=new MonThiModel(
                spinnerBacDaoTao.getSelectedItem().toString(),
                spinnerKiHoc.getSelectedItem().toString(),
                spinnerKhoa.getSelectedItem().toString(),
                edtMaHP.getText().toString(),
                edtTenHP.getText().toString(),
                spinnerSoTC.getSelectedItem().toString(),
                spinnerThoiLuong.getSelectedItem().toString()
        );
        arrayList.add(monThi);
        dbManager.addMonThi(monThi);
        lvMonThi_Adapter.notifyDataSetChanged();
        Toast.makeText(MonThiController.this, "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();


    }
    private void sua(){
        MonThiModel monThi=new MonThiModel(
                spinnerBacDaoTao.getSelectedItem().toString(),
                spinnerKiHoc.getSelectedItem().toString(),
                spinnerKhoa.getSelectedItem().toString(),
                edtMaHP.getText().toString(),
                edtTenHP.getText().toString(),
                spinnerSoTC.getSelectedItem().toString(),
                spinnerThoiLuong.getSelectedItem().toString()
        );
        if(vtri<0){
            Toast.makeText(MonThiController.this, "ch??a ch???n m??n thi", Toast.LENGTH_SHORT).show();
        }
        else {
            arrayList.set(vtri,monThi);
            dbManager.updateMonThi(monThi);
            lvMonThi_Adapter.notifyDataSetChanged();
            Toast.makeText(MonThiController.this, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
        }
    }
    private void dong(){
        AlertDialog alertDialog = new AlertDialog.Builder(MonThiController.this)
                .setTitle("Th??ng b??o")
                .setMessage("B???n c?? ch???c ch???n mu???n ????ng")
                .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(MonThiController.this, "????ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MonThiController.this, "????ng kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.show();
    }
    private void viewAll(){
        Intent intent=new Intent(getBaseContext(),ViewMonThi.class);
        startActivity(intent);
    }
    private void deleteMonThi(MonThiModel monthi)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteMonThi(monthi);
        this.arrayList.remove(monthi);
        // Refresh ListView.
        this.lvMonThi_Adapter.notifyDataSetChanged();
    }
}