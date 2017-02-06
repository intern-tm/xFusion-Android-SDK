package com.example.satku.xfusion_platformapp;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.teramatrix.library.IoTSDK;
import com.teramatrix.library.exception.UnAuthorizedAccess;
import com.teramatrix.library.receivers.LocationUpdateReceiver;
import com.teramatrix.library.service.FusedLocationProvider;
public class MainActivity extends AppCompatActivity implements LocationUpdateReceiver.INotifyLocationUpdates,View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    LocationUpdateReceiver locationUpdateReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
//Initialize IoT Sdk Servcies
        IoTSDK ioTSdk = IoTSDK.getInstance(MainActivity.this);
//Configure Location Tracking
        ioTSdk.setLocationTrackingEnabled(true);
        ioTSdk.setLocationUpdateInterval(20);
        ioTSdk.setLocationTrackingServiceExecuteMode(IoTSDK.SERVICE_EXECUTE_MODE_ALWAYS);
//Configure device|app servcie data API
        ioTSdk.setDeviceParametersToTrack(IoTSDK.DEVICE_PARAMETERS_PHONE,
                IoTSDK.DEVICE_PARAMETERS_NETWORKS, IoTSDK.DEVICE_PARAMETERS_TRAFIC);
        ioTSdk.setDevceiDataAPIUpdateInterval(30);
        try {
            ioTSdk.initService();
        } catch (UnAuthorizedAccess unAuthorizedAccess) {
            unAuthorizedAccess.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super . onResume ();
        locationUpdateReceiver = IoTSDK.registerLocationUpdateReeiver(this);
    }
    @ Override
    protected void onPause() {
        super . onPause ();
        IoTSDK.unregisterLocationUpdateReeiver(this,locationUpdateReceiver);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Toast.makeText(getApplicationContext(), " location ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


    }

    @Override
    public void onClick(View view) {

    }
}



