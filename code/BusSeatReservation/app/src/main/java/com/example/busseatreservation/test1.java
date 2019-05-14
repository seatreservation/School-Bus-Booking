package com.example.busseatreservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.avos.avoscloud.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test1 extends AppCompatActivity {
    private String name,email,pwd;
    private static final String EP="^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";//邮箱格式
    private static final String EPP="[^a-zA-Z0-9#_~!$&'()*+,=;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pa=Pattern.compile(EP);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        final EditText ed1=(EditText)findViewById(R.id.editText4);
        ed1.setKeyListener(null);
        final EditText ed2=(EditText)findViewById(R.id.editText5);
        final EditText ed3=(EditText)findViewById(R.id.editText);
        final AVUser currentUser = AVUser.getCurrentUser();
        ed1.setText(currentUser.getUsername());
        ed2.setText(currentUser.getString("password"));
        ed3.setText(currentUser.getString("email"));
        //
        Button b1=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // object 就是符合条件的第一个 AVObject
                        String s=ed3.getText().toString();
                        String m=ed2.getText().toString();
                        if(youxiang(s)&&m.length()!=0) {
                            currentUser.put("password", ed2.getText());
                            currentUser.put("email", ed3.getText());
                            currentUser.saveInBackground();
                            Toast.makeText(test1.this, "保存成功" , Toast.LENGTH_SHORT).show();

                        }
                        else if(m.length()==0) {
                            Toast.makeText(test1.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(test1.this,"邮箱格式不正确", Toast.LENGTH_SHORT).show();
                        }
            }
        });


    }
    //邮箱格式
    private boolean youxiang(String s){
        Matcher matcher=pa.matcher(s);
        return matcher.matches();
    }

}
