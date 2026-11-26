package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ActorTest {

    @Test
    void debeGuardarLosDatosRecibidosEnElConstructor() {
        Actor actor = new Actor("Ryan Gosling", "Noah Calhoun", "Canadiense", 1980);

        assertEquals("Ryan Gosling", actor.getNombre());
        assertEquals("Noah Calhoun", actor.getPersonaje());
        assertEquals("Canadiense", actor.getNacionalidad());
        assertEquals(1980, actor.getAnioNacimiento());
    }
}