package com.example.chatandmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

    }
    protected void onStart() {
        super.onStart();
        ImageButton exit_about_button = (ImageButton) findViewById(R.id.imageButton);
        Intent myIntent = new Intent(AboutActivity.this, MainActivity.class);

        exit_about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.this.startActivity(myIntent);
            }
        });
    }
    public void onBackPressed(){
        Intent exit_intent = new Intent(AboutActivity.this, MainActivity.class);
        AboutActivity.this.startActivity(exit_intent);
    }
}
