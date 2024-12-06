package it.epicode.catalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "riviste")
@NamedQuery(name = "Trova_tutto_Rivista", query = "SELECT a FROM Rivista a")
public class Rivista extends Catalogo {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    @Override
    public void mostraDettagli() {
        System.out.println("Rivista trovata: " + getTitolo() +
                "\nCodice ISBN: " + getCodiceISBN() +
                "\nNumero Pagine: " + getNumeroPagine() +
                "\nAnno di Pubblicazione: " + getAnnoDiPubblicazione() +
                "\nPeriodicità: " + getPeriodicita());
    }
}