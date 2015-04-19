package com.survivalgame;

import static com.survivalgame.Game.*;

public class Player {
    static int x = WIDTH / 2, y = HEIGHT / 2, speed = 20;

    static void update() {
        x = x + Touch.scaleMagnitudeX(speed);
        y = y + Touch.scaleMagnitudeY(speed);
//        x = (x > WIDTH - 50)? WIDTH - 50 : x;
//        y = (y > HEIGHT - 50)? HEIGHT - 50 : y;
//        x = (x < 50)? 50 : x;
//        y = (y < 50)? 50 : y;
//        painter.drawCircle(x, y, 50, brush);
        painter.drawCircle(WIDTH / 2, HEIGHT / 2, 50, brush);
    }
}
