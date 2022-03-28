package com.example.navigationdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThongBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_thongbao);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Quanli:
                        startActivity(new Intent(getApplicationContext(),Quanli.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Dangtin:
                        startActivity(new Intent(getApplicationContext(),Dangtin.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_thongbao:
                        return true;
                    case R.id.nav_Them:
                        startActivity(new Intent(getApplicationContext(),Them.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}