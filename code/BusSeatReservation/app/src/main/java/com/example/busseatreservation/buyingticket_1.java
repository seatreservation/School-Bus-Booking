package com.example.busseatreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class buyingticket_1 extends AppCompatActivity {
    private String way,name;
    private String date;
    private ListView lv2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyingticket_1);
        way=getIntent().getStringExtra("way");
        date=getIntent().getStringExtra("date");
        name=getIntent().getStringExtra("name");
        //Toast.makeText(buyingticket_1.this, name+"cehi", Toast.LENGTH_SHORT).show();
        TextView t1=(TextView)findViewById(R.id.textView14);
        t1.setText(way);
        TextView t2=(TextView)findViewById(R.id.textView16);
        t2.setText(date);
        //listview显示
        lv2 = (ListView) findViewById(R.id.lv2);/*定义一个动态数组*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList <HashMap<String,Object>>();/*在数组中存放数据*/
        for(int i=0;i<5;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            //map.put("ItemImage", R.drawable.b2);//加入图片
            int j=i+1;
            int t=i+8;
            switch (way){
                case"东校区——南校区":
                    map.put("ItemTitle", "车次：DX to NX."+j);
                    break;
                case"南校区——东校区":
                    map.put("ItemTitle", "车次：NX to DX."+j);
                    break;
                case"东校区——北校区":
                    map.put("ItemTitle", "车次：DX to BX."+j);
                    break;
                case"北校区——东校区":
                    map.put("ItemTitle", "车次：BX to DX."+j);
                    break;
                case"北校区——南校区":
                    map.put("ItemTitle", "车次：BX to NX."+j);
                    break;
                case"南校区——北校区":
                    map.put("ItemTitle", "车次：NX to BX."+j);
                    break;
            }

            map.put("ItemText2", "发车时间：");
            map.put("ItemText",date+" "+t+":00:00");
            listItem.add(map);
        }
        //new String  数据来源， new int 数据到哪去
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,R.layout.sample_adapter_item_1,
                new String[] {"ItemImage","ItemTitle","ItemText2", "ItemText"},
                new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText2,R.id.ItemText});
        lv2.setAdapter(mSimpleAdapter);//为ListView绑定适配器

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
               // setTitle("你点击了第"+arg2+"行");//设置标题栏显示点击的行
                int j=arg2+1;
                int t=arg2+8;
                Intent it1=new Intent();
                it1.setClass(buyingticket_1.this,buyingticket_1_1.class);
                switch (way){
                    case"东校区——南校区":
                        it1.putExtra("way","DX to NX."+j);
                        break;
                    case"南校区——东校区":
                        it1.putExtra("way","NX to DX."+j);
                        break;
                    case"南校区——北校区":
                        it1.putExtra("way","NX to BX."+j);
                        break;
                    case"北校区——南校区":
                        it1.putExtra("way","BX to NX."+j);
                        break;
                    case"东校区——北校区":
                        it1.putExtra("way","DX to BX."+j);
                        break;
                    case"北校区——东校区":
                        it1.putExtra("way","BX to DX."+j);
                        break;
                }
                it1.putExtra("time",date+" "+t+":00:00");
                it1.putExtra("lc",way);
                it1.putExtra("name",name);
                startActivity(it1);
            }
        });

    }
}
