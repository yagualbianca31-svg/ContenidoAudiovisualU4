# Contenido Audiovisual (Unidad 4: Código Limpio)

## Objetivo y propósito
Sistema en Java para gestionar contenidos audiovisuales (películas, series,
documentales, videos musicales y cortometrajes). Esta versión agrega manejo
de archivos CSV, código limpio, principios SOLID, patrón MVC y pruebas
unitarias con JUnit.

## Estructura del proyecto
- `src/model`: clases de negocio (ContenidoAudiovisual, Pelicula, SerieDeTV,
  Documental, VideoMusical, Cortometraje, Actor, Temporada, Investigador).
- `src/view`: `VistaConsola`, menú e interacción por consola.
- `src/controller`: `ControladorContenido`, une la vista con el modelo.
- `src/repository`: `RepositorioContenido` (interfaz) y `RepositorioCsv`
  (lectura y escritura del archivo CSV).
- `src/poo`: clase `Main` que arranca la aplicación.
- `test/`: pruebas unitarias con JUnit 5.
- `datos.csv`: datos iniciales del sistema.

## Cambios realizados
1. **Manejo de archivos:** `RepositorioCsv` lee `datos.csv` para crear los
   objetos y guarda el estado actual del sistema en el mismo formato.
2. **Código limpio:** nombres claros, métodos pequeños, sin código duplicado
   y comentarios solo donde aportan valor.
3. **SOLID:**
   - SRP: cada clase tiene una sola responsabilidad.
   - OCP/DIP: el controlador depende de la interfaz `RepositorioContenido`,
     no de una implementación concreta, y la recibe por constructor.
   - LSP: todas las subclases se usan como `ContenidoAudiovisual`.
   - ISP: la interfaz del repositorio tiene solo dos métodos (`cargar`, `guardar`).
4. **MVC:** modelo, vista y controlador separados en paquetes distintos.
5. **Pruebas unitarias:** casos normales y límite (archivo inexistente,
   líneas en blanco, números inválidos, tipos desconocidos).

## Formato del archivo CSV
Un contenido por línea, separado por punto y coma. La primera palabra indica
el tipo:

    PELICULA;titulo;duracion;genero;estudio;actor;personaje;nacionalidad;anio
    SERIE;titulo;duracion;genero;temporadas;episodios
    DOCUMENTAL;titulo;duracion;genero;tema;investigador;especialidad;institucion
    VIDEOMUSICAL;titulo;duracion;genero;artista;cancion;productora
    CORTOMETRAJE;titulo;duracion;genero;director;mensaje;premio

## Cómo clonar y ejecutar
1. Clonar: `git clone https://github.com/yagualbianca31-svg/ContenidoAudiovisualU4.git`
2. Abrir Eclipse: File > Import > Existing Projects into Workspace.
3. Ejecutar `src/poo/Main.java` con Run As > Java Application.
4. Usar el menú de la consola (ver contenidos, agregar película, guardar).

## Cómo ejecutar las pruebas
Clic derecho sobre la carpeta `test` > Run As > JUnit Test.