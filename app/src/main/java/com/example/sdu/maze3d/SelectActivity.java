package com.example.sdu.maze3d;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SelectActivity extends AppCompatActivity {

    private View view02, view03, view04, view05, view06, view07, view08, view09, view10, view11, view12;
    private ImageView lock02, lock03, lock04, lock05, lock06, lock07, lock08, lock09, lock10, lock11, lock12;
    private int level;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        sp = getSharedPreferences("level", Context.MODE_PRIVATE);
        level = sp.getInt("level", 0);
        initView();
    }

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

        refreshLevel(level);
    }

    public void select1(View view) {
        Intent intent = new Intent(SelectActivity.this, MainActivity.class);
        intent.putExtra("size", 5);
        intent.putExtra("level", 1);
        startActivityForResult(intent, 0);
    }

    public void select2(View view) {
        if(lock02.getVisibility() == View.GONE) { // GONE
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            int level = data.getIntExtra("level", 0);
            if(level > this.level) {
                sp.edit().putInt("level", level).apply();
                this.level = level;
                refreshLevel(level);
            }
        }
    }

    public void refreshLevel(int level){
        switch (level){
            case 12:
            case 11:
                lock12.setVisibility(View.GONE);
                view12.setVisibility(View.GONE);
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
