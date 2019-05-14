package com.example.busseatreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_login,btn_register,btn_forget;
    EditText edit_name,edit_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login=(Button)findViewById(R.id.login);
        btn_register=(Button)findViewById(R.id.register);
        btn_forget=(Button)findViewById(R.id.forget);
        edit_name=(EditText)findViewById(R.id.name);
        edit_pwd=(EditText)findViewById(R.id.password);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                AVUser.logInInBackground(edit_name.getText().toString(), edit_pwd.getText().toString(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if(e==null){
                            String name=edit_name.getText().toString();
                            Intent i1 = new Intent();
                            i1.putExtra("name", name);
                            i1.setClass(loginActivity.this, MainActivity.class);
                            startActivity(i1);
                            finish();
                        }
                        else{
                            Toast.makeText(loginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.register:
                startActivity(new Intent(loginActivity.this,registerActivity.class));

                break;
            case R.id.forget:
                break;
        }
    }
}