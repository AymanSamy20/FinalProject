package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(MainActivity.this, SlideActivity.class);
                    startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}