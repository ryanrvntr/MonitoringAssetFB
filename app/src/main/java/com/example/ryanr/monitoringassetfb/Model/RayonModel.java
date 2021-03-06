package com.example.ryanr.monitoringassetfb.Model;

import java.io.Serializable;

public class RayonModel implements Serializable {
    String nama_rayon,alamat;

    public RayonModel() {
    }

    public RayonModel(String nama_rayon, String alamat) {
        this.nama_rayon = nama_rayon;
        this.alamat = alamat;
    }

    public String getNama_rayon() {
        return nama_rayon;
    }

    public void setNama_rayon(String nama_rayon) {
        this.nama_rayon = nama_rayon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return " "+nama_rayon+"\n"+
                " "+alamat;
    }
}
