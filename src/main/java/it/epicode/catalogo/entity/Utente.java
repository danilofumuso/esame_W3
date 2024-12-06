package it.epicode.catalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "utenti")
@NamedQuery(name = "Trova_tutto_Utente", query = "SELECT u FROM Utente u")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_di_nascita", nullable = false)
    private LocalDate dataDiNascita;

    @Column(name = "numero_di_tessera", nullable = false)
    private String numeroDiTessera;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti = new ArrayList<>();

}