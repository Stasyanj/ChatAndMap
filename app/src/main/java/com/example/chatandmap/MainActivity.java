package com.example.chatandmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_chat = (Button) findViewById(R.id.button);
        Button button_map = (Button) findViewById(R.id.button2);
        Button button_about = (Button) findViewById(R.id.button3);
        Button button_exit = (Button) findViewById(R.id.button4);
            button_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainActivity.this.startActivity(myIntent);
                    //Тут код чата
                }
            });
            button_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.map_activity);
                    //Тут код карты
                }
            });
            button_about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.about);
                    //Тут код описания
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