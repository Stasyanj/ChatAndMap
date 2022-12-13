package com.example.chatandmap;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
    }
    protected void onStart() {
        super.onStart();
        ImageButton exit_chat_button = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton send_button = (ImageButton) findViewById(R.id.imageButton3);
        Intent myIntent = new Intent(ChatActivity.this, MainActivity.class);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_text();
            }
        });
        exit_chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.this.startActivity(myIntent);
            }
        });
    }
    public void send_text(){
        EditText Edit_text = (EditText) findViewById(R.id.editText);
        TextView txt_view = (TextView) findViewById(R.id.textView);
        Edit_text.setMovementMethod(new ScrollingMovementMethod());
        String text = Edit_text.getText().toString();
        text = text +"\n";
        txt_view.append(text);
    }
}
