package it.epicode.catalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="prestiti")
@NamedQuery(name = "Trova_tutto_Prestito", query = "SELECT a FROM Prestito a")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "pubblicazione_id")
    private Catalogo elementoPrestato;

    @Column(name="data_inizio_prestito",nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(name="data_restituzione_prevista",nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name="data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

}