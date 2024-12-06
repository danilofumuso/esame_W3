package it.epicode.catalogo.dao;

import it.epicode.catalogo.entity.Libro;
import it.epicode.catalogo.entity.Rivista;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RivistaDAO {
    private EntityManager em;

    public void save(Rivista oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public void update(Rivista oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Rivista oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public void insertAll(List<Rivista> listaRiviste) {
        em.getTransaction().begin();
        for (Rivista r : listaRiviste) {
            em.persist(r);
        }
        em.getTransaction().commit();
    }

    public Rivista findById(Long id) {
        return em.find(Rivista.class, id);
    }

    public List<Rivista> findAllRiviste() {
        return em.createNamedQuery("Trova_tutto_Rivista", Rivista.class).getResultList();
    }

}