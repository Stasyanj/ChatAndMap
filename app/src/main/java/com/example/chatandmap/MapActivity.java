package com.example.chatandmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        //Тут код карты
        ImageButton exit_map_button = (ImageButton) findViewById(R.id.imageButton);
        exit_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MapActivity.this, MainActivity.class);
                MapActivity.this.startActivity(myIntent);
            }
        });

    }
}
