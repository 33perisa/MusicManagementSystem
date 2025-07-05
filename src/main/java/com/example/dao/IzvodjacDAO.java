package com.example.dao;

import com.example.model.Izvodjac;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IzvodjacDAO {

    public void dodajIzvodjaca(Izvodjac izvodjac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(izvodjac);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Izvodjac> dobaviSveIzvodjace() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Izvodjac", Izvodjac.class).list();
        }
    }

    public void azurirajIzvodjaca(Izvodjac izvodjac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(izvodjac);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void obrisiIzvodjaca(Izvodjac izvodjac) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(izvodjac);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Izvodjac> dobaviSveSoloIzvodjace() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Izvodjac where tip = 'SOLO'", Izvodjac.class).list();
        }
    }

    public List<Izvodjac> dobaviIzvodjacePosleGodine(int godina) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Izvodjac where godinaFormacije > :godina", Izvodjac.class)
                    .setParameter("godina", godina)
                    .list();
        }
    }
}
