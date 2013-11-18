package com.machinemode.example.maps.tests.locator;


import android.location.Location;
import android.test.AndroidTestCase;

import com.machinemode.example.maps.locator.LocationUpdateListener;

public class PlatformLocatorTest extends AndroidTestCase implements LocationUpdateListener
{
    public PlatformLocatorTest()
    {
        super();
    }

    @Override
    public void onLocationUpdate(Location location)
    {

    }
}
