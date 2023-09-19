package com.mpos.mposventas.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

import com.mpos.mposventas.base.appGlobals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GPSService extends Service {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private ApiService apiService;
    private appGlobals gl= new appGlobals();
    private Location lastSavedLocation;
    private long lastSaveTimestamp = 0; // Initialize with 0
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(gl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start listening to GPS updates
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                long currentTimestamp = System.currentTimeMillis();

                // Save the location because the distance change is more than 10 meters
                if (lastSavedLocation == null || location.distanceTo(lastSavedLocation) >= 10) {
                    // Check if more than 5 minutes (300,000 milliseconds) have passed
                    if((currentTimestamp - lastSaveTimestamp >= 300000) || (lastSaveTimestamp == 0)) {
                        // Save the location because more than 5 minutes have passed
                        GpsCoordinate gpsCoordinate= new GpsCoordinate();
                        gpsCoordinate.setDispositivo("");
                        gpsCoordinate.setLatitude(location.getLatitude());
                        gpsCoordinate.setLongitude(location.getLatitude());
                        gpsCoordinate.setIdusuario(gl.IdUsuario);
                        postLocationData(gpsCoordinate);
                        // Update the timestamp
                        lastSaveTimestamp = currentTimestamp;
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, locationListener);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Stop GPS updates when the service is stopped
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void postLocationData(GpsCoordinate gpsCoordinate) {

        // Create a POST request to your API with latitude and longitude
        Call<Void> call = apiService.postLocation(gpsCoordinate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("GPSService", "Location data posted successfully");
                } else {
                    Log.e("GPSService", "Failed to post location data");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("GPSService", "Network error while posting location data", t);
            }
        });
    }
}
