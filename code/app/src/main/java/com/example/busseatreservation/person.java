package com.example.busseatreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.avos.avoscloud.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class person extends AppCompatActivity {
    private ListView lv;
    private String uid;
    private String way;
    private String time;
    private String lc,ps,zw;
    private String name,creatat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
        name=getIntent().getStringExtra("name");
       // ArrayList<HashMap<String, Object>> listItem = new ArrayList <HashMap<String,Object>>();/*在数组中存放数据*/


        AVQuery<AVObject> query = new AVQuery<>("info");
        query.whereEqualTo("name",name);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<AVObject>() {
            final ArrayList<HashMap<String, Object>> listItem = new ArrayList <HashMap<String,Object>>();/*在数组中存放数据*/
            @Override

            public void done(List<AVObject> list, AVException e) {
                for(AVObject account :list){
                    uid=account.getString("objectId");
                    lc=account.getString("lc");
                    way=account.getString("way");
                    time=account.getString("time");
                    ps=account.getString("ps");
                    zw=account.getString("zw");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    creatat=sdf.format(account.getDate("createdAt"));
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("ItemImage", R.drawable.b2);//加入图片
                    map.put("ItemTitle", lc);
                    map.put("tx1","购票时间：");
                    map.put("tx2",creatat);
                    map.put("tx3","发车时间：");
                    map.put("ItemText2", "车次："+way);
                    map.put("ItemText",time);
                    map.put("ItemText5", "排数："+ps);
                    map.put("ItemText6", "座位："+zw);
                    listItem.add(map);
                    lv = (ListView) findViewById(R.id.lv);
                    //new String  数据来源， new int 数据到哪去
                    SimpleAdapter mSimpleAdapter = new SimpleAdapter(person.this,listItem,R.layout.sample_adapter_item,
                            new String[] {"ItemImage","ItemTitle","tx1","tx2","tx3","ItemText2", "ItemText","ItemText4","ItemText5","ItemText6"},
                            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.tx1,R.id.tx2,R.id.tx3,R.id.ItemText2,R.id.ItemText,R.id.ItemText4,R.id.ItemText5,R.id.ItemText6});
                    lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            new AlertDialog.Builder(person.this).setTitle("是否取消此项预定")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                           delete();
                                           Toast.makeText(person.this, "取消成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//响应事件
                                    // TODO Auto-generated method stub
                                }
                            }).show();
                        }
                    });
                    }
                if (e == null) {

                    Toast.makeText(person.this, "查询成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(person.this, "查询失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
    //
    void delete(){

        final AVQuery<AVObject> query1 = new AVQuery<>("info");
        query1.whereEqualTo("name",name);
        query1.whereEqualTo("time",time);
        query1.whereEqualTo("way",way);
        query1.whereEqualTo("zw",zw);
        query1.whereEqualTo("ps",ps);
        query1.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e==null)
                {
                    query1.deleteAllInBackground(new DeleteCallback() {
                            @Override
                            public void done(AVException e) {

                            }
                    });
                }
                else{

                }
            }
        });
    }


}
