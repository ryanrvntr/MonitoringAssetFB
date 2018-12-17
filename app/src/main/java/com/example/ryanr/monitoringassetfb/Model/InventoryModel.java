package com.example.ryanr.monitoringassetfb.Model;

public class InventoryModel {
    private String nama,jenis_inventory,keterangan,merk,ruang,satuan,gambar,rayonId;
    private int jumlah;
    public InventoryModel() {
    }

    public InventoryModel(String nama, String jenis_inventory, String keterangan, String merk, String ruang, String satuan, String gambar,String rayonId, int jumlah) {
        this.nama = nama;
        this.jenis_inventory = jenis_inventory;
        this.keterangan = keterangan;
        this.merk = merk;
        this.ruang = ruang;
        this.satuan = satuan;
        this.gambar = gambar;
        this.rayonId = rayonId;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getrayonId() {
        return rayonId;
    }

    public void setrayonId(String rayonId) {
        this.rayonId = rayonId;
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

    @Override
    public String toString() {
        return "InventoryModel{" +
                "nama='" + nama + '\'' +
                ", jenis_inventory='" + jenis_inventory + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", merk='" + merk + '\'' +
                ", ruang='" + ruang + '\'' +
                ", satuan='" + satuan + '\'' +
                ", gambar='" + gambar + '\'' +
                ", rayonId=" + rayonId +
                ", jumlah=" + jumlah +
                '}';
    }
}
