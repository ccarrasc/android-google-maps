package com.machinemode.example.maps.locator;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.util.Log;

public final class PlatformLocator extends Locator
{
    private LocationManager locationManager;
    private String provider;

    public PlatformLocator(Context context, LocationUpdateListener listener)
    {
        super(listener);
        initialize(context);
        requestLocationUpdates();
    }

    @Override
    public void invalidate()
    {
        locationManager.removeUpdates(this);
    }

    private void initialize(Context context)
    {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, true);
    }

    private void requestLocationUpdates()
    {
        try
        {
            locationManager.requestLocationUpdates(provider, INTERVAL_MS, MIN_DISTANCE, this);
        }
        catch (SecurityException e)
        {
            Log.d(getTag(), e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            Log.d(getTag(), e.getMessage());
        }
    }
}
