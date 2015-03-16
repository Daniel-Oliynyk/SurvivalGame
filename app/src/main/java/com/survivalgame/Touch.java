package com.survivalgame;

import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import static com.survivalgame.Game.*;

public class Touch {

    public static final int FULL_SIZE = 175, INNER_SIZE = 100, OFFSET = 20;
    private static int x, y, id = -1;
    private static double angle, magnitude;

    public static View.OnTouchListener touchListener = new View.OnTouchListener()  {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean pressingDown = event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_DOWN;
            boolean withinBounds = Math.sqrt(Math.pow(event.getX() - x, 2) + Math.pow(y - event.getY(), 2)) <= FULL_SIZE;
            if (pressingDown && withinBounds && id < 0) id = event.getPointerId(event.getActionIndex());
            if (id == event.getPointerId(event.getActionIndex())) {
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                    magnitude = 0;
                    id = -1;
                }
                else {
                    angle = Math.atan2(y - event.getY(id), event.getX(id) - x);
                    magnitude = Math.sqrt(Math.pow(event.getX(id) - x, 2) + Math.pow(y - event.getY(id), 2));
                    magnitude = (magnitude > FULL_SIZE * 0.75)? FULL_SIZE * 0.75 : magnitude;
                }
            }
            return true;
        }
    };

    public static int scaleMagnitudeX(int scale) {
        double scaleDecimal = (magnitude / (FULL_SIZE * 0.75)) * scale;
        return (int) (Math.cos(angle) * scaleDecimal);
    }

    public static int scaleMagnitudeY(int scale) {
        double scaleDecimal = (magnitude / (FULL_SIZE * 0.75)) * scale;
        return -1 * (int) (Math.sin(angle) * scaleDecimal);
    }

    public static void update() {
        x = 0 + FULL_SIZE + OFFSET;
        y = HEIGHT - FULL_SIZE - OFFSET;
        brush.setStyle(Paint.Style.STROKE);
        painter.drawCircle(x, y, FULL_SIZE, brush);
        brush.setStyle(Paint.Style.FILL);
        painter.drawCircle(x + (int) (Math.cos(angle) * magnitude), y - (int) (Math.sin(angle) * magnitude), INNER_SIZE, brush);
    }
}
