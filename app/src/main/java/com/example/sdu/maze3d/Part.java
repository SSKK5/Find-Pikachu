package com.example.sdu.maze3d;

/**
 * 地图类中要用到的数据
 */
public class Part {
    boolean upClear;    // 上部是否清除
    boolean rightClear; // 右部是否清除

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
