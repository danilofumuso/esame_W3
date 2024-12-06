package it.epicode.catalogo.dao;

import it.epicode.catalogo.entity.Catalogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public abstract class CatalogoDAO {
    private static EntityManager em;

    public static void save(Catalogo oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public static void update(Catalogo oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Catalogo oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public static List<Catalogo> findAll() {
        return em.createNamedQuery("Trova_tutto_Catalogo", Catalogo.class).getResultList();
    }

    public static Catalogo removeFromCatalogo(String codiceISBN) {
        em.getTransaction().begin();
        TypedQuery<Catalogo> query = em.createQuery("SELECT p FROM Catalogo p WHERE p.codiceISBN = :codiceISBN", Catalogo.class);
        query.setParameter("codiceISBN", codiceISBN);

        Catalogo pubblicazione = query.getResultList().stream().findFirst().orElse(null);

        if (pubblicazione != null) {
            em.remove(pubblicazione);
        }

        em.getTransaction().commit();

        return pubblicazione;
    }

    public static Catalogo findByISBN(String codiceISBN) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT p FROM Catalogo p WHERE p.codiceISBN = :codiceISBN", Catalogo.class);
        query.setParameter("codiceISBN", codiceISBN);
        List<Catalogo> results = query.getResultList();
        return results.isEmpty() ? null : results.getFirst();
    }

    public static List<Catalogo> findByAnnoDiPubblicazione(int annoDiPubblicazione) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT p FROM Catalogo p WHERE p.annoDiPubblicazione = :annoDiPubblicazione", Catalogo.class);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return query.getResultList();
    }

    public static List<Catalogo> findByAutore(String autore) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT l FROM Libro l WHERE l.autore LIKE :autore", Catalogo.class);
        query.setParameter("autore", "%" + autore + "%");
        return query.getResultList();
    }

    public static List<Catalogo> findByPartialTitolo(String titolo) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT p FROM Catalogo p WHERE p.titolo LIKE :titolo", Catalogo.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

    public static List<Catalogo> findPrestitiOfUtente(String numeroDiTessera) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT p.elementoPrestato FROM Prestito p " +
                        "WHERE p.utente.numeroDiTessera = :numeroDiTessera " +
                        "AND p.dataRestituzioneEffettiva IS NULL",
                Catalogo.class
        );
        query.setParameter("numeroDiTessera", numeroDiTessera);
        return query.getResultList();
    }

    public static List<Catalogo> findPrestitiScaduti() {
        LocalDate dataOdierna = LocalDate.now();
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT p.elementoPrestato FROM Prestito p " +
                        "WHERE p.dataRestituzionePrevista < :dataOdierna " +
                        "AND p.dataRestituzioneEffettiva IS NULL",
                Catalogo.class
        );
        query.setParameter("dataOdierna", dataOdierna);
        return query.getResultList();
    }


}
