package it.epicode.catalogo.entity.mains;

import it.epicode.catalogo.dao.CatalogoDAO;
import it.epicode.catalogo.dao.LibroDAO;
import it.epicode.catalogo.dao.UtenteDAO;
import it.epicode.catalogo.entity.*;
import it.epicode.catalogo.entity.exceptions.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.*;

public class Archivio {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
    public static EntityManager em = emf.createEntityManager();
    public static UtenteDAO utenteDAO = new UtenteDAO(em);

    public static final Logger LOGGER = LoggerFactory.getLogger(Archivio.class);
    public static Scanner scanner = new Scanner(System.in);

    public static void aggiungiAlCatalogo() throws DuplicatoException {
        boolean aggiungi = true;

        while (aggiungi) {

            System.out.println("Cosa vuoi aggiungere al catalogo? Libro o Rivista?");
            String tipo = scanner.nextLine().toLowerCase();

            switch (tipo) {

                case "libro":
                    try {
                        System.out.println("Inserisci il codice ISBN del libro");
                        String libroIsbn = scanner.nextLine();
                        if (CatalogoDAO.findByISBN(libroIsbn) != null) {
                            throw new DuplicatoException("Esiste già un elemento con questo codice ISBN, riprova!");
                        }
                        System.out.println("Inserisci il titolo del libro");
                        String titoloLibro = scanner.nextLine();
                        int annoPubblicazioneLibro = 0;
                        try {
                            System.out.println("Inserisci l'anno di pubblicazione del libro");
                            annoPubblicazioneLibro = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Errore: Inserisci solo numeri in questo campo!");
                            scanner.nextLine();
                            continue;
                        }
                        int numeroPagineLibro = 0;
                        try {
                            System.out.println("Inserisci il numero di pagine del libro");
                            numeroPagineLibro = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Errore: Inserisci solo numeri in questo campo!");
                            scanner.nextLine();
                            continue;
                        }
                        System.out.println("Inserisci l'autore del libro");
                        String autore = scanner.nextLine();
                        System.out.println("Inserisci il genere del libro");
                        String genere = scanner.nextLine();

                        Libro libro = new Libro();


                        libro.setCodiceISBN(libroIsbn);
                        libro.setTitolo(titoloLibro);
                        libro.setAnnoDiPubblicazione(annoPubblicazioneLibro);
                        libro.setNumeroPagine(numeroPagineLibro);
                        libro.setAutore(autore);
                        libro.setGenere(genere);

                        CatalogoDAO.save(libro);
                        System.out.println("Libro aggiunto correttamente!");

                    } catch (DuplicatoException e) {
                        LOGGER.error(e::getMessage);
                        continue;
                    }
                    break;

                case "rivista":
                    try {
                        System.out.println("Inserisci il codice ISBN della rivista");
                        String rivistaIsbn = scanner.nextLine();
                        if (CatalogoDAO.findByISBN(rivistaIsbn) != null) {
                            throw new DuplicatoException("Esiste già un elemento con questo codice ISBN, riprova!");
                        }
                        System.out.println("Inserisci il titolo della rivista");
                        String titoloRivista = scanner.nextLine();
                        int annoPubblicazioneRivista = 0;
                        try {
                            System.out.println("Inserisci l'anno di pubblicazione della rivista");
                            annoPubblicazioneRivista = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Errore: Inserisci solo numeri in questo campo!");
                            scanner.nextLine();
                            continue;
                        }
                        int numeroPagineRivista = 0;
                        try {
                            System.out.println("Inserisci il numero di pagine della rivista");
                            numeroPagineRivista = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Errore: Inserisci solo numeri in questo campo!");
                            scanner.nextLine();
                            continue;
                        }
                        System.out.println("Inserisci la periodicità della rivista: settimanale, mensile o annuale?");
                        String periodicita = scanner.nextLine().toUpperCase();
                        if (!periodicita.equals("SETTIMANALE") && !periodicita.equals("MENSILE") && !periodicita.equals("SEMESTRALE")) {
                            throw new IllegalArgumentException("Periodicità non valida!");
                        }

                        Rivista rivista = new Rivista();
                        rivista.setCodiceISBN(rivistaIsbn);
                        rivista.setTitolo(titoloRivista);
                        rivista.setAnnoDiPubblicazione(annoPubblicazioneRivista);
                        rivista.setNumeroPagine(numeroPagineRivista);
                        rivista.setPeriodicita(Periodicita.valueOf(periodicita));

                        CatalogoDAO.save(rivista);
                        System.out.println("Rivista aggiunta correttamente!");

                    } catch (DuplicatoException | IllegalArgumentException e) {
                        LOGGER.error(e::getMessage);
                        continue;
                    }
                    break;

                default:
                    System.out.println("Tipologia di testo non riconosciuta, inseriscine una valida!");
                    continue;
            }

            System.out.print("Vuoi aggiungere un altro elemento al catalogo? (si/no): ");
            String risposta = scanner.nextLine().toLowerCase();
            if (risposta.equals("no")) {
                aggiungi = false;
            }
        }
    }

    public static void rimuoviConISBN(String codiceIsbn) throws IsbnNonTrovatoException {
        Catalogo pubblicazione = CatalogoDAO.removeFromCatalogo(codiceIsbn);

        if (pubblicazione != null) {
            System.out.println("Pubblicazione con ISBN " + codiceIsbn + " rimossa correttamente.");
        } else {
            throw new IsbnNonTrovatoException("Nessuna pubblicazione trovata da rimuovere con questo codice ISBN: " + codiceIsbn);
        }
    }

    public static void ricercaConISBN(String codiceIsbn) throws IsbnNonTrovatoException {
        Catalogo pubblicazione = CatalogoDAO.findByISBN(codiceIsbn);

        if (pubblicazione != null) {
            pubblicazione.mostraDettagli();
        } else {
            throw new IsbnNonTrovatoException("Nessuna pubblicazione trovata con questo codice ISBN: " + codiceIsbn);
        }
    }

    public static void ricercaConAnnoPubblicazione(int annoPubblicazione) throws AnnoPubblicazioneException {
        List<Catalogo> pubblicazioni = CatalogoDAO.findByAnnoDiPubblicazione(annoPubblicazione);

        if (!pubblicazioni.isEmpty()) {
            pubblicazioni.forEach(pubblicazione -> {
                pubblicazione.mostraDettagli();
                System.out.println();
            });
        } else {
            throw new AnnoPubblicazioneException("Nessuna pubblicazione trovata per l'anno di pubblicazione: " + annoPubblicazione);
        }
    }

    public static void ricercaPerAutore(String autore) throws AutoreNonTrovatoException {
        List<Catalogo> libri = CatalogoDAO.findByAutore(autore);

        if (!libri.isEmpty()) {
            libri.forEach(libro -> {
                libro.mostraDettagli();
                System.out.println();
            });
        } else {
            throw new AutoreNonTrovatoException("Nessun libro trovato con autore: " + autore);
        }
    }

    public static void ricercaPerTitolo(String titolo) throws TitoloNonTrovatoException {
        List<Catalogo> pubblicazioni = CatalogoDAO.findByPartialTitolo(titolo);

        if (!pubblicazioni.isEmpty()) {
            pubblicazioni.forEach(pubblicazione -> {
                pubblicazione.mostraDettagli();
                System.out.println();
            });
        } else {
            throw new TitoloNonTrovatoException("Nessuna pubblicazione trovata con il titolo: " + titolo);
        }

    }

    public static void ricercaPrestitiUtente(String numeroDiTessera) throws PrestitiException {
        boolean utenteEsistente = utenteDAO.findAll().stream()
                .anyMatch(utente -> utente.getNumeroDiTessera().equals(numeroDiTessera));

        if (utenteEsistente) {
            List<Catalogo> prestiti = CatalogoDAO.findPrestitiOfUtente(numeroDiTessera);
            if (!prestiti.isEmpty()) {
                System.out.println("I tuoi prestiti: ");
                prestiti.forEach(prestito -> {
                    prestito.mostraDettagli();
                    System.out.println();
                });
            } else {
                throw new PrestitiException("Nessun prestito per la tessera: " + numeroDiTessera);
            }
        } else {
            throw new PrestitiException("La tessera con numero di tessera: " + numeroDiTessera + ", non esiste!");
        }
    }

    public static void ricercaPrestitiScaduti() throws PrestitiException {
        List<Catalogo> prestitiScaduti = CatalogoDAO.findPrestitiScaduti();

        if (!prestitiScaduti.isEmpty()) {
            System.out.println("Questi prestiti sono scaduti: ");
            prestitiScaduti.forEach(prestito -> {
                prestito.mostraDettagli();
                System.out.println();
            });
        } else {
            throw new PrestitiException("Nessun prestito scaduto!");
        }

    }

    public static void aggiornaTestoPresenteInCatalogo(String codiceIsbn) throws IsbnNonTrovatoException {
        Catalogo pubblicazione = CatalogoDAO.findByISBN(codiceIsbn);


        System.out.println("Cosa vuoi aggiornare?");
        System.out.println("1- Titolo testo");
        System.out.println("2- Anno Pubblicazione");
        System.out.println("3- Numero di pagine testo");
        if (pubblicazione instanceof Libro) {
            System.out.println("4- Autore");
            System.out.println("5- Genere");
        } else if (pubblicazione instanceof Rivista) {
            System.out.println("0. Periodicità");
        }

        int modifica = scanner.nextInt();
        scanner.nextLine();

        switch (modifica) {
            case 1:
                System.out.println("Inserisci un nuovo titolo");
                pubblicazione.setTitolo(scanner.nextLine());
                break;
            case 2:
                System.out.println("Inserisci un nuovo anno di pubblicazione");
                pubblicazione.setAnnoDiPubblicazione(scanner.nextInt());
                scanner.nextLine();
                break;
            case 3:
                System.out.println("Inserisci nuovo numero di pagine");
                pubblicazione.setNumeroPagine(scanner.nextInt());
                scanner.nextLine();
                break;
            case 4:
                if (pubblicazione instanceof Libro) {
                    Libro libro = (Libro) pubblicazione;
                    System.out.println("Inserisci nuovo autore");
                    libro.setAutore(scanner.nextLine());
                }
                break;
            case 5:
                if (pubblicazione instanceof Libro) {
                    Libro libro = (Libro) pubblicazione;
                    System.out.println("Inserisci nuovo genere");
                    libro.setGenere(scanner.nextLine());
                }
                break;
            case 0:
                if (pubblicazione instanceof Rivista) {
                    Rivista rivista = (Rivista) pubblicazione;
                    System.out.println("Scegli nuova periodicità (1-SETTIMANALE, 2-MENSILE, 3-SEMESTRALE)");
                    String periodicitaScelta = scanner.nextLine().toUpperCase();
                    rivista.setPeriodicita(Periodicita.valueOf(periodicitaScelta));
                }
        }

        CatalogoDAO.update(pubblicazione);
        System.out.println("Testo aggiornato con successo!");
    }

    public static void mostraStatisticheCatalogo() {

        List<Catalogo> catalogo = CatalogoDAO.findAll();

        if (catalogo.isEmpty()) {
            System.out.println("Il catalogo è vuoto.");
            return;
        }

        long numeroLibri = catalogo.stream()
                .filter(testo -> testo instanceof Libro)
                .count();

        long numeroRiviste = catalogo.stream()
                .filter(testo -> testo instanceof Rivista)
                .count();

        Catalogo testoPiuLungo = catalogo.stream()
                .max(Comparator.comparingInt(Catalogo::getNumeroPagine))
                .orElse(null);

        double mediaPagine = (int) Math.round(catalogo.stream()
                .mapToInt(Catalogo::getNumeroPagine)
                .average()
                .orElse(0.0));

        System.out.println("Statistiche del catalogo:");

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        System.out.println("Elemento con il maggior numero di pagine: ");
        testoPiuLungo.mostraDettagli();
        System.out.println("Media delle pagine di tutti gli elementi: " + mediaPagine);
    }

    public static void main(String[] args) throws DuplicatoException {
        System.out.println("Benvenuto nel nostro Catalogo! Premi: ");

        boolean esecuzione = true;

        while (esecuzione) {
            System.out.println("0- Per uscire dal Catalogo!");
            System.out.println("1- Aggiungi un nuovo testo al catalogo");
            System.out.println("2- Rimuovi una pubblicazione con ISBN");
            System.out.println("3- Ricerca una pubblicazione con ISBN");
            System.out.println("4- Ricerca le pubblicazioni per anno di pubblicazione");
            System.out.println("5- Ricerca le pubblicazioni per autore");
            System.out.println("6- Ricerca una pubblicazione per titolo");
            System.out.println("7- I tuoi prestiti");
            System.out.println("8- Prestiti scaduti");
            System.out.println("9- Aggiorna una pubblicazione esistente");
            System.out.println("10- Mostra statistiche del catalogo");

            try {
                int sceltaUtente = scanner.nextInt();
                scanner.nextLine();

                switch (sceltaUtente) {
                    case 0:
                        System.out.println("Grazie per aver utilizzato il nostro catalogo. Arrivederci!");
                        esecuzione = false;
                        return;
                    case 1:
                        Archivio.aggiungiAlCatalogo();
                        break;
                    case 2:
                        System.out.println("Inserisci il codice ISBN");
                        String codiceIsbnRimozione = scanner.nextLine();
                        try {
                            Archivio.rimuoviConISBN(codiceIsbnRimozione);
                        } catch (IsbnNonTrovatoException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 3:
                        System.out.println("Inserisci il codice ISBN");
                        String codiceIsbn = scanner.nextLine();
                        try {
                            Archivio.ricercaConISBN(codiceIsbn);
                        } catch (IsbnNonTrovatoException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 4:
                        System.out.println("Inserisci anno di pubblicazione");
                        int annoPubblicazione = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            Archivio.ricercaConAnnoPubblicazione(annoPubblicazione);
                        } catch (AnnoPubblicazioneException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 5:
                        System.out.println("Inserisci il nome dell'autore, o parte di esso");
                        String nomeAutore = scanner.nextLine();
                        try {
                            Archivio.ricercaPerAutore(nomeAutore);
                        } catch (AutoreNonTrovatoException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 6:
                        System.out.println("Inserisci il titolo della pubblicazione,o parte di esso");
                        String titolo = scanner.nextLine();
                        try {
                            Archivio.ricercaPerTitolo(titolo);
                        } catch (TitoloNonTrovatoException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 7:
                        System.out.println("Inserisci il numero della tua tessera");
                        String numeroDiTessera = scanner.nextLine();
                        try {
                            Archivio.ricercaPrestitiUtente(numeroDiTessera);
                        } catch (PrestitiException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;

                    case 8:
                        try {
                            Archivio.ricercaPrestitiScaduti();
                        } catch (PrestitiException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 9:
                        System.out.println("Inserisci il codice ISBN");
                        String codiceIsbnModifica = scanner.nextLine();
                        try {
                            Archivio.aggiornaTestoPresenteInCatalogo(codiceIsbnModifica);
                        } catch (IsbnNonTrovatoException e) {
                            LOGGER.error(e::getMessage);
                        }
                        break;
                    case 10:
                        Archivio.mostraStatisticheCatalogo();
                        break;
                    default:
                        System.out.println("Scelta non valida. Per favore, inserisci un numero tra 0 e 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci solo numeri in questo campo!");
                scanner.nextLine();
            }

            System.out.print("Vuoi ancora interagire con il nostro catalogo? (si/no): ");
            String risposta = scanner.nextLine().toLowerCase();
            if (risposta.equals("no")) {
                System.out.println("Grazie per aver utilizzato il nostro catalogo. Arrivederci!");
                esecuzione = false;
            } else if (!risposta.equals("si")) {
                System.out.println("Inserisci soltanto si o no!");
            }
        }

        scanner.close();
    }
}

