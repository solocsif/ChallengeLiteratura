package com.javier.literaturachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer anoNacimiento;

    @JsonAlias("death_year")
    private Integer anoFallecimiento;

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

    @Override
    public String toString() {
        return "Autor: " + nombre +
                " | Nacimiento: " + (anoNacimiento != null ? anoNacimiento : "N/D") +
                " | Fallecimiento: " + (anoFallecimiento != null ? anoFallecimiento : "Vivo");
    }
}