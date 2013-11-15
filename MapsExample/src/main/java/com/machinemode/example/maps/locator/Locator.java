package com.machinemode.example.maps.locator;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * <a href="https://developers.google.com/maps/documentation/android/">Google Maps Android API v2</a>
 */
public abstract class Locator implements android.location.LocationListener,
        com.google.android.gms.location.LocationListener {
    final static String TAG = Locator.class.getSimpleName();
    final static long INTERVAL_MS = 1000;
    final static int ZOOM_LEVEL = 10;

    private GoogleMap map;

    public Locator(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
        updateMap(new GeoCoordinate(location));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled: " + provider);
    }

    public void updateMap(GeoCoordinate geoCoordinate) {
        if(geoCoordinate.isValid()) {
            LatLng latLng = new LatLng(geoCoordinate.getLatitude(), geoCoordinate.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL));
        }
    }

    public abstract void invalidate();
}
