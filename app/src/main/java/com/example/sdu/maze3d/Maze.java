package com.example.sdu.maze3d;

import com.threed.jpct.Object3D;

import java.util.Random;

/**
 * 迷宫类
 */
public class Maze {

    private int[][] checker;
    private Part[][] maze;
    private Random random;
    private int height;
    private int width;

    public Maze(){
        random = new Random();
    }

    public void createMaze(float height, float width){
        this.height = (int)height;
        this.width = (int)width;

        checker = new int[(int)height][(int)width];
        maze = new Part[(int)height][(int)width];

        for(int i = 0; i < (int)height; i++){
            for(int j = 0; j < (int)width; j++){
                checker[i][j] = (i * (int)width) + j;
                maze[i][j] = new Part(false, false);
            }
        }

        for(int x = 0; x < (int)height * (int)width * 10; x++){
            makeMaze(new Location(Math.abs(random.nextInt()) % (int)width, Math.abs(random.nextInt()) % (int)height));
        }
    }

    public void makeMaze(Location location){
        int dir = Math.abs(random.nextInt()) % 2; // dir == 1:向上检查是否需要清楚； dir == 2：向右检查是否需要清楚
        int setID = checker[(int)location.getY()][(int)location.getX()];
        int newSetID = -1;

        // 确保迷宫的墙是固体
        if((dir == 0 && (int)location.getY() == 0) || (dir == 1 && (int)location.getX() == maze[0].length - 1))
            return;

        // 随机的基础上，检查up和right是否需要清除
        if(dir == 0){
            newSetID = checker[(int)location.getY() - 1][(int)location.getX()];
            if(newSetID == setID)
                return;
            if(maze[(int)location.getY()][(int)location.getX()] == null)
                maze[(int)location.getY()][(int)location.getX()] = new Part(false, false);
            maze[(int)location.getY()][(int)location.getX()].upClear = true;
        }
        if(dir == 1){
            newSetID = checker[(int)location.getY()][(int)location.getX() + 1];
            if(newSetID == setID)
                return;
            if(maze[(int)location.getY()][(int)location.getX()] == null)
                maze[(int)location.getY()][(int)location.getX()] = new Part(false, false);
            maze[(int)location.getY()][(int)location.getX()].rightClear = true;
        }

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(checker[i][j] == setID)
                    checker[i][j] = newSetID;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean stepChecker(int dir, Location start){
        if(maze[(int)start.getY()][(int)start.getX()] == null)
            maze[(int)start.getY()][(int)start.getX()] = new Part(false, false);
        if(dir == 0)    // 上
            return maze[(int)start.getY()][(int)start.getX()].upClear;
        else if (dir == 1)  // 右
            return maze[(int)start.getY()][(int)start.getX()].rightClear;
        else if(dir == 2 && (int)start.getY() < height - 1) { // 下
            if(maze[(int) start.getY() + 1][(int) start.getX()] == null)
                maze[(int) start.getY() + 1][(int) start.getX()] = new Part(false, false);
            return maze[(int) start.getY() + 1][(int) start.getX()].upClear;
        }
        else if(dir == 3 && (int)start.getX() > 0) {  // 左
            if(maze[(int) start.getY()][(int) start.getX() - 1] == null)
                maze[(int) start.getY()][(int) start.getX() - 1] = new Part(false, false);
            return maze[(int) start.getY()][(int) start.getX() - 1].rightClear;
        }

        return false;
    }
}
