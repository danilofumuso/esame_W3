package it.epicode.catalogo.dao;

import it.epicode.catalogo.entity.Catalogo;
import it.epicode.catalogo.entity.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LibroDAO {
    private EntityManager em;

    public void save(Libro oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public void update(Libro oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Libro oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }

    public void insertAll(List<Libro> listaLibri) {
        em.getTransaction().begin();
        for (Libro l : listaLibri) {
            em.persist(l);
        }
        em.getTransaction().commit();
    }

    public Libro findById(Long id) {
        return em.find(Libro.class, id);
    }

    public List<Libro> findAllLibri() {
        return em.createNamedQuery("Trova_tutto_Libro", Libro.class).getResultList();
    }




}