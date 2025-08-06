package com.javier.literaturachallenge;

import com.javier.literaturachallenge.model.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class MyHttpClient {
    private final WebClient webClient;

    public MyHttpClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://gutendex.com").build();
    }

    public BookResponse consultarLibrosDesdeMenu(
            String idioma,           // Idioma del libro (ej. 'en', 'es')
            String autor,            // Nombre del autor
            String titulo,           // Título del libro
            String tema,             // Tema relacionado
            Integer anoNacimiento,   // Año de nacimiento del autor
            Integer anoFallecimiento,// Año de fallecimiento del autor
            Integer pagina,          // Número de página
            String ordenamiento      // Orden (ej. 'downloads')
    ) {
        String endpoint = construirEndpoint(
                idioma, autor, titulo, tema,
                anoNacimiento, anoFallecimiento,
                pagina, ordenamiento
        );

        System.out.println("🔗 Endpoint generado: " + endpoint);

        Mono<BookResponse> response = webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(BookResponse.class);

        return response.block();
    }

    private String construirEndpoint(
            String idioma, String autor, String titulo, String tema,
            Integer anoNacimiento, Integer anoFallecimiento,
            Integer pagina, String ordenamiento
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/books");

        // 🌐 Idioma
        if (idioma != null && !idioma.isEmpty()) {
            builder.queryParam("languages", idioma);
        }

        // 🔍 Búsqueda compuesta
        StringBuilder searchBuilder = new StringBuilder();
        if (titulo != null && !titulo.isEmpty()) {
            searchBuilder.append(titulo).append(" ");
        }
        if (autor != null && !autor.isEmpty()) {
            searchBuilder.append(autor).append(" ");
        }
        if (tema != null && !tema.isEmpty()) {
            searchBuilder.append(tema).append(" ");
        }
        if (anoNacimiento != null) {
            searchBuilder.append("born ").append(anoNacimiento).append(" ");
        }
        if (anoFallecimiento != null) {
            searchBuilder.append("died ").append(anoFallecimiento).append(" ");
        }

        String search = searchBuilder.toString().trim();
        if (!search.isEmpty()) {
            builder.queryParam("search", search);
        }

        // 📄 Página
        if (pagina != null && pagina > 0) {
            builder.queryParam("page", pagina);
        }

        // 📊 Ordenamiento
        if (ordenamiento != null && !ordenamiento.isEmpty()) {
            builder.queryParam("sort", ordenamiento);
        }

        return builder.toUriString();
    }
}