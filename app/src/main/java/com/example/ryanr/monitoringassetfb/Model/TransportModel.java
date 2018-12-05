package com.example.ryanr.monitoringassetfb.Model;

public class TransportModel {
    String gambar,jenis,keterangan,nama_kendaraan,nomor_polisi,pemilik,pengguna,rayonId,stand_awal,stand_akhir;

    public TransportModel() {
    }

    public TransportModel(String gambar, String jenis, String keterangan, String nama_kendaraan, String nomor_polisi, String pemilik, String pengguna, String rayonId, String stand_awal, String stand_akhir) {
        this.gambar = gambar;
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.nama_kendaraan = nama_kendaraan;
        this.nomor_polisi = nomor_polisi;
        this.pemilik = pemilik;
        this.pengguna = pengguna;
        this.rayonId = rayonId;
        this.stand_awal = stand_awal;
        this.stand_akhir = stand_akhir;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama_kendaraan() {
        return nama_kendaraan;
    }

    public void setNama_kendaraan(String nama_kendaraan) {
        this.nama_kendaraan = nama_kendaraan;
    }

    public String getNomor_polisi() {
        return nomor_polisi;
    }

    public void setNomor_polisi(String nomor_polisi) {
        this.nomor_polisi = nomor_polisi;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public String getRayonId() {
        return rayonId;
    }

    public void setRayonId(String rayonId) {
        this.rayonId = rayonId;
    }

    public String getStand_awal() {
        return stand_awal;
    }

    public void setStand_awal(String stand_awal) {
        this.stand_awal = stand_awal;
    }

    public String getStand_akhir() {
        return stand_akhir;
    }

    public void setStand_akhir(String stand_akhir) {
        this.stand_akhir = stand_akhir;
    }
}
