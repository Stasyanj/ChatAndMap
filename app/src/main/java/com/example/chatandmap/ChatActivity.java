package com.example.chatandmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ImageButton exit_chat_button = (ImageButton) findViewById(R.id.imageButton2);
        //Код чата
        exit_chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChatActivity.this, MainActivity.class);
                ChatActivity.this.startActivity(myIntent);
            }
        });

    }
}
