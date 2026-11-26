package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Actor;
import model.ContenidoAudiovisual;
import model.Cortometraje;
import model.Documental;
import model.Investigador;
import model.Pelicula;
import model.SerieDeTV;
import model.Temporada;
import model.VideoMusical;

public class RepositorioCsv implements RepositorioContenido {

    private static final String SEPARADOR = ";";

    private final String rutaArchivo;

    public RepositorioCsv(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<ContenidoAudiovisual> cargar() {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        try (BufferedReader lector = Files.newBufferedReader(Paths.get(rutaArchivo), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                agregarSiEsValida(contenidos, linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
        return contenidos;
    }

    @Override
    public void guardar(List<ContenidoAudiovisual> contenidos) {
        try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(rutaArchivo), StandardCharsets.UTF_8)) {
            for (ContenidoAudiovisual contenido : contenidos) {
                escribirLinea(escritor, contenido);
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    private void agregarSiEsValida(List<ContenidoAudiovisual> contenidos, String linea) {
        if (linea.isBlank()) {
            return;
        }
        try {
            ContenidoAudiovisual contenido = convertirLinea(linea);
            if (contenido != null) {
                contenidos.add(contenido);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Línea ignorada por formato incorrecto: " + linea);
        }
    }

    private void escribirLinea(BufferedWriter escritor, ContenidoAudiovisual contenido) throws IOException {
        String linea = convertirContenido(contenido);
        if (linea != null) {
            escritor.write(linea);
            escritor.newLine();
        }
    }

    private ContenidoAudiovisual convertirLinea(String linea) {
        String[] campos = linea.split(SEPARADOR, -1);
        switch (campos[0]) {
            case "PELICULA":
                return crearPelicula(campos);
            case "SERIE":
                return crearSerie(campos);
            case "DOCUMENTAL":
                return crearDocumental(campos);
            case "VIDEOMUSICAL":
                return crearVideoMusical(campos);
            case "CORTOMETRAJE":
                return crearCortometraje(campos);
            default:
                return null;
        }
    }

    private Pelicula crearPelicula(String[] c) {
        Pelicula pelicula = new Pelicula(c[1], Integer.parseInt(c[2]), c[3], c[4]);
        if (!c[5].isEmpty()) {
            pelicula.actor = new Actor(c[5], c[6], c[7], Integer.parseInt(c[8]));
        }
        return pelicula;
    }

    private SerieDeTV crearSerie(String[] c) {
        int temporadas = Integer.parseInt(c[4]);
        SerieDeTV serie = new SerieDeTV(c[1], Integer.parseInt(c[2]), c[3], temporadas);
        serie.setTemporada(new Temporada(temporadas, Integer.parseInt(c[5])));
        return serie;
    }

    private Documental crearDocumental(String[] c) {
        Documental documental = new Documental(c[1], Integer.parseInt(c[2]), c[3], c[4]);
        if (!c[5].isEmpty()) {
            documental.setInvestigador(new Investigador(c[5], c[6], c[7]));
        }
        return documental;
    }

    private VideoMusical crearVideoMusical(String[] c) {
        return new VideoMusical(c[1], Integer.parseInt(c[2]), c[3], c[4], c[5], c[6]);
    }

    private Cortometraje crearCortometraje(String[] c) {
        return new Cortometraje(c[1], Integer.parseInt(c[2]), c[3], c[4], c[5], c[6]);
    }

    private String convertirContenido(ContenidoAudiovisual contenido) {
        if (contenido instanceof Pelicula) {
            return convertirPelicula((Pelicula) contenido);
        }
        if (contenido instanceof SerieDeTV) {
            return convertirSerie((SerieDeTV) contenido);
        }
        if (contenido instanceof Documental) {
            return convertirDocumental((Documental) contenido);
        }
        if (contenido instanceof VideoMusical) {
            return convertirVideoMusical((VideoMusical) contenido);
        }
        if (contenido instanceof Cortometraje) {
            return convertirCortometraje((Cortometraje) contenido);
        }
        return null;
    }

    private String convertirPelicula(Pelicula p) {
        Actor actor = p.actor;
        if (actor == null) {
            return unir("PELICULA", p.getTitulo(), String.valueOf(p.getDuracionEnMinutos()),
                    p.getGenero(), p.getEstudio(), "", "", "", "");
        }
        return unir("PELICULA", p.getTitulo(), String.valueOf(p.getDuracionEnMinutos()),
                p.getGenero(), p.getEstudio(), actor.getNombre(), actor.getPersonaje(),
                actor.getNacionalidad(), String.valueOf(actor.getAnioNacimiento()));
    }

    private String convertirSerie(SerieDeTV s) {
        int episodios = (s.getTemporada() != null) ? s.getTemporada().getEpisodios() : 0;
        return unir("SERIE", s.getTitulo(), String.valueOf(s.getDuracionEnMinutos()),
                s.getGenero(), String.valueOf(s.getTemporadas()), String.valueOf(episodios));
    }

    private String convertirDocumental(Documental d) {
        Investigador inv = d.getInvestigador();
        if (inv == null) {
            return unir("DOCUMENTAL", d.getTitulo(), String.valueOf(d.getDuracionEnMinutos()),
                    d.getGenero(), d.getTema(), "", "", "");
        }
        return unir("DOCUMENTAL", d.getTitulo(), String.valueOf(d.getDuracionEnMinutos()),
                d.getGenero(), d.getTema(), inv.getNombre(), inv.getEspecialidad(), inv.getInstitucion());
    }

    private String convertirVideoMusical(VideoMusical v) {
        return unir("VIDEOMUSICAL", v.getTitulo(), String.valueOf(v.getDuracionEnMinutos()),
                v.getGenero(), v.getArtista(), v.getCancion(), v.getProductora());
    }

    private String convertirCortometraje(Cortometraje c) {
        return unir("CORTOMETRAJE", c.getTitulo(), String.valueOf(c.getDuracionEnMinutos()),
                c.getGenero(), c.getDirector(), c.getMensaje(), c.getPremio());
    }

    private String unir(String... campos) {
        return String.join(SEPARADOR, campos);
    }
}