package com.example.mapas_is;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener
{
    GoogleMap  Mapa;
    ArrayList <LatLng> puntos = new ArrayList<LatLng>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Toast.makeText(
                MainActivity.this,
                "Click\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lng: " + latLng.longitude + "\n",
                Toast.LENGTH_SHORT).show();

        Mapa.addMarker(new MarkerOptions().position(latLng)
                .title("lat: "+latLng.latitude+" lng: "+latLng.longitude));
        puntos.add(latLng);
        if(puntos.size()==4){
            PolylineOptions lineas = new PolylineOptions()

                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);

            Mapa.addPolyline(lineas);
            puntos.clear();
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Mapa = googleMap;
        Mapa.setOnMapClickListener(this);
        Mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Mapa.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(40.45411186063725, -3.6890324603360836), 20 );

        Mapa.moveCamera(camUpd1);

        /*PolylineOptions lineas = new PolylineOptions()

                .add(new LatLng(40.45438775029186, -3.6898591912247056))
                .add(new LatLng(40.45420746268186, -3.686978419919321))

                .add(new LatLng(40.45183961657279, -3.6866946213638774))
                .add(new LatLng(40.4520638454882, -3.6900721846874336))
                .add(new LatLng(40.45438775029186, -3.6898591912247056));

        lineas.width(8);
        lineas.color(Color.RED);

        Mapa.addPolyline(lineas);*/


    }
}