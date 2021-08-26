package com.example.quanlylichthi.ui.taikhoan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlylichthi.R;

public class TaiKhoanController extends AppCompatActivity {
    Button registerBtn;
    MyDatabaseHelper db;
    CheckBox chkAdmin;
    EditText usernameTxt, passwordTxt, confirmPassTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        getWidget();
        db = MyDatabaseHelper.getInstance(TaiKhoanController.this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameTxt.getText()+"" == ""){
                    Toast.makeText(TaiKhoanController.this, "Bạn chưa nhập tên tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(passwordTxt.getText()+"" == ""){
                    Toast.makeText(TaiKhoanController.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(passwordTxt.getText().length()<6){
                    Toast.makeText(TaiKhoanController.this, "Mật khẩu phải dài hơn 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(confirmPassTxt.getText()+"" == ""){
                    Toast.makeText(TaiKhoanController.this, "Bạn chưa xác nhận mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    //Kiểm tra xem mật khẩu và xác nhận mật khẩu đã đúngg hay chưa
                    if(String.valueOf(passwordTxt.getText()).equals(confirmPassTxt.getText()+"")){
                        User user;
                        user = db.getUser(usernameTxt.getText()+"");
                        //Kiểm tra nếu tìm thấy user thì thông báo username bị trùng
                        if(user != null){
                            Toast.makeText(TaiKhoanController.this, "Username đã được sử dụng. Vui lòng chọn username khác.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            user = new User();
                            user.setUsername(usernameTxt.getText()+"");
                            Log.e("Error", usernameTxt.getText()+"");
                            user.setPassword(passwordTxt.getText()+"");
                            if(chkAdmin.isChecked()){
                                //do quy dịnh admin là 1 và user là 0 nên ta truyền trực tiếp 0, 1 luôn
                                user.setAdmin(1);
                            }else{
                                user.setAdmin(0);
                            }
                            db.insertUser(user);
                            Toast.makeText(TaiKhoanController.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(TaiKhoanController.this, "Mật khẩu và xác nhận mật khẩu phải trùng nhau", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }

    public void getWidget(){
        usernameTxt = findViewById(R.id.username);
        passwordTxt = findViewById(R.id.password);
        chkAdmin = findViewById(R.id.chkType);
        registerBtn = findViewById(R.id.btnRegister);
        confirmPassTxt = findViewById(R.id.confirmPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.mn_listUser:
                openListUser(); break;
            case R.id.mn_exit:
                exitApp(); break;
        }
    }

    public void openListUser(){
        Intent myIntent = new Intent(TaiKhoanController.this, DSUserActivity.class);
        startActivity(myIntent);
    }

    public void exitApp(){
        this.finish();
    }

}