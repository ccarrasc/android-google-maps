package com.machinemode.example.maps.locator;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * <a href="https://developers.google.com/maps/documentation/android/">Google Maps Android API v2</a>
 */
public abstract class Locator implements LocationListener {
    final static String TAG = Locator.class.getSimpleName();
    private final static int ZOOM_LEVEL = 10;
    private GoogleMap map;
    private GeoCoordinate geoCoordinate = new GeoCoordinate();

    public Locator(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
        geoCoordinate.setLatitude(location.getLatitude());
        geoCoordinate.setLongitude(location.getLongitude());
        updateMap(geoCoordinate);
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
        LatLng latLng = new LatLng(geoCoordinate.getLatitude(), geoCoordinate.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL));
    }
}
