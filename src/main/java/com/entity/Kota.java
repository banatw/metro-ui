package com.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idKota;
    private String nama;

    @OneToMany(mappedBy = "kota")
    private Set<Pegawai> pegawaiSet;

    public Set<Pegawai> getPegawaiSet() {
        return pegawaiSet;
    }

    public void setPegawaiSet(Set<Pegawai> pegawaiSet) {
        this.pegawaiSet = pegawaiSet;
    }

    public Kota() {
    }

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
