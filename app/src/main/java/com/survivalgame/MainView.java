package com.survivalgame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by austin-james on 2015-03-13.
 */
public class MainView extends SurfaceView implements Runnable{

    //screen dimensions
    public static int width;
    public static int height;

    //define the mainLoop Thread
    Thread mainLoop = null;

    //define the surface holder
    SurfaceHolder screen;

    //create the asset manager
    public static AssetManager assets;

    private InternalListener iListener = new InternalListener();

    volatile boolean running = false;
    public boolean isPaused = false;


    public MainView(Context context){
        super(context);

        assets = context.getAssets();

        screen = getHolder();
        this.setOnTouchListener(iListener);

        //getting screen width and height
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    //custom resume method
    public void resume(){
        running = true;
        mainLoop = new Thread(this);
        mainLoop.start();
        isPaused = false;
    }

    //custom pause method
    public void pause(){
        running = false;
        isPaused = true;
    }

    @Override
    public void run() {
        while(running){

            //make sure the surface is usable
            if(!screen.getSurface().isValid()) continue;

            update();
            draw();

        }
    }

    public void update() {


    }

    public void draw(){
        //request the drawing canvas
        Canvas canvas = screen.lockCanvas();

        ////////////////////////////////////////////////////////////////////////////////////////////
        //draw stuff in here

        //create paint object
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        //clear the screen
        canvas.drawColor(Color.rgb(0,0,0));
        ////////////////////////////////////////////////////////////////////////////////////////////

        screen.unlockCanvasAndPost(canvas);
    }

    //inner listener class
    private class InternalListener implements OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }

    }
}