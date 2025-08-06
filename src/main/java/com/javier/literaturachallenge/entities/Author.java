package com.javier.literaturachallenge.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer anoNacimiento;

    private Integer anoFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> libros = new ArrayList<>();

    // Constructores
    public Author() {}

    public Author(String nombre, Integer anoNacimiento, Integer anoFallecimiento) {
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public List<Book> getLibros() {
        return libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }

    public void agregarLibro(Book libro) {
        libros.add(libro);
        libro.setAutor(this);
    }

    public void eliminarLibro(Book libro) {
        libros.remove(libro);
        libro.setAutor(null);
    }

    @Override
    public String toString() {
        return "Autor: " + nombre +
                " | Nacimiento: " + (anoNacimiento != null ? anoNacimiento : "N/D") +
                " | Fallecimiento: " + (anoFallecimiento != null ? anoFallecimiento : "Vivo");
    }
}