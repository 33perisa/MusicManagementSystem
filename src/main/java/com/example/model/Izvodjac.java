package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Izvodjac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naziv;
    @Enumerated(EnumType.STRING)
    private TipIzvodjaca tip;
    private int godinaFormacije;
    private Integer godinaRaspada;
    private String sajt;

    @OneToMany(mappedBy = "izvodjac", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albumi;

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setTip(TipIzvodjaca tip) {
        this.tip = tip;
    }

    public void setGodinaFormacije(int godinaFormacije) {
        this.godinaFormacije = godinaFormacije;
    }

    public void setGodinaRaspada(Integer godinaRaspada) {
        this.godinaRaspada = godinaRaspada;
    }

    public void setSajt(String sajt) {
        this.sajt = sajt;
    }

    public void setAlbumi(List<Album> albumi) {
        this.albumi = albumi;
    }

    public TipIzvodjaca getTip() {
        return tip;
    }

    public int getGodinaFormacije() {
        return godinaFormacije;
    }

    public Integer getGodinaRaspada() {
        return godinaRaspada;
    }

    public String getSajt() {
        return sajt;
    }

    public List<Album> getAlbumi() {
        return albumi;
    }

}
