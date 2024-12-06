package it.epicode.catalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "libri")
@NamedQuery(name = "Trova_tutto_Libro", query = "SELECT l FROM Libro l")
public class Libro extends Catalogo {

    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;

    @Override
    public void mostraDettagli() {
        System.out.println("Libro trovato: " + getTitolo() +
                "\nAutore: " + getAutore() +
                "\nCodice ISBN: " + getCodiceISBN() +
                "\nNumero Pagine: " + getNumeroPagine() +
                "\nGenere: " + getGenere() +
                "\nAnno di Pubblicazione: " + getAnnoDiPubblicazione());
    }
}