package poo;

import controller.ControladorContenido;
import repository.RepositorioContenido;
import repository.RepositorioCsv;
import view.VistaConsola;

public class Main {

    public static void main(String[] args) {
        RepositorioContenido repositorio = new RepositorioCsv("datos.csv");
        VistaConsola vista = new VistaConsola();

        ControladorContenido controlador = new ControladorContenido(repositorio, vista);
        controlador.iniciar();
    }
}