package com.example.model;

import java.util.List;

public class Discography {
    private Izvodjac izvodjac;
    private List<Album> albumi;

    public Discography(Izvodjac izvodjac, List<Album> albumi) {
        this.izvodjac = izvodjac;
        this.albumi = albumi;
    }


    @Override
    public String toString() {
        return "Discography{" +
                "izvodjac=" + izvodjac +
                ", albumi=" + albumi +
                '}';
    }
}
