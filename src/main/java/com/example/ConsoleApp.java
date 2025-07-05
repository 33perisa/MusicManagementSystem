package com.example;

import com.example.model.Album;
import com.example.model.Izvodjac;
import com.example.model.TipIzvodjaca;
import com.example.service.AlbumService;
import com.example.service.IzvodjacService;
import com.example.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static IzvodjacService izvodjacService = new IzvodjacService();
    private static AlbumService albumService = new AlbumService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Odaberite operaciju:");
            System.out.println("1. Dodaj izvođača");
            System.out.println("2. Prikaži sve izvođače");
            System.out.println("3. Ažuriraj izvođača");
            System.out.println("4. Obriši izvođača");
            System.out.println("5. Prikaži solo izvođače");
            System.out.println("6. Prikaži izvođače formirane posle određene godine");
            System.out.println("7. Dodaj album");
            System.out.println("8. Prikaži albume po izdavačkoj kući");
            System.out.println("9. Prikaži diskografiju izvođača");
            System.out.println("0. Izlaz");

            int izbor = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (izbor) {
                case 1 -> dodajIzvodjaca();
                case 2 -> prikaziSveIzvodjace();
                case 3 -> azurirajIzvodjaca();
                case 4 -> obrisiIzvodjaca();
                case 5 -> prikaziSoloIzvodjace();
                case 6 -> prikaziIzvodjacePoGodiniFormacije();
                case 7 -> dodajAlbum();
                case 8 -> prikaziAlbumePoIzdavackojKuci();
                case 9 -> prikaziDiskografijuIzvodjaca();
                case 0 -> {
                    HibernateUtil.shutdown();
                    return;
                }
                default -> System.out.println("Nepoznata opcija, pokušajte ponovo.");
            }
        }
    }

    private static void dodajIzvodjaca() {
        System.out.println("Unesite naziv izvođača:");
        String naziv = scanner.nextLine();
        System.out.println("Unesite tip izvođača (SOLO/BEND):");
        TipIzvodjaca tip = TipIzvodjaca.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Unesite godinu formacije:");
        int godinaFormacije = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Unesite godinu raspada (ili pritisnite Enter ako je izvođač još aktivan):");
        String godinaRaspadaStr = scanner.nextLine();
        Integer godinaRaspada = godinaRaspadaStr.isEmpty() ? null : Integer.parseInt(godinaRaspadaStr);
        System.out.println("Unesite zvanični sajt izvođača (ili pritisnite Enter ako ne postoji):");
        String sajt = scanner.nextLine();

        Izvodjac izvodjac = new Izvodjac();
        izvodjac.setNaziv(naziv);
        izvodjac.setTip(tip);
        izvodjac.setGodinaFormacije(godinaFormacije);
        izvodjac.setGodinaRaspada(godinaRaspada);
        izvodjac.setSajt(sajt);

        izvodjacService.dodajIzvodjaca(izvodjac);
        System.out.println("Izvođač uspešno dodat.");
    }

    private static void prikaziSveIzvodjace() {
        List<Izvodjac> izvodjaci = izvodjacService.dobaviSveIzvodjace();
        for (Izvodjac izvodjac : izvodjaci) {
            System.out.println(izvodjac);
        }
    }

    private static void azurirajIzvodjaca() {
        System.out.println("Unesite ID izvođača za ažuriranje:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Izvodjac izvodjac = izvodjacService.dobaviSveIzvodjace().stream().filter(i -> i.getId() == id).findFirst().orElse(null);
        if (izvodjac == null) {
            System.out.println("Izvođač sa datim ID nije pronađen.");
            return;
        }

        System.out.println("Unesite novi naziv izvođača:");
        String naziv = scanner.nextLine();
        System.out.println("Unesite novi tip izvođača (SOLO/BEND):");
        TipIzvodjaca tip = TipIzvodjaca.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Unesite novu godinu formacije:");
        int godinaFormacije = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Unesite novu godinu raspada (ili pritisnite Enter ako je izvođač još aktivan):");
        String godinaRaspadaStr = scanner.nextLine();
        Integer godinaRaspada = godinaRaspadaStr.isEmpty() ? null : Integer.parseInt(godinaRaspadaStr);
        System.out.println("Unesite novi zvanični sajt izvođača (ili pritisnite Enter ako ne postoji):");
        String sajt = scanner.nextLine();

        izvodjac.setNaziv(naziv);
        izvodjac.setTip(tip);
        izvodjac.setGodinaFormacije(godinaFormacije);
        izvodjac.setGodinaRaspada(godinaRaspada);
        izvodjac.setSajt(sajt);

        izvodjacService.azurirajIzvodjaca(izvodjac);
        System.out.println("Izvođač uspešno ažuriran.");
    }

    private static void obrisiIzvodjaca() {
        System.out.println("Unesite ID izvođača za brisanje:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Izvodjac izvodjac = izvodjacService.dobaviSveIzvodjace().stream().filter(i -> i.getId() == id).findFirst().orElse(null);
        if (izvodjac == null) {
            System.out.println("Izvođač sa datim ID nije pronađen.");
            return;
        }

        izvodjacService.obrisiIzvodjaca(izvodjac);
        System.out.println("Izvođač uspešno obrisan.");
    }

    private static void prikaziSoloIzvodjace() {
        List<Izvodjac> soloIzvodjaci = izvodjacService.dobaviSveSoloIzvodjace();
        if (soloIzvodjaci.isEmpty()) {
            System.out.println("Nema solo izvođača.");
        } else {
            soloIzvodjaci.forEach(System.out::println);
        }
    }

    private static void prikaziIzvodjacePoGodiniFormacije() {
        System.out.println("Unesite godinu formiranja:");
        int godina = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Izvodjac> izvodjaci = izvodjacService.dobaviIzvodjacePosleGodine(godina);
        if (izvodjaci.isEmpty()) {
            System.out.println("Nema izvođača formiranih posle zadate godine.");
        } else {
            izvodjaci.forEach(System.out::println);
        }
    }

    private static void dodajAlbum() {
        System.out.println("Unesite naziv albuma:");
        String naziv = scanner.nextLine();
        System.out.println("Unesite izvođača (ID):");
        int izvodjacId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Izvodjac izvodjac = izvodjacService.dobaviSveIzvodjace().stream().filter(i -> i.getId() == izvodjacId).findFirst().orElse(null);
        if (izvodjac == null) {
            System.out.println("Izvođač sa datim ID nije pronađen.");
            return;
        }
        System.out.println("Unesite izdavačku kuću:");
        String izdavackaKuca = scanner.nextLine();

        Album album = new Album();
        album.setNaziv(naziv);
        album.setIzvodjac(izvodjac);
        album.setIzdavackaKuca(izdavackaKuca);

        albumService.dodajAlbum(album);
        System.out.println("Album uspešno dodat.");
    }

    private static void prikaziAlbumePoIzdavackojKuci() {
        System.out.println("Unesite izdavačku kuću:");
        String izdavackaKuca = scanner.nextLine();

        List<Object[]> rezultat = albumService.pronadjiAlbumeSaIzvodjacimaPoIzdavackojKuci(izdavackaKuca);
        if (rezultat.isEmpty()) {
            System.out.println("Nema albuma za zadatu izdavačku kuću.");
        } else {
            for (Object[] obj : rezultat) {
                Album album = (Album) obj[0];
                Izvodjac izvodjac = (Izvodjac) obj[1];
                System.out.println("Album: " + album.getNaziv() + ", Izvođač: " + izvodjac.getNaziv());
            }
        }
    }

    private static void prikaziDiskografijuIzvodjaca() {
        List<Izvodjac> izvodjaci = izvodjacService.dobaviSveIzvodjace();
        for (Izvodjac izvodjac : izvodjaci) {
            System.out.println("Izvođač: " + izvodjac.getNaziv());
            List<Album> albumi = izvodjac.getAlbumi();
            if (albumi.isEmpty()) {
                System.out.println("Nema albuma za ovog izvođača.");
            } else {
                albumi.forEach(album -> System.out.println("- Album: " + album.getNaziv() + ", Izdavačka kuća: " + album.getIzdavackaKuca()));
            }
        }
    }
}
