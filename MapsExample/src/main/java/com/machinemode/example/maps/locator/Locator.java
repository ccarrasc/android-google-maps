package com.machinemode.example.maps.locator;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

/**
 * <a href="https://developers.google.com/maps/documentation/android/">Google Maps Android API v2</a>
 */
public abstract class Locator implements android.location.LocationListener,
                                         com.google.android.gms.location.LocationListener
{
    final static long INTERVAL_MS = 1000;
    final static long MIN_DISTANCE = 0;
    private String tag;
    private LocationUpdateListener locationUpdateListener;

    public Locator(LocationUpdateListener listener)
    {
        tag = ((Object) this).getClass().getSimpleName(); // http://youtrack.jetbrains.com/issue/IDEA-79680
        locationUpdateListener = listener;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Log.d(tag, "onLocationChanged: " + location.toString());
        locationUpdateListener.onLocationUpdate(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        Log.d(tag, "onStatusChanged: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        Log.d(tag, "onProviderEnabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        Log.d(tag, "onProviderDisabled: " + provider);
    }

    String getTag()
    {
        return tag;
    }

    public abstract void invalidate();
}
