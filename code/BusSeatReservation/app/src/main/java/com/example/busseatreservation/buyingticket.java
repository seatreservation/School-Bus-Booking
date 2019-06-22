package com.example.busseatreservation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class buyingticket extends AppCompatActivity {
    private Calendar cal;
    private int year,month,day;
    private String begin,dest,name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyingticket);
        name=getIntent().getStringExtra("name");
        final Spinner spinner=(Spinner) findViewById(R.id.spinner);
        final Spinner spinner2=(Spinner) findViewById(R.id.spinner2);
        final List<String>categories=new ArrayList<String>();
        categories.add("东校区");
        categories.add("南校区");
        categories.add("北校区");
        ArrayAdapter<String>dataAdapter=new ArrayAdapter<String>(buyingticket.this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                begin=categories.get(position);
               }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                dest=categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });
        //日期选择(日期限制未设置)：
        getDate();
       final TextView tx4=(TextView)findViewById(R.id.textView4);
        tx4.setText(year+"-"+(++month)+"-"+day);
        final View b1=(View)findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tx4.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(buyingticket.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
    //取消按钮
        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
     //查询按钮
        Button b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //查询操作
                if(begin==dest)
                {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Toast.makeText(buyingticket.this, "起始站和终点站不应该相同"+sdf.format(date), Toast.LENGTH_SHORT).show();
                }else {
                    String way=begin+"——"+dest;
                    Intent t1=new Intent();
                    t1.setClass(buyingticket.this,buyingticket_1.class);
                    t1.putExtra("way",way);
                    t1.putExtra("date",tx4.getText());
                    t1.putExtra("name",name);
                    startActivity(t1);
                }

            }
        });
    }
    //获取日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
}
