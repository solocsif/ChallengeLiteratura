package com.javier.literaturachallenge.controllers;

import com.javier.literaturachallenge.services.BookImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final BookImportService bookImportService;

    public ImportController(BookImportService bookImportService) {
        this.bookImportService = bookImportService;
    }

    @PostMapping("/books")
    public ResponseEntity<String> importarLibros() {
        bookImportService.importarLibros();
        return ResponseEntity.ok("Libros importados correctamente");
    }
}