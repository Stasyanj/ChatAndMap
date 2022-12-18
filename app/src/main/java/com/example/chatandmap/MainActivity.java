package com.example.chatandmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            super.onCreate(savedInstanceState);
        }
    }
    protected  void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        Button button_chat = (Button) findViewById(R.id.button);
        Button button_map = (Button) findViewById(R.id.button2);
        Button button_about = (Button) findViewById(R.id.button3);
        Button button_exit = (Button) findViewById(R.id.button4);
        Intent myIntent_chat = new Intent(MainActivity.this, ChatActivity.class);
        Intent myIntent_map = new Intent(MainActivity.this, MapActivity.class);
        Intent myIntent_about = new Intent(MainActivity.this, AboutActivity.class);
        button_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(myIntent_chat);
            }
        });
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(myIntent_map);
            }
        });
        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(myIntent_about);
            }
        });
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}