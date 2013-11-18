package com.machinemode.example.maps;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.machinemode.example.maps.locator.GoogleServicesLocator;
import com.machinemode.example.maps.locator.LocationUpdateListener;
import com.machinemode.example.maps.locator.PlatformLocator;

public class MainActivity extends Activity
{

    final static int ZOOM_LEVEL = 10;
    private final static String TAG = MainActivity.class.getSimpleName();
    private PlatformLocator platformLocator;
    private GoogleServicesLocator googleServicesLocator;
    private GoogleMap mapA, mapB;
    private LocationUpdateListener mapAUpdateListener = new LocationUpdateListener()
    {
        @Override
        public void onLocationUpdate(Location location)
        {
            updateMap(mapA, location);
        }
    };
    private LocationUpdateListener mapBUpdateListener = new LocationUpdateListener()
    {
        @Override
        public void onLocationUpdate(Location location)
        {
            updateMap(mapB, location);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Check for Google Play Services

        MapFragment mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapA));

        if (mapFragment != null)
        {
            mapA = mapFragment.getMap();
            mapA.setMyLocationEnabled(true);
        }

        mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapB));

        if (mapFragment != null)
        {
            mapB = mapFragment.getMap();
            mapB.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        platformLocator = new PlatformLocator(this, mapAUpdateListener);
        googleServicesLocator = new GoogleServicesLocator(this, mapBUpdateListener);
    }

    @Override
    protected void onStop()
    {
        platformLocator.invalidate();
        googleServicesLocator.invalidate();
        super.onStop();
    }

    private void updateMap(GoogleMap map, Location location)
    {
        if (location != null)
        {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL));
        }
    }
}
