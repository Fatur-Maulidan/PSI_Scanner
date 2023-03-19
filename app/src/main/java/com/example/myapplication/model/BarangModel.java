package com.example.myapplication.model;

import java.util.List;

public class BarangModel {
    private List<Barang> result;

    public List<Barang> getResult() {
        return result;
    }

    public void setResult(List<Barang> result) {
        this.result = result;
    }

    public static class Barang {
        private String kode;
        private String nama;
        private int harga;
        private int stok;

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }
    }
}
