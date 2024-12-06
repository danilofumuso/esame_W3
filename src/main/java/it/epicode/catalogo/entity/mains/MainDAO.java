package it.epicode.catalogo.entity.mains;


import it.epicode.catalogo.dao.LibroDAO;
import it.epicode.catalogo.dao.PrestitoDAO;
import it.epicode.catalogo.dao.RivistaDAO;
import it.epicode.catalogo.dao.UtenteDAO;
import it.epicode.catalogo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainDAO {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        // Inserimento libri al database

        LibroDAO libroDAO = new LibroDAO(em);
        List<Libro> listaLibri = new ArrayList<>();

        Libro libro = new Libro();
        libro.setCodiceISBN("101");
        libro.setTitolo("Il Signore degli Anelli: La Compagnia dell'Anello");
        libro.setAnnoDiPubblicazione(1954);
        libro.setNumeroPagine(423);
        libro.setAutore("J.R.R. Tolkien");
        libro.setGenere("Fantasy epico");

        Libro libro2 = new Libro();
        libro2.setCodiceISBN("102");
        libro2.setTitolo("Harry Potter e la Pietra Filosofale");
        libro2.setAnnoDiPubblicazione(1997);
        libro2.setNumeroPagine(223);
        libro2.setAutore("J.K. Rowling");
        libro2.setGenere("Fantasy");

        Libro libro3 = new Libro();
        libro3.setCodiceISBN("103");
        libro3.setTitolo("Eragon");
        libro3.setAnnoDiPubblicazione(2002);
        libro3.setNumeroPagine(503);
        libro3.setAutore("Christopher Paolini");
        libro3.setGenere("Fantasy epico");

        Libro libro4 = new Libro();
        libro4.setCodiceISBN("104");
        libro4.setTitolo(" La Storia Infinita");
        libro4.setAnnoDiPubblicazione(1979);
        libro4.setNumeroPagine(448);
        libro4.setAutore("Michael Ende");
        libro4.setGenere("Fantasy per ragazzi, avventura");

        Libro libro5 = new Libro();
        libro5.setCodiceISBN("105");
        libro5.setTitolo("Mistborn: L’Ultimo Impero");
        libro5.setAnnoDiPubblicazione(2006);
        libro5.setNumeroPagine(672);
        libro5.setAutore("Brandon Sanderson");
        libro5.setGenere("Fantasy epico");

        Libro libro6 = new Libro();
        libro6.setCodiceISBN("106");
        libro6.setTitolo("The Name of the Wind");
        libro6.setAnnoDiPubblicazione(2007);
        libro6.setNumeroPagine(662);
        libro6.setAutore("Patrick Rothfuss");
        libro6.setGenere("Fantasy epico");

        Libro libro7 = new Libro();
        libro7.setCodiceISBN("107");
        libro7.setTitolo("The Lies of Locke Lamora");
        libro7.setAnnoDiPubblicazione(2006);
        libro7.setNumeroPagine(722);
        libro7.setAutore("Scott Lynch");
        libro7.setGenere("Fantasy epico");

        Libro libro8 = new Libro();
        libro8.setCodiceISBN("108");
        libro8.setTitolo("The Blade Itself");
        libro8.setAnnoDiPubblicazione(2006);
        libro8.setNumeroPagine(515);
        libro8.setAutore("Joe Abercrombie");
        libro8.setGenere("Fantasy grimdark");

        Libro libro9 = new Libro();
        libro9.setCodiceISBN("109");
        libro9.setTitolo("A Game of Thrones");
        libro9.setAnnoDiPubblicazione(1996);
        libro9.setNumeroPagine(694);
        libro9.setAutore("George R.R. Martin");
        libro9.setGenere("Fantasy epico");

        Libro libro10 = new Libro();
        libro10.setCodiceISBN("110");
        libro10.setTitolo("The Priory of the Orange Tree");
        libro10.setAnnoDiPubblicazione(2019);
        libro10.setNumeroPagine(848);
        libro10.setAutore("Samantha Shannon");
        libro10.setGenere("Fantasy epico");

        listaLibri.add(libro);
        listaLibri.add(libro2);
        listaLibri.add(libro3);
        listaLibri.add(libro4);
        listaLibri.add(libro5);
        listaLibri.add(libro6);
        listaLibri.add(libro7);
        listaLibri.add(libro8);
        listaLibri.add(libro9);
        listaLibri.add(libro10);
        libroDAO.insertAll(listaLibri);

        // Inserimento riviste al database

        RivistaDAO rivistaDAO = new RivistaDAO(em);
        List<Rivista> listaRiviste = new ArrayList<>();

        Rivista rivista = new Rivista();
        rivista.setCodiceISBN("111");
        rivista.setTitolo("National Geographic - Special Edition");
        rivista.setAnnoDiPubblicazione(2022);
        rivista.setNumeroPagine(150);
        rivista.setPeriodicita(Periodicita.MENSILE);

        Rivista rivista2 = new Rivista();
        rivista2.setCodiceISBN("112");
        rivista2.setTitolo("The New Yorker - Fantasy Fiction Issue");
        rivista2.setAnnoDiPubblicazione(2021);
        rivista2.setNumeroPagine(120);
        rivista2.setPeriodicita(Periodicita.SETTIMANALE);

        Rivista rivista3 = new Rivista();
        rivista3.setCodiceISBN("113");
        rivista3.setTitolo("Fantasy & Science Fiction Magazine");
        rivista3.setAnnoDiPubblicazione(2023);
        rivista3.setNumeroPagine(100);
        rivista3.setPeriodicita(Periodicita.SEMESTRALE);

        Rivista rivista4 = new Rivista();
        rivista4.setCodiceISBN("114");
        rivista4.setTitolo("Locus: The Magazine of the Science Fiction and Fantasy Field");
        rivista4.setAnnoDiPubblicazione(2023);
        rivista4.setNumeroPagine(96);
        rivista4.setPeriodicita(Periodicita.SEMESTRALE);

        Rivista rivista5 = new Rivista();
        rivista5.setCodiceISBN("115");
        rivista5.setTitolo("Time Magazine - Creativity Issue");
        rivista5.setAnnoDiPubblicazione(2022);
        rivista5.setNumeroPagine(110);
        rivista5.setPeriodicita(Periodicita.MENSILE);

        Rivista rivista6 = new Rivista();
        rivista6.setCodiceISBN("116");
        rivista6.setTitolo("Scientific American - Future Technologies");
        rivista6.setAnnoDiPubblicazione(2023);
        rivista6.setNumeroPagine(128);
        rivista6.setPeriodicita(Periodicita.SETTIMANALE);

        Rivista rivista7 = new Rivista();
        rivista7.setCodiceISBN("117");
        rivista7.setTitolo("Forbes - Innovation Issue");
        rivista7.setAnnoDiPubblicazione(2022);
        rivista7.setNumeroPagine(98);
        rivista7.setPeriodicita(Periodicita.SETTIMANALE);

        Rivista rivista8 = new Rivista();
        rivista8.setCodiceISBN("118");
        rivista8.setTitolo("Wired - The AI Revolution");
        rivista8.setAnnoDiPubblicazione(2023);
        rivista8.setNumeroPagine(144);
        rivista8.setPeriodicita(Periodicita.MENSILE);

        Rivista rivista9 = new Rivista();
        rivista9.setCodiceISBN("119");
        rivista9.setTitolo("Nature - Special Climate Edition");
        rivista9.setAnnoDiPubblicazione(2021);
        rivista9.setNumeroPagine(112);
        rivista9.setPeriodicita(Periodicita.SEMESTRALE);

        Rivista rivista10 = new Rivista();
        rivista10.setCodiceISBN("120");
        rivista10.setTitolo("Popular Mechanics - Breakthroughs 2022");
        rivista10.setAnnoDiPubblicazione(2022);
        rivista10.setNumeroPagine(102);
        rivista10.setPeriodicita(Periodicita.MENSILE);

        listaRiviste.add(rivista);
        listaRiviste.add(rivista2);
        listaRiviste.add(rivista3);
        listaRiviste.add(rivista4);
        listaRiviste.add(rivista5);
        listaRiviste.add(rivista6);
        listaRiviste.add(rivista7);
        listaRiviste.add(rivista8);
        listaRiviste.add(rivista9);
        listaRiviste.add(rivista10);
        rivistaDAO.insertAll(listaRiviste);

        // Inserimento Utenti al database

        UtenteDAO utenteDAO = new UtenteDAO(em);

        Utente utente = new Utente();
        utente.setNome("Danilo");
        utente.setCognome("Fumuso");
        utente.setDataDiNascita(LocalDate.of(1991, 2, 6));
        utente.setNumeroDiTessera("T-001");

        Utente utente2 = new Utente();
        utente2.setNome("Riccardo");
        utente2.setCognome("Santilli");
        utente2.setDataDiNascita(LocalDate.of(1991, 5, 16));
        utente2.setNumeroDiTessera("T-002");

        utenteDAO.save(utente);
        utenteDAO.save(utente2);

        // Inserimento prestiti al database

        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        Prestito prestito = new Prestito();
        prestito.setDataInizioPrestito(LocalDate.now());
        prestito.setDataRestituzionePrevista(prestito.getDataInizioPrestito().plusDays(30));
        prestito.setUtente(utente);
        prestito.setElementoPrestato(libro);

        Prestito prestito2 = new Prestito();
        prestito2.setDataInizioPrestito(LocalDate.now());
        prestito2.setDataRestituzionePrevista(prestito2.getDataInizioPrestito().plusDays(30));
        prestito2.setUtente(utente);
        prestito2.setElementoPrestato(libro2);

        Prestito prestito3 = new Prestito();
        prestito3.setDataInizioPrestito(LocalDate.now());
        prestito3.setDataRestituzionePrevista(prestito3.getDataInizioPrestito().plusDays(30));
        prestito3.setUtente(utente2);
        prestito3.setElementoPrestato(libro5);

        Prestito prestito4 = new Prestito();
        prestito4.setDataInizioPrestito(LocalDate.now());
        prestito4.setDataRestituzionePrevista(prestito4.getDataInizioPrestito().plusDays(30));
        prestito4.setUtente(utente2);
        prestito4.setElementoPrestato(rivista);

        prestitoDAO.save(prestito);
        prestitoDAO.save(prestito2);
        prestitoDAO.save(prestito3);
        prestitoDAO.save(prestito4);
    }
}
