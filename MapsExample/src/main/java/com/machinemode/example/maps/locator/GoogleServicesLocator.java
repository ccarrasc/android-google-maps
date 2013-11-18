package com.machinemode.example.maps.locator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;

public class GoogleServicesLocator extends Locator implements
                                                   GooglePlayServicesClient.ConnectionCallbacks,
                                                   GooglePlayServicesClient.OnConnectionFailedListener
{
    private LocationClient locationClient;
    private LocationRequest locationRequest;

    public GoogleServicesLocator(Context context, LocationUpdateListener listener)
    {
        super(listener);
        initialize(context);
        requestLocationUpdates();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        Log.d(getTag(), "onConnected");
        locationClient.requestLocationUpdates(locationRequest, this);
    }

    @Override
    public void onDisconnected()
    {
        Log.d(getTag(), "onDisconnected");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        Log.d(getTag(), "onConnectionFailed");
    }

    @Override
    public void invalidate()
    {
        locationClient.removeLocationUpdates(this);
        locationClient.disconnect();
    }

    private void initialize(Context context)
    {
        locationClient = new LocationClient(context, this, this);
        locationClient.connect();
    }

    private void requestLocationUpdates()
    {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(INTERVAL_MS);
        locationRequest.setFastestInterval(INTERVAL_MS);
    }
}
