package com.lilly021.quickok;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng winwin = new LatLng(45.254450880537426, 19.841089024486163);
        mMap.addMarker(new MarkerOptions().position(winwin).title("Win Win"));
        LatLng gigatron = new LatLng(45.255236352420596, 19.845294728167044);
        mMap.addMarker(new MarkerOptions().position(gigatron).title("Gigatron"));
        LatLng tehnomanija = new LatLng(45.25013059098036, 19.84520889747968);
        mMap.addMarker(new MarkerOptions().position(tehnomanija).title("Tehnomanija"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gigatron));
        mMap.setMinZoomPreference(15);
    }
}