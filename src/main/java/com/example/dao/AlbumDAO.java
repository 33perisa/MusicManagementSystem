package com.example.dao;

import com.example.model.Album;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDAO {

    public void dodajAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Album> dobaviSveAlbume() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Album", Album.class).list();
        }
    }

    public List<Object[]> pronadjiAlbumeSaIzvodjacimaPoIzdavackojKuci(String izdavackaKuca) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select a, i from Album a join a.izvodjac i where a.izdavackaKuca = :izdavackaKuca")
                    .setParameter("izdavackaKuca", izdavackaKuca)
                    .list();
        }
    }
}
