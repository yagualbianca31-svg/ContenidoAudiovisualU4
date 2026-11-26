package view;

import java.util.List;
import java.util.Scanner;

import model.ContenidoAudiovisual;

public class VistaConsola {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println();
        System.out.println("=== CONTENIDO AUDIOVISUAL ===");
        System.out.println("1. Ver todos los contenidos");
        System.out.println("2. Agregar una película");
        System.out.println("3. Guardar en archivo");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    public int leerOpcion() {
        return leerEntero();
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine().trim();
    }

    public int pedirNumero(String mensaje) {
        System.out.print(mensaje + ": ");
        return leerEntero();
    }

    public void mostrarContenidos(List<ContenidoAudiovisual> contenidos) {
        if (contenidos.isEmpty()) {
            mostrarMensaje("No hay contenidos registrados.");
            return;
        }
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}