package com.example.model;

import java.util.Objects;

public class Album {
    private int id;
    private String naziv;
    private int godinaIzdanja;
    private String izdavackaKuca;
    private Izvodjac izvodjac;

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getIzdavackaKuca() {
        return izdavackaKuca;
    }

    public void setIzdavackaKuca(String izdavackaKuca) {
        this.izdavackaKuca = izdavackaKuca;
    }

    public Izvodjac getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(Izvodjac izvodjac) {
        this.izvodjac = izvodjac;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, godinaIzdanja, izdavackaKuca, izvodjac);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                godinaIzdanja == album.godinaIzdanja &&
                Objects.equals(naziv, album.naziv) &&
                Objects.equals(izdavackaKuca, album.izdavackaKuca) &&
                Objects.equals(izvodjac, album.izvodjac);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", godinaIzdanja=" + godinaIzdanja +
                ", izdavackaKuca='" + izdavackaKuca + '\'' +
                ", izvodjac=" + izvodjac +
                '}';
    }
}
