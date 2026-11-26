package poo;

import java.util.List;

import model.ContenidoAudiovisual;
import repository.RepositorioCsv;

public class PruebaCsv {

    public static void main(String[] args) {
        RepositorioCsv repositorio = new RepositorioCsv("datos.csv");
        List<ContenidoAudiovisual> contenidos = repositorio.cargar();

        System.out.println("Contenidos leidos: " + contenidos.size());
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
        }

        new RepositorioCsv("salida.csv").guardar(contenidos);
        System.out.println("Archivo salida.csv guardado.");
    }
}