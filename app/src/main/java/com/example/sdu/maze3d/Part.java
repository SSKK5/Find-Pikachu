package com.example.sdu.maze3d;

/**
 * Created by Administrator on 2018/4/8.
 */
public class Part {
    boolean upClear;
    boolean rightClear;

    public Part(boolean upClear, boolean rightClear){
        this.upClear = upClear;
        this.rightClear = rightClear;
    }

    public boolean getUp(){
        return upClear;
    }

    public boolean getRightClear(){
        return rightClear;
    }
}
