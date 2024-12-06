package it.epicode.catalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "catologo")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Trova_tutto_Catalogo", query = "SELECT p FROM Catalogo p")
public abstract class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_isbn", nullable = false)
    private String codiceISBN;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "anno_di_pubblicazione", nullable = false)
    private int annoDiPubblicazione;

    @Column(name = "numero_pagine", nullable = false)
    private int numeroPagine;

    @OneToMany(mappedBy = "elementoPrestato")
    private List<Prestito> prestiti = new ArrayList<>();

    public abstract void mostraDettagli();
}