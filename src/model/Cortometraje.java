package model;

public class Cortometraje extends ContenidoAudiovisual {
    private String director;
    private String mensaje;
    private String premio;

    public Cortometraje(String titulo, int duracionEnMinutos, String genero,
                        String director, String mensaje, String premio) {
        super(titulo, duracionEnMinutos, genero);
        this.director = director;
        this.mensaje = mensaje;
        this.premio = premio;
    }
    
    public String getDirector() {
        return director;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getPremio() {
        return premio;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del cortometraje:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Director: " + director);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Premio: " + premio);
        System.out.println();
    }
}