package com.model.view;

import java.util.List;

public class PegawaiFormView {
    private Integer idPegawai;
    private String nama;
    private String tglLahir;
    private String kota;
    private List<KotaView> kotaViewModels;

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


    public List<KotaView> getKotaViewModels() {
        return kotaViewModels;
    }

    public void setKotaViewModels(List<KotaView> kotaViewModels) {
        this.kotaViewModels = kotaViewModels;
    }

}
