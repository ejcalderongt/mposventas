package com.mpos.mposventas.service;
import java.util.Date;

public class GpsCoordinate {
    private int idRegistro = 0;
    private String dispositivo = "";;
    private double coordenada_X = 0;
    private double coordenada_Y = 0;
    private Date fecha = new Date();
    private int idusuario =0;

    public GpsCoordinate() {
        // Empty constructor for deserialization
    }
    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public double getLatitude() {
        return coordenada_X;
    }

    public void setLatitude(double latitude) {
        this.coordenada_X = latitude;
    }

    public double getLongitude() {
        return coordenada_Y;
    }

    public void setLongitude(double longitude) {
        this.coordenada_Y = longitude;
    }

    public double getCoordenada_X() {
        return coordenada_X;
    }

    public void setCoordenada_X(double coordenada_X) {
        this.coordenada_X = coordenada_X;
    }

    public double getCoordenada_Y() {
        return coordenada_Y;
    }

    public void setCoordenada_Y(double coordenada_Y) {
        this.coordenada_Y = coordenada_Y;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}