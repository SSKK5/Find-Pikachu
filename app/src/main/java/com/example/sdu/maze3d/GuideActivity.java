package com.example.sdu.maze3d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 首页界面
 */
public class GuideActivity extends AppCompatActivity {

    /*
    Activity创建时调用
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){ // 如果顶部标题栏存在
            getSupportActionBar().hide();   // 隐藏顶部标题栏
        }
        setContentView(R.layout.activity_guide);    // 设置布局为activity_guide.xml
    }

    public void startGame(View view) {  // 当点击start按钮时触发的函数
        Intent intent = new Intent(GuideActivity.this, SelectActivity.class);   // 定义一个由当前activity到SelectActivity的意图
        startActivity(intent);  // 执行意图，打开SelectActivity界面
    }
}
