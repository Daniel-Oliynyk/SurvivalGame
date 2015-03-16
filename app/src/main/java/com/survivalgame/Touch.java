package com.survivalgame;

import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import static com.survivalgame.Game.*;

public class Touch {

    public static final int FULL_SIZE = 175, INNER_SIZE = 100, OFFSET = 20;
    public static int x = 0 + FULL_SIZE + OFFSET, y = HEIGHT - FULL_SIZE - OFFSET;
    public static double angle, magnitude;

    public static View.OnTouchListener touchListener = new View.OnTouchListener()  {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            angle = Math.atan2(y - event.getY(), event.getX() - x);
            magnitude = Math.sqrt(Math.pow(event.getX() - x, 2) + Math.pow(y - event.getY(), 2));
            magnitude = (magnitude > FULL_SIZE * 0.75)? FULL_SIZE * 0.75 : magnitude;
            return true;
        }
    };

    public static void update() {
        x = 0 + FULL_SIZE + OFFSET;
        y = HEIGHT - FULL_SIZE - OFFSET;
        brush.setStyle(Paint.Style.STROKE);
        painter.drawCircle(x, y, FULL_SIZE, brush);
        brush.setStyle(Paint.Style.FILL);
        painter.drawCircle(x + (int) (Math.cos(angle) * magnitude), y - (int) (Math.sin(angle) * magnitude), INNER_SIZE, brush);
    }

}
