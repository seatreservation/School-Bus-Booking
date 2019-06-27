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
import com.avos.avoscloud.RequestPasswordResetCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String EP="^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";//邮箱格式
    private static final String EPP="[^a-zA-Z0-9#_~!$&'()*+,=;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pa=Pattern.compile(EP);
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
                String eadd=edit_name.getText().toString();
                if(!eaddformat(eadd))
                {
                    Toast.makeText(loginActivity.this,"请输入正确邮箱格式",Toast.LENGTH_LONG).show();
                }
                else{
                    AVUser.requestPasswordResetInBackground(eadd, new RequestPasswordResetCallback() {
                        public void done(AVException e) {
                            if (e == null) {
                                Toast.makeText(loginActivity.this," 已发送邮件到您的邮箱",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(loginActivity.this," 重置密码出错,请检查邮箱地址",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                break;
        }
    }
    //邮箱格式
    private boolean eaddformat(String s){
        Matcher matcher=pa.matcher(s);
        return matcher.matches();
    }
}