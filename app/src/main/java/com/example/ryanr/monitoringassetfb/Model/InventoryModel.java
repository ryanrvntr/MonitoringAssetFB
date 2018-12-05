package com.example.ryanr.monitoringassetfb.Model;

public class InventoryModel {
    private String nama,jenis_inventory,keterangan,merk,ruang,satuan,gambar;
    private int id_rayon,jumlah;
    public InventoryModel() {
    }

    public InventoryModel(String nama, int id_rayon, String jenis_inventory, int jumlah, String keterangan, String merk, String ruang, String satuan, String gambar) {
        this.nama = nama;
        this.id_rayon = id_rayon;
        this.jenis_inventory = jenis_inventory;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.merk = merk;
        this.ruang = ruang;
        this.satuan = satuan;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId_rayon() {
        return id_rayon;
    }

    public void setId_rayon(int id_rayon) {
        this.id_rayon = id_rayon;
    }

    public String getJenis_inventory() {
        return jenis_inventory;
    }

    public void setJenis_inventory(String jenis_inventory) {
        this.jenis_inventory = jenis_inventory;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
