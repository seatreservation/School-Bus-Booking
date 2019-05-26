package com.example.busseatreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_usersDB = "create table usersDB(" +

            "pwd1 text primary key," +
            "pwd2 text)";
    public static final String CREATE_trainDB = "create table trainDB(" +

            "id integer primary key autoincrement," +
            "name text," +
            "adr text," +
            "pwd text)" ;



    private Context mContext;

    //构造方法：第一个参数Context，第二个参数数据库名，第三个参数cursor允许我们在查询数据的时候返回一个自定义的光标位置，一般传入的都是null，第四个参数表示目前库的版本号（用于对库进行升级）
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory , int version){
        super(context,name ,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用SQLiteDatabase中的execSQL（）执行建表语句。
        db.execSQL(CREATE_usersDB);
        db.execSQL(CREATE_trainDB);
        //创建成功
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usersDB");
        db.execSQL("drop table if exists trainDB");
        onCreate(db);
    }

}
