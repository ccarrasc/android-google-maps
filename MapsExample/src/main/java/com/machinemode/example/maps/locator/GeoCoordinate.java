package com.machinemode.example.maps.locator;

import android.location.Location;

import java.lang.reflect.Field;

public class GeoCoordinate {
    private double latitude;
    private double longitude;

    public GeoCoordinate(Location location) {
        if(location != null) {
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isValid() {
        return latitude != 0 && longitude != 0;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Field fields[] = this.getClass().getDeclaredFields();

        for(Field field : fields)
        {
            stringBuilder.append(field.getName());
            stringBuilder.append(": ");
            try
            {
                stringBuilder.append(field.get(this));
            }
            catch(IllegalArgumentException e)
            {
                stringBuilder.append("IllegalArgumentException!");
            }
            catch(IllegalAccessException e)
            {
                stringBuilder.append("IllegalAccessException!");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
