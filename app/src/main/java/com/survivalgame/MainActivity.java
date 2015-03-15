package com.survivalgame;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView);
    }

    public void onStart(){
        super.onStart();
        mainView.resume();
    }

    public void onStop(){
        super.onStop();
        mainView.pause();
    }
}
