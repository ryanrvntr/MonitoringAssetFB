package com.example.ryanr.monitoringassetfb.Model;

public class PropertyModel {
    private String nama_property,alamat,keterangan,kode_pos, gambar;
    private double latitude, longitude;

    public PropertyModel() {
    }

    public PropertyModel(String nama_property, String alamat, String keterangan, String kode_pos, String gambar, double latitude, double longitude) {
        this.nama_property = nama_property;
        this.alamat = alamat;
        this.keterangan = keterangan;
        this.kode_pos = kode_pos;
        this.gambar = gambar;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNama_property() {
        return nama_property;
    }

    public void setNama_property(String nama_property) {
        this.nama_property = nama_property;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode_pos() {
        return kode_pos;
    }

    public void setKode_pos(String kode_pos) {
        this.kode_pos = kode_pos;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
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
