package com.example.chatandmap;

import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.chatandmap.databinding.MapActivityBinding;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = MapActivityBinding.inflate(getLayoutInflater());
       // setContentView(R.layout.map_activity);
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageButton exit_map_button = (ImageButton) findViewById(R.id.imageButton4);
        Intent myIntent = new Intent(MapActivity.this, MainActivity.class);
        //Тут код карты
        exit_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.this.startActivity(myIntent);
            }
        });
    }
    public void onMapReady(GoogleMap googleMap){
        this.mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void onBackPressed(){
        Intent exit_intent = new Intent(MapActivity.this, MainActivity.class);
        MapActivity.this.startActivity(exit_intent);
    }
}
