package com.javier.literaturachallenge.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer cantidadDescargas;

    private String idiomaPrincipal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author autor;

    // Constructores
    public Book() {}

    public Book(String titulo, Integer cantidadDescargas, String idiomaPrincipal, Author autor) {
        this.titulo = titulo;
        this.cantidadDescargas = cantidadDescargas;
        this.idiomaPrincipal = idiomaPrincipal;
        this.autor = autor;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public String getIdiomaPrincipal() {
        return idiomaPrincipal;
    }

    public void setIdiomaPrincipal(String idiomaPrincipal) {
        this.idiomaPrincipal = idiomaPrincipal;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro: " + titulo +
                " | Idioma: " + idiomaPrincipal +
                " | Descargas: " + cantidadDescargas +
                " | Autor: " + (autor != null ? autor.getNombre() : "Desconocido");
    }
}