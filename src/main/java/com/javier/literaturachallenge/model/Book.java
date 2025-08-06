package com.javier.literaturachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @JsonAlias("id")
    private int identificador;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<Author> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int cantidadDescargas;

    // Getters y setters

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    // Métodos auxiliares para mostrar información de forma simple
    public String getPrimerAutor() {
        return autores != null && !autores.isEmpty() ? autores.get(0).getNombre() : "Autor desconocido";
    }

    public String getIdiomaPrincipal() {
        return idiomas != null && !idiomas.isEmpty() ? idiomas.get(0) : "Idioma no especificado";
    }

    @Override
    public String toString() {
        return "📘 Título: " + titulo +
                "\n👤 Autor: " + getPrimerAutor() +
                "\n🌐 Idioma: " + getIdiomaPrincipal() +
                "\n📥 Descargas: " + cantidadDescargas +
                "\n🆔 ID: " + identificador +
                "\n------------------------------";
    }
}