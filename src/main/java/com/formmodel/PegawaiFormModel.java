package com.formmodel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class PegawaiFormModel {
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;
    @NotNull(message = "tgl lahir tidak boleh kosong")
    private String tglLahir;
    @NotNull(message = "Kota tdk boleh kosong")
    private String kota;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
