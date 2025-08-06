package com.javier.literaturachallenge.dtos;

public class BookImportDTO {

    private String titulo;
    private Integer cantidadDescargas;
    private String idiomaPrincipal;
    private String nombreAutor;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    // Constructor con limpieza
    public BookImportDTO(String titulo, Integer cantidadDescargas, String idiomaPrincipal,
                         String nombreAutor, Integer anoNacimiento, Integer anoFallecimiento) {
        this.titulo = limpiarTitulo(titulo);
        this.cantidadDescargas = cantidadDescargas != null ? cantidadDescargas : 0;
        this.idiomaPrincipal = idiomaPrincipal != null ? idiomaPrincipal : "desconocido";
        this.nombreAutor = nombreAutor != null ? nombreAutor.trim() : "Autor desconocido";
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
    }

    private String limpiarTitulo(String titulo) {
        if (titulo == null) return "Sin título";
        return titulo.length() > 255 ? titulo.substring(0, 255) : titulo.trim();
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public String getIdiomaPrincipal() {
        return idiomaPrincipal;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }
}