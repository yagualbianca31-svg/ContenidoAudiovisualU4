package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ModeloTest {

    @Test
    void peliculaDebeGuardarSusDatosYNacerSinActor() {
        Pelicula pelicula = new Pelicula("Nemo", 100, "Animacion", "Pixar");

        assertEquals("Nemo", pelicula.getTitulo());
        assertEquals(100, pelicula.getDuracionEnMinutos());
        assertEquals("Animacion", pelicula.getGenero());
        assertEquals("Pixar", pelicula.getEstudio());
        assertNull(pelicula.actor);
    }

    @Test
    void cadaContenidoDebeTenerUnIdDistinto() {
        Pelicula primera = new Pelicula("A", 90, "Drama", "X");
        Pelicula segunda = new Pelicula("B", 95, "Drama", "X");

        assertNotEquals(primera.getId(), segunda.getId());
    }

    @Test
    void serieDebeCrearSuTemporadaConElNumeroIndicado() {
        SerieDeTV serie = new SerieDeTV("Sheldon", 22, "Comedia", 7);

        assertEquals(7, serie.getTemporadas());
        assertNotNull(serie.getTemporada());
        assertEquals(7, serie.getTemporada().getNumero());
    }

    @Test
    void documentalDebeIniciarSinInvestigadorYPermitirAsignarlo() {
        Documental documental = new Documental("Naturaleza", 45, "Naturaleza", "Animales");
        assertNull(documental.getInvestigador());

        documental.setInvestigador(new Investigador("David", "Naturaleza", "BBC"));

        assertEquals("David", documental.getInvestigador().getNombre());
    }

    @Test
    void videoMusicalYCortometrajeDebenGuardarSusDatos() {
        VideoMusical video = new VideoMusical("Video", 5, "Balada", "Bryan Adams", "Cancion", "A&M");
        Cortometraje corto = new Cortometraje("Cuerdas", 11, "Animacion", "Pedro", "Amistad", "Goya");

        assertEquals("Bryan Adams", video.getArtista());
        assertEquals("Pedro", corto.getDirector());
        assertEquals("Goya", corto.getPremio());
    }
}