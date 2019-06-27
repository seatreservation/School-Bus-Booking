package com.example.busseatreservation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.avos.avoscloud.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class buyingticket_1_1 extends AppCompatActivity {
    private String name,way;
    private String time;
    private String lc,ps,zw;
    private ListView lv;
    int co=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyingticket_1_1);
        way=getIntent().getStringExtra("way");
        time=getIntent().getStringExtra("time");
        lc=getIntent().getStringExtra("lc");
        name=getIntent().getStringExtra("name");
        final TextView t1 = findViewById(R.id.textView20);
        t1.setText(way);
        TextView t2 = findViewById(R.id.textView21);
        t2.setText(time);
        //spinner
        final Spinner spinner4= findViewById(R.id.spinner4);
        final Spinner spinner5= findViewById(R.id.spinner5);
        final List<String> categories=new ArrayList<String>();
        final List<String> categories2=new ArrayList<String>();
        AVQuery<AVObject> query = new AVQuery<>("info");
        query.whereEqualTo("lc",lc);
        query.whereEqualTo("way",way);
        query.whereEqualTo("time",time);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<AVObject>() {
            final ArrayList<HashMap<String, Object>> listItem = new ArrayList <HashMap<String,Object>>();/*在数组中存放数据*/
            @Override

            public void done(List<AVObject> list, AVException e) {
                for(AVObject account :list){

                    ps=account.getString("ps");
                    zw=account.getString("zw");
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("ItemText5", "排数："+ps);
                    map.put("ItemText6", "座位："+zw);
                    listItem.add(map);
                    lv = (ListView) findViewById(R.id.lv2);
                    //new String  数据来源， new int 数据到哪去
                    SimpleAdapter mSimpleAdapter = new SimpleAdapter(buyingticket_1_1.this,listItem,R.layout.sample_adapter_itemm,
                            new String[] {"ItemText5","ItemText6"},
                            new int[] {R.id.ItemText5,R.id.ItemText6});
                    lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器
                }

            }

        });


        for(int i=1;i<=10;i++){
            categories.add(i+"排");
        }
        categories2.add("A座");
        categories2.add("B座");
        categories2.add("C座");
        categories2.add("D座");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(buyingticket_1_1.this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(dataAdapter);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                ps=categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });
        ArrayAdapter<String> dataAdapter2=new ArrayAdapter<String>(buyingticket_1_1.this,android.R.layout.simple_spinner_item,categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(dataAdapter2);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                zw=categories2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });

        //确认按钮
        final View b1=(View)findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(buyingticket_1_1.this).setTitle("请确认信息")//设置对话框标题

                        .setMessage("您购买"+lc+"选择的车次是"+way+"  " +
                                "出发时间："+time+"  "+
                                "排数："+ps+"  " +
                                "座位号："+zw)//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                //保存数据
                                collide();
                                if(co==1)
                                {
                                    Toast.makeText(buyingticket_1_1.this, "座位已被预定", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    save();
                                    Toast.makeText(buyingticket_1_1.this, "购票成功", Toast.LENGTH_SHORT).show();

                                }

                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                    }

                }).show();//在按键响应事件中显示此对话框
            }
        });

    }
    void save(){
        AVObject inf=new AVObject("info");
        inf.put("name",name);
        inf.put("lc",lc);
        inf.put("way",way);
        inf.put("zw",zw);
        inf.put("ps",ps);
        inf.put("time",time);
        inf.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e==null){
                    Toast.makeText(buyingticket_1_1.this, "购票成功", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(buyingticket_1_1.this, "购票失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void collide() {
        final AVQuery<AVObject> query1 = new AVQuery<>("info");
        query1.whereEqualTo("time", time);
        query1.whereEqualTo("way", way);
        query1.whereEqualTo("zw", zw);
        query1.whereEqualTo("lc", lc);
        query1.whereEqualTo("ps", ps);
        query1.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e==null)
                {
                   co=1;
                }
                else{

                }
            }
        });
    }
   }
