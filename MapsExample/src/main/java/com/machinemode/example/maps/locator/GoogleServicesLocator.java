package com.machinemode.example.maps.locator;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;

public class GoogleServicesLocator extends Locator implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {
    private final static String TAG = GoogleServicesLocator.class.getSimpleName();
    private LocationClient locationClient;
    private LocationRequest locationRequest;

    private GoogleMap map;

    public GoogleServicesLocator(Context context, GoogleMap googleMap) {
        super(googleMap);
        locationClient = new LocationClient(context, this, this);
        locationClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(INTERVAL_MS);
        locationRequest.setFastestInterval(INTERVAL_MS);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
        Location location = locationClient.getLastLocation();
        updateMap(new GeoCoordinate(location));
        locationClient.requestLocationUpdates(locationRequest, this);
    }

    @Override
    public void onDisconnected() {
        Log.d(TAG, "onDisconnected");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
    }

    @Override
    public void invalidate() {
        locationClient.removeLocationUpdates(this);
        locationClient.disconnect();
    }
}
