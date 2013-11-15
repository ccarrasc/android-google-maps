package com.machinemode.example.maps;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.machinemode.example.maps.locator.GoogleServicesLocator;
import com.machinemode.example.maps.locator.PlatformLocator;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private PlatformLocator platformLocator;
    private GoogleServicesLocator googleServicesLocator;
    private GoogleMap mapA, mapB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Check for Google Play Services

        mapA = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapA)).getMap();
        mapA.setMyLocationEnabled(true);

        mapB = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapB)).getMap();
        mapB.setMyLocationEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        platformLocator = new PlatformLocator(this, mapA);
        googleServicesLocator = new GoogleServicesLocator(this, mapB);
    }

    @Override
    protected void onStop() {
        platformLocator.invalidate();
        googleServicesLocator.invalidate();
        super.onStop();
    }
}
