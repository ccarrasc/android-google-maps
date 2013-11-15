package com.machinemode.example.maps.locator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.GoogleMap;

public class GoogleServicesLocator extends Locator implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {
    private final static String TAG = GoogleServicesLocator.class.getSimpleName();

    private GoogleMap map;

    public GoogleServicesLocator(Context context, GoogleMap googleMap) {
        super(googleMap);

        // TODO: Implement this
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
    }

    @Override
    public void onDisconnected() {
        Log.d(TAG, "onDisconnected");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
    }
}
