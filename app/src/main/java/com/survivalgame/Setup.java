package com.survivalgame;

import android.app.Activity;
import android.os.Bundle;

public class Setup extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this);
        game.resume();
        setContentView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.resume();
    }
}
