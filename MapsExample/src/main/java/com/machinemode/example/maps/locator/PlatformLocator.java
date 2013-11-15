package com.machinemode.example.maps.locator;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

public final class PlatformLocator extends Locator {
    private LocationManager locationManager;
    private Criteria criteria = new Criteria();
    private String provider;

    public PlatformLocator(Context context, GoogleMap googleMap) {
        super(googleMap);
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        provider = locationManager.getBestProvider(criteria, true);
        Log.d(TAG, provider);

        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            updateMap(new GeoCoordinate(location));

            locationManager.requestLocationUpdates(provider, INTERVAL_MS, 0, this);
        }
        catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        }
        catch (IllegalArgumentException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void invalidate() {
        locationManager.removeUpdates(this);
    }
}
