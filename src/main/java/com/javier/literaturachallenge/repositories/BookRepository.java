package com.javier.literaturachallenge.repositories;

import com.javier.literaturachallenge.entities.Book;
import com.javier.literaturachallenge.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTituloAndAutor(String titulo, Author autor);
    long countByIdiomaPrincipal(String idiomaPrincipal);
}