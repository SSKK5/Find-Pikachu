package com.example.sdu.maze3d;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 选择关卡界面
 */
public class SelectActivity extends AppCompatActivity {

    private View view02, view03, view04, view05, view06, view07, view08, view09, view10, view11, view12;
    private ImageView lock02, lock03, lock04, lock05, lock06, lock07, lock08, lock09, lock10, lock11, lock12;
    private int level;
    private SharedPreferences sp;

    /*
    Activity创建时调用
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);   // 设置布局为activity_guide.xml
        sp = getSharedPreferences("level", Context.MODE_PRIVATE);   // sharedpreferences为本地缓存，获取名称为level的本地缓存，也就是获取已经玩到的关卡数
        level = sp.getInt("level", 0);  // 获取缓存中名称为level的int数，即通关卡数，如果不存在这个数据，则默认值为0
        initView(); // 初始化视图函数
    }

    /*
    初始化视图函数
     */
    private void initView(){
        view02 = findViewById(R.id.view02);
        view03 = findViewById(R.id.view03);
        view04 = findViewById(R.id.view04);
        view05 = findViewById(R.id.view05);
        view06 = findViewById(R.id.view06);
        view07 = findViewById(R.id.view07);
        view08 = findViewById(R.id.view08);
        view09 = findViewById(R.id.view09);
        view10 = findViewById(R.id.view10);
        view11 = findViewById(R.id.view11);
        view12 = findViewById(R.id.view12);

        lock02 = (ImageView) findViewById(R.id.lock02);
        lock03 = (ImageView) findViewById(R.id.lock03);
        lock04 = (ImageView) findViewById(R.id.lock04);
        lock05 = (ImageView) findViewById(R.id.lock05);
        lock06 = (ImageView) findViewById(R.id.lock06);
        lock07 = (ImageView) findViewById(R.id.lock07);
        lock08 = (ImageView) findViewById(R.id.lock08);
        lock09 = (ImageView) findViewById(R.id.lock09);
        lock10 = (ImageView) findViewById(R.id.lock10);
        lock11 = (ImageView) findViewById(R.id.lock11);
        lock12 = (ImageView) findViewById(R.id.lock12);

        refreshLevel(level);    // 更新界面中可以玩的关卡的函数
    }

    public void select1(View view) {    // 当点击第一关的皮卡丘
        Intent intent = new Intent(SelectActivity.this, MainActivity.class);    // 创建从当前界面到MainActivity的意图
        intent.putExtra("size", 5); // 意图中增加一个size参数，值为5，也就是迷宫地图的大小为5
        intent.putExtra("level", 1);    // 意图中增加level参数，值为1，也就是迷宫难度为第一关
        startActivityForResult(intent, 0);  // 执行意图打开MainActivity，并且监听一个result，当MainActivity关闭，返回一个结果
    }

    /*
    下面所有的select函数都同select1函数
     */
    public void select2(View view) {
        if(lock02.getVisibility() == View.GONE) { // 锁图片必须要是隐藏的，点击才有效果，否则不会执行下面的操作
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 7);
            intent.putExtra("level", 2);
            startActivityForResult(intent, 0);
        }
    }

    public void select3(View view) {
        if(lock03.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 9);
            intent.putExtra("level", 3);
            startActivityForResult(intent, 0);
        }
    }

    public void select4(View view) {
        if(lock04.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 11);
            intent.putExtra("level", 4);
            startActivityForResult(intent, 0);
        }
    }

    public void select5(View view) {
        if(lock05.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 13);
            intent.putExtra("level", 5);
            startActivityForResult(intent, 0);
        }
    }

    public void select6(View view) {
        if(lock06.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 15);
            intent.putExtra("level", 6);
            startActivityForResult(intent, 0);
        }
    }

    public void select7(View view) {
        if(lock07.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 17);
            intent.putExtra("level", 7);
            startActivityForResult(intent, 0);
        }
    }

    public void select8(View view) {
        if(lock08.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 19);
            intent.putExtra("level", 8);
            startActivityForResult(intent, 0);
        }
    }

    public void select9(View view) {
        if(lock09.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 21);
            intent.putExtra("level", 9);
            startActivityForResult(intent, 0);
        }
    }

    public void select10(View view) {
        if(lock10.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 23);
            intent.putExtra("level", 10);
            startActivityForResult(intent, 0);
        }
    }

    public void select11(View view) {
        if(lock11.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 25);
            intent.putExtra("level", 11);
            startActivityForResult(intent, 0);
        }
    }

    public void select12(View view) {
        if(lock12.getVisibility() == View.GONE) { // GONE
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra("size", 27);
            intent.putExtra("level", 12);
            startActivityForResult(intent, 0);
        }
    }

    /*
    当MainActivity关闭，返回结果在这里监听
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){    // 如果从MainActivity返回的结果为1，则为MainActivity中通过了关卡，这时需要更新这个关卡界面
            int level = data.getIntExtra("level", 0);   // 获取已经通过的关卡
            if(level > this.level) {    // 如果已经通过的关卡比缓存中存储的关卡要大，则更新缓存中的关卡
                sp.edit().putInt("level", level).apply();
                this.level = level;
                refreshLevel(level);
            }
        }
    }

    /*
    更新界面中可以玩的关卡的函数
     */
    public void refreshLevel(int level){       // 判断level值
        switch (level){
            case 12:
            case 11:    // 如果已经通了11关
                lock12.setVisibility(View.GONE);    // 将第12关的锁图片设置为不可见
                view12.setVisibility(View.GONE);    // 将第12关的白色图片设置为不可见，没有break，会继续执行下面的case
            case 10:
                lock11.setVisibility(View.GONE);
                view11.setVisibility(View.GONE);
            case 9:
                lock10.setVisibility(View.GONE);
                view10.setVisibility(View.GONE);
            case 8:
                lock09.setVisibility(View.GONE);
                view09.setVisibility(View.GONE);
            case 7:
                lock08.setVisibility(View.GONE);
                view08.setVisibility(View.GONE);
            case 6:
                lock07.setVisibility(View.GONE);
                view07.setVisibility(View.GONE);
            case 5:
                lock06.setVisibility(View.GONE);
                view06.setVisibility(View.GONE);
            case 4:
                lock05.setVisibility(View.GONE);
                view05.setVisibility(View.GONE);
            case 3:
                lock04.setVisibility(View.GONE);
                view04.setVisibility(View.GONE);
            case 2:
                lock03.setVisibility(View.GONE);
                view03.setVisibility(View.GONE);
            case 1:
                lock02.setVisibility(View.GONE);
                view02.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
