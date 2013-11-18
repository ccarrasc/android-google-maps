package com.machinemode.example.maps.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.google.android.gms.maps.MapFragment;
import com.machinemode.example.maps.MainActivity;
import com.machinemode.example.maps.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>
{

    private MainActivity mainActivity;
    private MapFragment mapFragmentA;
    private MapFragment mapFragmentB;

    public MainActivityTest()
    {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mainActivity = getActivity();
        mapFragmentA = ((MapFragment) mainActivity.getFragmentManager().findFragmentById(R.id.mapA));
        mapFragmentB = ((MapFragment) mainActivity.getFragmentManager().findFragmentById(R.id.mapB));
    }

    public void testPreconditions()
    {
        assertNotNull(mainActivity);
        assertNotNull(mapFragmentA);
        assertNotNull(mapFragmentB);
    }

    public void testViews()
    {
        View viewA = mapFragmentA.getView();
        View viewB = mapFragmentB.getView();
        assertNotNull(viewA);
        assertNotNull(viewB);
    }

    public void testMapHeights()
    {
        int heightA = mapFragmentA.getView().getMeasuredHeight();
        int heightB = mapFragmentB.getView().getMeasuredHeight();
        assertEquals(heightA, heightB, 1);
    }

    public void testMapWidths()
    {
        int widthA = mapFragmentA.getView().getMeasuredWidth();
        int widthB = mapFragmentB.getView().getMeasuredWidth();
        assertEquals(widthA, widthB, 1);
    }
}
