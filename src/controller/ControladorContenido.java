package controller;

import java.util.List;

import model.ContenidoAudiovisual;
import model.Pelicula;
import repository.RepositorioContenido;
import view.VistaConsola;

public class ControladorContenido {

    private static final int OPCION_VER = 1;
    private static final int OPCION_AGREGAR_PELICULA = 2;
    private static final int OPCION_GUARDAR = 3;
    private static final int OPCION_SALIR = 0;

    private final RepositorioContenido repositorio;
    private final VistaConsola vista;
    private final List<ContenidoAudiovisual> contenidos;

    public ControladorContenido(RepositorioContenido repositorio, VistaConsola vista) {
        this.repositorio = repositorio;
        this.vista = vista;
        this.contenidos = repositorio.cargar();
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != OPCION_SALIR);
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case OPCION_VER:
                vista.mostrarContenidos(contenidos);
                break;
            case OPCION_AGREGAR_PELICULA:
                agregarPelicula();
                break;
            case OPCION_GUARDAR:
                guardarContenidos();
                break;
            case OPCION_SALIR:
                vista.mostrarMensaje("Hasta pronto.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida.");
        }
    }

    private void agregarPelicula() {
        String titulo = vista.pedirTexto("Título");
        int duracion = vista.pedirNumero("Duración en minutos");
        String genero = vista.pedirTexto("Género");
        String estudio = vista.pedirTexto("Estudio");

        contenidos.add(new Pelicula(titulo, duracion, genero, estudio));
        vista.mostrarMensaje("Película agregada.");
    }

    private void guardarContenidos() {
        repositorio.guardar(contenidos);
        vista.mostrarMensaje("Datos guardados correctamente.");
    }
}