package model;

public class Actor {
    private String nombre;
    private String personaje;
    private String nacionalidad;
    private int anioNacimiento;

    public Actor(String nombre, String personaje, String nacionalidad, int anioNacimiento) {
        this.nombre = nombre;
        this.personaje = personaje;
        this.nacionalidad = nacionalidad;
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPersonaje() {
        return personaje;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void mostrarDetalles() {
        System.out.println("Actor: " + nombre);
        System.out.println("Personaje: " + personaje);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Año de nacimiento: " + anioNacimiento);
    }
}