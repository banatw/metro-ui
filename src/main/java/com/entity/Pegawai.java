package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pegawai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPegawai;
    private String nama;
    private Date tanggalLahir;
    @ManyToOne()
    @JoinColumn(name = "idKota")
    private Kota kota;

    public Pegawai() {
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

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }
}
