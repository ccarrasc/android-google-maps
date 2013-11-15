package com.machinemode.example.maps.locator;

import java.lang.reflect.Field;

public class GeoCoordinate {
    private double latitude;
    private double longitude;

    public GeoCoordinate() {

    }

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
