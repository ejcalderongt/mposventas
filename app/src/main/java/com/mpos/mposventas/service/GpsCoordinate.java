package com.mpos.mposventas.service;
public class GpsCoordinate {

    private double latitude;
    private double longitude;

    public GpsCoordinate() {
        // Empty constructor for deserialization
    }

    public GpsCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}