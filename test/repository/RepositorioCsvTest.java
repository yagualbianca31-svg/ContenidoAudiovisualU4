package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import model.ContenidoAudiovisual;
import model.Pelicula;
import model.SerieDeTV;

class RepositorioCsvTest {

    @TempDir
    Path carpetaTemporal;

    private Path crearArchivo(String contenido) throws IOException {
        Path archivo = carpetaTemporal.resolve("datos.csv");
        Files.writeString(archivo, contenido);
        return archivo;
    }

    @Test
    void debeCargarUnaPeliculaConSuActor() throws IOException {
        Path archivo = crearArchivo(
                "PELICULA;The Notebook;123;Drama;New Line;Ryan Gosling;Noah;Canadiense;1980\n");

        List<ContenidoAudiovisual> contenidos = new RepositorioCsv(archivo.toString()).cargar();

        assertEquals(1, contenidos.size());
        Pelicula pelicula = (Pelicula) contenidos.get(0);
        assertEquals("The Notebook", pelicula.getTitulo());
        assertEquals("Ryan Gosling", pelicula.actor.getNombre());
    }

    @Test
    void debeIgnorarLineasEnBlancoYTiposDesconocidos() throws IOException {
        Path archivo = crearArchivo("\nOTRO;Algo;10;Genero\nSERIE;Sheldon;22;Comedia;7;0\n");

        List<ContenidoAudiovisual> contenidos = new RepositorioCsv(archivo.toString()).cargar();

        assertEquals(1, contenidos.size());
    }

    @Test
    void debeIgnorarLineasConNumeroInvalido() throws IOException {
        Path archivo = crearArchivo(
                "PELICULA;Mala;abc;Drama;Estudio;;;;\nSERIE;Sheldon;22;Comedia;7;0\n");

        List<ContenidoAudiovisual> contenidos = new RepositorioCsv(archivo.toString()).cargar();

        assertEquals(1, contenidos.size());
    }

    @Test
    void debeDevolverListaVaciaSiElArchivoNoExiste() {
        Path inexistente = carpetaTemporal.resolve("noexiste.csv");

        List<ContenidoAudiovisual> contenidos = new RepositorioCsv(inexistente.toString()).cargar();

        assertTrue(contenidos.isEmpty());
    }

    @Test
    void debeGuardarYVolverACargarLosMismosDatos() {
        Path archivo = carpetaTemporal.resolve("salida.csv");
        List<ContenidoAudiovisual> originales = new ArrayList<>();
        originales.add(new Pelicula("Nemo", 100, "Animacion", "Pixar"));
        originales.add(new SerieDeTV("Sheldon", 22, "Comedia", 7));

        RepositorioCsv repositorio = new RepositorioCsv(archivo.toString());
        repositorio.guardar(originales);
        List<ContenidoAudiovisual> cargados = repositorio.cargar();

        assertEquals(2, cargados.size());
        Pelicula pelicula = (Pelicula) cargados.get(0);
        assertEquals("Nemo", pelicula.getTitulo());
        assertNull(pelicula.actor);
        SerieDeTV serie = (SerieDeTV) cargados.get(1);
        assertEquals(7, serie.getTemporadas());
    }
}