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

public class MainView extends SurfaceView implements Runnable {
    public static int width;
    public static int height;
    Thread mainLoop = null;
    SurfaceHolder screen;
    public static AssetManager assets;
    private InternalListener iListener = new InternalListener();
    volatile boolean running = false;
    public boolean isPaused = false;


    public MainView(Context context) {
        super(context);
        assets = context.getAssets();
        screen = getHolder();
        this.setOnTouchListener(iListener);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    public void resume() {
        running = true;
        mainLoop = new Thread(this);
        mainLoop.start();
        isPaused = false;
    }

    public void pause() {
        running = false;
        isPaused = true;
    }

    @Override
    public void run() {
        while (running) {
            if(!screen.getSurface().isValid()) continue;
            update();
            draw();
        }
    }

    public void update() {

    }

    public void draw() {
        Canvas canvas = screen.lockCanvas();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        canvas.drawColor(Color.rgb(0,0,0));
        screen.unlockCanvasAndPost(canvas);
    }

    private class InternalListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }

    }
}