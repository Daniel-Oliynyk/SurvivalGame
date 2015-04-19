package com.survivalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import java.util.Random;

public class Game extends SurfaceView implements Runnable {
    public static int WIDTH, HEIGHT;
    public static Picture screen = new Picture();
    public static Paint brush = new Paint();
    public static Canvas painter;
    public static Random random = new Random();
    public static Noise noise = new Noise();

    private Thread thread = null;
    private SurfaceHolder surface;

    private boolean running = false;
    public boolean isPaused = false;


    public Game(Context context) {
        super(context);
        surface = getHolder();
        this.setOnTouchListener(Touch.touchListener);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        WIDTH = size.x;
        HEIGHT = size.y;

        painter = screen.beginRecording(WIDTH, HEIGHT);
        brush.setAntiAlias(true);
        brush.setColor(Color.WHITE);
        brush.setStyle(Paint.Style.FILL);
    }

    public void resume() {
        running = true;
        isPaused = false;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        running = false;
        isPaused = true;
    }

    @Override
    public void run() {
        while (running) {
            if(!surface.getSurface().isValid()) continue;
            Canvas canvas = surface.lockCanvas();
            painter.drawColor(Color.rgb(139, 69, 19));

            int temp = 50;
            brush.setColor(Color.GREEN);
            for (float x = 0; x < WIDTH; x += temp) {
                for (float y = 0; y < HEIGHT; y += temp) {
                    int test = (int) (Math.abs(noise.eval(x / 1000, y / 1000)) * 256);
                    brush.setColor(Color.rgb(test, test, test));
                    painter.drawRect(x, y, x + temp, y + temp, brush);
                }
            }
            brush.setColor(Color.WHITE);
            Touch.update();
            Player.update();

            screen.endRecording();
            canvas.drawPicture(screen);
            screen = new Picture();
            painter = screen.beginRecording(WIDTH, HEIGHT);
            surface.unlockCanvasAndPost(canvas);
        }
    }
}