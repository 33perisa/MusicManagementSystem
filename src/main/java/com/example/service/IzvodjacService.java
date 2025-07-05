package com.example.service;

import com.example.dao.IzvodjacDAO;
import com.example.model.Izvodjac;

import java.util.List;

public class IzvodjacService {
    private IzvodjacDAO izvodjacDAO = new IzvodjacDAO();

    public void dodajIzvodjaca(Izvodjac izvodjac) {
        izvodjacDAO.dodajIzvodjaca(izvodjac);
    }

    public List<Izvodjac> dobaviSveIzvodjace() {
        return izvodjacDAO.dobaviSveIzvodjace();
    }

    public void azurirajIzvodjaca(Izvodjac izvodjac) {
        izvodjacDAO.azurirajIzvodjaca(izvodjac);
    }

    public void obrisiIzvodjaca(Izvodjac izvodjac) {
        izvodjacDAO.obrisiIzvodjaca(izvodjac);
    }

    public List<Izvodjac> dobaviSveSoloIzvodjace() {
        return izvodjacDAO.dobaviSveSoloIzvodjace();
    }

    public List<Izvodjac> dobaviIzvodjacePosleGodine(int godina) {
        return izvodjacDAO.dobaviIzvodjacePosleGodine(godina);
    }
}
