package com.mpos.mposventas.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Define the endpoint for storing GPS coordinates
    @POST("store_gps_coordinates") // Replace with your actual API endpoint
    Call<Void> postLocation(@Body GpsCoordinate gpsCoordinate);
}

