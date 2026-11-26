package repository;

import java.util.List;

import model.ContenidoAudiovisual;

public interface RepositorioContenido {

    List<ContenidoAudiovisual> cargar();

    void guardar(List<ContenidoAudiovisual> contenidos);
}