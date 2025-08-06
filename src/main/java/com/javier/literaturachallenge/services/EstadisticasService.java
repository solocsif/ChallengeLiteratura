package com.javier.literaturachallenge.services;

import com.javier.literaturachallenge.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadisticasService {

    private final BookRepository bookRepository;
    private final List<String> idiomasSoportados;

    // Constructor con inyección de BookRepository y configuración de idiomas
    public EstadisticasService(BookRepository bookRepository,
                               @Value("${estadisticas.idiomas}") String idiomas) {
        this.bookRepository = bookRepository;
        this.idiomasSoportados = List.of(idiomas.split(","));
    }

    public Map<String, Long> obtenerCantidadPorIdioma() {
        Map<String, Long> estadisticas = new LinkedHashMap<>();
        for (String idioma : idiomasSoportados) {
            estadisticas.put(idioma, bookRepository.countByIdiomaPrincipal(idioma));
        }
        return estadisticas;
    }
}