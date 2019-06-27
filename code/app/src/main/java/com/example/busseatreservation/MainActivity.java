package com.example.busseatreservation;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        name=getIntent().getStringExtra("name");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.train_info) {
            Intent t1=new Intent();
            t1.setClass(this,train_info.class);
            startActivity(t1);
        } else if (id == R.id.buyingticket) {
            Intent t1=new Intent();
            t1.setClass(this,buyingticket.class);
            t1.putExtra("name",name);
            startActivity(t1);
        } else if (id == R.id.person) {
            Intent t1=new Intent();
            t1.setClass(this,person.class);
            t1.putExtra("name",name);
            startActivity(t1);
        } else if (id == R.id.blue) {
            Intent t1=new Intent();
            t1.setClass(this,test1.class);
            startActivity(t1);
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
