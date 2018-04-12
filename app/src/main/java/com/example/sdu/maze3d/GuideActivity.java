package com.example.sdu.maze3d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2018/4/9.
 */
public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_guide);
    }

    public void startGame(View view) {
        Intent intent = new Intent(GuideActivity.this, SelectActivity.class);
        startActivity(intent);
    }
}
