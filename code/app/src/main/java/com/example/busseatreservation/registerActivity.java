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
import com.avos.avoscloud.SignUpCallback;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String EP="^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";//邮箱格式
    private static final String EPP="[^a-zA-Z0-9#_~!$&'()*+,=;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pa=Pattern.compile(EP);
    Button btn_cancel,btn_RE;
    EditText edit_rename,edit_repwd,edit_reemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_cancel=findViewById(R.id.cancel);
        btn_RE=findViewById(R.id.Re);
        edit_reemail=findViewById(R.id.reemail);
        edit_rename=findViewById(R.id.rename);
        edit_repwd=findViewById(R.id.repwd);
        btn_RE.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.cancel:
                Intent intent=new Intent(this,loginActivity.class);
                startActivity(intent);
                break;
            case R.id.Re:
                String n=edit_rename.getText().toString();
                String em=edit_reemail.getText().toString();
                String p=edit_repwd.getText().toString();
                if(n.length()==0) {
                Toast.makeText(registerActivity.this,"用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(p.length()==0) {
                Toast.makeText(registerActivity.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(!eaddformat(em)){
                Toast.makeText(registerActivity.this,"邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
                else {
                    AVUser user = new AVUser();
                    user.setUsername(edit_rename.getText().toString());
                    user.setEmail(edit_reemail.getText().toString());
                    user.setPassword(edit_repwd.getText().toString());
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                startActivity(new Intent(registerActivity.this, loginActivity.class));
                                registerActivity.this.finish();
                                Toast.makeText(registerActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(registerActivity.this, "账号或邮箱已经存在，请重新输入", Toast.LENGTH_LONG).show();
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
