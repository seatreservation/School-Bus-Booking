package com.example.busseatreservation;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.setServer(AVOSCloud.SERVER_TYPE.API, "https://avoscloud.com");
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"zerszxwdedzsvP8XohfvHiEu-gzGzoHsz","HEhDPjfa7UCRm0JJxEOTJhgK");

    }
}