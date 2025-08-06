package com.javier.literaturachallenge.controller;

import com.javier.literaturachallenge.MyHttpClient;
import com.javier.literaturachallenge.model.BookResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final MyHttpClient myHttpClient;

    public BookController(MyHttpClient myHttpClient) {
        this.myHttpClient = myHttpClient;
    }

    @GetMapping("/libros")
    public BookResponse getLibros(
            @RequestParam(required = false) String idioma,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String tema,
            @RequestParam(required = false) Integer anoNacimiento,
            @RequestParam(required = false) Integer anoFallecimiento,
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) String ordenamiento
    ) {
        return myHttpClient.consultarLibrosDesdeMenu(
                idioma,
                autor,
                titulo,
                tema,
                anoNacimiento,
                anoFallecimiento,
                pagina,
                ordenamiento
        );
    }
}