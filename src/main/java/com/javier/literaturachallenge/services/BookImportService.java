package com.javier.literaturachallenge.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.javier.literaturachallenge.dtos.BookImportDTO;
import com.javier.literaturachallenge.entities.Author;
import com.javier.literaturachallenge.entities.Book;
import com.javier.literaturachallenge.repositories.AuthorRepository;
import com.javier.literaturachallenge.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BookImportService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    public BookImportService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.restTemplate = new RestTemplate();
    }

    public void importarLibros() {
        String url = "https://gutendex.com/books";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        JsonNode books = response.getBody().get("results");

        for (JsonNode bookNode : books) {
            JsonNode authorsNode = bookNode.get("authors").get(0); // solo el primero por simplicidad

            BookImportDTO dto = new BookImportDTO(
                    bookNode.get("title").asText(),
                    bookNode.has("download_count") && !bookNode.get("download_count").isNull()
                            ? bookNode.get("download_count").asInt()
                            : 0,
                    bookNode.has("languages") && bookNode.get("languages").size() > 0
                            ? bookNode.get("languages").get(0).asText()
                            : "desconocido",
                    authorsNode.get("name").asText(),
                    authorsNode.get("birth_year").isNull() ? null : authorsNode.get("birth_year").asInt(),
                    authorsNode.get("death_year").isNull() ? null : authorsNode.get("death_year").asInt()
            );

            // Buscar autor por nombre
            Optional<Author> autorExistente = authorRepository.findByNombre(dto.getNombreAutor());
            Author autor;

            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Author(dto.getNombreAutor(), dto.getAnoNacimiento(), dto.getAnoFallecimiento());
                autor = authorRepository.save(autor);
            }

            // Verificar si el libro ya existe por título y autor
            boolean libroExiste = bookRepository.existsByTituloAndAutor(dto.getTitulo(), autor);
            if (!libroExiste) {
                Book libro = new Book(
                        dto.getTitulo(),
                        dto.getCantidadDescargas(),
                        dto.getIdiomaPrincipal(),
                        autor
                );
                bookRepository.save(libro);
            }
        }
    }
}