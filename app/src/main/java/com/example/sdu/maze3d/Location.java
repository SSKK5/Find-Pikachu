package com.example.sdu.maze3d;

/**
 * 坐标类
 */
public class Location {
    private float x;
    private float y;

    public Location(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
