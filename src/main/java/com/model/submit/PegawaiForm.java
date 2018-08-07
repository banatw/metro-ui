package com.model.submit;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class PegawaiForm {
    private Integer idPegawai;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;
    @NotEmpty(message = "tgl lahir tidak boleh kosong")
    private String tglLahir;
    @NotEmpty(message = "Kota tidak boleh kosong")
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

    public Integer getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Integer idPegawai) {
        this.idPegawai = idPegawai;
    }
}
