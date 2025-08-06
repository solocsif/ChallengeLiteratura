package com.javier.literaturachallenge;

import com.javier.literaturachallenge.model.BookResponse;
import com.javier.literaturachallenge.model.Book;
import com.javier.literaturachallenge.model.Author;
import com.javier.literaturachallenge.services.BookImportService;
import com.javier.literaturachallenge.services.EstadisticasService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class LiteraturaChallengeApplication implements CommandLineRunner {

    private final MyHttpClient myHttpClient;
    private final BookImportService bookImportService;
    private final EstadisticasService estadisticasService;

    public LiteraturaChallengeApplication(MyHttpClient myHttpClient, BookImportService bookImportService, EstadisticasService estadisticasService) {
        this.myHttpClient = myHttpClient;
        this.bookImportService = bookImportService;
        this.estadisticasService = estadisticasService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mostrarMenu();
    }

    private void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n📚 Menú de Consulta de Libros");
            System.out.println("1 - Consultar libros por página");
            System.out.println("2 - Buscar libros por palabra clave");
            System.out.println("3 - Buscar libro por título y guardar en catálogo");
            System.out.println("4 - Mostrar todos los libros guardados");
            System.out.println("5 - Mostrar libros por idioma");
            System.out.println("6 - Mostrar autores consultados");
            System.out.println("7 - Mostrar autores vivos en determinado año");
            System.out.println("8 - Importar libros desde Gutendex y guardar en base de datos");
            System.out.println("9 - Mostrar estadísticas de libros por idioma");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> consultarPorPagina(scanner);
                    case 2 -> buscarPorPalabraClave(scanner);
                    case 3 -> buscarYGuardarPorTitulo(scanner);
                    case 4 -> mostrarCatalogo();
                    case 5 -> mostrarPorIdioma(scanner);
                    case 6 -> mostrarAutoresDesdeAPI();
                    case 7 -> buscarAutoresVivosPorAno(scanner);
                    case 8 -> importarLibrosDesdeAPI();
                    case 0 -> System.out.println("¡Hasta luego! 👋");
                    case 9 -> mostrarEstadisticasPorIdioma();
                    default -> System.out.println("❌ Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Debe ser un número.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private final List<Book> catalogo = new ArrayList<>();
    private final Set<Author> autoresGuardados = new HashSet<>();

    private void consultarPorPagina(Scanner scanner) {
        System.out.print("Ingrese el número de página: ");
        try {
            int pagina = Integer.parseInt(scanner.nextLine());
            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, null, null, null, null, pagina, null);
            imprimirLibros(response.getLibros());
        } catch (Exception e) {
            System.out.println("❌ Error al consultar libros: " + e.getMessage());
        }
    }

    private void mostrarEstadisticasPorIdioma() {
        System.out.println("📊 Estadísticas de libros por idioma en la base de datos:");
        Map<String, Long> estadisticas = estadisticasService.obtenerCantidadPorIdioma();

        if (estadisticas.isEmpty()) {
            System.out.println("📭 No hay libros registrados.");
        } else {
            estadisticas.forEach((idioma, cantidad) ->
                    System.out.println("🌐 Idioma '" + idioma + "': " + cantidad + " libro(s)")
            );
        }
    }


    private void buscarPorPalabraClave(Scanner scanner) {
        System.out.print("Ingrese palabra clave: ");
        String keyword = scanner.nextLine();
        try {
            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, keyword, null, null, null, null, null);
            imprimirLibros(response.getLibros());
        } catch (Exception e) {
            System.out.println("❌ Error al buscar libros: " + e.getMessage());
        }
    }

    private void buscarYGuardarPorTitulo(Scanner scanner) {
        System.out.print("🔎 Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        try {
            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, titulo, null, null, null, null, null);
            if (response != null && !response.getLibros().isEmpty()) {
                Book libro = response.getLibros().get(0);
                catalogo.add(libro);
                libro.getAutores().forEach(autoresGuardados::add);
                System.out.println("✅ Libro guardado en el catálogo:");
                System.out.println(libro);
            } else {
                System.out.println("⚠️ No se encontró ningún libro con ese título.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar libro: " + e.getMessage());
        }
    }

    private void mostrarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("📭 Catálogo vacío.");
        } else {
            System.out.println("📘 Libros guardados:");
            catalogo.forEach(System.out::println);
        }
    }

    private void mostrarPorIdioma(Scanner scanner) {
        System.out.print("🌐 Ingrese el código de idioma (ej. 'en', 'es'): ");
        String idioma = scanner.nextLine().trim();

        try {
            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, null, idioma, null, null, null, null);
            List<Book> libros = response.getLibros();
            if (libros.isEmpty()) {
                System.out.println("⚠️ No se encontraron libros en ese idioma.");
            } else {
                System.out.println("📗 Libros en idioma '" + idioma + "':");
                libros.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar libros: " + e.getMessage());
        }
    }

    private void mostrarAutoresDesdeAPI() {
        try {
            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, null, null, null, null, 1, null);
            Set<Author> autores = new HashSet<>();
            for (Book libro : response.getLibros()) {
                autores.addAll(libro.getAutores());
            }

            if (autores.isEmpty()) {
                System.out.println("📭 No se encontraron autores.");
            } else {
                System.out.println("👥 Autores consultados desde API:");
                autores.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar autores: " + e.getMessage());
        }
    }

    private void buscarAutoresVivosPorAno(Scanner scanner) {
        System.out.print("📅 Ingrese el año (ej. 1900): ");
        try {
            int año = Integer.parseInt(scanner.nextLine());

            BookResponse response = myHttpClient.consultarLibrosDesdeMenu(null, null, null, null, null, null, 1, null);

            List<Author> vivos = new ArrayList<>();
            for (Book libro : response.getLibros()) {
                for (Author autor : libro.getAutores()) {
                    if (autor.getAnoNacimiento() != null &&
                            autor.getAnoNacimiento() <= año &&
                            (autor.getAnoFallecimiento() == null || autor.getAnoFallecimiento() > año)) {
                        vivos.add(autor);
                    }
                }
            }

            if (vivos.isEmpty()) {
                System.out.println("⚠️ No se encontraron autores vivos en ese año.");
            } else {
                System.out.println("🧬 Autores vivos en " + año + ":");
                vivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Año inválido.");
        } catch (Exception e) {
            System.out.println("❌ Error al consultar autores vivos: " + e.getMessage());
        }
    }

    private void importarLibrosDesdeAPI() {
        try {
            System.out.println("⏳ Importando libros desde Gutendex...");
            bookImportService.importarLibros();
            System.out.println("✅ Libros importados y guardados en la base de datos.");
        } catch (Exception e) {
            System.out.println("❌ Error al importar libros: " + e.getMessage());
        }
    }

    private void imprimirLibros(List<Book> libros) {
        if (libros.isEmpty()) {
            System.out.println("📭 No se encontraron resultados.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}