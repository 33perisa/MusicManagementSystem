package com.example.service;

import com.example.dao.AlbumDAO;
import com.example.model.Album;

import java.util.List;

public class AlbumService {
    private AlbumDAO albumDAO = new AlbumDAO();

    public void dodajAlbum(Album album) {
        albumDAO.dodajAlbum(album);
    }

    public List<Album> dobaviSveAlbume() {
        return albumDAO.dobaviSveAlbume();
    }

    public List<Object[]> pronadjiAlbumeSaIzvodjacimaPoIzdavackojKuci(String izdavackaKuca) {
        return albumDAO.pronadjiAlbumeSaIzvodjacimaPoIzdavackojKuci(izdavackaKuca);
    }
}
