package com.example.sdu.maze3d;

import android.content.Intent;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView gl_view;
    private World world = null;
    Camera cam = null;
    // 立方体
    private Object3D cube = null;
    private Object3D girl = null;
    // 光照类
    private Light sun = null;
    private RGBColor back = new RGBColor(0, 0, 0);
    private FrameBuffer fb = null;
    private float angle;    // 相对于向下的旋转角

    private Maze maze;
    private int row;    // 女孩当前所在行数
    private int column; // 女孩当前所在列数

    private int goalRow;    // 皮卡丘当前所在行数
    private int goalColumn; // 皮卡丘当前所在列数

    private int mazeSize;   // 迷宫大小
    private int level;  // 迷宫难度等级

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        mazeSize = intent.getIntExtra("size", 0);   // 获取迷宫大小
        level = intent.getIntExtra("level", 0); // 获取难度等级

        setContentView(R.layout.activity_main);
        gl_view = (GLSurfaceView) findViewById(R.id.gl_view);
        gl_view.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                angle = 0;
                row = 0;
                column = 0;

                world = new World();

                sun = new Light(world);
                sun.setIntensity(255, 255, 255);

                loadTexture();

                Object3D[] pika = Loader.loadOBJ(getResources().openRawResource(R.raw.pikachu), getResources().openRawResource(R.raw.pikachu1), 0.003f);
                cube = Object3D.mergeAll(pika);
                cube.rotateZ((float) Math.PI);
                cube.rotateY((float) Math.PI);
                cube.rotateX((float) Math.PI / 6);

                Object3D[] girlArray = Loader.loadOBJ(getResources().openRawResource(R.raw.kanna), getResources().openRawResource(R.raw.kanna_mtl), 0.08f);
                girl = Object3D.mergeAll(girlArray);
                girl.rotateZ((float) Math.PI);
                girl.rotateY((float) Math.PI);
                girl.translate(0.5f, -1.4f, -0.5f);

                cube.strip();
                cube.build();
                girl.strip();
                girl.build();
                world.addObject(girl);
                world.addObject(cube);

                maze = new Maze();
                maze.createMaze(mazeSize, mazeSize);

                cube.translate(maze.getWidth() - 0.5f, -0.5f, -maze.getHeight() + 0.5f);
                goalRow = maze.getHeight() - 1;
                goalColumn = maze.getWidth() - 1;
                drawWall(0, maze.getWidth() + 0.1f, 0, -1, 0, 0.1f); // 上
                drawWall(0, 0.1f, 0, -1, -maze.getHeight(), 0); // 左
                drawWall(0, maze.getWidth() + 0.1f, 0, -1, -maze.getHeight(), -maze.getHeight() + 0.1f); // 下
                drawWall(maze.getWidth(), maze.getWidth() + 0.1f, 0, -1, -maze.getHeight(), 0); // 右

                for (int counter = 0; counter < maze.getHeight(); counter++) {
                    for (int counter2 = 0; counter2 < maze.getWidth(); counter2++) {
                        if (!maze.stepChecker(0, new Location(counter2, counter))) {
                            drawWall(counter2, counter2 + 1.1f, 0, -1, -counter, -counter + 0.1f);
                        }
                        if (!maze.stepChecker(1, new Location(counter2, counter))) {
                            drawWall(counter2 + 1, counter2 + 1.1f, 0, -1, -counter - 1, -counter);
                        }
                    }
                }
                drawSlate(); // 画地板

                // 摄像机
                cam = world.getCamera();
                cam.setFovAngle((float) Math.PI / 4);
                cam.setPosition(new SimpleVector(0, -Math.cos(Math.PI / 5) * 7, -Math.sin(Math.PI / 5) * 5));
                cam.lookAt(new SimpleVector(0, 0, 0));
                cam.moveCamera(Camera.CAMERA_MOVERIGHT, 0.5f);
                cam.moveCamera(Camera.CAMERA_MOVEDOWN, 0.5f);

                SimpleVector sv = new SimpleVector();
                sv.set(girl.getTransformedCenter());
                sv.y -= 5;
                sun.setPosition(sv);
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                if (fb != null) {
                    fb.dispose();
                }
                // 创建一个宽度为w,高为h的FrameBuffer
                fb = new FrameBuffer(gl, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                try {
                    fb.clear(back);
                    world.renderScene(fb);
                    world.draw(fb);
                    fb.display();
                } catch (Exception e) {
                    Log.e("drawFrameException", e.getMessage());
                }
            }
        });
        gl_view.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    private void loadTexture() {
        // 皮卡丘纹理
        Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.pikagen)), 64, 64));
        addTexture("pikagen.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.eye1)), 64, 64));
        addTexture("eye1.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.mouth1)), 64, 64));
        addTexture("mouth1.png", texture);

        // 女孩纹理
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.phong1sg)), 64, 64));
        addTexture("phong1sg.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.phong2sg)), 64, 64));
        addTexture("phong2sg.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.phong3sg)), 64, 64));
        addTexture("phong3sg.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.phong4sg)), 64, 64));
        addTexture("phong4sg.png", texture);
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.phong5sg)), 64, 64));
        addTexture("phong5sg.png", texture);

        // 墙的纹理
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.wall)), 64, 64));
        addTexture("wall.jpg", texture);

        // 地板纹理
        texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.slate)), 64, 64));
        addTexture("slate.jpg", texture);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gl_view != null) {
            gl_view.onResume();
        }
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        if (gl_view != null) {
            gl_view.onPause();
        }
    }*/

    public void up(View view) {
        girl.rotateY(-angle);
        girl.rotateY((float) Math.PI);
        angle = (float) Math.PI;
        if (maze.stepChecker(0, new Location(column, row))) {
            girl.translate(0f, 0f, 1f);
            SimpleVector vector = cam.getPosition();
            SimpleVector sv = sun.getPosition();
            vector.z += 1f;
            sv.z += 1f;
            cam.setPosition(vector);
            sun.setPosition(sv);
            row--;
            if (row == goalRow && column == goalColumn) { // 找到皮卡丘
                finishGame();
            }
        }
    }

    public void down(View view) {
        girl.rotateY(-angle);
        angle = 0;
        if (maze.stepChecker(2, new Location(column, row))) {
            girl.translate(0f, 0f, -1f);
            SimpleVector vector = cam.getPosition();
            vector.z -= 1f;
            SimpleVector sv = sun.getPosition();
            sv.z -= 1f;
            cam.setPosition(vector);
            sun.setPosition(sv);
            row++;
            if (row == goalRow && column == goalColumn) {
                finishGame();
            }
        }
    }

    public void left(View view) {
        girl.rotateY(-angle);
        girl.rotateY((float) Math.PI / 2);
        angle = (float) Math.PI / 2;
        if (maze.stepChecker(3, new Location(column, row))) {
            girl.translate(-1f, 0f, 0f);
            SimpleVector vector = cam.getPosition();
            vector.x -= 1f;
            SimpleVector sv = sun.getPosition();
            sv.x -= 1f;
            cam.setPosition(vector);
            sun.setPosition(sv);
            column--;
            if (row == goalRow && column == goalColumn) {
                finishGame();
            }
        }
    }

    public void right(View view) {
        girl.rotateY(-angle);
        girl.rotateY((float) Math.PI / 2 * 3);
        angle = (float) Math.PI / 2 * 3;
        if (maze.stepChecker(1, new Location(column, row))) {
            girl.translate(1f, 0f, 0f);
            SimpleVector vector = cam.getPosition();
            vector.x += 1f;
            SimpleVector sv = sun.getPosition();
            sv.x += 1f;
            cam.setPosition(vector);
            sun.setPosition(sv);
            column++;
            if (row == goalRow && column == goalColumn) {
                finishGame();
            }
        }
    }

    public void drawWall(float xa, float xb, float ya, float yb, float za, float zb) {
        Object3D fang = new Object3D(12);

        int t1 = TextureManager.getInstance().getTextureID("wall.jpg");
        SimpleVector upperLeftFront = new SimpleVector(xa, yb, za);
        SimpleVector upperRightFront = new SimpleVector(xb, yb, za);
        SimpleVector lowerLeftFront = new SimpleVector(xa, ya, za);
        SimpleVector lowerRightFront = new SimpleVector(xb, ya, za);

        SimpleVector upperLeftBack = new SimpleVector(xa, yb, zb);
        SimpleVector upperRightBack = new SimpleVector(xb, yb, zb);
        SimpleVector lowerLeftBack = new SimpleVector(xa, ya, zb);
        SimpleVector lowerRightBack = new SimpleVector(xb, ya, zb);

        //画三角形
        //第一个参数是顶点位置()，2,3是纹理位置（u）,以此类推
        //最后一个参数是纹理的ID
        // Front
        fang.addTriangle(upperLeftFront, 0, 0, lowerLeftFront, 0, 1, upperRightFront, 1, 0, t1);
        fang.addTriangle(upperRightFront, 1, 0, lowerLeftFront, 0, 1, lowerRightFront, 1, 1, t1);

        // Back
        fang.addTriangle(upperLeftBack, 0, 0, upperRightBack, 1, 0, lowerLeftBack, 0, 1, t1);
        fang.addTriangle(upperRightBack, 1, 0, lowerRightBack, 1, 1, lowerLeftBack, 0, 1, t1);

        // Upper
        fang.addTriangle(upperLeftBack, 0, 0, upperLeftFront, 0, 1, upperRightBack, 1, 0, t1);
        fang.addTriangle(upperRightBack, 1, 0, upperLeftFront, 0, 1, upperRightFront, 1, 1, t1);

        // Lower
        fang.addTriangle(lowerLeftBack, 0, 0, lowerRightBack, 1, 0, lowerLeftFront, 0, 1, t1);
        fang.addTriangle(lowerRightBack, 1, 0, lowerRightFront, 1, 1, lowerLeftFront, 0, 1, t1);

        // Left
        fang.addTriangle(upperLeftFront, 0, 0, upperLeftBack, 1, 0, lowerLeftFront, 0, 1, t1);
        fang.addTriangle(upperLeftBack, 1, 0, lowerLeftBack, 1, 1, lowerLeftFront, 0, 1, t1);

        // Right
        fang.addTriangle(upperRightFront, 0, 0, lowerRightFront, 0, 1, upperRightBack, 1, 0, t1);
        fang.addTriangle(upperRightBack, 1, 0, lowerRightFront, 0, 1, lowerRightBack, 1, 1, t1);

        fang.strip();
        fang.build();

        world.addObject(fang);
    }

    /*
    绘制地板
     */
    private void drawSlate() {
        int t1 = TextureManager.getInstance().getTextureID("slate.jpg");
        for(int i = 0; i < mazeSize; i++){
            for(int j = 0; j < mazeSize; j++){
                Object3D fang = new Object3D(2);

                SimpleVector LeftFront = new SimpleVector(j, 0, -i);
                SimpleVector RightFront = new SimpleVector(j + 1, 0, -i);
                SimpleVector LeftBack = new SimpleVector(j, 0, -(i + 1));
                SimpleVector RightBack = new SimpleVector(j + 1, 0, -(i + 1));

                fang.addTriangle(LeftFront, 0, 0, LeftBack, 1, 0, RightFront, 0, 1, t1);
                fang.addTriangle(LeftBack, 1, 0, RightBack, 1, 1, RightFront, 0, 1, t1);

                fang.strip();
                fang.build();

                world.addObject(fang);
            }
        }
    }

    public void addTexture(String name, Texture texture) {
        if (!TextureManager.getInstance().containsTexture(name))
            TextureManager.getInstance().addTexture(name, texture);
    }

    public void finishGame() {
        boolean isPassAll;
        isPassAll = (level == 12);
        final FinishDialog dialog = new FinishDialog(MainActivity.this, isPassAll);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setOnCallbackLister(new FinishDialog.ClickListenerInterface() {
            @Override
            public void click(int id) {
                switch (id) {
                    case R.id.btn_yes: // yes按钮
                        dialog.dismiss();
                        enlargeScene();
                        break;
                    case R.id.btn_no:
                    case R.id.btn_return:
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("level", level);
                        setResult(1, intent);
                        world.dispose();
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("level", level - 1);
        setResult(1, intent);
        world.dispose();
        finish();
    }

    public void enlargeScene() {
        level++;

        girl.rotateY(-angle);   // 转回向下
        angle = 0;
        row = 0;
        column = 0;

        girl.translate(-(mazeSize - 1), 0f, mazeSize - 1);
        SimpleVector vector = cam.getPosition();
        vector.x -= (mazeSize - 1);
        vector.z += (mazeSize - 1);
        SimpleVector sv = sun.getPosition();
        sv.x -= (mazeSize - 1);
        sv.z += (mazeSize - 1);
        cam.setPosition(vector);
        sun.setPosition(sv);

        cube.translate(2, 0, -2);

        world.removeAllObjects();
        world.addObject(girl);
        world.addObject(cube);

        mazeSize += 2;
        goalColumn = mazeSize - 1;
        goalRow = mazeSize - 1;

        maze.createMaze(mazeSize, mazeSize);

        goalRow = maze.getHeight() - 1;
        goalColumn = maze.getWidth() - 1;

        drawWall(0, maze.getWidth() + 0.1f, 0, -1, 0, 0.1f); // 上
        drawWall(0, 0.1f, 0, -1, -maze.getHeight(), 0); // 左
        drawWall(0, maze.getWidth() + 0.1f, 0, -1, -maze.getHeight(), -maze.getHeight() + 0.1f); // 下
        drawWall(maze.getWidth(), maze.getWidth() + 0.1f, 0, -1, -maze.getHeight(), 0); // 右

        for (int counter = 0; counter < maze.getHeight(); counter++) {
            for (int counter2 = 0; counter2 < maze.getWidth(); counter2++) {
                if (!maze.stepChecker(0, new Location(counter2, counter))) {
                    drawWall(counter2, counter2 + 1.1f, 0, -1, -counter, -counter + 0.1f);
                }
                if (!maze.stepChecker(1, new Location(counter2, counter))) {
                    drawWall(counter2 + 1, counter2 + 1.1f, 0, -1, -counter - 1, -counter);
                }
            }
        }

        drawSlate();
    }
}
